package com.vreijsen.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.kinesis.coordinator.Scheduler;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class KinesisRunner {

    private final Scheduler scheduler;

    @PostConstruct
    public void run() {
        Thread schedulerThread = new Thread(scheduler);
        schedulerThread.setDaemon(true);
        schedulerThread.start();
    }
}
