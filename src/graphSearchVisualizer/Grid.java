package graphSearchVisualizer;

import graphSearchVisualizer.listeners.GridClickListener;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel implements GridClickListener {

    private int width;
    private int height;
    private int cellSize;
    private Cell[][] cells;
    private Cell start;
    private Cell end;

    public Grid(int width, int height, int cellSize) {
        super();
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.start = null;
        this.end = null;

        configWindow();
        initializeCells();
    }

    private void configWindow() {
        this.setPreferredSize(new Dimension(width*cellSize, height*cellSize));
        this.setLayout(new GridLayout(height, width));
        this.setFocusable(true);
        this.setVisible(true);
    }

    private void initializeCells() {
        cells = new Cell[width][height];
        for (int row = 0; row < width; row++) {
            for (int column = 0; column < height; column++) {
                cells[row][column] = new Cell(cellSize, row, column, this);
                this.add(cells[row][column]);
            }
        }
    }

    @Override
    public void leftClick(Cell cell) {
        cell.toggleWallState();
    }

    @Override
    public void rightClick(Cell cell) {
        if (start == null) {
            cell.toggleStartState();
            start = cell;
        } else {
            if (start == cell) {
                start = null;
                cell.toggleStartState();
            } else if (end == cell) {
                end = null;
                cell.toggleEndState();
            } else if (end == null) {
                end = cell;
                cell.toggleEndState();
            }
        }
    }
}
