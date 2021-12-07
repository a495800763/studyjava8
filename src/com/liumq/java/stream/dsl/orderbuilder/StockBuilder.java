package com.liumq.java.stream.dsl.orderbuilder;

import com.liumq.java.stream.dsl.stocktradedemo.Stock;
import com.liumq.java.stream.dsl.stocktradedemo.Trade;

public class StockBuilder {
    private final MethodChainingOrderBuilder builder;
    private final Trade trade;
    private final Stock stock= new Stock();

    public StockBuilder(MethodChainingOrderBuilder builder,Trade trade,String symbol){
        this.builder=builder;
        this.trade=trade;
        stock.setSymbol(symbol);
    }

    public TradeBuilderWithStock on(String market)
    {
        stock.setMarket(market);
        trade.setStock(stock);
        return new TradeBuilderWithStock(builder,trade);
    }
}
