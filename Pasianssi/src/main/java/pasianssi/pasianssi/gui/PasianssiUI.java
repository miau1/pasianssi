package pasianssi.pasianssi.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import pasianssi.pasianssi.logiikka.*;

public class PasianssiUI extends JFrame {

    private Klondyke k;
    private String strMista;
    private Pakka mista;

    private JFrame mainFrame;
    private JPanel yla;
    private JPanel ala;
    private JPanel ala0;
    private JPanel ala1;
    private JPanel ala2;
    private JPanel ala3;
    private JPanel ala4;
    private JPanel ala5;
    private JPanel ala6;
    private JButton[] ylat;
    private JPanel[] alat;

    private JLabel status1;
    private JLabel status2;

    public PasianssiUI() throws IOException {
        prepareGUI();
    }

    public static void main(String[] args) throws IOException {
        PasianssiUI pasianssi = new PasianssiUI();
        pasianssi.testi();
    }

    private void prepareGUI() throws IOException {
        k = new Klondyke();
        strMista = "";

        status1 = new JLabel("tyhjä");
        status2 = new JLabel("tyhjä");

        k.alustaPeli();
        ylat = new JButton[6];
        alat = new JPanel[7];
        yla = new JPanel();
        ala = new JPanel();

        mainFrame = new JFrame("Pasianssi");
        mainFrame.setSize(750, 700);
        mainFrame.setLayout(new GridLayout(2, 1));

        yla.setLayout(new FlowLayout());
        ala.setLayout(new FlowLayout());
        alat[0] = ala0;
        alat[1] = ala1;
        alat[2] = ala2;
        alat[3] = ala3;
        alat[4] = ala4;
        alat[5] = ala5;
        alat[6] = ala6;
        for (int i = 0; i < 7; i++) {
            alat[i] = new JPanel();
            alat[i].setLayout(new BoxLayout(alat[i], BoxLayout.Y_AXIS));
            rakennaAla(k.getAlapakat()[i], alat[i], i);
            ala.add(alat[i]);
        }
        for (int i = 0; i < 6; i++) {
            ylat[i] = new JButton("");
            yla.add(ylat[i]);
        }
        ylat[0].setText("selkä");
        ylat[1].setText("tyhjä");
        for (int i = 2; i < 6; i++) {
            ylat[i].setText("tyhjä");
        }

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        yla.add(status1);
        yla.add(status2);

        mainFrame.add(yla);
        mainFrame.add(ala);
        mainFrame.setVisible(true);

//        BufferedImage myPicture1 = ImageIO.read(new File("osoite"));
//        alkupakka = new JButton(new ImageIcon(myPicture1.getScaledInstance(100, -1, -1)));
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
    }

