package com.pattern.facede;

public class Facade {

    private SubSystemA sa;
    private SubSystemB sb;
    private SubSystemC sc;

    public Facade() {
        sa = new SubSystemA();
        sb = new SubSystemB();
        sc = new SubSystemC();
    }

    public void show() {
        sa.show();
        sb.show();
        sc.show();
    }
}