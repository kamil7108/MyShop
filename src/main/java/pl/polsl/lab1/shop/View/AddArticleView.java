package pl.polsl.lab1.shop.View;

import pl.polsl.lab1.shop.Conroller.MainViewController;
import pl.polsl.lab1.shop.Conroller.SearchViewController;
import pl.polsl.lab1.shop.Model.Shop;

import javax.swing.*;

/**
 * GUI view for adding new articles
 *
 * @author kamil_machulik
 * @version 1.2
 */
public class AddArticleView {

    /**
     * Main view panel contains all view elements
     */
    public JPanel mainPanel;

    /**
     * Text field for entering the name
     */
    public JTextField textFieldName;

    /**
     * Text field for entering the name
     */
    public JTextField textFieldPrice;

    /**
     * Back to main view button
     */
    public JButton returnButton;

    /**
     * Button ,after pressing the button, the action is performed to add a new item to the store
     */
    public JButton saveArticleButton;

    /**
     * Text field for entering the description
     */
    public JTextField textFieldDescription;

    /**
     * Text field for entering the mark
     */
    public JTextField markField;



}
