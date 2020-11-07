package labyrinthSolver;

import javax.swing.*;
import java.awt.*;

public class Launcher extends JFrame {

    public static final String TITLE = "LABYRINTH SOLVER";
    public static final int MIN_WIDTH = 500;
    public static final int MIN_HEIGHT = 500;

    public Launcher(){
        this.setVisible(false);
        this.setTitle(TITLE);
        this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args){
        Launcher solver = new Launcher();
        solver.setVisible(true);
    }
}