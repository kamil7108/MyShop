package pl.polsl.lab1.shop.Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * The class representing the store
 *
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
    private List<MarkWarehouse> warehouseList;

    /**
     * Creates new shop and assigns a name to it
     *
     * @param name
     */
    public Shop(String name) {
        this.name = name;
        this.warehouseList = new ArrayList<>();
        this.mockWarehouseData();
    }

    /**
     * Adds article to the shop warehouse
     *
     * @param a article to add
     */
    public void addArticle(Article a, String mark) {
        if (!warehouseList.isEmpty()) {
            for (MarkWarehouse warehouse : warehouseList) {
                if (warehouse.getMark().equals(mark)) {
                    warehouse.addArticle(a);
                    break;
                } else if (warehouseList.get(warehouseList.size() - 1).equals(warehouse)) {
                    MarkWarehouse newMarkWarehouse = new MarkWarehouse(mark);
                    newMarkWarehouse.addArticle(a);
                    warehouseList.add(newMarkWarehouse);
                }
            }
        } else {
            MarkWarehouse newMarkWarehouse = new MarkWarehouse(mark);
            newMarkWarehouse.addArticle(a);
            warehouseList.add(newMarkWarehouse);
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

    public DefaultListModel<Article> getFilteredListOfArticles(Optional<String> filter, List<String> marks) {
        DefaultListModel<Article> defaultModel = new DefaultListModel<>();
        if (filter != null) {
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
            for (String mark : marks) {
                warehouseList.stream()
                        .filter(w -> w.getMark().equals(mark))
                        .findFirst()
                        .get()
                        .getListOfArticle()
                        .stream()
                        .forEach(article -> defaultModel.addElement(article));

            }

        }
        return defaultModel;
    }

    public DefaultListModel<Article> getFilteredListOfArticles(Optional<String> filter, String mark) {

        DefaultListModel<Article> defaultModel = new DefaultListModel<>();
        if (filter != null) {
            warehouseList.stream()
                    .filter(w -> w.getMark().equals(mark))
                    .findFirst()
                    .get()
                    .getFilteredListOfArticle(filter.get())
                    .stream()
                    .forEach(article -> defaultModel.addElement(article));
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

    private void mockWarehouseData() {
        String[] marks = {"Dom Wydawniczy Muza", "Nike", "LV", "Adidas"};
        for (String mark : marks) {
            warehouseList.add(new MarkWarehouse(mark));
        }

    }

    public List<String> getAllMarks() {
        List<String> markList = new ArrayList<>();
        warehouseList.stream().forEach(m -> markList.add(m.getMark()));
        return markList;
    }
}