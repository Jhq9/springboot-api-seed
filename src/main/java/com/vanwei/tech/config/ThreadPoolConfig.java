package com.vanwei.tech.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author jin_huaquan
 * @version 1.0
 * @date 2018/4/27 9:01
 */
@Configuration
public class ThreadPoolConfig implements AsyncConfigurer {

	/**
	 * 线程池的每个线程的名字前缀
	 */
	private static final String THREAD_NAME_PREFIX = "global-executor-";

	@Override
	@Bean("applicationTaskExecutor")
	public ThreadPoolTaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		int processCount = Runtime.getRuntime().availableProcessors();
		if (processCount == 1) {
			processCount *= 5;
		}
		executor.setCorePoolSize(processCount);
		executor.setMaxPoolSize(processCount * 5);
		executor.setQueueCapacity(processCount * 2);
		executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
		executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				try {
					executor.getQueue().put(r);
				} catch (InterruptedException e) {
					throw new RejectedExecutionException("There was an unexpected InterruptedException whilst waiting to add a Runnable in the executor's blocking queue", e);
				}
			}
		});
		executor.initialize();

		return executor;
	}

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
