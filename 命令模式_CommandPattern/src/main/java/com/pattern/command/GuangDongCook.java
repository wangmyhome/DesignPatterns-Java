package com.pattern.command;

/**
 * 广东厨师类
 */
public class GuangDongCook implements ICook {


    @Override
    public void doCooking() {
        System.out.println("广东厨师，烹饪鲁菜，宫廷最大菜系，以孔府风味为龙头");
    }

}