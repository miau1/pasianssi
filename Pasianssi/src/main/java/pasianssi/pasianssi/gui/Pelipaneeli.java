/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.pasianssi.gui;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Pelialusta, joka sisältää kaikki kortit ja muut napit.
 * @author mikko
 */
public class Pelipaneeli extends JPanel {

    private JLabel testiP;
    private String testiS;

    public Pelipaneeli(Ylapaneeli yla, Alapaneeli ala, Nappipaneeli napit) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        testiS = "  ";
        testiP = new JLabel(" ");
        this.add(testiP);
        this.add(yla);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(ala);
        this.add(napit);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    public JLabel getTestiP() {
        return testiP;
    }

    public String getTestiS() {
        return testiS;
    }

    public void setTestiS(String testiS) {
        this.testiS = testiS;
    }

}
