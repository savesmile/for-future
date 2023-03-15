### condition的两个重要方法

#### await

流程：

#### signal

流程：

```java
public class AQSCondition {

    public final void await() throws InterruptedException {
        // 响应中断
        if (Thread.interrupted())
            throw new InterruptedException();
        // 添加到条件队列尾部（等待队列）
        // 内部会创建 Node.CONDITION 类型的 Node
        Node node = addConditionWaiter();
        // 释放当前线程获取的锁（通过操作 state 的值）
        // 释放了锁就会被阻塞挂起
        int savedState = fullyRelease(node);
        int interruptMode = 0;
        // 节点已经不在同步队列中，则调用 park 让其在等待队列中挂着
        while (!isOnSyncQueue(node)) {
            // 调用 park 阻塞挂起当前线程
            LockSupport.park(this);
            // 说明 signal 被调用了或者线程被中断，校验下唤醒原因
            // 如果因为终端被唤醒，则跳出循环
            if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                break;
        }
        // while 循环结束， 线程开始抢锁
        if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
            interruptMode = REINTERRUPT;
        if (node.nextWaiter != null) // clean up if cancelled
            unlinkCancelledWaiters();
        // 统一处理中断的
        if (interruptMode != 0)
            reportInterruptAfterWait(interruptMode);
    }

    public final void signal() {
        // 是否为当前持有线程
        if (!isHeldExclusively())
            throw new IllegalMonitorStateException();
        Node first = firstWaiter;
        if (first != null)
            doSignal(first);
    }

    private Node addConditionWaiter() {
        Node t = lastWaiter;
        // If lastWaiter is cancelled, clean out.
        // 判断尾节点状态，如果被取消，则清除所有被取消的节点
        if (t != null && t.waitStatus != Node.CONDITION) {
            unlinkCancelledWaiters();
            t = lastWaiter;
        }
        // 创建新节点，类型为 Node.CONDITION
        Node node = new Node(Thread.currentThread(), Node.CONDITION);
        // 将新节点放到等待队列尾部
        if (t == null)
            firstWaiter = node;
        else
            t.nextWaiter = node;
        lastWaiter = node;
        return node;
    }

    final int fullyRelease(Node node) {
        boolean failed = true;
        try {
            // 获取当前节点的 state
            int savedState = getState();
            // 释放锁
            if (release(savedState)) {
                failed = false;
                return savedState;
            } else {
                throw new IllegalMonitorStateException();
            }
        } finally {
            if (failed)
                node.waitStatus = Node.CANCELLED;
        }
    }

    public final boolean release(int arg) {
        if (tryRelease(arg)) {
            Node h = head;
            if (h != null && h.waitStatus != 0)
                unparkSuccessor(h);
            return true;
        }
        return false;
    }

    final boolean isOnSyncQueue(Node node) {
        // 当前节点是条件队列节点，或者上一个节点是空
        if (node.waitStatus == Node.CONDITION || node.prev == null)
            return false;
        if (node.next != null) // If has successor, it must be on queue
            return true;

        return findNodeFromTail(node);
    }

    // 从尾部开始遍历
    private boolean findNodeFromTail(Node node) {
        Node t = tail;
        for (; ; ) {
            if (t == node)
                return true;
            if (t == null)
                return false;
            t = t.prev;
        }
    }

    final boolean acquireQueued(final Node node, int arg) {
        // 是否拿到资源
        boolean failed = true;
        try {
            // 中断状态
            boolean interrupted = false;
            // 无限循环
            for (; ; ) {
                // 当前节点之前的节点
                final Node p = node.predecessor();
                // 前一个节点是头节点， 说明当前节点是 头节点的 next 即真实的第一个数据节点 （因为 head 是虚拟节点）
                // 然后再尝试获取资源
                if (p == head && tryAcquire(arg)) {
                    // 获取成功之后 将头指针指向当前节点
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return interrupted;
                }
                // p 不是头节点， 或者 头节点未能获取到资源 （非公平情况下被别的节点抢占）
                // 判断 node 是否要被阻塞，获取不到锁就会一直阻塞
                if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt())
                    interrupted = true;
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }
    }

    private void doSignal(Node first) {
        do {
            // firstWaiter 头节点指向条件队列头的下一个节点
            if ((firstWaiter = first.nextWaiter) == null)
                lastWaiter = null;
            // 将原来的头节点和同步队列断开
            first.nextWaiter = null;
        } while (!transferForSignal(first) &&
                (first = firstWaiter) != null);
    }

    final boolean transferForSignal(Node node) {

        // 判断节点是否已经在之前被取消了
        if (!compareAndSetWaitStatus(node, Node.CONDITION, 0))
            return false;

        // 调用 enq 添加到 同步队列的尾部
        Node p = enq(node);
        int ws = p.waitStatus;
        // node 的上一个节点 修改为 SIGNAL 这样后续就可以唤醒自己了
        if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL))
            LockSupport.unpark(node.thread);
        return true;
    }

    private Node enq(final Node node) {
        for (; ; ) {
            Node t = tail;
            // 尾节点为空 需要初始化头节点，此时头尾节点是一个
            if (t == null) { // Must initialize
                if (compareAndSetHead(new Node()))
                    tail = head;
            } else {
                // 不为空 循环赋值
                node.prev = t;
                if (compareAndSetTail(t, node)) {
                    t.next = node;
                    return t;
                }
            }
        }
    }
}
```