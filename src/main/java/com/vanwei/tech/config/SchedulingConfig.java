package com.vanwei.tech.config;

import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Quartz定时任务 config
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2020/2/24 20:46
 */
@Order(9)
@Configuration
public class SchedulingConfig implements SchedulerFactoryBeanCustomizer {


	@Override
	public void customize(SchedulerFactoryBean schedulerFactoryBean) {
		schedulerFactoryBean.setStartupDelay(2);
		schedulerFactoryBean.setAutoStartup(true);
		schedulerFactoryBean.setOverwriteExistingJobs(true);
	}
}
