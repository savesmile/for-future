### 中介者模式

>在多个对象依赖的情况下，通过加入中介者角色，取消了多个对象的关联或依赖关系，
 减少了对象的耦合性。
 
 #### 一个抽象的中介者
 
```java
public abstract class AbstractMediator {
    protected AssociationModule1 module1;
    protected AssociationModule2 module2;
    protected AssociationModule3 module3;
    //构造函数
    public AbstractMediator(){
        AssociationModule1 = new AssociationModule1(this);
        AssociationModule2 = new AssociationModule2(this);
        AssociationModule3 = new AssociationModule3(this);
    }
    //中介者最重要的方法叫做事件方法，处理多个对象之间的关系
    public abstract void execute(String str,Object...objects);

    }
```

#### 不同中介者负责不同关系对象之间的关联请求处理



中介者模式适用于多个对象之间紧密耦合的情况，紧密耦合的标准是：在类图中出现了蜘蛛网状结构。在
这种情况下一定要考虑使用中介者模式，这有利于把蜘蛛网梳理为星型结构，使原本复杂混
乱的关系变得清晰简单。


一个中介者抽象类一般只有一个实现
者，除非中介者逻辑非常复杂，代码量非常大，这时才会出现多个中介者的情况






