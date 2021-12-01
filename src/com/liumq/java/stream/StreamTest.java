package com.liumq.java.stream;

import com.liumq.java.stream.entity.Dish;
import com.liumq.java.stream.entity.Trader;
import com.liumq.java.stream.entity.Transaction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamTest {
    public static void main(String[] args) {
        List<Dish> menu = getList();
        createStreamFromValue();
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

    /**
     * 使用迭代器从外部循环筛选卡路里大于300 的dish
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
     * 使用流 从内部循环筛选卡路里大于300 的dish
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
     * 使用流筛选出前两个荤菜
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
     * 找出2011发生的所有交易，并按照交易额排序
     */
    public static void test1() {
        List<Transaction> list = getTransactions();
        list.stream()
                .filter(q -> q.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
    }

    /**
     * 交易员都在哪些不同的城市工作过
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
     * 查找所有来自剑桥（Cambridge）的交易员，并按字母顺序排序
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
     * 返回所有交易员的姓名字符串，按字母顺序排序
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
     * 有没有交易员是在米兰工作的
     */
    public static void test5() {
        List<Transaction> list = getTransactions();
        boolean anyMatch = list.stream()
                .anyMatch(p -> p.getTrader().getCity().equals("Milan"));
        System.out.println(anyMatch);
    }

    /**
     * 打印生活在剑桥的交易员的所有交易额
     */
    public static void test6() {
        List<Transaction> list = getTransactions();
        list.stream()
                .filter(p -> p.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    /**
     * 所有交易中，最高的交易额是多少
     */
    public static void test7(){
        List<Transaction> list = getTransactions();
        list.stream().map(q->q.getValue())
                .reduce(Integer::max);

    }

    /**
     * 找到交易额最小的交易
     */
    public static void test8(){
        List<Transaction> list = getTransactions();
        list.stream().map(q->q.getValue())
                .min(Integer::compareTo);

    }

    /**
     * 数值流生成勾股数组
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
     * 由值创建流
     */
    public static void createStreamFromValue(){
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }


}
