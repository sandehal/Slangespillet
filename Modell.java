import javax.swing.*; 
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.util.ArrayList;
import java.util.Random;
public class Modell {
    Gui gui;
    Kontroller kontroller;
    String ruteNett[][];
    String retning = "NORD";
    int yPos;
    int xPos;
    int totalLengde;
    int lengdeKropp;
    int[] y;
    int[] x;

    Modell(Gui gui, Kontroller kontroller, int yPos, int xPos) {
        this.gui = gui;
        this.kontroller = kontroller;
        this.xPos = xPos;
        this.yPos = yPos;
        lengdeKropp = 0;
        totalLengde = lengdeKropp + 1;
        ruteNett = new String[12][12];
        y = new int[200];
        x = new int[200];

        for (int rad = 0; rad < 12; rad++) {
            for(int kol = 0; kol < 12; kol++) {
                ruteNett[rad][kol] = "";
            }
        }
    }

    void start(int a, int b) {
        y[0] = a;
        x[0] = b;
    }

    void flyttRetning() {
        try {
            for (int i=totalLengde+1; i > 1; i--) {
                x[i] = x[i-1];
                y[i] = y[i-1];
            }
            int yForrige = y[0];
            int xForrige = x[0];
            if (retning.equals("NORD")) {
                y[0] = y[0]-1;
            }
            if (retning.equals("SOR")) {
                y[0] = y[0] + 1;
            }
    
            if (retning.equals("VEST")) {
                x[0] = x[0] - 1;
            }
    
            if (retning.equals("OST")) {
                x[0] = x[0] + 1;
            }
            if (ruteNett[y[0]][x[0]].equals("$")) {
                ruteNett[y[0]][x[0]] = "";
                this.genererMat();
                this.okLengde();
                for (int i=totalLengde+1; i > 1; i--) {
                    y[i+1] = y[i];
                    x[i+1] = x[i];
                }
                // y[totalLengde] = y[totalLengde-1]-1;
                // x[totalLengde] = x[totalLengde-1]-1;
            }
            else {
                for(int i = totalLengde+1; i < 2; i--) {
                    y[i] = y[i-1];
                    x[i] = x[i-1];
                }
            }
            y[1] = yForrige;
            x[1] = xForrige;
            if (totalLengde == 20) {
                Kontroller.settHastighet(Kontroller.hentHastighet()*0.99);
            }
            if (totalLengde == 40) {
                Kontroller.settHastighet(Kontroller.hentHastighet()*0.98);
            }
            gui.notifiserGUI(y, x);
            gui.visSlange();
        } catch(IndexOutOfBoundsException e) {
            kontroller.spillFerdig();
            Kontroller.erFerdig = true;
        }
        
    }

    void lastAllMat() {
        for (int i = 0; i < 10; i++) {
            int yAkse = Kontroller.trekk(0, 11);
            int xAkse = Kontroller.trekk(0, 11);
            while (!ruteNett[yAkse][xAkse].equals("")) {
                yAkse = Kontroller.trekk(0, 11);
                xAkse = Kontroller.trekk(0, 11);
            }
            ruteNett[yAkse][xAkse] = "$";
            gui.visMat(yAkse, xAkse);
        }
    }

    void genererMat() {
        int yAkse = Kontroller.trekk(0, 11);
        int xAkse = Kontroller.trekk(0, 11);
        while(!ruteNett[yAkse][xAkse].equals("")) {
            yAkse = Kontroller.trekk(0, 11);
            xAkse = Kontroller.trekk(0, 11);
        }
        ruteNett[yAkse][xAkse] = "$";
        gui.visMat(yAkse, xAkse);
    }

    private void okLengde() {
        totalLengde++;
        lengdeKropp++;
        gui.okLengde();
    }
}
