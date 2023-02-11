package com.vreijsen.consumer.processor;

import lombok.extern.slf4j.Slf4j;
import software.amazon.kinesis.exceptions.InvalidStateException;
import software.amazon.kinesis.exceptions.ShutdownException;
import software.amazon.kinesis.lifecycle.events.*;
import software.amazon.kinesis.processor.ShardRecordProcessor;

@Slf4j
public class KinesisRecordProcessor implements ShardRecordProcessor {

    @Override
    /* Called when initializing the record processor; can be used to set some MDC properties before receiving data. */
    public void initialize(InitializationInput input) { }

    @Override
    public void processRecords(ProcessRecordsInput input) {
        input.records().forEach(record -> log.info("Received Kinesis message: {}.", record));
    }

    @Override
    /* Called when the lease has been lost to another consumer; can be used to remove some MDC properties. */
    public void leaseLost(LeaseLostInput input) { }

    @Override
    /* Called when the last message of the shard has been processed, and we need to persist our checkpoint. */
    public void shardEnded(ShardEndedInput input) {
        try {
            input.checkpointer().checkpoint();
        } catch (InvalidStateException | ShutdownException e) {
            log.error("Exception while checkpointing at shard end. Giving up.", e);
        }
    }

    @Override
    /* Called when the app is shutting down, so we need to persist our current checkpoint. */
    public void shutdownRequested(ShutdownRequestedInput input) {
        try {
            input.checkpointer().checkpoint();
        } catch (InvalidStateException | ShutdownException e) {
            log.error("Exception while checkpointing at shutdown. Giving up.", e);
        }
    }
}
