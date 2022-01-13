# 适配器模式

适配器在项目中常见的运用是，服务提供的接口，通过适配器转换成目类需要的接口。

例如手机充电需要5V的电压，家用电是220V，需要通过充电器适配将220V电压转化成5V电压。

## UML图

![img](http://cdn.processon.com/5d141e96e4b02c7470fe69d7?e=1561603238&token=trhI0BY8QfVrIGn9nENop6JAc6l5nZuxhjQ62UfM:9fXVknevSzL5fz35nMKGHQFJMwY=)

- 对象适配器模式包含如下角色
  - Target（目标抽象类）：定义客户所需接口，可以是接口、抽象类或者具体类
  - Adapter（适配器）：继承实现Target关联Adaptee，完成接口的转换
  - Adaptee（适配者）：定义存在的接口被适配器适配

## 类适配器模式

> 基本介绍：Adapter类，通过继承 src(被适配)类，实现 dst(目标)类接口， 完成src->dst的适配

> 应用实例 以生活中充电器的例子来讲解适配器，充电器本身相当于Adapter， 220V交流电相当于src (即被适配者)，我们的目dst(即 目标)是5V直流电

```java
/**
 * 适配接口
 */
public interface IVoltage5V {
	public int output5V();
}

/**
 * 被适配的类
 */
public class Voltage220V {
    //输出220V的电压
    public int output220V() {
        int src = 220;
        System.out.println("电压=" + src + "伏");
        return src;
    }
}
/**
 * 适配器类
 */
public class VoltageAdapter  extends Voltage220V implements IVoltage5V {

    @Override
    public int output5V() {
        // TODO Auto-generated method stub
        //获取到220V电压
        int srcV = output220V();
        int dstV = srcV / 44 ; //转成 5v
        return dstV;
    }
}

public class Phone {
    //充电
    public void charging(IVoltage5V iVoltage5V) {
        if(iVoltage5V.output5V() == 5) {
            System.out.println("电压为5V, 可以充电~~");
        } else if (iVoltage5V.output5V() > 5) {
            System.out.println("电压大于5V, 不能充电~~");
        }
    }
}
public class Client {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(" === 类适配器模式 ====");
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
    }
}
```



优缺点：

Java是单继承机制，所以类适配器需要继承src类这一点算是一 个缺点, 因为这要求dst必须是接口，有一定局限性;

src类的方法在Adapter中都会暴露出来，也增加了使用的成本。 

由于其继承了src类，所以它可以根据需求重写src类的方法， 使得Adapter的灵活性增强了。

## 对象适配器模式

> 基本思路和类的适配器模式相同，只是将Adapter类作修改，不是继承src类，而是持有src类的实例，以解决兼容性的问题。 
> 即：持有 src类，实现 dst 类接口，完成src->dst的适配
>
> 根据“合成复用原则”，在系统中尽量使用关联关系来替代继承关系。
>
> 对象适配器模式是适配器模式常用的一种



> 应用实例说明 以生活中充电器的例子来讲解适配器，充电器本身相当于Adapter，220V交流电相当于src (即被适配者)，我们的目dst(即目标)是5V直流电，使用对象适配器模式完成

```java
public class VoltageAdapter  implements IVoltage5V {

    private Voltage220V voltage220V; // 关联关系-聚合

    //通过构造器，传入一个 Voltage220V 实例
    public VoltageAdapter(Voltage220V voltage220v) {
        this.voltage220V = voltage220v;
    }

    @Override
    public int output5V() {
        int dst = 0;
        if(null != voltage220V) {
            int src = voltage220V.output220V();//获取220V 电压
            System.out.println("使用对象适配器，进行适配~~");
            dst = src / 44;
            System.out.println("适配完成，输出的电压为=" + dst);
        }
        return dst;
    }

}

public static void main(String[] args) {
  System.out.println(" === 类适配器模式 ====");
  Phone phone = new Phone();
  phone.charging(new VoltageAdapter(new Voltage220V()));
}
```

优缺点：

对象适配器和类适配器其实算是同一种思想，只不过 实现方式不同。根据合成复用原则，使用组合替代继 承， 所以它解决了类适配器必须继承src的 局限性问题，也不再要求dst必须是接口。 

使用成本更低，更灵活。

## 接口适配器模式

> 一些书籍称为：适配器模式(Default Adapter Pattern)或缺省适配器模式。
>
> 当不需要全部实现接口提供的方法时，可先设计一个抽象类实现接口，并为该接口中每个方法提供一个默认实现（空方法），那么该抽象类的子类可有选择地覆盖父类的某些方法来实现需求
>
> 适用于一个接口不想使用其所有的方法的情况。



项目中应用实例

**目标接口请求参数和响应参数字段**和**提供方接口请求参数和响应参数字段**不一致，提供一个适配层，对目标接口和提供方接口做一个适配。



优缺点：

符合开闭原则，不过写的代码量增加。



## 使用场景

### Spring MVC HandlerAdapter


