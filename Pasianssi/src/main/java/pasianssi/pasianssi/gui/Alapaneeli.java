/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.pasianssi.gui;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import pasianssi.pasianssi.logiikka.Klondyke;
import pasianssi.pasianssi.logiikka.Kortti;
import pasianssi.pasianssi.logiikka.Pakka;

/**
 *
 * @author mikko
 */
public class Alapaneeli extends JPanel {

    private JButton[] alatPaal;
    private JPanel[] alat;
    private ImageHandler kuvat;
    private MainFrame main;

    public Alapaneeli(Klondyke k, ImageHandler kuvat2, MainFrame main) throws IOException {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        alatPaal = new JButton[7];
        alat = new JPanel[7];
        this.kuvat = kuvat2;
        this.main = main;

        this.add(Box.createRigidArea(new Dimension(20, 0)));
        for (int i = 0; i < 7; i++) {
            alat[i] = new JPanel();
            alat[i].setLayout(new BoxLayout(alat[i], BoxLayout.Y_AXIS));
            rakennaAla(k.getAlapakat()[i], alat[i], i);
            this.add(alat[i]);
            this.add(Box.createRigidArea(new Dimension(10, 0)));
        }

        for (int i = 0; i < 7; i++) {
            alatPaal[i] = new JButton();
            alatPaal[i].setIcon(kuvat.kuva(k.getAlapakat()[i].paallimmainen().kuva()));
            alatPaal[i].setPreferredSize(new Dimension(70, 95));
            alat[i].add(alatPaal[i]);
            alat[i].add(Box.createVerticalGlue());
            alat[i].add(Box.createRigidArea(new Dimension(5, 0)));
        }
    }

    public void rakennaAla(Pakka p, JPanel jp, int i) throws IOException {
        jp.removeAll();
        if (p.koko() == 0) {
            JButton jb = new JButton();
            jb.setIcon(kuvat.kuva(105));
            jb.setPreferredSize(new Dimension(70, 95));
            jb.setActionCommand("ala" + Integer.toString(i) + "00");
            jb.addActionListener(new SiirtoListener(main));
            jp.add(jb);
        } else {
            int luku = p.koko();
            int j = 0;
            for (Kortti kortti : p.getKortit()) {
                if (luku != 1) {
                    if (kortti.getPuoli() == 0) {
                        JButton jl = new JButton();
                        jl.setIcon(kuvat.kuva(0));
                        jl.setPreferredSize(new Dimension(70, 25));
                        jp.add(jl);
                    } else {
                        JButton jb = new JButton();
                        jb.setIcon(kuvat.kuva(kortti.kuva() + 52));
                        jb.setActionCommand("ala" + Integer.toString(i) + rakennaLuku(luku));
                        jb.addActionListener(new SiirtoListener(main));
                        jb.setPreferredSize(new Dimension(70, 25));
                        jp.add(jb);
                    }
                }
                luku--;
                j++;
            }
        }
    }

    private String rakennaLuku(int luku) {
        String ret = "";
        if (luku < 10) {
            ret = ret + "0";
        }
        ret = ret + Integer.toString(luku);
        return ret;
    }

    public JButton[] getAlatPaal() {
        return alatPaal;
    }

    public JPanel[] getAlat() {
        return alat;
    }

}
