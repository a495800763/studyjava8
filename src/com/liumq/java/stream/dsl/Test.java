package com.liumq.java.stream.dsl;

import com.liumq.java.entity.Car;
import com.liumq.java.enumpackage.Brand;
import com.liumq.java.enumpackage.Color;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;

/**
 * 测试第十章 ：dsl
 */
public class Test {
    public static void main(String[] args) {
        List<Car> cars = List.of(
                new Car(Brand.Audi, Color.BLACK, "audiA4"),
                new Car(Brand.Toyota, Color.WHITE, "carola"),
                new Car(Brand.Toyota, Color.RED, "RAV4"),
                new Car(Brand.Honda, Color.RED, "civic10"),
                new Car(Brand.VolksWagen, Color.BLACK, "sagita"),
                new Car(Brand.VolksWagen, Color.WHITE, "lavida"),
                new Car(Brand.Mazda, Color.GREEN, "atenza")
        );


        smoothCombination(cars);
    }

    /**
     * 多层分组嵌套式的collectors
     * @param cars
     */
    private static void multilevelGrouping(List<Car> cars) {
        //外层Brand里层Color 得到的结果也是 外层Brand里层Color
        Map<Brand, Map<Color, List<Car>>> carsByBrandAndColor = cars.stream().collect(groupingBy(Car::getBrand, groupingBy(Car::getColor)));

        System.out.println(carsByBrandAndColor);
    }

    /**
     * 流畅分组组合式的collectors
     * 自定义DSL 描述 先groupOn Brand再after groupOn Color
     * 但是结果去正好相反
     * 先Brand 再Color ，得到的结果确实先Color再Brand
     */
    private static void smoothCombination(List<Car> cars)
    {
        //先Brand 再Color ，得到的结果确实先Color再Brand
        Collector<? super Car, ?, Map<Color, Map<Brand, List<Car>>>> collector = GroupingBuilder.groupOn(Car::getBrand).after(Car::getColor).get();
        Map<Color, Map<Brand, List<Car>>> collect = cars.stream().collect(collector);
        System.out.println(collect);
    }
}
