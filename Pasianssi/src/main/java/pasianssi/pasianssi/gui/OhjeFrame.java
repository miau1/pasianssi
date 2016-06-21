/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.pasianssi.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author mikko
 */
public class OhjeFrame extends JFrame{
    
    private JTextArea ohjeArea;
    
    public OhjeFrame(){
        this.setTitle("Ohje");
        this.setSize(500, 500);
        ohjeArea = new JTextArea("Pasianssi:"
                + "\nPelin tarkoituksena on siirtää kaikki pakan kortit maittain ylhäällä oleviin"
                + "\npakkoihin järjestyksessä alkaen ässästä ja päättyen kuninkaaseen. Voit"
                + "\nrakentaa korttikasoja pakan päällimmäisestä kortista ja alhaalla olevien"
                + "\npakkojen korteista siten, että kasan joka toinen kortti on erivärinen ja että"
                + "\nalla oleva kortti on aina yhden suurempi kuin sen päälle tuleva kortti."
                + "\n\nSiirrot:"
                + "\nKlikkaa ensin korttia, jonka haluat siirtää, ja sen jälkeen korttia, jonka"
                + "\npäälle haluat siirtää. Jos siirto ei onnistu, vaikka sen pitäisi, tuplaklikkaa"
                + "\nkorttia, jota haluat siirtää, sillä olet todennälöisesti valinnut vahingossa"
                + "\nvälissä jonkun toisen kortin siirrettäväksi."
                + "\n\nVaikeustaso:"
                + "\nJos haluat vaihtaa vaikeustasoa, paina vaikeustasonappia ja aloita uusi peli."
                + "\n\nHelpolla pakasta vedetään yksi kortti kerrallaan, ja pakan voi käydä läpi"
                + "\nniin monta kertaa kuin haluaa."
                + "\n\nKeskitasolla pakasta vedetään kolme korttia, ja pakkaa voi yhä käydä läpi"
                + "\nloputtomasti."
                + "\n\nVaikealla vedetään kolme korttia, ja pakan voi käydä läpi kolme kertaa.");
        ohjeArea.setEditable(false);
        this.add(ohjeArea);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setVisible(false);
    }
}
