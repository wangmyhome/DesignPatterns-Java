package com.pattern.staticproxy;

/**
 * 客户端小明
 *
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-04
 */
public class XiaoMingClient {
    public static void main(String[] args) {
        ChanelFactory factory = new ChanelFactory();
        //购买商品找小红代理
        XiaoHongSellProxy proxy = new XiaoHongSellProxy(factory);
        proxy.sellPerfume(1999.99);
    }
}
