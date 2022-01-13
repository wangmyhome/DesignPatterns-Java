# 桥接模式模式

> **将抽象部分与实现部分分离，把多种匹配进行组合。**

从多种维度去创建我们需要的产品。



## UML图

![img](http://cdn.processon.com/5d146dbee4b09b5580e9ebca?e=1561623502&token=trhI0BY8QfVrIGn9nENop6JAc6l5nZuxhjQ62UfM:BEEqx9w01HX-ulz6UqgTipc-pZ8=)

- 桥接模式包含如下角色：
  - Abstraction（抽象类）：用于定义抽象类的接口，一般为抽象类而不是接口，其中维持一个Implementor的引用
  - RefinedAbstraction（扩充抽象类）：继承或者实现抽象类，通常情况下为具体类而不是抽象类，实现抽象类中定义的抽象业务方法，在具体业务方法中可以调用Implementor中定义的业务方法
  - Implementor（实现类接口）：定义实现类的接口，一般而言，Implementor接口仅提供基本操作，并交由子类去实现
  - ConcreteImplementor （具体实现类）：实现Implementor中定义的基本操作方法







例如：

从多种维度去创建我们需要的产品。

手机是一个抽象的类（手机包含多种品牌），手机内存是一个抽象的类（有4G，8G，16G）。

（1）分离抽象和实现部分：把手机、内存抽象出来。实现与之分离。

（2）松耦合：两个维度分开

（3）单一职责原则：每个维度各干各的活

```java

//内存
public interface Memory {

    //安装内存
    public void addMemory();
}
public class Memory6G implements Memory{
    @Override
    public void addMemory() {
        System.out.println("手机安装了6G内存");
    }
}
public class Memory8G implements Memory{
    @Override
    public void addMemory() {
        System.out.println("手机安装了8G内存");
    }
}

public abstract class Phone {
    public Memory memory;
    //设置内存
    public void setMemory(Memory memory){
        this.memory = memory;
    }
    public abstract void byPhone();
}

public class XiaoMiPhone extends Phone{

    @Override
    public void byPhone() {
        memory.addMemory();
        System.out.println("购买小米手机");
    }
}

public class HuaWeiPhone extends Phone{

    @Override
    public void byPhone() {
        memory.addMemory();
        System.out.println("购买华为手机");
    }
}

 		public static void main(String[] args) {
        HuaWeiPhone huaWeiPhone = new HuaWeiPhone();
        //选择6g的手机
        huaWeiPhone.setMemory(new Memory6G());
        huaWeiPhone.byPhone();
    }
```

如果未来在有16G，32G的内存不需要改代码。





## 使用场景



