package tech.helen_app.provider;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import tech.helen_app.model.Author;
import tech.helen_app.model.Book;

@Component
public class AuthorProvider {

    private final WebClient webClient;

    public AuthorProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }

    public Author findAuthorById(Long author_id) {
        return webClient.get()
                .uri("http://book-service/author/{id}", author_id)
                .retrieve()
                .bodyToMono(Author.class)
                .block();
    }
}
