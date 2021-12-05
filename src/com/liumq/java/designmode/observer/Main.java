package com.liumq.java.designmode.observer;

public class Main {

    public static void main(String[] args) {
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.registerObserver(tweet -> {
            if((tweet != null)&&  tweet.contains("java") ){
                System.out.println("This is a new tweet about CS... : "+ tweet);
            }
        });
        f.notifyObservers("The queen said her favourite book is Modern java in action");
    }
}
