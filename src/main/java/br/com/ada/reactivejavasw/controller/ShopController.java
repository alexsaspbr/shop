package br.com.ada.reactivejavasw.controller;

import br.com.ada.reactivejavasw.dto.OrderDTO;
import br.com.ada.reactivejavasw.dto.ResponseDTO;
import br.com.ada.reactivejavasw.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/purchase")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Create purchase shop")
    public Mono<ResponseDTO<OrderDTO>> create(@RequestHeader("code") String code, @RequestHeader("quantity") Integer quantity) {
        return this.shopService.create(code, quantity);
    }

}
