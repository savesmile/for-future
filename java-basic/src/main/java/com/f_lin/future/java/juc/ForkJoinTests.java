package com.f_lin.future.java.juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author F_lin
 * @since 2019/4/13
 **/
public class ForkJoinTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForkJoinTests.class);

    /**
     * 集中存储task的状态
     */
    private static Map<String, AtomicBoolean> TASKS_STATUS = new ConcurrentHashMap<>();


    /**
     * test
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        List<String> sources = new ArrayList<>(Arrays.asList("s1", "s2", "s3", "s4"));
        String[] strings = sources.toArray(new String[sources.size()]);
        //不带返回值
        ForkJoinTests.submitTask("123456", "within-return-test", running ->
                {
                    ForkJoinTests.taskSplit(
                            strings,
                            running,
                            source -> {
                                LOGGER.info("=================== within-result:【{}】 ================", source);
                            }
                    );
                    countDownLatch.countDown();
                }
        );

        //带返回值
        ForkJoinTests.submitTask("234567", "with-return-test", running -> {
            List<TestEntity> result = ForkJoinTests.taskSplit(
                    strings,
                    running,
                    TestEntity::new

            );

            for (TestEntity entity : result) {
                LOGGER.info("=================== with-result:【{}】 =======================", entity.getResult());
            }
            countDownLatch.countDown();
        });

        //防止主线程直接退出
        countDownLatch.await();
    }

    public static class TestEntity {
        private String result;

        String getResult() {
            return result;
        }

        TestEntity(String result) {
            this.result = result + "ss";
        }
    }


    public static void submitTask(String taskId, String taskName, Consumer<AtomicBoolean> consumer) {
        //TODO 重复task校验 需根据依赖的持久化方式实现

        final AtomicBoolean running = new AtomicBoolean(true);
        TASKS_STATUS.put(taskId, running);
        LOGGER.info("提交任务「{}」", taskName);
        CompletableFuture.runAsync(() -> {
            LOGGER.info("任务{}「{}」开始", taskName, taskId);
            Date start = new Date();
            try {
                consumer.accept(running);
            } finally {
                TASKS_STATUS.remove(taskId);
            }
            Date end = new Date();
            long elapseSecond = TimeUnit.MILLISECONDS.toSeconds(end.getTime() - start.getTime());
            LOGGER.info("任务{}「{}」，总耗时{}s", taskName, taskId, elapseSecond);
        });


    }

    /**
     * 取消task
     *
     * @param taskId taskId
     */
    public static void cancalTask(String taskId) throws IllegalArgumentException {
        AtomicBoolean taskRunning = TASKS_STATUS.get(taskId);
        if (taskRunning != null) {
            taskRunning.set(false);
        } else {
            throw new IllegalArgumentException("相关task未运行或不存在相关的task");
        }

        //TODO task 相关数据记录。

    }


    public static <T> void taskSplit(T[] targets, AtomicBoolean running, Consumer<T> consumer) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(ForkJoinPool.getCommonPoolParallelism() * 4);
        for (int fromIdx = 0, toIdx, length = targets.length; running.get() && fromIdx < length; fromIdx = toIdx) {
            toIdx = fromIdx + 500;
            T[] subArr = Arrays.copyOfRange(targets, fromIdx, toIdx > length ? length : toIdx);
            forkJoinPool.invoke(new BisectRecursiveAction<>(subArr, consumer));
        }
    }

    public static <T, R> List<R> taskSplit(T[] targets, AtomicBoolean running, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        ForkJoinPool forkJoinPool = new ForkJoinPool(ForkJoinPool.getCommonPoolParallelism() * 4);
        for (int fromIdx = 0, toIdx, length = targets.length; running.get() && fromIdx < length; fromIdx = toIdx) {
            toIdx = fromIdx + 500;
            T[] subArr = Arrays.copyOfRange(targets, fromIdx, toIdx > length ? length : toIdx);
            List<R> rList = forkJoinPool.invoke(new BisectRecursiveTask<>(subArr, function));
            result.addAll(rList);
        }
        return result;
    }
}

/**
 * 二分任务并行通用模型
 */

class BisectRecursiveAction<T> extends RecursiveAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(BisectRecursiveAction.class);
    private T[] arr;
    private int threshold = 10;
    private Consumer<T> action;

    public BisectRecursiveAction(T[] arr, int threshold, Consumer<T> action) {
        this.arr = arr;
        this.threshold = threshold;
        this.action = action;
    }

    public BisectRecursiveAction(T[] arr, Consumer<T> action) {
        this.arr = arr;
        this.action = action;
    }

    @Override
    protected void compute() {
        if (arr.length <= threshold) {
            for (T arg : arr) {
                try {
                    action.accept(arg);
                } catch (RuntimeException re) {
                    LOGGER.error("Action Compute Ex: {}", re.getMessage(), re);
                }
            }
            return;
        }
        BisectRecursiveAction<T> left = new BisectRecursiveAction<>(Arrays.copyOfRange(arr, 0, arr.length >> 1), threshold, action);
        BisectRecursiveAction<T> right = new BisectRecursiveAction<>(Arrays.copyOfRange(arr, arr.length >> 1, arr.length), threshold, action);
        left.fork();
        right.fork();
        left.join();
        right.join();
    }
}


/**
 * 带返回值的二分任务并行通用模型
 **/
class BisectRecursiveTask<T, R> extends RecursiveTask<List<R>> {
    private static Logger LOGGER = LoggerFactory.getLogger(BisectRecursiveTask.class);
    private T[] arr;
    private int threshold = 10;
    private Function<T, R> action;

    public BisectRecursiveTask(T[] arr, int threshold, Function<T, R> action) {
        this.arr = arr;
        this.threshold = threshold;
        this.action = action;
    }

    public BisectRecursiveTask(T[] arr, Function<T, R> action) {
        this.arr = arr;
        this.action = action;
    }

    @Override
    protected List<R> compute() {
        List<R> results = new ArrayList<>();
        R result = null;
        if (arr.length <= threshold) {
            for (T arg : arr) {
                try {
                    result = action.apply(arg);
                } catch (RuntimeException re) {
                    LOGGER.error("获取子任务出现异常 {}", re.getMessage(), re);
                }
                results.add(result);
            }

            return results;
        }
        BisectRecursiveTask<T, R> left = new BisectRecursiveTask<>(Arrays.copyOfRange(arr, 0, arr.length >> 1), threshold, action);
        BisectRecursiveTask<T, R> right = new BisectRecursiveTask<>(Arrays.copyOfRange(arr, arr.length >> 1, arr.length), threshold, action);
        left.fork();
        right.fork();
        //结果汇总
        List<R> leftJoin = left.join();
        List<R> rightJoin = right.join();
        results.addAll(leftJoin);
        results.addAll(rightJoin);
        return results;
    }

}
