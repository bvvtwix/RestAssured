package controllers;

import io.qameta.allure.Step;
import io.restassured.http.Method;
import models.Repository;
import utils.ApiClient;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RepositoryController {

    public static final String CREATE_REPO_PATH = "/user/repos";
    private static final String DELETE_REPO_PATH = "repos/{user}/{repo}";
    private static final String CREATE_REPO_SCHEMA_RS = "jsonSchemas/createRepoRs.json";
    public static final String EMPTY_SCHEMA = "";

    @Step("Step - create new repository")
    public Repository executePostNewRepo(Repository repository) {
        return new ApiClient().build().addJsonContentType()
                .addHeader("Accept", "application/vnd.github+json")
//                .addBody(repository)
                .sendRequest(Method.POST, 201, repository, CREATE_REPO_PATH, CREATE_REPO_SCHEMA_RS )
                .as(Repository.class);
    }

    @Step("Step - remove repository")
    public void executeRemoveRepo(String user, String repo) {
        new ApiClient().build()
                .sendRequest(Method.DELETE, 204, DELETE_REPO_PATH, EMPTY_SCHEMA,
                        user, repo);
    }
}
