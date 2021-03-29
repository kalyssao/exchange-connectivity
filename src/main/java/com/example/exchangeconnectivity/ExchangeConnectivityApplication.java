package com.example.exchangeconnectivity;

import com.example.exchangeconnectivity.exchange.OrderConsumer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableScheduling
public class ExchangeConnectivityApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeConnectivityApplication.class, args);
	}

	@Override
	public void run(String ...arg0)throws Exception{
		OrderConsumer orderConsumer = new OrderConsumer();

		try {
			orderConsumer.consumeOrder();
		}catch (Exception e){
			System.out.println(e);
		}
	}

}