    private class TestiListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("alku")) {
                k.korttiAlustaKaantoon();
                if (k.getAlku().koko() > 0) {
                    ylat[0].setText("selkä");
                } else {
                    ylat[0].setText("tyhjä");
                }
                if (k.getKaanto().koko() > 0) {
                    ylat[1].setText(k.getKaanto().paallimmainen().toString());
                } else {
                    ylat[1].setText("tyhjä");
                }
            } else {
                if (strMista.equals("")) {
                    strMista = command;
                } else {
                    if (strMista.equals("kaanto")) {
                        mista = k.getKaanto();
                    } else if (strMista.equals("yla0")) {
                        mista = k.getYlapakat()[0];
                    } else if (strMista.equals("yla1")) {
                        mista = k.getYlapakat()[1];
                    } else if (strMista.equals("yla2")) {
                        mista = k.getYlapakat()[2];
                    } else if (strMista.equals("yla3")) {
                        mista = k.getYlapakat()[3];
                    } else if (strMista.startsWith("ala0")) {
                        mista = k.getAlapakat()[0];
                    } else if (strMista.startsWith("ala1")) {
                        mista = k.getAlapakat()[1];
                    } else if (strMista.startsWith("ala2")) {
                        mista = k.getAlapakat()[2];
                    } else if (strMista.startsWith("ala3")) {
                        mista = k.getAlapakat()[3];
                    } else if (strMista.startsWith("ala4")) {
                        mista = k.getAlapakat()[4];
                    } else if (strMista.startsWith("ala5")) {
                        mista = k.getAlapakat()[5];
                    } else if (strMista.startsWith("ala6")) {
                        mista = k.getAlapakat()[6];
                    }
                    if (command.equals("yla0")) {
                        k.siirraKortteja(mista, k.getYlapakat()[0], 1);
                        asetaTekstit();
                        asetaTeksti(k.getYlapakat()[0], ylat[2]);
                    } else if (command.equals("yla1")) {
                        k.siirraKortteja(mista, k.getYlapakat()[1], 1);
                        asetaTekstit();
                        asetaTeksti(k.getYlapakat()[1], ylat[3]);
                    } else if (command.equals("yla2")) {
                        k.siirraKortteja(mista, k.getYlapakat()[2], 1);
                        asetaTekstit();
                        asetaTeksti(k.getYlapakat()[2], ylat[4]);
                    } else if (command.equals("yla3")) {
                        k.siirraKortteja(mista, k.getYlapakat()[3], 1);
                        asetaTekstit();
                        asetaTeksti(k.getYlapakat()[3], ylat[5]);
                    } else if (command.startsWith("ala0")) {
                        k.siirraKortteja(mista, k.getAlapakat()[0], puraLuku(strMista.substring(4)));
                        asetaTekstit();
                        rakennaAla(k.getAlapakat()[0], alat[0], 0);
                    } else if (command.startsWith("ala1")) {
                        k.siirraKortteja(mista, k.getAlapakat()[1], puraLuku(strMista.substring(4)));
                        asetaTekstit();
                        rakennaAla(k.getAlapakat()[1], alat[1], 1);
                    } else if (command.startsWith("ala2")) {
                        k.siirraKortteja(mista, k.getAlapakat()[2], puraLuku(strMista.substring(4)));
                        asetaTekstit();
                        rakennaAla(k.getAlapakat()[2], alat[2], 2);
                    } else if (command.startsWith("ala3")) {
                        k.siirraKortteja(mista, k.getAlapakat()[3], puraLuku(strMista.substring(4)));
                        asetaTekstit();
                        rakennaAla(k.getAlapakat()[3], alat[3], 3);
                    } else if (command.startsWith("ala4")) {
                        k.siirraKortteja(mista, k.getAlapakat()[4], puraLuku(strMista.substring(4)));
                        asetaTekstit();
                        rakennaAla(k.getAlapakat()[4], alat[4], 4);
                    } else if (command.startsWith("ala5")) {
                        k.siirraKortteja(mista, k.getAlapakat()[5], puraLuku(strMista.substring(4)));
                        asetaTekstit();
                        rakennaAla(k.getAlapakat()[5], alat[5], 5);
                    } else if (command.startsWith("ala6")) {
                        k.siirraKortteja(mista, k.getAlapakat()[6], puraLuku(strMista.substring(4)));
                        asetaTekstit();
                        rakennaAla(k.getAlapakat()[6], alat[6], 6);
                    }
                    strMista = "";
                }
            }
        }
    }

    private void asetaTekstit() {
        if (strMista.equals("kaanto")) {
            asetaTeksti(k.getKaanto(), ylat[1]);
        } else if (strMista.equals("yla0")) {
            asetaTeksti(k.getYlapakat()[0], ylat[2]);
        } else if (strMista.equals("yla1")) {
            asetaTeksti(k.getYlapakat()[1], ylat[3]);
        } else if (strMista.equals("yla2")) {
            asetaTeksti(k.getYlapakat()[2], ylat[4]);
        } else if (strMista.equals("yla3")) {
            asetaTeksti(k.getYlapakat()[3], ylat[5]);
        } else if (strMista.startsWith("ala0")) {
            rakennaAla(k.getAlapakat()[0], alat[0], 0);
        } else if (strMista.startsWith("ala1")) {
            rakennaAla(k.getAlapakat()[1], alat[1], 1);
        } else if (strMista.startsWith("ala2")) {
            rakennaAla(k.getAlapakat()[2], alat[2], 2);
        } else if (strMista.startsWith("ala3")) {
            rakennaAla(k.getAlapakat()[3], alat[3], 3);
        } else if (strMista.startsWith("ala4")) {
            rakennaAla(k.getAlapakat()[4], alat[4], 4);
        } else if (strMista.startsWith("ala5")) {
            rakennaAla(k.getAlapakat()[5], alat[5], 5);
        } else if (strMista.startsWith("ala6")) {
            rakennaAla(k.getAlapakat()[6], alat[6], 6);
        }
    }

    private void asetaTeksti(Pakka p, JButton j) {
        if (p.koko() > 0) {
            j.setText(p.paallimmainen().toString());
        } else {
            j.setText("tyhjä");
        }
    }

    private void rakennaAla(Pakka p, JPanel jp, int i) {
        jp.removeAll();
        if (p.koko() == 0) {
            JButton jb = new JButton("tyhjä");
            jb.setActionCommand("ala" + Integer.toString(i) + "00");
            jb.addActionListener(new TestiListener());
            jp.add(jb);
        } else {
            int luku = p.koko();
            for (Kortti kortti : p.getKortit()) {
                if (kortti.getPuoli() == 0) {
                    jp.add(new JLabel("selkä"));
                } else {
                    JButton jb = new JButton(kortti.toString());
                    jb.setActionCommand("ala" + Integer.toString(i) + rakennaLuku(luku));
                    jb.addActionListener(new TestiListener());
                    jp.add(jb);
                }
                luku--;
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

    private int puraLuku(String luku) {
        if (luku.equals("to") || luku.equals("a0") || luku.equals("a1")
                || luku.equals("a2") || luku.equals("a3") || luku.equals("")) {
            return 1;
        }
        return Integer.parseInt(luku);
    }
}
