package org.example.cloudlandgeminispringai;

import org.example.cloudlandgeminispringai.functions.WeatherConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(WeatherConfigProperties.class)
public class CloudlandGeminiSpringAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudlandGeminiSpringAiApplication.class, args);
    }

}
