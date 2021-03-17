package com.example.exchangeconnectivity.exchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.List;

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

    public void consume() {
        Jedis jedis = new Jedis("localhost", 8083, 1800);
        List<String> processedOrders = null;
        while (true){
            String order = jedis.rpop("outgoing-orders");

            ObjectMapper mapper = new ObjectMapper();
            try {
                ExchangeOrder processedExchangeOrder = mapper.readValue(order, ExchangeOrder.class);
                System.out.println("done with" + processedExchangeOrder);
            } catch (JsonProcessingException e){
                System.out.println("consume whoops");
            }
        }
    }

}
