package com.example.exchangeconnectivity.exchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

public class MockTradeEngine {
    public void produce() {
        ExchangeOrder fakeOrder = new ExchangeOrder("MSFT", 23, 2.5, "SELL", 2, 1);
        Jedis jedis = new Jedis("redis-17587.c92.us-east-1-3.ec2.cloud.redislabs.com", 17587);
        jedis.auth("rLAKmB4fpXsRZEv9eJBkbddhTYc1RWtK");
        ObjectMapper mapper = new ObjectMapper();

        try {
            String orderString = mapper.writeValueAsString(fakeOrder);
            jedis.rpush("incoming-orders", orderString);
        } catch (JsonProcessingException e){
            System.out.println("whoops!");
        }
    }

}
