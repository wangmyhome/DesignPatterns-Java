package com.pattern.strategy;

/**
 * 开车回家
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class GoHomeByCar implements GoHomeStrategy{

    @Override
    public void goHome() {
        System.out.println("开车回家。。。。");
    }

}
