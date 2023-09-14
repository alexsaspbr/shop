package br.com.ada.reactivejavasw.proxy;

import br.com.ada.reactivejavasw.dto.ProductDTO;
import br.com.ada.reactivejavasw.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductProxy {

    @Value("${host.products}")
    private String hostProducts;

    public Mono<ResponseDTO<ProductDTO>> getProductByCode(String code) {
        return WebClient.create(hostProducts)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/products/{code}")
                        .build(code))
                .attribute("code", code)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseDTO<ProductDTO>>(){});
    }

}
