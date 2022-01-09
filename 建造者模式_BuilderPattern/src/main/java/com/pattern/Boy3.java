package com.pattern;

import lombok.ToString;

/**
 * 方式3
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-09
 */
//@Builder
@ToString
public class Boy3 {
    private String name;
    private int age;
    private int weight;
    private int height;

    protected Boy3 build(){
        return this;
    }
    public static BoyBuilder builder(){
        return new BoyBuilder();
    }
    public static class BoyBuilder extends Boy3 {
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
        public Boy3 build() {
            return super.build();
        }
    }
}
