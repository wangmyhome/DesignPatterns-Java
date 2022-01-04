package com.pattern.staticproxy;

/**
 * 售卖香水的厂商
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-04
 */
public class ChanelFactory implements SellPerfume{
    @Override
    public void sellPerfume(double price) {
        System.out.println("成功购买香奈儿品牌的香水，价格是：" + price + "元");
    }
}
