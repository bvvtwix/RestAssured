package tests;

import models.User;
import org.junit.jupiter.api.Test;
import controllers.UserController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest extends BaseTest{

    @Test
    void checkGetUserResponseTest() {
        User user = new UserController().executeGetUserInfo();
        assertTrue(user.getUrl().contains("https://api.github.com/users/"));
        assertEquals(user.getLogin(), "autotestacc");
    }

    @Test
    void checkGetUserReposList() {
        UserController userController = new UserController();
        userController.executeGetUserReposList();
    }
}
