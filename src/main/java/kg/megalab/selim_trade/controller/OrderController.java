package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.OrderRequest;
import kg.megalab.selim_trade.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/get")
    ResponseEntity<?> getOrder(@RequestBody OrderRequest dto) {
        return ResponseEntity.ok(service.saveOrder(dto));
    }
}
