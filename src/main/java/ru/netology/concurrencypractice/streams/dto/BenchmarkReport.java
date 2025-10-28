package ru.netology.concurrencypractice.streams.dto;

public record BenchmarkReport(
        int size,
        long seed,
        int availableCores,
        RunStats sequential,
        RunStats parallel
) { }
