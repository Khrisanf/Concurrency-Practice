package ru.netology.concurrencypractice;

import org.springframework.stereotype.Service;
import ru.netology.concurrencypractice.Threads.CounterWorker;
import ru.netology.concurrencypractice.Threads.LoggerTask;

import java.util.Comparator;
import java.util.List;

@Service
public class SimpleService {

    private volatile Thread counter;
    private volatile Thread logger;

    public void startDemo(int iterations) {
        counter = new CounterWorker("CounterWorker", iterations, 150);
        logger  = new Thread(new LoggerTask(iterations, 200), "LoggerThread");

        counter.start();
        logger.start();

        printActiveThreads("После старта");
    }

    public void waitForCompletion() throws InterruptedException {
        if (counter != null) counter.join();
        if (logger  != null) logger.join();
        printActiveThreads("После join()");
    }

    public List<String> activeThreads() {
        return Thread.getAllStackTraces().keySet().stream()
                .sorted(Comparator.comparingLong(Thread::getId))
                .map(t -> String.format("[%d] name=%s state=%s daemon=%s",
                        t.getId(), t.getName(), t.getState(), t.isDaemon()))
                .toList();
    }

    private static void printActiveThreads(String title) {
        System.out.println("\n== " + title + " ==");
        Thread.getAllStackTraces().keySet().stream()
                .sorted(Comparator.comparingLong(Thread::getId))
                .forEach(t -> System.out.printf("[%2d] name=%s state=%s daemon=%s%n",
                        t.getId(), t.getName(), t.getState(), t.isDaemon()));
    }
}