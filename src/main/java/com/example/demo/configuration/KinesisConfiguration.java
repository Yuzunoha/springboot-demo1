package com.example.demo.configuration;

import com.example.demo.processor.KinesisRecordProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.kinesis.common.ConfigsBuilder;
import software.amazon.kinesis.coordinator.Scheduler;
import software.amazon.kinesis.retrieval.polling.PollingConfig;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class KinesisConfiguration {

        @Bean
        public Scheduler scheduler(KinesisAsyncClient kinesis, DynamoDbAsyncClient dynamodb,
                        CloudWatchAsyncClient cloudwatch) {
                String stream = LocalStackClientConfiguration.STREAM;
                ConfigsBuilder configs = new ConfigsBuilder(stream, stream, kinesis,
                                dynamodb, cloudwatch,
                                UUID.randomUUID().toString(),
                                KinesisRecordProcessor::new);

                return new Scheduler(
                                configs.checkpointConfig(),
                                configs.coordinatorConfig(),
                                configs.leaseManagementConfig(),
                                configs.lifecycleConfig(),
                                configs.metricsConfig(),
                                configs.processorConfig(),
                                configs.retrievalConfig().retrievalSpecificConfig(
                                                new PollingConfig(stream, kinesis)));
        }
}
