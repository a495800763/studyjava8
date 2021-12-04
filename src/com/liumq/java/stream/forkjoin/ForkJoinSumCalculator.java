package com.liumq.java.stream.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    /**
     * 待求和的数组
     */
    private final long[] numbers;
    /**
     * 由子任务处理的子数组的起始和终点位置
     */
    private final int start;
    private final int end;
    /**
     * 将任务分解为子任务的阈值大小
     */
    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        int length = end - start;
        if (length < THRESHOLD) {
            //如果当前子数组长度小于阈值，就直接结算结果
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        //创建一个子任务来为数组的前一半（左侧）求和,使用的是另外一个线程（不是主线程）
        leftTask.fork();

        //创建右侧一半的任务
        ForkJoinSumCalculator rightTadsk = new ForkJoinSumCalculator(numbers, start + length / 2, end);

        //右侧任务直接在主线程执行就可以了
        Long rightResult = rightTadsk.compute();

        //从左侧任务线程中读取其结果，如果尚未完成的话 ，会等待
        Long leftResult = leftTask.join();

        return leftResult + rightResult;

    }

    /**
     * 顺序计算当前子数组的和
     *
     * @return
     */
    private Long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);

        // 在forkjoinpool 线程池中调用task对number 使用forkjoin 框架求和
        return new ForkJoinPool().invoke(task);
    }
}
