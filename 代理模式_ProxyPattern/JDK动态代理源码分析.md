



## JDK动态代理

JDK Proxy 是 JDK 提供的一个动态代理机制，它涉及到两个核心类，分别是`Proxy`和`InvocationHandler`，我们先来了解如何使用它们。

以小红代理卖香水的故事为例，小明要购买红酒，建立`卖红酒厂商(SellWineFactory)`，统一的`酒售卖接口(SellWine)`。重点是**小红代理**，这里的代理对象不再是小红一个人，而是一个`代理工厂(SellProxyFactory)`，里面会有许多的代理对象。



```java
public class SellProxyFactory implements InvocationHandler {
    /** 代理的真实对象 */
    private Object realObject;

    public SellProxyFactory(Object realObject) {
        this.realObject = realObject;
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 真正执行的方法
     * @param args 执行方法的入参
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doSomethingBefore();
        Object obj = method.invoke(realObject, args);
        doSomethingAfter();
        return obj;
    }

    private void doSomethingAfter() {
        System.out.println("执行代理后的额外操作...");
    }

    private void doSomethingBefore() {
        System.out.println("执行代理前的额外操作...");
    }

}


public interface SellWine {
    void sellRedWine();
}
public class SellWineFactory implements SellWine{
    @Override
    public void sellRedWine() {
        System.out.println("成功购买红酒，价格是：5000 元");
    }
}

public class XiaoMing {
    public static void main(String[] args) {

        //卖香水的厂商
        ChanelFactory chanelFactory = new ChanelFactory();
        //卖红酒的厂商
        SellWineFactory sellWineFactory = new SellWineFactory();

        SellWine sellWine = (SellWine) Proxy.newProxyInstance(sellWineFactory.getClass().getClassLoader(), sellWineFactory.getClass().getInterfaces(), new SellProxyFactory(sellWineFactory));
        sellWine.sellRedWine();

        /**
         * ClassLoader loader：加载动态代理类的类加载器，被代理类的类加载器
         * Class<?>[] interfaces：代理类实现的接口，可以传入多个接口，被代理类的实现的接口
         * InvocationHandler h：指定代理类的调用处理程序，即调用接口中的方法时，会找到该代理工厂h，执行invoke()方法
         * Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h);
         */
        SellPerfume sellPerfume = (SellPerfume) Proxy.newProxyInstance(chanelFactory.getClass().getClassLoader(),
                chanelFactory.getClass().getInterfaces(),
                new SellProxyFactory(chanelFactory));
        sellPerfume.sellPerfume(1999.99);
    }
}
```

![image-20220105100712807](/Users/wangshanpeng/Library/Application Support/typora-user-images/image-20220105100712807.png)



平常我们写了一个java文件，通过javac命令生成字节码文件存放在硬盘，然后通过类加载子系统找到对应的类加载器，加载到jvm内存中。

动态代理是，动态的生成接口的代理类，然后存放在内存中，通过对应的类加载器，加载jvm内存中。

## 源码分析

### 大概流程

1. 为接口创建代理类的字节码文件
2. 使用ClassLoader将字节码文件加载到JVM
3. 创建代理类实例对象，执行对象的目标方法

### jdk动态代理涉及到的主要类

- java.lang.reflect.**Proxy**
- java.lang.reflect.**InvocationHandler**
- java.lang.reflect.**WeakCache**
- sun.misc.**ProxyGenerator**

```java
// 绑定该类实现的所有接口，取得代理类
Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h);


		@CallerSensitive
    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
        throws IllegalArgumentException
    {
      	//代理类判空校验
        Objects.requireNonNull(h);
        final Class<?>[] intfs = interfaces.clone();
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            checkProxyAccess(Reflection.getCallerClass(), loader, intfs);
        }

        /*
         * 生成接口的代理类的字节码文件
         */
        Class<?> cl = getProxyClass0(loader, intfs);

        /*
         * 使用自定义的InvocationHandler作为参数，调用构造函数获取代理类对象实例
         */
        try {
            if (sm != null) {
                checkNewProxyPermission(Reflection.getCallerClass(), cl);
            }
					// 获取代理类的构造函数，入参为InvocationHandler
            final Constructor<?> cons = cl.getConstructor(constructorParams);
            final InvocationHandler ih = h;
            if (!Modifier.isPublic(cl.getModifiers())) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        cons.setAccessible(true);
                        return null;
                    }
                });
            }
          	// 通过构造函数实例化代理类
            return cons.newInstance(new Object[]{h});
        } catch (IllegalAccessException|InstantiationException e) {
            throw new InternalError(e.toString(), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new InternalError(t.toString(), t);
            }
        } catch (NoSuchMethodException e) {
            throw new InternalError(e.toString(), e);
        }
    }
```

