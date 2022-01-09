package com.pattern.abstractfactory;


/**
 * 每一个品牌商可以生产的产品
 */
public interface AbstractFactory {
    Phone makePhone();

    PC makePC();
}