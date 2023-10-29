import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

public class LibraryGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private List<Item> Items;

    private void createAndShowPopularityChart() {
        PopularityChartScreen popularityChartScreen = new PopularityChartScreen(Items);
    }

    public LibraryGUI() {
        setTitle("Library Management System");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Library library = new Library();
        library.readItem("types.txt");
        Items = library.Items;

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Authors/Publisher");
        tableModel.addColumn("Publication Date/Year");
        tableModel.addColumn("Cost");

        tableModel.addColumn("Read Data");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        addButton = new JButton("Add Item");
        editButton = new JButton("Edit Item");
        deleteButton = new JButton("Delete Item");
        JButton readButton = new JButton("Read");
        JButton viewPopularityButton = new JButton("View Popularity");
        viewPopularityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowPopularityChart();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewPopularityButton);

        buttonPanel.add(readButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editBook();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
        readButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                readBook();
            }

        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    table.setRowSelectionInterval(row, row);
                    table.setSelectionBackground(Color.GREEN);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    table.clearSelection();
                }
            }
        });

        displayLibraryItems();

        setVisible(true);
    };

    public void saveLibraryData() {
        try (PrintWriter writer = new PrintWriter("dest.txt")) {
            for (Item libraryItem : Items) {
                String ItemString = libraryItem.toString();

                writer.println(ItemString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayLibraryItems() {
        for (Item libraryItem : Items) {

            tableModel.addRow(new Object[] {
                    Item.Id, libraryItem.title, libraryItem.getAuthor(),
                    libraryItem.getYear(), libraryItem.calculateCost()
            });
        }

    }

    private void addBook() {
        JDialog addDialog = new JDialog(this, "Add Item", true);
        addDialog.setLayout(new GridLayout(5, 2));

        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField costField = new JTextField();

        addDialog.add(new JLabel("Title:"));
        addDialog.add(titleField);
        addDialog.add(new JLabel("Authors/Publisher:"));
        addDialog.add(authorField);
        addDialog.add(new JLabel("Publication Date/Year:"));
        addDialog.add(yearField);
        addDialog.add(new JLabel("Cost:"));
        addDialog.add(costField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                int year = Integer.parseInt(yearField.getText());
                int cost = Integer.parseInt(costField.getText());

                Item newBook = new Book(title, author, 2, year, cost);

                Items.add(newBook);
                if (newBook instanceof Book) {
                    tableModel.addRow(new Object[] { Item.Id, newBook.title, newBook.getAuthor(), newBook.getYear(),
                            newBook.calculateCost() });
                }
                addDialog.dispose();
            }
        });

        addDialog.add(addButton);
        addDialog.pack();
        addDialog.setVisible(true);
        saveLibraryData();
    }

    private void editBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a library Item to edit.");
            return;
        }

        Item selectedItem = Items.get(selectedRow);

        JDialog editDialog = new JDialog(this, "Edit Item", true);
        editDialog.setLayout(new GridLayout(5, 2));

        JTextField titleField = new JTextField(selectedItem.title);
        JTextField authorField = new JTextField(selectedItem.getAuthor());
        JTextField yearField = new JTextField(selectedItem.getYear());
        JTextField costField = new JTextField(String.valueOf(selectedItem.calculateCost()));

        editDialog.add(new JLabel("Title:"));
        editDialog.add(titleField);
        editDialog.add(new JLabel("Authors/Publisher:"));
        editDialog.add(authorField);
        editDialog.add(new JLabel("Publication Date/Year:"));
        editDialog.add(yearField);
        editDialog.add(new JLabel("Cost:"));
        editDialog.add(costField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newTitle = titleField.getText();
                String newAuthor = authorField.getText();
                int newYear = Integer.parseInt(yearField.getText());
                double newCost = Double.parseDouble(costField.getText());

                selectedItem.setTitle(newTitle);
                selectedItem.setAuthor(newAuthor);
                selectedItem.setYear(newYear);

                tableModel.setValueAt(newTitle, selectedRow, 1);
                tableModel.setValueAt(newAuthor, selectedRow, 2);
                tableModel.setValueAt(newYear, selectedRow, 3);

                editDialog.dispose();
            }
        });

        editDialog.add(saveButton);
        editDialog.pack();
        editDialog.setVisible(true);
        saveLibraryData();
    }

    // private void readBook() {
    // int selecRow = table.getSelectedRow();
    // if (selecRow == -1) {
    // JOptionPane.showMessageDialog(this, "Please select a library Item to Read.");
    // return;
    // }
    // Item selectedItem = Items.get(selecRow);
    // if (selectedItem instanceof Book) {
    // JTextArea ContentField = new JTextArea(selectedItem.Content);
    // ContentField.setSize(800, 600);
    // ContentField.setEditable(false);
    // JScrollPane scrollPane = new JScrollPane(ContentField);
    // JDialog editDialog = new JDialog(this, "Read Item", false);
    // editDialog.setLayout(new BorderLayout());
    // editDialog.add(new JLabel("Content:"));
    // editDialog.add(ContentField);
    // editDialog.pack();
    // editDialog.setVisible(true);
    // // saveLibraryData();
    // }

    // }
    private void readBook() {
        int selecRow = table.getSelectedRow();
        if (selecRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a library Item to Read.");
            return;
        }
        Item selectedItem = Items.get(selecRow);
        if (selectedItem instanceof Book) {
            JTextArea ContentField = new JTextArea(selectedItem.Content);
            ContentField.setWrapStyleWord(true); // Wrap words
            ContentField.setLineWrap(true); // Wrap lines
            ContentField.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(ContentField);

            // Set the preferred size of the ContentField
            ContentField.setPreferredSize(new Dimension(800, 600));

            JDialog editDialog = new JDialog(this, "Read Item", false);
            editDialog.setLayout(new BorderLayout());
            editDialog.add(new JLabel("Content:"), BorderLayout.NORTH);
            editDialog.add(scrollPane, BorderLayout.CENTER);

            editDialog.pack();
            editDialog.setVisible(true);
            // saveLibraryData();
        }
    }

    private void deleteBook() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a library Item to delete.");
            return;
        }

        Items.remove(selectedRow);

        tableModel.removeRow(selectedRow);
        saveLibraryData();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibraryGUI();
            }
        });
    }
}