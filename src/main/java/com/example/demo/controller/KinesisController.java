package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kinesis")
public class KinesisController {

    @GetMapping("/send")
    public String send() {
        return "sendです。";
    }
}
