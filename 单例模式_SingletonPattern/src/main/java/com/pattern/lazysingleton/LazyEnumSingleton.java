package com.pattern.lazysingleton;

/**
 * 2-6 懒加载，枚举类型
 * 枚举不会被反序列化
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public enum LazyEnumSingleton {
    INSTANCE;
    public LazyEnumSingleton getInstance(){
        return LazyEnumSingleton.INSTANCE;
    }
}
