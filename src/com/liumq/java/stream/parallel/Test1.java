package com.liumq.java.stream.parallel;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("求和结果: "+sequentialSum(123456L));
        System.out.println("用时： "+(System.currentTimeMillis()-start)+" ms");
        long start2 = System.currentTimeMillis();
        System.out.println("并行求和结果: "+sequentialSumParallel(123456L));
        System.out.println("用时： "+(System.currentTimeMillis()-start2)+" ms");
    }

    /**
     * 求1-n的和
     * @param n
     * @return
     */
    public static long sequentialSum(long n ){

        //通过迭代（数组通项公式）创建流  a(n)=a(n-1)+1
        Long sum = Stream.iterate(1L, i -> i + 1)
                //无限流需要限制前n项
                .limit(n)
                //使用求和函数进行规约
                .reduce(0L, Long::sum);
        return sum;
    }

    public static long sequentialSumParallel(long n ){

        //通过迭代（数组通项公式）创建流  a(n)=a(n-1)+1
        Long sum = Stream.iterate(1L, i -> i + 1)
                //无限流需要限制前n项
                .limit(n)
                //将结果转换为并行流
                .parallel()
                //使用求和函数进行规约
                .reduce(0L, Long::sum);
        return sum;
    }
}
