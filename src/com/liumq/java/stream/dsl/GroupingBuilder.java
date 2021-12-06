package com.liumq.java.stream.dsl;

import java.util.function.Function;
import java.util.stream.Collector;
import java.util.*;
import static java.util.stream.Collectors.groupingBy;

/**
 * 一个流程分组的collectors 构建器
 * @param <T>  the type of input elements to the reduction operation
 * @param <D>  group by 之后的 value 类型
 * @param <K>  group by 之后的 key   类型
 */
public class GroupingBuilder<T,D,K> {
    private final Collector<? super T,?,Map<K,D>> collector;

    private  GroupingBuilder(Collector<? super T,?,Map<K,D>> collector){
        this.collector=collector;
    }

    public Collector<? super T,?,Map<K,D>> get(){
        return collector;
    }

    public <J> GroupingBuilder<T,Map<K,D>,J> after (Function<? super T,? extends  J> classifier){
        return new GroupingBuilder<>(groupingBy(classifier,collector));
    }

    public static <T,D,K> GroupingBuilder<T,List<T>,K> groupOn(Function<? super T,?extends K> classifier){
        return new GroupingBuilder<>(groupingBy(classifier));
    }
}
