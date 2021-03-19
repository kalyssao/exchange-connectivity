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

            System.out.println(exchangeOrder);

            ExchangeService exchangeService = new ExchangeService();
            String orderId = exchangeService.confirmOrderWithExchange(exchangeOrder);

            System.out.println(orderId);

            String sansQuotes = orderId.replaceAll("^\"|\"$", "");
            exchangeOrder.setId(sansQuotes);
            exchangeOrder.setStatus("Pending");

            System.out.println("final exchange order" + exchangeOrder);
//
//            System.out.println("persist this" + exchangeOrder);

            // persist to database through rest call
//            ExchangeOrderService exchangeOrderService = new ExchangeOrderService();
//            exchangeOrderService.persistToDb(exchangeOrder);

         }
    }
}
