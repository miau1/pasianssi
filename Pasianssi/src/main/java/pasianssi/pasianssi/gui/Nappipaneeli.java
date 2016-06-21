/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.pasianssi.gui;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mikko
 */
public class Nappipaneeli extends JPanel {

    private JButton uusi;
    private JButton vaikeus;
    private JButton ohje;
    private JLabel paras;

    public Nappipaneeli() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        uusi = new JButton("Uusi peli");
        vaikeus = new JButton("Vaikeustaso: Keskitaso");
        ohje = new JButton("Ohje");
        this.add(uusi);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(vaikeus);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(ohje);
    }

    public JButton getUusi() {
        return uusi;
    }

    public JButton getVaikeus() {
        return vaikeus;
    }

    public JButton getOhje() {
        return ohje;
    }

}
