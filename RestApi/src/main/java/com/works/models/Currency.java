package com.works.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Currency {
    public String isim;
    public String forexBuying;
    public String forexSelling;
    public String banknoteBuying;
    public String banknoteSelling;
}