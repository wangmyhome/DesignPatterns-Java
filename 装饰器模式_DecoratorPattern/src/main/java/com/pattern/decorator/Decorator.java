package com.pattern.decorator;

/**
 * 抽象装饰者
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-10
 */
public class Decorator extends Component{
    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    void operation() {
        component.operation();
    }
}
