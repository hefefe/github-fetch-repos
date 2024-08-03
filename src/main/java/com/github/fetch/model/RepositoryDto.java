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
public class RepositoryDto {

    @Schema(
            description = "name of a repository",
            name = "repository name",
            type = "String",
            example = "Demo application")
    private String name;

    private List<RepositoryBranchDto> branches;
}
