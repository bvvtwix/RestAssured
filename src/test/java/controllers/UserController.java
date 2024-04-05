package controllers;

import io.qameta.allure.Step;
import io.restassured.http.Method;
import models.Repository;
import models.User;
import utils.ApiClient;
import java.util.List;

public class UserController {

    public final String USER_PATH = "user";
    public static final String GET_USER_SCHEMA_RS = "jsonSchemas/getUserSchema.json";
    public static final String GET_REPO_LIST_RS = "jsonSchemas/getRepoListRs.json";

    @Step("Step - get user info")
    public User executeGetUserInfo() {
        return new ApiClient().build()
                .sendRequest(Method.GET, 200, USER_PATH, GET_USER_SCHEMA_RS)
                .as(User.class);
    }

    @Step("Step - get user repository list")
    public List<Repository> executeGetUserReposList() {
        System.out.println("executeGetUserReposList method call -------------");
        return new ApiClient().build()
                .sendRequest(Method.GET, 200, executeGetUserInfo().getReposUrl(), GET_REPO_LIST_RS)
                .body().jsonPath().getList(".", Repository.class);
    }
}
