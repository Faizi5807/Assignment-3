import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PopularityChartScreen extends JFrame {
    private List<Item> Items;

    public PopularityChartScreen(List<Item> Items) {
        this.Items = Items;
        setTitle("Popularity Chart");
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
            int y = 50;
            int barWidth = 30;

            for (Item libraryItem : Items) {
                int ItemPopularity = libraryItem.popularityCount;
                int barHeight = (int) (ItemPopularity / (double) totalPopularity * 300); // Scale the bar height

                g.setColor(Color.BLUE);
                g.fillRect(x, y, barWidth, barHeight);

                g.setColor(Color.GREEN);
                g.drawString(libraryItem.title, x - 10, y + barHeight + 20);

                g.setColor(Color.ORANGE);
                g.drawString(String.valueOf(ItemPopularity), x - 10, y - 10);

                x += 100;
            }
        }
    }

}
