
/**
 * a Class representing a stor's food stock, which each item in it is a FoodItem object.
 *
 * @author Aviv Naaman
 * @version 2020a
 */
public class Stock
{
    /* Constants */
    // maximum size of stock
    private final int MAX_STOCK_SIZE = 100;
    
    /* Instance variables */
    // the array which stores the FoodItems in the stock
    private FoodItem[] _stock;
    // the number of items in the stock.
    private int _noOfItems;
    
    /* Constructors */
    /**
     * Creates a new empty Stock object, with a size of 100 items.
     */
    public Stock() {
        // initialize _stock array in max size
        _stock = new FoodItem[MAX_STOCK_SIZE];
        // set _noOfItem to 0, because we have no items yet
        _noOfItems = 0;
    }
    
    /* Getters */
    /**
     * returns the amount of items in the stock.
     * @return the item amount in the stock
     */
    public int getNumOfItems() {
        return this._noOfItems;
    }
    
    /* Methods */
    /**
     * Adds a FoodItem object to the stock, and returns whether operation succeeded (if true)
     * if there is already an item in the stock with
     * same name, catalogue number and prod./expiry dates, then their quantities are summed up, and operation succeeded,
     * otherwise, adds only if there's more space left in the array (not more than 100 items),
     * and returns whether operation succeeded or not (whether place left)
     * @param newItem The FoodItem to add to the stock. Quantites of items are summed if they're same name, catNumber and dates, or addded if space left.
     * @return whether added successfully - if item's not same, and there's place left in the array.
     */
    public boolean addItem(FoodItem newItem) {
        // find index of same item if exists
        int index = findIndexInStock(newItem);
        // if doesn't exist, add in the last clear space, which is the
        if (index < 0) {
            // if there's no space in the array, stop and return false.
            if (this._noOfItems == MAX_STOCK_SIZE)
                return false;
                
            addAtIndex(findIndexToAddNew(newItem.getCatalogueNumber()), newItem);
        }
        else {
            // if exists, increase quantity
            FoodItem existItem = this._stock[index];
            int oldQuantity = existItem.getQuantity();
            // add to old item the quantity
            existItem.setQuantity(oldQuantity+newItem.getQuantity());
        }
        // and return true
        return true;
    }
    
    /**
     * returns string with the names of items which their quantity is less than the 
     * parameter quantity (quantity is summed up if both names and catalogue numbers are same, and copmared afterwards)
     * @param amount The minimum quantity of item that doesn't need to be ordered (won't be returned in the list)
     * @return a string containing the names of items which their quantities are less than the amount parameter.
     */
    public String order(int amount) {
        if (this._noOfItems < 1) {
            return ""; // return empty if nothing to check for
        }
        // Thestring to return
        String resultString = "";
        // stores the previous items' sum of quantitys
        int prevQuantity = 0;
        FoodItem prevItem = null;
        FoodItem currentItem = null;
        // for each item in stock, and an extra loop to check the last
        for (int i = 0; i <= this._noOfItems; i++) {
            if (i != this._noOfItems) {
                 currentItem = this._stock[i];
            }
            if (i > 0) {
                // if not same item as previous OR after last item (means we're checking for last item)
                if (i == this._noOfItems || (currentItem.getName() != prevItem.getName() ||
                    currentItem.getCatalogueNumber() != prevItem.getCatalogueNumber())) {
                        // if the total amount of all sames is less than parameter amount
                        if (prevQuantity < amount) {
                            // add comma and space if nothing have been added yet
                            if (!(resultString.length() == 0)) {
                                resultString+=", ";
                            }
                            // add name to string
                            resultString += prevItem.getName();
                        }
                        // reset quantity, because the next to be checked (means, the current) is different
                        prevQuantity = 0;
                    }
            }
            // sum the quantities - have already resetted if last or different
            prevQuantity += currentItem.getQuantity();
            // anyway, set the next loop prev item to this loop current.
            prevItem = currentItem;
        }
        return resultString;
    }
    
    /**
     * Returns the total quantities of all the items which can be stored at the temperature in the parameter temp.
     * @param temp the temperature which items should be checked and summed if the parameter in their temperature range 
     * @return the total quantity of items which are storable in the temperature
     */
    public int howMany(int temp) {
        // initial quantity is 0
        int totalQuantity = 0;
        // for each item
        for (int i = 0; i < this._noOfItems; i++) {
            FoodItem currentItem = this._stock[i];
            // if refridgerator temperature is in the item's valid temperature range
            if (temp >= currentItem.getMinTemperature() &&
                temp <= currentItem.getMaxTemperature()) {
                // sum the quantity of the item with previous quantitys sum.
                totalQuantity += currentItem.getQuantity();
            }
        }
        // and return the total quantity
        return totalQuantity;
    }
    
