import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    // sku vs Quantity
    private HashMap<String, Integer> cartItems ;
    private Inventory inventory;

    public ShoppingCart(Inventory inventory) {
        this.cartItems = new HashMap<>();
        this.inventory = inventory;
    }

    public void add(String item) {
        cartItems.merge(item, 1, Integer::sum);
    }

    public void remove(String item) {
        if (cartItems.containsKey(item)){
            int qty = cartItems.get(item);
            if (qty == 1)
                cartItems.remove(item);
            else {
                qty--;
                cartItems.put(item, qty);
            }
        }
    }

    public int getQuantity(String item) {
        return cartItems.getOrDefault(item, 0);
    }

    public int calculateCheckout() {
        int total = 0;
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {

            String sku = entry.getKey();
            int quantity = entry.getValue();
            Item item = inventory.getItem(sku);

            if (item==null) {
                System.out.println("An unknown item ("+ sku +") with no inventory has been added to the cart! Skipping product..");
                continue;
            }

            int price = item.getPrice();
            int specialPrice = item.getSpecialPrice();
            int specialQuantity = item.getSpecialQuantity();

            //check for offers
            if (specialPrice > 0 && specialQuantity > 0){
                int multiBuyQty = quantity / specialQuantity;
                int remainingQty = quantity % specialQuantity;
                total += multiBuyQty * specialPrice + remainingQty * price;

            } else {
                total += price * quantity;
            }

        }

        System.out.println("Total checkout price: £" + String.format("%.2f", total/100.0));

        return total;
    }
}
