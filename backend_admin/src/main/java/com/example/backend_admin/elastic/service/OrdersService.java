package com.example.backend_admin.elastic.service;

import com.example.backend_admin.elastic.model.entity.OrdersDocument;
import com.example.backend_admin.elastic.model.entity.ProductDetailDocument;
import com.example.backend_admin.elastic.model.dto.GetCategoryOrdersRes;
import com.example.backend_admin.elastic.model.dto.GetTodayOrdersRes;
import com.example.backend_admin.elastic.repository.OrdersDocumentRepository;
import com.example.backend_admin.elastic.repository.ProductDetailDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private  final OrdersDocumentRepository ordersDocumentRepository;
    private final ProductDetailDocumentRepository productDetailDocumentRepository;
    public GetTodayOrdersRes countTodayOrders() {
        ZoneId zoneId = ZoneId.of("Asia/Seoul");

        // 오늘 00시
        LocalDateTime startOfToday = LocalDate.now(zoneId).atStartOfDay().plusHours(9);
        // 현재 시간
        LocalDateTime now = LocalDateTime.now(zoneId).plusHours(9);
        // 전날 00시
        LocalDateTime startOfYesterday = startOfToday.minusDays(1);
        // 전날 동시간대
        LocalDateTime sameTimeYesterday = now.minusDays(1);

        // LocalDateTime을 Date로 변환
        Date startOfTodayDate = Date.from(startOfToday.atZone(zoneId).toInstant());
        Date nowDate = Date.from(now.atZone(zoneId).toInstant());
        Date startOfYesterdayDate = Date.from(startOfYesterday.atZone(zoneId).toInstant());
        Date sameTimeYesterdayDate = Date.from(sameTimeYesterday.atZone(zoneId).toInstant());

        List<OrdersDocument> todayOrders = ordersDocumentRepository.findAllByTimestampBetween(startOfTodayDate,nowDate);
        List<OrdersDocument> yestOrders = ordersDocumentRepository.findAllByTimestampBetween(startOfYesterdayDate, sameTimeYesterdayDate);

        int todayOrderAmt = 0;
        int yestOrderAmt = 0;
        for (OrdersDocument order: todayOrders){
            todayOrderAmt += order.getPrice();
        }

        for (OrdersDocument order: yestOrders){
            yestOrderAmt += order.getPrice();
        }

        System.out.println( "오늘 결제건수: "+ todayOrders.size()+ ", 어제 동시간대 결제건수 : "+ yestOrders.size() +
                "오늘 결제금액: " + todayOrderAmt + "어제 동시간대 결제금액: " + yestOrderAmt);

        return GetTodayOrdersRes.builder()
                .todayOrdersCount(todayOrders.size())
                .todayOrdersAmount(todayOrderAmt)
                .difOrdersCount( yestOrders.size())
                .difOrdersAmount(yestOrderAmt)
                .build();
    }

    //월별 매출
    public GetCategoryOrdersRes monthlySales(){
        LocalDateTime startofYear = LocalDateTime.of(LocalDate.now().getYear(), 1, 1, 0, 0);
        Date startDate = Date.from(startofYear.atZone(ZoneId.systemDefault()).toInstant());

        List<OrdersDocument> list = ordersDocumentRepository.findAllByTimestampAfter(startDate);

        int[] categoryAmt = new int[12];
        for(OrdersDocument order : list) {
            categoryAmt[order.getTimestamp().getMonth()] += order.getPrice();
        }

        System.out.println( "월별 결제금액: [" + categoryAmt[0] + "," + categoryAmt[1] + "," + categoryAmt[2] + ","  +categoryAmt[3] + "," +
                categoryAmt[4] + categoryAmt[5] + "," + categoryAmt[6] + "," + categoryAmt[7] + ","  +categoryAmt[8] + "," +
                categoryAmt[9] + categoryAmt[10] + "," + categoryAmt[11] +
                "]");
        return GetCategoryOrdersRes.builder()
                .orders(categoryAmt)
                .build();
    }

    //카테고리별 판매율
    public GetCategoryOrdersRes catergorySales(){
        LocalDateTime start = LocalDate.now().minusDays(14).atStartOfDay();
        Date startDate = Date.from(start.atZone(ZoneId.systemDefault()).toInstant());

        List<OrdersDocument> list = ordersDocumentRepository.findAllByTimestampAfter(startDate);

        int totalAmt = 0;
        int[] categoryAmt = new int[5];
        for(OrdersDocument order : list) {
            System.out.println(order.toString());
            totalAmt += order.getPrice();
            categoryAmt[order.getCategory()] += order.getPrice();
        }

        System.out.println( "결제건수: " + list.size() + ", 결제금액: " + totalAmt +
                ", 카테고리별 결제금액: [" + categoryAmt[0] + "," + categoryAmt[1] + "," + categoryAmt[2] + ","  +categoryAmt[3] + "," +
                categoryAmt[4] + "]");

        return GetCategoryOrdersRes.builder()
                .orders(categoryAmt)
                .ordersAmount(totalAmt)
                .ordersCount(list.size())
                .build();
    }

    //고객 집계함수 - 카테고리별 주문 금액, 카테고리별 상품 조회
    public GetCategoryOrdersRes custCatergoryOrders(Long idx) {

        List<OrdersDocument> list1 = ordersDocumentRepository.findAllByCustomerIdx(idx);

        int[] categoryAmt = new int[5];
        for(OrdersDocument order : list1) {
            categoryAmt[order.getCategory()] += order.getPrice();
        }


        List<ProductDetailDocument> list2 = productDetailDocumentRepository.findAllByCustomerIdx(idx);

        int[] categoryRead = new int[5];
        for(ProductDetailDocument product : list2) {
            categoryRead[product.getCategory()-1]++;
        }

        System.out.println( " 카테고리별 조회: [" + categoryRead[0] + "," + categoryRead[1] + "," + categoryRead[2] + ","  +categoryRead[3] + "," +
                categoryRead[4] + "]");
        System.out.println( " 카테고리별 결제금액: [" + categoryAmt[0] + "," + categoryAmt[1] + "," + categoryAmt[2] + ","  +categoryAmt[3] + "," +
                categoryAmt[4] + "]");

        return GetCategoryOrdersRes.builder()
                .orders(categoryAmt)
                .productRead(categoryRead)
                .build();
    }
}
