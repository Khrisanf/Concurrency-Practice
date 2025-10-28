package ru.netology.concurrencypractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.concurrencypractice.streams.StreamBenchmarkService;
import ru.netology.concurrencypractice.streams.dto.BenchmarkReport;

@RestController
@RequestMapping("/api/streams")
public class StreamsController {

    private final StreamBenchmarkService service;

    public StreamsController(StreamBenchmarkService service) {
        this.service = service;
    }

    @GetMapping("/benchmark")
    public BenchmarkReport benchmark(
            @RequestParam(defaultValue = "1000000") int size,
            @RequestParam(defaultValue = "42") long seed,
            @RequestParam(defaultValue = "2") int warmups
    ) {
        return service.runBenchmark(size, seed, warmups);
    }
}
