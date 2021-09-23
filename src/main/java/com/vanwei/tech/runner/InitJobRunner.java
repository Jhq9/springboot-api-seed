package com.vanwei.tech.runner;

import com.vanwei.tech.job.DemoJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitJobRunner implements CommandLineRunner {

    private final Scheduler scheduler;

    @Override
    public void run(String... args) throws Exception {
        // 任务组
        String group = DemoJob.class.getName();

        // 任务-1
        String firstTaskName = "task-1";
        JobKey firstJobKey = new JobKey(firstTaskName, group);
        if (!scheduler.checkExists(firstJobKey)) {
            JobDetail jobDetail = JobBuilder
                    .newJob(DemoJob.class)
                    .withIdentity(firstTaskName, group)
                    .build();

            jobDetail.getJobDataMap().put("name", firstTaskName);

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(firstTaskName, group)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ? *"))
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
        }

        // 任务-2
        String secondTaskName = "task-2";
        JobKey secondJobKey = new JobKey(secondTaskName, group);
        if (!scheduler.checkExists(secondJobKey)) {
            JobDetail secondJobDetail = JobBuilder
                    .newJob(DemoJob.class)
                    .withIdentity(secondTaskName, group)
                    .build();
            secondJobDetail.getJobDataMap().put("name", secondTaskName);

            Trigger secondTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(secondTaskName, group)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 * * * ? *"))
                    .build();

            scheduler.scheduleJob(secondJobDetail, secondTrigger);
        }
    }
}
