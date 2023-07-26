package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geo")
public class GeocodingController {

    // @Value("${org.yyama.hoge}")

    @GetMapping("/")
    public String index() {
        return "郵便番号";
    }
}
