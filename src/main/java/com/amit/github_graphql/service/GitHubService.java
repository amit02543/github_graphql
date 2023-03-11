package com.amit.github_graphql.service;

public interface GitHubService {

    String fetchOrgDetailsByName(String name);

    String fetchCommitsByOrgAndRepoName(String orgName, String repoName);

}
