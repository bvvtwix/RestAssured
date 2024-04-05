package tests;

import controllers.RepositoryController;
import controllers.UserController;
import models.Repository;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import steps.RepositorySteps;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTest extends BaseTest {

    // TODO вынести создание контролеров в переменные

    @ParameterizedTest
    @MethodSource("dataProviders.NewRepos#newRepos")
    void checkThatCreatedRepoPresentInRepoList(Repository repository) {
        Repository repo = new RepositoryController()
                .executePostNewRepo(repository);
        List<Repository> reposList = new UserController().executeGetUserReposList();

        assertTrue(RepositorySteps.isRepoPresentInList(reposList, repo.getName()),
                "New repo name is not present in list: " + repo.getName());
    }

    @ParameterizedTest
    @MethodSource("dataProviders.NewRepos#newRepos")
    void checkThatRemovedRepoIsNotPresentInRepoList(Repository repository) {
        RepositoryController repositoryController = new RepositoryController();
        UserController userController = new UserController();

        Repository repo = repositoryController.executePostNewRepo(repository);
        repositoryController.executeRemoveRepo(userController.executeGetUserInfo().getLogin(), repo.getName());

        assertThat(userController.executeGetUserReposList()).extracting(Repository::getName).isNotEqualTo(repo.getName());
        assertFalse(RepositorySteps.isRepoPresentInList(userController.executeGetUserReposList(), repo.getName()),
                "New repo name is not present in list: " + repo.getName());
    }

    @ParameterizedTest
    @MethodSource("dataProviders.NewRepos#newRepos")
    void checkCreatedRepoInfo(Repository repository) {
        Repository repo = new RepositoryController()
                .executePostNewRepo(repository);

        assertEquals(repo.getName(), repository.getName());
        assertEquals(repo.getDescription(), repository.getDescription());
        assertEquals(repo.getHomepage(), repository.getHomepage());
        assertEquals(repo.isTemplate(), repository.isTemplate());
        assertEquals(repo.isPrivate(), repository.isPrivate());
    }

}
