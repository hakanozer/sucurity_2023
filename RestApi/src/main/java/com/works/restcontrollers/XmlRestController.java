package com.works.restcontrollers;

import com.works.models.Currency;
import com.works.services.XmlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/xml")
public class XmlRestController {

    final XmlService xmlService;

    @GetMapping("/tcmb")
    public List<Currency> tcmb() {
         return xmlService.xml();
    }

}
