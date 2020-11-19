package pl.polsl.lab1.shop.Module;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Class acts as a warehouse
 * @author kamil_machulik
 * @version 1.0
 */
public class Warehouse {
    /**
     * List of articles that warehouse contains
     */
    private DefaultListModel<Article> listOfArticle = new DefaultListModel<Article>();

    /**
     * Add article to listOfArticle
     * @param article new article
     */
    public void addArticle(Article article){
        listOfArticle.addElement(article);
    }

    /**
     * Get all of shop articles
     * @return shop articles
     */
    public DefaultListModel<Article> getListOfArticle(){return listOfArticle;}


}