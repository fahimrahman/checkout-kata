import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {

    ShoppingCart shoppingCart;
    Inventory inventory;

    @BeforeEach
    void setUp() {

        inventory = new Inventory();

        inventory.addOrUpdate("A", new Item(50, 130, 3));
        inventory.addOrUpdate("B", new Item(30, 45, 2));
        inventory.addOrUpdate("C", new Item(20, 0, 0));
        inventory.addOrUpdate("D", new Item(15, 0, 0));

        shoppingCart = new ShoppingCart(inventory);

        shoppingCart.add("B");
        shoppingCart.add("A");
        shoppingCart.add("B");
        shoppingCart.add("B");
        shoppingCart.add("C");
        shoppingCart.add("C");
        shoppingCart.add("D");
    }

    @Test
    public void itemsAreAddedToCartCorrectly(){

        assertEquals(1, shoppingCart.getQuantity("A"));
        assertEquals(3, shoppingCart.getQuantity("B"));
        assertEquals(0, shoppingCart.getQuantity("Z"));
    }

    @Test
    public void itemsAreRemovedFromCartCorrectly(){

        shoppingCart.remove("B");
        shoppingCart.remove("B");
        assertEquals(1, shoppingCart.getQuantity("B"));

        shoppingCart.remove("D");
        shoppingCart.remove("D");
        assertEquals(0, shoppingCart.getQuantity("D"));
    }

    @Test
    public void totalCostEmptyCart(){

        ShoppingCart emptyShoppingCart = new ShoppingCart(inventory);
        assertEquals(0, emptyShoppingCart.calculateCheckout());
    }

    @Test
    public void totalCostWithoutSpecialPrice(){

        Inventory inventoryNoDiscounts = new Inventory();
        inventoryNoDiscounts.addOrUpdate("E", new Item(10, 0, 0));
        inventoryNoDiscounts.addOrUpdate("F", new Item(60, 0, 0));

        ShoppingCart shoppingCart = new ShoppingCart(inventoryNoDiscounts);
        shoppingCart.add("E");
        shoppingCart.add("E");
        shoppingCart.add("F");

        assertEquals(80, shoppingCart.calculateCheckout());
    }

    @Test
    public void totalCostWithSpecialPrice(){

        Inventory inventoryWithDiscounts = new Inventory();

        inventoryWithDiscounts.addOrUpdate("A", new Item(50, 130, 3));
        inventoryWithDiscounts.addOrUpdate("B", new Item(30, 45, 2));
        inventoryWithDiscounts.addOrUpdate("C", new Item(20, 0, 0));
        inventoryWithDiscounts.addOrUpdate("D", new Item(15, 0, 0));

        shoppingCart = new ShoppingCart(inventoryWithDiscounts);

        // 4 B's + 1 A's + 1 C's + 1 D's
        shoppingCart.add("B");
        shoppingCart.add("A");
        shoppingCart.add("B");
        shoppingCart.add("B");
        shoppingCart.add("B");
        shoppingCart.add("C");
        shoppingCart.add("D");

        assertEquals(175, shoppingCart.calculateCheckout());
    }

    @Test
    public void totalCostPricingRuleChange(){

        Inventory inventoryWithDiscounts = new Inventory();

        inventoryWithDiscounts.addOrUpdate("A", new Item(50, 130, 3));
        inventoryWithDiscounts.addOrUpdate("B", new Item(30, 45, 2));
        inventoryWithDiscounts.addOrUpdate("C", new Item(20, 0, 0));
        inventoryWithDiscounts.addOrUpdate("D", new Item(15, 0, 0));

        shoppingCart = new ShoppingCart(inventoryWithDiscounts);

        // 4 B's + 1 A's + 1 C's + 2 D's
        shoppingCart.add("B");
        shoppingCart.add("A");
        shoppingCart.add("B");
        shoppingCart.add("B");
        shoppingCart.add("B");
        shoppingCart.add("C");
        shoppingCart.add("D");
        shoppingCart.add("D");

        //Remove B's offer and add an offer for D
        inventoryWithDiscounts.addOrUpdate("B", new Item(30, 0, 0));
        inventoryWithDiscounts.addOrUpdate("D", new Item(15, 20, 2));

        assertEquals(210, shoppingCart.calculateCheckout());

    }
}
