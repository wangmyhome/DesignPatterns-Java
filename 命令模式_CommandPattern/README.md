# 命令模式

将一个请求封装为一个对象，使发出请求的责任和执行请求的责任分割开。这样两者之间通过命令对象进行沟通，这样方便将命令对象进行存储、传递、调用、增加与管理。

**系统需要将请求调用者和请求接收者解耦，使得调用者和接收者不直接交互。**

## UML图

![img](http://cdn.processon.com/5d15b670e4b0fbffddf8026f?e=1561707648&token=trhI0BY8QfVrIGn9nENop6JAc6l5nZuxhjQ62UfM:7UNrDato_AJ0gdI88adZ19AZAOU=)

**角色：**

- `抽象命令类`（Command）角色： 定义命令的接口，声明执行的方法。
- `具体命令`（Concrete Command）角色：具体的命令，实现命令接口；通常会持有接收者，并调用接收者的功能来完成命令要执行的操作。
- `实现者/接收者`（Receiver）角色： 接收者，真正执行命令的对象。任何类都可能成为一个接收者，只要它能够实现命令要求实现的相应功能。
- `调用者/请求者`（Invoker）角色： 要求命令对象执行请求，通常会持有命令对象，可以持有很多的命令对象。这个是客户端真正触发命令并要求命令执行相应操作的地方，也就是说相当于使用命令对象的入口。



案例：点餐

![image-20220111200618070](/Users/wangshanpeng/Library/Application Support/typora-user-images/image-20220111200618070.png)

> 调用者：服务员小二
>
> 抽象命令类：菜品
>
> 具体命令：粤菜、苏菜、鲁菜、川菜
>
> 抽象实现者：厨师
>
> 具体实现者：粤菜厨师、苏菜厨师、鲁菜厨师、川菜厨师

![image-20220111203854666](/Users/wangshanpeng/Library/Application Support/typora-user-images/image-20220111203854666.png)

经过这样的拆解就可以非常方面的扩展`菜品`、`厨师`，对于调用者来说这部分都是松耦合的，在整体的框架下可以非常容易加入实现逻辑。

代码实现：

github：https://github.com/wangmyhome/DesignPatterns-Java



**优点：**

降低系统的耦合度。命令模式能将调用操作的对象与实现该操作的对象解耦。

增加或删除命令非常方便。采用命令模式增加与删除命令不会影响其他类，它满足“开闭原则”，

对扩展比较灵活。

可以实现宏命令。命令模式可以与组合模式结合，将多个命令装配成一个组合命令，即宏命令。

方便实现 Undo 和 Redo 操作。命令模式可以与后面介绍的备忘录模式结合，实现命令的撤销

与恢复。

**缺点：**

使用命令模式可能会导致某些系统有过多的具体命令类。

系统结构更加复杂。开发中使用比较少。

## 使用场景

- 



