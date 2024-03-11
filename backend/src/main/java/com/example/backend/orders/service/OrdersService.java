package com.example.backend.orders.service;

import com.example.backend.customer.model.entity.Customer;
import com.example.backend.customer.repository.CustomerRepository;
import com.example.backend.orders.model.entity.GetPortoneRes;
import com.example.backend.orders.model.entity.Orders;
import com.example.backend.orders.model.response.GetOrdersCreateRes;
import com.example.backend.orders.repository.OrdersRepository;
import com.example.backend.utils.TokenProvider;
import com.google.gson.Gson;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrdersService {
    private final PaymentService paymentService;
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public ResponseEntity<GetOrdersCreateRes> createOrder(String token, String impUid) throws IamportResponseException, IOException {
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

            return ResponseEntity.ok(getOrdersCreateRes);
        }
        return null;
    }

}
