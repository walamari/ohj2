package liiga;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

import static apumetodit.Apumetodit.*;

/**
 * Joukkue joka osaa mm. itse huolehtia tunnusnumerostaan
 * 
 * @author Valtteri Peltoniemi & Konsta K�ht�v�
 * @version 7.3.2019
 *
 */
public class Joukkue {

    private int joukkueId;
    private int pelaajanId;
    private String joukkue = "";
    private String kaupunki = "";
    private String perustusvuosi = "0";
    private String sMestaruudet = "0";
    private String hopeat = "0";
    private String pronssit = "0";
    private String valmentaja = "";
    

    //saa k�ytt�� koska se laskee montako oliota ollaan tehty t�st� luokasta, kaikki oliot p��see k�siksi
    private static int seuraavaNro = 1;
    
    /**
     *  Alustetaan joukkue
     */
    public Joukkue() {
        // ei tarvitse viel� k�ytt��
    }
    
    
    /**
     * Apumetodi, jolla saadaan t�ytetty� arvoja joukkueelle
     * @param numero viite joukkueeseen mist� on kyse
     */
    public void vastaaJoukkue(int numero) {
        pelaajanId = numero;
        joukkue = "Jyp";  
        kaupunki = "Jyv�skyl�";
        perustusvuosi = "1923";
        sMestaruudet = "2";
        hopeat = "2";
        pronssit = "5";
        valmentaja = "Risto Dufva";
    }
    
    
    /**
     * Asettaa joukkueid:n ja samalla varmistaa ett�
     * seuraava numero on aina suurempi kuin t�h�n menness� suurin.
     * @param nr asetettava id
     */
    private void setJoukkueNro(int nr) {
        joukkueId = nr;
        if (joukkueId >= seuraavaNro) seuraavaNro = joukkueId + 1;
    }
    

    /**
     * Palauttaa harrastuksen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return harrastus tolppaeroteltuna merkkijonona
     * @example
     * <pre name="test">
     *   Joukkue jouk = new Joukkue();
     *   jouk.parse("   3  |  Jyv�skyl�   | 1962");
     *   jouk.toString().startsWith("3|Jyv�skyl�|1962|") === true;
     * </pre>
     */
    @Override
    public String toString() {
        return "" + getJoukkuenumero() + "|" + joukkue + "|" + kaupunki + "|" + perustusvuosi + "|" + sMestaruudet+ "|" + hopeat + "|" + pronssit + "|" + valmentaja;
    }
    
    /**
     * Selvitt�� pelaajan tiedot | erotellusta merkkijonosta
     * Pit�� huolen ett� seuraavaNro on suurempi kuin tuleva joukkuuenid.
     * @param rivi josta joukkueen tiedot otetaan
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setJoukkueNro(Mjonot.erota(sb, '|', getJoukkuenumero()));
        joukkue = Mjonot.erota(sb, '|', joukkue);
        kaupunki = Mjonot.erota(sb, '|', kaupunki);
        perustusvuosi = Mjonot.erota(sb, '|', perustusvuosi);
        sMestaruudet = Mjonot.erota(sb, '|', sMestaruudet);
        hopeat = Mjonot.erota(sb, '|', hopeat);
        pronssit = Mjonot.erota(sb, '|', pronssit);
        valmentaja = Mjonot.erota(sb, '|', valmentaja);
        pelaajanId = Mjonot.erota(sb, '|', pelaajanId);
        
    }
    
    
    /**
     * Alustetaan tietyn pelaajan joukkue
     * @param pelaajanId pelaajan viitenumero
     */
    public Joukkue(int pelaajanId) {
        this.pelaajanId = pelaajanId;
        
    }
    

    /**
     * Tulostaa joukkueen tiedot
     * @param out Tietovirta mihin tulostetaan
     * 
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", pelaajanId) + " " + joukkue + " " + " " + kaupunki + " " +  perustusvuosi + " " + sMestaruudet + " " + hopeat + " " + pronssit+ " " + valmentaja + " " + pelaajanId + " " + rand(100,999));
       
    }
    
    
    /**
     * Tulostaa joukkuuen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    @Override
    public boolean equals(Object obj) {
        if ( obj == null ) return false;
        return this.toString().equals(obj.toString());
    }
    
    @Override
    public int hashCode() {
        return joukkueId;
    }

    /**
     * Antaa joukkueelle seuraavan rekisterinumeron
     * @return joukkueen uusi rekisterinumero
     * @example
     * <pre name="test">
     * Joukkue joukkue1 = new Joukkue();
     * joukkue1.getJoukkuenumero() === 0;
     * joukkue1.rekisteroi();
     * Joukkue joukkue2 = new Joukkue(); 
     * joukkue2.rekisteroi();
     * int j1 = joukkue1.getJoukkuenumero();
     * int j2 = joukkue2.getJoukkuenumero();
     * j1 === j2-1;
     * </pre>
     * 
     */
    public int rekisteroi() {
        if (joukkueId != 0 ) return joukkueId; //est�� saman joukkueen rekisteroitymisen
        joukkueId = seuraavaNro;
        seuraavaNro++;
        return joukkueId;
        
    }
    
    
    /**
     * Palauttaa joukkueen rekisterinumeron
     * @return joukkueen rekisterinumero
     */
    public int getJoukkuenumero() {
        return joukkueId;
    }
    
    
    /**
     * Palautetaan mille pelaajalle joukkue kuuluu
     * @return pelaajan id
     */
    public int getPelaajannumero() {
        return pelaajanId;
    }
    
    /**
     * Palauttaa joukkueen merkkijonona
     * @return joukkue
     */
    public String getJoukkue() {
        return joukkue;
    }


    /**
     * Palauttaa kaupungin merkkijonona
     * @return joukkueen kaupungin
     */
    public String getKaupunki() {
        return kaupunki;
    }


    /**
     * Palauttaa vuoden merkkijonona
     * @return joukkuuen perustusvuoden
     */
    public String getVuosi() {
        return perustusvuosi;
    }

    /**
     * Palauttaa sMestaruudet merkkijonona
     * @return joukkuuen suomenmestaruudet
     */
    public String getMestaruudet() {
        return sMestaruudet;
    }
    
    /**
     * Palauttaa hopeat merkkijonona
     * @return joukkueen hopea mitalit
     */
    public String getHopeat() {
        return hopeat;
    }
    
    /**
     * Palauttaa pronssit merkkijonona
     * @return joukkueen pronssimitalit
     */
    public String getPronssit() {
        return pronssit;
    }
    
    /**
     * Palauttaa valmentajat merkkijonona
     * @return joukkueen valmentaja
     */
    public String getValmentaja() {
        return valmentaja;
    }
    

    
    /**
     * Testiohjelma Joukkue-luokalle
     * @param args ei k�yt�ss�
     */
    public static void main(String[] args) {

        Joukkue jyp2 = new Joukkue();
        Joukkue jyp = new Joukkue();
        jyp2.rekisteroi();
        jyp2.vastaaJoukkue(1);
        jyp2.tulosta(System.out);
        jyp.rekisteroi();
        jyp.vastaaJoukkue(2);
        jyp.tulosta(System.out);
        
    }

}
