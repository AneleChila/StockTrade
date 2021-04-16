package com.hackerrank.stocktrade.pojo;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeRequest {

    private Long id;
    private String type;
    private User user;
    private String symbol;
    private Integer shares;
    private Float price;
    private Timestamp timestamp;

    public TradeRequest(Trade trade) {
        this.id = trade.getId();
        this.type = trade.getType();
        this.symbol = trade.getSymbol();
        this.shares = trade.getShares();
        this.price = trade.getPrice();
        this.timestamp = trade.getTimestamp();
    }
}
