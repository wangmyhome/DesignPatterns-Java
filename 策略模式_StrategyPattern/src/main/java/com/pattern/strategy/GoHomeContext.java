package com.pattern.strategy;

import java.util.Arrays;

/**
 *
 * 环境类
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-07
 */
public class GoHomeContext {

    private GoHomeStrategy goHomeStrategy;

    public GoHomeStrategy getGoHomeStrategy() {
        return goHomeStrategy;
    }

    public void setGoHomeStrategy(GoHomeStrategy goHomeStrategy) {
        this.goHomeStrategy = goHomeStrategy;
    }

    public void goHome(){
        goHomeStrategy.goHome();
    }
}
