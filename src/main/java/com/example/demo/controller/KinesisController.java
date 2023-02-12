package com.example.demo.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.kinesis.KinesisSender;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kinesis")
public class KinesisController {

    private final KinesisSender kinesisSender;

    @GetMapping("/send")
    public String send() {
        try {
            kinesisSender.run();
        } catch (ExecutionException e) {
            return "ExecutionExceptionが発生しました。";
        } catch (InterruptedException e) {
            return "InterruptedExceptionが発生しました。";
        }
        return "sendで例外は発生しませんでした。";
    }
}
