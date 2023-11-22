package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository repository;
    final CacheManager cacheManager;

    public ResponseEntity save(Product product) {
        repository.save(product);
        cacheManager.getCache("product").clear();
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @Cacheable("product")
    public ResponseEntity list() {
        List<Product> list = repository.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @Scheduled(fixedDelay = 10000, timeUnit = TimeUnit.MILLISECONDS)
    public void clearCache() {
        //System.out.println("clearCache call");
        cacheManager.getCache("product").clear();
    }



}
