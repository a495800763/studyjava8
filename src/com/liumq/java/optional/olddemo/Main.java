package com.liumq.java.optional.olddemo;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Insurance insurance = new Insurance();
        insurance.setName("hahaha");
        Car car = new Car();
        car.setInsurance(Optional.of(insurance));
        Person person = new Person();
        person.setCar(Optional.of(car));

        System.out.println(optionalObjectTest(person));
    }

//    /**
//     * 深层质疑解决nullpointerexception的方式
//     */
//    public String deepQuestioningGetInsuranceName(Person person){
//        if(person!=null){
//            Car car = person.getCar();
//            if(car!=null){
//                Insurance insurance = car.getInsurance();
//                if(insurance!=null){
//                    return insurance.getName();
//                }
//            }
//        }
//        return "unknown";
//    }
//
//    /**
//     * 多次退出的解决方式
//     * @param person
//     * @return
//     */
//    public String multipleExits(Person person){
//        if(person==null){
//            return "unknown";
//        }
//        Car car = person.getCar();
//
//        if(car==null){
//            return "unknown";
//        }
//
//        Insurance insurance = car.getInsurance();
//        if(insurance==null){
//            return "unknown";
//        }
//
//        return insurance.getName();
//    }

    public static String optionalObjectTest(Person person) {
        Optional<Person> optPerson = Optional.of(person);
        String insuranceName = optPerson.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");

        return insuranceName;

    }

    public Set<String> getCarInsuranceNames(List<Person> persons) {
        Set<String> collect = persons.stream().map(Person::getCar)
                //此处map操作的是Optional<Car>,没有Car的方法，因此还需要将Optional<Car>转换为Car，即使用flatmap 得到car并传递方法引用得到 insurance
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                //此处map操作的是Optional<Insurance>,没有Insurance的方法，因此还需要将Optional<Insurance>转换为Insurance，即使用flatmap 得到转换为Insurance并传递方法引用得到 name
                .map(optIns -> optIns.map(Insurance::getName))
                //.flatMap(Optional::stream)
                //Optional  的 stream 方法与下面两部方法的结果一致
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        return collect;
    }

    public Set<String> getCarInsuranceNamesNew(List<Person> persons) {
//         persons.stream().map(Person::getCar)
//                .map(Car::getInsurance)
        return null;
    }

    public Insurance findCheapestInsurance(Car car, Person person) {
        return null;
        //todo
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Car> car, Optional<Person> person) {
        if (car.isPresent() && person.isPresent()) {
            return Optional.of(findCheapestInsurance(car.get(), person.get()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Insurance> nullSafeFindCheapestInsuranceNew(Optional<Car> car, Optional<Person> person) {
        Optional<Insurance> insurance = person.flatMap(optPer -> car.map(optCar -> findCheapestInsurance(optCar, optPer)));
        return insurance;
    }

    public String getCarInsurance(Optional<Person> person, int minAge) {
        String name = person.filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");
        return name;
    }

    /**
     * 处理null指针的方式获取properties 中的可作为正整数的值
     * @param props
     * @param name
     * @return
     */
    public int readDuration(Properties props,String name){
        String value = props.getProperty(name);
        if(value!=null){
            try{
                int i = Integer.parseInt(value);
                if(i>0){
                    return i;
                }
            }
            catch (NumberFormatException e){

            }
        }
        return 0;
    }

    public int readDurationByOptional (Properties props,String name){
        Integer integer = Optional.ofNullable(props.get(name))
                .flatMap(q -> Optional.of(Integer.parseInt((String) q)))
                .filter(i -> i > 0)
                .orElse(0);
        return integer;
    }
}
