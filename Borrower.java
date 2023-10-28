import java.util.ArrayList;
import java.util.List;

public class Borrower implements Configuration {
    private String name;
    List<Item> borrowedItems;

    public Borrower(String Name) {
        name = Name;
        borrowedItems = new ArrayList<>();
    }

    public Boolean populateBorrowList(Item item) {
        boolean check = true;
        if (!item.isBorrowed) {
            borrowedItems.add(item);
            System.out.println("Pay the cost of borowing this item:" + item.calculateCost());
            item.isBorrowed = true;
            System.out.println("Thanks for Borrowing this Item! Return as soon as possible.");
            return true;
        } else {

            System.out.println("Item has already been borrowed!");
            check = false;
            return false;
        }

        // if (check) {
        // borrowedItems.add(item);
        // item.incrementPopularityCount();
        // System.out.println("Pay the cost of borowing this item:" +
        // item.calculateCost());
        // item.isBorrowed = true;
        // }
    }

    public int calculateCost() {
        return 0;
    }

    public void display() {
        System.out.println("Borrower's Name: " + this.name + "\n The Items he/she has borrowed :\n");
        for (Item item : borrowedItems) {
            item.display();
        }
    }
}