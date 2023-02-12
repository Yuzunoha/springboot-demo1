package com.example.demo.kinesis;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import software.amazon.kinesis.processor.ShardRecordProcessor;
import software.amazon.kinesis.processor.ShardRecordProcessorFactory;

@RequiredArgsConstructor
@Component
public class MyShardRecordProcessorFactory implements ShardRecordProcessorFactory {

    private final ShardRecordProcessor shardRecordProcessor;

    @Override
    public ShardRecordProcessor shardRecordProcessor() {
        return shardRecordProcessor;
    }

}
