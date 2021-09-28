### 三种代理模式

#### 普通代理。
只能通过代理创建真实对象。
#### 强制代理
只能通过代理执行真实对象的逻辑
#### 动态代理
运行期动态生成
1. java.proxy....实现接口。一个InvocationHandler。。一个Proxy.newProxyInstance
2. CGLIB 第三方库动态代理。MethodInterceptor


```java
//生成代理类
System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
```
