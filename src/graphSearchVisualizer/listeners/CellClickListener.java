package graphSearchVisualizer.listeners;

import graphSearchVisualizer.Cell;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellClickListener extends MouseAdapter {

    GridClickListener listener;

    public CellClickListener(GridClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            Cell selected = (Cell) e.getSource();
            listener.leftClick(selected);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            Cell selected = (Cell) e.getSource();
            listener.rightClick(selected);
        }
    }
}
