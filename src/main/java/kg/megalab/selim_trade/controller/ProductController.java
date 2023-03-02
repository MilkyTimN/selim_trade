package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/get")
    ResponseEntity<?> getProductById(@RequestParam int id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @GetMapping("/get/all")
    ResponseEntity<?> getAllProducts(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }
}
