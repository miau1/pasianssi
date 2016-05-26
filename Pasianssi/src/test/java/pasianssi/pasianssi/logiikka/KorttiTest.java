package pasianssi.pasianssi.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttiTest {

    Kortti k;

    @Before
    public void setUp() {
        k = new Kortti(1, 1);
    }

    @Test
    public void konstruktoriAsettaaOikeanMaan() {
        assertEquals(1, k.getMaa());
    }

    @Test
    public void konstruktoriAsettaaOikeanNumero() {
        assertEquals(1, k.getNumero());
    }

    @Test
    public void konstruktoriAsettaaOikeanVarin() {
        assertEquals(1, k.getVari());
        Kortti k2 = new Kortti(2, 2);
        assertEquals(0, k2.getVari());
    }

    @Test
    public void konstruktoriAsettaaPuolenOikein() {
        assertEquals(0, k.getPuoli());
    }

    @Test
    public void korttiKaantyyOikein() {
        k.kaanna();
        assertEquals(1, k.getPuoli());
        k.kaanna();
        assertEquals(0, k.getPuoli());
    }
}
