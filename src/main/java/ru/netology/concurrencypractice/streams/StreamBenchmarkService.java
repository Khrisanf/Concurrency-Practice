package ru.netology.concurrencypractice.streams;

import org.springframework.stereotype.Service;
import ru.netology.concurrencypractice.streams.dto.BenchmarkReport;
import ru.netology.concurrencypractice.streams.dto.RunStats;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class StreamBenchmarkService {

    public BenchmarkReport runBenchmark(int size, long seed, int warmups) {
        final int cores = Runtime.getRuntime().availableProcessors();

        List<Integer> data = new Random(seed)
                .ints(size, 0, 1_000_000)
                .boxed()
                .collect(Collectors.toList());

        for (int i = 0; i < warmups; i++) {
            runSequential(data);
            runParallel(data);
        }

        RunStats seq = runSequential(data);
        RunStats par = runParallel(data);

        return new BenchmarkReport(size, seed, cores, seq, par);
    }

    private RunStats runSequential(List<Integer> data) {
        long t0 = System.nanoTime();
        long sum = data.stream()
                .filter(x -> (x & 1) == 0)
                .mapToLong(x -> (long) x * 2)
                .sum();
        long t1 = System.nanoTime();

        long evenCount = data.stream().filter(x -> (x & 1) == 0).count();
        return new RunStats("sequential", (t1 - t0), sum, evenCount);
    }

    private RunStats runParallel(List<Integer> data) {
        long t0 = System.nanoTime();
        long sum = data.parallelStream()
                .filter(x -> (x & 1) == 0)
                .mapToLong(x -> (long) x * 2)
                .sum();
        long t1 = System.nanoTime();

        long evenCount = data.parallelStream().filter(x -> (x & 1) == 0).count();
        return new RunStats("parallel", (t1 - t0), sum, evenCount);
    }
}
