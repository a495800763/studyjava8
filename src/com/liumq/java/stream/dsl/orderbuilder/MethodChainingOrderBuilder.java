package com.liumq.java.stream.dsl.orderbuilder;

import com.liumq.java.stream.dsl.stocktradedemo.Order;
import com.liumq.java.stream.dsl.stocktradedemo.Trade;

public class MethodChainingOrderBuilder {

    /**
     * 由构造器封装的订单对象
     */
    public final Order order = new Order();

    private MethodChainingOrderBuilder(String customer){
        order.setCustomer(customer);
    }

    /**
     * 静态工厂方法，用于创建指定客户订单的builder
     * @param customer
     * @return
     */
    public static MethodChainingOrderBuilder forcustomer(String customer){
        return new MethodChainingOrderBuilder(customer);
    }

    public TradeBuilder buy (int quantity){
        return new TradeBuilder(this, Trade.Type.BUY,quantity);
    }

    public TradeBuilder sell(int quantity){
        return new TradeBuilder(this, Trade.Type.SELL,quantity);
    }


    public MethodChainingOrderBuilder addTrade(Trade trade){
        order.addTrade(trade);
        return this;
    }

    public Order end()
    {
        return  order;
    }

}
