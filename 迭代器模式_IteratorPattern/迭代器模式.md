# 迭代器模式

提供一个对象来顺序访问聚合对象中的一系列数据，而不暴露聚合对象的内部表示。

## UML图

![img](http://cdn.processon.com/5d15cbb7e4b014412aa6f8ab?e=1561713095&token=trhI0BY8QfVrIGn9nENop6JAc6l5nZuxhjQ62UfM:YdIAsLGDrhrmjVQPUIpKp2LAmFg=)

- 角色
  - 抽象聚合（Aggregate）角色：定义存储、添加、删除聚合元素以及创建迭代器对象的接口。
  - 具体聚合（ConcreteAggregate）角色：实现抽象聚合类，返回一个具体迭代器的实例。
  - 抽象迭代器（Iterator）角色：定义访问和遍历聚合元素的接口，通常包含 hasNext()、next() 等方法。
  - 具体迭代器（Concretelterator）角色：实现抽象迭代器接口中所定义的方法，完成对聚合对象的遍历，记录遍历的当前位置。



案例：JDK集合源码

List：抽象聚合类

ArrayList：具体的聚合类

Iterator：抽象迭代器

list.iterator()：返回的是实现了 Iterator 接口的具体迭代器对象



**优点**

它支持以不同的方式遍历一个聚合对象，在同一个聚合对象上可以定义多种遍历方式。在迭代器模式中只需要用一个不同的迭代器来替换原有迭代器即可改变遍历算法，我们也可以自己定义迭代器的子类以支持新的遍历方式。

迭代器简化了聚合类。由于引入了迭代器，在原有的聚合对象中不需要再自行提供数据遍历等方法，这样可以简化聚合类的设计。

在迭代器模式中，由于引入了抽象层，增加新的聚合类和迭代器类都很方便，无须修改原有代码，满足 “开闭原则” 的要求。

**缺点**

增加了类的个数，这在一定程度上增加了系统的复杂性。

## 使用场景

### Iterator

用于容器和容器遍历。

**Itertor接口类**

```java
public interface Iterator<E> {

  /**
   * 检查集合中是否还有元素
   */
  boolean hasNext();

  /**
   * 返回迭代的下一个元素
   * @throws NoSuchElementException 如果没有可迭代的元素将抛出异常
   */
  E next();

  /**
   *将迭代器新返回的元素删除
   */
  default void remove() {
    throw new UnsupportedOperationException("remove");
  }
  
  /**
   * 对每个剩余元素执行给定操作，直到所有元素都被处理或操作抛出异常。
   * 如果指定了该顺序，则操作按迭代顺序执行
   * @since 1.8
   */
  default void forEachRemaining(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    while (hasNext())
      action.accept(next());
  }
}
```

**Collection集合类**

```java
public interface Collection<E> extends Iterable<E> {
		int size();
  
    boolean isEmpty();
  
  	boolean contains(Object o);
  	
    /**
     * 获取Iterator对象
     */
    Iterator<E> iterator();
}
```

**ArrayList类**

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
  
  public Iterator<E> iterator() {
        return new Itr();
   }
  //在ArrayList内部定义了一个内部类Itr，该类实现了Iterator接口
	private class Itr implements Iterator<E> {
    int cursor;       // 表示下一个元素的索引位置
    int lastRet = -1; // 表示上一个元素的索引位置
    
    // 预期被修改的次数
    int expectedModCount = modCount;

    public boolean hasNext() {
        return cursor != size;
    }

    @SuppressWarnings("unchecked")
    public E next() {
        // 检查是否在修改
        checkForComodification();
        int i = cursor;
        if (i >= size)
            throw new NoSuchElementException();
        Object[] elementData = ArrayList.this.elementData;
        if (i >= elementData.length)
            throw new ConcurrentModificationException();
        cursor = i + 1;
        return (E) elementData[lastRet = i];
    }
   final void checkForComodification() {
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
    }
    public void remove() {
        if (lastRet < 0)
            throw new IllegalStateException();
      	//在使用得迭代器遍历集合进行修改这个集合数据的时候，可能这里会报错
        checkForComodification();

        try {
            ArrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
            // 删除后将modCount重新赋值给expectedModCount
            expectedModCount = modCount;
        } catch (IndexOutOfBoundsException ex) {
            throw new ConcurrentModificationException();
        }
    }
	}  
}

```
