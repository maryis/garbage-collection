package com.gc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class GarbageCollector {

    private Heap heap;
    private int edenRate;
    private int g1Rate;
    private ScheduledExecutorService scheduledExecutorService;

    public GarbageCollector(Heap heap,
                            @Value("${gc.eden.sweep.rate}") int edenRate,
                            @Value("${gc.g_v1.sweep.rate}") int g1Rate,
                            ScheduledExecutorService scheduledExecutorService
                            ) {
        this.heap = heap;
        this.edenRate = edenRate;
        this.g1Rate = g1Rate;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public void start() {
        scheduledExecutorService.scheduleWithFixedDelay(heap::sweepEden, edenRate, edenRate, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(heap::sweepG1, g1Rate, g1Rate, TimeUnit.SECONDS);
        System.out.println("GC started with two sweepers");
    }
}
