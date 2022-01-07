package com.pattern.lazysingleton;

/**
 * 2-1懒汉式：（不安全，不推荐使用）
 * 只有在真正去使用的时候才回去加载
 *
 * 线程不安全：
 * 这种写法起到了Lazy Loading的效果，但是只能在单线程下使用。如果在多线程下，
 * 一个线程进入了if (singleton == null)判断语句块，还未来得及往下执行，另一个
 * 线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程环境下不可使用这种方式。
 *
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-06
 */
public class LazySingleton {

    private static LazySingleton lazySingleton;
    //构造器私有化, 外部不能new
    private LazySingleton(){}
    public static LazySingleton getInstance(){
        //提供一个静态的公有方法，当使用到该方法时，才去创建 instance
        //即懒汉式
        if(null == lazySingleton){
            return lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
