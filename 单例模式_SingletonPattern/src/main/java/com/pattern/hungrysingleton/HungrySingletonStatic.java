package com.pattern.hungrysingleton;

/**
 * 饿汉式：1-2静态代码块
 * 线程安全
 *
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class HungrySingletonStatic {

    private static HungrySingletonStatic instance;
    static {
        instance = new HungrySingletonStatic();
    }
    private HungrySingletonStatic() {}

    public static HungrySingletonStatic getInstance() {
        return instance;
    }
}
