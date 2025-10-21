package ru.netology.concurrencypractice.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.concurrencypractice.SimpleService;

import java.util.List;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    private final SimpleService simpleService;

    public DemoController(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    @PostMapping("/start")
    public String start(@RequestParam(defaultValue = "5") int iterations,
                        @RequestParam(defaultValue = "false") boolean wait) throws InterruptedException {
        simpleService.startDemo(iterations);
        if (wait) {
            simpleService.waitForCompletion();
        }
        return "Started: CounterWorker & LoggerThread, iterations=" + iterations + (wait ? " (waited for completion)" : "");
    }

    @GetMapping("/threads")
    public List<String> threads() {
        return simpleService.activeThreads();
    }
}