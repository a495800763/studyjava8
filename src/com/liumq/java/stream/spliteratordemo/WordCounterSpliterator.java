package com.liumq.java.stream.spliteratordemo;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 单词计数使用的分词迭代器
 * 即定义如何在并行流中拆解一个string 只能在单词之间拆开
 */
public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    /**
     * 标志当前字符的光标位置
     */
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }


    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        //处理当前字符并把光标后移一位
        action.accept(string.charAt(currentChar++));
        // 返回是否还有字符需要 advance;
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            //返回null 表示当前要分的string 已经足够小，不需要再分
            return null;
        }

        //将试探拆分的位置设置为当前字符串的中间位置
        for (int splitPos = currentSize / 2 + currentChar;
             splitPos < string.length(); splitPos++) {
            if (Character.isWhitespace(string.charAt(splitPos))) {
                //只有当当前位置为空格时，才考虑要不要拆分，否则走外层循环直到找到空格为止，找不到空格表示是一个完整的单词不能拆分
                WordCounterSpliterator newSpliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));

                //从currentChar 位置，到 solitPos 位置的子串将由NewSpliterator来处理，
                //而this 表示的Spliterator 仅从splitPos开始往后处理即可，即产生了一个新的区分
                currentChar = splitPos;
                return newSpliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        //估计还剩多少元素需要处理
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
