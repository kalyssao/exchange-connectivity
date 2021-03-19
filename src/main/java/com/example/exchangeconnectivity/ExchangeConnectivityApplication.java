package com.example.exchangeconnectivity;

import com.example.exchangeconnectivity.exchange.MockTradeEngine;
import com.example.exchangeconnectivity.exchange.OrderConsumer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ExchangeConnectivityApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeConnectivityApplication.class, args);
	}

	@Override
	public void run(String ...arg0)throws Exception{
//		MockTradeEngine mockTradeEngine = new MockTradeEngine();
		OrderConsumer orderConsumer = new OrderConsumer();

//		mockTradeEngine.produce();
		orderConsumer.consumeOrder();
	}

}
