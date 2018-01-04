package com.chimpim.toolbox.common;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUtils {

    private static class IoThreadPoolHolder {
        private static final ExecutorService instance =
                Executors.newCachedThreadPool(defaultThreadFactory("io"));
    }

    private static class ComputeThreadPoolHolder {
        private static final ExecutorService instance =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1,
                        defaultThreadFactory("compute"));
    }

    private static class ScheduledThreadHolder {
        private static final ScheduledExecutorService instance =
                Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() + 1,
                        defaultThreadFactory("scheduled"));
    }


    public static void runOnIoThread(@NotNull Runnable runnable) {
        IoThreadPoolHolder.instance.execute(runnable);
    }

    public static void runOnComputeThread(@NotNull Runnable runnable) {
        ComputeThreadPoolHolder.instance.execute(runnable);
    }

    public static void schedule(@NotNull Runnable runnable, long delay, @NotNull TimeUnit timeUnit) {
        ScheduledThreadHolder.instance.schedule(runnable, delay, timeUnit);
    }

    public static void scheduleWithFixedDelay(@NotNull Runnable runnable, long initialDelay, long period, @NotNull TimeUnit timeUnit) {
        ScheduledThreadHolder.instance.scheduleWithFixedDelay(runnable, initialDelay, period, timeUnit);
    }

    public static void scheduleAtFixedRate(@NotNull Runnable runnable, long initialDelay, long delay, @NotNull TimeUnit timeUnit) {
        ScheduledThreadHolder.instance.scheduleAtFixedRate(runnable, initialDelay, delay, timeUnit);
    }

    private static ThreadFactory defaultThreadFactory(String name) {
        return new DefaultThreadFactory(name);
    }

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix =
                    name + "-" + "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(@NotNull Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
