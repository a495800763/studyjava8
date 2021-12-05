package com.liumq.java.designmode.observer;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
