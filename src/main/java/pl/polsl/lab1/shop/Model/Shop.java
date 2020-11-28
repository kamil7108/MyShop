package pl.polsl.lab1.shop.Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * The class representing the store
 *
 * @author  kamil_machulik
 * @version 1.1
 */
public class Shop {
    /**
     * Shop name
     */
    private String name;

    /**
     * Store warehouse is used to store goods
     */
    private List<MarkWarehouse> warehouseList;

    /**
     * Creates new shop and assigns a name to it
     *
     * @param name - shop name
     */
    public Shop(String name) {
        this.name = name;
        this.warehouseList = new ArrayList<>();
    }

    /**
     * Adds article to the shop warehouse
     *
     * @param a    article to add
     * @param mark article mark
     */
    public void addArticle(Article a, String mark) {
        if (mark != null) {
            if (!mark.isEmpty()) {
                if (!warehouseList.isEmpty()) {
                    for (MarkWarehouse warehouse : warehouseList) {
                        if (warehouse.getMark().equals(mark)) {
                            warehouse.addArticle(a);
                            break;
                        } else if (warehouseList.get(warehouseList.size() - 1).equals(warehouse)) {
                            MarkWarehouse newMarkWarehouse = new MarkWarehouse(mark);
                            newMarkWarehouse.addArticle(a);
                            warehouseList.add(newMarkWarehouse);
                            break;
                        }
                    }
                } else {
                    MarkWarehouse newMarkWarehouse = new MarkWarehouse(mark);
                    newMarkWarehouse.addArticle(a);
                    warehouseList.add(newMarkWarehouse);
                }
            }
        }
    }

    /**
     * Get all of shop articles
     *
     * @return shop articles
     */
    public DefaultListModel<Article> getAllArticles() {
        DefaultListModel<Article> model = new DefaultListModel<>();
        for (MarkWarehouse warehouse : warehouseList) {
            warehouse.getListOfArticle().stream().forEach(a -> model.addElement(a));
        }
        return model;
    }

    /**
     * Method filtered the articles by marks and filter
     * If filter is null or empty return all articles of marks
     *
     * @param filter - name or part of name that article should contains
     * @param marks  - wanted marks
     * @return - DefaultListModel with the desired articles
     */
    public DefaultListModel<Article> getFilteredListOfArticles(Optional<String> filter, List<String> marks) {
        DefaultListModel<Article> defaultModel = new DefaultListModel<>();
        if (filter != null) {
            if (!marks.isEmpty()) {
                for (String mark : marks) {
                    warehouseList.stream()
                            .filter(w -> w.getMark().equals(mark))
                            .findFirst()
                            .get()
                            .getFilteredListOfArticle(filter.get())
                            .stream()
                            .forEach(article -> defaultModel.addElement(article));

                }
            } else {
                for (String mark : getAllMarks()) {
                    warehouseList.stream()
                            .filter(w -> w.getMark().equals(mark))
                            .findFirst()
                            .get()
                            .getFilteredListOfArticle(filter.get())
                            .stream()
                            .forEach(article -> defaultModel.addElement(article));

                }
            }
        } else {
            if (!marks.isEmpty()) {
                for (String mark : marks) {
                    warehouseList.stream()
                            .filter(w -> w.getMark().equals(mark))
                            .findFirst()
                            .get()
                            .getListOfArticle()
                            .stream()
                            .forEach(article -> defaultModel.addElement(article));

                }
            } else {
                for (String mark : getAllMarks()) {
                    warehouseList.stream()
                            .filter(w -> w.getMark().equals(mark))
                            .findFirst()
                            .get()
                            .getFilteredListOfArticle(filter.get())
                            .stream()
                            .forEach(article -> defaultModel.addElement(article));

                }
            }

        }
        return defaultModel;
    }

    /**
     * Method filtered the articles by mark and filter
     *
     * @param filter - name or part of name that article should contains
     * @param mark   - mark of article
     * @return DefaultListModel with the desired articles
     */
    public DefaultListModel<Article> getFilteredListOfArticles(Optional<String> filter, String mark) {

        DefaultListModel<Article> defaultModel = new DefaultListModel<>();
        if (filter != null) {
            if (filter.equals("")) {
                warehouseList.stream()
                        .filter(w -> w.getMark().equals(mark))
                        .findFirst()
                        .get()
                        .getListOfArticle()
                        .stream()
                        .forEach(article -> defaultModel.addElement(article));
            } else {
                warehouseList.stream()
                        .filter(w -> w.getMark().equals(mark))
                        .findFirst()
                        .get()
                        .getFilteredListOfArticle(filter.get())
                        .stream()
                        .forEach(article -> defaultModel.addElement(article));
            }
        } else {
            warehouseList.stream()
                    .filter(w -> w.getMark().equals(mark))
                    .findFirst()
                    .get()
                    .getListOfArticle()
                    .stream()
                    .forEach(article -> defaultModel.addElement(article));
        }

        return defaultModel;
    }

    /**
     * @return List of all marks in shop
     */
    public List<String> getAllMarks() {
        List<String> markList = new ArrayList<>();
        warehouseList.stream().forEach(m -> markList.add(m.getMark()));
        return markList;
    }
}