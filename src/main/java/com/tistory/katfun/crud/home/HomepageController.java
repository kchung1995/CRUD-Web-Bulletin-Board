package com.tistory.katfun.crud.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomepageController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

}
