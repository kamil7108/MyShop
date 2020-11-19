package pl.polsl.lab1.shop.View;

import pl.polsl.lab1.shop.Module.Article;

import javax.swing.*;

/**
 * GUI view for searching and viewing article properties
 *  @author kamil_machulik
 *  @version 1.0
 */
public class SearchArticleView {

    /**
     * Main view panel contains all view elements
     */
    public JPanel           panel1;

    /**
     * JList shows all shop articles
     */
    public JList <Article>listOfArticles;

    /**
     * Text pane shows selected item description
     */
    public JTextPane textPane1;

    /**
     *  Back to main view button
     */
    public JButton  returnToMainPageButton;

}
