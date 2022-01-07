package com.pattern.lazysingleton;

/**
 *
 * 2-5静态内部类（推荐使用）
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class LazySingletonStaticSafe {

    private LazySingletonStaticSafe(){
    }
    public static class  LazySingletonStaticSafeHandler{
        public static LazySingletonStaticSafe lazySingletonStaticSafe = new LazySingletonStaticSafe();
    }

    public LazySingletonStaticSafe getInstance(){
        return LazySingletonStaticSafeHandler.lazySingletonStaticSafe;
    }
}
