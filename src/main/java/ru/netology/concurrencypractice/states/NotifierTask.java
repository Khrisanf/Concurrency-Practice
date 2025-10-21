package ru.netology.concurrencypractice.states;

public class NotifierTask implements Runnable {
    private final Object waitMonitor = new Object();
    private final long delayMs;
    public NotifierTask(long delayMs) {
        this.delayMs = delayMs;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            synchronized (waitMonitor) {
                waitMonitor.notifyAll();
            }
        }
    }
}
