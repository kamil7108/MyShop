package pl.polsl.lab1.shop.Conroller;

import pl.polsl.lab1.shop.View.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MainView controller defines the behavior of the window MainView
 *
 * @author kamil_machulik
 * @version 1.0
 */
public class MainViewController {

    /**
     * MainView Controller  constructor
     */
    public MainViewController(){
        mainView=new MainView();
    }

    /**
     * Get MainView JPanel
     * @return JPanel
     */
    public JPanel getMainViewPanel() {
        return mainView.panelMain;
    }

    /**
     * GUI Main View first view of application
     */
    private MainView mainView;

    /**
     * Sets properties and functionality of main view
     * @param frame program frame
     * @param addViewController controller of add article view
     * @param searchViewController controller of search article view
     */
    public void initMainView(JFrame frame,AddViewController addViewController,SearchViewController searchViewController) {
        frame.setContentPane(mainView.panelMain);
        frame.setVisible(true);
        mainView.createNewArticleButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(addViewController.getAddArticleViewPanel());
                frame.setVisible(true);
            }

        });
        mainView.searchArticleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(searchViewController.getSearchArticleViewPanel());
                frame.setVisible(true);
            }
        });
    }
}
