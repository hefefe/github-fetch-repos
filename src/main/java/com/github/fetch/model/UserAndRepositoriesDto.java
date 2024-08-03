package com.github.fetch.model;

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

    private String owner;

    private List<RepositoryDto> repositories;
}
