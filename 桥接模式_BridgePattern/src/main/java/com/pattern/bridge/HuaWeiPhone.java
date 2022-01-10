package com.pattern.bridge;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-10
 */
public class HuaWeiPhone extends Phone{

    @Override
    public void byPhone() {
        memory.addMemory();
        System.out.println("购买华为手机");
    }
}
