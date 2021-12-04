package com.liumq.java.stream.spliteratordemo;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TestMain {

    private static final String SENTENCE =
            " my name is liumengqi and my book is very big "
            +" mi  ritrovai  in  una selva  oscura "
            +" che  la  dritta  via era smarrita ";

    public static void main(String[] args) {
        //Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        //System.out.println("Found "+countWords(stream.parallel())+" words");
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("Found "+countWords(stream.parallel())+" words");
    }

    private static int countWords(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }
}
