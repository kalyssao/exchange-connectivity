package com.example.exchangeconnectivity.exchange;

import org.springframework.web.client.RestTemplate;

public class ExchangeOrderService {

    public void persistToDb(ExchangeOrder exchangeOrder){
        final String API_URL = "https://tradeenginedb.herokuapp.com/api/v1/exchangeorder";
        // final String API_URL = "http://localhost:8080/api/v1/exchangeorder";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ExchangeOrderServiceErrorHandler());

        restTemplate.postForObject(API_URL, exchangeOrder, ExchangeOrder.class);
    }
}
