package com.pattern.dynamicproxy.cglib;

import com.pattern.dynamicproxy.jdk.SellWineFactory;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-05
 */
public class XiaoMingCglib {

    public static void main(String[] args) {
        SellFlowerFactory sellFlowerFactory = SellProxyFactoryCglib.getInstance().getProxy(SellFlowerFactory.class);
        sellFlowerFactory.sellRedFlower();

        SellWineFactory sellWineFactory = SellProxyFactoryCglib.getInstance().getProxy(SellWineFactory.class);
        sellWineFactory.sellRedWine();
    }
}
