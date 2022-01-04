package com.pattern.dynamicproxy.jdk;

import com.pattern.staticproxy.ChanelFactory;
import com.pattern.staticproxy.SellPerfume;

import java.lang.reflect.Proxy;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-04
 */
public class XiaoMing {
    public static void main(String[] args) {

        //卖香水的厂商
        ChanelFactory chanelFactory = new ChanelFactory();
        //卖红酒的厂商
        SellWineFactory sellWineFactory = new SellWineFactory();

        SellWine sellWine = (SellWine) Proxy.newProxyInstance(sellWineFactory.getClass().getClassLoader(), sellWineFactory.getClass().getInterfaces(), new SellProxyFactory(sellWineFactory));
        sellWine.sellRedWine();

        /**
         * ClassLoader loader：加载动态代理类的类加载器，被代理类的类加载器
         * Class<?>[] interfaces：代理类实现的接口，可以传入多个接口，被代理类的实现的接口
         * InvocationHandler h：指定代理类的调用处理程序，即调用接口中的方法时，会找到该代理工厂h，执行invoke()方法
         * Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h);
         */
        SellPerfume sellPerfume = (SellPerfume) Proxy.newProxyInstance(chanelFactory.getClass().getClassLoader(),
                chanelFactory.getClass().getInterfaces(),
                new SellProxyFactory(chanelFactory));
        sellPerfume.sellPerfume(1999.99);
    }

}
