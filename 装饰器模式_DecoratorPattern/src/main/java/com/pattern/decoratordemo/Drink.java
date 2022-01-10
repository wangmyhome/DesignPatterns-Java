package com.pattern.decoratordemo;


import lombok.Getter;
import lombok.Setter;

/**
 * 饮料抽象类
 * 抽象构件
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-10
 */
@Setter
@Getter
public abstract class Drink {

    /**
     * 饮品描述
     */
    public String des;
    /**
     * 饮品价格
     */
    private float price = 0.0f;

    /**
     * 计算费用的抽象方法
     * 子类来实现
     * @return
     */
    public abstract float cost();
}
