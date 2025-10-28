package ru.netology.concurrencypractice.streams.dto;

public record RunStats(String mode, long nanos, long result, long passedCount) {
    public double millis() {
        return nanos / 1_000_000.0;
    }
}
