package ru.netology.concurrencypractice.flows;

public class CounterWorker extends Thread {
    private final int iterations;
    private final long delayMs;

    public CounterWorker(String name, int iterations, long delayMs) {
        super(name);
        this.iterations = iterations;
        this.delayMs = delayMs;
    }

    @Override
    public void run() {
        final String name = Thread.currentThread().getName();
        for (int i = 1; i <= iterations; i++) {
            System.out.printf("%s -> %d%n", name, i);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                interrupt();
                break;
            }
        }
    }
}
