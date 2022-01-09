package com.pattern.smplefactory;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class ClientTest {
    public static void main(String[] args) {
        PhoneFactory phoneFactory = new PhoneFactory();
        MiPhone miphone = (MiPhone) phoneFactory.makePhone("Miphone");
//        IPhone iPone = (IPhone) phoneFactory.makePhone("IPhone");


    }
}
