package com.liumq.java.stream;

import com.liumq.java.stream.collect.PrimeNumberCollector;
import com.liumq.java.stream.entity.Dish;
import com.liumq.java.stream.entity.Trader;
import com.liumq.java.stream.entity.Transaction;

import java.util.*;
import static java.util.stream.Collectors.*;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class StreamTest {
    public static void main(String[] args) {
        List<Dish> menu = getList();
        Map<Boolean, List<Integer>> result = partitionPrimes(500);
        System.out.println(result);
        //long count = menu.stream().count();
        //System.out.println(count);
        //System.out.println(menu.size());
        //createStreamFromValue();
        //highCalotries.stream().forEach(System.out::println);
    }

    public static List<Dish> getList() {
        return Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicked", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    public static void test2(List<Dish> list){
        IntSummaryStatistics collect = list.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(collect.toString());
    }

    public static void genericCollector(List<Dish> list){
        //list.stream().collect(Collectors.reducing())
    }

    public static void groupingByTest(List<Dish> list){
        Map<String, List<Dish>> dic = list.stream()
                .collect(groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return "?????????";
                    } else if (dish.getCalories() <= 700) {
                        return "????????????";
                    } else {
                        return "?????????";
                    }
                }));

        System.out.println(dic);
    }

    public static void groupingTest(List<Dish> list){
        Map<Dish.Type, List<String>> map = list.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(Dish::getName, toList())));

        System.out.println(map);
    }
    /**
     * ???????????????????????????????????????????????????300 ???dish
     *
     * @param list
     * @return
     */
    public static List<String> getHighCalotries(List<Dish> list) {
        List<String> highCalories = new ArrayList<>();
        Iterator<Dish> iterator = list.iterator();
        while (iterator.hasNext()) {
            Dish currentDish = iterator.next();
            if (currentDish.getCalories() > 300) {
                highCalories.add(currentDish.getName());
            }
        }
        return highCalories;
    }

    /**
     * ????????? ????????????????????????????????????300 ???dish
     *
     * @param list
     * @return
     */
    public static List<String> getHighCalotriesNew(List<Dish> list) {
        List<String> result = list.stream()
                .filter(q -> q.getCalories() > 300)
                .map(Dish::getName).collect(toList());
        return result;

        //Arrays.stream()
    }


    /**
     * ?????????????????????????????????
     *
     * @param list
     * @return
     */
    public static List<Integer> getTop2Meat(List<Dish> list) {
        List<Integer> result = list.stream()
                .filter(q -> q.getType().equals(Dish.Type.MEAT))
                .limit(2)
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        return result;
    }

    public static List<Transaction> getTransactions() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        return transactions;
    }


    /**
     * ??????2011????????????????????????????????????????????????
     */
    public static void test1() {
        List<Transaction> list = getTransactions();
        list.stream()
                .filter(q -> q.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
    }

    /**
     * ?????????????????????????????????????????????
     */
    public static void test2() {
        List<Transaction> list = getTransactions();
        list.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * ???????????????????????????Cambridge??????????????????????????????????????????
     */
    public static void test3() {
        List<Transaction> list = getTransactions();
        list.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getTrader())
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
    }


    /**
     * ???????????????????????????????????????????????????????????????
     */
    public static void test4() {
        List<Transaction> list = getTransactions();
        String reduce = list.stream()
                .map(q -> q.getTrader().getName())
                .distinct()
                .sorted(String::compareTo)
                .reduce("", (p, q) -> p + q);
        System.out.println(reduce);

    }

    /**
     * ???????????????????????????????????????
     */
    public static void test5() {
        List<Transaction> list = getTransactions();
        boolean anyMatch = list.stream()
                .anyMatch(p -> p.getTrader().getCity().equals("Milan"));
        System.out.println(anyMatch);
    }

    /**
     * ???????????????????????????????????????????????????
     */
    public static void test6() {
        List<Transaction> list = getTransactions();
        list.stream()
                .filter(p -> p.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    /**
     * ?????????????????????????????????????????????
     */
    public static void test7(){
        List<Transaction> list = getTransactions();
        list.stream().map(Transaction::getValue)
                .reduce(Integer::max);

    }

    /**
     * ??????????????????????????????
     */
    public static void test8(){
        List<Transaction> list = getTransactions();
        list.stream().map(q->q.getValue())
                .min(Integer::compareTo);

    }

    public static void collectTest(){
        List<Transaction> list = getTransactions();
        //groupingBy??????????????????????????????function ?????????fanction ???????????????Key,??????list????????????dic
        //????????????List<Transaction>?????????list ???Transaction???????????? ?????? getYear() ?????????????????????????????????????????????List<Transaction>
        Map<Integer, List<Transaction>> dic = list.stream().collect(groupingBy(Transaction::getYear));

        //Map<Integer, List<Transaction>> dic = list.stream().collect(toList());
    }

    /**
     * ???????????????????????????
     */
    public static void getGouguNumber(){
        IntStream.rangeClosed(1,100).boxed()
                .flatMap(a->
                        IntStream.rangeClosed(a,100)
                .filter(b->Math.sqrt(a*a+b*b)%1==0)
                .mapToObj(b->new int[] {a,b,(int)Math.sqrt(a*a+b*b)}))
                .limit(20)
                .forEach(t->System.out.println(t[0]+","+t[1]+","+t[2]));
    }


    /**
     * ???????????????
     */
    public static void createStreamFromValue(){
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }


    /**
     * ????????????????????????????????????
     *
     */
    public static Boolean IsPrime(int candidite){

        //???2 ??????????????????????????????candidate??????candidite???????????????????????????candidite???????????????

        boolean b = IntStream.range(2, candidite)
                .noneMatch(i -> candidite % i == 0);

        return b;
    }

    public static Map<Boolean,List<Integer>> partitionPrimes(int n){
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, n).boxed()
                //.collect(partitioningBy(q -> IsPrime(q)));
        .collect(new PrimeNumberCollector());
        return collect;
    }


}
