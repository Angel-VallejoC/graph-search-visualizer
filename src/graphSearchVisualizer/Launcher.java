package graphSearchVisualizer;

import graphSearchVisualizer.listeners.SearchListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame implements SearchListener, ActionListener {

    public static final String TITLE = "GRAPH SEARCH VISUALIZER";
    public static final int MIN_WIDTH = 500;
    public static final int MIN_HEIGHT = 500;
    private Grid grid;
    private JButton searchButton;
    private JButton clearButton;
    private JButton resetButton;
    private JComboBox<String> searchMethodCB;

    public Launcher() {
        configWindow();
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
        grid = new Grid(10, 10, 30, this);
        add(grid);

        searchMethodCB = new JComboBox<String>();
        searchMethodCB.addItem(Search.BFS);
        searchMethodCB.addItem(Search.DFS);
        add(searchMethodCB);

        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        add(searchButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        clearButton.setEnabled(false);
        add(clearButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        resetButton.setEnabled(false);
        add(resetButton);
    }

    // Buttons callback
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source == searchButton){
            if (grid.isStartAndEndSelected()){
                source.setEnabled(false);
                clearButton.setEnabled(false);
                resetButton.setEnabled(false);
                grid.search((String) searchMethodCB.getSelectedItem());
            }
            else {
                JOptionPane.showMessageDialog(this, "You must select start and end cells to start searching",
                        "Select start and end cells", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (source == clearButton){
            grid.clear();
            searchButton.setEnabled(true);
        }
        else if (source == resetButton){
            grid.reset();
            searchButton.setEnabled(true);
        }
    }

    // Grid Search callbacks
    @Override
    public void started() {

    }
    @Override
    public void finished(String method, boolean result) {
        clearButton.setEnabled(true);
        resetButton.setEnabled(true);
    }

    public static void main(String[] args) {
        Launcher solver = new Launcher();
        solver.setVisible(true);
    }
}
