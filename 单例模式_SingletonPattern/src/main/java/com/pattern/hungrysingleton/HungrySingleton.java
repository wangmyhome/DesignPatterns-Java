package com.pattern.hungrysingleton;

/**
 * 饿汉式
 * 在类加载的时候实例化并加载到内存，只会被实例化一次。
 * 线程安全
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-06
 */
public class HungrySingleton {
    private final static HungrySingleton hungrySingleton = new HungrySingleton();
    //1. 构造器私有化, 外部不能new
    private HungrySingleton(){}
    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }
}
