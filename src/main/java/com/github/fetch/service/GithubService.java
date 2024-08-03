package com.github.fetch.service;

import com.github.fetch.model.UserAndRepositoriesDto;

public interface GithubService {

    UserAndRepositoriesDto fetchRepositoriesOfUser(String userName, Integer pageSize, Integer page);
}
