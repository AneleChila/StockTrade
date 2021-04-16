package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.pojo.GetHighestLowestPriceErrorResponse;
import com.hackerrank.stocktrade.pojo.GetHighestLowestPriceResponse;
import com.hackerrank.stocktrade.pojo.TradeRequest;
import com.hackerrank.stocktrade.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {

    private CommonService commonService;

    public TradesController(CommonService commonService) {
        this.commonService = commonService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createTrade(@RequestBody TradeRequest request) {


        boolean response = commonService.createTrade(request);

        if(response)
            return new ResponseEntity<>(true, HttpStatus.CREATED);


        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findTrade(@PathVariable("id") Long id) {


        TradeRequest response = commonService.findTrade(id);

        if(response == null)
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> findAllTrade() {


        List<TradeRequest> response = commonService.findAllTrades();

        if(response.isEmpty())
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userID}", method = RequestMethod.GET)
    public ResponseEntity<?> findAllTradeByUserId(@PathVariable("userId") Long userID) {


        List<TradeRequest> response = commonService.findByUserId(userID);

        if(response.isEmpty())
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/stocks/{stockSymbol}", method = RequestMethod.GET)
    public ResponseEntity<?> findFilteredTrades(@PathVariable("stockSymbol") String stockSymbol,
                                                @RequestParam(value = "tradeType", required = true) String tradeType,
                                                @RequestParam(value = "start", required = true) String startDate,
                                                @RequestParam(value = "end", required = true) String endDate) {


        List<TradeRequest> response = commonService.findFilteredTrades(
                stockSymbol, tradeType, Timestamp.valueOf(startDate + " 00:00:00.000"), Timestamp.valueOf(endDate + " 00:00:00.000"));

        if(response.isEmpty())
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }







    
}
