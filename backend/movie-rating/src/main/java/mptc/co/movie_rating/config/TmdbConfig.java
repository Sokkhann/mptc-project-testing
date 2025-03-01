package mptc.co.movie_rating.config;

import mptc.co.movie_rating.Client.TmdbClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TmdbConfig {

    // Bean for RestTemplate
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Bean for TmdbClient
    @Bean
    public TmdbClient tmdbClient(RestTemplate restTemplate) {
        return new TmdbClient(restTemplate);
    }
}