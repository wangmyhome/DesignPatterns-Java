package com.pattern.bridge;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-10
 */
public class Memory6G implements Memory{
    @Override
    public void addMemory() {
        System.out.println("手机安装了6G内存");
    }
}
