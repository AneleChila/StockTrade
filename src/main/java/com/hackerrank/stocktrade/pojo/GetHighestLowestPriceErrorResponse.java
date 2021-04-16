package com.hackerrank.stocktrade.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetHighestLowestPriceErrorResponse {

    private String message;

    public GetHighestLowestPriceErrorResponse() {
        this.message = "There are no trades in the given date range";
    }

}
