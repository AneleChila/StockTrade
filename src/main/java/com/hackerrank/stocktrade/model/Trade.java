package com.hackerrank.stocktrade.model;

import com.hackerrank.stocktrade.pojo.TradeRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Trade {

    @Id
    private Long id;
    @Column
    private String type;
    @Column
    private Long userId;
    @Column
    private String symbol;
    @Column
    private Integer shares;
    @Column
    private Float price;
    @Column
    private Timestamp timestamp;
    
    public Trade() {
    }

    public Trade(TradeRequest request) {
        this.id = request.getId();
        this.type = request.getType();
        this.userId = request.getUser().getId();
        this.symbol = request.getSymbol();
        this.shares = request.getShares();
        this.price = request.getPrice();
        this.timestamp = request.getTimestamp();
    }

    public Trade(Long id, String type, User user, String symbol, Integer quantity, Float price, Timestamp timestamp) {
        this.id = id;
        this.type = type;
        this.userId = user.getId();
        this.symbol = symbol;
        this.shares = quantity;
        this.price = price;
        this.timestamp = timestamp;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Long getUserId() {
        return this.userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public Integer getShares() {
        return this.shares;
    }
    
    public void setShares(Integer shares) {
        this.shares = shares;
    }
    
    public Float getPrice() {
        return this.price;
    }
    
    public void setPrice(Float price) {
        this.price = price;
    }
    
    public Timestamp getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
