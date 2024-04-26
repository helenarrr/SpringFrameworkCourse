package tech.helen_app.provider;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import tech.helen_app.model.Book;

@Component
public class BookProvider {
    private final WebClient webClient;

    public BookProvider(ReactorLoadBalancerExchangeFilterFunction loadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder()
                .filter(loadBalancerExchangeFilterFunction)
                .build();
    }

    public Book findBookById(Long bookId) {
        return webClient.get()
                .uri("http://book-service/book/{id}", bookId)
                .retrieve()
                .bodyToMono(Book.class)
                .block();
    }
}
