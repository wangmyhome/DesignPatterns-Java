package com.pattern.prototype;

import java.io.Serializable;


public class DeepProtoType implements Serializable,Cloneable {

    public String name;//String 属性
    public DeepCloneable deepCloneable;//引用类型
    public DeepProtoType(){
        super();
    }

    /**
     * 深拷贝 方式1使用clone方法
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object deep = null;
        //这里完成基本类型和String类型的克隆
        deep = super.clone();
        //对引用类型的属性，进行单独处理
        DeepProtoType deepProtoType = (DeepProtoType) deep;
        deepProtoType.deepCloneable = (DeepCloneable) deepCloneable.clone();
        return deep;
    }
    static class DeepCloneable implements Cloneable{
        @Override
        protected Object clone() throws CloneNotSupportedException {
            Object deep = null;
            //这里完成基本类型和String类型的克隆
            deep = super.clone();
            return deep;
        }
    }
}