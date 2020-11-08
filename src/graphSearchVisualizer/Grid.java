package graphSearchVisualizer;

import graphSearchVisualizer.listeners.GridClickListener;
import graphSearchVisualizer.listeners.SearchListener;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel implements GridClickListener {

    private int width;
    private int height;
    private int cellSize;
    private Cell[][] cells;
    private Cell start;
    private Cell end;
    private SearchListener searchListener;

    public Grid(int width, int height, int cellSize, SearchListener searchListener) {
        super();
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.start = null;
        this.end = null;
        this.searchListener = searchListener;

        configWindow();
        initializeCells();
    }

    private void configWindow() {
        this.setPreferredSize(new Dimension(width * cellSize, height * cellSize));
        this.setLayout(new GridLayout(height, width));
        this.setFocusable(true);
        this.setVisible(true);
    }

    public void clear(){
        for (int row = 0; row < width; row++) {
            for (int column = 0; column < height; column++) {
                if (cells[row][column] == start)
                    cells[row][column].setState(Cell.STATE_START_POINT);
                else if (cells[row][column].isEndPoint())
                    cells[row][column].setState(Cell.STATE_END_POINT);
                else if (!cells[row][column].isProhibitedCell())
                    cells[row][column].setState(Cell.STATE_DEFAULT);
            }
        }
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

    public void reset(){
        for (int row = 0; row < width; row++) {
            for (int column = 0; column < height; column++) {
                cells[row][column].setState(Cell.STATE_DEFAULT);
                start = null;
                end = null;
            }
        }
    }

    public boolean isStartAndEndSelected(){
        return start != null && end != null;
    }

    public void search(String method) {
        if (! isStartAndEndSelected())
            return;

        new Thread(() -> {
            Search search = new Search(cells);

            searchListener.started();

            boolean result;
            switch (method){
                case Search.BFS:
                    result = search.bfs(start, end);
                    if (result){
                        try {
                            Thread.sleep(250);
                        } catch (Exception exception){
                            System.out.println(exception.getMessage());
                        }
                        clear();
                        search.tracePath(start, end);
                    }
                    searchListener.finished(Search.BFS, result);
                    break;
                case Search.DFS:
                default:
                    result = search.dfs(start, end);
                    searchListener.finished(Search.DFS, result);
            }
        }).start();
    }

    @Override
    public void leftClick(Cell cell) {
        if (!(cell.isStartPoint() || cell.isEndPoint()))
            cell.setState(Cell.STATE_PROHIBITED);
    }

    @Override
    public void rightClick(Cell cell) {
        if (start == null && end == null) {
            cell.setState(Cell.STATE_START_POINT);
            start = cell;
        }
        else if (start == cell && end == null) {
            cell.setState(Cell.STATE_DEFAULT);
            start = null;
        }
        else if (start != null && end == null) {
            cell.setState(Cell.STATE_END_POINT);
            end = cell;
        }
        else if (start != null && end == cell) {
            cell.setState(Cell.STATE_DEFAULT);
            end = null;
        }
    }
}
