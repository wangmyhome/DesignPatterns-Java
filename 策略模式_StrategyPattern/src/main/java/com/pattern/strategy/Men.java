package com.pattern.strategy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class Men {

    public static void main(String[] args) {
        Arrays.sort(new Integer[]{2,3,42,2}, (o1, o2) -> o2 - o1);
        GoHomeContext goHomeContext = new GoHomeContext();
        //1.开车
        goHomeContext.setGoHomeStrategy(new GoHomeByCar());
        goHomeContext.goHome();
        //2.坐火车
//        goHomeContext.setGoHomeStrategy(new GoHomeByTrain());
//        goHomeContext.goHome();
//        //3.坐飞机
//        goHomeContext.setGoHomeStrategy(new GoHomeByAirplane());
//        goHomeContext.goHome();
    }
}
