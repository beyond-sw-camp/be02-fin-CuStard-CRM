package com.example.backend.orders.service;

import com.example.backend.common.BaseException;
import com.example.backend.orders.model.entity.GetPortoneRes;
import com.example.backend.product.model.entity.Product;
import com.example.backend.product.repository.ProductRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.BindException;
import java.net.URL;

import java.util.Map;
import java.util.Optional;

import static com.example.backend.common.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final ProductRepository productRepository;
    private final IamportClient iamportClient;

    @Value("${imp.apiKey}")
    private String apiKey;

    @Value("${imp.secretKey}")
    private String secretKey;

    //PortOne 토큰 발급
    private String getToken() throws IOException {
        HttpsURLConnection conn = null;

        URL url = new URL("https://api.iamport.kr/users/getToken");

        //HTTP 프로토콜 만들어주기
        conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        //데이터를 담아서 보내겠다
        conn.setDoOutput(true);

        JsonObject json = new JsonObject();
        json.addProperty("imp_key", apiKey);
        json.addProperty("imp_secret", secretKey);

        //보내기
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(json.toString());
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        Gson gson = new Gson();
        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
        String token = gson.fromJson(response, Map.class).get("access_token").toString();

        br.close();
        conn.disconnect();

        return token;
    }

    //결제 정보를 가져옴
    public IamportResponse getPaymentInfo(String impUid) throws IamportResponseException, IOException,BaseException {
        IamportResponse<Payment> response = iamportClient.paymentByImpUid(impUid);
        return response;
    }

    //Portone에서 결제 정보 가져와서 검증 처리
    public Boolean paymentValidation(String impUid) throws IamportResponseException, IOException,BaseException {
        IamportResponse<Payment> response = getPaymentInfo(impUid);
        Integer price = response.getResponse().getAmount().intValue();
        String customDataString = response.getResponse().getCustomData();
        System.out.println(customDataString);
//        customDataString = "{\"products\":" + customDataString + "}";
        customDataString = customDataString.replace("[", "");
        customDataString = customDataString.replace("]", "");

        Gson gson = new Gson();
        GetPortoneRes getPortoneRes = gson.fromJson(customDataString, GetPortoneRes.class);

        Optional<Product> product = productRepository.findById(getPortoneRes.getId());
        if(product.isPresent()){
            Integer priceFromDb = product.get().getProductPrice();
            if(price.equals(priceFromDb) ) {
                return true;
            }else {
                throw new BaseException(ORDERS_VALIDATION_INCORRECT_INFOMATION);
            }
        }
        return null;
    }

}
