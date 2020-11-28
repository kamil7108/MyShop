package pl.polsl.lab1.shop.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * User class
 *
 * @author kamil_machulik
 * @version 1.0
 */
public class User {
    /**
     * User constructor
     *
     * @param nickname user nickname
     * @param password user password
     */
    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
        shopping = new HashMap<>();
    }

    /**
     * User nickname
     */
    private String                nickname;
    /**
     * User password
     */
    private String                password;
    /**
     * User map of article and quantity
     */
    private Map<Article, Integer> shopping;

    /**
     * Get map
     *
     * @return map of article and quantity
     */
    public Map<Article, Integer> getShopping() {
        return shopping;
    }

    /**
     * Get user nickname
     *
     * @return user nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Get user password
     *
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Add article to user shopping
     *
     * @param article  new article
     * @param quantity article quantity
     */
    public void addArticleToBasket(Article article, int quantity) {
        if (article != null && quantity > 0) {
            shopping.put(article, quantity);
        }
    }


}
