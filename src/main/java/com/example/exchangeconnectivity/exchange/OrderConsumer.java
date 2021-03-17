package com.example.exchangeconnectivity.exchange;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

public class OrderConsumer {
    public void consumeOrder() throws JsonProcessingException {
         Jedis jedis = new Jedis("localhost", 8083, 1800);
         Jedis jedis1 = new Jedis("localhost", 8083, 1800);
         List<String> orders = null;

         while(true){
             System.out.println("waiting for message");
             orders = jedis.blpop(0, "incoming-orders");
             String order = orders.get(1);

             ObjectMapper mapper = new ObjectMapper();
             ExchangeOrder exchangeOrder = mapper.readValue(order, ExchangeOrder.class);

             ExchangeService exchangeService = new ExchangeService();
             String orderId = exchangeService.confirmOrderWithExchange(exchangeOrder);
             System.out.println(orderId);

             ExchangeOrder outgoingOrder = new ExchangeOrder(orderId,
                     exchangeOrder.getProduct(), exchangeOrder.getQuantity(),
                     exchangeOrder.getPrice(), exchangeOrder.getSide(), exchangeOrder.getExchange());
             System.out.println(outgoingOrder);

             String outgoingOrderJson = mapper.writeValueAsString(outgoingOrder);
             System.out.println(outgoingOrderJson);
             jedis1.rpush("outgoing-orders", outgoingOrderJson);
             System.out.println("sending to outgoing queue");

         }
    }
}
