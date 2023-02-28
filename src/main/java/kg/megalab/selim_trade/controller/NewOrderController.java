package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.OrderRequest;
import kg.megalab.selim_trade.dto.OrderResponse;
import kg.megalab.selim_trade.service.NewOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class NewOrderController {

    private final NewOrderService service;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse saveNewOrder(@RequestBody OrderRequest dto) {
        return service.saveOrder(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNewOrder(@PathVariable("id") int id) {
        service.deleteNewOrder(id);
    }
}
