package com.liumq.java.stream.collect;


import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

public class PrimeNumberCollector implements Collector<Integer,Map<Boolean, List<Integer>>,Map<Boolean, List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>(){
            {put(true,new ArrayList<Integer>());
            put(false,new ArrayList<Integer>());}
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (acc, candidate) -> acc.get(isPrime(acc.get(true),candidate)).add(candidate);
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (map1, map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return  map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public static boolean isPrime (List<Integer> primes,int  candicate){
        int candicateRoot = (int) Math.sqrt((double)candicate);
        return  takeWhile(primes, integer -> integer<=candicateRoot).stream().noneMatch(p->candicate%p==0);
    }

    private static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
        int i =0;
        for(A item : list){
            if(!p.test(item)){
                return list.subList(0,i);
            }
            i++;
        }

        return list;
    }
}
