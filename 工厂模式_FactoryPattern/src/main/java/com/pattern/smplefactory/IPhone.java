package com.pattern.smplefactory;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class IPhone implements Phone{
    public IPhone() {
        this.make();
    }
    @Override
    public void make() {
        System.out.println("制作苹果手机");
    }
}