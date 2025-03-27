package app.cybergalaxy.safir_gallery.controller;

import app.cybergalaxy.safir_gallery.service.impl.BasketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/baskets")
public class BasketController {

    @Autowired
    private BasketService service;


    @PostMapping
    private ResponseEntity<String> addOrder(
            @RequestParam("client") String client,
            @RequestParam("phone") String phone,
            @RequestParam("flowers") List<Integer> flowers,
            @RequestParam("deliveryDate") LocalDate deliveryDate
            ) {
        service.addOrder(client, phone, flowers, deliveryDate);
        return ResponseEntity.ok("The order has been sent.");
    }

}
