package com.github.fetch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = FetchApplication.class)
class GithubFetchControllerIT {

    @Autowired
    private MockMvc mvc;

    private static final String GITHUB_FETCH_ENDPOINT = "/api/v1/repos";

    @Test
    void shouldReturnStatus200() throws Exception {

        //given
        var testUserName = "hefefe";
        var fetchEndpoint = String.format(GITHUB_FETCH_ENDPOINT + "/%s", testUserName);

        //when
        var result = mvc.perform(get(fetchEndpoint).contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpectAll(
                status().isOk(),
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                jsonPath("$.owner").value(testUserName)
        );
    }

    @Test
    void shouldReturnStatus404WithMessage() throws Exception {

        //given
        var testUserName = "7e57n4me7ha75h0uldn783f1nd48l3";
        var errorMessage = "Not Found";
        var fetchEndpoint = String.format(GITHUB_FETCH_ENDPOINT + "/%s", testUserName);

        //when
        var result = mvc.perform(get(fetchEndpoint).contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpectAll(
                status().isNotFound(),
                content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                jsonPath("$.message").value(errorMessage)
        );
    }

}
