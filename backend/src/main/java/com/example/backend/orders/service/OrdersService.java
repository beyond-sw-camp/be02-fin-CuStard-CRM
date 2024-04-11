package com.example.backend.orders.service;

import com.example.backend.common.BaseException;
import com.example.backend.customer.model.entity.Customer;
import com.example.backend.customer.repository.CustomerRepository;
import com.example.backend.orders.model.entity.GetPortoneRes;
import com.example.backend.orders.model.entity.Orders;
import com.example.backend.orders.model.response.GetOrdersCreateRes;
import com.example.backend.orders.repository.OrdersRepository;
import com.example.backend.product.model.entity.Product;
import com.example.backend.product.repository.ProductRepository;
import com.example.backend.product.service.ProductService;
import com.example.backend.utils.TokenProvider;
import com.google.gson.Gson;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

import static com.example.backend.common.BaseResponseStatus.CUSTOMER_READ_FAIL;


@Service
@RequiredArgsConstructor
public class OrdersService {
    private final PaymentService paymentService;
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger("ordersLogger");

    @Transactional
    public GetOrdersCreateRes createOrder(String token, String impUid) throws IamportResponseException, IOException, BaseException {
        IamportResponse<Payment> iamportResponse = paymentService.getPaymentInfo(impUid);
        Integer amount = iamportResponse.getResponse().getAmount().intValue();
        String customDataString = iamportResponse.getResponse().getCustomData();
        customDataString = customDataString.replace("[", "");
        customDataString = customDataString.replace("]", "");

        Gson gson = new Gson();
        GetPortoneRes getPortoneRes = gson.fromJson(customDataString, GetPortoneRes.class);

        token = TokenProvider.replaceToken(token);
        Long customerIdx = TokenProvider.getIdx(token);

        Optional<Customer> result = customerRepository.findById(customerIdx);
        if (result.isPresent()) {
            Customer customer = result.get();
            ordersRepository.save(Orders.dtoToEntity(impUid, customer, getPortoneRes.getId(), getPortoneRes.getPrice()));
            GetOrdersCreateRes getOrdersCreateRes = GetOrdersCreateRes.builder()
                    .impUid(impUid)
                    .productName(getPortoneRes.getName())
                    .price(getPortoneRes.getPrice())
                    .build();

            customer.setTotalAmount(getPortoneRes.getPrice()+customer.getTotalAmount());
            customerRepository.save(customer);

            Optional<Product> product = productRepository.findById(getPortoneRes.getId());
            Integer category = product.get().getCategory();
            //주문 로그 남기기
            logger.info("[주문] 고객 번호: {}, 상품: {}, 카테고리:{}, 상품 가격: {}, impUid: {}", result.get().getIdx(), getPortoneRes.getId(), category, getPortoneRes.getPrice(), impUid );

            return getOrdersCreateRes;
        }
        throw new BaseException(CUSTOMER_READ_FAIL);
    }

}
