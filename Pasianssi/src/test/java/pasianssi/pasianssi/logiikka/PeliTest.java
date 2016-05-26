package pasianssi.pasianssi.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliTest {

    Peli p;

    @Before
    public void setUp() {
        p = new Peli();
    }

    @Test
    public void konstruktoriLuoTarvittavatTyhjatPakat() {
        assertEquals(0, p.getAlku().koko());
        assertEquals(0, p.getKaanto().koko());
        for (int i = 0; i < p.getAlapakat().length; i++) {
            assertEquals(0, p.getAlapakat()[i].koko());
        }
        for (int i = 0; i < p.getYlapakat().length; i++) {
            assertEquals(0, p.getYlapakat()[i].koko());
        }
    }

    @Test
    public void alustaPakatAlustaaPakatOikein() {
        p.alustaPakat();
        assertEquals(24, p.getAlku().koko());
        assertEquals(0, p.getKaanto().koko());
        for (int i = 0; i < p.getAlapakat().length; i++) {
            assertEquals(i + 1, p.getAlapakat()[i].koko());
            assertEquals(1, p.getAlapakat()[i].paallimmainen().getPuoli());
        }
        for (int i = 0; i < p.getYlapakat().length; i++) {
            assertEquals(0, p.getYlapakat()[i].koko());
        }
    }

    @Test
    public void alustaPakatSekoittaaAlkupakan() {
        p.alustaPakat();
        boolean ret = false;
        for (int i = 11; i >= 1; i--) {
            Kortti k1 = p.getAlku().poistaKortti();
            if (k1.getNumero() != i || k1.getMaa() != 2) {
                ret = true;
            }
        }
        for (int i = 13; i >= 1; i--) {
            Kortti k2 = p.getAlku().poistaKortti();
            if (k2.getNumero() != i || k2.getMaa() != 1) {
                ret = true;
            }
        }
        assertEquals(true, ret);
    }

    @Test
    public void korttiAlustaKaantoonSiirtaaKortinAlustaKaantoonOikeinpain() {
        p.alustaPakat();
        p.korttiAlustaKaantoon();
        assertEquals(23, p.getAlku().koko());
        assertEquals(1, p.getKaanto().koko());
        assertEquals(1, p.getKaanto().paallimmainen().getPuoli());
        p.korttiAlustaKaantoon();
        assertEquals(22, p.getAlku().koko());
        assertEquals(2, p.getKaanto().koko());
        assertEquals(1, p.getKaanto().paallimmainen().getPuoli());
    }

    @Test
    public void korttiAlustaKaantoonAloittaaUudenKierroksenJosAlkupakkaLoppuu() {
        p.alustaPakat();
        for (int i = 0; i < 25; i++) {
            p.korttiAlustaKaantoon();
        }
        assertEquals(24, p.getAlku().koko());
        assertEquals(0, p.getKaanto().koko());
    }

    @Test
    public void uudenKierroksenAloittaminenKaantaaKortitOikeinpainAlkupakkaan() {
        p.alustaPakat();
        for (int i = 0; i < 25; i++) {
            p.korttiAlustaKaantoon();
        }
        boolean ret = true;
        for (Kortti k : p.getAlku().getKortit()) {
            if (k.getPuoli() == 1) {
                ret = false;
            }
        }
        assertEquals(true, ret);
    }

    @Test
    public void kaantopakastaVoiSiirtaaKortinAlapakkaan() {
        Pakka kaanto = new Pakka(2);
        Kortti risti7 = new Kortti(7, 4);
        kaanto.lisaaKortti(risti7);
        kaanto.kaannaPaallimmainen();
        Pakka alapakka = new Pakka(3);
        Kortti hertta13 = new Kortti(13, 1);
        Kortti ruutu8 = new Kortti(8, 3);
        alapakka.lisaaKortti(hertta13);
        alapakka.lisaaKortti(ruutu8);
        alapakka.kaannaPaallimmainen();
        p.siirraKortteja(kaanto, alapakka, 1);
        assertEquals(0, kaanto.koko());
        assertEquals(3, alapakka.koko());
    }

    @Test
    public void eiVoiSiirtaaKorttiaAlapakkaanVaarallaVarilla() {
        Pakka kaanto = new Pakka(2);
        Kortti risti7 = new Kortti(7, 4);
        kaanto.lisaaKortti(risti7);
        kaanto.kaannaPaallimmainen();
        Pakka alapakka = new Pakka(3);
        Kortti hertta13 = new Kortti(13, 1);
        Kortti pata8 = new Kortti(8, 2);
        alapakka.lisaaKortti(hertta13);
        alapakka.lisaaKortti(pata8);
        alapakka.kaannaPaallimmainen();
        p.siirraKortteja(kaanto, alapakka, 1);
        assertEquals(1, kaanto.koko());
        assertEquals(2, alapakka.koko());
        kaanto.kaannaPaallimmainen();
        Kortti hertta7 = new Kortti(7, 1);
        kaanto.lisaaKortti(hertta7);
        kaanto.kaannaPaallimmainen();
        alapakka.kaannaPaallimmainen();
        Kortti hertta8 = new Kortti(8, 1);
        alapakka.lisaaKortti(hertta8);
        alapakka.kaannaPaallimmainen();
        p.siirraKortteja(kaanto, alapakka, 1);
        assertEquals(2, kaanto.koko());
        assertEquals(3, alapakka.koko());
    }

    @Test
    public void eiVoiSiirtaaKorttiaAlapakkaanVaarallaNumerolla() {
        Pakka kaanto = new Pakka(2);
        Kortti risti13 = new Kortti(13, 4);
        kaanto.lisaaKortti(risti13);
        Pakka alapakka = new Pakka(3);
        Kortti hertta13 = new Kortti(13, 1);
        Kortti pata8 = new Kortti(8, 2);
        alapakka.lisaaKortti(hertta13);
        alapakka.lisaaKortti(pata8);
        alapakka.kaannaPaallimmainen();
        p.siirraKortteja(kaanto, alapakka, 1);
        assertEquals(1, kaanto.koko());
        assertEquals(2, alapakka.koko());
    }

    @Test
    public void kuninkaanVoiSiirtaaTyhjaanAlapakkaan() {
        Pakka kaanto = new Pakka(2);
        Kortti risti13 = new Kortti(13, 4);
        kaanto.lisaaKortti(risti13);
        kaanto.kaannaPaallimmainen();
        Pakka alapakka = new Pakka(3);
        alapakka.kaannaPaallimmainen();
        p.siirraKortteja(kaanto, alapakka, 1);
        assertEquals(0, kaanto.koko());
        assertEquals(1, alapakka.koko());
    }

    @Test
    public void josAlapakastaSiirretaanKorttejaAlapakanPaallimmainenKaantyy() {
        Pakka ylapakka = new Pakka(4);
        Pakka alapakka1 = new Pakka(3);
        Kortti ruutu2 = new Kortti(2, 3);
        Kortti ruutu7 = new Kortti(7, 3);
        alapakka1.lisaaKortti(ruutu2);
        alapakka1.lisaaKortti(ruutu7);
        alapakka1.kaannaPaallimmainen();
        Pakka alapakka2 = new Pakka(3);
        Kortti hertta13 = new Kortti(13, 1);
        Kortti pata8 = new Kortti(8, 2);
        alapakka2.lisaaKortti(hertta13);
        alapakka2.lisaaKortti(pata8);
        alapakka2.kaannaPaallimmainen();
        p.siirraKortteja(alapakka1, alapakka2, 1);
        Pakka alapakka3 = new Pakka(3);
        Kortti pata9 = new Kortti(9, 2);
        Kortti pata1 = new Kortti(1, 2);
        alapakka3.lisaaKortti(pata9);
        alapakka3.lisaaKortti(pata1);
        alapakka3.kaannaPaallimmainen();
        p.siirraKortteja(alapakka3, ylapakka, 1);
        assertEquals(1, alapakka1.koko());
        assertEquals(3, alapakka2.koko());
        assertEquals(1, alapakka3.koko());
        assertEquals(1, ylapakka.koko());
        assertEquals(1, alapakka1.paallimmainen().getPuoli());
        assertEquals(1, alapakka3.paallimmainen().getPuoli());
    }

    @Test
    public void tyhjastaPakastaEiVoiSiirtaaKortteja() {
        Pakka kaanto = new Pakka(2);
        Pakka alapakka = new Pakka(3);
        Kortti hertta13 = new Kortti(13, 1);
        Kortti pata8 = new Kortti(8, 2);
        alapakka.lisaaKortti(hertta13);
        alapakka.lisaaKortti(pata8);
        alapakka.kaannaPaallimmainen();
        p.siirraKortteja(kaanto, alapakka, 1);
        assertEquals(0, kaanto.koko());
        assertEquals(2, alapakka.koko());
    }

    @Test
    public void ylaPakkaanVoiSiirtaaOikeanKortin() {
        Pakka yla = new Pakka(4);
        Kortti hertta1 = new Kortti(1, 1);
        yla.lisaaKortti(hertta1);
        Pakka ala = new Pakka(3);
        Kortti hertta2 = new Kortti(2, 1);
        ala.lisaaKortti(hertta2);
        ala.kaannaPaallimmainen();
        p.siirraKortteja(ala, yla, 1);
        assertEquals(0, ala.koko());
        assertEquals(2, yla.koko());
    }

    @Test
    public void assanVoiSiirtaaTyhjaanYlapakkaan() {
        Pakka yla = new Pakka(4);
        Pakka ala = new Pakka(3);
        Kortti hertta1 = new Kortti(1, 1);
        ala.lisaaKortti(hertta1);
        ala.kaannaPaallimmainen();
        p.siirraKortteja(ala, yla, 1);
        assertEquals(0, ala.koko());
        assertEquals(1, yla.koko());
    }

    @Test
    public void ylapakkaanVoiSiirtaaVainYhdenKortinKerrallaan() {
        Pakka yla = new Pakka(4);
        Pakka ala = new Pakka(3);
        Kortti hertta1 = new Kortti(1, 1);
        Kortti hertta2 = new Kortti(2, 1);
        Kortti hertta3 = new Kortti(3, 1);
        ala.lisaaKortti(hertta1);
        ala.lisaaKortti(hertta2);
        ala.lisaaKortti(hertta3);
        ala.kaannaPaallimmainen();
        p.siirraKortteja(ala, yla, 3);
        assertEquals(3, ala.koko());
        assertEquals(0, yla.koko());
    }

    @Test
    public void ylapakkaanEiVoiSiirtaaKorttiaVaarallaMaalla() {
        Pakka yla = new Pakka(4);
        Pakka ala = new Pakka(3);
        Kortti hertta1 = new Kortti(1, 1);
        yla.lisaaKortti(hertta1);
        Kortti ruutu2 = new Kortti(2, 3);
        ala.lisaaKortti(ruutu2);
        ala.kaannaPaallimmainen();
        p.siirraKortteja(ala, yla, 1);
        assertEquals(1, ala.koko());
        assertEquals(1, yla.koko());
    }

    @Test
    public void ylapakkaanEiVoiSiirtaaKorttiaVaarallaNumerolla() {
        Pakka yla = new Pakka(4);
        Pakka ala = new Pakka(3);
        Kortti hertta1 = new Kortti(1, 1);
        yla.lisaaKortti(hertta1);
        Kortti hertta3 = new Kortti(3, 1);
        ala.lisaaKortti(hertta3);
        ala.kaannaPaallimmainen();
        p.siirraKortteja(ala, yla, 1);
        assertEquals(1, ala.koko());
        assertEquals(1, yla.koko());
    }

    @Test
    public void alkupakastaEiVoiSiirtaaKortteja() {
        Pakka alku = new Pakka(1);
        Kortti hertta1 = new Kortti(1, 1);
        alku.lisaaKortti(hertta1);
        Pakka yla = new Pakka(4);
        p.siirraKortteja(alku, yla, 1);
        assertEquals(1, alku.koko());
        assertEquals(0, yla.koko());
    }

    @Test
    public void vaarinPainOleviaKorttejaEiVoiSiirtaa() {
        Pakka ala1 = new Pakka(3);
        Pakka ala2 = new Pakka(3);
        Kortti hertta1 = new Kortti(1, 1);
        Kortti pata2 = new Kortti(2, 2);
        Kortti hertta3 = new Kortti(3, 1);
        Kortti pata4 = new Kortti(4, 1);
        ala1.lisaaKortti(hertta3);
        ala1.lisaaKortti(pata2);
        ala1.lisaaKortti(hertta1);
        ala1.kaannaPaallimmainen();
        ala2.lisaaKortti(pata4);
        ala2.kaannaPaallimmainen();
        p.siirraKortteja(ala1, ala2, 3);
        assertEquals(3, ala1.koko());
        assertEquals(1, ala2.koko());
    }

    @Test
    public void kaantopakastaVoiSiirtaaVainYhdenKortinKerrallaan() {
        Pakka kaanto = new Pakka(2);
        kaanto.lisaaKortti(new Kortti(2, 2));
        kaanto.kaannaPaallimmainen();
        kaanto.lisaaKortti(new Kortti(1, 1));
        kaanto.paallimmainen();
        Pakka ala = new Pakka(3);
        ala.lisaaKortti(new Kortti(3, 1));
        ala.kaannaPaallimmainen();
        p.siirraKortteja(kaanto, ala, 2);
        assertEquals(2, kaanto.koko());
        assertEquals(1, ala.koko());
    }
    
    @Test
    public void ylapakastaVoiSiirtaaVainYhdenKortinKerrallaan() {
        Pakka yla = new Pakka(4);
        yla.lisaaKortti(new Kortti(2, 1));
        yla.kaannaPaallimmainen();
        yla.lisaaKortti(new Kortti(1, 1));
        yla.paallimmainen();
        Pakka ala = new Pakka(3);
        ala.lisaaKortti(new Kortti(3, 2));
        ala.kaannaPaallimmainen();
        p.siirraKortteja(yla, ala, 2);
        assertEquals(2, yla.koko());
        assertEquals(1, ala.koko());
    }
}
