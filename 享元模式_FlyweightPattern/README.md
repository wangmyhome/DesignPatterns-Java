# 享元模式

享元模式（共享元数据模式）

运用共享技术来有效地支持大量细粒度对象的复用。它通过共享已经存在的对象来大幅度减少需要创建的对象数量、避免大量相似对象的开销，从而提高系统资源的利用率。



提到享元模式，就应该想到字符串的设计。一旦创建不会改变，创建的字符都放在字符串池中，需要就需要去串池中引用。



## UML图

![image-20220111140723488](/Users/wangshanpeng/Library/Application Support/typora-user-images/image-20220111140723488.png)

**角色：**

- Flyweight（抽象享元类）：通常是一个接口或者是抽象类，在抽象享元类中声明了共享享元类的公共方法，通过这些方法可以访问享元类的内部数据（内部状态），也可以设置享元类的外部数据（外部状态）
- Concrete Flyweight（共享具体享元类）：实现抽象享元类，其实例称为享元对象，一般为单例模式，提供唯一的享元对象
- UnsharedConcrete Flyweight（非共享具体享元类）：实现抽象享元类，但是不共享，通过构造函数进行实例化
- Flyweight Factory（享元工厂类）：创建并管理享元对象，以内部的键值对结构存储享元对象



代码：https://github.com/wangmyhome/DesignPatterns-Java





## 使用场景

- Integer类使用了享元模式

```java
Intager.valueOf(1);

//如果-128～128之间的数就会去缓存中去数据，否者就是new
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```

- 字符串池



