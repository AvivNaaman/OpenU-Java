import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * a Tester for the class Stock
 * @author AvivN
 * @version 2020a.1
 */
public class StockTest {
    public StockTest() {}
    // dates
Date d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11;
// items
FoodItem Bread, BreadSame, Milk, Hummus, Cheese, OldCheese, Onions;
// main stock object
Stock stock = new Stock();

    String FullStockString;
    // Setup
    @Before
    public void setUp() {
        d1 = new Date(1,12,2019);
        d2 = new Date(1,12,2019);
        d3 = new Date(3,12,2019);
        d4 = new Date(3,12,2019);
        d5 = new Date(5,12,2019);
        d6 = new Date(7,12,2019);
        d7 = new Date(9,12,2019);
        d8 = new Date(11,12,2019);
        d9 = new Date(13,12,2019);
        d10 = new Date(15,12,2019);
        d11 = d10.tomorrow();
        Bread = new FoodItem("Bread", 1234, 25, d1, d8, 25, 35, 7);
        BreadSame = new FoodItem("Bread",1234,15,d1,d8,25,35,7);
        Milk = new FoodItem("Milk",4567,50,d5,d8,4,8,3);
        Hummus  = new FoodItem("Hummus",1357,20,d2,d6,5,10,8);
        Cheese = new FoodItem("Cheese", 7854, 10,d7,d9,4,9,5);
        OldCheese = new FoodItem("Cheese", 7854, 10,d1,d4,4,9,5);
        Onions = new FoodItem("Onions", 7492, 60, d3,d10,0,50,3);
    }

