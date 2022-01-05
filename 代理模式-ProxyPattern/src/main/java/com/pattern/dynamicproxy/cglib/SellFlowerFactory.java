package com.pattern.dynamicproxy.cglib;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-05
 */
public class SellFlowerFactory implements SellFlower{
    @Override
    public void sellRedFlower() {
        System.out.println("售卖红花 1000 元");
    }
}


