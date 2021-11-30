package com.liumq.java.stream;

import com.liumq.java.stream.entity.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class StreamTest {
    public static void main(String[] args) {
        List<Dish> menu = getList();
        List<Integer> highCalotries = getTop2Meat(menu);
        highCalotries.stream().forEach(System.out::println);
    }

    public static List<Dish> getList(){
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
     * @param list
     * @return
     */
    public static List<String> getHighCalotries (List<Dish> list){
        List<String> highCalories = new ArrayList<>();
        Iterator<Dish> iterator = list.iterator();
        while (iterator.hasNext()){
            Dish currentDish = iterator.next();
            if(currentDish.getCalories()>300){
                highCalories.add(currentDish.getName());
            }
        }
        return highCalories;
    }

    /**
     * 使用流 从内部循环筛选卡路里大于300 的dish
     * @param list
     * @return
     */
    public static  List<String> getHighCalotriesNew(List<Dish> list){
        List<String> result = list.stream()
                .filter(q -> q.getCalories() > 300)
                .map(Dish::getName).collect(toList());
        return  result;

        //Arrays.stream()
    }


    /**
     * 使用流筛选出前两个荤菜
     * @param list
     * @return
     */
    public static List<Integer> getTop2Meat(List<Dish> list){
        List<Integer> result = list.stream()
                .filter(q -> q.getType().equals(Dish.Type.MEAT))
                .limit(2)
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        return result;
    }



}
