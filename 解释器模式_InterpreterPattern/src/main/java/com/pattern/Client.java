package com.pattern;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-13
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        Variable a = new Variable("a");
        Variable b = new Variable("b");
        Variable c = new Variable("c");
        Variable d = new Variable("d");
        Variable e = new Variable("e");
        Variable o = new Variable("o");

        context.assign(a, 1);
        context.assign(b, 2);
        context.assign(c, 3);
        context.assign(d, 4);
        context.assign(e, 5);
        context.assign(o, 8);
        AbstractExpression expression = new Plus(new Minus(new Plus(new Plus(new Plus(a, b), c), d), e),o);
        System.out.println(expression + "= " + expression.interpret(context));
    }
}
