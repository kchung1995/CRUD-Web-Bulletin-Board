package com.tistory.katfun.crud.posts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class postsListController {

    @RequestMapping("/postsList")
    public String getPostsList() {
        return "This will return posts's URI.";
    }
}
