package com.pattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 小二调用者
 * @author wangshanpeng
 */
public class XiaoEr {


    private List<ICuisine> cuisineList = new ArrayList<ICuisine>();

    public void order(ICuisine cuisine) {
        cuisineList.add(cuisine);
    }

    public synchronized void placeOrder() {
        for (ICuisine cuisine : cuisineList) {
            cuisine.cook();
        }
        cuisineList.clear();
    }

}