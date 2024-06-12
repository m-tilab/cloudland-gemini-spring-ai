package org.example.cloudlandgeminispringai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;


@Configuration
public class GeminiConfiguration {

    public record Request(String location) {
    }

    public record Response(double temp, String conditions) {
    }

    @Bean
    @Description("Get the current weather conditions for the given city.") // function description
    public Function<GeminiConfiguration.Request, GeminiConfiguration.Response> currentWeather() {
        return request -> {

            int temp = 0;
            String conditions = "";

            if (request.location().equalsIgnoreCase("Frankfurt")) {

                temp = 15;
                conditions = "Party Cloudy";
            } else if (request.location().equalsIgnoreCase("Berlin")) {

                temp = 13;
                conditions = "Cloudy";

            }
            return new GeminiConfiguration.Response(temp, conditions);

        };
    }
}

