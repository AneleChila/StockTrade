package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.pojo.GetHighestLowestPriceErrorResponse;
import com.hackerrank.stocktrade.pojo.GetHighestLowestPriceResponse;
import com.hackerrank.stocktrade.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.Timestamp;

@RestController
@RequestMapping(value = "/stocks")
public class StocksController {


    private CommonService commonService;

    public StocksController(CommonService commonService) {
        this.commonService = commonService;
    }


    @RequestMapping(value = "/{stockSymbol}/price", method = RequestMethod.GET)
    public ResponseEntity<?> findFilteredTrades(@PathVariable("stockSymbol") String stockSymbol,
                                                @RequestParam(value = "start", required = true) String startDate,
                                                @RequestParam(value = "end", required = true) String endDate) {


        GetHighestLowestPriceResponse response = commonService.findFilteredTrades(
                stockSymbol, Timestamp.valueOf(startDate + " 00:00:00.000"), Timestamp.valueOf(endDate + " 00:00:00.000"));

        if(response == null)
            return new ResponseEntity<>(new GetHighestLowestPriceErrorResponse(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
