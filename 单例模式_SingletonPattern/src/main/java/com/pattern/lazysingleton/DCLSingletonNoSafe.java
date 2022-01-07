package com.pattern.lazysingleton;

/**
 * 2-3双重检查锁（不安全，不推荐使用）
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class DCLSingletonNoSafe {

    private static DCLSingletonNoSafe instance;
    private DCLSingletonNoSafe() { }
    public static DCLSingletonNoSafe getInstance(){
        //先判断是否存在，不存在再加锁处理
        if (instance == null){
            //在同一个时刻加了锁的那部分程序只有一个线程可以进入
            synchronized (DCLSingletonNoSafe.class){
                if (instance == null){
                    //在对象实例化的时候，分为三个步骤 分配内存空间，对象默认初始化，将对象指向内存空间地址。jit优化会对这三个步骤重排序，
                    instance = new DCLSingletonNoSafe();
                }
            }
        }
        return instance;
    }
}
