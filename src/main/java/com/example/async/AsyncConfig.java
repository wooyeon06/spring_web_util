package com.example.async;

import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@Configuration
@EnableAsync
@Log4j2
public class AsyncConfig {

    @Bean(name = "threadPoolTaskExecutor", destroyMethod = "destroy")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2); //작업이 큐에 추가되면 스레드 풀에서 바로 작업을 수행할 수 있는 스레드
        executor.setMaxPoolSize(10); //최대 쓰레드 수
        executor.setQueueCapacity(500); //큐에 대기가능한 작업 수

        executor.setThreadNamePrefix("demo-async-");

        executor.setTaskDecorator(new AsyncDecorator());

        executor.initialize();

        return new HandlingExecutor(executor);
    }


    public class HandlingExecutor implements AsyncTaskExecutor {
        private AsyncTaskExecutor executor;

        public HandlingExecutor(AsyncTaskExecutor executor) {
            this.executor = executor;
        }

        @Override
        public void execute(Runnable task) {
            executor.execute(createWrappedRunnable(task));
        }

        @Override
        public void execute(Runnable task, long startTimeout) {
            executor.execute(createWrappedRunnable(task), startTimeout);
        }

        @Override
        public Future<?> submit(Runnable task) {
            // TODO Auto-generated method stub
            return executor.submit(createWrappedRunnable(task));
        }




        private Runnable createWrappedRunnable(final Runnable task) {
            return new Runnable() {

                HashMap map;

                public void run() {
                    try {
                        task.run();
                        FutureTask<HashMap> future = (FutureTask<HashMap>) task;

                        if (future.isDone()) {
                            map = future.get();
                        }

                        log.debug("async end... map : " + map);

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        MDC.clear();
                    }
                }
            };
        }// end of createWrappedRunnable

        public void destroy() {
            if (executor instanceof ThreadPoolTaskExecutor) {
                ((ThreadPoolTaskExecutor) executor).shutdown();
            }
        }
    }

}