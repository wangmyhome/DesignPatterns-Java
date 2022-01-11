# 模板方法模式

在面向对象程序设计过程中，程序员常常会遇到这种情况：设计一个系统时知道了算法所需的关键步骤，而且确定了这些步骤的执行顺序，但某些步骤的具体实现还未知，或者说某些步骤的实现与具体的环境相关。

所以定义一个操作中的算法骨架，而将算法的一些步骤延迟到子类中，使得子类可以不改变该算法结构的情况下重定义该算法的某些特定步骤。



## UML图

![image-20220111140723488](/Users/wangshanpeng/Library/Application Support/typora-user-images/image-20220111140723488.png)

**角色：**

- 抽象类（Abstract Class）：负责给出一个算法的轮廓和骨架。它由一个模板方法和若干个基本方法构成。
- 具体子类（Concrete Class）：实现抽象类中所定义的抽象方法和钩子方法，它们是一个顶级逻辑的组成步骤。



案例：炒菜

炒菜的步骤是固定的，分为`倒油`、`热油`、`倒蔬菜`、`倒调料品`、`翻炒`等步骤。现通过模板方法模式来用代码模拟。类图如下：

![image-20220111164017600](/Users/wangshanpeng/Library/Application Support/typora-user-images/image-20220111164017600.png)



代码：https://github.com/wangmyhome/DesignPatterns-Java

## 使用场景

- `InputStream`类就使用了模板方法模式。在`InputStream`类中定义了多个 `read() `方法

```java
public abstract class InputStream implements Closeable {
  //抽象方法，子类必须重写
  public abstract int read() throws IOException;
  
  public int read(byte b[]) throws IOException {
        return read(b, 0, b.length);
  }
  
  public int read(byte b[], int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }
 				//调用了无参的read方法，该方法是每次读取一个字节数据
        int c = read();
        if (c == -1) {
            return -1;
        }
        b[off] = (byte)c;

        int i = 1;
        try {
            for (; i < len ; i++) {
                c = read();
                if (c == -1) {
                    break;
                }
                b[off + i] = (byte)c;
            }
        } catch (IOException ee) {
        }
        return i;
    }
}
```

从上面代码可以看到，无参的 read() 方法是抽象方法，要求子类必须实现。而 read(byte b[])方法调用了 read(byte b[], int off, int len) 方法，所以在此处重点看的方法是带三个参数的方法。

 在InputStream父类中已经定义好了读取一个字节数组数据的方法是每次读取一个字节，并将其存储到数组的第一个索引位置，读取len个字节数据。具体如何读取一个字节数据呢？由子类实现。







