import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicArrowButton;

public class Gui {
    public static void main(String[] args) {
        Kontroller kontroller = new Kontroller();
    }
    Kontroller kontroller;
    JFrame vindu, sluttMeny;
    JPanel totalPanel, styring, konsoll, ruteNett, lengde, avslutt;
    static JLabel ruter[][] = new JLabel[12][12];
    JLabel storrelseTeller = new JLabel("");
    JButton stopp;
    int xPos;
    int yPos;
    int totalLengde = 1;
    int kropp = 0;
    int[] y;
    int[] x;
    Color kroppFarge;
    

    Gui(Kontroller kontroller, int a, int b) {
        this.kontroller = kontroller;
        this.xPos = b;
        this.yPos = a;
        kropp = 0;
        totalLengde = 1;
        x = new int[200];
        y = new int[200];
        ArrayList<Color> kroppfargeListe = new ArrayList<>();
        Color rod = new Color(186, 55, 26);
        Color gronn = new Color(32, 145, 36);
        Color gronn2 = new Color(139, 224, 142);
        Color brun = new Color(107, 51, 39);
        Color gul = new Color(196, 209, 73);
        Color blaa = new Color(91, 35, 150);
        kroppfargeListe.add(rod);
        kroppfargeListe.add(gronn);
        kroppfargeListe.add(brun);
        kroppfargeListe.add(gul);
        kroppfargeListe.add(blaa);
        kroppfargeListe.add(gronn2);
        kroppFarge = kroppfargeListe.get(Kontroller.trekk(0, 5));
        
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(-1);
        }

        vindu = new JFrame("SNAKE IV: A NEW SNAKE");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sluttMeny = new JFrame();
        sluttMeny.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Lag 1
        totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());
        vindu.add(totalPanel);


        //Lag 2
        styring = new JPanel();
        styring.setLayout(new BorderLayout());
        styring.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        styring.setOpaque(true);
        styring.setBackground(Color.BLUE);
        totalPanel.add(styring, BorderLayout.SOUTH);

        ruteNett = new JPanel();
        ruteNett.setLayout(new GridLayout(12, 12));
        totalPanel.add(ruteNett, BorderLayout.CENTER);
        int rad = 0;
        int kol = 0;
        for (JLabel[] labels : ruter) {
            kol = 0;
            for (JLabel label : labels) {
                label = new JLabel("");
                label.setOpaque(true);
                label.setBackground(Color.RED);
                ruter[rad][kol] = label;
                label.setPreferredSize(new Dimension(30,30));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                ruteNett.add(label);
                kol++;
            }
            rad++;
        }

        //Lag 3
        lengde = new JPanel();
        lengde.setLayout(new BorderLayout());
        storrelseTeller.setText("Lengde: " + totalLengde);
        styring.add(lengde, BorderLayout.WEST);
        lengde.add(storrelseTeller, BorderLayout.CENTER);


        konsoll = new JPanel();
        konsoll.setLayout(new BorderLayout());
        konsoll.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        konsoll.setOpaque(true);
        konsoll.setBackground(Color.BLUE);
        styring.add(konsoll, BorderLayout.CENTER);
        JButton nord = new JButton("Opp");
        nord.setPreferredSize(new Dimension(20, 40));
        nord.setBackground(Color.ORANGE);
        class gaaNord implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.gaaNord();
            }
        }
        JButton ost = new JButton("Hoyre");
        ost.setPreferredSize(new Dimension(50, 50));
        ost.setBackground(Color.ORANGE);
        class gaaOst implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.gaaOst();
            }
        }
        JButton sor = new JButton("Ned");
        sor.setPreferredSize(new Dimension(20, 40));
        sor.setBackground(Color.ORANGE);
        class gaaSor implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.gaaSor();
            }
        }
        JButton vest = new JButton("Venstre");
        vest.setPreferredSize(new Dimension(50, 50));
        vest.setBackground(Color.ORANGE);
        class gaaVest implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.gaaVest();
            }
        }
        sor.setFocusable(false);
        vest.setFocusable(false);
        nord.setFocusable(false);
        ost.setFocusable(false);
        vindu.setFocusable(true);
        sor.addActionListener(new gaaSor());
        vest.addActionListener(new gaaVest());
        nord.addActionListener(new gaaNord());
        ost.addActionListener(new gaaOst());

        vindu.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
              int tast = e.getKeyCode();
              if (tast == KeyEvent.VK_W || tast == KeyEvent.VK_UP) {
                kontroller.gaaNord();
              }
              else if (tast == KeyEvent.VK_S || tast == KeyEvent.VK_DOWN) {
                kontroller.gaaSor();
              }
              else if (tast == KeyEvent.VK_A || tast == KeyEvent.VK_LEFT) {
                kontroller.gaaVest();        
              }
              else if (tast == KeyEvent.VK_D || tast == KeyEvent.VK_RIGHT) {
                kontroller.gaaOst();       
              }
            }
          });
        konsoll.add(vest, BorderLayout.WEST);
        konsoll.add(sor, BorderLayout.SOUTH);
        konsoll.add(nord, BorderLayout.NORTH);
        konsoll.add(ost, BorderLayout.EAST);

        avslutt = new JPanel();
        avslutt.setLayout(new BorderLayout());
        avslutt.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        styring.add(avslutt, BorderLayout.EAST);

        stopp = new JButton("Avslutt");
        class StoppFunksjon implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.avsluttSpillet();
            }
        }
        stopp.addActionListener(new StoppFunksjon());
        avslutt.add(stopp, BorderLayout.CENTER);

        vindu.pack();
        vindu.setVisible(true);
    }

    void okLengde() {
        totalLengde++;
        storrelseTeller.setText("Lengde: " + totalLengde);
    }

    void visMat(int yAkse, int xAkse) {
        ruter[yAkse][xAkse].setText("$");
    }

    void notifiserGUI(int[] y, int[] x) {
        this.y = y;
        this.x = x;
    }
    void visSlange() {
        for(JLabel[] labels : ruter) {
            for (JLabel label : labels) {
                if (!label.getText().equals("$")) {
                    label.setText("");
                }
                label.setBackground(Color.RED);
            }
        }
        for (int i=totalLengde-1; i >= 0; i--) {
            JLabel kropp = ruter[y[i]][x[i]];
            System.out.println(y[i] + ":" + x[i]);
            if (i!=0) {
                kropp.setText("+");
                kropp.setBackground(kroppFarge);
                
            }
            else {
                if (kropp.getText().equals("+")) {
                    kontroller.spillFerdig();
                    Kontroller.erFerdig = true;
                }
                kropp.setText("o");
                kropp.setBackground(Color.GREEN);
            }
        }
    }
    void visSluttMeny() {
        JPanel tekstPanel = new JPanel();
        sluttMeny.add(tekstPanel);
        JLabel beskjed = new JLabel("Spillet er ferdig");
        sluttMeny.add(beskjed);
        sluttMeny.pack();
        sluttMeny.setVisible(true);

    }
}
