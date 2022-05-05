import javax.swing.*; 
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
public class Kontroller {
    Gui gui;
    Modell modell;

    public Kontroller() {
        gui = new Gui(this);
        modell = new Modell(gui);
    }

    void avsluttSpillet() {
        System.exit(0);
    }
}
