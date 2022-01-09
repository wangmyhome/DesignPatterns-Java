package com.pattern;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-09
 */
public class BuilderTest {

    public static void main(String[] args) {
        Boy1 boy1 = Boy1.builder().age(1).height(1).build();
        Boy2 boy2 = Boy2.builder().age(2).height(2).build();
        Boy3 boy3 = Boy3.builder().age(3).height(3).build();
        System.out.println(boy1);
        System.out.println(boy2);
        System.out.println(boy3);
    }
}
