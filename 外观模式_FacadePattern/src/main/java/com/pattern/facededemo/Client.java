package com.pattern.facededemo;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-11
 */
public class Client {
    public static void main(String[] args) {
        //创建外观对象
        SmartFacade facade = new SmartFacade();
        //客户端直接与外观对象进行交互
        facade.say("打开家电");
        facade.say("关闭家电");
    }
}
