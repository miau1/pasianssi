package pasianssi.pasianssi.gui;

import java.util.InputMismatchException;
import java.util.Scanner;
import pasianssi.pasianssi.logiikka.Klondyke;
import pasianssi.pasianssi.logiikka.Kortti;
import pasianssi.pasianssi.logiikka.Pakka;

/**
 * Alustava käyttöliittymä pasianssille tekstimuotoisena.
 *
 * @author mikko
 */
public class Teksti {

    Scanner sc;
    Klondyke kl;
    boolean jatketaanko;

    /**
     * Alustaa käyttöliittymän.
     */
    public Teksti() {
        sc = new Scanner(System.in);
        kl = new Klondyke();
        kl.alustaPeli(3);
        jatketaanko = true;
    }

    /**
     * Pelikierros, jonka aikana tulostetaan näkymä, siirretään kortteja ja
     * tarkistetaan onko pelaaja voittanut.
     */
    public void kierros() {
        tulostaNakyma();
        siirraKortteja();
        tarkistaVoitto();
    }

    /**
     * Tulostaa kääntöpakan päällimmäisen kortin, alapakkojen korttien määrän ja
     * oikeinpäin olevat kortit sekä yläpakkojen päällimäiset kortit.
     */
    public void tulostaNakyma() {
        kaantopakka();
        alapakat();
        ylapakat();
    }

    /**
     * Tulostaa käätnöpakan päällimäisen kortin.
     */
    public void kaantopakka() {
        System.out.print("Kääntopakassa on: ");
        System.out.println(kl.getKaanto().koko());
        if (kl.getKaanto().koko() == 0) {
            System.out.println("0 korttia.");
        } else {
            System.out.println("num " + kl.getKaanto().paallimmainen().getNumero()
                    + " maa " + kl.getKaanto().paallimmainen().getMaa());
        }
    }

    /**
     * Tulostaa alapakat.
     */
    public void alapakat() {
        System.out.println("");
        System.out.println("Alapakoissa on: ");
        for (int i = 0; i < 7; i++) {
            System.out.print(i + 1 + ": ");
            if (kl.getAlapakat()[i].koko() == 0) {
                System.out.println("0 korttia.");
            } else {
                System.out.print(kl.getAlapakat()[i].koko() + " korttia");
                Pakka oik = kl.getAlapakat()[i].oikeinPainOlevat();
                while (oik.koko() > 0) {
                    Kortti k = oik.poistaKortti();
                    System.out.print(", num " + k.getNumero() + " maa " + k.getMaa());
                }
                System.out.println("");
            }
        }
    }

    /**
     * Tulostaa yläpakkojen päällimäiset korit.
     */
    public void ylapakat() {
        System.out.println("");
        System.out.println("Yläpakoissa on: ");
        for (int i = 0; i < 4; i++) {
            System.out.print(i + 1 + ": ");
            if (kl.getYlapakat()[i].koko() == 0) {
                System.out.println("0 korttia.");
            } else {
                System.out.println("num " + kl.getYlapakat()[i].paallimmainen().getNumero()
                        + " maa " + kl.getYlapakat()[i].paallimmainen().getMaa());
            }
        }
        System.out.println("");
    }

    /**
     * Kysyy käyttäjältä, mistä pakasta siirretään, mihin pakkaan siirretään,
     * montako korttia siirretään ja lopulta siirttää kortit. Käyttäjä voi myös
     * aloittaa uuden pelin tai lopettaa pelin.
     */
    public void siirraKortteja() {
        System.out.println("Siirrä kortteja ");
        System.out.println("Mistä? ('alku', 'kaanto', 'yla', 'ala', 'uusipeli', 'lopeta')");
        String vast = sc.next();
        if (vast.equals("alku")) {
            kl.korttiAlustaKaantoon();
        } else if (vast.equals("uusipeli")) {
            kl.tyhjennaKaikki();
            kl.alustaAlkupakka();
            kl.alustaAlapakat();
        } else if (vast.equals("lopeta")) {
            jatketaanko = false;
        } else {
            Pakka mista = new Pakka(5);
            Pakka mihin = new Pakka(5);
            if (vast.equals("kaanto")) {
                mista = kl.getKaanto();
            } else if (vast.equals("yla")) {
                int num = kysyNumeroa("Pakka numero?");
                pakanRajat(num, 1, 4);
                mista = kl.getYlapakat()[num - 1];
            } else if (vast.equals("ala")) {
                int num = kysyNumeroa("Pakka numero?");
                pakanRajat(num, 1, 7);
                mista = kl.getAlapakat()[num - 1];
            } else {
                vaaraSyote();
            }
            System.out.println("Mihin? ('yla', 'ala')");
            vast = sc.next();
            if (vast.equals("yla")) {
                int num = kysyNumeroa("Pakka numero?");
                pakanRajat(num, 1, 4);
                mihin = kl.getYlapakat()[num - 1];
            } else if (vast.equals("ala")) {
                int num = kysyNumeroa("Pakka numero?");
                pakanRajat(num, 1, 7);
                mihin = kl.getAlapakat()[num - 1];
            } else {
                vaaraSyote();
            }
            int maara = kysyNumeroa("Kuinka monta?");;
            kl.siirraKortteja(mista, mihin, maara);
        }
    }

    /**
     * Tulostaa kysymyksen ja ottaa talteen käyttäjän antaman numeron.
     *
     * @param kysymys
     * @return numero
     */
    public int kysyNumeroa(String kysymys) {
        System.out.println(kysymys);
        int ret = 0;
        try {
            ret = sc.nextInt();
        } catch (InputMismatchException e) {
            this.vaaraSyote();
        }
        return ret;
    }

    /**
     * Tarkistaa, että annettu pakannumero on mahdollinen.
     *
     * @param num
     * @param ala
     * @param yla
     */
    public void pakanRajat(int num, int ala, int yla) {
        if (num < ala || num > yla) {
            vaaraSyote();
        }
    }

    /**
     * Tulostaa virheviesitin ja aloittaa uuden kierroksen.
     */
    public void vaaraSyote() {
        System.out.println("En ymmärtänyt");
        kierros();
    }

    /**
     * Tarkistaa onko pelaaja voittanut yläpakkojen korttimäärien perusteella.
     * Pelaaja voi voittaessaan aloittaa uuden pelin, jos haluaa.
     */
    public void tarkistaVoitto() {
        if (jatketaanko == true) {
            if (kl.tarkistaVoitto() == false) {
                this.kierros();
            } else {
                System.out.println("VOITIT PELIN!");
                System.out.println("Uusi peli?(k/e)");
                String vast = sc.next();
                if (vast.equals("k")) {
                    kl.tyhjennaKaikki();
                    kl.alustaAlkupakka();
                    kl.alustaAlapakat();
                    this.kierros();
                }
            }
        }
    }
}
