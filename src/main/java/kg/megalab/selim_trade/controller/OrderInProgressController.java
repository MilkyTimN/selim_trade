package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.dto.NewOrderInProgressRequest;
import kg.megalab.selim_trade.dto.OrderInProgressResponse;
import kg.megalab.selim_trade.service.OrderInProgressService;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-in-progress")
public class OrderInProgressController {
    private final OrderInProgressService orderInProgressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderInProgressResponse createOrderInProgress(
            @RequestBody NewOrderInProgressRequest orderRequest,
            @AuthenticationPrincipal UserDetails adminDetails
            ) {
        return orderInProgressService.createOrderInProgress(orderRequest, adminDetails);
    }

    @GetMapping
    public Page<OrderInProgressResponse> getAllOrdersInProgress(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return orderInProgressService.getAllOrdersInProgress(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public OrderInProgressResponse getOrderInProgressById(@PathVariable("id") int id) {
        return orderInProgressService.getOrderInProgressById(id);
    }
}
