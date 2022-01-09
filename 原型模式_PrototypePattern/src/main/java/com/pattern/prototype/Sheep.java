package com.pattern.prototype;

/**
 * 浅拷贝
 */
public class Sheep implements Cloneable{

    private String name;
    private String age;
    
    /**
     * 浅拷贝
     * @return
     */
    @Override
    protected Object clone() {
        Sheep sheep = null;
        try {
            sheep = (Sheep) super.clone();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return sheep;
    }   
}