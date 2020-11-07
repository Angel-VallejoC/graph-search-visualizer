package labyrinthSolver;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WallClickListener extends MouseAdapter {

    LabyrinthClickListener listener;

    public WallClickListener(LabyrinthClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            Wall selected = (Wall) e.getSource();
            listener.leftClick(selected);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            Wall selected = (Wall) e.getSource();
            listener.rightClick(selected);
        }
    }
}
