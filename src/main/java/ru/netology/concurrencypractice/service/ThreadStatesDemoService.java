package ru.netology.concurrencypractice.service;

import org.springframework.stereotype.Service;
import ru.netology.concurrencypractice.states.*;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ThreadStatesDemoService {
    private final String time = LocalTime.now().withNano(0).toString();

    public void runDemo() throws InterruptedException {
        Thread sleeper = new Thread(new SleeperTask(2500), "Sleeper");
        Thread waiter = new Thread(new WaiterTask(), "Waiter");
        Thread locker = new Thread(new LockerTask(3000), "Locker");
        Thread blocked = new Thread(new BlockedTask(), "Blocked");
        Thread notifier = new Thread(new NotifierTask(2000), "Notifier");

        Thread[] ts = {sleeper, waiter, locker, blocked, notifier};

        printStates("Created (expect NEW)", ts);

        locker.start();
        Thread.sleep(50);
        blocked.start();

        waiter.start();
        sleeper.start();
        notifier.start();

        while (anyAlive(ts)) {
            printStates("Sampling", ts);
            Thread.sleep(200);
        }

        printStates("Finished (expect TERMINATED)", ts);
    }

    private static boolean anyAlive(Thread[] ts) {
        for (Thread t : ts) {
            if (t.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private void printStates(String title, Thread[] ts) {
        System.out.println("\n[" + time + "] " + title);
        Map<String, Thread.State> snapshot = new LinkedHashMap<>();
        for (Thread t : ts) {
            snapshot.put(t.getName(), t.getState());
            snapshot.forEach((n, s) -> System.out.printf("  %-8s : %s%n", n, s));
        }
    }
}
