package com.pattern.decoratordemo;

/**
 * 抽象构件具体
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-10
 */
public class Coffee1 extends Coffee{
    public Coffee1(){
        setDes("第一种具体的咖啡");
        setPrice(100.0f);
    }
}
