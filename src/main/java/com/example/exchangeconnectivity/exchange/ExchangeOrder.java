package com.example.exchangeconnectivity.exchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeOrder {
    private String id;
    private String product;
    private Integer quantity;
    private Double price;
    private String side;
    private Integer exchange;

    public ExchangeOrder() {}

    // Receiving from trade engine
    public ExchangeOrder(String product, Integer quantity, Double price, String side, Integer exchange) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.exchange = exchange;
    }

    // Sending to exchange
    public ExchangeOrder(String product, Integer quantity, Double price, String side) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
    }

    // Sending back to trading engine for persisting
    public ExchangeOrder(String id, String product, Integer quantity, Double price, String side, Integer exchange) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.exchange = exchange;
        this.price = price;
        this.side = side;
    }

    public String getProduct() {
        return product;
    }

    public Double getPrice(){
        return price;
    }

    public Integer getQuantity(){
        return quantity;
    }

    public String getSide() {
        return side;
    }

    public Integer getExchange() {
        return exchange;
    }

    public void setQuantity(Integer newQuantity){
        this.quantity = newQuantity;
    }

    public void setPrice(Double newPrice){
        this.price = newPrice;
    }

    @Override
    public String toString() {
        return "ExchangeOrder{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                ", exchange=" + exchange +
                '}';
    }

}
