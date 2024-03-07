package com.example.backend.orders.service;

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


@Service
@RequiredArgsConstructor
public class OrdersService {
    private final PaymentService paymentService;
    private final OrdersRepository ordersRepository;

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
        Long consumerIdx = TokenProvider.getIdx(token);

        if (consumerIdx != null) {
            ordersRepository.save(Orders.dtoToEntity(impUid, consumerIdx, getPortoneRes.getId(), getPortoneRes.getPrice()));
            GetOrdersCreateRes getOrdersCreateRes = GetOrdersCreateRes.builder()
                    .impUid(impUid)
                    .productName(getPortoneRes.getName())
                    .price(getPortoneRes.getPrice())
                    .build();

            return ResponseEntity.ok(getOrdersCreateRes);
        }
        return null;
    }

}
