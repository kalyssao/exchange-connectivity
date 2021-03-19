package com.example.exchangeconnectivity.exchange;

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

public class OrderConsumer {
    public void consumeOrder() throws JsonProcessingException {
        Jedis jedis = new Jedis("redis-17587.c92.us-east-1-3.ec2.cloud.redislabs.com", 17587);
        jedis.auth("rLAKmB4fpXsRZEv9eJBkbddhTYc1RWtK");
        List<String> orders;

        while(true){
            System.out.println("waiting for order");
            orders = jedis.blpop(0, "incoming-orders");
            String order = orders.get(1);

            System.out.println("order from engine " + order);

            ObjectMapper mapper = new ObjectMapper();
            ExchangeOrder exchangeOrder = mapper.readValue(order, ExchangeOrder.class);

            ExchangeService exchangeService = new ExchangeService();
            String orderId = exchangeService.confirmOrderWithExchange(exchangeOrder);

            String sansQuotes = orderId.replaceAll("^\"|\"$", "");
            exchangeOrder.setExchangeOrderId(sansQuotes);
//            exchangeOrder.setExchangeOrderId("testing-123-456");
            exchangeOrder.setStatus("Pending");
            exchangeOrder.setCreatedAt();

            System.out.println(exchangeOrder);

            // persist to database through rest call
            ExchangeOrderService exchangeOrderService = new ExchangeOrderService();
            exchangeOrderService.persistToDb(exchangeOrder);

         }
    }
}
