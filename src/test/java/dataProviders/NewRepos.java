package dataProviders;

import models.Repository;
import org.junit.jupiter.params.provider.Arguments;
import utils.RepositoryNameGenerator;

import java.util.stream.Stream;

public class NewRepos {

    public static Stream<Arguments> newRepos() {
        return Stream.of(
                Arguments.of(Repository.builder()
                        .name(RepositoryNameGenerator.generateUniqueRepositoryName())
                        .description("Autotest created")
                        .homepage("https://github.com")
                        .isTemplate(false)
                        .isPrivate(false)
                        .build())
        );
    }
}
