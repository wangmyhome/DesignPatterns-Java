package com.pattern.lazysingleton;

/**
 * 懒汉式
 * 线程安全
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-06
 */
public class LazySingletonSafe {

    private static LazySingletonSafe lazySingleton;
    //构造器私有化, 外部不能new
    private LazySingletonSafe(){}
    public static synchronized LazySingletonSafe getInstance(){
        //提供一个静态的公有方法，当使用到该方法时，才去创建 instance
        //即懒汉式
        if(null == lazySingleton){
            return lazySingleton = new LazySingletonSafe();
        }
        return lazySingleton;
    }
}