    /**
     * removes all items in stock which are expired in the d date parameter (their expiry date is before the parameter d).
     * @param d the date which all the items with expiry dates after it should be removed.
     */
    public void removeAfterDate(Date d) {
        for (int i = 0; i < this._noOfItems; i++) {
            FoodItem currentItem = this._stock[i];
            // if item's expiry date before the date param
                if (currentItem.getExpiryDate().before(d)) {
                    // remove it from the stock
                    removeAtIndex(i);
                    // because an item was removed from [i], we'd like to stay in same indexer
                    // (because all items are "shifted" back in the array)
                    i--;
                }
        }
    }
    
    /**
     * Returns the most expensive item in stock - the item which costs the most.
     * @return the item which costs the most.
     */
    public FoodItem mostExpensive() {
        FoodItem mostExpensive = null;
        for (int i = 0; i < this._noOfItems; i++) {
            FoodItem currentItem = this._stock[i];
            // if not first, compare the most expensive yet to current,
            // and change the most expensive if current's price is grater
            if (i != 0) {
                // if price is bugger than all tested yet
                if (currentItem.getPrice() > mostExpensive.getPrice()) {
                    // set it as biggest yet
                    mostExpensive = new FoodItem(currentItem); // avoid aliasing
                }
            }
            else 
                mostExpensive = new FoodItem(currentItem); //avoid aliasing
        }
        // return biggest
        return mostExpensive;
    }
    
    /**
     * Returns the total quantity of stock items (total pieces, not item number)
     * @return the total pieces in stock
     */
    public int howManyPieces() {
        int totalPieces = 0;
        // for each item in stock
        for (int i = 0; i < this._noOfItems; i++) {
            // add the quantity of the item to the sum
            totalPieces += this._stock[i].getQuantity();
        }
        // and return the total of quantities
        return totalPieces;
    }
    
    /**
     * returns a string respresents the whole stock, in the format: in each line one item,
     * with it's prameters name, catalogueNumber, prod./expiry dates and quantity seperated by tab, for example:<br />
     * FoodItem: name   CatalogueNumber: 1234   ProductionDate: 01/01/2000  ExpiryDate: 01/01/2000  Quantity: 1 <br />
     * FoodItem: anotherName    catalogueNumber: 5678   ProductionDate: 01/01/2000  ExpiryDate: 01/01/2000  Quantity: 2
     * @return a string of each item in stock, formatted in each line other string of FoodItem in stock
     */
    public String toString() {
        String result = "";
        // for each item in stock
        for (int i = 0; i < this._noOfItems; i++) {
            // add \n (line down) for prev. item if it's not the first item
            if (i > 0)
                result += "\n";
            // add it's toString result to the final result string
            result += this._stock[i].toString();
        }
        return result;
    }
    
    /** 
     * Decreases the quantity of first item found by it's name, each name is a seperated String in the array.
     * If the quantity after decreasing is less than 1, the item is completly removed from Stock array.
     * @param itemsList the list of items to decrease their amount from the stock, and remove if quantity is 0.
     */
    public void updateStock(String[] itemsList) {
        // for each item name in list
        for (int i = 0; i < itemsList.length; i++) {
            int firstFoundIndex = findIndexByName(itemsList[i]);
            // if such item was found
            if (firstFoundIndex > -1) {
                FoodItem firstFoundItem = this._stock[firstFoundIndex];
                int firstFoundQuantity = firstFoundItem.getQuantity();
                // if 1 piece left, remove the item because quantity should be 0 - so it's out of stock;
                if (firstFoundQuantity == 1) {
                    removeAtIndex(firstFoundIndex);
                }
                else {
                    // otherwise, decrease the quantity by 1.
                    firstFoundItem.setQuantity(firstFoundQuantity-1);
                }
            }
            // otherwise just ignore
        }
    }
    
