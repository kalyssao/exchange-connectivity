package com.example.exchangeconnectivity.exchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

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

    ExchangeOrder(){}

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

    public void setId(String newId){
        this.exchangeOrderId = newId;
    }

    public void setClientOrderId(long clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public void setExchange(Integer exchange) { this.exchange = exchange; }

    @Override
    public String toString() {
        return "ExchangeOrder{" +
                "id='" + exchangeOrderId + '\'' +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                ", exchange=" + exchange +
                ", status='" + status + '\'' +
                ", clientOrderId=" + clientOrderId +
                '}';
    }
}
