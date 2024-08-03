package com.github.fetch.exception.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GithubErrorResponse {

    @Schema(
            description = "error message",
            name = "message",
            type = "String",
            example = "Not Found")
    private String message;
}
