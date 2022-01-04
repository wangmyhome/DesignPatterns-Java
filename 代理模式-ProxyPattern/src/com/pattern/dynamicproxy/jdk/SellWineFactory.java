package com.pattern.dynamicproxy.jdk;

/**
 *
 * 被代理的类，售卖酒的厂商
 *
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-04
 */
public class SellWineFactory implements SellWine{

    @Override
    public void sellRedWine() {
        System.out.println("成功购买红酒，价格是：5000 元");
    }
}
