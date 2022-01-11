package com.pattern.facededemo;

/**
 * 智能音箱
 *
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-11
 */
public class SmartFacade {
    private Light light;
    private TV tv;
    private AirCondition airCondition;

    public SmartFacade() {
        light = new Light();
        tv = new TV();
        airCondition = new AirCondition();
    }

    public void say(String message) {
        if (message.contains("打开")) {
            on();
        } else if (message.contains("关闭")) {
            off();
        } else {
            System.out.println("我还听不懂你说的！！！");
        }
    }

    //起床后一键开电器
    private void on() {
        System.out.println("起床了");
        light.on();
        tv.on();
        airCondition.on();
    }

    //睡觉一键关电器
    private void off() {
        System.out.println("睡觉了");
        light.off();
        tv.off();
        airCondition.off();
    }
}
