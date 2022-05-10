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
    ArrayList<String> slange = new ArrayList<>();

    Modell(Gui gui, Kontroller kontroller, int yPos, int xPos) {
        this.gui = gui;
        this.kontroller = kontroller;
        this.xPos = xPos;
        this.yPos = yPos;
        lengdeKropp = 0;
        totalLengde = lengdeKropp + 1;
        ruteNett = new String[12][12];

        for (int rad = 0; rad < 12; rad++) {
            for(int kol = 0; kol < 12; kol++) {
                ruteNett[rad][kol] = "";
            }
        }
    }

    void start(int a, int b) {
        ruteNett[a][b] = "o";
        gui.start();
    }

    void flyttRetning() {
        if (retning.equals("NORD")) {
            try {
                ruteNett[yPos][xPos] = "";
                yPos = yPos-1;
                if (ruteNett[yPos][xPos].equals("$")) {
                    ruteNett[yPos+1][xPos] = "+";
                    gui.okLengde();
                    this.genererMat();
                }
                ruteNett[yPos][xPos] = "o";
                gui.bevegNord();
            } catch (IndexOutOfBoundsException e) {
                Kontroller.erFerdig = true ;
            }
            
        }
        else if (retning.equals("SOR")) {
            try {
                ruteNett[yPos][xPos] = "";
                yPos = yPos+1;
                if (ruteNett[yPos][xPos].equals("$")) {
                    ruteNett[yPos-1][xPos] = "+";
                    this.genererMat();
                    gui.okLengde();
                }
                ruteNett[yPos][xPos] = "o";
                gui.bevegSor();
            } catch (IndexOutOfBoundsException e) {
                Kontroller.erFerdig = true ;
            }
        }

        else if (retning.equals("VEST")) {
            try {
                ruteNett[yPos][xPos] = "";
                xPos = xPos-1;
                if (ruteNett[yPos][xPos].equals("$")) {
                    ruteNett[yPos][xPos+1] = "+";
                    this.genererMat();
                    gui.okLengde();
                }
                ruteNett[yPos][xPos] = "o";
                gui.bevegVest();
            } catch (IndexOutOfBoundsException e) {
                Kontroller.erFerdig = true ;
            }
        }

        else if (retning.equals("OST")) {
            try {
                ruteNett[yPos][xPos] = "";
                xPos = xPos+1;
                if (ruteNett[yPos][xPos].equals("$")) {
                    ruteNett[yPos][xPos-1] = "+";
                    this.genererMat();
                    gui.okLengde();
                }
                ruteNett[yPos][xPos] = "o";
                gui.bevegOst();
            } catch (IndexOutOfBoundsException e) {
                Kontroller.erFerdig = true ;
            }
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

    void okLengde() {
        
    }

    void spistMat() {
        lengdeKropp += 1;
        gui.okLengde();
    }

    
}
