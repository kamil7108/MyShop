package ControllerTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.lab1.shop.Model.User;
import pl.polsl.lab1.shop.Model.UserRepo;

import static org.junit.Assert.*;

public class TestUserRepo {

    /**
     * Attempting to add a user with an existing nickname
     *
     * @param nickname
     * @param password
     */
    @ParameterizedTest
    @CsvSource({"TEST,TEST", ","})
    public void test_addUser_passed_existing_nickname_or_null(String nickname, String password) {
        //GIVEN
        UserRepo SUT = new UserRepo();
        SUT.addUser("TEST", "TEST");
        //WHEN+THEN
        assertFalse(SUT.addUser(nickname, password));
    }

    /**
     * Check if user is adding to user repository
     * @param nickname
     * @param password
     */
    @ParameterizedTest
    @CsvSource("Test,Test")
    public void test_addUser(String nickname, String password) {
        //GIVEN
        UserRepo SUT = new UserRepo();

        //WHEN+THEN
        assertTrue(SUT.addUser(nickname, password));
        assertTrue(!SUT.getUserList().isEmpty());
    }

    /**
     * Check if logIn returns correct user
     *
     * @param nickname
     * @param password
     */
    @ParameterizedTest
    @CsvSource({"TEST,TEST", "KAMIL,KAMIL12"})
    public void test_logIn(String nickname, String password) {
        //GIVEN
        UserRepo SUT = new UserRepo();
        SUT.addUser("TEST", "TEST");
        SUT.addUser("KAMIL", "KAMIL12");
        //WHEN
        User user = SUT.logIn(nickname, password);
        //THEN
        assertNotNull(user);
        assertEquals(nickname, user.getNickname());
        assertEquals(password, user.getPassword());
    }

    /**
     * Check if logIn returns correct user
     *
     * @param nickname
     * @param password
     */
    @ParameterizedTest
    @CsvSource({",TEST", "KAMIL,", ","})
    public void test_logIn_passed_wrong_nick_or_password(String nickname, String password) {
        //GIVEN
        UserRepo SUT = new UserRepo();
        SUT.addUser("TEST", "TEST");
        SUT.addUser("KAMIL", "KAMIL12");
        //WHEN
        User user = SUT.logIn(nickname, password);
        //THEN
        assertNull(user);
    }


}
