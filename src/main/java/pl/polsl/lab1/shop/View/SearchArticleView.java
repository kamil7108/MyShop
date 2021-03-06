package pl.polsl.lab1.shop.View;

import pl.polsl.lab1.shop.Model.Article;

import javax.swing.*;

/**
 * GUI view for searching and viewing article properties
 *
 * @author kamil_machulik
 * @version 1.2
 */
public class SearchArticleView {

    /**
     * Main view panel contains all view elements
     */
    public JPanel panel1;

    /**
     * JList shows all shop articles
     */
    public JList<Article> listOfArticles;

    /**
     * Text pane shows selected item description
     */
    public JTextPane textPane1;

    /**
     * Back to main view button
     */
    public JButton    returnToMainPageButton;

    /**
     * Text field for entering the searched article
     */
    public JTextField searchTextField;

    /**
     * Combo box of articles marks
     */
    public JComboBox markBox;
    public JButton addTheItemToButton;
    public JButton viewCartButton;

}
