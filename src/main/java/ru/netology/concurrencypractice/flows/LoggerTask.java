package ru.netology.concurrencypractice.flows;

public class LoggerTask implements Runnable {
    private final int iterations;
    private final long delayMs;

    public LoggerTask(int iterations, long delayMs) {
        this.iterations = iterations;
        this.delayMs = delayMs;
    }

    public void run() {
        final String threadName = Thread.currentThread().getName();
        for (int i = 1; i <= iterations; i++) {
            System.out.printf("%s -> %d%n", threadName, i);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
