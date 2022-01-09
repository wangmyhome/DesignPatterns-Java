package com.pattern;

import lombok.Builder;
import lombok.ToString;

/**
 *
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-09
 */
//@Builder
@ToString
public class Boy {
    private String name;
    private int age;
    private int weight;
    private int height;

    protected Boy build(){
        return this;
    }
    public static BoyBuilder builder(){
        return new BoyBuilder();
    }
    public static class BoyBuilder extends Boy{
        public BoyBuilder name(String name){
            super.name = name;
            return this;
        }
        public BoyBuilder age(int age){
            super.age = age;
            return this;
        }
        public BoyBuilder weight(int weight){
            super.weight = weight;
            return this;
        }
        public BoyBuilder height(int height){
            super.height = height;
            return this;
        }
        @Override
        public Boy build() {
            return super.build();
        }
    }
}
