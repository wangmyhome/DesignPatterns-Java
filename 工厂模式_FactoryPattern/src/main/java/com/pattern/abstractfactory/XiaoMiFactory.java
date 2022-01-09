package com.pattern.abstractfactory;


/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-08
 */
public class XiaoMiFactory implements AbstractFactory{
    @Override
    public Phone makePhone() {
        return new MiPhone();
    }

    @Override
    public PC makePC() {
        return new MAC();
    }
}
