package pl.polsl.lab1.shop.Module;

/**
 * Class represent a physical object sold in shop
 * @author kamil
 */
public class Article {
    public Article(String name,double price,String description){
        id=ACTUAL_LAST_ID;
        ACTUAL_LAST_ID++;
        this.name=name;
        this.price=price;
        this.description=description;

    }
    /**
     * Static parameter represent actual last id od article
     */
    private static int ACTUAL_LAST_ID=0;
    /**
     * Unique product id
     */
    private int id;
    /**
     * Name of article
     */
    private String name;
    /**
     * Price of article
     */
    private double price;
    /**
     * Description of article
     */
    private String description;

    /**
     * Get name of article
     * @return
     */
    public String getName(){return name;}
    /**
     * Get price of article
     * @return
     */
    public double getPrice(){return price;}
    /**
     * Get description of article
     * @return
     */
    public String getDescription(){return description;}
    /**
     * Get id of article
     * @return
     */
    public int getId(){return id;}

    /**
     * Set name of article
     * @param name name to set
     */
    public void setName(String name){this.name=name;}
    /**
     * Set price of article
     * @param price price to set
     */
    public void setPrice(double price){this.price=price;}
    /**
     * Set description of article
     * @param description description to set
     */
    public void setDescription(String description){this.description=description;}

}