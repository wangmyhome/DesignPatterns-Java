package com.pattern.abstractfactory;

public class MiPC extends PC{

    public MiPC(){
        this.make();
    }
    @Override
    public void make() {
        System.out.println("制作小米电脑");
    }
}