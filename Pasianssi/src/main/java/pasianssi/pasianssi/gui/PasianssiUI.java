package pasianssi.pasianssi.gui;

import java.awt.*;
import static java.awt.Image.SCALE_SMOOTH;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import pasianssi.pasianssi.logiikka.*;

public class PasianssiUI extends JFrame {

    private Klondyke k;
    private String strMista;
    private Pakka mista;

    private JFrame mainFrame;

    private JPanel peli;
    private JPanel yla;
    private JPanel ala;
    private JButton[] ylat;
    private JButton[] alatPaal;
    private JPanel[] alat;

    private JPanel nappipaneeli;
    private JButton uusi;
    private JButton vaikeus;
    private JLabel paras;
    private JLabel aika;

    private ImageIcon[] kuvat;

    private JLabel testiP;
    private String testiS;

    /**
     * Pasianssin graafinen käyttöliittymä.
     * 
     * @throws IOException 
     */
    public PasianssiUI() throws IOException {
        prepareGUI();
    }

    public static void main(String[] args) throws IOException {
        PasianssiUI pasianssi = new PasianssiUI();
        pasianssi.testi();
    }

    private void prepareGUI() throws IOException {
        mainFrame = new JFrame("Pasianssi");
        mainFrame.setSize(1000, 700);

        kuvat = new ImageIcon[106];
        BufferedImage myPicture1 = ImageIO.read(new File("src/main/resources/images/kortit/Backs/Card-Back-01.png"));
        kuvat[0] = new ImageIcon(myPicture1.getScaledInstance(70, 95, SCALE_SMOOTH));
        for (int i = 1; i < 53; i++) {
            String osoite = "src/main/resources/images/kortit/Cards/Classic/";
            if (i < 14) {
                osoite = osoite + "h" + rakennaLuku(i);
            } else if (i > 13 && i < 27) {
                osoite = osoite + "s" + rakennaLuku(i - 13);
            } else if (i > 26 && i < 40) {
                osoite = osoite + "d" + rakennaLuku(i - 26);
            } else {
                osoite = osoite + "c" + rakennaLuku(i - 39);
            }
            osoite = osoite + ".png";
            myPicture1 = ImageIO.read(new File(osoite));
            kuvat[i] = new ImageIcon(myPicture1.getScaledInstance(70, 95, SCALE_SMOOTH));
            kuvat[i + 52] = new ImageIcon(myPicture1.getSubimage(0, 0, 768, 250).getScaledInstance(70, 25, SCALE_SMOOTH));
        }
        myPicture1 = ImageIO.read(new File("src/main/resources/images/kortit/Backs/pohja.png"));
        kuvat[105] = new ImageIcon(myPicture1.getScaledInstance(70, 95, SCALE_SMOOTH));

        k = new Klondyke();
        k.alustaPeli();
        strMista = "";

        luoYla();
        luoAla();
        luoNappipan();
        luoPeli();

        mainFrame.add(peli);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);

    }

    private void testi() {
        ylat[0].setActionCommand("alku");
        ylat[0].addActionListener(new TestiListener());
        ylat[1].setActionCommand("kaanto");
        ylat[1].addActionListener(new TestiListener());
        ylat[2].setActionCommand("yla0");
        ylat[2].addActionListener(new TestiListener());
        ylat[3].setActionCommand("yla1");
        ylat[3].addActionListener(new TestiListener());
        ylat[4].setActionCommand("yla2");
        ylat[4].addActionListener(new TestiListener());
        ylat[5].setActionCommand("yla3");
        ylat[5].addActionListener(new TestiListener());
        alatPaal[0].setActionCommand("ala001");
        alatPaal[0].addActionListener(new TestiListener());
        alatPaal[1].setActionCommand("ala101");
        alatPaal[1].addActionListener(new TestiListener());
        alatPaal[2].setActionCommand("ala201");
        alatPaal[2].addActionListener(new TestiListener());
        alatPaal[3].setActionCommand("ala301");
        alatPaal[3].addActionListener(new TestiListener());
        alatPaal[4].setActionCommand("ala401");
        alatPaal[4].addActionListener(new TestiListener());
        alatPaal[5].setActionCommand("ala501");
        alatPaal[5].addActionListener(new TestiListener());
        alatPaal[6].setActionCommand("ala601");
        alatPaal[6].addActionListener(new TestiListener());
        uusi.addActionListener(new TestiListener());
        uusi.setActionCommand("uusi");
    }

    private class TestiListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("uusi")) {
                k.tyhjennaKaikki();
                k.alustaPeli();
                ylat[0].setIcon(kuvat[0]);
                ylat[1].setIcon(kuvat[105]);
                for (int i = 2; i < 6; i++) {
                    ylat[i].setIcon(kuvat[105]);
                }
                for (int i = 0; i < 7; i++) {
                    rakennaAla(k.getAlapakat()[i], alat[i], i);
                    asetaAlaKuva(i);
                }
                testiP.setText(testiS);

            } else if (command.equals("alku")) {
                k.korttiAlustaKaantoon();
                if (k.getAlku().koko() > 0) {
                    ylat[0].setIcon(kuvat[0]);
                } else {
                    ylat[0].setIcon(kuvat[105]);
                }
                if (k.getKaanto().koko() > 0) {
                    ylat[1].setIcon(kuvat[k.getKaanto().paallimmainen().kuva()]);
                } else {
                    ylat[1].setIcon(kuvat[105]);
                }
            } else {
                if (strMista.equals("")) {
                    strMista = command;
                } else {
                    if (strMista.equals("kaanto")) {
                        mista = k.getKaanto();
                    } else if (strMista.startsWith("yla")) {
                        mista = k.getYlapakat()[Integer.parseInt(strMista.substring(3))];
                    } else if (strMista.startsWith("ala")) {
                        mista = k.getAlapakat()[Integer.parseInt(strMista.substring(3, 4))];
                    }
                    if (command.startsWith("yla")) {
                        int pakka = Integer.parseInt(command.substring(3));
                        k.siirraKortteja(mista, k.getYlapakat()[pakka], 1);
                        asetaKuvat();
                        asetaKuva(k.getYlapakat()[pakka], ylat[pakka + 2]);
                        testiP.setText(testiS);
                    } else if (command.startsWith("ala")) {
                        int pakka = Integer.parseInt(command.substring(3, 4));
                        k.siirraKortteja(mista, k.getAlapakat()[pakka], puraLuku(strMista.substring(4)));
                        asetaKuvat();
                        rakennaAla(k.getAlapakat()[pakka], alat[pakka], pakka);
                        asetaAlaKuva(pakka);
                        testiP.setText(testiS);
                    }
                    if (testiS.equals(" ")) {
                        testiS = "  ";
                    } else {
                        testiS = " ";
                    }

                    strMista = "";
                }
            }
        }
    }

    /**
     * Päivittää valitun alapakan päällimmäisen kortin kuvan.
     * 
     * @param i Pakan numero
     */
    private void asetaAlaKuva(int i) {
        if (k.getAlapakat()[i].koko() == 0) {
            alatPaal[i].setIcon(kuvat[105]);
        } else {
            alat[i].add(alatPaal[i]);
            alatPaal[i].setIcon(kuvat[k.getAlapakat()[i].paallimmainen().kuva()]);
        }
        alat[i].add(Box.createVerticalGlue());
        alat[i].add(Box.createRigidArea(new Dimension(5, 0)));
    }

    /**
     * Päivittään sen pakan kuvat, josta on viimeksi siirretty kortteja.
     */
    private void asetaKuvat() {
        if (strMista.equals("kaanto")) {
            asetaKuva(k.getKaanto(), ylat[1]);
        } else if (strMista.startsWith("yla")) {
            int pakka = Integer.parseInt(strMista.substring(3));
            asetaKuva(k.getYlapakat()[pakka], ylat[pakka + 2]);
        } else if (strMista.startsWith("ala")) {
            int pakka = Integer.parseInt(strMista.substring(3, 4));
            rakennaAla(k.getAlapakat()[pakka], alat[pakka], pakka);
            asetaAlaKuva(pakka);
        }
    }

    /**
     * Päivittää valitun pakan päällimäisen kortin kuvan.
     * 
     * @param p Pakka
     * @param j Nappi
     */
    private void asetaKuva(Pakka p, JButton j) {
        if (p.koko() > 0) {
            j.setIcon(kuvat[p.paallimmainen().kuva()]);
        } else {
            j.setIcon(kuvat[105]);
        }
    }

    /**
     * Päivittää valitun alapakan kaikki kuvat ja napit.
     * 
     * @param p Pakka
     * @param jp Paneeli, jossa pakka on
     * @param i Pakan numero
     */
    private void rakennaAla(Pakka p, JPanel jp, int i) {
        jp.removeAll();
        if (p.koko() == 0) {
            JButton jb = new JButton();
            jb.setIcon(kuvat[105]);
            jb.setPreferredSize(new Dimension(70, 95));
            jb.setActionCommand("ala" + Integer.toString(i) + "00");
            jb.addActionListener(new TestiListener());
            jp.add(jb);
        } else {
            int luku = p.koko();
            int j = 0;
            for (Kortti kortti : p.getKortit()) {
                if (luku != 1) {
                    if (kortti.getPuoli() == 0) {
                        JButton jl = new JButton();
                        jl.setIcon(kuvat[0]);
                        jl.setPreferredSize(new Dimension(70, 25));
                        jp.add(jl);
                    } else {
                        JButton jb = new JButton();
                        jb.setIcon(kuvat[kortti.kuva() + 52]);
                        jb.setActionCommand("ala" + Integer.toString(i) + rakennaLuku(luku));
                        jb.addActionListener(new TestiListener());
                        jb.setPreferredSize(new Dimension(70, 25));
                        jp.add(jb);
                    }
                }
                luku--;
                j++;
            }
        }
    }

    /**
     * Muuttaa integerin stringiksi, jota pystytään käsittelemään jatkossa.
     * 
     * @param luku
     * @return luku string-muodossa
     */
    private String rakennaLuku(int luku) {
        String ret = "";
        if (luku < 10) {
            ret = ret + "0";
        }
        ret = ret + Integer.toString(luku);
        return ret;
    }

    /**
     * Muutta string-muodossa olevan luvun takaisin integeriksi.
     * @param luku
     * @return luku integerinä
     */
    private int puraLuku(String luku) {
        if (luku.equals("to") || luku.equals("a0") || luku.equals("a1")
                || luku.equals("a2") || luku.equals("a3") || luku.equals("")) {
            return 1;
        }
        return Integer.parseInt(luku);
    }

    /**
     * Luo pelialustan yläpaneelin.
     */
    private void luoYla() {
        yla = new JPanel();
        yla.setLayout(new BoxLayout(yla, BoxLayout.X_AXIS));

        ylat = new JButton[6];

        yla.add(Box.createRigidArea(new Dimension(20, 0)));

        for (int i = 0; i < 2; i++) {
            ylat[i] = new JButton();
            ylat[i].setPreferredSize(new Dimension(70, 95));
            yla.add(ylat[i]);
            yla.add(Box.createRigidArea(new Dimension(10, 0)));
        }

        yla.add(Box.createRigidArea(new Dimension(200, 0)));

        for (int i = 2; i < 6; i++) {
            ylat[i] = new JButton();
            ylat[i].setPreferredSize(new Dimension(70, 95));
            yla.add(ylat[i]);
            yla.add(Box.createRigidArea(new Dimension(10, 0)));
        }

        ylat[0].setIcon(kuvat[0]);
        ylat[1].setIcon(kuvat[105]);
        for (int i = 2; i < 6; i++) {
            ylat[i].setIcon(kuvat[105]);
        }
    }

    /**
     * Luo pelialustan alapaneelin.
     */
    public void luoAla() {
        ala = new JPanel();
        ala.setLayout(new BoxLayout(ala, BoxLayout.X_AXIS));
        alatPaal = new JButton[7];
        alat = new JPanel[7];

        ala.add(Box.createRigidArea(new Dimension(20, 0)));
        for (int i = 0; i < 7; i++) {
            alat[i] = new JPanel();
            alat[i].setLayout(new BoxLayout(alat[i], BoxLayout.Y_AXIS));
            rakennaAla(k.getAlapakat()[i], alat[i], i);
            ala.add(alat[i]);
            ala.add(Box.createRigidArea(new Dimension(10, 0)));
        }

        for (int i = 0; i < 7; i++) {
            alatPaal[i] = new JButton();
            alatPaal[i].setIcon(kuvat[k.getAlapakat()[i].paallimmainen().kuva()]);
            alatPaal[i].setPreferredSize(new Dimension(70, 95));
            alat[i].add(alatPaal[i]);
            alat[i].add(Box.createVerticalGlue());
            alat[i].add(Box.createRigidArea(new Dimension(5, 0)));
        }
    }

    /**
     * Luo pelin nappipaneelin.
     */
    public void luoNappipan() {
        nappipaneeli = new JPanel();
        nappipaneeli.setLayout(new BoxLayout(nappipaneeli, BoxLayout.X_AXIS));
        uusi = new JButton("Uusi peli");
        vaikeus = new JButton("Vaikeustaso");
        paras = new JLabel("Paras tulos");
        aika = new JLabel("Aika: 0:00");
        nappipaneeli.add(uusi);
        nappipaneeli.add(Box.createRigidArea(new Dimension(10, 0)));
        nappipaneeli.add(vaikeus);
        nappipaneeli.add(Box.createRigidArea(new Dimension(10, 0)));
        nappipaneeli.add(paras);
        nappipaneeli.add(Box.createRigidArea(new Dimension(10, 0)));
        nappipaneeli.add(aika);
    }

    /**
     * Luo pelialustan.
     */
    public void luoPeli() {
        peli = new JPanel();
        peli.setLayout(new BoxLayout(peli, BoxLayout.Y_AXIS));
        testiS = "  ";
        testiP = new JLabel(" ");
        peli.add(testiP);
        peli.add(yla);
        peli.add(Box.createRigidArea(new Dimension(0, 30)));
        peli.add(ala);
        peli.add(nappipaneeli);
        peli.add(Box.createRigidArea(new Dimension(0, 5)));
    }

}
