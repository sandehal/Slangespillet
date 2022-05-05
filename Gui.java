import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Gui {
    public static void main(String[] args) {
        Kontroller kontroller = new Kontroller();
    }
    Kontroller kontroller;
    JFrame vindu;
    JPanel totalPanel, styring, konsoll, ruteNett, lengde, avslutt;
    JLabel[][] ruter = new JLabel[12][12];
    JButton stopp;

    Gui(Kontroller kontroller) {
        this.kontroller = kontroller;

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(-1);
        }

        vindu = new JFrame("SNAKE VI: RETURN OF THE SNAKE");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Lag 1
        totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());
        vindu.add(totalPanel);


        //Lag 2
        styring = new JPanel();
        styring.setLayout(new BorderLayout());
        styring.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        totalPanel.add(styring, BorderLayout.NORTH);

        ruteNett = new JPanel();
        ruteNett.setLayout(new GridLayout(12, 12));
        totalPanel.add(ruteNett, BorderLayout.SOUTH);
        for (JLabel[] labels : ruter) {
            for (JLabel label : labels) {
                label = new JLabel("");
                label.setPreferredSize(new Dimension(30,30));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                ruteNett.add(label);
            }
        }

        //Lag 3
        lengde = new JPanel();
        lengde.setLayout(new BorderLayout());
        JLabel storrelseTeller = new JLabel("Antall");
        styring.add(lengde, BorderLayout.WEST);
        lengde.add(storrelseTeller, BorderLayout.CENTER);


        konsoll = new JPanel();
        konsoll.setLayout(new BorderLayout());
        konsoll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        styring.add(konsoll, BorderLayout.CENTER);
        JButton nord = new JButton("Opp");
        // class gaaNord implements ActionListener {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         kontroller.gaaNord();
        //     }
        // }
        JButton ost = new JButton("Hoyre");
        // class gaaOst implements ActionListener {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         kontroller.gaaOst();
        //     }
        // }
        JButton sor = new JButton("Ned");
        // class gaaSor implements ActionListener {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         kontroller.gaaSor();
        //     }
        // }
        JButton vest = new JButton("Venstre");
        // class gaavest implements ActionListener {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         kontroller.gaavest();
        //     }
        // }
        konsoll.add(vest, BorderLayout.WEST);
        konsoll.add(sor, BorderLayout.SOUTH);
        konsoll.add(nord, BorderLayout.NORTH);
        konsoll.add(ost, BorderLayout.EAST);

        avslutt = new JPanel();
        avslutt.setLayout(new BorderLayout());
        avslutt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        styring.add(avslutt, BorderLayout.EAST);

        stopp = new JButton("Avslutt");
        class StoppTraad implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontroller.avsluttSpillet();
            }
        }
        stopp.addActionListener(new StoppTraad());
        avslutt.add(stopp, BorderLayout.CENTER);

        vindu.pack();
        vindu.setVisible(true);
    }
}
