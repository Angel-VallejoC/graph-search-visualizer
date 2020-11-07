package labyrinthSolver;

import javax.swing.*;
import java.awt.*;

public class Wall extends JButton {

    public static final String STATE_ACTIVE = "active";
    public static final String STATE_INACTIVE = "inactive";
    public static final String STATE_START_POINT = "start";
    public static final String STATE_END_POINT = "end";
    private static Color activeColor = new Color(13, 71, 161);
    private static Color inactiveColor = Color.WHITE;
    private static Color startColor = new Color(46, 125, 50);
    private static Color endColor = new Color(198, 40, 40);

    private int row;
    private int column;
    private String state;
    private LabyrinthClickListener clickListener;

    public Wall(int cellSize, int row, int column, LabyrinthClickListener clickListener) {
        super();

        this.state = STATE_INACTIVE;
        this.row = row;
        this.column = column;
        this.clickListener = clickListener;

        configButton(cellSize);
    }

    private void configButton(int cellSize) {
        this.setPreferredSize(new Dimension(cellSize, cellSize));
        this.setFocusPainted(false);
        this.setBackground(Color.WHITE);

        this.addMouseListener(new WallClickListener(clickListener));
    }

    public void toggleWallState() {
        if (isStartPoint())
            toggleStartState();
        if (isEndPoint())
            toggleEndState();

        state = state.equals(STATE_ACTIVE) ? STATE_INACTIVE : STATE_ACTIVE;
        setBackground(state.equals(STATE_ACTIVE) ? activeColor : inactiveColor);
    }

    public void toggleStartState() {
        if (isWallActive())
            toggleWallState();

        state = state.equals(STATE_START_POINT) ? STATE_INACTIVE : STATE_START_POINT;
        setBackground(state.equals(STATE_START_POINT) ? startColor : inactiveColor);
    }

    public void toggleEndState() {
        if (isWallActive())
            toggleWallState();

        state = state.equals(STATE_END_POINT) ? STATE_INACTIVE : STATE_END_POINT;
        setBackground(state.equals(STATE_END_POINT) ? endColor : inactiveColor);
    }

    public boolean isWallActive() {
        return state.equals(STATE_ACTIVE);
    }

    public boolean isStartPoint() {
        return state.equals(STATE_START_POINT);
    }

    public boolean isEndPoint() {
        return state.equals(STATE_END_POINT);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
