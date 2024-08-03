package com.github.fetch.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RepositoryBranchDto {

    @Schema(
            description = "name of a branch in repository",
            name = "branch name",
            type = "String",
            example = "main")
    private String name;

    @Schema(
            description = "last commit sha",
            name = "sha",
            type = "String",
            example = "28000929e7dee950f3404000ec5eb1e3484f15f3")
    private String lastSha;
}
