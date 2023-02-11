package com.example.demo.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.kinesis.common.KinesisClientUtil;
import java.net.URI;

@Configuration
@RequiredArgsConstructor
public class LocalStackClientConfiguration {

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
}
