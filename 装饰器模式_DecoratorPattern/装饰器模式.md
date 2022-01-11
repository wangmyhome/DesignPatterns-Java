# 装饰器模式

装饰器的核心就是再不改原有类的基础上给类新增功能。不改变原有类，可能有的小伙伴会想到继承、AOP切面，当然这些方式都可以实现，但是使用装饰器模式会是另外一种思路更为灵活，可以避免继承导致的子类过多，也可以避免AOP带来的复杂性。

动态地给对象添加一些额外的职责或者行为，装饰器模式相比于生成子类更为灵活。

## UML图

![image-20220111090035938](/Users/wangshanpeng/Library/Application Support/typora-user-images/image-20220111090035938.png)

**角色：**

1.抽象构件角色（Component）-定义抽象接口
2.具体构件角色（ConcreteComponent）-实现抽象接口，可以是一组
3.装饰角色（Decorator）-定义抽象类并继承接口中的方法，保证一致性
4.具体装饰角色（ConcreteDecorator）-扩展装饰具体的实现逻辑





例如：

我们去买一杯咖啡，加奶加糖加糖。

我们定义好所有的单品咖啡，和咖啡中可以加的调料（糖、奶、盐...）。

当需要购买咖啡时，可以动态的给咖啡加糖加奶加等等。



代码实现：

定一个`抽象构件`，饮品抽象类。

```java
@Setter
@Getter
public abstract class Drink {

    /**
     * 饮品描述
     */
    public String des;
    /**
     * 饮品价格
     */
    private float price = 0.0f;

    /**
     * 计算费用的抽象方法
     * 子类来实现
     * @return
     */
    public abstract float cost();
}
```

定义饮品中的咖啡接口

```java
public class Coffee extends Drink {
    @Override
    public float cost() {
        return super.getPrice();
    }
}

```

添加`具体构件`类，咖啡1234...

```java
public class Coffee1 extends Coffee{
    public Coffee1(){
        setDes("第一种具体的咖啡");
        setPrice(100.0f);
    }
}
public class Coffee2 extends Coffee{
    public Coffee2(){
        setDes("第二种具体的咖啡");
        setPrice(200.0f);
    }
}
```

定义`装饰角色`，需要继承`抽象构件`

```java
public class Decorator extends Drink{

    private Drink drink;

    public Decorator(Drink drink){
        this.drink = drink;
    }

    @Override
    public float cost() {
        //当前花费的钱+添加装饰的钱
        return drink.cost() + super.getPrice();
    }

    @Override
    public String getDes() {
        return  drink.getDes()+ "&&" + super.getDes();
    }
}
```

添加`具体抽象构件`

```java
public class Milk extends Decorator{
    public Milk(Drink drink) {
        super(drink);
        //加牛奶，10元
        setPrice(10.0f);
        setDes("加牛奶");
    }
}

public class Sugar extends Decorator{
    public Sugar(Drink drink) {
        super(drink);
        //加糖，5元
        setPrice(5.0f);
        setDes("加糖");
    }
}
```

测试

```java
public static void main(String[] args) {

  //点一份单品的咖啡1
  Drink drink = new Coffee1();
  System.out.println("点一份单品的咖啡1费用： " + drink.cost());
  System.out.println("点一份单品的咖啡1描述： " + drink.getDes());

  //点一份单品的咖啡1 + 1份糖
  drink = new Sugar(drink);
  System.out.println("点一份单品的咖啡1+1份糖费用： " + drink.cost());
  System.out.println("点一份单品的咖啡1和1份糖描述： " + drink.getDes());

  //点一份单品的咖啡1 + 2份糖
  drink = new Sugar(drink);
  System.out.println("点一份单品的咖啡1+2份糖费用： " + drink.cost());
  System.out.println("点一份单品的咖啡1和2份糖描述： " + drink.getDes());
}

//结果
点一份单品的咖啡1费用： 100.0
点一份单品的咖啡1描述： 第一种具体的咖啡
点一份单品的咖啡1+1份糖费用： 105.0
点一份单品的咖啡1和1份糖描述： 第一种具体的咖啡&&加糖
点一份单品的咖啡1+2份糖费用： 110.0
点一份单品的咖啡1和2份糖描述： 第一种具体的咖啡&&加糖&&加糖
```

如果后期再加其他咖啡，增加一个构件具体类，再加其他调料就增加装饰具体类就行，也可以和其他咖啡组合。

![image-20220111092810090](/Users/wangshanpeng/Library/Application Support/typora-user-images/image-20220111092810090.png)

## 使用场景

https://juejin.cn/post/6844903681322647566#comment

- Java I/O 中的装饰者模式

```java
DataInputStream in=new DataInputStream(new BufferedInputStream(new  FileInputStream("D:\\hello.txt")));     
```



- spring session 中的装饰者模式
- Mybatis 缓存中的装饰者模式

