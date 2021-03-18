package com.example.exchangeconnectivity.exchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

public class MockTradeEngine {
    public void produce() {
        ExchangeOrder fakeOrder = new ExchangeOrder("TSLA", 50, 2.5, "BUY", 2);
        Jedis jedis = new Jedis("localhost", 8083, 1800);
        ObjectMapper mapper = new ObjectMapper();

        try {
            String orderString = mapper.writeValueAsString(fakeOrder);
            jedis.rpush("incoming-orders", orderString);
        } catch (JsonProcessingException e){
            System.out.println("whoops!");
        }
    }

}
