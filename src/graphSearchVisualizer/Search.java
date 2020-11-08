package graphSearchVisualizer;

import java.util.HashSet;

public class Search {

    private Cell[][] cells;
    private static boolean found = false;

    public Search(Cell[][] cells){
        this.cells = cells;
    }

    public boolean dfs(Cell start, Cell end) {
        found = false;
        HashSet<Cell> visited = new HashSet<Cell>();
        dfs(visited, start, end);
        return found;
    }

    private void dfs(HashSet<Cell> visited, Cell current, Cell end){

        if (current == end) {
            System.out.println("found");
            found = true;
            return;
        }

        System.out.println("[" + current.getRow() + "," + current.getColumn() + "]");

        visited.add(current);
        current.setSearchState();

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
            current.setDefaultState();
    }

    private boolean isValidCell(int row, int column) {
        return row >= 0 && row < cells.length &&
                column >= 0 && column < cells[0].length;
    }

}
