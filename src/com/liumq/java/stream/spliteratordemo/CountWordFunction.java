package com.liumq.java.stream.spliteratordemo;

public class CountWordFunction {
    /**
     * 使用迭代的方式计算字符串中的单词数
     * @param s
     * @return
     */
    public static int countWordsIteratively(String s){
        int counter = 0;
        boolean lastSpace = true;
        for(char c:s.toCharArray()){
            if(Character.isWhitespace(c)){
                lastSpace=true;
            }else{
                if(lastSpace){
                    counter++;
                }
                lastSpace=false;
            }
        }

        return counter;
    }

    public static void main(String[] args) {
        System.out.println(countWordsIteratively("my name is liumengqi and my home is in hubei qianjiang "));
    }
}
