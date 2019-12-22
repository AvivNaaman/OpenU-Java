/**
 * a class representing a one FoodItem of stock.
 * @version 2020a
 * @author Aviv Naaman
 */
public class FoodItem
{
    // Default item name
    private static String DEFAULT_NAME = "item";
    // Minimum/Maximum catalogue number value
    private static long MAX_CATALOGUE_VALUE = 9999;
    private static long MIN_CATALOGUE_VALUE = 1000;
    // Default catalogue number
    private static int DEFAULT_CATALOGUE = 9999;
    // Default quantity
    private static int DEFAULT_QUANTITY = 0;
    // Minimum quantity
    private static int MIN_QUANTITY = 0;
    // Minimum price
    private static int MIN_PRICE = 0;
    // Default Price
    private static int DEFAULT_PRICE = 1;
    
    /* Properties */
    private String name;
    private long catalogueNumber;
    private int quantity;
    private Date productionDate;
    private Date expiryDate;
    private int minTemperature;
    private int maxTemperature;
    private int price;
    
    /* Constructors */
    /**
     * creates a new FoodItem object
     * @param name name of food item
     * @param catalogueNumber catalogue number of food item
     * @param quantity quantity of food item
     * @param productionDate production date
     * @param expiryDate expiry date
     * @param minTemperature minimum storage temperature
     * @param maxTemperature maximum storage temperature
     * @param price unit price
     */
    public FoodItem(String name, long catalogueNumber,
                    int quantity, Date productionDate, 
                    Date expiryDate, int minTemperature,
                    int maxTemperature, int price)
    {
        // if string's empty, set name to "item"
        if (name.length() < 1)
            this.name = DEFAULT_NAME;
        else
            this.name = name;
            
        // if cat number isn't in range, set it to 9999
        if (catalogueNumber < MIN_CATALOGUE_VALUE || catalogueNumber > MAX_CATALOGUE_VALUE)
            this.catalogueNumber = DEFAULT_CATALOGUE;
        else
            this.catalogueNumber = catalogueNumber;
            
        // if qunatity's below minimum (=below 0), set it to default.
        if (quantity < MIN_QUANTITY)
            this.quantity = DEFAULT_QUANTITY;
        else
            this.quantity = quantity;
            
        // if price isn't above minimum, set to default
        if (price <= MIN_PRICE)
            this.price = DEFAULT_PRICE;
        else
            this.price = price;
        
        // set production date
        this.productionDate = new Date(productionDate);
        
        Date expiryToSet = expiryDate;
        // if given expiry date before production date, set the expiry to a day after production
        if (expiryDate.before(productionDate)) {
            expiryToSet = productionDate.tomorrow();
        }
        this.expiryDate = new Date(expiryToSet);
        
        // if given max temperature above given min temperature, then switch between them.
        if(minTemperature > maxTemperature)
        {
            this.minTemperature = maxTemperature;
            this.maxTemperature = minTemperature;
        }
        else 
        {
            this.minTemperature = minTemperature;
            this.maxTemperature = maxTemperature;
        }
    }
    
    /**
     * copy constructor
     * @param other the food item to be copied
     */
    public FoodItem(FoodItem other)
    {
        // Copy constructor: copy each instance varriable to the new instance.
        this.name = other.getName();
        this.catalogueNumber = other.getCatalogueNumber();
        this.quantity = other.getQuantity();
        this.price = other.getPrice();
        this.productionDate = other.getProductionDate();
        this.expiryDate = other.getExpiryDate();
        this.minTemperature = other.getMinTemperature();
        this.maxTemperature = other.getMaxTemperature();
    }
    
    /* Getters */
    /**
     * gets the name
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * gets the catalogue number
     * @return the catalogue number
     */
    public long getCatalogueNumber()
    {
        return this.catalogueNumber;
    }
    
    /**
     * gets the quantity
     * @return the quantity
     */
    public int getQuantity()
    {
        return this.quantity;
    }
    
    /**
     * gets the unit price
     * @return the unit price
     */
    public int getPrice()
    {
        return this.price;
    }
    
    /**
     * gets the production date
     * @return the production date
     */
    public Date getProductionDate()
    {
        return new Date(this.productionDate);
    }
    
