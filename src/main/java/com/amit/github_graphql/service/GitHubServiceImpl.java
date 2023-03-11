package com.amit.github_graphql.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
@Service
public class GitHubServiceImpl implements GitHubService {


    @Value("${github.api.url}")
    private String githubApiUrl;


    @Value("${github.api.token}")
    private String githubApiToken;


    @Autowired
    private RestTemplate restTemplate;



    @Override
    public String fetchOrgDetailsByName(String name) {

        String authToken = "token " + githubApiToken;


        String query = "{\n" +
                "    organization(login: \"spring-projects\") {\n" +
                "        login\n" +
                "        name\n" +
                "        description\n" +
                "        email\n" +
                "        createdAt\n" +
                "        repositories (first: 100) {\n" +
                "            totalCount\n" +
                "            pageInfo {\n" +
                "                hasNextPage\n" +
                "                startCursor\n" +
                "                endCursor\n" +
                "            }\n" +
                "            edges {\n" +
                "                node {\n" +
                "                    name\n" +
                "                    description\n" +
                "                    homepageUrl\n" +
                "                    url\n" +
                "                    latestRelease {\n" +
                "                        name\n" +
                "                        description\n" +
                "                        createdAt\n" +
                "                        isLatest\n" +
                "                        isPrerelease\n" +
                "                        updatedAt\n" +
                "                        publishedAt\n" +
                "                    }\n" +
                "                    languages(first: 10) {\n" +
                "                        totalCount\n" +
                "                        edges {\n" +
                "                            node {\n" +
                "                                name\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n" +
                "                    labels(first: 10) {\n" +
                "                        edges {\n" +
                "                            node {\n" +
                "                                name\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n" +
                "                    forkCount\n" +
                "                    stargazerCount\n" +
                "                    createdAt\n" +
                "                    updatedAt\n" +
                "                    isArchived\n" +
                "                    isDisabled\n" +
                "                    isPrivate\n" +
                "                    isLocked\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";


        JSONObject requestBody = new JSONObject();
        requestBody.put("query", query);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.put("Authorization", Arrays.asList(authToken));


        ResponseEntity<String> response = restTemplate.exchange(
                githubApiUrl, HttpMethod.POST,
                new HttpEntity<>(requestBody.toString(), headers), String.class);


        return response.getBody();
    }



    @Override
    public String fetchCommitsByOrgAndRepoName(String orgName, String repoName) {
        String authToken = "token " + githubApiToken;


        String query = "{\n" +
                "    repository(owner: \"spring-projects\", name: \"spring-boot\") {\n" +
                "        defaultBranchRef {\n" +
                "            target {\n" +
                "                ... on Commit {\n" +
                "                    id\n" +
                "                    history(first: 100, since: \"2023-01-01T00:00:00\") {\n" +
                "                        totalCount\n" +
                "                        pageInfo {\n" +
                "                            hasNextPage\n" +
                "                            startCursor\n" +
                "                            endCursor\n" +
                "                        }\n" +
                "                        edges {\n" +
                "                            node {\n" +
                "                                message\n" +
                "                                oid\n" +
                "                                additions\n" +
                "                                deletions\n" +
                "                                changedFiles\n" +
                "                                author {\n" +
                "                                    name\n" +
                "                                    email\n" +
                "                                    date\n" +
                "                                }\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";


        JSONObject requestBody = new JSONObject();
        requestBody.put("query", query);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.put("Authorization", Arrays.asList(authToken));


        ResponseEntity<String> response = restTemplate.exchange(
                githubApiUrl, HttpMethod.POST,
                new HttpEntity<>(requestBody.toString(), headers), String.class);


        return response.getBody();
    }


}
