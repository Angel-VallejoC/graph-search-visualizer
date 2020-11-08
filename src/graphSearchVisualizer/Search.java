package graphSearchVisualizer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Search {

    public static final String BFS = "Breath First Search (BFS)";
    public static final String DFS = "Depth First Search (DFS)";

    private static boolean found = false;
    private Cell[][] cells;
    private HashMap<Cell, Cell> parent;

    public Search(Cell[][] cells){
        this.cells = cells;
    }

    public boolean bfs(Cell start, Cell end){
        parent = new HashMap<>();
        HashSet<Cell> visited = new HashSet<Cell>();
        Queue<Cell> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()){

            for (int i = 0; i < queue.size(); i++) {
                Cell head = queue.poll();

                if (head == end) {
                    return true;
                }

                visited.add(head);
                int row = head.getRow();
                int column = head.getColumn();
                head.setState(Cell.STATE_SEARCHING);

                // go left
                if (isValidCell(row, column - 1) && !visited.contains(cells[row][column - 1]) &&
                        !cells[row][column - 1].isProhibitedCell()) {
                    queue.add(cells[row][column - 1]);
                    visited.add(cells[row][column - 1]);
                    parent.put(cells[row][column - 1], head);
                }

                // go right
                if (isValidCell(row, column + 1) && !visited.contains(cells[row][column + 1]) &&
                        !cells[row][column + 1].isProhibitedCell()) {
                    queue.add(cells[row][column + 1]);
                    visited.add(cells[row][column + 1]);
                    parent.put(cells[row][column + 1], head);
                }

                // go up
                if (isValidCell(row - 1, column) && !visited.contains(cells[row - 1][column]) &&
                        !cells[row - 1][column].isProhibitedCell()) {
                    queue.add(cells[row - 1][column]);
                    visited.add(cells[row - 1][column]);
                    parent.put(cells[row - 1][column], head);
                }

                // go down
                if (isValidCell(row + 1, column) && !visited.contains(cells[row + 1][column]) &&
                        !cells[row + 1][column].isProhibitedCell()) {
                    queue.add(cells[row + 1][column]);
                    visited.add(cells[row + 1][column]);
                    parent.put(cells[row + 1][column], head);
                }
            }

            try {
                Thread.sleep(250);
            } catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }

        return false;
    }

    public void tracePath(Cell start, Cell end){
        if (parent == null)
            return;

        Cell current = end;

        while (current != start){
            current = parent.get(current);
            current.setState(Cell.STATE_SEARCHING);
        }
    }

    public boolean dfs(Cell start, Cell end) {
        found = false;
        HashSet<Cell> visited = new HashSet<Cell>();
        dfs(visited, start, end);
        return found;
    }

    private void dfs(HashSet<Cell> visited, Cell current, Cell end){

        if (current == end) {
            found = true;
            return;
        }

        visited.add(current);
        current.setState(Cell.STATE_SEARCHING);

        try {
            Thread.sleep(250);
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        // go left
        if ( isValidCell(current.getRow(), current.getColumn()-1) &&
                ! visited.contains(cells[current.getRow()][current.getColumn()-1]) &&
                ! cells[current.getRow()][current.getColumn()-1].isProhibitedCell() && !found)
            dfs(visited, cells[current.getRow()][current.getColumn()-1], end);

        // go right
        if ( isValidCell(current.getRow(), current.getColumn()+1) &&
                ! visited.contains(cells[current.getRow()][current.getColumn()+1]) &&
                !cells[current.getRow()][current.getColumn()+1].isProhibitedCell() && !found)
            dfs(visited, cells[current.getRow()][current.getColumn()+1], end);

        // go up
        if ( isValidCell(current.getRow()-1, current.getColumn()) &&
                ! visited.contains(cells[current.getRow()-1][current.getColumn()]) &&
                ! cells[current.getRow()-1][current.getColumn()].isProhibitedCell() && !found)
            dfs(visited, cells[current.getRow()-1][current.getColumn()], end);

        // go down
        if ( isValidCell(current.getRow()+1, current.getColumn()) &&
                ! visited.contains(cells[current.getRow()+1][current.getColumn()]) &&
                ! cells[current.getRow() + 1][current.getColumn()].isProhibitedCell() && !found)
            dfs(visited, cells[current.getRow()+1][current.getColumn()], end);

        if (!found)
            current.setState(Cell.STATE_DEFAULT);
    }

    private boolean isValidCell(int row, int column) {
        return row >= 0 && row < cells.length &&
                column >= 0 && column < cells[0].length;
    }

}
