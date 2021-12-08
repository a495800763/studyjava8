package com.liumq.java.optional.olddemo;

public class Main {
    public static void main(String[] args) {

    }

    /**
     * 深层质疑解决nullpointerexception的方式
     */
    public String deepQuestioningGetInsuranceName(Person person){
        if(person!=null){
            Car car = person.getCar();
            if(car!=null){
                Insurance insurance = car.getInsurance();
                if(insurance!=null){
                    return insurance.getName();
                }
            }
        }
        return "unknown";
    }

    /**
     * 多次退出的解决方式
     * @param person
     * @return
     */
    public String multipleExits(Person person){
        if(person==null){
            return "unknown";
        }
        Car car = person.getCar();

        if(car==null){
            return "unknown";
        }

        Insurance insurance = car.getInsurance();
        if(insurance==null){
            return "unknown";
        }

        return insurance.getName();
    }
}
