# 外观模式

外观（Facade）模式是“迪米特法则”的典型应用。

定义一个高层接口，供客户端调用访问子系统。

## UML图

![img](http://cdn.processon.com/5d14d511e4b0a07de827ed36?e=1561649953&token=trhI0BY8QfVrIGn9nENop6JAc6l5nZuxhjQ62UfM:_lOSi2T9Rs0ZZvLF3Of4H_PX8O4=)

**角色：**

- 外观（Facade）角色：为多个子系统对外提供一个共同的接口。

- 子系统（Sub System）角色：实现系统的部分功能，客户可以通过外观角色访问它。





案例：智能家电控制

小明的爷爷已经60岁了，一个人在家生活：每次都需要打开灯、打开电视、打开空调；睡觉时关闭灯、关闭电视、关闭空调；操作起来都比较麻烦。所以小明给爷爷买了智能音箱，可以通过语音直接控制这些智能家电的开启和关闭。

代码实现：

创建子系统模块

```java
public class Light {
    public void on() {
        System.out.println("打开了灯....");
    }

    public void off() {
        System.out.println("关闭了灯....");
    }
}

public class TV {
    public void on() {
        System.out.println("打开了电视....");
    }

    public void off() {
        System.out.println("关闭了电视....");
    }
}

public class AirCondition {
    public void on() {
        System.out.println("打开了空调....");
    }

    public void off() {
        System.out.println("关闭了空调....");
    }
}
```

定义外观提供类

```java
public class SmartAppliancesFacade {
    private Light light;
    private TV tv;
    private AirCondition airCondition;

    public SmartAppliancesFacade() {
        light = new Light();
        tv = new TV();
        airCondition = new AirCondition();
    }

    public void say(String message) {
        if (message.contains("打开")) {
            on();
        } else if (message.contains("关闭")) {
            off();
        } else {
            System.out.println("我还听不懂你说的！！！");
        }
    }

    //起床后一键开电器
    private void on() {
        System.out.println("起床了");
        light.on();
        tv.on();
        airCondition.on();
    }

    //睡觉一键关电器
    private void off() {
        System.out.println("睡觉了");
        light.off();
        tv.off();
        airCondition.off();
    }
}
```

客户端测试

```java
public static void main(String[] args) {
    //创建外观对象
    SmartAppliancesFacade facade = new SmartAppliancesFacade();
    //客户端直接与外观对象进行交互
    facade.say("打开家电");
    facade.say("关闭家电");
}
```

![image-20220111100636286](/Users/wangshanpeng/Library/Application Support/typora-user-images/image-20220111100636286.png)

**好处：**

降低了子系统与客户端之间的耦合度，使得子系统的变化不会影响调用它的客户类。

对客户屏蔽了子系统组件，减少了客户处理的对象数目，并使得子系统使用起来更加容易。

**缺点：**

不符合开闭原则，修改很麻烦





## 使用场景

- spring JDBC 中的外观模式
- Mybatis中的外观模式
- Tomcat 中的外观模式
- SLF4J 中的外观模式