    /**
     * returns the minimal temperature which all the items in stock can be stored in.
     * if there's no such temperature the value Integer.MAX_VALUE will be returned.
     * @return the minimal temperature that all the items can be stored in, if no such one, returns Integer.MAX_VALUE
     */
    public int getTempOfStock() {
        // store the biggest min temp. and smallest max temp.
        int smallestMaxTemp = 0, biggestMinTemp = Integer.MAX_VALUE;
        // for each item in stock
        for (int i = 0; i < this._noOfItems; i++) {
            FoodItem currentItem = this._stock[i];
            // if first, don't compare at all.
            if (i == 0) {
                smallestMaxTemp = currentItem.getMaxTemperature();
                biggestMinTemp = currentItem.getMinTemperature();
            }
            else {
                // check and set the smallest max and biggest min:
                smallestMaxTemp = Math.min(currentItem.getMaxTemperature(), smallestMaxTemp);
                biggestMinTemp = Math.max(currentItem.getMinTemperature(), biggestMinTemp);
                // check if there's no value between both values:
                if ((smallestMaxTemp - biggestMinTemp) < 0)
                    // so the values are invalid, there's no range between so we'll return Integer.MAX_VALUE authout continuing.
                    return Integer.MAX_VALUE;
            }
        }
        return biggestMinTemp;
    }
    
    /* Private Methods */
    // returns the index of item if it's already in stock, or -1 if isn't in stock.
    private int findIndexInStock(FoodItem itemToCheck) {
        // for each item in stock
        for (int i = 0; i < this._noOfItems; i++) {
            // if they're the same
            if (isSameFoodItem(this._stock[i], itemToCheck)) {
                // return current index.
                return i;
            }
        }
        return -1;
    }
    
    // checks if FoodItem is same for Stock:
    // if name, expiry/production dates and catalogueNumbers are the same
    private boolean isSameFoodItem(FoodItem item, FoodItem other) {
        // return equality of:
        return item.getName().equals(other.getName()) && // names
                item.getCatalogueNumber() == other.getCatalogueNumber() && // catalogue numbers
                item.getExpiryDate().equals(other.getExpiryDate()) && // expiry dates
                item.getProductionDate().equals(other.getProductionDate()); // production dates
    }
    
    // inserts the item in the array at a specific index, preserving (shifting) the others after it
    private void addAtIndex(int index, FoodItem item) {
        // store the "pushed" forward item
        FoodItem toMoveNext = this._stock[index];
        // and set the new item in index
        this._stock[index] = new FoodItem(item);
        
        // "push" forward in the array each item, until no more items to push.
        for (int i = index+1; i < _noOfItems+1; i++) {
            FoodItem tempToMoveNext = _stock[i];
            _stock[i] = toMoveNext;
            toMoveNext = tempToMoveNext;
        }
        
        // we added a new item for _stock, so now increase the noOfItem:
        this._noOfItems++;
    }
    
    // removes the item at the index, and shifts all items after back to "fill the hole"
    private void removeAtIndex(int index) {
        // save the last item
        FoodItem toMoveBack = this._stock[this._noOfItems-1];
        // make it null
        this._stock[this._noOfItems-1] = null;
        // for each item until the index to remove from:
        for (int i = this._noOfItems-2; i > index-1; i--) {
            // save the last moved back temporarily
            FoodItem tempToMoveBack = toMoveBack;
            // assign to the next iteration item to move back
            toMoveBack = this._stock[i];
            // move back the item for current iteration
            this._stock[i] = tempToMoveBack;
        }
        // we removed an item - so let's decrease the item counter.
        this._noOfItems--;
    }
    
    // returns first index of item in the array which it's name is the parameter name.
    // if not found it returns -1.
    private int findIndexByName(String name) {
        // for each item in stock, while item var isn unassigned:
        for (int i = 0; i < this._noOfItems; i++) {
            // if name's the same, return current index - firs place where the same name was found
            if (this._stock[i].getName().equals(name))
                return i;
        }
        // if not found, return -1
        return -1;
    }
    
    // find the index to insert a new item:
    // where the catalogueNumber of existing item is grater then the current cataloguNumber
    private int findIndexToAddNew(long catalogueNumber) {
        // foreach item in the array
        for (int i = 0; i<this._noOfItems;i++) {
            // if catalogue number smaller or equals, it's the place to insert at
            if(catalogueNumber<=this._stock[i].getCatalogueNumber())
                return i;
        }
        // if it's the greatest (no smaller or equal) return the last filles index + 1= _noOfItems.
        return this._noOfItems;
    }
}
