package tests;

import controllers.RepositoryController;
import controllers.UserController;
import models.Repository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utils.PropertiesLoader;
import java.util.List;

public class BaseTest {

    @BeforeAll
    static void loadProperties() {
        PropertiesLoader.getInstance().readAllProperties();
    }

    @AfterAll
    static public void removeAllRepos() {
        UserController userController = new UserController();
        List<Repository> reposList = userController.executeGetUserReposList();
        RepositoryController repositoryController = new RepositoryController();

        reposList.forEach(repo -> repositoryController
                .executeRemoveRepo(userController.executeGetUserInfo().getLogin(),
                        repo.getName()));
    }
}
