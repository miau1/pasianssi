package pasianssi.pasianssi.logiikka;

import java.util.Collections;
import java.util.Stack;

/**
 * Luokka kuvaa korttipakkaa ja tarjoaa metodeja pakan käsittelemiseen.
 *
 * @author mikko
 */
public class Pakka {

    private Stack<Kortti> kortit;
    private int tunnus;

    /**
     * Luo uuden tyhjän pakan.
     */
    public Pakka(int tunnus) {
        kortit = new Stack<>();
        this.tunnus = tunnus;
    }

    /**
     * Palauttaa pakan kortit.
     *
     * @return Kortit.
     */
    public Stack<Kortti> getKortit() {
        return kortit;
    }

    /**
     * Palauttaa pakan tunnuksen. 1 = alkupakka, 2 = kaantopakka, 3 = alapakka,
     * 4 = ylapakka, 5 = muu.
     *
     * @return Pakan tunnus.
     */
    public int getTunnus() {
        return tunnus;
    }

    /**
     * Lisää kortin pakan päälle, lisäämällä sen pinoon "kortit"
     *
     * @param k
     */
    public void lisaaKortti(Kortti k) {
        kortit.push(k);
    }

    /**
     * Poistaa päällimmäisen kortin pakasta ja palauttaa sen.
     *
     * @return Päällimmäinen kortti
     */
    public Kortti poistaKortti() {
        if (!kortit.isEmpty()) {
            return kortit.pop();
        }
        return null;
    }

    /**
     * Sekoittaa pakan.
     */
    public void sekoita() {
        Collections.shuffle(kortit);
    }

    /**
     * Palauttaa pakan korttien lukumäärän.
     *
     * @return Korttien lukumäärä
     */
    public int koko() {
        return kortit.size();
    }

    /**
     * Kääntää pakan päällimäisen kortin.
     */
    public void kaannaPaallimmainen() {
        if (!kortit.isEmpty()) {
            kortit.peek().kaanna();
        }
    }

    /**
     * Poistaa monta korttia kerralla pakan päältä. Palauttaa poistetut kortit
     * käänteisessä järjestyksessä olevana pakkana.
     *
     * @param maara Kuinka monta korttia poistetaan
     *
     * @return Poistetut kortit käänt. järj. pakkana
     */
    public Pakka poistaKortteja(int maara) {
        Pakka p = new Pakka(5);
        for (int i = 0; i < maara; i++) {
            p.lisaaKortti(this.poistaKortti());
        }
        return p;
    }

    /**
     * Lisää annetun pakan tähän pakkaan. Annetun pakan järjestys kääntyy
     * prosessissa.
     *
     * @param p Lisättävät kortit pakkana
     */
    public void lisaaKortteja(Pakka p) {
        while (p.koko() > 0) {
            this.lisaaKortti(p.poistaKortti());
        }
    }

    /**
     * Näyttää pakan päällimäisen kortin.
     *
     * @return Päällimmäinen kortti.
     */
    public Kortti paallimmainen() {
        if (!kortit.isEmpty()) {
            return kortit.peek();
        }
        return null;
    }

    /**
     * Palauttaa kopiopakan pakan päällä olevista kuvapuoli ylöspäin olevista
     * korteista.
     *
     * @return Pakka.
     */
    public Pakka oikeinPainOlevat() {
        Pakka apu = new Pakka(5);
        Pakka kopio = new Pakka(5);
        int n = this.koko();
        for (int i = 0; i < n; i++) {
            if (this.paallimmainen().getPuoli() == 1) {
                Kortti k = this.poistaKortti();
                apu.lisaaKortti(k);
            }
        }
        kopio.kortit = (Stack<Kortti>) apu.getKortit().clone();
        this.lisaaKortteja(apu);
        return kopio;
    }
}
