package com.pattern.abstractfactory;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-08
 */
public class ClientAbstractTest {

    public static void main(String[] args) {
        AppleFactory appleFactory = new AppleFactory();
        appleFactory.makePC();
        appleFactory.makePhone();

        XiaoMiFactory xiaoMiFactory = new XiaoMiFactory();
        xiaoMiFactory.makePC();
        xiaoMiFactory.makePhone();


    }
}
