package pasianssi.pasianssi.logiikka;

/**
 * Klondyke pelin logiikka, joka perii luokan Peli.
 *
 * @author mikko
 */
public class Klondyke extends Peli {

    private Pakka[] alapakat;
    private Pakka[] ylapakat;

    /**
     * Luo seitsemän tyhjää alapakkaa ja neljä tyhjää yläpakkaa.
     */
    public Klondyke() {
        alapakat = new Pakka[7];
        for (int i = 0; i < alapakat.length; i++) {
            alapakat[i] = new Pakka(3);
        }
        ylapakat = new Pakka[4];
        for (int i = 0; i < ylapakat.length; i++) {
            ylapakat[i] = new Pakka(4);
        }
    }

    /**
     * Palauttaa alapakat.
     *
     * @return Alapakat.
     */
    public Pakka[] getAlapakat() {
        return alapakat;
    }

    /**
     * Palauttaa yläpakat.
     *
     * @return Yläpakat.
     */
    public Pakka[] getYlapakat() {
        return ylapakat;
    }

    /**
     * Alustaa alkupakan ja alapakat.
     */
    public void alustaPeli() {
        this.alustaAlkupakka();
        this.alustaAlapakat();
    }
    
    /**
     * Alustaa 7 alapakkaa jakamalla niihin 1-7 korttia järjestyksessä, ja
     * kääntää pakkojen päällimmäiset kortit kuvapuoli ylöspäin.
     */
    public void alustaAlapakat() {
        for (int i = 0; i < 7; i++) {
            for (int j = i; j < alapakat.length; j++) {
                alapakat[j].lisaaKortti(this.getAlku().poistaKortti());
            }
            alapakat[i].kaannaPaallimmainen();
        }
    }

    /**
     * Poistaa kaikki kortit kaikista pakoista.
     */
    public void tyhjennaKaikki() {
        this.getAlku().poistaKortteja(52);
        this.getKaanto().poistaKortteja(52);
        for (int i = 0; i < 4; i++) {
            this.getYlapakat()[i].poistaKortteja(52);
        }
        for (int i = 0; i < 7; i++) {
            this.getAlapakat()[i].poistaKortteja(52);
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
        if (pak.paallimmainen().getPuoli() == 1) {
            if (mihin.koko() == 0) {
                if (pak.paallimmainen().getNumero() == 13) {
                    mihin.lisaaKortteja(pak);
                    this.kaantoapu(mista);
                } else {
                    mista.lisaaKortteja(pak);
                }
            } else {
                if ((mihin.paallimmainen().getNumero() - pak.paallimmainen().getNumero() == 1
                        && mihin.paallimmainen().getVari() - pak.paallimmainen().getVari() != 0)) {
                    mihin.lisaaKortteja(pak);
                    this.kaantoapu(mista);
                } else {
                    mista.lisaaKortteja(pak);
                }
            }

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
            mihin.lisaaKortti(kor);
            this.kaantoapu(mista);
        } else if (mihin.koko() > 0 && kor.getNumero() - mihin.paallimmainen().getNumero() == 1
                && kor.getMaa() == mihin.paallimmainen().getMaa()) {
            mihin.lisaaKortti(kor);
            this.kaantoapu(mista);
        } else {
            mista.lisaaKortti(kor);
        }
    }

    /**
     * Kääntää pakan "mihin" päällimmäisen kortin, jos "mihin" on alapakka.
     *
     * @param mihin Pakka, josta siirretään.
     */
    private void kaantoapu(Pakka mista) {
        if (mista.getTunnus() == 3 && mista.koko() > 0) {
            if (mista.paallimmainen().getPuoli() == 0) {
                mista.kaannaPaallimmainen();
            }
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

    /**
     * Tarkistaa onko peli voitettu yläpakkojen kokojen perusteella.
     *
     * @return true jos voitettu, false jos ei voitettu
     */
    public boolean tarkistaVoitto() {
        return ylapakat[0].koko() == 13 && ylapakat[1].koko() == 13
                && ylapakat[2].koko() == 13 && ylapakat[3].koko() == 13;
    }
}
