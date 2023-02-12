package com.example.demo.kinesis;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.kinesis.common.ConfigsBuilder;
import software.amazon.kinesis.common.KinesisClientUtil;
import software.amazon.kinesis.coordinator.Scheduler;
import software.amazon.kinesis.retrieval.polling.PollingConfig;
import java.net.URI;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class KinesisConfiguration {

        private static final URI LOCALSTACK_URI = URI.create("http://localhost:4566");
        public static final String STREAM = "some-data-stream";
        public static final Region REGION = Region.AP_NORTHEAST_1;

        @Bean
        public KinesisAsyncClient kinesisAsyncClient() {
                return KinesisClientUtil.createKinesisAsyncClient(
                                KinesisAsyncClient.builder()
                                                .endpointOverride(LOCALSTACK_URI)
                                                .region(REGION));
        }

        @Bean
        public DynamoDbAsyncClient dynamoClient() {
                return DynamoDbAsyncClient.builder()
                                .endpointOverride(LOCALSTACK_URI)
                                .region(REGION)
                                .build();
        }

        @Bean
        public CloudWatchAsyncClient cloudWatchClient() {
                return CloudWatchAsyncClient.builder()
                                .endpointOverride(LOCALSTACK_URI)
                                .region(REGION)
                                .build();
        }

        @Bean
        public Scheduler scheduler(KinesisAsyncClient kinesis, DynamoDbAsyncClient dynamodb,
                        CloudWatchAsyncClient cloudwatch) {
                ConfigsBuilder configs = new ConfigsBuilder(STREAM, STREAM, kinesis,
                                dynamodb, cloudwatch,
                                UUID.randomUUID().toString(),
                                MyShardRecordProcessor::new);

                return new Scheduler(
                                configs.checkpointConfig(),
                                configs.coordinatorConfig(),
                                configs.leaseManagementConfig(),
                                configs.lifecycleConfig(),
                                configs.metricsConfig(),
                                configs.processorConfig(),
                                configs.retrievalConfig().retrievalSpecificConfig(
                                                new PollingConfig(STREAM, kinesis)));
        }
}
