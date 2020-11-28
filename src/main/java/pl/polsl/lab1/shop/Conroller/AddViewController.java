package pl.polsl.lab1.shop.Conroller;

import pl.polsl.lab1.shop.Model.Article;
import pl.polsl.lab1.shop.Model.Shop;
import pl.polsl.lab1.shop.View.AddArticleView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * AddView controller defines the behavior of the window addArticleView
 *
 * @author kamil_machulik
 * @version 1.0
 */
public class AddViewController {

    /**
     * AddView Controller Constructor
     */
    public AddViewController() {
        addArticleView = new AddArticleView();
    }

    /**
     * Get addArticleView panel
     *
     * @return JPanel
     */
    public JPanel getAddArticleViewPanel() {
        return addArticleView.mainPanel;
    }

    /**
     * GUI View where you can add articles
     */
    private AddArticleView addArticleView;

    /**
     * Sets properties and functionality of addArticle view
     * @param jFrame program frame
     * @param shop shop
     * @param searchViewController controller of search article view
     * @param mainViewController controller of main view
     */
    public void initAddArticleView(JFrame jFrame, Shop shop, SearchViewController searchViewController, MainViewController mainViewController) {
        addArticleView.saveArticleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = addArticleView.textFieldName.getText();
                String description = addArticleView.textFieldDescription.getText();
                String mark = addArticleView.markField.getText();
                Double price;
                try {
                    price = Double.parseDouble(addArticleView.textFieldPrice.getText());
                    if (!name.equals("") && !description.equals("") && !mark.equals("")) {
                        int previousItemCount=shop.getAllMarks().size();
                        shop.addArticle(new Article(name, price, description), mark);
                        int currentItemCount=shop.getAllMarks().size();

                        if(currentItemCount!=previousItemCount)
                            searchViewController.addNewMark(shop);

                        searchViewController.updateSearchControllerJList(shop);
                    } else {
                        JOptionPane.showMessageDialog(null, "Empty name or description", "InfoBox ", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Write a number", "InfoBox ", JOptionPane.INFORMATION_MESSAGE);
                } catch (NullPointerException exception) {
                    JOptionPane.showMessageDialog(null, "Price wasn't write", "InfoBox ", JOptionPane.INFORMATION_MESSAGE);
                }
                addArticleView.textFieldName.setText("");
                addArticleView.textFieldDescription.setText("");
                addArticleView.textFieldPrice.setText("");
                addArticleView.markField.setText("");
            }
        });
        addArticleView.returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setContentPane(mainViewController.getMainViewPanel());
                jFrame.setVisible(true);
            }
        });
    }

}
