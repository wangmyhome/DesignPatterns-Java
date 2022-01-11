package com.pattern.command;

/**
 * 广东菜
 */
public class GuangDoneCuisine implements ICuisine {

    //厨师
    private ICook cook;

    public GuangDoneCuisine(ICook cook) {
        this.cook = cook;
    }

    @Override
    public void cook() {
        cook.doCooking();
    }

}