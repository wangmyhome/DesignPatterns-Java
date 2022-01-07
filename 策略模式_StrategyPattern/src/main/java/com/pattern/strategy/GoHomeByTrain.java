package com.pattern.strategy;

/**
 * 回家通过火车
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class GoHomeByTrain implements GoHomeStrategy{

    @Override
    public void goHome() {
        System.out.println("坐火车回家。。。。");
    }

}