    /**
     * Test Stock Class
     */
    @Test
    public void testAll() {
        FullStockString = "FoodItem: Bread\tCatalogueNumber: 1234\tProductionDate: "+d1.toString()+"\tExpiryDate: "+d8.toString()+"\tQuantity: 40\n" +
                Hummus.toString() + "\n" + Milk.toString() + "\n" + Onions.toString() + "\n" + OldCheese.toString()+"\n"+Cheese.toString();

        // add 100 times
        for (int i = 0; i < 100; i++) {
            FoodItem toAdd = new FoodItem("toAdd" + i, 3000+i, 60, d3,d10,0,50,3);
            assertTrue(stock.addItem(toAdd));
        }
        assertFalse(stock.addItem(Cheese));
        stock.removeAfterDate(d11); // remove all

        // addItem
        assertTrue(stock.addItem(Bread));
        assertTrue(stock.addItem(BreadSame));
        assertTrue(stock.addItem(Milk));
        assertTrue(stock.addItem(Hummus));
        assertTrue(stock.addItem(Cheese));
        assertTrue(stock.addItem(OldCheese));
        assertTrue(stock.addItem(Onions));
        // check addItem + toString
        assertEquals(FullStockString, stock.toString());


        // check order
        String res = stock.order(21);
        assertEquals("Hummus, Cheese", res);
        assertEquals("Bread, Hummus, Milk, Onions, Cheese", stock.order(Integer.MAX_VALUE)); //order all
        assertEquals("", stock.order(Integer.MIN_VALUE));//order nothing
        assertEquals("Bread, Hummus, Cheese", stock.order(50));
        assertEquals("Bread, Hummus, Milk, Cheese", stock.order(60));
        assertEquals("Hummus, Cheese", stock.order(40));


        // check howMany
        assertEquals(150, stock.howMany(5));
        assertEquals(130, stock.howMany(4));
        assertEquals(100, stock.howMany(30));
        assertEquals(0, stock.howMany(Integer.MAX_VALUE));
        assertEquals(0, stock.howMany(Integer.MIN_VALUE));
        assertEquals(60, stock.howMany(13));
        assertEquals(80, stock.howMany(10));


        // check removeAfterDate

        /* Removes nothing*/
        stock.removeAfterDate(d1);
        assertEquals(FullStockString, stock.toString());
        stock.removeAfterDate(d3);
        assertEquals(FullStockString, stock.toString());
        stock.removeAfterDate(d4);
        assertEquals(FullStockString, stock.toString());
        /* END Removes nothing */

        stock.removeAfterDate(d5); // remove all after 07/12/2019 - removes OldCheese
        assertEquals("FoodItem: Bread\tCatalogueNumber: 1234\tProductionDate: "+d1.toString()+"\tExpiryDate: "+d8.toString()+"\tQuantity: 40\n" +
                Hummus.toString() + "\n" + Milk.toString() + "\n" + Onions.toString() +"\n"+Cheese.toString(), stock.toString());
        stock.addItem(OldCheese); // add back

        stock.removeAfterDate(d7); // also remove after 09/12/2019 - removes Hummus
        assertEquals("FoodItem: Bread\tCatalogueNumber: 1234\tProductionDate: "+d1.toString()+"\tExpiryDate: "+d8.toString()+"\tQuantity: 40\n" +
                Milk.toString() + "\n" + Onions.toString() +"\n"+Cheese.toString(), stock.toString());
        stock.addItem(Hummus);
        stock.addItem(OldCheese);

        stock.removeAfterDate(d8); // same as prev test
        assertEquals("FoodItem: Bread\tCatalogueNumber: 1234\tProductionDate: "+d1.toString()+"\tExpiryDate: "+d8.toString()+"\tQuantity: 40\n" +
                Milk.toString() + "\n" + Onions.toString() +"\n"+Cheese.toString(), stock.toString());
        stock.addItem(Hummus);
        stock.addItem(OldCheese);

        stock.removeAfterDate(d9); // bread and milk are also removed
        assertEquals( Onions.toString() +"\n"+Cheese.toString(), stock.toString());
        stock.addItem(Hummus);
        stock.addItem(OldCheese);
        stock.addItem(Bread);
        stock.addItem(BreadSame);
        stock.addItem(Milk);

        stock.removeAfterDate(d11);
        assertEquals("", stock.toString());
        // reinsert all
        stock.addItem(Bread);
        stock.addItem(BreadSame);
        stock.addItem(Milk);
        stock.addItem(Hummus);
        stock.addItem(Cheese);
        stock.addItem(OldCheese);
        stock.addItem(Onions);

        // test mostExpensive
        assertTrue(Hummus.equals(stock.mostExpensive()));

        stock.removeAfterDate(d11); //remove all
        assertTrue(stock.addItem(Bread));
        assertTrue(stock.addItem(BreadSame));
        assertTrue(stock.addItem(Milk));

        assertTrue((new FoodItem("Bread", 1234, 40, d1, d8, 25, 35, 7)).equals(stock.mostExpensive()));

        stock.removeAfterDate((d11)); // remove all
        stock.addItem(OldCheese);
        stock.addItem(Onions);
        stock.addItem(Milk);
        assertTrue(OldCheese.equals(stock.mostExpensive()));

        // test howManyPieces
        assertEquals(120, stock.howManyPieces());
        stock.addItem(Cheese);
        assertEquals(130, stock.howManyPieces());
        stock.addItem(Bread);
        stock.addItem(BreadSame);
        assertEquals(170, stock.howManyPieces());
        stock.removeAfterDate(d11); // remove all
        assertEquals(0, stock.howManyPieces());

        // test getTempOfStock
        assertEquals(Integer.MAX_VALUE, stock.getTempOfStock()); //stock ia empty => no temp found. TODO: Check For Sure!

        stock.addItem(Hummus); // 5-10
        stock.addItem(Cheese); //4-8
        stock.addItem(OldCheese); //4-8
        stock.addItem(Onions);//0-60
        assertEquals(5, stock.getTempOfStock()); //5-8=>5

        stock.removeAfterDate(d11); //remove all
        stock.addItem(Bread); // 25-35
        stock.addItem(Onions); // 0-60
        assertEquals(25, stock.getTempOfStock()); //25-35=>25

        // NOT REMOVING!
        stock.addItem(Hummus); // 5-10
        assertEquals(Integer.MAX_VALUE, stock.getTempOfStock()); // No Range! => Integer.MAX_VALUE

        // test UpdateStock
        // readd all items
        stock.addItem(BreadSame);
        stock.addItem(Milk);
        stock.addItem(Cheese);
        stock.addItem(OldCheese);
        // now: 40 bread, 50 milk, 20 hummus, 10 cheese, 10 cheese, 60 onion
        stock.updateStock(new String[] {}); // make no change
        assertEquals(FullStockString, stock.toString());


        FoodItem tempBread = new FoodItem(Bread), tempMilk = new FoodItem(Milk), tempCheese = new FoodItem(OldCheese);
        tempBread.setQuantity(40-3);
        tempMilk.setQuantity(50-1);
        tempCheese.setQuantity(10-5);
        Stock tempStock = new Stock();
        tempStock.addItem(tempBread);
        tempStock.addItem(tempMilk);
        tempStock.addItem(Hummus);
        tempStock.addItem(Cheese);
        tempStock.addItem(tempCheese);
        tempStock.addItem(Onions);

        // -3 bread, -1 milk, -5 cheese
        stock.updateStock(new String[]{"Bread", "Milk", "Cheese", "Bread", "Bread", "Cheese", "Cheese", "Cheese", "Cheese"});
        assertEquals(tempStock.toString(), stock.toString());
        // now: 37 bread, 49 milk, 20 hummus, 5 cheese, 10 cheese, 60 onion

        // -15 cheese => should be removed
        stock.updateStock(new String[]{"Cheese", "Cheese", "Cheese", "Cheese", "Cheese", "Cheese", "Cheese", "Cheese", "Cheese", "Cheese", "Cheese", "Cheese", "Cheese", "Cheese", "Cheese"});
        tempStock = new Stock();
        tempStock.addItem(tempBread);
        tempStock.addItem(tempMilk);
        tempStock.addItem(Hummus);
        tempStock.addItem(Onions);
        assertEquals(tempStock.toString(), stock.toString());
        // -20 humus => should be removed
        stock.updateStock(new String[]{"Hummus", "Hummus", "Hummus", "Hummus", "Hummus", "Hummus", "Hummus", "Hummus", "Hummus", "Hummus", "Hummus", "Hummus", "Hummus", "Hummus", "Hummus","Hummus","Hummus","Hummus","Hummus","Hummus"});
        tempStock = new Stock();
        tempStock.addItem(tempBread);
        tempStock.addItem(tempMilk);
        tempStock.addItem(Onions);
        assertEquals(tempStock.toString(), stock.toString());
        // now: 37 bread, 49 milk, 60 onion
        // should remove all - with no errors: -39 Bread, -53 Milk, -61 Onion
        stock.updateStock(new String[]{"Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Milk","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Onions","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Bread","Onions","Onions","Onions","Onions"});
        assertEquals("", stock.toString()); // now empty


        System.out.println("Success!");
    }
}