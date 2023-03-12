package kg.megalab.selim_trade.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.megalab.selim_trade.dto.NewOrderInProgressRequest;
import kg.megalab.selim_trade.dto.OrderInProgressResponse;
import kg.megalab.selim_trade.dto.UpdateOrderInProgressRequest;
import kg.megalab.selim_trade.service.OrderInProgressService;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-in-progress")
public class OrderInProgressController {
    private final OrderInProgressService orderInProgressService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderInProgressResponse createOrderInProgress(
            @PathVariable("id") int id,
            @RequestBody NewOrderInProgressRequest orderRequest,
            @AuthenticationPrincipal UserDetails adminDetails
            ) {
        return orderInProgressService.createOrderInProgress(id,orderRequest, adminDetails);
    }

    @SecurityRequirements
    @PreAuthorize("permitAll()")
    @GetMapping
    public Page<OrderInProgressResponse> getAllOrdersInProgress(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return orderInProgressService.getAllOrdersInProgress(pageNo, pageSize, sortBy);
    }

    @SecurityRequirements
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public OrderInProgressResponse getOrderInProgressById(@PathVariable("id") int id) {
        return orderInProgressService.getOrderInProgressById(id);
    }


    @PutMapping("/{id}")
    public OrderInProgressResponse updateOrderInProgressById(
            @PathVariable("id") int id,
            @RequestBody UpdateOrderInProgressRequest updateOrderInProgressRequest,
            @AuthenticationPrincipal UserDetails adminDetails) {
        return orderInProgressService.updateOrderInProgressById(id, updateOrderInProgressRequest, adminDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderInProgressById(@PathVariable("id") int id) {
        orderInProgressService.deleteById(id);
    }
}
