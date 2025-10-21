package ru.netology.concurrencypractice.states;

public class WaiterTask implements Runnable {
    private final Object waitMonitor = new Object();
    @Override
    public void run() {
        synchronized (waitMonitor) {
            try {
                waitMonitor.wait();
            } catch (InterruptedException ignored) {

            }
        }
    }
}
