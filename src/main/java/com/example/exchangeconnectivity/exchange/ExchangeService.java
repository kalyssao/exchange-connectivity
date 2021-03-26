package com.example.exchangeconnectivity.exchange;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeService {

    public String confirmOrderWithExchange(ExchangeOrder exchangeOrder) throws HttpStatusCodeException  {
        final String API_URL;
        final String privateKey = "6b055a2f-c488-4386-83b8-e9f30c773d35";

        Integer exchange = exchangeOrder.getExchange();
        switch (exchange) {
            case 1: API_URL = "https://exchange.matraining.com/";
                break;
            case 2: API_URL = "https://exchange2.matraining.com/";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + exchange);
        }

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new ExchangeOrderServiceErrorHandler());

        return restTemplate.postForObject(API_URL + "/" + privateKey + "/order", exchangeOrder, String.class);
    }

}
