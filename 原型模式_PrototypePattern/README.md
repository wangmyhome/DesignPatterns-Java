# 四、原型模式

![image-20220109120913670](/Users/wangshanpeng/Library/Application Support/typora-user-images/image-20220109120913670.png)

常见克隆羊实例；

定义一个Sheep类时在类后面实现Cloneable接口，重写clone方法。

在复制该对象时可以使用类名.clone()方法，对类的信息复制，浅克隆（浅拷贝）

```java
//Sheep类实现Cloneable接口，重写clone方法
@Override
protected Object clone(){
    Sheep sheep = null;
    try{
        sheep = (Sheep)super.clone();
    }catch(Exception e){
        System.out.println(e.getMessage);
    }
    return sheep;
}
```

## 4.1 浅拷贝

- 对于数据类型是基本数据类型的成员变量，浅拷贝会直接进行值传递，也就是将该属性的值复制一份给新的对象。
- 对于数据类型是引用数据类型的成员变量，比如说成员变量是某个数组、某个类的对象等，那么浅拷贝会进行引用传递，也就是将该成员变量的引用值（内存地址）复制一份给新的对象。因为实际上两个对象的该成员变量都是指向同一个实例。只要一个对象的成员变量被修改就会影响到另一个对象。
- 浅拷贝是使用默认的clone()方法来实现sheep = (Sheep)super.clone();

```java
public class Sheep implements Cloneable{

    private String name;
    private String age;
    
    /**
     * 浅拷贝
     * @return
     */
    @Override
    protected Object clone() {
        Sheep sheep = null;
        try {
            sheep = (Sheep) super.clone();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return sheep;
    }   
}
```

==总结：在拷贝一个类中，成员变量是obj，只是增加了一个指针指向已存在的内存地址，obj内存地址值一样==

## 4.2 深拷贝

- 复制对象的所有基本数据类型的成员变量值

- 为所有引用数据类型的成员变量申请存储空间，并复制每个引用数据类型成员变量所引用的对象，直到该对象可达的所有对象。也就是说，对象进行拷贝要对整个对象进行拷贝。

- 深拷贝实现方式1：重写clone方法来实现深拷贝

  ```java
  public class DeepProtoType implements Serializable,Cloneable {
  
      public String name;//String 属性
      public DeepCloneable deepCloneable;//引用类型
      public DeepProtoType(){
          super();
      }
  
      /**
       * 深拷贝 方式1使用clone方法
       * @return
       * @throws CloneNotSupportedException
       */
      @Override
      protected Object clone() throws CloneNotSupportedException {
          Object deep = null;
          //这里完成基本类型和String类型的克隆
          deep = super.clone();
          //对引用类型的属性，进行单独处理
          DeepProtoType deepProtoType = (DeepProtoType) deep;
          deepProtoType.deepCloneable = (DeepCloneable) deepCloneable.clone();
          return super.clone();
      }
  }
  ```

- 深拷贝实现方式2：通过对象序列化实现深拷贝

  ```java
  /**
   * 深拷贝 方式2 通过对象的序列化实现（推荐）
   * @return
   */
  public Object deepClone(){
      //创建流对象
      ByteArrayOutputStream bos = null;
      ObjectOutputStream oos = null;
      ByteArrayInputStream bis = null;
      ObjectInputStream ois = null;
  
      try {
          //序列化
          bos = new ByteArrayOutputStream();
          oos = new ObjectOutputStream(bos);
          oos.writeObject(this);//当前对象以对象流方式输出
  
          //反序列化
          bis = new ByteArrayInputStream(bos.toByteArray());
          ois = new ObjectInputStream(bis);
          DeepProtoType copyobj = (DeepProtoType) ois.readObject();
          return copyobj;
      }catch (Exception e){
          System.out.println(e.getMessage());
          return null;
      }finally {
          try{
              ois.close();
              bis.close();
              oos.close();
              bos.close();
          }catch (Exception e){
              System.out.println(e.getMessage());
          }
      }
  }
  ```

==总结：是增加了一个指针并且申请了一个新的内存，使这个增加的指针指向这个新的内存。==

为什么使用clone必须实现cloneable？

这是java中的规定，cloneable接口中什么也没有，只当作一个标记。当实现了这个接口，执行这个类的clone方法，就相当于告诉了java，去执行object中的clone方法。

原型模式的性能不比直接new出来的对象性能高多少，就是代码简洁了一些。开发中很少用到。

部分代码检查器会有这个提示
//Remove this "clone" implementation; use a copy constructor or copy factory instead.
//删除此“克隆”实现；请改用复制构造函数或复制工厂。
http://www.javashuo.com/article/p-otvrvbcx-hu.html
