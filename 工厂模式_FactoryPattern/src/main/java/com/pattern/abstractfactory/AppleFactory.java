package com.pattern.abstractfactory;


/**
 * 苹果工厂
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-08
 */
public class AppleFactory implements AbstractFactory{
    @Override
    public Phone makePhone() {
        return new IPhone();
    }

    @Override
    public PC makePC() {
        return new MAC();
    }
}
