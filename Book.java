public class Book extends Item {
    // Static variable to auto-increment the ID
    // private int id;
    // private String title;
    private int BookID = 0;
    public String author;
    public int year;

    public Book() {
        // this.Id = Id++;
    }

    public Book(String t, String a, int y, int Pc, int cost) {
        super(t, Pc, cost);
        // Assign the next available ID and increment it
        author = a;
        year = y;
        BookID = Item.Id + 1;
        Item.Id++;
        Content = "This is My Book content";
    }

    public void setTitle(String Name) {
        this.title = Name;
    }

    public void setAuthor(String uthor) {
        this.author = uthor;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int calculateCost() {
        int borrowCost = cost + (20 / 100 * (cost))
                + 200;
        return borrowCost;
    }

    public void display() {
        System.out.println("Type: Book " + "ID: " + BookID + " Title: " + title + " by " + author + " (" + year + ")"
                + "Cost:" + this.cost + " Popularity:" + this.popularityCount);
    }

    public int returnID() {
        return BookID;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getYear() {
        return this.year;
    }
}