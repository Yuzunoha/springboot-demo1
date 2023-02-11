package com.example.demo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;

@Data
@Configuration
@ConfigurationProperties(prefix = "kinesis")
public class KinesisConfigurationProperties {

    String stream;
    Region region = Region.AP_NORTHEAST_1;
}
