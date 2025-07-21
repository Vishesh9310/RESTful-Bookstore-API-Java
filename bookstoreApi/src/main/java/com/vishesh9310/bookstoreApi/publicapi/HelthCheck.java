package com.vishesh9310.bookstoreApi.publicapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelthCheck {

    @GetMapping("/health-check")
    public String helthcheck(){
        return "ok";
    }
}
