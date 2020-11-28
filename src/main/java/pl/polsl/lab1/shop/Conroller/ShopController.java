package pl.polsl.lab1.shop.Conroller;

import pl.polsl.lab1.shop.Exception.WrongNumberOfParams;
import pl.polsl.lab1.shop.Model.Article;
import pl.polsl.lab1.shop.Model.Shop;
import pl.polsl.lab1.shop.Model.User;
import pl.polsl.lab1.shop.Model.UserRepo;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Class acts as a controller between the model and the view
 *
 * @author kamil_machulik
 * @version 1.0
 */
public class ShopController {

    /**
     * The constructor creates Controller and initializes all windows
     */
    private ShopController() {
        mockData();
    }

    /**
     * Submit 2 parmas to log in
     *
     * @param args args[0] - User nickname, args[1] - User password
     * @throws WrongNumberOfParams -when args length not 2
     */
    public static void main(String[] args) throws WrongNumberOfParams {
        ShopController shopController = new ShopController();
        shopController.takeArgs(args);
    }

    /**
     * Current user
     */
    private User user;

    /**
     * User repository
     */
    private final UserRepo userRepo = new UserRepo();

    /**
     * Represent the physical object of shop
     */
    private final Shop shop = new Shop("YourShop");

    /**
     * SearchArticleView controller
     */
    private SearchViewController searchViewController;

    /**
     * AddArticleView controller
     */
    private AddViewController addViewController;

    /**
     * MainView controller
     */
    private MainViewController mainViewController;

    /**
     *
     */
    private CartViewController cartViewController;
    /**
     * Application GUI frame
     */
    private JFrame             frame;

    /**
     * User screen width
     */
    private final int widthScreen = Toolkit.getDefaultToolkit().getScreenSize().width;

    /**
     * User screen height
     */
    private final int heightScreen = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**
     * The initial width of the window
     */
    private final int frameWidth = widthScreen / 3;

    /**
     * The initial height of the window
     */
    private final int frameHeight = heightScreen / 3;

    /**
     * X coordinate of frame
     */
    private final int x = (widthScreen - frameWidth) / 2;

    /**
     * Y coordinate of frame
     */
    private final int y = (heightScreen - frameHeight) / 2;

    /**
     * takeArgs takes params from main method validate them and log in to warehouse
     *
     * @param args Required params info
     * @throws WrongNumberOfParams when user passed wrong number of info
     */
    private void takeArgs(String[] args) throws WrongNumberOfParams {
        if (args.length == 2) {
            user = userRepo.logIn(args[0], args[1]);
            if (user != null)
                initProgram();
        } else {
            throw new WrongNumberOfParams();
        }
    }

    /**
     * Frame initialization
     */
    private void initFrame() {
        frame = new JFrame("YourShop");
        frame.setBounds(x, y, frameWidth, frameHeight);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Mocking some extra data
     */
    private void mockData() {
        shop.addArticle(new Article("A Game of Thrones", 50.00, "A Game of Thrones is the first novel in A Song of Ice and Fire, a series of fantasy novels by the American author George R. R. Martin. It was first published on August 1, 1996. The novel won the 1997 Locus Award[2] and was nominated for both the 1997 Nebula Award[2] and the 1997 World Fantasy Award.[3] The novella Blood of the Dragon, comprising the Daenerys Targaryen chapters from the novel, won the 1997 Hugo Award for Best Novella. In January 2011, the novel became a New York Times Bestseller[4] and reached No. 1 on the list in July 2011"), "Dom Wydawniczy Muza");
        shop.addArticle(new Article("A Clash of Kings", 49.99, "A Clash of Kings is the second novel in A Song of Ice and Fire, an epic fantasy series by American author George R. R. Martin expected to consist of seven volumes. It was first published on 16 November 1998 in the United Kingdom, although the first United States edition did not follow until February 2, 1999[2] Like its predecessor, A Game of Thrones, it won the Locus Award (in 1999) for Best Novel and was nominated for the Nebula Award (also in 1999) for best novel. In May 2005 Meisha Merlin released a limited edition of the novel, fully illustrated by John Howe."), "Dom Wydawniczy Muza");
        shop.addArticle(new Article("A Storm of Swords ", 39.99, "A Storm of Swords is the third of seven planned novels in A Song of Ice and Fire, a fantasy series by American author George R. R. Martin. It was first published on August 8, 2000, in the United Kingdom,[1] with a United States edition following in November 2000. Its publication was preceded by a novella called Path of the Dragon, which collects some of the Daenerys Targaryen chapters from the novel into a single book."), "Dom Wydawniczy Muza");
        shop.addArticle(new Article("EDGE LUX 4 SHOES", 99.00, "Sometimes life is so busy you feel like you're living out of your gym bag. Who wants to carry three pairs of shoes in there? These adidas shoes will take you from an early-morning run around the reservoir to an evening HIIT class and keep you stylish and comfortable for everything that happens in between."), "Adidas");
        shop.addArticle(new Article("ULTRABOOST DNA SHOES", 180.00, "Celebrate game-changing adidas running technologies that makes every step more comfortable. These adidas Ultraboost DNA Shoes have a colorful knit upper that honors the already legendary technology. Responsive cushioning gives you endless energy on city streets."), "Adidas");
        shop.addArticle(new Article("NIKE SHOES", 100.00, "Celebrate game-changing adidas running technologies that makes every step more comfortable. These adidas Ultraboost DNA Shoes have a colorful knit upper that honors the already legendary technology. Responsive cushioning gives you endless energy on city streets."), "Nike");
        shop.addArticle(new Article("HYPERBOOST SHOES", 1000.00, "Already a legend five years in, the adidas Ultraboost gets a colorful update. Pink equals powerful. Embrace the color that represents a drive for unity and meaningful change. The famously responsive cushioning has a bright hue that matches a stretchy knit upper that's made with recycled materials. Stitched-in reinforcement right where you need it keeps you supported and running at your best."), "Nike");
        shop.addArticle(new Article("Suitcase LV ", 10000.00, "Best suitcase"), "LV");
        userRepo.addUser("test", "test");
    }

    private void initProgram() {
        initFrame();
        addViewController = new AddViewController();
        searchViewController = new SearchViewController();
        mainViewController = new MainViewController();
        cartViewController = new CartViewController();
        addViewController.initAddArticleView(frame, shop, searchViewController, mainViewController);
        mainViewController.initMainView(frame, addViewController, searchViewController);
        searchViewController.initSearchArticleView(frame, shop, mainViewController, cartViewController, user);
        cartViewController.initCartView(frame, searchViewController, user);
    }

}
