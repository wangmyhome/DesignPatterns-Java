package com.pattern;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-09
 */
public class BuilderTest {

    public static void main(String[] args) {
        Boy boy = Boy.builder().age(1).height(2).build();
        System.out.println(boy);
    }
}
