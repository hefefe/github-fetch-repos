package com.github.fetch.controller;

import com.github.fetch.exception.model.GithubErrorResponse;
import com.github.fetch.model.UserAndRepositoriesDto;
import com.github.fetch.service.GithubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/repos")
public class GithubFetchController {

    private final GithubService githubService;

    @Operation(summary = "Get list of the user's repositories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched list of repositories of given user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserAndRepositoriesDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = GithubErrorResponse.class))})
    })
    @GetMapping("/{userName}")
    public ResponseEntity<UserAndRepositoriesDto> getUserAndRepositoriesData(@PathVariable String userName,
                                                                             @RequestParam(defaultValue = "5") Integer pageSize,
                                                                             @RequestParam(defaultValue = "0") Integer page) {

        return ResponseEntity.ok(githubService.fetchRepositoriesOfUser(userName, pageSize, page));
    }
}
