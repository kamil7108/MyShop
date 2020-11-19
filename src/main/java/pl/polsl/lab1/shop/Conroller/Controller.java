package pl.polsl.lab1.shop.Conroller;

import pl.polsl.lab1.shop.Exception.WrongNumberOfParams;
import pl.polsl.lab1.shop.Module.Article;
import pl.polsl.lab1.shop.Module.Shop;
import pl.polsl.lab1.shop.View.MainView;
import pl.polsl.lab1.shop.View.AddArticleView;
import pl.polsl.lab1.shop.View.SearchArticleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *  Class acts as a controller between the model and the view
 *  @author kamil_machulik
 *  @version 1.0
 */
public class Controller {

 /**
  * The constructor creates Controller and initializes all windows
  */
 private Controller(){
  initFrame();
  initMainView();
  initAddArticleView();
  initSearchArticleView();
 }

 /**
  * Submit 3 params to add new article
  * @param args args[0] - Name of article, args[1] - Price of article, args[2] - Description of article
  */
 public static void main(String [] args) throws WrongNumberOfParams {

  Controller controller= new Controller();
  controller.mockData();
  controller.takeArgs(args);
 }

 /**
  * Represent the physical object of shop
  */
 private Shop shop=new Shop("YourShop");

 /**
  * Application GUI frame
  */
 private JFrame frame;

 /**
  * GUI Main View first view of application
  */
 private MainView       mainView;

 /**
  * GUI View where you can add articles
  */
 private AddArticleView    addArticleView;

 /**
  * GUI View where you can view all articles
  */
 private SearchArticleView searchArticleView;

 /**
  * User screen width
  */
 private int widthScreen  = Toolkit.getDefaultToolkit().getScreenSize().width;

 /**
  * User screen height
  */
 private int heightScreen =Toolkit.getDefaultToolkit().getScreenSize().height;

 /**
  * The initial width of the window
  */
 private int frameWidth   = widthScreen /3;

 /**
  * The initial height of the window
  */
 private int frameHeight  = heightScreen /3;

 /**
  * X coordinate of frame
  */
 private int x            = (widthScreen -frameWidth)/2;

 /**
  * Y coordinate of frame
  */
 private int y            = (heightScreen -frameHeight)/2;

 /**
  * takeArgs takes params from main method validate them and add article to warehouse
  * @param args Required params info
  * @throws WrongNumberOfParams when user passed wrong number of info
  */
 private  void takeArgs(String [] args) throws WrongNumberOfParams {
  if(args.length==3){
   String name=args[0];
   String description=args[2];
   try{
   if(!name.equals("") && !description.equals("")){
    Double price= Double.parseDouble(args[1]);
    shop.addArticle(new Article(name,price,description));
   }
   }
   catch (Exception e){

   }
 }
  else{
   throw new WrongNumberOfParams();
  }
 }

 /**
  * Frame initialization
  */
 private  void initFrame(){
  frame =new JFrame("YourShop");
  frame.setBounds(x,y,frameWidth,frameHeight);
  frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 }

 /**
  * Mocking some extra data
  */
 private void mockData(){
  shop.addArticle(new Article("A Game of Thrones",50.0,"A Game of Thrones is the first novel in A Song of Ice and Fire, a series of fantasy novels by the American author George R. R. Martin. It was first published on August 1, 1996. The novel won the 1997 Locus Award[2] and was nominated for both the 1997 Nebula Award[2] and the 1997 World Fantasy Award.[3] The novella Blood of the Dragon, comprising the Daenerys Targaryen chapters from the novel, won the 1997 Hugo Award for Best Novella. In January 2011, the novel became a New York Times Bestseller[4] and reached No. 1 on the list in July 2011"));
  shop.addArticle(new Article("A Clash of Kings",49.99,"A Clash of Kings is the second novel in A Song of Ice and Fire, an epic fantasy series by American author George R. R. Martin expected to consist of seven volumes. It was first published on 16 November 1998 in the United Kingdom, although the first United States edition did not follow until February 2, 1999[2] Like its predecessor, A Game of Thrones, it won the Locus Award (in 1999) for Best Novel and was nominated for the Nebula Award (also in 1999) for best novel. In May 2005 Meisha Merlin released a limited edition of the novel, fully illustrated by John Howe."));
  shop.addArticle(new Article("A Storm of Swords ",39.99,"A Storm of Swords is the third of seven planned novels in A Song of Ice and Fire, a fantasy series by American author George R. R. Martin. It was first published on August 8, 2000, in the United Kingdom,[1] with a United States edition following in November 2000. Its publication was preceded by a novella called Path of the Dragon, which collects some of the Daenerys Targaryen chapters from the novel into a single book."));
 }

 /**
  * Sets properties and functionality of main view
  */
 private void initMainView(){
  mainView=new MainView();
  frame.setContentPane(mainView.panelMain);
  frame.setVisible(true);
  mainView.createNewArticleButton.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    frame.setContentPane(addArticleView.mainPanel);
    frame.setVisible(true);
   }

  });
  mainView.searchArticleButton.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    frame.setContentPane(searchArticleView.panel1);
    frame.setVisible(true);
   }
  });
 }

 /**
  * Sets properties and functionality of addArticle view
  */
 private  void initAddArticleView(){
   addArticleView = new AddArticleView();
   addArticleView.saveArticleButton.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    String name = addArticleView.textFieldName.getText();
    String description= addArticleView.textFieldDescription.getText();
    Double price;
    try {
      price= Double.parseDouble(addArticleView.textFieldPrice.getText());
      if(!name.equals("") && !description.equals("")){
      shop.addArticle(new Article(name,price,description));
      }
      else{
       JOptionPane.showMessageDialog(null, "Empty name or description", "InfoBox " , JOptionPane.INFORMATION_MESSAGE);
      }
    }
    catch (NumberFormatException exception){
     JOptionPane.showMessageDialog(null, "Write a number", "InfoBox " , JOptionPane.INFORMATION_MESSAGE);
    }
    catch (NullPointerException exception){
     JOptionPane.showMessageDialog(null, "Price wasn't write", "InfoBox " , JOptionPane.INFORMATION_MESSAGE);
    }
    addArticleView.textFieldName.setText("");
    addArticleView.textFieldDescription.setText("");
    addArticleView.textFieldPrice.setText("");
   }
  });
   addArticleView.returnButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
     frame.setContentPane(mainView.panelMain);
     frame.setVisible(true);
    }
   });
 }

 /**
  * Sets properties and functionality of searchArticle view
  */
 private void initSearchArticleView(){
  searchArticleView=new SearchArticleView();
  searchArticleView.listOfArticles.setModel(shop.getAllArticles());

  searchArticleView.listOfArticles.getSelectionModel().addListSelectionListener(e->{
   searchArticleView.textPane1.setText(searchArticleView.listOfArticles.getSelectedValue().getDescription());
  });
  searchArticleView.returnToMainPageButton.addActionListener(e -> {
   frame.setContentPane(mainView.panelMain);
   frame.setVisible(true);
  });
 }
}
