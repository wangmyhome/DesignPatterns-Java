# 建造者模式

是创建型设计模式比较简单。主要是动态的构造出一个对象。

例如平常我们new完对象，还要进行set属性值操作。使用该模式可以代替编写复杂的代码。

使用的是链式编程，例如lambda表达式，@Builder注解，StringBuilder源码，都是建造者模式。



自定义来手写一个@Builder是的功能

```java
//方式1
@ToString
public class Boy {
    private String name;
    private int age;
    private int weight;
    private int height;

    protected Boy build(){
        return this;
    }
    public static BoyBuilder builder(){
        return new BoyBuilder();
    }
    public static class BoyBuilder extends Boy{
        public BoyBuilder name(String name){
            super.name = name;
            return this;
        }
        public BoyBuilder age(int age){
            super.age = age;
            return this;
        }
        public BoyBuilder weight(int weight){
            super.weight = weight;
            return this;
        }
        public BoyBuilder height(int height){
            super.height = height;
            return this;
        }
        @Override
        public Boy build() {
            return super.build();
        }
    }
}


public static void main(String[] args) {
  Boy boy = Boy.builder().age(1).height(2).build();
  System.out.println(boy);
}
//Boy(name=null, age=1, weight=0, height=2)


```

```java
//方式2
@ToString
public class Boy2 {
    private String name;
    private int age;
    private int weight;
    private int height;

    private Boy2(BoyBuilder builder){
        name = builder.name;
        age = builder.age;
        weight = builder.weight;
        height = builder.height;
    }

    public static BoyBuilder builder(){
        return new BoyBuilder();
    }
    public static class BoyBuilder{
        private String name;
        private int age;
        private int weight;
        private int height;

        public BoyBuilder name(String name){
            this.name = name;
            return this;
        }
        public BoyBuilder age(int age){
            this.age = age;
            return this;
        }
        public BoyBuilder weight(int weight){
            this.weight = weight;
            return this;
        }
        public BoyBuilder height(int height){
            this.height = height;
            return this;
        }
        public Boy2 build() {
            return new Boy2(this);
        }
    }
}
```

使用这种模式可以动态的加载属性值。链式编程的方式提高了阅读性，代码的简洁性。

# 应用场景

- lambda表达式
- @Builder注解
- StringBuilder源码

