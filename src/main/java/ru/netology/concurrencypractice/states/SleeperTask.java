package ru.netology.concurrencypractice.states;

public class SleeperTask implements Runnable {
    private final long sleepMs;
    public SleeperTask(long sleepMs) {
        this.sleepMs = sleepMs;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(sleepMs);
        } catch (InterruptedException ignored) {

        }
    }
}
