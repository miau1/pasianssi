/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.pasianssi.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import pasianssi.pasianssi.logiikka.Klondyke;
import pasianssi.pasianssi.logiikka.Pakka;

/**
 *
 * @author mikko
 */
public class MainFrame extends JFrame {

    private Klondyke k;

    private ImageHandler kuvat;

    private VoittoFrame voittof;
    private OhjeFrame ohjef;

    private Pelipaneeli peli;
    private Ylapaneeli yla;
    private Alapaneeli ala;
    private Nappipaneeli napit;

    private String strMista;
    private Pakka mista;

    private int vtaso;

    private long kello;

    public MainFrame() throws IOException {
        this.setTitle("Pasianssi");
        this.setSize(1000, 700);

        strMista = "";

        voittof = new VoittoFrame();
        ohjef = new OhjeFrame();

        k = new Klondyke();
        vtaso = 2;
        k.alustaPeli(vtaso);

        kuvat = new ImageHandler();

        yla = new Ylapaneeli(kuvat);
        ala = new Alapaneeli(k, kuvat, this);
        napit = new Nappipaneeli();
        peli = new Pelipaneeli(yla, ala, napit);

        this.add(peli);

        lisaaKuuntelijat();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        kello = System.currentTimeMillis();
    }

    private void lisaaKuuntelijat() throws IOException {
        yla.getYlat()[0].setActionCommand("alku");
        yla.getYlat()[0].addActionListener(new SiirtoListener(this));
        yla.getYlat()[1].setActionCommand("kaanto");
        yla.getYlat()[1].addActionListener(new SiirtoListener(this));
        yla.getYlat()[2].setActionCommand("yla0");
        yla.getYlat()[2].addActionListener(new SiirtoListener(this));
        yla.getYlat()[3].setActionCommand("yla1");
        yla.getYlat()[3].addActionListener(new SiirtoListener(this));
        yla.getYlat()[4].setActionCommand("yla2");
        yla.getYlat()[4].addActionListener(new SiirtoListener(this));
        yla.getYlat()[5].setActionCommand("yla3");
        yla.getYlat()[5].addActionListener(new SiirtoListener(this));
        ala.getAlatPaal()[0].setActionCommand("ala001");
        ala.getAlatPaal()[0].addActionListener(new SiirtoListener(this));
        ala.getAlatPaal()[1].setActionCommand("ala101");
        ala.getAlatPaal()[1].addActionListener(new SiirtoListener(this));
        ala.getAlatPaal()[2].setActionCommand("ala201");
        ala.getAlatPaal()[2].addActionListener(new SiirtoListener(this));
        ala.getAlatPaal()[3].setActionCommand("ala301");
        ala.getAlatPaal()[3].addActionListener(new SiirtoListener(this));
        ala.getAlatPaal()[4].setActionCommand("ala401");
        ala.getAlatPaal()[4].addActionListener(new SiirtoListener(this));
        ala.getAlatPaal()[5].setActionCommand("ala501");
        ala.getAlatPaal()[5].addActionListener(new SiirtoListener(this));
        ala.getAlatPaal()[6].setActionCommand("ala601");
        ala.getAlatPaal()[6].addActionListener(new SiirtoListener(this));
        napit.getUusi().addActionListener(new SiirtoListener(this));
        napit.getUusi().setActionCommand("uusi");
        napit.getOhje().addActionListener(new SiirtoListener(this));
        napit.getOhje().setActionCommand("ohje");
        napit.getVaikeus().addActionListener(new SiirtoListener(this));
        napit.getVaikeus().setActionCommand("vaikeus");
    }

    public Ylapaneeli getYla() {
        return yla;
    }

    public Klondyke getK() {
        return k;
    }

    public ImageHandler getKuvat() {
        return kuvat;
    }

    public JButton[] getYlat() {
        return yla.getYlat();
    }

    public Alapaneeli getAla() {
        return ala;
    }

    public JPanel[] getAlat() {
        return ala.getAlat();
    }

    public JButton[] getAlatPaal() {
        return ala.getAlatPaal();
    }

    public JButton getUusi() {
        return napit.getUusi();
    }

    public JButton getVaikeus() {
        return napit.getVaikeus();
    }

    public int getVtaso() {
        return vtaso;
    }

    public JButton getOhje() {
        return napit.getOhje();
    }

    public long getKello() {
        return kello;
    }

    public JLabel getTestiP() {
        return peli.getTestiP();
    }

    public String getTestiS() {
        return peli.getTestiS();
    }

    public VoittoFrame getVoittof() {
        return voittof;
    }

    public OhjeFrame getOhjef() {
        return ohjef;
    }

    public void setTestiS(String testiS) {
        peli.setTestiS(testiS);
    }

    public void setKello(long kello) {
        this.kello = kello;
    }

    public void setVtaso(int vtaso) {
        this.vtaso = vtaso;
    }

    public String getStrMista() {
        return strMista;
    }

    public void setStrMista(String strMista) {
        this.strMista = strMista;
    }

    public Pakka getMista() {
        return mista;
    }

    public void setMista(Pakka mista) {
        this.mista = mista;
    }

}
