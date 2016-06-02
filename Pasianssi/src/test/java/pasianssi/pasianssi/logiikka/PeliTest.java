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
    }

    @Test
    public void alustaAlkupakkaAlustaaPakanOikein() {
        p.alustaAlkupakka();
        assertEquals(52, p.getAlku().koko());
        assertEquals(0, p.getKaanto().koko());
    }

    @Test
    public void alustaAlkupakkaSekoittaaAlkupakan() {
        p.alustaAlkupakka();
        boolean ret = false;
        for (int i = 4; i >= 1; i--) {
            for (int j = 13; j >= 1; j--) {
                Kortti k1 = p.getAlku().poistaKortti();
                if (k1.getNumero() != j || k1.getMaa() != i) {
                    ret = true;
                }
            }

        }
        assertEquals(true, ret);
    }

    @Test
    public void korttiAlustaKaantoonSiirtaaKortinAlustaKaantoonOikeinpain() {
        p.alustaAlkupakka();
        p.korttiAlustaKaantoon();
        assertEquals(51, p.getAlku().koko());
        assertEquals(1, p.getKaanto().koko());
        assertEquals(1, p.getKaanto().paallimmainen().getPuoli());
        p.korttiAlustaKaantoon();
        assertEquals(50, p.getAlku().koko());
        assertEquals(2, p.getKaanto().koko());
        assertEquals(1, p.getKaanto().paallimmainen().getPuoli());
    }

    @Test
    public void korttiAlustaKaantoonAloittaaUudenKierroksenJosAlkupakkaLoppuu() {
        p.alustaAlkupakka();
        for (int i = 0; i < 53; i++) {
            p.korttiAlustaKaantoon();
        }
        assertEquals(52, p.getAlku().koko());
        assertEquals(0, p.getKaanto().koko());
    }

    @Test
    public void uudenKierroksenAloittaminenKaantaaKortitOikeinpainAlkupakkaan() {
        p.alustaAlkupakka();
        for (int i = 0; i < 53; i++) {
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

}
