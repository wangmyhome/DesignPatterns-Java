package com.pattern;

import java.util.HashMap;
import java.util.Map;

/**
 * 环境类
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-13
 */
public class Context {
    private Map<Variable, Integer> map = new HashMap<Variable, Integer>();

    public void assign(Variable var, Integer value) {
        map.put(var, value);
    }

    public int getValue(Variable var) {
        return map.get(var);
    }
}
