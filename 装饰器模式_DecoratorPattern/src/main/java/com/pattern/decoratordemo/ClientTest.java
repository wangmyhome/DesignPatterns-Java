package com.pattern.decoratordemo;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-11
 */
public class ClientTest {

    public static void main(String[] args) {

        //点一份单品的咖啡1
        Drink drink = new Coffee1();
        System.out.println("点一份单品的咖啡1费用： " + drink.cost());
        System.out.println("点一份单品的咖啡1描述： " + drink.getDes());

        //点一份单品的咖啡1 + 1份糖
        drink = new Sugar(drink);
        System.out.println("点一份单品的咖啡1+1份糖费用： " + drink.cost());
        System.out.println("点一份单品的咖啡1和1份糖描述： " + drink.getDes());

        //点一份单品的咖啡1 + 2份糖
        drink = new Sugar(drink);
        System.out.println("点一份单品的咖啡1+2份糖费用： " + drink.cost());
        System.out.println("点一份单品的咖啡1和2份糖描述： " + drink.getDes());
    }
}
