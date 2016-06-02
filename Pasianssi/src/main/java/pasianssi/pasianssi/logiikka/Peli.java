package pasianssi.pasianssi.logiikka;

/**
 * Luokka kuvaa pelialustalla olevia pakkoja ja tarjoaa niille tomintoja, jotka
 * ovat samoja kaikille pasiansseille.
 *
 * @author mikko
 */
public class Peli {

    private Pakka alku;
    private Pakka kaanto;

    /**
     * Luo uuden pelialustan ja luo alku- ja kääntöpakat.
     */
    public Peli() {
        alku = new Pakka(1);
        kaanto = new Pakka(2);
    }

    /**
     * Palauttaa alkupakan.
     *
     * @return Alkupakka.
     */
    public Pakka getAlku() {
        return alku;
    }

    /**
     * Palauttaa kaantopakan.
     *
     * @return Kaantopakka.
     */
    public Pakka getKaanto() {
        return kaanto;
    }

    /**
     * Alustaa alkupakan, luomalla 52 korttipakan korttia, antamalla niille maat
     * 1-4 ja numerot 1-13, ja lopuksi sekoittaa pakan.
     */
    public void alustaAlkupakka() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 13; j++) {
                Kortti k = new Kortti(j, i);
                alku.lisaaKortti(k);
            }
        }
        alku.sekoita();
    }

    /**
     * Poistaa kortin alkupakasta, kääntää sen kuvapuoli ylöspäin, ja lisää sen
     * kääntopakkaa.
     */
    public void korttiAlustaKaantoon() {
        if (alku.koko() > 0) {
            Kortti k = alku.poistaKortti();
            k.kaanna();
            kaanto.lisaaKortti(k);
        } else {
            this.uusiKierros();
        }
    }

    /**
     * Aloittaa alkupakan uuden kierroksen poistamalla jokaisen kortin
     * kääntöpakasta, kääntämällä ne kuvapuoli alaspäin, ja lisäämällä ne
     * alkupakkaan.
     */
    private void uusiKierros() {
        while (kaanto.koko() > 0) {
            Kortti k = kaanto.poistaKortti();
            k.kaanna();
            alku.lisaaKortti(k);
        }
    }

}
