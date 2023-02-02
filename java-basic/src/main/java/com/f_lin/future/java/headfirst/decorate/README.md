### 装饰器模式

> 装饰类还是把动作的执行委托给需要装饰的对象  
`动态`地给一个对象添加一些`额外的职责`。就增加功能来说，装饰模式相比生成子类更为灵活。

#### 组成

##### 抽象构件 装饰对象上层结构
```java
public abstract class Component {
    //抽象的方法
    public abstract void operate();

}

//实现类
public class ConcreteComponent extends Component {
    //具体实现
    @Override
    public void operate() {
        System.out.println("do Something");
    }
}
```

##### 装饰者
```java
public abstract class Decorator extends Component {
    private Component component = null;
    
    //通过构造函数传递被装饰者
    public Decorator(Component _component){
        this.component = _component;
    }
    //委托给被装饰者执行
    @Override
    public void operate() {
        this.component.operate();
    }
}

//子类各种装饰者实现。重写父类`operate()`方法即可
public class ConcreteDecorator1 extends Decorator {
    //定义被修饰者
    public ConcreteDecorator1(Component _component){
        super(_component);
    }
    //定义自己的修饰方法
    private void method1(){
        System.out.println("method1 修饰");
    }
    //重写父类的Operation方法
    public void operate(){
        this.method1();
        super.operate();
    }   
}
```
#### 装饰类其本身是具有`Component`属性的`(extends Component)`。。所以**其它装饰器依然可以装饰此类**

