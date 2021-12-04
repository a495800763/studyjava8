package com.liumq.java.stream.spliteratordemo;

import java.util.*;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
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
//        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
//        Stream<Character> stream = StreamSupport.stream(spliterator, true);
//        System.out.println("Found "+countWords(stream.parallel())+" words");
        testMapMerge();
    }

    private static int countWords(Stream<Character> stream){
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();


    }


    private static void testMapForeach(){
        Map<String,Integer> map = new HashMap<>();
        map.put("liumengqi",3);
        map.put("liuman",2);
        map.put("wangxiaoxuan",6);
        map.put("chenzuyqin",8);

        map.forEach((s, integer) -> System.out.println("key: "+s+",value: "+integer));

    }


    private static void testMapMerge(){
        Map<String,String> family = Map.ofEntries(
                Map.entry("Teo","Star Wars"), Map.entry("Cristina","James Bonds")
        );

        Map<String,String> friends = Map.ofEntries(
                Map.entry("Raphael","Star Wars"), Map.entry("Cristina","Matrix")
        );

        Map<String,String> everyone = new HashMap<>(family);
        friends.forEach((k, v) -> everyone.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie2));
        everyone.entrySet().stream().forEach(System.out::println);
    }
}
