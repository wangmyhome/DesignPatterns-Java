package com.pattern;

/**
 * 加法 非终结符表达式角色
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-13
 */
public class Plus extends AbstractExpression {

    private AbstractExpression left;

    private AbstractExpression right;

    public Plus(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) + right.interpret(context);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " + " + right.toString() + ")";
    }
}
