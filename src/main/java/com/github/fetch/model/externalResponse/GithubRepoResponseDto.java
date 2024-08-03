package com.github.fetch.model.externalResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GithubRepoResponseDto {

    private String name;

    private Boolean fork;
}
