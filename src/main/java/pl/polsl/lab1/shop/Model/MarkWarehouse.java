package pl.polsl.lab1.shop.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class acts as a warehouse
 *
 * @author kamil_machulik
 * @version 1.1
 */
public class MarkWarehouse {

    /**
     * MarkWarehouse constructor
     * @param mark - warehouse mark
     */
    public MarkWarehouse(String mark) {
        this.mark = mark;
    }

    /**
     * Warehouse mark
     */
    String mark;

    /**
     * List of articles that warehouse contains
     */
    private List<Article> listOfArticle = new ArrayList<>();

    /**
     * Add article to listOfArticle
     *
     * @param article new article
     */
    public void addArticle(Article article) {
        if (article != null)
            listOfArticle.add(article);
    }

    /**
     * Get all of shop articles
     *
     * @return shop articles
     */
    public List<Article> getListOfArticle() {
        return listOfArticle;
    }

    /**
     * Returns the list of articles which contains filter
     *
     * @param filter value that articles name should contains
     * @return new list with articles filtered by name
     */
    public List<Article> getFilteredListOfArticle(String filter) {
        List<Article> arrayList = new ArrayList<>();
        listOfArticle.stream()
                .filter(article -> article.getName().toLowerCase().contains(filter.toLowerCase()))
                .forEach(arrayList::add);

        return arrayList;
    }

    /**
     * Get mark of warehouse
     *
     * @return warehouse mark
     */
    public String getMark() {
        return mark;
    }


}