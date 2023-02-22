package kg.megalab.selim_trade.controller;

import kg.megalab.selim_trade.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService service;

    @GetMapping("/get")
    ResponseEntity<?> getNewsById(@RequestParam int id){
        return ResponseEntity.ok(service.getNewsById(id));
    }

    @GetMapping("/get/all")
    ResponseEntity<?> getAllNewses(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(service.getAllNewses(pageable));
    }
}
