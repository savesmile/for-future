package com.f_lin.future.java.scheduler.tests;

import com.f_lin.future.spring.core.aop.tests.Cat;
import com.f_lin.future.java.scheduler.QuartzManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author F_lin
 * @since 2019/4/15
 **/
@Component
public class QuartzTests implements Job, InitializingBean {
    @Autowired
    QuartzManager quartzManager;

    @Autowired
    Cat cat;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("hello word!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
       /* quartzManager.addJob(new JobPackage(
                "1",
                "11",
                "22",
                "22",
                QuartzTests.class,
                "0/5 * * * * ? ")
        );

        cat.say();*/
    }
}
