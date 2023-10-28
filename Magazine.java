
import java.util.ArrayList;
import java.util.List;

class Magazine extends Item {
    // Static variable to auto-increment the ID
    // private int id;
    // private String title;
    private int MagID = 0;
    private String publisher;
    private List<String> Authors;

    public Magazine() {
        this.Authors = new ArrayList<>();
        // this.Id = Id++;
    }

    public Magazine(String t, List<String> y, String a, int Pc, int Cost) {
        super(t, Pc, Cost);
        // Assign the next available ID and increment it
        publisher = a;
        Authors = y;
        MagID = Item.Id + 1;
        Item.Id++;
    }

    public void setTitle(String Name) {
        this.title = Name;
    }

    public void setAuthor(String Pub) {
        this.publisher = Pub;
    }

    // public void setYear(int year) {
    // this.year = year;
    // }
    public void setAuthorList(List<String> A) {
        Authors = A;
    }

    public void display() {
        System.out.println(
                "Type: Magazine " + "ID: " + MagID + " Title: " + title + " by " + publisher + " Popularity :"
                        + "Cost:" + this.cost + this.popularityCount + "\n Authors:");
        for (String au : Authors) {
            System.out.println(au);
        }
    }

    public int calculateCost() {
        int borrowCost = cost * popularityCount;
        return borrowCost;
    }

    public int returnID() {
        return MagID;
    }
}
