
// import javax.swing.*;
// import java.awt.*;
// import java.util.List;

// public class PopularityChartGraphics extends JFrame {
//     private List<Item> Items;

//     public PopularityChartGraphics(List<Item> Items) {
//         this.Items = Items;
//         setTitle("Popularity Bar Chart");
//         setSize(800, 600);
//         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//         setLocationRelativeTo(null);
//         add(new PopularityChartPanel());
//         setVisible(true);
//     }

//     class PopularityChartPanel extends JPanel {
//         @Override
//         protected void paintComponent(Graphics g) {
//             super.paintComponent(g);

//             int totalPopularity = 0;
//             for (Item libraryItem : Items) {
//                 totalPopularity += libraryItem.popularityCount;
//             }

//             int x = 50;
//             int y = 400; // Starting point for bars at the bottom
//             int barWidth = 30;
//             int spaceBetweenBars = 20;

//             for (Item libraryItem : Items) {
//                 int itemPopularity = libraryItem.popularityCount;
//                 int barHeight = (int) (itemPopularity / (double) totalPopularity * 300);

//                 // Draw the bar
//                 g.setColor(Color.BLUE);
//                 g.fillRect(x, y - barHeight, barWidth, barHeight);

//                 // Label the item title below the bar
//                 g.setColor(Color.BLACK);
//                 g.drawString(libraryItem.title, x - 10, y + 20);

//                 // Display popularity count above the bar
//                 g.setColor(Color.BLACK);
//                 g.drawString(String.valueOf(itemPopularity), x - 10, y - barHeight - 5);

//                 // Update x position for the next bar
//                 x += barWidth + spaceBetweenBars;
//             }
//         }
//     }

// }
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PopularityChartGraphics extends JFrame {
    private List<Item> Items;

    public PopularityChartGraphics(List<Item> Items) {
        this.Items = Items;
        setTitle("Popularity Bar Chart");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new PopularityChartPanel());
        setVisible(true);
    }

    class PopularityChartPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int totalPopularity = 0;
            for (Item libraryItem : Items) {
                totalPopularity += libraryItem.popularityCount;
            }

            int x = 50;
            int y = 400; // Starting point for bars at the bottom
            int barWidth = 30;
            int spaceBetweenBars = 40; // Increased space between bars

            for (Item libraryItem : Items) {
                int itemPopularity = libraryItem.popularityCount;
                int barHeight = (int) (itemPopularity / (double) totalPopularity * 300);

                // Draw the bar
                g.setColor(Color.BLUE);
                g.fillRect(x, y - barHeight, barWidth, barHeight);

                // Label the item title below the bar
                g.setColor(Color.BLACK);
                g.drawString(libraryItem.title, x - 10, y + 20);

                // Display popularity count above the bar
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(itemPopularity), x - 10, y - barHeight - 5);

                // Update x position for the next bar
                x += barWidth + spaceBetweenBars;
            }
        }
    }

}
