package com.pattern.Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-12
 */
public class ClientTest {
    public static void main(String[] args) {
        /*List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("item" + i);
        }
        //获取迭代器
        Iterator<String> listIterator = list.iterator();
        //判断是否还有元素
        while (listIterator.hasNext()) {
            System.err.println(listIterator.next());
            //对剩下的元素执行指定操作
            listIterator.forEachRemaining((String consumer) -> {
                System.err.println(consumer.concat("-test"));
            });
        }*/


        Collection_<String> list1 = new ArrayList_<>();
        for(int i=0; i<15; i++) {
            list1.add("s" + i);
        }
        System.out.println(list1.size());

        //这个接口的调用方式：
        Iterator_<String> it = list1.iterator();
        while(it.hasNext()) {
            String o = it.next();
            System.out.println(o);
        }
    }
}
