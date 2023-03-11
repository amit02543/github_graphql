package com.amit.github_graphql.controller;

import com.amit.github_graphql.service.GitHubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/app/github")
public class GitHubController {


    @Autowired
    private GitHubService gitHubService;



    @RequestMapping(
            value = "/org/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> fetchOrgDetailsByName(@PathVariable String name) {
        return new ResponseEntity<>(gitHubService.fetchOrgDetailsByName(name), HttpStatus.OK);
    }



    @RequestMapping(
            value = "org/{orgName}/repo/{repoName}/commit",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> fetchCommitsByOrgAndRepoName(
            @PathVariable String orgName, @PathVariable String repoName) {
        return new ResponseEntity<>(
                gitHubService.fetchCommitsByOrgAndRepoName(orgName, repoName),
                HttpStatus.OK);
    }



}
