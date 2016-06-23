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
import javax.swing.JPanel;

/**
 * Yläpaneeli sisältää kaikki ylhäällä olevat pakat pasianssissa.
 * 
 * @author mikko
 */
public class Ylapaneeli extends JPanel {

    private JPanel[] ylatp;
    private JButton[] ylat;

    public Ylapaneeli(ImageHandler kuvat) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        ylat = new JButton[6];

        this.add(Box.createRigidArea(new Dimension(20, 0)));

        for (int i = 0; i < 2; i++) {
            ylat[i] = new JButton();
            ylat[i].setPreferredSize(new Dimension(70, 95));
            this.add(ylat[i]);
            this.add(Box.createRigidArea(new Dimension(10, 0)));
        }

        this.add(Box.createRigidArea(new Dimension(200, 0)));

        for (int i = 2; i < 6; i++) {
            ylat[i] = new JButton();
            ylat[i].setPreferredSize(new Dimension(70, 95));
            this.add(ylat[i]);
            this.add(Box.createRigidArea(new Dimension(10, 0)));
        }

        ylat[0].setIcon(kuvat.kuva(0));
        ylat[1].setIcon(kuvat.kuva(105));
        for (int i = 2; i < 6; i++) {
            ylat[i].setIcon(kuvat.kuva(105));
        }
    }

    public JButton[] getYlat() {
        return ylat;
    }

    public JPanel[] getYlatp() {
        return ylatp;
    }

}
