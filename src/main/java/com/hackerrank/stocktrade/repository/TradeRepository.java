package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    @Query("select trade from Trade trade where 1=1 order by trade.id asc")
    List<Trade> findAllByOrderAsc();

    @Query("select trade from Trade trade where trade.userId = :userId order by trade.id asc")
    List<Trade> findByUserId(@PathParam("userId") Long userId);


    @Query("select trade from Trade trade where trade.symbol = :symbol and trade.type = :type " +
            "and trade.timestamp > :start and trade.timestamp < :end order by trade.id asc")
    List<Trade> findFiltered(@PathParam("symbol") String symbol,
                             @PathParam("type") String type,
                             @PathParam("start") Timestamp start,
                             @PathParam("end") Timestamp end );

    @Query("select trade from Trade trade where trade.symbol = :symbol " +
            "and trade.timestamp > :start and trade.timestamp < :end order by trade.id asc")
    List<Trade> findFiltered(@PathParam("symbol") String symbol,
                             @PathParam("start") Timestamp start,
                             @PathParam("end") Timestamp end );
}
