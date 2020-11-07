package labyrinthSolver;

import javax.swing.*;
import java.awt.*;

public class Wall extends JButton {

    public static final String STATE_ACTIVE = "active";
    public static final String STATE_INACTIVE = "inactive";
    private static Color activeColor = new Color(13, 71, 161);
    private static Color inactiveColor = Color.WHITE;

    private int row;
    private int column;
    private String state;

    public Wall(int cellSize, int row, int column) {
        super();

        this.state = STATE_INACTIVE;
        this.row = row;
        this.column = column;

        configButton(cellSize);
    }

    private void configButton(int cellSize){
        this.setPreferredSize(new Dimension(cellSize, cellSize));
        this.setFocusPainted(false);
        this.setBackground(Color.WHITE);
    }

    public void toggleState() {
        state = state.equals(STATE_ACTIVE) ? STATE_INACTIVE : STATE_ACTIVE;
        setBackground(state.equals(STATE_ACTIVE) ? activeColor : inactiveColor);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
