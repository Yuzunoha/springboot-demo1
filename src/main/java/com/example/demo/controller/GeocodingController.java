package com.example.demo.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geo")
public class GeocodingController {

    @Value("${apikey}")
    private String apikey;

    @GetMapping("")
    public String index() {
        var client = HttpClient.newHttpClient();
        var url = "https://dummyapi.com";
        var request = HttpRequest.newBuilder(
                URI.create(url))
                .header("X-API-Key", apikey)
                .header("accept", "application/json")
                .build();

        try {
            var response = client.send(request, BodyHandlers.ofString());
            System.out.println(response);
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "郵便番号";
    }
}
