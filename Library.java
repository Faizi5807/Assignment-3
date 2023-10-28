import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;

public class Library {
    public List<Item> Items;
    public List<Borrower> Borrowers;

    Library() {
        this.Items = new ArrayList<>();
        this.Borrowers = new ArrayList<>();
    }

    public void AddItem(Item item) {
        Items.add(item);
    }

    public void Additem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the type of item (Book, Newspaper, Magazine):");
        String itemType = scanner.nextLine();

        System.out.println("Enter title:");
        String title = scanner.nextLine();

        System.out.println("Enter cost:");
        int cost = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (itemType.toLowerCase()) {
            case "book":
                System.out.println("Enter author:");
                String author = scanner.nextLine();

                System.out.println("Enter year:");
                int year = scanner.nextInt();
                System.out.println("Enter Popularity Count:");
                int Pc = scanner.nextInt();
                Book book = new Book(title, author, year, Pc, cost);
                // System.out.println("Book created: " + book.toString());
                this.AddItem(book);
                break;

            case "newspaper":
                System.out.println("Enter publisher name:");
                String newspaperPublisher = scanner.nextLine();

                System.out.println("Enter date:");
                String date = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate newsDate = LocalDate.parse(date, formatter);
                // LocalDate newsDate = LocalDate.parse(date);
                System.out.println("Enter Popularity Count:");
                int Pc2 = scanner.nextInt();

                Newspaper newspaper = new Newspaper(title, newspaperPublisher, newsDate, Pc2);
                // System.out.println("Newspaper created: " + newspaper.toString());
                this.AddItem(newspaper);
                break;

            case "magazine":
                System.out.println("Enter publisher name:");
                String magazinePublisher = scanner.nextLine();

                System.out.println("Enter authors (comma-separated):");
                String authorsInput = scanner.nextLine();
                List<String> magazineAuthors = new ArrayList<>();
                String[] authorNames = authorsInput.split(",");
                for (String authorName : authorNames) {
                    magazineAuthors.add(authorName.trim());
                }
                System.out.println("Enter Popularity Count:");
                int Pc3 = scanner.nextInt();

                Magazine magazine = new Magazine(title, magazineAuthors, magazinePublisher, Pc3, cost);
                // System.out.println("Magazine created: " + magazine.toString());
                this.AddItem(magazine);
                break;

            default:
                System.out.println("Invalid item type.");
        }
    }

    public void readItem(String Filename) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(Filename));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                String Part1 = parts[0].trim();

                if (Part1.equals("1")) {
                    String Part2 = parts[1].trim();
                    String Part3 = parts[2].trim();
                    String Part4 = parts[3].trim();
                    String Part5 = parts[4].trim();
                    String Part6 = parts[5].trim();
                    int part4 = Integer.parseInt(Part4);
                    int part5 = Integer.parseInt(Part5);
                    int part6 = Integer.parseInt(Part6);
                    Book newBook = new Book(Part2, Part3, part4, part5, part6);
                    this.AddItem(newBook);
                } else if (Part1.equals("3")) {
                    String Part2 = parts[1].trim();
                    String Part3 = parts[2].trim();
                    String Part4 = parts[3].trim();
                    String Part5 = parts[4].trim();
                    int part4 = Integer.parseInt(Part4);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(Part5, formatter);
                    Newspaper newPaper = new Newspaper(Part2, Part3, date, part4);
                    this.AddItem(newPaper);
                } else if (Part1.equals("2")) {
                    String Part2 = parts[1];
                    List<String> Authorlist = new ArrayList<>();
                    int start = 2;
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].endsWith(".")) {
                            Authorlist.add(parts[i].substring(0, parts[i].length() - 1));
                            start = i + 1;
                            break;
                        }
                        Authorlist.add(parts[i]);
                    }
                    String Part3 = parts[start];
                    int Part4 = Integer.parseInt(parts[start + 1]);
                    int Part5 = Integer.parseInt(parts[start + 2]);
                    Magazine newMag = new Magazine(Part2, Authorlist, Part3, Part4, Part5);
                    this.AddItem(newMag);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public void RemoveItem(int ID) {
        if (Items.isEmpty()) {
            System.out.println("Library has no book");
        } else {
            for (Item item : Items) {
                if (item instanceof Book) {
                    Book myBook = (Book) item;
                    if (myBook.returnID() == ID) {
                        Items.remove(item);
                        System.out.println("Book has been removed from Library");
                        return;
                    }
                } else if (item instanceof Magazine) {
                    Magazine myMag = (Magazine) item;
                    if (myMag.returnID() == ID) {
                        Items.remove(item);
                        System.out.println("Magazine has been removed from Library");
                        return;
                    }
                } else if (item instanceof Newspaper) {
                    Newspaper MyPaper = (Newspaper) item;
                    if (MyPaper.returnID() == ID) {
                        Items.remove(item);
                        System.out.println("Book has been removed from Library");
                        return;
                    }
                } else {
                    System.out.println("No item with this ID found");
                    return;
                }
            }
        }
    }

    public void EditItem(int ID) {
        Scanner scanner = new Scanner(System.in);
        // System.out.println("Enter Item Type:");
        // String type = input.nextLine();
        if (!Items.isEmpty()) {
            for (Item item : Items) {
                if (item instanceof Book) {
                    Book myBook = (Book) item;
                    if (myBook.returnID() == ID) {
                        System.out.println("Enter title:");
                        String title = scanner.nextLine();
                        myBook.setTitle(title);
                        System.out.println("Enter cost:");
                        int cost = scanner.nextInt();
                        myBook.setCost(cost);
                        System.out.println("Enter author:");
                        String author = scanner.nextLine();
                        myBook.setAuthor(author);
                        System.out.println("Enter year:");
                        int year = scanner.nextInt();
                        myBook.setYear(year);
                        System.out.println("Enter Popularity Count:");
                        int Pc = scanner.nextInt();
                        myBook.popularityCount = Pc;
                        System.out.println("Book Details Updated!");
                        return;
                    }
                } else if (item instanceof Magazine) {
                    Magazine mag = (Magazine) item;
                    if (mag.returnID() == ID) {
                        System.out.println("Enter title:");
                        String title = scanner.nextLine();
                        mag.setTitle(title);
                        System.out.println("Enter cost:");
                        int cost = scanner.nextInt();
                        mag.setCost(cost);
                        System.out.println("Enter publisher name:");
                        String magazinePublisher = scanner.nextLine();
                        mag.setAuthor(magazinePublisher);
                        System.out.println("Enter authors (comma-separated):");
                        String authorsInput = scanner.nextLine();
                        List<String> magazineAuthors = new ArrayList<>();
                        String[] authorNames = authorsInput.split(",");
                        for (String authorName : authorNames) {
                            magazineAuthors.add(authorName.trim());
                        }
                        mag.setAuthorList(magazineAuthors);
                        System.out.println("Enter Popularity Count:");
                        int Pc3 = scanner.nextInt();
                        mag.popularityCount = Pc3;
                        System.out.println("Magazine Details Updated!");
                        return;
                    }

                } else if (item instanceof Newspaper) {
                    Newspaper pap = (Newspaper) item;
                    if (pap.returnID() == ID) {
                        System.out.println("Enter title:");
                        String title = scanner.nextLine();
                        pap.setTitle(title);
                        System.out.println("Enter cost:");
                        int cost = scanner.nextInt();
                        pap.setCost(cost);
                        System.out.println("Enter publisher name:");
                        String newspaperPublisher = scanner.nextLine();
                        pap.setAuthor(newspaperPublisher);
                        System.out.println("Enter date:");
                        String date = scanner.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate newsDate = LocalDate.parse(date, formatter);
                        pap.setYear(newsDate);
                        // LocalDate newsDate = LocalDate.parse(date);
                        System.out.println("Enter Popularity Count:");
                        int Pc2 = scanner.nextInt();
                        pap.popularityCount = Pc2;
                        System.out.println("Newspaper Details Updated!");
                        return;
                    }
                } else {
                    System.out.println("No item with this ID found");
                    return;
                }
            }
        } else {
            System.out.println("Items List is Empty!");
        }
    }

    public void ViewItems() {
        if (!Items.isEmpty()) {
            for (Item item : Items) {
                if (item instanceof Book) {
                    displayItem(((Book) item));

                } else if (item instanceof Magazine) {
                    displayItem(((Magazine) item));

                } else if (item instanceof Newspaper) {
                    displayItem(((Newspaper) item));

                }
            }
            return;
        } else {
            System.out.println("Library is Empty!");
            return;
        }
    }

    public void viewBookbyID(int ID) {
        if (!Items.isEmpty()) {
            for (Item item : Items) {
                if (item instanceof Book) {
                    if (((Book) item).returnID() == ID) {
                        ((Book) item).display();
                        return;
                    }
                } else if (item instanceof Magazine) {
                    if (((Magazine) item).returnID() == ID) {
                        ((Magazine) item).display();
                        return;
                    }
                } else if (item instanceof Newspaper) {
                    if (((Newspaper) item).returnID() == ID) {
                        ((Newspaper) item).display();
                        return;
                    }
                }
            }
        } else {
            System.out.println("Libray is Empty!");
            return;
        }
    }

    public void displayItem(Item item) {
        item.display(); // Invokes the display fucntion of child class i.e Polymorphism in Java
    }
    // public static List<Item> loadItemsFromFile(String filename) throws
    // IOException {
    // List<Item> items = new ArrayList<>();

    // try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
    // String line;
    // while ((line = reader.readLine()) != null) {
    // String[] parts = line.split(",");
    // if (parts.length == 5) {
    // int type = Integer.parseInt(parts[0].trim());
    // String title = parts[1].trim();
    // double cost = Double.parseDouble(parts[2].trim());
    // String author = parts[3].trim();
    // int year = Integer.parseInt(parts[4].trim());

    // //Item item = new Item(title, cost, author, year, type);
    // // items.add(item);
    // }
    // }
    // }

    // return items;
    // }
    void BorrowItem(String type, int Iid) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your Name:");
        String Bname = in.nextLine();
        Borrower borrower = new Borrower(Bname);
        boolean check = false;

        if (type.toLowerCase().equals("book")) {
            for (Item item : Items) {
                if (item instanceof Book) {
                    Book book = (Book) item;
                    if (book.returnID() == Iid) {
                        if (borrower.populateBorrowList(book)) {
                            book.popularityCount++;
                            check = true;
                            Borrowers.add(borrower);
                        }
                        return;
                    }
                }
            }
        } else if (type.toLowerCase().equals("magazine")) {
            for (Item item : Items) {
                if (item instanceof Magazine) {
                    Magazine magazine = (Magazine) item;
                    if (magazine.returnID() == Iid) {
                        if (borrower.populateBorrowList(magazine)) {
                            magazine.popularityCount++;
                            check = true;
                            Borrowers.add(borrower);
                        }
                        return;
                    }
                }
            }
        } else if (type.toLowerCase().equals("newspaper")) {
            for (Item item : Items) {
                if (item instanceof Newspaper) {
                    Newspaper newspaper = (Newspaper) item;
                    if (newspaper.returnID() == Iid) {
                        if (borrower.populateBorrowList(newspaper)) {
                            newspaper.popularityCount++;
                            check = true;
                            Borrowers.add(borrower);
                        }
                        return;
                    }
                }
            }
        } else {
            System.out.println("Wrong Type!");
            return;
        }

        if (!check) {
            System.out.println("Item with this Id doesn't exist");
            return;
        }
    }

    void DisplayBorrowers() {
        if (!Borrowers.isEmpty()) {
            System.out.println("This is the List of Borrowers:");
            for (Borrower Bor : Borrowers) {
                Bor.display();
            }
            return;
        } else {
            System.out.println("Borrowers List is empty !");
            return;
        }
    }

    public void HotPicks() {
        // Create a copy of the itemList to avoid modifying the original list
        List<Item> HotPicks = new ArrayList<>(Items);

        // Define a custom Comparator to sort items by popularity count in descending
        // order
        Comparator<Item> popularityComparator = new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                // Sort in descending order (item2 to item1)
                return Integer.compare(item2.popularityCount, item1.popularityCount);
            }
        };

        // Sort the hotPicksList using the custom comparator
        Collections.sort(HotPicks, popularityComparator);
        for (Item sortedItems : HotPicks) {
            System.out.println(sortedItems.title + ": " + sortedItems.popularityCount);
        }
        return;

        // return hotPicksList;
    }

    public void hotPicks() {
        List<Item> HotPicks;
        HotPicks = new ArrayList<>();
        for (Item allitems : Items) {
            HotPicks.add(allitems);
        }
        Comparator<Item> PopCom = new Comparator<Item>() {
            public int compare(Item item1, Item item2) {
                return Integer.compare(item2.popularityCount, item1.popularityCount);
            }
        };
        Collections.sort(HotPicks, PopCom);
        for (Item sortedItems : HotPicks) {
            System.out.println(sortedItems.title + ": " + sortedItems.popularityCount);
        }
        return;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 9) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Hot Picks!");
            System.out.println("2. Borrow an Item");
            System.out.println("3. Add an Item");
            System.out.println("4. Remove an Item");
            System.out.println("5. View all Items");
            System.out.println("6. Edit an item");
            System.out.println("7. View Borrowers List");
            System.out.println("8. View item by ID");
            System.out.println("9. Exit");
            System.out.print("Enter your choice (1-9): ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {

                case 1:
                    // AddBook(null);
                    HotPicks();
                    break;
                case 2:
                    // System.out.print("Enter the ID of the book you want to view: ");
                    // int bookID = scanner.nextInt();
                    // this.RemoveItem(bookID);
                    System.out.println("This is the list of Available Items:\n");
                    this.ViewItems();
                    System.out.println("Enter Type of item you want to borrow: ");
                    String type = scanner.nextLine();
                    System.out.println("Enter ID of Item you want to borrow :");
                    int iID = scanner.nextInt();
                    this.BorrowItem(type, iID);
                    break;
                case 3:
                    // View all Items
                    this.Additem();
                    break;
                case 4:
                    System.out.print("Enter the ID of the book you want to view: ");
                    int bkID = scanner.nextInt();
                    RemoveItem(bkID);
                    break;
                case 5:
                    // View book by ID
                    // System.out.print("Enter the ID of the book you want to view: ");
                    // int bokID = scanner.nextInt();
                    // viewBookbyID(bokID);
                    this.ViewItems();
                    break;
                case 6:
                    System.out.print("Enter the ID of the item you want to Edit: ");
                    int itemID = scanner.nextInt();
                    this.EditItem(itemID);
                    break;
                case 7:
                    DisplayBorrowers();
                    break;
                case 8:
                    System.out.print("Enter the ID of the item you want to View: ");
                    int itemId = scanner.nextInt();
                    this.viewBookbyID(itemId);
                    break;

                case 9:
                    System.out.println("Exiting the Program!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option (1-9).");
            }
        }

        scanner.close();
    }
}