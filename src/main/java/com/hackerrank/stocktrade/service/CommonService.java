package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.pojo.GetHighestLowestPriceResponse;
import com.hackerrank.stocktrade.pojo.TradeRequest;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.TradeType;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommonService {

    private TradeRepository tradeRepository;
    private UserRepository userRepository;

    @Autowired
    public CommonService(TradeRepository tradeRepository, UserRepository userRepository) {
        this.tradeRepository = tradeRepository;
        this.userRepository = userRepository;
    }


    public void deleteAllTrades() {
        tradeRepository.deleteAll();
    }

    public boolean createTrade(TradeRequest request) {

        Optional<Trade> optionalTrade = tradeRepository.findById(request.getId());

        if(optionalTrade.isPresent()) {
            return false;
        }

        Trade trade = new Trade(request);

        userRepository.save(request.getUser());

        tradeRepository.save(trade);

        return true;

    }

    public TradeRequest findTrade(Long id) {

        Optional<Trade> optionalTrade = tradeRepository.findById(id);

        if(!optionalTrade.isPresent()) {
            return null;
        }

        Trade trade = optionalTrade.get();


        User user = userRepository.findById(trade.getUserId()).get();

        TradeRequest tradeRequest = new TradeRequest(trade);
        tradeRequest.setUser(user);

        return tradeRequest;

    }

    public List<TradeRequest> findAllTrades() {

        List<TradeRequest> response = new ArrayList<>();
        List<Trade> trades = tradeRepository.findAllByOrderAsc();


        for(Trade trade : trades) {
            User user = userRepository.findById(trade.getUserId()).get();

            TradeRequest tradeRequest = new TradeRequest(trade);
            tradeRequest.setUser(user);

            response.add(tradeRequest);
        }

        return response;
    }

    public List<TradeRequest> findByUserId(Long id) {

        List<TradeRequest> response = new ArrayList<>();
        List<Trade> trades = tradeRepository.findByUserId(id);

        trades.forEach( trade -> {

            User user = userRepository.findById(trade.getUserId()).get();

            TradeRequest tradeRequest = new TradeRequest(trade);
            tradeRequest.setUser(user);

            response.add(tradeRequest);

        });

        return response;
    }

    public List<TradeRequest> findFilteredTrades(String symbol, String tradeType, Timestamp start, Timestamp end) {

        List<TradeRequest> response = new ArrayList<>();

        if(!tradeType.equals(TradeType.SELL) && !tradeType.equals(TradeType.BUY)) {
            return response;
        }

        List<Trade> trades = tradeRepository.findFiltered(symbol, tradeType, start, end);


        trades.forEach( trade -> {

            User user = userRepository.findById(trade.getUserId()).get();

            TradeRequest tradeRequest = new TradeRequest(trade);
            tradeRequest.setUser(user);

            response.add(tradeRequest);

        });

        return response;

    }

    public GetHighestLowestPriceResponse findFilteredTrades(String symbol, Timestamp start, Timestamp end) {

        List<Trade> trades = tradeRepository.findFiltered(symbol, start, end);

        if(trades == null || trades.isEmpty()) {
            return null;
        }

        GetHighestLowestPriceResponse highestLowestPriceResponse = new GetHighestLowestPriceResponse();
        highestLowestPriceResponse.setHighest(trades.get(trades.size()).getPrice());
        highestLowestPriceResponse.setLowest(trades.get(0).getPrice());
        highestLowestPriceResponse.setSymbol(symbol);



        return highestLowestPriceResponse;

    }

}
