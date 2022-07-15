import java.util.HashMap;

public class Inventory {

    private HashMap<String, Item> inventoryMap;

    public Inventory() {
        this.inventoryMap =  new HashMap<>();
    }

    public void addOrUpdate(String sku, Item item){
        this.inventoryMap.put(sku, item);
    }

    public Item getItem(String sku){
        return inventoryMap.get(sku);
    }

}
