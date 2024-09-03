package com.sku.fitizen.controller.test;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/test")
public class MainController {

    @GetMapping("")
    public String main() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        return "Main Controller : " + name;
    }
}
