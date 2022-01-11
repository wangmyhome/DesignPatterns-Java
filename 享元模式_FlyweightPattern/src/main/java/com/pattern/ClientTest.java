package com.pattern;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-11
 */
public class ClientTest {
    public static void main(String[] args) {
        BoxFactory.getInstance().getBox("I").display("白色");
    }
}
