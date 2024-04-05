package steps;

import models.Repository;
import java.util.List;

public class RepositorySteps {

    // TODO добавить библиотеку assertJ и вынести эту проверку в тест
    public static boolean isRepoPresentInList(List<Repository> repoList, String repoName) {
        return repoList.stream().anyMatch(repo -> repo.getName().equals(repoName));
    }

}
