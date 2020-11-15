package pl.polsl.lab1.shop.Module;

import java.util.ArrayList;

/**
 *
 * @author kamil
 */
public class Warehouse {
    /**
     * List of articles that warehouse contains
     */
    private ArrayList <Article> listOfArticle = new ArrayList();
    /**
     * Add article to listOfArticle
     * @param article new article
     */
    public void addArticle(Article article){
        listOfArticle.add(article);
    }
    /**
     * Get article by name
     * @param name
     * @return
     */
    public Article getArticleByName(String name){
        for(Article a: listOfArticle){
            if(a.getName().equals(name))
                return a;
        }
        return null;
    }


    public ArrayList<Article> getListOfArticle(){return listOfArticle;}


}