package graphSearchVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame {

    public static final String TITLE = "GRAPH SEARCH VISUALIZER";
    public static final int MIN_WIDTH = 500;
    public static final int MIN_HEIGHT = 500;
    private Grid grid;

    public Launcher() {
        configWindow();

        grid = new Grid(10, 10, 30);
        addComponents();
    }

    private void configWindow(){
        this.setVisible(false);
        this.setTitle(TITLE);
        this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
    }

    private void addComponents() {
        this.add(grid);
        JComboBox prueba = new JComboBox();
        prueba.addItem("DFS");
        prueba.addItem("BFS");
        this.add(prueba);

        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.search();
            }
        });

        this.add(search);
    }

    public static void main(String[] args) {
        Launcher solver = new Launcher();
        solver.setVisible(true);
    }
}
