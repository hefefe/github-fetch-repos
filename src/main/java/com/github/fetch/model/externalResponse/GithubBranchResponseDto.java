package com.github.fetch.model.externalResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GithubBranchResponseDto {

    private String name;

    private GithubLastCommitDto commit;

}
