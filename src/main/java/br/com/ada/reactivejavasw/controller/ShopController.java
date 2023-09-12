package br.com.ada.reactivejavasw.controller;

import br.com.ada.reactivejavasw.dto.OrderDTO;
import br.com.ada.reactivejavasw.service.ShopService;
import br.com.ada.reactivejavasw.dto.ProductDTO;
import br.com.ada.reactivejavasw.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Create purchase shop")
    public Mono<ResponseDTO> create(@RequestHeader("code") String code) {
        return this.shopService.create(code);
    }

}
