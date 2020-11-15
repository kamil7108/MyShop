package pl.polsl.lab1.shop.Module;

import java.util.ArrayList;

/**
 * The class representing the store
 * @author kamil
 * @verison 1.0
 */
public class Shop {
    private String name;
    private Warehouse warehouse=new Warehouse();
    public Shop(String name){
        this.name=name;
    }

    public void addArticle(Article a){warehouse.addArticle(a);}
    public Article getArticle(String name){
        return warehouse.getArticleByName(name);}
    public ArrayList<Article> getAllArticles(){return warehouse.getListOfArticle();}

}