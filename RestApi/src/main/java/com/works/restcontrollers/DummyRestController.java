package com.works.restcontrollers;

import com.works.models.Products;
import com.works.models.UserLogin;
import com.works.services.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dummy")
public class DummyRestController {

    final DummyService dummyService;

    @GetMapping("/products")
    public Products products(){
        Products products = dummyService.allProduct();
        products.getProducts().forEach(item -> {
            item.setPrice( item.getPrice() + 50 );
        });
        return products;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLogin userLogin) {
        return dummyService.login(userLogin);
    }

}