`newProxyInstance`方法调用`getProxyClass0(loader, intfs)`方法生成代理类的字节码文件。

```java
private static Class<?> getProxyClass0(ClassLoader loader,
                                           Class<?>... interfaces) {
        // 限定代理的接口不能超过65535个
        if (interfaces.length > 65535) {
            throw new IllegalArgumentException("interface limit exceeded");
        }

        // 如果缓存中已经存在相应接口的代理类，直接返回；否则，使用ProxyClassFactory创建代理类
        return proxyClassCache.get(loader, interfaces);
    }

```

java.lang.reflect.**WeakCache**

其中==proxyClassCache = new WeakCache<>(new KeyFactory(), new ProxyClassFactory());==
缓存使用的是WeakCache实现的，此处主要关注使用ProxyClassFactory创建代理的情况。ProxyClassFactory是Proxy类的静态内部类，实现了BiFunction接口，实现了BiFunction接口中的apply方法。

当WeakCache中没有缓存相应接口的代理类，则会调用ProxyClassFactory类的apply方法来创建代理类。
proxyClassCache.get(loader, interfaces);方法就不跟了

**ProxyClassFactory.java**

```java
private static final class ProxyClassFactory
        implements BiFunction<ClassLoader, Class<?>[], Class<?>>
    {
        // 代理类前缀
        private static final String proxyClassNamePrefix = "$Proxy";

        // 生成代理类名称的计数器
        private static final AtomicLong nextUniqueNumber = new AtomicLong();

        @Override
        public Class<?> apply(ClassLoader loader, Class<?>[] interfaces) {

            Map<Class<?>, Boolean> interfaceSet = new IdentityHashMap<>(interfaces.length);
            for (Class<?> intf : interfaces) {
                /*
                 * 校验类加载器是否能通过接口名称加载该类
                 * 这个加载器是被代理目标类的加载器
                 */
                Class<?> interfaceClass = null;
                try {
                    interfaceClass = Class.forName(intf.getName(), false, loader);
                } catch (ClassNotFoundException e) {
                }
                if (interfaceClass != intf) {
                    throw new IllegalArgumentException(
                        intf + " is not visible from class loader");
                }
                /*
                 * 校验该类是否是接口
                 */
                if (!interfaceClass.isInterface()) {
                    throw new IllegalArgumentException(
                        interfaceClass.getName() + " is not an interface");
                }
                /*
                 * 校验接口是否重复
                 */
                if (interfaceSet.put(interfaceClass, Boolean.TRUE) != null) {
                    throw new IllegalArgumentException(
                        "repeated interface: " + interfaceClass.getName());
                }
            }

            String proxyPkg = null;     // 代理类包名
            int accessFlags = Modifier.PUBLIC | Modifier.FINAL;

            /*
             * 非public接口，代理类的包名与接口的包名相同
             */
            for (Class<?> intf : interfaces) {
                int flags = intf.getModifiers();
                if (!Modifier.isPublic(flags)) {
                    accessFlags = Modifier.FINAL;
                    String name = intf.getName();
                    int n = name.lastIndexOf('.');
                    String pkg = ((n == -1) ? "" : name.substring(0, n + 1));
                    if (proxyPkg == null) {
                        proxyPkg = pkg;
                    } else if (!pkg.equals(proxyPkg)) {
                        throw new IllegalArgumentException(
                            "non-public interfaces from different packages");
                    }
                }
            }

            if (proxyPkg == null) {
                // public代理接口，使用com.sun.proxy包名
                proxyPkg = ReflectUtil.PROXY_PACKAGE + ".";
            }

            /*
             * 为代理类生成名字
             * com.sun.proxy.$Proxy0
             */
            long num = nextUniqueNumber.getAndIncrement();
            String proxyName = proxyPkg + proxyClassNamePrefix + num;

            /*
             * 真正生成代理类的字节码文件的地方
             */
            byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, interfaces, accessFlags);
            try {
            	// 使用类加载器将代理类的字节码文件加载到JVM中，native方法
                return defineClass0(loader, proxyName,
                                    proxyClassFile, 0, proxyClassFile.length);
            } catch (ClassFormatError e) {
                /*
                 * A ClassFormatError here means that (barring bugs in the
                 * proxy class generation code) there was some other
                 * invalid aspect of the arguments supplied to the proxy
                 * class creation (such as virtual machine limitations
                 * exceeded).
                 */
                throw new IllegalArgumentException(e.toString());
            }
        }
    }

```

