package com.pattern.decoratordemo;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-11
 */
public class Sugar extends Decorator{
    public Sugar(Drink drink) {
        super(drink);
        //加糖，5元
        setPrice(5.0f);
        setDes("加糖");
    }
}
