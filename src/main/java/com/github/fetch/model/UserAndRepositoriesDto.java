package com.github.fetch.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAndRepositoriesDto {

    @Schema(
            description = "name of an owner",
            name = "owner name",
            type = "String",
            example = "testUser23")
    private String owner;

    private List<RepositoryDto> repositories;
}
