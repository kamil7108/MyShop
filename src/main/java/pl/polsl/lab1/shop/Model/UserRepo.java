package pl.polsl.lab1.shop.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * User Repo
 *
 * @author kamil_machulik
 * @version 1.0
 */
public class UserRepo {
    /**
     * User repo constructor
     */
    public UserRepo() {
        userList = new ArrayList<>();
    }

    /**
     * User list
     */
    List<User> userList;

    /**
     * Try to add new user to userService
     *
     * @param nickname - new user nickname
     * @param password - new user password
     * @return - if data are correct return true otherwise false
     */
    public boolean addUser(String nickname, String password) {
        try {
            if (!nickname.isEmpty() && !password.isEmpty()) {
                for (User user : userList) {
                    if (nickname.toLowerCase().equals(user.getNickname().toLowerCase())) {
                        return false;
                    }
                }
                userList.add(new User(nickname, password));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Log in to the program
     *
     * @param nickname - written nickname
     * @param password - written password
     * @return logged user
     */
    public User logIn(String nickname, String password) {
        try {
            if (!nickname.isEmpty() && !password.isEmpty()) for (User user : userList) {
                if (nickname.equals(user.getNickname()) && password.equals(user.getPassword())) {
                    return user;
                }
            }
            else return null;
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * @return userList
     */
    public List<User> getUserList() {
        return userList;
    }

}
