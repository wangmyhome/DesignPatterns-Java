# 单例模式的八种写法

**单例模式是一种常用的软件设计模式，其定义是单例对象的类只能允许一个实例存在。**

许多时候整个系统只需要拥有一个的全局对象，这样有利于我们协调系统整体的行为。比如在某个服务器程序中，该服务器的配置信息存放在一个文件中，这些配置数据由一个单例对象统一读取，然后服务进程中的其他对象再通过这个单例对象获取这些配置信息。这种方式简化了在复杂环境下的配置管理。

## 一、饿汉式

### 1-1饿汉式（静态常量）

```java
public class HungrySingleton {
    private final static HungrySingleton hungrySingleton = new HungrySingleton();
    //1. 构造器私有化, 外部不能new
    private HungrySingleton(){}
    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }
}
```

我们知道，**<font color="red">类加载的方式是按需加载，且加载一次</font>**。因此，在上述单例类被加载时，就会实例化一个对象并交给自己的引用，供系统使用；而且，由于这个类在整个生命周期中只会被加载一次，因此只会创建一个实例，即能够充分保证单例。

> ==优点==：这种写法比较简单，就是在**类装载的时候就完成实例化**。避免了线程同步问题。
>
> ==缺点==：在类装载的时候就完成实例化，没有达到Lazy Loading的效果。如果从始至终从未使用过这个实例，则会造成内存的浪费。

### 1-2饿汉式（静态代码块）

```java
public class HungrySingletonStatic {

    private static HungrySingletonStatic instance;
    static {
        instance = new HungrySingletonStatic();
    }
    private HungrySingletonStatic() {}

    public static HungrySingletonStatic getInstance() {
        return instance;
    }
}
```

这种方式和上面的方式其实类似，只不过将类实例化的过程放在了静态代码块中，也是在类装载的时候，就执行静态代码块中的代码，初始化类的实例。优缺点和上面是一样的。



## 2 懒汉式

### 2-1懒汉式（不安全）

```java
public class LazySingleton {
    private static LazySingleton lazySingleton;
    //构造器私有化, 外部不能new
    private LazySingleton(){}
    public static LazySingleton getInstance(){
        //提供一个静态的公有方法，当使用到该方法时，才去创建 instance
        //即懒汉式
        if(null == lazySingleton){
            return lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}
```

我们从懒汉式单例可以看到，单例实例被<font color="red">**延迟加载**</font>，即只有在真正使用的时候才会实例化一个对象并交给自己的引用。

这种写法起到了Lazy Loading的效果，但是只能在单线程下使用。如果在多线程下，一个线程进入了if (singleton == null)判断语句块，还未来得及往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程环境下不可使用这种方式。

### 2-2懒汉式（同步方法，安全）

```java
public class LazySingletonSafe {
    private static LazySingletonSafe lazySingleton;
    //构造器私有化, 外部不能new
    private LazySingletonSafe(){}
    public static synchronized LazySingletonSafe getInstance(){
        //提供一个静态的公有方法，当使用到该方法时，才去创建 instance
        //即懒汉式
        if(null == lazySingleton){
            return lazySingleton = new LazySingletonSafe();
        }
        return lazySingleton;
    }
}
```

使用synchronized关键字，锁住整个方法，线程安全。效率低。

### 2-3 懒汉式（双重加锁机制，不安全）

```java
public class DCLSingletonNoSafe {
    private static DCLSingletonNoSafe instance;
    private DCLSingletonNoSafe() { }
    public static DCLSingletonNoSafe getInstance(){
        //先判断是否存在，不存在再加锁处理
        if (instance == null){
            //在同一个时刻加了锁的那部分程序只有一个线程可以进入
            synchronized (DCLSingletonNoSafe.class){
                if (instance == null){
                    //在对象实例化的时候，分为三个步骤 分配内存空间，对象默认初始化，将对象指向内存空间地址。jit优化会对这三个步骤重排序，
                    instance = new DCLSingletonNoSafe();
                }
            }
        }
        return instance;
    }
}
```

实例化对象的读写操作步骤可能会重排序，可能出现2，3步骤调换，出现错误。

例如：当a线程分配空间后执行3，此时b线程进来后发现对象不为空，就会出现跳出if判断。

> 1. 分配内存空间
> 2. 初始化对象
> 3. 将对象指向刚分配的内存空间

<font color=red>使用了volatile关键字后，重排序被禁止，所有的写（write）操作都将发生在读（read）操作之前。</font>

==优点==：解决多线程下访问，保证了单例，提高了性能。

结论：在实际开发中，推荐使用这种单例设计模式

### 2-4 懒汉式（双重加锁机制，安全）

```java
public class DCLSingleton {
    private volatile static DCLSingleton instance;
    private DCLSingleton() { }
    public static DCLSingleton getInstance(){
        //先判断是否存在，不存在再加锁处理
        if (instance == null){
            //在同一个时刻加了锁的那部分程序只有一个线程可以进入
            synchronized (DCLSingleton.class){
                if (instance == null){
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}
```

使用了`volatile`关键字，禁止了指令重排。

### 2-5 懒汉式（静态内部类）

```java
public class LazySingletonStaticSafe {

    private LazySingletonStaticSafe(){
    }
    public static class  LazySingletonStaticSafeHandler{
        public static LazySingletonStaticSafe lazySingletonStaticSafe = new LazySingletonStaticSafe();
    }

    public LazySingletonStaticSafe getInstance(){
        return LazySingletonStaticSafeHandler.lazySingletonStaticSafe;
    }
}




```

这种方式跟饿汉式方式采用的机制类似，但又有不同。两者都是采用了类装载的机制来保证初始化实例时只有一个线程。不同的地方在饿汉式方式是只要Singleton类被装载就会实例化，没有Lazy-Loading的作用，而静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，才会装载SingletonInstance类，从而完成Singleton的实例化。

> ==优点：==线程安全，利用静态内部类特点实现延迟加载， 效率高。

### 2-6 单例枚举

```java
public enum LazyEnumSingleton {
    INSTANCE;
    public LazyEnumSingleton getInstance(){
        return LazyEnumSingleton.INSTANCE;
    }
}
```

> 不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象。
> 是单例实现中唯一一种不会被破坏的单例实现模式
>
> 枚举类不可以反序列化



## 3 单例应用场景



### JDK源码Runtime类

从上面源代码中可以看出Runtime类使用的是恶汉式（静态属性）方式来实现单例模式的。

```java

public class Runtime {
    private static Runtime currentRuntime = new Runtime();
    public static Runtime getRuntime() {
        return currentRuntime;
    }
    private Runtime() {}
}
```





## 4 总结：

**优点：**

>在内存中只有一个对象，节省内存空间；
>
>避免频繁的创建销毁对象，可以提高新能；
>
>避免对共享资源的多重占用，简化访问；

**缺点：**

>如果实例化的对象长时间不被利用，系统会认为该对象是垃圾而被回收，这可能会导致对象状态的丢失；
>
>滥用单例将带来一些负面问题，如为了节省资源将数据库连接池对象设计为的单例类，可能会导致共享连接池对象的程序过多而出现连接池溢出；
>
>不适用于变化频繁的对象；



