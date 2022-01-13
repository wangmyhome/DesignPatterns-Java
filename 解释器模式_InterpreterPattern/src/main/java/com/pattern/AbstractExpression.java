package com.pattern;

/**
 * 抽象角色AbstractExpression
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-13
 */
public abstract class AbstractExpression {
    /**
     * 运算
     * @param context
     * @return
     */
    public abstract int interpret(Context context);
}
