package com.example.exchangeconnectivity.exchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeOrder {
    private String exchangeOrderId;
    private String product;
    private Integer quantity;
    private Double price;
    private String side;
    private Integer exchange;
    private long clientOrderId;
    private String status;

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

    public String getExchangeOrderId() {
        return exchangeOrderId;
    }

    public ExchangeOrder(String id, String product, Integer quantity, Double price, String side, Integer exchange) {
        this.exchangeOrderId = id;
        this.product = product;
        this.quantity = quantity;
        this.exchange = exchange;
        this.price = price;
        this.side = side;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Integer getExchange() {
        return exchange;
    }

    public void setExchange(Integer exchange) {
        this.exchange = exchange;
    }

    public long getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(long clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
