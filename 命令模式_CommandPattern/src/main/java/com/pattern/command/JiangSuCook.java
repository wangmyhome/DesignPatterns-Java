package com.pattern.command;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

/**
 * 江苏厨师类
 */
public class JiangSuCook implements ICook {


    @Override
    public void doCooking() {
        System.out.println("江苏厨师，烹饪苏菜，宫廷第二大菜系，古今国宴上最受人欢迎的菜系。");
    }

}