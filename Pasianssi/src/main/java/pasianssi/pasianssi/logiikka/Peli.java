package pasianssi.pasianssi.logiikka;

/**
 * Luokka kuvaa pelialustalla olevia pakkoja ja tarjoaa niille tomintoja.
 *
 * @author mikko
 */
public class Peli {

    private Pakka alku;
    private Pakka kaanto;
    private Pakka[] alapakat;
    private Pakka[] ylapakat;

    /**
     * Luo uuden pelialustan ja luo alku- ja kääntöpakat sekä seitsemän
     * alapakkaa ja neljä yläpakkaa, jotka ovat kaikki aluksi tyhjiä.
     */
    public Peli() {
        alku = new Pakka(1);
        kaanto = new Pakka(2);
        alapakat = new Pakka[7];
        for (int i = 0; i < alapakat.length; i++) {
            alapakat[i] = new Pakka(3);
        }
        ylapakat = new Pakka[4];
        for (int i = 0; i < ylapakat.length; i++) {
            ylapakat[i] = new Pakka(4);
        }
    }

    public Pakka getAlku() {
        return alku;
    }

    public Pakka getKaanto() {
        return kaanto;
    }

    public Pakka[] getAlapakat() {
        return alapakat;
    }

    public Pakka[] getYlapakat() {
        return ylapakat;
    }

    /**
     * Alustaa pelissä tarvittavat pakat.
     */
    public void alustaPakat() {
        alustaAlkupakka();
        alustaAlapakat();
    }

    /**
     * Alustaa alkupakan, luomalla 52 korttipakan korttia, antamalla niille maat
     * 1-4 ja numerot 1-13, ja lopuksi sekoittaa pakan.
     */
    private void alustaAlkupakka() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 13; j++) {
                Kortti k = new Kortti(j, i);
                alku.lisaaKortti(k);
            }
        }
        alku.sekoita();
    }

    /**
     * Alustaa 7 alapakkaa jakamalla niihin 1-7 korttia järjestyksessä, ja
     * kääntää pakkojen päällimmäiset kortit kuvapuoli ylöspäin.
     */
    private void alustaAlapakat() {
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < alapakat.length; j++) {
                alapakat[j].lisaaKortti(alku.poistaKortti());
            }
            alapakat[i].kaannaPaallimmainen();
        }
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

    /**
     * Poistaa pakasta halutun määrän kortteja ja siirtää ne johonkin
     * alapakkaan, jos siirrettävän pakan pohjimmainen kortti on yhden pienempi
     * kuin alapakan päällimmäinen ja ne ovat erivärisiä, tai siirrettävän pakan
     * pohjimmainen kortti on kuningas ja alapakka on tyhjä.
     *
     * @param mista Pakka, josta siirretään.
     * @param mihin Pakka, johon siirretään.
     * @param maara Siirrettävien korttien määrä.
     */
    private void siirraKorttejaAla(Pakka mista, Pakka mihin, int maara) {
        Pakka pak = mista.poistaKortteja(maara);
        if ((pak.paallimmainen().getPuoli() == 1) && ((pak.paallimmainen().getNumero() == 13
                && mihin.koko() == 0) || (mihin.paallimmainen().getNumero()
                - pak.paallimmainen().getNumero() == 1 && mihin.paallimmainen().getVari()
                - pak.paallimmainen().getVari() != 0))) {
            if (mista.getTunnus() == 3) {
                mista.kaannaPaallimmainen();
            }
            mihin.lisaaKortteja(pak);
        } else {
            mista.lisaaKortteja(pak);
        }
    }

    /**
     * Siirtää yhden kortin halutusta pakasta johonkin yläpakkaan, jos
     * siirrettävä kortti on ässä ja yläpakka on tyhjä, tai jos siirrettävä
     * kortti on yhden suurempi ja samaa maata kuin yläpakankortti.
     *
     * @param mista Pakka, josta siirretään.
     * @param mihin Pakka, johon siirretään.
     */
    private void siirraKorttiYla(Pakka mista, Pakka mihin) {
        Kortti kor = mista.poistaKortti();
        if ((mihin.koko() == 0 && kor.getNumero() == 1)) {
            this.sKYapu(mihin, mista, kor);
        } else if (mihin.koko() > 0 && kor.getNumero() - mihin.paallimmainen().getNumero() == 1
                && kor.getMaa() == mihin.paallimmainen().getMaa()) {
            this.sKYapu(mihin, mista, kor);
        } else {
            mista.lisaaKortti(kor);
        }
    }

    /**
     * Siirtää kortin pakasta toiseen ja kaantaa pakan "mihin" päällimmäisen
     * kortin, jos "mihin" on alapakka.
     *
     * @param mihin Pakka, josta siirretään.
     * @param mista Pakka, johon siirretään.
     * @param kor Kortti, joka siirretään.
     */
    private void sKYapu(Pakka mihin, Pakka mista, Kortti kor) {
        mihin.lisaaKortti(kor);
        if (mista.getTunnus() == 3) {
            mista.kaannaPaallimmainen();
        }
    }

    /**
     * Tarkistaa mihin pakkaan kortit ovat menossa, ja valitsee sen perusteella
     * käytettävän metodin. Alkupakasta ja tyjästä pakasta ei voi siirtää
     * kortteja. Kaantopakasta ja ylapakasta ei voi siirtää yli yhtä korttia.
     *
     * @param mista Mistä pakasta.
     * @param mihin Mihin pakkaa.
     * @param maara Korttien maara.
     */
    public void siirraKortteja(Pakka mista, Pakka mihin, int maara) {
        if (mista.getTunnus() != 1 && mista.koko() > 0
                && !((mista.getTunnus() == 2 || mista.getTunnus() == 4) && maara > 1)) {
            if (mihin.getTunnus() == 4) {
                this.siirraKorttiYla(mista, mihin);
            } else if (mihin.getTunnus() == 3) {
                this.siirraKorttejaAla(mista, mihin, maara);
            }
        }
    }
}
