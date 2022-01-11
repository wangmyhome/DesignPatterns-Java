package com.pattern;

/**
 *
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-11
 */
public abstract class AbstractBox {
    public abstract String getShape();

    public void display(String color) {
        System.out.println("方块形状：" + this.getShape() + " 颜色：" + color);
    }
}
