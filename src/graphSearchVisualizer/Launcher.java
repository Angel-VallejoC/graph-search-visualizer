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
        add(grid);
        JComboBox searchMethod = new JComboBox();
        searchMethod.addItem("DFS");
        searchMethod.addItem("BFS");
        add(searchMethod);

        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.search();
            }
        });
        add(search);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.clear();
            }
        });
        add(clearButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.reset();
            }
        });
        add(resetButton);


    }

    public static void main(String[] args) {
        Launcher solver = new Launcher();
        solver.setVisible(true);
    }
}
