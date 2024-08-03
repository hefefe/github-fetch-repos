package com.github.fetch.exception.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ErrorDto extends GithubErrorResponse {
    @Schema(
            description = "error status code",
            name = "status",
            type = "Integer",
            example = "404")
    private Integer status;
}
