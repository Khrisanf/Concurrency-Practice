package ru.netology.concurrencypractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.concurrencypractice.service.ThreadStatesDemoService;

@RestController
@RequestMapping("/api/states")
public class ThreadStatesController {
    private final ThreadStatesDemoService service;

    public ThreadStatesController(ThreadStatesDemoService service) {
        this.service = service;
    }

    @GetMapping("/run")
    public String run() throws InterruptedException {
        service.runDemo();
        return "Done";
    }
}
