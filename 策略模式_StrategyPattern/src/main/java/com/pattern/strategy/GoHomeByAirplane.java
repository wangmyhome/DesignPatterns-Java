package com.pattern.strategy;

/**
 * 回家做飞机
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class GoHomeByAirplane implements GoHomeStrategy{

    @Override
    public void goHome() {
        System.out.println("坐飞机回家。。。。");
    }
}
