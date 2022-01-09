package com.pattern.methodfactory;

import com.pattern.abstractfactory.AppleFactory;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class ClientMethodTest {
    public static void main(String[] args) {
        MethodFactory xiaoMiFactory = new XiaoMiPhoneFactory();
        MethodFactory appleFactory = new ApplePhoneFactory();
        xiaoMiFactory.makePhone();
        appleFactory.makePhone();
    }
}
