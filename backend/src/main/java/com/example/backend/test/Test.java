package com.example.backend.test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class Test {
    final String gmarket= "https://category.gmarket.co.kr/listview/L100000003.aspx/java/";

    Document document = Jsoup.connect(gmarket).get();

    public Test() throws IOException {
    }
}
