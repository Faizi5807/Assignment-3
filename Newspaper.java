
import java.time.LocalDate;
import java.time.temporal.ChronoField;
// class Date {
//     private int day;
//     private int month;
//     private int year;

//     public Date(int d, int m, int y) {
//         day = d;
//         month = m;
//         year = y;
//     }

//     public String Display() {
//         return (day + "-" + month + "-" + year);
//     }

// }

class Newspaper extends Item {
    // Static variable to auto-increment the ID
    // private int id;
    // private String title;
    private int PaperID = 0;
    private String publisher;
    private LocalDate date;

    public Newspaper() {
        // this.Id = Id++;
    }

    public Newspaper(String t, String a, LocalDate y, int Pc) {
        super(t, Pc, 15);
        // Assign the next available ID and increment it
        publisher = a;
        date = y;
        PaperID = Item.Id + 1;
        Item.Id++;
        Content = "This is My Newspaper content";
    }

    public void setTitle(String Name) {
        this.title = Name;
    }

    public void setAuthor(String Pub) {
        this.publisher = Pub;
    }

    public void setYear(LocalDate year) {
        this.date = year;
    }

    public void display() {
        // String S = date.Display();
        System.out
                .println("Type: Newspaper " + "ID: " + PaperID + " Title: " + title + " by " + publisher + " on "
                        + date.get(ChronoField.DAY_OF_MONTH) + "-" + date.get(ChronoField.MONTH_OF_YEAR) + "-"
                        + date.get(ChronoField.YEAR) + " Popularity :" + this.popularityCount + ".");
    }

    public int calculateCost() {
        return 15;
    }

    public int returnID() {
        return PaperID;
    }
}
