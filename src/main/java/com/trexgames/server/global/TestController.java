package com.trexgames.server.global;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public String hello () {
        System.out.println("프론트에서 api 호출 성공");
        return "Hello from spring";
    }
}