sun.misc.**ProxyGenerator**

在**ProxyClassFactory**类的`apply`方法中可看出真正生成代理类字节码的地方是**ProxyGenerator**类中的`generateProxyClass`

```java
public static byte[] generateProxyClass(final String var0, Class<?>[] var1, int var2) {
        ProxyGenerator var3 = new ProxyGenerator(var0, var1, var2);
        // 生成代理类的字节码
        final byte[] var4 = var3.generateClassFile();
        // 是否要将生成代理类的字节码文件保存到磁盘中，该变量可以通过系统属性设置
        if (saveGeneratedFiles) {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                public Void run() {
                    try {
                        int var1 = var0.lastIndexOf(46);
                        Path var2;
                        if (var1 > 0) {
                            Path var3 = Paths.get(var0.substring(0, var1).replace('.', File.separatorChar));
                            Files.createDirectories(var3);
                            var2 = var3.resolve(var0.substring(var1 + 1, var0.length()) + ".class");
                        } else {
                            var2 = Paths.get(var0 + ".class");
                        }
						// 将文件写入磁盘
                        Files.write(var2, var4, new OpenOption[0]);
                        return null;
                    } catch (IOException var4x) {
                        throw new InternalError("I/O exception saving generated file: " + var4x);
                    }
                }
            });
        }

        return var4;
    }

```



生成的代理类

```java
public final class $Proxy0 extends Proxy implements SellWine {
    private static Method m1;
    private static Method m3;
    private static Method m2;
    private static Method m0;

    public $Proxy0(InvocationHandler var1) throws  {
        super(var1);
    }

    public final boolean equals(Object var1) throws  {
        try {
            return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void sellRedWine() throws  {
        try {
          //执行的就是实现InvocationHandler的代理类中的invoke方法
            super.h.invoke(this, m3, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final String toString() throws  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int hashCode() throws  {
        try {
            return (Integer)super.h.invoke(this, m0, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m3 = Class.forName("com.pattern.dynamicproxy.jdk.SellWine").getMethod("sellRedWine");
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}
```



### 为什么jdk动态代理必须基于接口

原因如下： 
1、生成的代理类继承了Proxy，由于java是单继承，所以只能实现接口，通过接口实现 

2、从代理模式的设计来说，充分利用了java的多态特性，也符合基于接口编码的规范 

参考：https://segmentfault.com/a/1190000021821314

### 代理类为什么要重写equals、toString与hashCode方法

根据java面向对象知道，所有的类都有最终继承Object，而Object中默认实现了equals、toString与hashCode方法。
因为代理类继承了Proxy，所以如果不重写equals、toString与hashCode方法
那么当代理类执行equals、toString与hashCode方法时，实现的就是Proxy中的方法，也就是Object中的方法

但是如果目标类重写了equals、toString与hashCode方法，这时就会出现问题了。
所以代理必须重写equals、toString与hashCode方法，通过反射执行目标类的equals、toString与hashCode方法





参考：https://blog.csdn.net/yu_kang/article/details/88423600
