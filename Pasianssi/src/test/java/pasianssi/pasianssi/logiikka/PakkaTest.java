package pasianssi.pasianssi.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PakkaTest {

    Pakka p;

    @Before
    public void setUp() {
        p = new Pakka(5);
    }

    @Test
    public void konstruktoriLuoTyhjanPakan() {
        assertEquals(0, p.getKortit().size());
    }

    @Test
    public void pakkaanVoiLisataKortin() {
        p.lisaaKortti(new Kortti(4, 4));
        assertEquals(1, p.koko());
    }

    @Test
    public void pakastaVoiPoistaaKortin() {
        p.lisaaKortti(new Kortti(4, 4));
        assertEquals(1, p.koko());
        p.poistaKortti();
        assertEquals(0, p.koko());
    }

    @Test
    public void kortinPoistaminenPalauttaaPaallimmaisenKortin() {
        p.lisaaKortti(new Kortti(1, 1));
        p.lisaaKortti(new Kortti(2, 2));
        p.lisaaKortti(new Kortti(3, 3));
        assertEquals(3, p.poistaKortti().getNumero());
    }

    @Test
    public void kaannaEnsimmainenKorttiKaantaaPaallimmaisen() {
        p.lisaaKortti(new Kortti(1, 1));
        p.lisaaKortti(new Kortti(2, 2));
        p.lisaaKortti(new Kortti(3, 3));
        p.kaannaPaallimmainen();
        assertEquals(1, p.poistaKortti().getPuoli());
        assertEquals(0, p.poistaKortti().getPuoli());
    }

    @Test
    public void poistaKorttejaPoistaaOikeanMaaranKortteja() {
        p.lisaaKortti(new Kortti(1, 1));
        p.lisaaKortti(new Kortti(2, 2));
        p.lisaaKortti(new Kortti(3, 3));
        p.poistaKortteja(2);
        assertEquals(1, p.koko());
    }

    @Test
    public void poistaKorttejaPalauttaaOikenKokoisenPakan() {
        p.lisaaKortti(new Kortti(1, 1));
        p.lisaaKortti(new Kortti(2, 2));
        p.lisaaKortti(new Kortti(3, 3));
        assertEquals(2, p.poistaKortteja(2).koko());
    }

    @Test
    public void lisaaKorttejaLisaaOikeanMaaranKortteja() {
        p.lisaaKortti(new Kortti(1, 1));
        p.lisaaKortti(new Kortti(2, 2));
        p.lisaaKortti(new Kortti(3, 3));
        Pakka p2 = new Pakka(5);
        p2.lisaaKortti(new Kortti(4, 4));
        p2.lisaaKortti(new Kortti(5, 5));
        p.lisaaKortteja(p2);
        assertEquals(5, p.koko());
    }

    @Test
    public void paallimainenNayttaaPaallimaisen() {
        p.lisaaKortti(new Kortti(1, 1));
        p.lisaaKortti(new Kortti(2, 2));
        p.lisaaKortti(new Kortti(3, 3));
        assertEquals(3, p.paallimmainen().getMaa());
    }

    @Test
    public void paallimmainenPalauttaaNullJosPakkaOnTyhja() {
        assertEquals(null, p.paallimmainen());
    }

    @Test
    public void kokoNayttaaOikeanKoon() {
        for (int i = 0; i < 64; i++) {
            p.lisaaKortti(new Kortti(1, 1));
        }
        assertEquals(64, p.koko());
    }

    @Test
    public void poistaKorttiTyhjallePakallePalauttaaNull() {
        assertEquals(null, p.poistaKortti());
    }

    @Test
    public void sekoitaSekoittaaPakan() {
        for (int i = 1; i <= 13; i++) {
            p.lisaaKortti(new Kortti(i, i));
        }
        p.sekoita();
        assertEquals(13, p.koko());
        boolean ret = false;
        for (int i = 13; i >= 1; i--) {
            Kortti k = p.poistaKortti();
            if (k.getNumero() != i) {
                ret = true;
            }
        }
        assertEquals(true, ret);
    }
}
