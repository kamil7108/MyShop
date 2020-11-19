package pl.polsl.lab1.shop.Module;

import javax.swing.*;


/**
 * The class representing the store
 * @author @author kamil_machulik
 * @version 1.0
 */
public class Shop {
    /**
     * Shop name
     */
    private String name;

    /**
     * Store warehouse is used to store goods
     */
    private Warehouse warehouse;

    /**
     * Creates new shop and assigns a name to it
     * @param name
     */
    public Shop(String name){
        this.name=name;
        warehouse=new Warehouse();
    }

    /**
     * Adds article to the shop warehouse
     * @param a article to add
     */
    public void addArticle(Article a){warehouse.addArticle(a);}

    /**
     * Get all of shop articles
     * @return shop articles
     */
    public DefaultListModel<Article> getAllArticles(){return warehouse.getListOfArticle();}

}