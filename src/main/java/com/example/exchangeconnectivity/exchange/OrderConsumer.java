package com.example.exchangeconnectivity.exchange;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import redis.clients.jedis.Jedis;

@Component
public class OrderConsumer {
    Jedis jedis = new Jedis("redis-17587.c92.us-east-1-3.ec2.cloud.redislabs.com", 17587);
    Jedis jedis1 = new Jedis("redis-17587.c92.us-east-1-3.ec2.cloud.redislabs.com", 17587);
    String orderId;

    private List<String> orders;
    private String order;

    public void jedisInit(){
        jedis.auth("rLAKmB4fpXsRZEv9eJBkbddhTYc1RWtK");
        jedis1.auth("rLAKmB4fpXsRZEv9eJBkbddhTYc1RWtK");
    }


    @Scheduled(fixedRate = 500)
    public void consumeOrder() throws Exception {
        ExchangeOrderService exchangeOrderService = new ExchangeOrderService();
        jedisInit();

        order = jedis.rpop("incoming-orders");
        if(order == null){
            return;
        }
        System.out.println(order);
        //String order = orders.get(1);

        jedis1.publish("report-message",
                order.toString() + " has been received by the exchange");

        ObjectMapper mapper = new ObjectMapper();
        ExchangeOrder exchangeOrder = mapper.readValue(order, ExchangeOrder.class);

        ExchangeService exchangeService = new ExchangeService();

        try {
            orderId = exchangeService.confirmOrderWithExchange(exchangeOrder);
        } catch (Exception e) {

            // tells the reporting service that exchange said no
            System.out.println("exchange rejected");
            exchangeOrder.setStatus("rejected");

            jedis1.publish("report-message",
                    exchangeOrder.toString() + " has been rejected by the exchange");

            exchangeOrderService.persistToDb(exchangeOrder);
            return;
        }


        orderId = (orderId.subSequence(1, orderId.length() - 1)).toString();
        // updated order with string from exchange
        ExchangeOrder outgoingOrder = new ExchangeOrder(orderId,
                exchangeOrder.getProduct(), exchangeOrder.getQuantity(),
                exchangeOrder.getPrice(), exchangeOrder.getSide(), exchangeOrder.getExchange(),exchangeOrder.getClientOrderId(),"PENDING");

        // persist to database through rest call
        try {
            exchangeOrderService.persistToDb(outgoingOrder);
        } catch (HttpClientErrorException e) {
            // tell the reporting service the db said no?
            System.out.println("db");
                jedis1.publish("report-message",
                        exchangeOrder.toString() + " has been sent to the exchange & but could not be persisted");

            return;
        }


        // publish to messaging service
        //String message = mapper.writeValueAsString(outgoingOrder);
        jedis1.publish("report-message",
                outgoingOrder.toString() + " has been sent to the exchange & persisted into the db");
    }
}
