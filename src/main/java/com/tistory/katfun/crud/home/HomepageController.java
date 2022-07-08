package com.tistory.katfun.crud.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomepageController {

    @GetMapping("/")
    public ModelAndView homePage() {
        return new ModelAndView("home");
    }
}
