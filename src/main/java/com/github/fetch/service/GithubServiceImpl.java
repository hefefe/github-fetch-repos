package com.github.fetch.service;

import com.github.fetch.exception.CustomErrorException;
import com.github.fetch.exception.model.GithubErrorResponse;
import com.github.fetch.model.RepositoryBranchDto;
import com.github.fetch.model.RepositoryDto;
import com.github.fetch.model.UserAndRepositoriesDto;
import com.github.fetch.model.externalResponse.GithubBranchResponseDto;
import com.github.fetch.model.externalResponse.GithubRepoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GithubServiceImpl implements GithubService {

    private final WebClient webClient;

    private static final String API_ADDRESS = "https://api.github.com";
    private static final String API_GET_REPOS_ENDPOINT = "/users/%s/repos?" +
            "type=owner&" +
            "per_page=%d&" +
            "page=%d";
    private static final String API_GET_BRANCHES_ENDPOINT = "/repos/%s/%s/branches";

    @Override
    @Cacheable("userRepos")
    public UserAndRepositoriesDto fetchRepositoriesOfUser(String userName, Integer pageSize, Integer page) {
        var listOfRepositories = fetchRepositoriesOfUserFromApi(userName, pageSize, page);

        return UserAndRepositoriesDto.builder()
                .owner(userName)
                .repositories(buildListOfRepositories(userName, listOfRepositories))
                .build();
    }

    private List<String> fetchRepositoriesOfUserFromApi(String name, Integer pageSize, Integer page) {
        var repoResourceParams = String.format(API_GET_REPOS_ENDPOINT, name, pageSize, page);

        var response = makeEndpointCall(repoResourceParams)
                .bodyToFlux(GithubRepoResponseDto.class)
                .collectList();

        return Objects.requireNonNull(response.block()).stream()
                .filter(githubResponseDto -> !githubResponseDto.getFork())
                .map(GithubRepoResponseDto::getName)
                .toList();
    }

    private List<RepositoryBranchDto> fetchListOfBranchesFromApi(String userName, String repositoryName) {
        var repoResourceParams = String.format(API_GET_BRANCHES_ENDPOINT, userName, repositoryName);

        var listOfBranches = makeEndpointCall(repoResourceParams)
                .bodyToFlux(GithubBranchResponseDto.class)
                .collectList();

        return Objects.requireNonNull(listOfBranches.block()).parallelStream()
                .map(branch -> RepositoryBranchDto.builder()
                        .name(branch.getName())
                        .lastSha(branch.getCommit().getSha())
                        .build())
                .collect(Collectors.toList());
    }

    private List<RepositoryDto> buildListOfRepositories(String userName, List<String> repositoryName) {
        return repositoryName.parallelStream()
                .map(repoName -> RepositoryDto.builder()
                        .name(repoName)
                        .branches(fetchListOfBranchesFromApi(userName, repoName))
                        .build())
                .collect(Collectors.toList());
    }

    private WebClient.ResponseSpec makeEndpointCall(String endpointString) {
        return webClient.get()
                .uri(API_ADDRESS + endpointString)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> clientResponse.bodyToMono(GithubErrorResponse.class)
                        .map(errorDto -> new CustomErrorException(errorDto.getMessage(), HttpStatus.valueOf(clientResponse.statusCode().value()))));
    }
}
