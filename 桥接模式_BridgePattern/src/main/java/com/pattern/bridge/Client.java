package com.pattern.bridge;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-10
 */
public class Client {

    public static void main(String[] args) {
        HuaWeiPhone huaWeiPhone = new HuaWeiPhone();
        //选择6g的手机
        huaWeiPhone.setMemory(new Memory6G());
        huaWeiPhone.byPhone();

    }
}
