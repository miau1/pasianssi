**Aihe:** pasianssi

Toteutetaan pasianssikorttipeli. Pelille voi valita vaikeustason. Vaikeustaso määrittää, kuinka monta korttia vedetään kerralla pakasta ja kuinka monta kertaa pakan voi mennä ympäri. Kun aloitetaan uusi peli, ohjelma alustaa pasianssin sekoitetulla korttipakalla. Pelaaja pystyy siirtämään kortteja toistensa päälle, jos ne sopivat päällekäin. Pelaaja voittaa pelin, jos kaikki kortit on siirretty yläpakkoihin. Kun pelaaja pääsee pelin läpi, pelaajalle näytetään peliin kulunut aika. Sovellus sisältää myös ohjeet pasianssin pelaamiseen.

**Käyttäjät:** Pelaaja

**Pelaajan toiminnot:**
* vaikeustason valitseminen
* pelin pelaaminen
   * siirtojen tekeminen
   * pelin keskeyttäminen ja uuden pelin aloittaminen
* pelin ohjeiden tarkastelu

**Käyttöohjeet**

Pelin tarkoituksena on siirtää kaikki pakan kortit maittain ylhäällä oleviin pakkoihin järjestyksessä alkaen ässästä ja päättyen kuninkaaseen. Voit rakentaa korttikasoja pakan päällimmäisestä kortista ja alhaalla olevien pakkojen korteista siten, että kasan joka toinen kortti on erivärinen ja että alla oleva kortti on aina yhden suurempi kuin sen päälle tuleva kortti. 

Siirrot: 
Klikkaa ensin korttia, jonka haluat siirtää, ja sen jälkeen korttia, jonka päälle haluat siirtää. Jos siirto ei onnistu, vaikka sen pitäisi, tuplaklikkaakorttia, jota haluat siirtää, sillä olet todennäköisesti valinnut vahingossa välissä jonkun toisen kortin siirrettäväksi.

Vaikeustaso:
Jos haluat vaihtaa vaikeustasoa, paina vaikeustasonappia ja aloita uusi peli.

Helpolla pakasta vedetään yksi kortti kerrallaan, ja pakan voi käydä läpi niin monta kertaa kuin haluaa. 

Keskitasolla pakasta vedetään kolme korttia, ja pakkaa voi yhä käydä läpi loputtomasti.

Vaikealla vedetään kolme korttia, ja pakan voi käydä läpi kolme kertaa.

**Rakennekuvaus**

Luokka kortti kuvaa yhtä korttia, ja se sisältää tiedot kortin numerosta, maasta ja siitä, kumminpäin kortti on. Lisäksi kortti kertoo, missä sijaitsee kyseisen kortin kuva. Kortin voi myös kääntää toisin päin.

Pakka sisältää useita kortteja, ja kortteja voi siirtää pakasta toiseen. Pakoilla on eri tunnuksia, ja tunnusten perusteella Klondyke-luokassa päätellään, mitä rajoituksia tulee käyttää, kun kortteja siirretään.

Abstrakti luokka Peli sisältää perustoimintoja, joita voidaan käyttää useissa eri pasianssipeleissä.

Klondyke perii luokan Peli, ja se sisältää säännöt tavalliselle klondike-pasianssille.

**Luokkakaavio:**

![Alt text](/dokumentaatio/pasianssi_luokkakaavio.png)

**Sekvenssikaaviot:**

![Alt text](/dokumentaatio/sekvenssikaavio1.png)
