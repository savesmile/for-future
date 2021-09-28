package com.f_lin.future.solution.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author F_Lin fjl@jinzantech.com
 * @since 2019/6/18 17:57
 */
public class BreadthFirst {

    private static final Logger LOGGER = LoggerFactory.getLogger(BreadthFirst.class);

    public static void main(String[] args) throws InterruptedException {


        findTarget();

    }

    //简单图寻路径。
    private static boolean findTarget() throws InterruptedException {
        Map<String, List<String>> figure = initFigure();
        BlockingQueue<String> workQueue = new ArrayBlockingQueue<String>(100);
        workQueue.addAll(figure.get("f"));
        Set<String> checkedMans = new HashSet<String>();
        //
        while (!workQueue.isEmpty()) {
            String currentMan = workQueue.take();
            if ("target".equals(currentMan)) {
                LOGGER.info("找到target了，路由{}", 1);
                return true;
            }
            if (checkedMans.add(currentMan)) {
                for (String relation : figure.get(currentMan)) {
                    if (checkedMans.add(relation)) {
                        workQueue.add(relation);
                    }
                }
            }

        }
        return false;
    }

    private static Map<String, List<String>> initFigure() {
        Map<String, List<String>> figure = new HashMap<String, List<String>>();
        List<String> fFriends = Arrays.asList("tom", "jim", "ergou");
        List<String> tomFriends = Arrays.asList("tis", "ros", "xixi");
        List<String> jimFriends = Arrays.asList("lala", "bobo", "target");
        List<String> gouFriends = Arrays.asList("haha", "chuchu", "bobo");
        List<String> tisFriends = Collections.emptyList();
        List<String> rosFriends = Collections.emptyList();
        List<String> xixiFriends = Collections.emptyList();
        List<String> lalFriends = Collections.emptyList();
        List<String> hahaFriends = Collections.emptyList();
        List<String> chuchuFriends = Collections.emptyList();
        List<String> boboFriends = Collections.emptyList();
        List<String> targetFriends = Collections.emptyList();
        figure.put("f", fFriends);
        figure.put("tom", tomFriends);
        figure.put("jim", jimFriends);
        figure.put("ergou", gouFriends);
        figure.put("tis", tisFriends);
        figure.put("ros", rosFriends);
        figure.put("xixi", xixiFriends);
        figure.put("lala", lalFriends);
        figure.put("haha", hahaFriends);
        figure.put("chuchu", chuchuFriends);
        figure.put("bobo", boboFriends);
        figure.put("target", targetFriends);
        return figure;
    }
}
