package ru.netology.concurrencypractice.states;

public class BlockedTask implements Runnable {
    private final Object lockMonitor = new Object();
    @Override
    public void run() {
        synchronized (lockMonitor) {

        }
    }
}
