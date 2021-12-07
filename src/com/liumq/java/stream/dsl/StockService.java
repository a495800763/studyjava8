package com.liumq.java.stream.dsl;

import com.liumq.java.stream.dsl.orderbuilder.MethodChainingOrderBuilder;
import com.liumq.java.stream.dsl.stocktradedemo.Order;
import com.liumq.java.stream.dsl.stocktradedemo.Stock;
import com.liumq.java.stream.dsl.stocktradedemo.Trade;

public class StockService {
    public static void main(String[] args) {
        newFunction();
    }

    /**
     * 不使用dsl而直接进行业务操作的代码逻辑
     */
    public static void oldFunction (){
        Order order  =new Order();
        order.setCustomer("BigBank");

        Trade trade1 = new Trade();
        trade1.setType(Trade.Type.BUY);

        Stock stick1 = new Stock();
        stick1.setSymbol("IBM");
        stick1.setSymbol("NYSE");

        trade1.setStock(stick1);
        trade1.setPrice(125.00);
        trade1.setQuantity(80);
        order.addTrade(trade1);

        Trade trade2 = new Trade();
        trade2.setType(Trade.Type.SELL);

        Stock stock2 = new Stock();
        stock2.setSymbol("GOOGLE");
        stock2.setMarket("NASDAQ");

        trade2.setStock(stock2);
        trade2.setPrice(375.00);
        trade2.setQuantity(50);

        order.addTrade(trade2);
    }

    public static void newFunction(){
        Order end = MethodChainingOrderBuilder.forcustomer("BigBank")
                .buy(80)
                .stock("IBM")
                .on("NYSE")
                .at(125.00)
                .sell(50)
                .stock("GOOGLE")
                .on("NASDAQ")
                .at(375.00).end();

    }
}
