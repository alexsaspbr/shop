package br.com.ada.reactivejavasw.service;

import br.com.ada.reactivejavasw.dto.ItemDTO;
import br.com.ada.reactivejavasw.dto.OrderDTO;
import br.com.ada.reactivejavasw.dto.ProductDTO;
import br.com.ada.reactivejavasw.dto.ResponseDTO;
import br.com.ada.reactivejavasw.proxy.ProductProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {

    @Autowired
    private ProductProxy productProxy;

    public Mono<ResponseDTO<OrderDTO>> create(String code, Integer quantity) {

        Mono<ResponseDTO<ProductDTO>> responseDTOMono = this.productProxy.getProductByCode(code);

        return responseDTOMono.flatMap(response -> {
            ResponseDTO<OrderDTO> orderDTOResponseDTO = new ResponseDTO<>();
            orderDTOResponseDTO.setMessage("Compra realizada com sucesso!");
            List<ItemDTO> items = List.of(new ItemDTO(quantity, response.getData()));
            BigDecimal total = items.stream()
                    .map(item -> item.getProduct().getAmount().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            orderDTOResponseDTO.setData(new OrderDTO(items, total));
            return Mono.just(orderDTOResponseDTO);
        });

    }
}
