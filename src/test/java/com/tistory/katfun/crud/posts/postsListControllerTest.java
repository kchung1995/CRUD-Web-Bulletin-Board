package com.tistory.katfun.crud.posts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(postsListController.class)
public class postsListControllerTest {

    @Autowired
    postsListController postsListController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getPostsList() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/postsList"))
                .andExpect(status().isOk())
                .andExpect(content().string("This will return posts's URI."))
                ;
    }
}
