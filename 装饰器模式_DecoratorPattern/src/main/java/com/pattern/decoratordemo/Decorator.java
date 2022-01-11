package com.pattern.decoratordemo;

/**
 * 抽象装饰者
 * 装饰抽象
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-11
 */
public class Decorator extends Drink{

    private Drink drink;

    public Decorator(Drink drink){
        this.drink = drink;
    }

    @Override
    public float cost() {
        //当前花费的钱+添加装饰的钱
        return drink.cost() + super.getPrice();
    }

    @Override
    public String getDes() {
        return  drink.getDes()+ "&&" + super.getDes();
    }
}
