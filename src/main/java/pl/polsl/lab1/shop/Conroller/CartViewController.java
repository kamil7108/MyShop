package pl.polsl.lab1.shop.Conroller;

import pl.polsl.lab1.shop.Model.Article;
import pl.polsl.lab1.shop.Model.User;
import pl.polsl.lab1.shop.View.CartView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Map;

/**
 * CartViewController defines behaviour of cart view and its functionality
 *
 * @author kamil_machulik
 * @version 1.0
 */
public class CartViewController {
    /**
     * CartViewController constructor
     */
    public CartViewController() {
        cartView = new CartView();
    }

    /**
     * Get CartView main panel
     *
     * @return JPanel
     */
    public JPanel getCartViewPanel() {
        return cartView.panel1;
    }

    /**
     * Gui cart view
     */
    private CartView cartView;

    /**
     * Sets properties and functionality of cart view
     *
     * @param jFrame               - program frame
     * @param searchViewController -searchView controller
     * @param user                 - current user
     */
    public void initCartView(JFrame jFrame, SearchViewController searchViewController, User user) {
        cartView.label.setText("Your shopping cart " + user.getNickname());
        cartView.returnButton.addActionListener(e -> {
            jFrame.setContentPane(searchViewController.getSearchArticleViewPanel());
        });
    }

    /**
     * Update table data
     *
     * @param currentUser current user
     */
    public void updateTable(User currentUser) {

        UpdateDataInterface updateDataInterface = user -> {
            String[] columnNames = {"Article", "Quantity", "Price"};
            int size = user.getShopping().size();
            Object[][] data = new Object[size][3];
            int i = 0;
            for (Map.Entry<Article, Integer> entry : user.getShopping().entrySet()) {
                Article article = entry.getKey();
                Integer integer = entry.getValue();
                data[i][0] = article.getName();
                data[i][1] = integer;
                data[i][2] = article.getPrice();
                i++;
            }
            DefaultTableModel model = new DefaultTableModel(data,
                    columnNames);
            cartView.table1.setModel(model);
            cartView.table1.updateUI();
        };

        updateDataInterface.updateData(currentUser);

    }
}
