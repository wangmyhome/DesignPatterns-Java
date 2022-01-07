package com.pattern.lazysingleton;

/**
 * 2-4双重检查锁（推荐使用）
 * 线程安全；延迟加载；效率较高
 * 结论：在实际开发中，推荐使用这种单例设计模式
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-06
 */
public class DCLSingleton {
    private volatile static DCLSingleton instance;
    private DCLSingleton() { }
    public static DCLSingleton getInstance(){
        //先判断是否存在，不存在再加锁处理
        if (instance == null){
            //在同一个时刻加了锁的那部分程序只有一个线程可以进入
            synchronized (DCLSingleton.class){
                if (instance == null){
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}