    /**
     * gets the expiry date
     * @return the expiry date
     */
    public Date getExpiryDate()
    {
        return new Date(this.expiryDate);
    }
    
    /**
     * gets the minimum storage temperature
     * @return the minimum storage temperature
     */
    public int getMinTemperature()
    {
        return this.minTemperature;
    }
    
    /**
     * gets the maximum storage temperature
     * @return the maximum storage temperature
     */
    public int getMaxTemperature()
    {
        return this.maxTemperature;
    }
    
    /* Setters */
    /**
     * set the quantity (only if not negative)
     * @param n the quantity value to be set
     */
    public void setQuantity(int n)
    {
        // check if not below minimum, and set
        if (n >= MIN_QUANTITY) 
            this.quantity = n;
    }
    
    /**
     * set the expiry date (only if not before production date )
     * @param d expiry date value to be set
     */
    public void setExpiryDate(Date d)
    {
        // make sure new expiry date is after production before set
        if (!d.before(this.productionDate))
            this.expiryDate = new Date(d);
    }
    
    /**
     * set the production date (only if not after expiry date)
     * @param n the quantity value to be set.
     */
    public void setProductionDate(Date d)
    {
        // make sure before set new production date isn't before expiry.
        if (!d.after(this.expiryDate))
            this.productionDate = new Date(d);
    }
    
    /**
     * set the price (only if positive)
     * @param n price value to be set
     */
    public void setPrice(int n)
    {
        // check if minimum price not below or equals minimum
        if (n > MIN_PRICE)
            this.price = n;
    }
    
    /* Methods */
    /**
     * check if 2 food items are the same (excluding the quantity values)
     * @param other the food item to compare this food item to
     * @return true if the items are the same
     */
    public boolean equals(FoodItem other)
    {
        // check for each property whether it equals 
        // the same property in the other instance or not, except quantity.
        return this.name.equals(other.getName()) &&
                this.catalogueNumber == other.getCatalogueNumber() &&
                this.price == other.getPrice() &&
                this.minTemperature == other.getMinTemperature() &&
                this.maxTemperature == other.getMaxTemperature() &&
                this.productionDate.equals(other.getProductionDate()) &&
                this.expiryDate.equals(other.getExpiryDate());
    }
    
    /**
     * check if this food item is fresh on the date d
     * @param d date to check
     * @return true if this food item is fresh on the date d
     */
    public boolean isFresh(Date d)
    {
        // check if d is between production date and expiry date
        return !d.after(this.expiryDate) 
            && !d.before(this.productionDate);
    }
    
    /**
     * returns a String that represents this food item
     * @return String that represents this food item in the following format:
     * FoodItem: milk CatalogueNumber: 1234 ProductionDate: 14/12/2019 ExpiryDate: 21/12/2019 Quantity: 3
     */
    public String toString()
    {
        // return formatted: each parameter and it's value, with tab between
        return "FoodItem: " + this.name + 
            "\tCatalogueNumber: " + this.catalogueNumber +
            "\tProductionDate: " + this.productionDate +
            "\tExpiryDate: " + this.expiryDate +
            "\tQuantity: " + this.quantity;
    }
    
    /**
     * check if this food item is older than other food item
     * @param other food item to compare this food item to
     * @return true if this food item is older than other date
     */
    public boolean olderFoodItem(FoodItem other){
        // compare dates to check who produced before.
        return this.productionDate.before(other.getProductionDate());
    }
    
    
    /**
     * returns the number of units of products that can be purchased for a given amount
     * @param amount amount to purchase
     * @return the number of units can be purchased
     */
    public int howManyItems(int amount)
    {
        // calculate how much can buy without price limitation
        int howMuchCanBuy = amount / this.price;
        // Find the limitation for buying: quantity or money.
        return Math.min(howMuchCanBuy, this.quantity);
    }
    
    /**
     * check if this food item is cheaper than other food item
     * @param other food item to compare this food item to
     * @return true if this food item is cheaper than other date
     */
    public boolean isCheaper(FoodItem other)
    {
        // Just return whether this price is lower than other = means it's cheaper
        return this.price < other.getPrice();
    }
}
