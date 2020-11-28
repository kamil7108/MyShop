package ControllerTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.lab1.shop.Model.Article;
import pl.polsl.lab1.shop.Model.MarkWarehouse;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestMarkWarehouseClass {


    /**
     * New class with the same functionality as Article with new constructor
     */
    private static class NewArticle extends Article {
        public NewArticle(String name) {
            super(name, 0.0, "");
        }
    }

    /**
     * Check if filter doesn't match to any article method will return empty list.
     *
     * @param filter
     */
    @ParameterizedTest
    @ValueSource(strings = {"filter", "zx", "ma", "pp", "Filter", "testowanko"})
    public void test_getFilteredListOfArticle_when_pass_filter_dosent_match_to_any_article(String filter) {
        //given
        MarkWarehouse SUT = new MarkWarehouse("test");
        String[] names = {"test", "pad", "ksiazka", "chusteczka"};
        //when
        for (String name : names) {
            SUT.addArticle(new NewArticle(name));
        }
        List<Article> filteredListOfArticle = SUT.getFilteredListOfArticle(filter);
        //then
        assertTrue(filteredListOfArticle.isEmpty());
    }

    /**
     * Check if filter is empty, method will return all articles
     *
     * @param filter
     */
    @ParameterizedTest
    @ValueSource(strings = "")
    public void test_getFilteredListOfArticle_when_pass_empty_filter(String filter) {
        //given
        MarkWarehouse SUT = new MarkWarehouse("test");
        String[] names = {"test", "pad", "ksiazka", "chusteczka"};
        for (String name : names) {
            SUT.addArticle(new NewArticle(name));
        }
        //when
        List<Article> filteredListOfArticle = SUT.getFilteredListOfArticle(filter);
        //then
        assertEquals(SUT.getListOfArticle(), filteredListOfArticle);
    }


    static Stream<Arguments> methodSource() {
        List<List<Article>> params =
                List.of(
                        List.of(new NewArticle("NAME"), new NewArticle("NAME2")),
                        List.of(new NewArticle("Game"), new NewArticle("GameOfThrone"), new NewArticle("Giereczka"))
                );
        List<List<Article>> results =
                List.of(
                        List.of(new NewArticle("NAME"), new NewArticle("NAME2")),
                        List.of(new NewArticle("Game"), new NewArticle("GameOfThrone"))
                );
        List<String> filtrs = List.of("NAME", "Game");

        return IntStream.range(0, params.size())
                .mapToObj(index -> Arguments.arguments(params.get(index), results.get(index), filtrs.get(index)));
    }

    /**
     * Check if returned list is equal to expected
     *
     * @param filter
     */
    @ParameterizedTest
    @MethodSource("methodSource")
    public void test_getFilteredListOfArticle_when_pass_filter_match_to_articles(List<Article> allArticles, List<Article> result, String filter) {
        //given
        MarkWarehouse SUT = new MarkWarehouse("test");
        for (Article article : allArticles) {
            SUT.addArticle(article);
        }
        //when

        List<Article> filteredListOfArticle = SUT.getFilteredListOfArticle(filter);

        //then
        int i = 0;
        for (Article article : filteredListOfArticle) {
            assertEquals(result.get(i++).toString(), article.toString());
        }

    }

    /**
     * Check addArticle method when null passed
     *
     * @param articleList
     */
    @ParameterizedTest
    @MethodSource("methodSource")
    public void test_addArticle(List<Article> articleList) {
        //given
        MarkWarehouse SUT = new MarkWarehouse("test");
        for (Article a : articleList) {
            SUT.addArticle(a);
        }
        //when
        Article article = null;
        List<Article> expectedList = SUT.getListOfArticle();
        SUT.addArticle(article);
        //then
        assertEquals(expectedList, SUT.getListOfArticle());


    }


}
