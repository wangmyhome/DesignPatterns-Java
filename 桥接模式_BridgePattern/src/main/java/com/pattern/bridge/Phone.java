package com.pattern.bridge;

/**
 * 手机抽象类
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-10
 */
public abstract class Phone {

    public Memory memory;
    //设置内存
    public void setMemory(Memory memory){
        this.memory = memory;
    }
    public abstract void byPhone();
}
