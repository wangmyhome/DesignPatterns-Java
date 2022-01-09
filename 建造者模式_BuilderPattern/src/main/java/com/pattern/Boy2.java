package com.pattern;

import lombok.ToString;

/**
 *
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-09
 */
@ToString
public class Boy2 {
    private String name;
    private int age;
    private int weight;
    private int height;

    private Boy2(BoyBuilder builder){
        name = builder.name;
        age = builder.age;
        weight = builder.weight;
        height = builder.height;
    }

    public static BoyBuilder builder(){
        return new BoyBuilder();
    }
    public static class BoyBuilder{
        private String name;
        private int age;
        private int weight;
        private int height;

        public BoyBuilder name(String name){
            this.name = name;
            return this;
        }
        public BoyBuilder age(int age){
            this.age = age;
            return this;
        }
        public BoyBuilder weight(int weight){
            this.weight = weight;
            return this;
        }
        public BoyBuilder height(int height){
            this.height = height;
            return this;
        }
        public Boy2 build() {
            return new Boy2(this);
        }
    }
}
