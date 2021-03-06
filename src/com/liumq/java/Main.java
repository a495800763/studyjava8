package com.liumq.java;

import com.liumq.java.comparatorclass.AppleComparator;
import com.liumq.java.entity.Apple;
import com.liumq.java.enumpackage.Color;
import com.liumq.java.interfacepackage.AppleFormatter;
import com.liumq.java.interfacepackage.ApplePredicate;
import com.liumq.java.predicateclass.AppleGreenColorPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;

import static java.util.Comparator.comparing;

public class Main {

    public static void main(String[] args) {
        //test1();
//        List<Integer> weight = Arrays.asList(7,3,4,10);
//        List<Apple> list = map(weight,Apple::new);
//
//        for(Apple apple : list){
//            System.out.println(apple.toString());
//        }
        function(123, o -> {

            System.out.println(o);
            return o;
        });
    }

    public static void function (long times,  Function f){
        f.apply(1);
        if(times>1){
            function(times-1,f);
        }
    }

    private static void test1() {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(200, Color.GREEN));
        apples.add(new Apple(145, Color.RED));
        apples.add(new Apple(165, Color.RED));
        apples.add(new Apple(150, Color.GREEN));
        apples.add(new Apple(123, Color.GREEN));

        System.out.println(filterApples(apples, new AppleGreenColorPredicate()));

        //不需要装箱的  IntPredicate
        IntPredicate p = (int a) -> a < 100;




        apples.sort((a1,a2)-> a1.getWeight()>a2.getWeight()?-1:0);
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 根据weights 映射生成对应每一个weight 的apple
     * @param weights
     * @param f
     * @return
     */
    public static List<Apple> map (List<Integer> weights, Function<Integer,Apple> f){
        List<Apple> result = new ArrayList<>();
        for(Integer a :weights){
            result.add(f.apply(a));
        }
        return result;
    }


    private void SortList(List<Apple> apples){
        //apples.sort(new AppleComparator());
//        apples.sort(new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//               return  o1.getWeight()-o2.getWeight();
//            }
//        });

//        apples.sort((o1,o2)->o1.getWeight()-o2.getWeight());

        apples.sort(comparing((Apple a)->a.getWeight()));

        apples.sort(comparing(Apple::getWeight));
    }
}
