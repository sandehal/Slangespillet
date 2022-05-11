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
    int mat = 10;
    
    static boolean erFerdig = false;

    public Kontroller() {
        int yPos = trekk(5, 11);
        int xPos = trekk(2, 9);
        
        gui = new Gui(this, yPos, xPos);
        modell = new Modell(gui, this, yPos, xPos);
        System.out.println("Spawn: " + yPos + ":" + xPos);
        startSpill(modell, xPos, yPos);
        lastAllMat(modell);
    }

    static void startSpill(Modell modell, int a, int b) {
        modell.start(a, b);
        Thread traad = new Thread(new KlokkeTraad(modell));
        traad.start();
    }

    static void lastAllMat(Modell modell) {
        modell.lastAllMat();
    }

    void gaaNord() {
        modell.retning = "NORD";
    }

    void gaaSor() {
        modell.retning = "SOR";
    }

    void gaaVest() {
        modell.retning = "VEST";
    }

    void gaaOst() {
        modell.retning = "OST";
    }

    void avsluttSpillet() {
        System.exit(0);
    }

    void okLengde() {
        modell.okLengde();
    }
    static int trekk (int a, int b) {
        // Trekk et tilfeldig heltall i intervallet [a..b];
        return (int)(Math.random()*(b-a+1))+a;
}
}
