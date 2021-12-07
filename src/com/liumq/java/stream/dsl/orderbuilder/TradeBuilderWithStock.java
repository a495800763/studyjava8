package com.liumq.java.stream.dsl.orderbuilder;

import com.liumq.java.stream.dsl.stocktradedemo.Trade;

public class TradeBuilderWithStock {
    private final  MethodChainingOrderBuilder builder;
    private final Trade trade;

    public TradeBuilderWithStock(MethodChainingOrderBuilder builder,Trade trade){
        this.builder=builder;
        this.trade=trade;
    }

    public MethodChainingOrderBuilder at (double price){
        trade.setPrice(price);
        return builder.addTrade(trade);
    }
}
