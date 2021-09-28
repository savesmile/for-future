package com.f_lin.future.spring.cloud.scheduler;

/**
 * @author F_lin fengjunlin@23mofang.com
 * @since 2018/8/9
 **/

public class JobPackage {

    private String jobName;
    private String jobGroupName;
    private String triggerName;
    private String triggerGroupName;
    private Class jobClazz;
    private String cron;

    public JobPackage(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClazz, String cron) {
        this.jobName = jobName;
        this.jobGroupName = jobGroupName;
        this.triggerName = triggerName;
        this.triggerGroupName = triggerGroupName;
        this.jobClazz = jobClazz;
        this.cron = cron;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroupName() {
        return triggerGroupName;
    }

    public void setTriggerGroupName(String triggerGroupName) {
        this.triggerGroupName = triggerGroupName;
    }

    public Class getJobClazz() {
        return jobClazz;
    }

    public void setJobClazz(Class jobClazz) {
        this.jobClazz = jobClazz;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}

