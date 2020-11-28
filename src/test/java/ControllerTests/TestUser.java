package ControllerTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.lab1.shop.Model.Article;
import pl.polsl.lab1.shop.Model.User;

import static org.junit.Assert.assertTrue;

public class TestUser {
    /**
     * New class with the same functionality as Article with new constructor
     */
    private static class NewArticle extends Article {
        public NewArticle(String name) {
            super(name, 0.0, "");
        }
    }

    /**
     * Test method addArticleToBasket when pass null
     *
     * @param quantity
     */
    @ParameterizedTest
    @CsvSource({"2", "1"})
    public void test_addArticleToBasket_when_pass_null(int quantity) {
        //GIVEN
        User SUT = new User("test", "test");
        //WHEN
        SUT.addArticleToBasket(null, quantity);
        //THEN
        assertTrue(SUT.getShopping().isEmpty());
    }
}
