package pl.polsl.lab1.shop.Conroller;

import pl.polsl.lab1.shop.Model.Shop;
import pl.polsl.lab1.shop.Model.User;
import pl.polsl.lab1.shop.View.SearchArticleView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 * Searching view controller defines the behavior of the window searchArticleView
 *
 * @author kamil_machulik
 * @version 1.0
 */
public class SearchViewController {
    /**
     * SearchViewController constructor
     */
    public SearchViewController() {
        searchArticleView = new SearchArticleView();
    }

    /**
     * Get SearchArticleView panel
     *
     * @return JPanel
     */
    public JPanel getSearchArticleViewPanel() {
        return searchArticleView.panel1;
    }

    /**
     * GUI View where you can view all articles
     */
    private SearchArticleView searchArticleView;

    /**
     * Update JList data model
     * Sets the model to all articles
     *
     * @param shop - reference to shop object
     */
    public void updateSearchControllerJList(Shop shop) {
        searchArticleView.listOfArticles.setModel(shop.getAllArticles());
        searchArticleView.listOfArticles.updateUI();
    }

    /**
     * Add new mark to markBox
     *
     * @param shop - reference to shop object
     */
    public void addNewMark(Shop shop) {
        searchArticleView.markBox.addItem(shop.getAllMarks().get(shop.getAllMarks().size() - 1));
    }

    /**
     * Sets properties and functionality of searchArticle view
     *
     * @param frame - program frame
     * @param shop - reference to shop object
     * @param mainViewController - controller of main view
     * @param cartViewController - controller of cart view
     * @param user - current user
     */
    public void initSearchArticleView(JFrame frame, Shop shop, MainViewController mainViewController, CartViewController cartViewController, User user) {
        searchArticleView.markBox.addItem("All");
        for (String mark : shop.getAllMarks()) {
            searchArticleView.markBox.addItem(mark);
        }

        searchArticleView.markBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                searchArticle(shop);
            }
        });
        searchArticleView.listOfArticles.setModel(shop.getAllArticles());

        searchArticleView.searchTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                searchArticle(shop);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchArticle(shop);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        searchArticleView.listOfArticles.getSelectionModel().addListSelectionListener(e -> {
            try {
                searchArticleView.textPane1.setText(searchArticleView.listOfArticles.getSelectedValue().getDescription());
            } catch (Exception ex) {
                searchArticleView.textPane1.setText("");
            }
        });

        searchArticleView.returnToMainPageButton.addActionListener(e -> {
            frame.setContentPane(mainViewController.getMainViewPanel());
            frame.setVisible(true);
        });

        searchArticleView.viewCartButton.addActionListener(e -> {
            frame.setContentPane(cartViewController.getCartViewPanel());
            cartViewController.updateTable(user);
            frame.setVisible(true);
        });

        searchArticleView.addTheItemToButton.addActionListener(e -> {
            user.addArticleToBasket(searchArticleView.listOfArticles.getSelectedValue(), 1);
        });
    }

    /**
     * Check that arctInfo is not empty and it is not part of word Search
     *
     * @param artcInfo word to check
     * @param shop - reference to shop object
     * @return true when artcInfo is not a part of Search and is not empty otherwise false
     */
    private boolean arcticInfoIsWrote(String artcInfo, Shop shop) {
        String search = "Search";

        if (artcInfo.isEmpty()) {
            updateSearchControllerJList(shop);
            return false;
        }
        return !search.contains(artcInfo);
    }


    /**
     * Method takes texts form searchTextField and markBox
     * selected item and choose articles that meet the conditions
     *
     * @param shop - reference to shop object
     */
    private void searchArticle(Shop shop) {
        String artcInfo = searchArticleView.searchTextField.getText();
        String mark = (String) searchArticleView.markBox.getSelectedItem();

        if (arcticInfoIsWrote(artcInfo, shop) && !mark.equals((String) searchArticleView.markBox.getItemAt(0))) {
            searchArticleView.listOfArticles.setModel(shop.getFilteredListOfArticles(java.util.Optional.of(artcInfo), mark));
        } else if (arcticInfoIsWrote(artcInfo, shop) && mark.equals((String) searchArticleView.markBox.getItemAt(0))) {
            searchArticleView.listOfArticles.setModel(shop.getFilteredListOfArticles(java.util.Optional.of(artcInfo), shop.getAllMarks()));
        } else if (!arcticInfoIsWrote(artcInfo, shop) && !mark.equals((String) searchArticleView.markBox.getItemAt(0))) {
            searchArticleView.listOfArticles.setModel(shop.getFilteredListOfArticles(null, mark));
        } else if (!arcticInfoIsWrote(artcInfo, shop) && mark.equals((String) searchArticleView.markBox.getItemAt(0))) {
            searchArticleView.listOfArticles.setModel(shop.getFilteredListOfArticles(null, shop.getAllMarks()));
        }

        searchArticleView.listOfArticles.updateUI();

    }
}
