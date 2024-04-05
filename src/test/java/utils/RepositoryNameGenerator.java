package utils;

import java.time.LocalDateTime;
import java.util.Random;

public class RepositoryNameGenerator {

    public static synchronized String generateUniqueRepositoryName() {
        return "repo_" + System.currentTimeMillis();
    }

}
