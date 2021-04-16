package com.hackerrank.stocktrade.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetHighestLowestPriceResponse {

    private String symbol;
    private Float highest;
    private Float lowest;
}
