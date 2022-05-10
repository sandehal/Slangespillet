import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class Gui {
    public static void main(String[] args) {
        Kontroller kontroller = new Kontroller();
    }
    Kontroller kontroller;
    JFrame vindu;
    JPanel totalPanel, styring, konsoll, ruteNett, lengde, avslutt;
    static JLabel ruter[][] = new JLabel[12][12];
    JLabel storrelseTeller = new JLabel("");
    JButton stopp;
    int xPos;
    int yPos;
    int kroppsLengde = 1;

    Gui(Kontroller kontroller, int a, int b) {
        this.kontroller = kontroller;
        this.xPos = b;
        this.yPos = a;

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(-1);
        }

        vindu = new JFrame("SNAKE IV: A NEW SNAKE");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Lag 1
        totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());
        vindu.add(totalPanel);


        //Lag 2
        styring = new JPanel();
        styring.setLayout(new BorderLayout());
        styring.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
                ruter[rad][kol] = label;
                label.setPreferredSize(new Dimension(30,30));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                ruteNett.add(label);
                kol++;
            }
            rad++;
        }

        //Lag 3
        lengde = new JPanel();
        lengde.setLayout(new BorderLayout());
        storrelseTeller.setText("Lengde: " + kroppsLengde);
        styring.add(lengde, BorderLayout.WEST);
        lengde.add(storrelseTeller, BorderLayout.CENTER);


        konsoll = new JPanel();
        konsoll.setLayout(new BorderLayout());
        konsoll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        styring.add(konsoll, BorderLayout.CENTER);
        JButton nord = new JButton("Opp");
        nord.setPreferredSize(new Dimension(20, 40));
        class gaaNord implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.gaaNord();
            }
        }
        JButton ost = new JButton("-->");
        ost.setPreferredSize(new Dimension(50, 50));
        class gaaOst implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.gaaOst();
            }
        }
        JButton sor = new JButton("Ned");
        sor.setPreferredSize(new Dimension(20, 40));
        class gaaSor implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.gaaSor();
            }
        }
        JButton vest = new JButton("<--");
        vest.setPreferredSize(new Dimension(50, 50));
        class gaaVest implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.gaaVest();
            }
        }
        sor.addActionListener(new gaaSor());
        vest.addActionListener(new gaaVest());
        nord.addActionListener(new gaaNord());
        ost.addActionListener(new gaaOst());
        konsoll.add(vest, BorderLayout.WEST);
        konsoll.add(sor, BorderLayout.SOUTH);
        konsoll.add(nord, BorderLayout.NORTH);
        konsoll.add(ost, BorderLayout.EAST);

        avslutt = new JPanel();
        avslutt.setLayout(new BorderLayout());
        avslutt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
    void start() {
        ruter[yPos][xPos].setText("o");
        ruter[yPos][xPos].setBackground(Color.GREEN);
        
    }

    void bevegNord() {
        ruter[yPos][xPos].setText("");
        ruter[yPos][xPos].setBackground(new Color(238, 238, 238));
                yPos = yPos-1;
        try {
            if (ruter[yPos][xPos].getText().equals("$")) {
                ruter[yPos+1][xPos].setText("+");
            }
            ruter[yPos][xPos].setText("o");
            ruter[yPos][xPos].setBackground(Color.GREEN);
            
        } catch (IndexOutOfBoundsException e) {
        }
    }

    void bevegSor() {
        ruter[yPos][xPos].setText("");
        ruter[yPos][xPos].setBackground(new Color(238, 238, 238));
                yPos = yPos+1;
        try {
            if (ruter[yPos][xPos].getText().equals("$")) {
                ruter[yPos-1][xPos].setText("+");
            }
            ruter[yPos][xPos].setText("o");
            ruter[yPos][xPos].setBackground(Color.GREEN);
            
        } catch (IndexOutOfBoundsException e) {
        }
    }

    void bevegVest() {
        ruter[yPos][xPos].setText("");
        ruter[yPos][xPos].setBackground(new Color(238, 238, 238));
                xPos = xPos - 1;
        try {
            if (ruter[yPos][xPos].getText().equals("$")) {
                ruter[yPos][xPos+1].setText("+");
            }
            ruter[yPos][xPos].setText("o");
            ruter[yPos][xPos].setBackground(Color.GREEN);
            
        } catch (IndexOutOfBoundsException e) {
        }
    }

    void bevegOst() {
        ruter[yPos][xPos].setText("");
        ruter[yPos][xPos].setBackground(new Color(238, 238, 238));
        xPos = xPos + 1;
        try {
            if (ruter[yPos][xPos].getText().equals("$")) {
                ruter[yPos][xPos-1].setText("+");
            }
            ruter[yPos][xPos].setText("o");
            ruter[yPos][xPos].setBackground(Color.GREEN);
            
            
        } catch (IndexOutOfBoundsException e) {
        }
    }

    void okLengde() {
        kroppsLengde++;
        storrelseTeller.setText("Lengde: " + kroppsLengde);
    }

    void visMat(int yAkse, int xAkse) {
        ruter[yAkse][xAkse].setText("$");
    }
}
