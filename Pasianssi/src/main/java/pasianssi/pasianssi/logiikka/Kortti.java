package pasianssi.pasianssi.logiikka;

/**
 * Luokka kuvaa yksittäistä pelikorttia, jolla on numero, maa ja puoli.
 *
 * @author mikko
 */
public class Kortti {

    private int numero;
    private int maa;
    private int vari;
    private int puoli;
    private String kuva;

    /**
     * Luo uuden kortin annetun numeron ja maan perusteella. Puoli on aluksi 0
     * eli selkäpuoli ylöspäin. Antaa kortille värin maan perusteella.
     *
     * @param numero Annettu numero välillä 1-13.
     * @param maa Annettu maa välillä 1-4.
     */
    public Kortti(int numero, int maa) {
        this.numero = numero;
        this.maa = maa;
        vari = maa % 2;
        this.puoli = 0;
        this.kuva = kuvanOsoite(numero, maa);
    }

    /**
     * Metodi kääntää kortin toisinpäin vaihtamalla nollan ykköseksi tai ykkösen
     * nollaksi.
     */
    public void kaanna() {
        this.puoli = 1 - this.puoli;
    }

    /**
     * Palauttaa kortin numeron.
     *
     * @return Kortin numero.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Palauttaa kortin maan. 1 = hertta, 2 = pata, 3 = ruutu ja 4 = risti.
     *
     * @return Kortin maa.
     */
    public int getMaa() {
        return maa;
    }

    /**
     * Palauttaa kortin varin. 0 = musta ja 1 = punainen.
     *
     * @return Kortin vari.
     */
    public int getVari() {
        return vari;
    }

    /**
     * Kertoo, kumpi puoli kortilla on ylöspäin. 0 = selkäpuoli ja 1 on
     * kuvapuoli.
     *
     * @return Kortin puoli.
     */
    public int getPuoli() {
        return puoli;
    }

    /**
     * Palauttaa kortin kuvan osoitteen.
     *
     * @return Kuvan osoite
     */
    public String getKuva() {
        return kuva;
    }

    /**
     * Määrittää kuvan osoitteen maan ja numeron perusteella.
     *
     * @param num
     * @param maa
     * @return Kuvan osoite
     */
    private String kuvanOsoite(int num, int maa) {
        String osoite = "src/main/resources/images/kortit/Cards/Classic/";
        if (maa == 1) {
            osoite = osoite + "h";
        } else if (maa == 2) {
            osoite = osoite + "s";
        } else if (maa == 3) {
            osoite = osoite + "d";
        } else if (maa == 4) {
            osoite = osoite + "c";
        }
        if (num < 10) {
            osoite = osoite + "0";
        }
        osoite = osoite + Integer.toString(num) + ".png";
        return osoite;
    }

    public String toString() {
        String maa2 = "";
        if (this.maa == 1) {
            maa2 = "Hertta";
        } else if (this.maa == 2) {
            maa2 = "Pata";
        } else if (this.maa == 3) {
            maa2 = "Ruutu";
        } else if (this.maa == 4) {
            maa2 = "Risti";
        }
        return maa2 + " " + Integer.toString(this.numero);
    }
}
