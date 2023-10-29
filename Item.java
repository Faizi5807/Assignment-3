interface Configuration {
    public void display();

    public int calculateCost();
}

public class Item implements Configuration {
    public String title;
    protected boolean isBorrowed;
    protected int cost;
    public static int Id;
    public int popularityCount;
    public String Content;

    public Item() {
        // Id = Id++;
    }

    public Item(String t, int pC, int c) {
        title = t;
        cost = c;
        popularityCount = pC;
    }

    public void setTitle(String Name) {
        this.title = Name;
    }

    public void setCost(int price) {
        this.cost = price;
    }

    public void setAuthor(String author) {
        System.out.println("Set Author function of Item class");
    }

    public void setYear(int year) {
        System.out.println("setyear function of Item class");
    }

    public void display() {
        System.out.println("ID: " + Id + " Title: " + title + " cost: " + cost + "Popularity" + popularityCount + ")");

    }

    public int calculateCost() {
        return this.cost;
    }

    int returnID() {
        return Id;
    }

    void incrementPopularityCount() {
        this.popularityCount++;
    }

    public String getAuthor() {
        return "l";
    }

    public int getYear() {
        return 2;
    }
}