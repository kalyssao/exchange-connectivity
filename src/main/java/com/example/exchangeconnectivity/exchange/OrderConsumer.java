package com.example.exchangeconnectivity.exchange;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

public class OrderConsumer {
    public void consumeOrder() throws JsonProcessingException {
         Jedis jedis = new Jedis("localhost", 8083, 1800);
         Jedis jedis1 = new Jedis("localhost", 8083, 1800);
         List<String> orders;

         while(true){
             System.out.println("waiting for incoming order");
             orders = jedis.blpop(0, "incoming-orders");
             String order = orders.get(1);

             ObjectMapper mapper = new ObjectMapper();
             ExchangeOrder exchangeOrder = mapper.readValue(order, ExchangeOrder.class);

             ExchangeService exchangeService = new ExchangeService();
             String orderId = exchangeService.confirmOrderWithExchange(exchangeOrder);
             System.out.println("api response "+ orderId);

             // updated order with string from exchange
             ExchangeOrder outgoingOrder = new ExchangeOrder(orderId,
                     exchangeOrder.getProduct(), exchangeOrder.getQuantity(),
                     exchangeOrder.getPrice(), exchangeOrder.getSide(), exchangeOrder.getExchange());
             System.out.println(outgoingOrder);

             // not seeing id in this json object
             // String outgoingOrderJson = mapper.writeValueAsString(outgoingOrder);
             // System.out.println(outgoingOrderJson);

             // persist to database through rest call
             ExchangeOrderService exchangeOrderService = new ExchangeOrderService();
             exchangeOrderService.persistToDb(outgoingOrder);

             // need to figure out where persisting will occur
             //jedis.rpush("outgoing-orders", outgoingOrderJson);

         }
    }
}
