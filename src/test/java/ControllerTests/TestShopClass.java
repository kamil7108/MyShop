package ControllerTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import pl.polsl.lab1.shop.Model.Article;
import pl.polsl.lab1.shop.Model.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestShopClass {

    private void mockData(Shop shop) {
        shop.addArticle(new Article("A Game of Thrones", 50.00, "A Game of Thrones is the first novel in A Song of Ice and Fire, a series of fantasy novels by the American author George R. R. Martin. It was first published on August 1, 1996. The novel won the 1997 Locus Award[2] and was nominated for both the 1997 Nebula Award[2] and the 1997 World Fantasy Award.[3] The novella Blood of the Dragon, comprising the Daenerys Targaryen chapters from the novel, won the 1997 Hugo Award for Best Novella. In January 2011, the novel became a New York Times Bestseller[4] and reached No. 1 on the list in July 2011"), "Dom Wydawniczy Muza");
        shop.addArticle(new Article("A Clash of Kings", 49.99, "A Clash of Kings is the second novel in A Song of Ice and Fire, an epic fantasy series by American author George R. R. Martin expected to consist of seven volumes. It was first published on 16 November 1998 in the United Kingdom, although the first United States edition did not follow until February 2, 1999[2] Like its predecessor, A Game of Thrones, it won the Locus Award (in 1999) for Best Novel and was nominated for the Nebula Award (also in 1999) for best novel. In May 2005 Meisha Merlin released a limited edition of the novel, fully illustrated by John Howe."), "Dom Wydawniczy Muza");
        shop.addArticle(new Article("A Storm of Swords", 39.99, "A Storm of Swords is the third of seven planned novels in A Song of Ice and Fire, a fantasy series by American author George R. R. Martin. It was first published on August 8, 2000, in the United Kingdom,[1] with a United States edition following in November 2000. Its publication was preceded by a novella called Path of the Dragon, which collects some of the Daenerys Targaryen chapters from the novel into a single book."), "Dom Wydawniczy Muza");
    }

    static Stream<Arguments> stringArrayProvider() {
        return Stream.of(
                Arguments.of((Object) new String[]{"A Game of Thrones", "A Clash of Kings", "A Storm of Swords"})
        );
    }

    /**
     * Verify that getAllArticles links MarkWarehouse articles correctly and return all articles
     *
     * @param artcNames - expected result
     */
    @ParameterizedTest
    @MethodSource("stringArrayProvider")
    public void test_get_all_Articles(String[] artcNames) {
        //GIVEN
        Shop shop = new Shop("Test");
        mockData(shop);
        int i = 0;
        //WHEN
        Object[] articles = shop.getAllArticles().toArray();

        for (Object article : articles) {
            //THEN
            assertEquals(artcNames[i], ((Article) article).getName());
            i++;
        }
    }

    /**
     * Check if there is no such brand, a new brand will be created and the article will be added to it
     *
     * @param mark
     */
    @ParameterizedTest
    @CsvSource("TEST")
    public void test_addArticle_to_warehouse_which_doesnt_exist(String mark) {
        //GIVEN
        Shop SUT = new Shop("shop");
        mockData(SUT);
        Article expeted = new Article("NAME", 0.0, "DESC");
        //WHEN
        SUT.addArticle(expeted, mark);
        //THEN
        //check if the warehouse is added to shop
        assertTrue(SUT.getAllMarks().stream().filter(m -> m.equals(mark)).findFirst().isPresent());
        //check if the article is added to warehouse
        Article newArticle = SUT.getFilteredListOfArticles(java.util.Optional.ofNullable(expeted.getName()), mark).get(0);
        assertEquals(expeted, newArticle);
    }

    /**
     * When passed null then nothing will be added to list
     *
     * @param name
     */
    @ParameterizedTest
    @CsvSource("TEST")
    public void test_addArticle_when_passed_null(String name) {
        //GIVEN
        Shop SUT = new Shop(name);
        //WHEN
        SUT.addArticle(null, "test");
        //THEN
        assertTrue(SUT.getAllArticles().isEmpty());
    }

    /**
     * New class with the same functionality as Article with new constructor
     */
    private static class NewArticle extends Article {
        public NewArticle(String name) {
            super(name, 0.0, "");
        }
    }

    static Stream<Arguments> methodSource() {
        List<List<Article>> params =
                List.of(
                        List.of(new NewArticle("NAME"), new NewArticle("NAME2")),
                        List.of(new NewArticle("Game"), new NewArticle("GameOfThrone"), new NewArticle("Giereczka")),
                        List.of(new NewArticle("Game"), new NewArticle("GameOfThrone"), new NewArticle("Giereczka"))
                );
        List<List<String>> marksOfAtricles = List.of(
                List.of("T", "A"),
                List.of("T", "T", "T"),
                List.of("T", "A", "T")
        );
        List<List<Article>> results =
                List.of(
                        List.of(new NewArticle("NAME"), new NewArticle("NAME2")),
                        List.of(new NewArticle("Game"), new NewArticle("GameOfThrone")),
                        List.of(new NewArticle("Game"), new NewArticle("GameOfThrone"), new NewArticle("Giereczka"))
                );
        List<String> filtrs = List.of("NAME", "Game", "");
        List<List<String>> marks = List.of(
                List.of("T", "A"),
                List.of("T"),
                List.of()
        );

        return IntStream.range(0, params.size())
                .mapToObj(index -> Arguments.arguments(params.get(index), marksOfAtricles.get(index), filtrs.get(index), marks.get(index), results.get(index)));
    }

    /**
     * Test checks if filtering by brand and filter returns appropriate values. The test also checks extreme situations.
     * Test checks if the values returned by the method are as expected
     *
     * @param articles        All articles in shop
     * @param marksOfArticles Marks of articles
     * @param filter          filter value
     * @param marks           filter value
     * @param expectedResult  expected articles
     */
    @ParameterizedTest
    @MethodSource("methodSource")
    public void test(List<Article> articles, List<String> marksOfArticles, String filter, List<String> marks, List<Article> expectedResult) {
        //GIVEN
        Shop SUT = new Shop("Test");
        int i = 0;
        for (Article article : articles) {
            SUT.addArticle(article, marksOfArticles.get(i++));
        }
        //WHEN
        Object[] newArticleArray = SUT.getFilteredListOfArticles(java.util.Optional.ofNullable(filter), marks).toArray();
        for (int index = 0; index < expectedResult.size(); index++) {
            int finalIndex = index;
            //THEN
            assertTrue(Arrays.stream(newArticleArray).anyMatch(a -> ((Article) a).getName().equals(expectedResult.get(finalIndex).getName())));
        }

    }
}
