package com.example.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceTestController {
    @GetMapping("/test01")
    @PreAuthorize("hasAuthority('USER')")
    public String test01() {
        return "test01";
    }

    @GetMapping("/test02")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public String test02() {
        return "test02";
    }

    @GetMapping("/app")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String app() {
        return "app";
    }
    @GetMapping("/hello")
    public String hello(){
        return "===========";
    }
}
