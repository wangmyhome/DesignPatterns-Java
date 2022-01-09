package com.pattern.abstractfactory;

public class MAC extends PC{
    public MAC(){
        this.make();
    }
    @Override
    public void make() {
        System.out.println("制作苹果电脑");
    }
}