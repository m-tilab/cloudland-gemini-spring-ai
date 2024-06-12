package org.example.cloudlandgeminispringai.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;


@Configuration
public class GeminiFunctionConfiguration {

    private final WeatherConfigProperties weatherConfigProperties;

    public GeminiFunctionConfiguration(WeatherConfigProperties weatherConfigProperties) {
        this.weatherConfigProperties = weatherConfigProperties;
    }

    @Bean
    @Description("Get the current weather conditions for the given city.") // function description
    public Function<WeatherService.Request, WeatherService.Response> currentWeather() {
        return new WeatherService(weatherConfigProperties);
    }
}

