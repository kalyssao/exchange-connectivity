package com.example.exchangeconnectivity.exchange;

import org.springframework.web.client.RestTemplate;

public class ExchangeOrderService {

    public void persistToDb(ExchangeOrder exchangeOrder){
        final String API_URL = "https://tradeenginedb.herokuapp.com/api/v1/exchangeorder/new";
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.postForObject(API_URL, exchangeOrder, String.class);
    }
}
