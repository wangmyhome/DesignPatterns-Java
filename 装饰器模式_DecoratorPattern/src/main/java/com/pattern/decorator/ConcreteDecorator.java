package com.pattern.decorator;

/**
 * 具体抽象装饰者
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-10
 */
public class ConcreteDecorator extends Decorator{


    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    void operation() {
        super.operation();
    }
}
