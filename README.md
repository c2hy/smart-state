# smart-state

SmartState 是一个用来运算多个状态位的工具库。它解决的问题是实际业务中时常能碰到的需要用多个开关状态来标识某一个记录的场景。接下来，我们用一个例子来解释为什么我们需要这样一个工具库。

例如，我们有一个容器 container，container 有一个开关属性 enabled。在 container 中又有一些所属物 item，而 item 又有一个锁定属性 locked。这时候，我们需要判断一个 item 是否可用，需要 enabled 为 true 且 locked 为 false，这就需要维护两个属性参与计算。如果我们再给 item 增加一个激活属性 activated，又要再维护一个属性，可以感受这其中有一些麻烦在。

假如我们做这样一个约定，我们有一个 32 位的二进制数字，第一位代表 container 的 enabled，第二位代表 item 的 locked，第三位代表 item 的 activated。如果一个 item 是可用的，那么它的大概是这样一个二进制数字 [...101]，把它转换成十进制的数字就是 5。也就是说我们只需要判断数字是否为 5 就可以知道它是否可用。

接下来我们尝试使用 SmartState 来实现这一方案。假如我们需要新建一个 item，enabled 为 true，locked 为 false，activated 为 false。我们可以使用如下代码获得其状态值：

```java
// 该方法会创建一个所有位都是 false 的 state
SmartState state = SmartStateFactory.create();
// 我们需要把 enabled 标记为 true, 它的位置是 1。注意这里允许的范围是 1 到 31，后文备注部分会进行解释
state = state.setTrue(1);
// 接下来我们就可以获得它的十进制值进行存储，number 方法将会返回 1
int number = state.number();
```

接下来我们触发 item 的激活流程，需要将 activated 标记为 true。我们可以使用如下代码来更新其状态值：

```java
SmartState state = SmartStateFactory.create(1);
// 我们需要把 activated 标记为 true，它的位置是 3
state = state.setTrue(3);
// 接下来我们就可以获得它的十进制值进行存储，number 方法将会返回 5
int number = state.number();
// 也可以使用 setFalse 将指定位置设置为 false
state = state.setFalse(4);
```

我们也可以使用批量操作的方式更新多个位置的状态，这里需要用到 `PositioningState` 类（它的使用很简单）。

```java
SmartState state = SmartStateFactory.create();
// 注意，setState 最多可传 31 个参数，of 方法的第一个参数允许的范围是 1 到 31，后文备注部分会进行解释
state = state.setState(PositioningState.of(1, true), PositioningState.of(2, true));
// number 方法将会返回 5
int number = state.number();
```

工具类还提供了判断位置状态的方法。

```java
SmartState state = SmartStateFactory.create(5);
// position3State 的值为 true。注意这里的数字允许的范围是 1 到 31，后文备注部分会进行解释
boolean position3State = state.isTrue(3);
// position1State 的值为 false
boolean position1State = state.isFalse(1);
// 也可以同时判断多个位置的状态，同样需要使用 PositioningState 类，position1And3State 的值为 true
boolean position1And3State = state.expects(PositioningState.of(1, true), PositioningState.of(3, true));
```



**备注**

- 为什么 set XXX 和 is XXX 方法的值 PositioningState.of 方法的第一个参数允许的范围都是 1 到 31，为什么 expects 和 setState 允许的参数个数最多是 31 个？

  因为预期使用一个正整数存储状态，其转成二进制最大有 32 位，而第 32 位是正负标志位，其永远为 0，所以只有 31 个位置可用。
