/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.pasianssi.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import pasianssi.pasianssi.logiikka.Pakka;

/**
 *
 * @author mikko
 */
public class SiirtoListener implements ActionListener {

    private MainFrame main;

    public SiirtoListener(MainFrame m) throws IOException {
        main = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("uusi")) {
            main.getK().tyhjennaKaikki();
            main.getK().alustaPeli(main.getVtaso());
            main.getYlat()[0].setIcon(main.getKuvat().kuva(0));
            main.getYlat()[1].setIcon(main.getKuvat().kuva(105));
            for (int i = 2; i < 6; i++) {
                main.getYlat()[i].setIcon(main.getKuvat().kuva(105));
            }
            for (int i = 0; i < 7; i++) {
                try {
                    main.getAla().rakennaAla(main.getK().getAlapakat()[i], main.getAlat()[i], i);
                    asetaAlaKuva(i);
                } catch (IOException ex) {
                    Logger.getLogger(SiirtoListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            main.getTestiP().setText(main.getTestiS());
            if (main.getTestiS().equals(" ")) {
                main.setTestiS("  ");
            } else {
                main.setTestiS(" ");
            }
            main.setKello(System.currentTimeMillis());
        } else if (command.equals("ohje")) {
            main.getOhjef().setVisible(true);
        } else if (command.equals("vaikeus")) {
            if (main.getVtaso() == 1) {
                main.setVtaso(2);
                main.getVaikeus().setText("Vaikeustaso: Keskitaso");
            } else if (main.getVtaso() == 2) {
                main.setVtaso(3);
                main.getVaikeus().setText("Vaikeustaso: Vaikea");
            } else if (main.getVtaso() == 3) {
                main.setVtaso(1);
                main.getVaikeus().setText("Vaikeustaso: Helppo");
            }
        } else if (command.equals("alku")) {
            main.getK().korttiAlustaKaantoon();
            if (main.getK().getAlku().koko() > 0) {
                main.getYlat()[0].setIcon(main.getKuvat().kuva(0));
            } else {
                main.getYlat()[0].setIcon(main.getKuvat().kuva(105));
            }
            if (main.getK().getKaanto().koko() > 0) {
                main.getYlat()[1].setIcon(main.getKuvat().kuva(main.getK().getKaanto().paallimmainen().kuva()));
            } else {
                main.getYlat()[1].setIcon(main.getKuvat().kuva(105));
            }
        } else {
            if (main.getStrMista().equals("")) {
                main.setStrMista(command);
            } else {
                if (main.getStrMista().equals("kaanto")) {
                    main.setMista(main.getK().getKaanto());
                } else if (main.getStrMista().startsWith("yla")) {
                    main.setMista(main.getK().getYlapakat()[Integer.parseInt(main.getStrMista().substring(3))]);
                } else if (main.getStrMista().startsWith("ala")) {
                    main.setMista(main.getK().getAlapakat()[Integer.parseInt(main.getStrMista().substring(3, 4))]);
                }
                if (command.startsWith("yla")) {
                    int pakka = Integer.parseInt(command.substring(3));
                    main.getK().siirraKortteja(main.getMista(), main.getK().getYlapakat()[pakka], 1);
                    try {
                        asetaKuvat();
                    } catch (IOException ex) {
                        Logger.getLogger(SiirtoListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    asetaKuva(main.getK().getYlapakat()[pakka], main.getYlat()[pakka + 2]);
                    main.getTestiP().setText(main.getTestiS());
                    if (main.getK().tarkistaVoitto()) {
                        main.getVoittof().vaihdaTeksti("Voitit pelin, onneksi olkoon!\nAikasi: " + Long.toString((System.currentTimeMillis() - main.getKello()) / 1000) + " sekuntia");
                        main.getVoittof().setVisible(true);
                    }
                } else if (command.startsWith("ala")) {
                    int pakka = Integer.parseInt(command.substring(3, 4));
                    main.getK().siirraKortteja(main.getMista(), main.getK().getAlapakat()[pakka], puraLuku(main.getStrMista().substring(4)));
                    try {
                        asetaKuvat();
                    } catch (IOException ex) {
                        Logger.getLogger(SiirtoListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        main.getAla().rakennaAla(main.getK().getAlapakat()[pakka], main.getAlat()[pakka], pakka);
                    } catch (IOException ex) {
                        Logger.getLogger(SiirtoListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    asetaAlaKuva(pakka);
                    main.getTestiP().setText(main.getTestiS());
                }
                if (main.getTestiS().equals(" ")) {
                    main.setTestiS("  ");
                } else {
                    main.setTestiS(" ");
                }

                main.setStrMista("");;
            }
        }
    }

    /**
     * Muutta string-muodossa olevan luvun takaisin integeriksi.
     *
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
     * Päivittään sen pakan kuvat, josta on viimeksi siirretty kortteja.
     */
    private void asetaKuvat() throws IOException {
        if (main.getStrMista().equals("kaanto")) {
            asetaKuva(main.getK().getKaanto(), main.getYlat()[1]);
        } else if (main.getStrMista().startsWith("yla")) {
            int pakka = Integer.parseInt(main.getStrMista().substring(3));
            asetaKuva(main.getK().getYlapakat()[pakka], main.getYlat()[pakka + 2]);
        } else if (main.getStrMista().startsWith("ala")) {
            int pakka = Integer.parseInt(main.getStrMista().substring(3, 4));
            main.getAla().rakennaAla(main.getK().getAlapakat()[pakka], main.getAlat()[pakka], pakka);
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
            j.setIcon(main.getKuvat().kuva(p.paallimmainen().kuva()));
        } else {
            j.setIcon(main.getKuvat().kuva(105));
        }
    }

    /**
     * Päivittää valitun alapakan päällimmäisen kortin kuvan.
     *
     * @param i Pakan numero
     */
    private void asetaAlaKuva(int i) {
        if (main.getK().getAlapakat()[i].koko() == 0) {
            main.getAlatPaal()[i].setIcon(main.getKuvat().kuva(105));
        } else {
            main.getAlat()[i].add(main.getAlatPaal()[i]);
            main.getAlatPaal()[i].setIcon(main.getKuvat().kuva(main.getK().getAlapakat()[i].paallimmainen().kuva()));
        }
        main.getAlat()[i].add(Box.createVerticalGlue());
        main.getAlat()[i].add(Box.createRigidArea(new Dimension(5, 0)));
    }
}
