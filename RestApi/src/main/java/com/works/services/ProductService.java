package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository repository;

    public ResponseEntity save(Product product) {
        repository.save(product);
        return new ResponseEntity(product, HttpStatus.OK);
    }

    public ResponseEntity list() {
        List<Product> list = repository.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

}
