package com.pattern.decoratordemo;

/**
 * 具体抽象构件
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-11
 */
public class Milk extends Decorator{
    public Milk(Drink drink) {
        super(drink);
        //加牛奶，10元
        setPrice(10.0f);
        setDes("加牛奶");
    }
}
