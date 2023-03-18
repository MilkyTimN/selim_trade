package kg.megalab.selim_trade.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.megalab.selim_trade.dto.NewOrderRequest;
import kg.megalab.selim_trade.dto.NewOrderResponse;
import kg.megalab.selim_trade.service.NewOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/new-order")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class NewOrderController {

    private final NewOrderService newOrderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewOrderResponse saveNewOrder(@RequestBody NewOrderRequest dto) {
        return newOrderService.saveOrder(dto);
    }

    @DeleteMapping("/{newOrderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNewOrder(@PathVariable("newOrderId") int id) {
        newOrderService.deleteNewOrder(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    @GetMapping
    public Page<NewOrderResponse> getAllNewOrders(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return newOrderService.getAllNewOrders(pageNo, pageSize, sortBy);
    }

    @SecurityRequirements
    @GetMapping("/{newOrderId}")
    public NewOrderResponse getNewOrderById(@PathVariable("newOrderId") int id) {
        return newOrderService.getNewOrderById(id);
    }
}
