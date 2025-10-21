package ru.netology.concurrencypractice.states;

public class LockerTask implements Runnable {
    private final Object lockMonitor = new Object();
    private final long holdMs;

    public LockerTask(long holdMs) {
        this.holdMs = holdMs;
    }

    @Override
    public void run() {
        synchronized (lockMonitor) {
            try {
                Thread.sleep(holdMs);
            } catch (InterruptedException ignored) {

            }
        }
    }
}
