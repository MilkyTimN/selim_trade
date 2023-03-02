package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.service.OrderInProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-in-progress")
public class OrderInProgressController {
    private final OrderInProgressService orderInProgressService;

    /*@PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public */
}
