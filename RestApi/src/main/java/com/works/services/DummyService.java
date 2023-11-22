package com.works.services;

import com.works.models.Products;
import com.works.models.UserJWT;
import com.works.models.UserLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DummyService {

    final RestTemplate restTemplate;

    public Products allProduct() {
        String url = "https://dummyjson.com/products";
        Products products = restTemplate.getForObject(url, Products.class);
        return products;
    }

    public ResponseEntity<UserJWT> login(UserLogin userLogin) {
        String url = "https://dummyjson.com/auth/login";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity httpEntity = new HttpEntity(userLogin, headers);
        ResponseEntity<UserJWT> responseEntity = restTemplate.postForEntity(url, httpEntity, UserJWT.class);
        return responseEntity;
    }


}
