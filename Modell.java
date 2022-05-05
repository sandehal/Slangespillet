import javax.swing.*; 
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
public class Modell {
    Gui gui;

    Modell(Gui gui) {
        this.gui = gui;
        String[][] ruteNett = new String[12][12];

        for (int rad = 0; rad < 12; rad++) {
            for(int kol = 0; kol < 12; kol++) {
                ruteNett[rad][kol] = "";
            }
        }
    }
}
