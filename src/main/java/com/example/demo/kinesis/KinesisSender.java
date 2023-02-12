package com.example.demo.kinesis;

import com.amazonaws.services.kinesis.producer.KinesisProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class KinesisSender {

    private final KinesisProducer producer;

    public void run() throws ExecutionException, InterruptedException {
        ByteBuffer payload = ByteBuffer.wrap("{ 'data': 'Something important.' }".getBytes(StandardCharsets.UTF_8));
        producer.addUserRecord("some-data-stream", "partitionKey", payload).get();
    }
}