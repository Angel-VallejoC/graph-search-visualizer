package graphSearchVisualizer;

import graphSearchVisualizer.listeners.CellClickListener;
import graphSearchVisualizer.listeners.GridClickListener;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    public static final String STATE_DEFAULT = "default";
    public static final String STATE_PROHIBITED = "prohibited";
    public static final String STATE_START_POINT = "start";
    public static final String STATE_END_POINT = "end";
    private static Color prohibitedCellColor = new Color(13, 71, 161);
    private static Color defaultColor = Color.WHITE;
    private static Color startColor = new Color(46, 125, 50);
    private static Color endColor = new Color(198, 40, 40);

    private int row;
    private int column;
    private String state;
    private GridClickListener clickListener;

    public Cell(int cellSize, int row, int column, GridClickListener clickListener) {
        super();

        this.state = STATE_DEFAULT;
        this.row = row;
        this.column = column;
        this.clickListener = clickListener;

        configButton(cellSize);
    }

    private void configButton(int cellSize) {
        this.setPreferredSize(new Dimension(cellSize, cellSize));
        this.setFocusPainted(false);
        this.setBackground(Color.WHITE);

        this.addMouseListener(new CellClickListener(clickListener));
    }

    public void toggleProhibitedState() {
        if (isStartPoint())
            toggleStartState();
        if (isEndPoint())
            toggleEndState();

        state = state.equals(STATE_PROHIBITED) ? STATE_DEFAULT : STATE_PROHIBITED;
        setBackground(state.equals(STATE_PROHIBITED) ? prohibitedCellColor : defaultColor);
    }

    public void toggleStartState() {
        if (isProhibitedCell())
            toggleProhibitedState();

        state = state.equals(STATE_START_POINT) ? STATE_DEFAULT : STATE_START_POINT;
        setBackground(state.equals(STATE_START_POINT) ? startColor : defaultColor);
    }

    public void toggleEndState() {
        if (isProhibitedCell())
            toggleProhibitedState();

        state = state.equals(STATE_END_POINT) ? STATE_DEFAULT : STATE_END_POINT;
        setBackground(state.equals(STATE_END_POINT) ? endColor : defaultColor);
    }

    public boolean isProhibitedCell() {
        return state.equals(STATE_PROHIBITED);
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
