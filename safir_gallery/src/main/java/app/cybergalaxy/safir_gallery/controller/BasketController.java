package app.cybergalaxy.safir_gallery.controller;

import app.cybergalaxy.safir_gallery.dto.request.BasketAddRequest;
import app.cybergalaxy.safir_gallery.exception.MyException;
import app.cybergalaxy.safir_gallery.service.impl.BasketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baskets")
public class BasketController {

    @Autowired
    private BasketService service;


    @PostMapping
    private ResponseEntity<String> addOrder(@Valid @RequestBody BasketAddRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException("There is a problem in sent data!", br);
        }
        service.addOrder(request);
        return ResponseEntity.ok("The order has been sent.");
    }

}
