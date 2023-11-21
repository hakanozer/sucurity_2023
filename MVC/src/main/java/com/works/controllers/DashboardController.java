package com.works.controllers;

import com.works.services.TinkEncDec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    final TinkEncDec tinkEncDec;

    @GetMapping("/dashboard")
    public String dashboard() {
        String cipherText = tinkEncDec.encrypt("12345");
        System.out.println(cipherText);
        return "dashboard";
    }

}
