package com.example.exchangeconnectivity.exchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeOrder {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String exchangeOrderId;
    private String product;
    private Integer quantity;
    private Double price;
    private String side;
    private Integer exchange;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long clientOrderId;

    public ExchangeOrder(){

    }

    // Sending to exchange
    public ExchangeOrder(String product, Integer quantity, Double price, String side, Integer exchange) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.exchange = exchange;
    }

    // Receiving from trade engine
    public ExchangeOrder(String product, Integer quantity, Double price, String side, Integer exchange, long clientOrderId ) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
        this.exchange = exchange;
        this.clientOrderId = clientOrderId;
    }

    public ExchangeOrder(String exchangeOrderId, String product, Integer quantity, Double price, String side, Integer exchange, long clientOrderId, String status) {
        this.exchangeOrderId = exchangeOrderId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.exchange = exchange;
        this.clientOrderId = clientOrderId;
        this.status = status;
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

    public long getClientOrderId() {
        return clientOrderId;
    }

    public String getStatus() {
        return status;
    }

    public void setQuantity(Integer newQuantity){
        this.quantity = newQuantity;
    }

    public void setPrice(Double newPrice){
        this.price = newPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setClientOrderId(long clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public void setExchange(Integer exchange) { this.exchange = exchange; }

    public void setExchangeOrderId(String exchangeOrderId) {
        this.exchangeOrderId = exchangeOrderId;
    }

    @Override
    public String toString() {
        return "ExchangeOrder{" +
                "exchangeOrderId='" + exchangeOrderId + '\'' +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                ", exchange=" + exchange +
                ", clientOrderId=" + clientOrderId +
                ", status='" + status + '\'' +
                '}';
    }
}
