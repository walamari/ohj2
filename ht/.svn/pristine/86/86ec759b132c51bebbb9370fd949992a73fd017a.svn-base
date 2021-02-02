package liiga;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

import static apumetodit.Apumetodit.*;


/**
 * Joukkueen pelaaja joka osaa mm. itse huolehtia tunnusnumerostaan
 * 
 * @author Valtteri Peltoniemi & Konsta Kähtävä
 * @version 7.3.2019
 *
 */
public class Pelaaja implements Cloneable  {


    private int pelaajanId;
    private String  nimi = "";
    private int pelipaikkaId;
    private int joukkueId;
    private String pelipaikka;
    private String[] paikkataulukko = {"hyökkääjä", "puolustaja", "maalivahti"};

    
    
    
    //saa käyttää koska se laskee montako oliota ollaan tehty tästä luokasta, kaikki oliot pääsee käsiksi
    private static int seuraavaNro = 1;
    
    
    /**
     * Tehdään identtinen klooni pelaajasta
     * @return Object kloonattu pelaaja
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Pelaaja pelaaja = new Pelaaja();
     *   pelaaja.parse("   3  |  Teemu Selänne   | Hyökkääjä");
     *   Pelaaja kopio = pelaaja.clone();
     *   kopio.toString() === pelaaja.toString();
     *   pelaaja.parse("   4  |  Teemu Selänne   | Hyökkääjä");
     *   kopio.toString().equals(pelaaja.toString()) === false;
     * </pre>
     */
    @Override
    public  Pelaaja clone() throws CloneNotSupportedException {
        Pelaaja uusi;
        uusi = (Pelaaja) super.clone();
        return uusi;
    }
    
    
    /**
      * Asettaa pelaajanid:n ja samalla varmistaa että
      * seuraava numero on aina suurempi kuin tähän mennessä suurin.
      * @param nr asetettava id
      */
     private void setTunnusNro(int nr) {
         pelaajanId = nr;
         if (pelaajanId >= seuraavaNro) seuraavaNro = pelaajanId + 1;
     }
    
    /**
     * Apumetodi, jolla saadaan annettua testitiedot pelaajalle
     */
    public void vastaaErikPerrin() {
        this.nimi = "Erik Perrin" + " " + rand(1, 999 );
        this.pelipaikka = paikkataulukko[pelipaikkaId];
        this.joukkueId = 1;

    }
    
    
    /** 
      * Palauttaa pelaajan tiedot merkkijonona jonka voi tallentaa tiedostoon. 
      * @return pelaajan tolppaeroteltuna merkkijonona  
      * 
      * @example
      * <pre name="test">
      *   Pelaaja pelaaja = new Pelaaja();
      *   pelaaja.parse("   3  |  Erik Perrin   | Hyökkääjä");
      *   pelaaja.toString().startsWith("3|Erik Perrin|Hyökkääjä|") === true;
      * </pre> 
      */ 
     @Override 
     public String toString() { 
         return "" + getPelaajaid() + "|" + nimi + "|" + pelipaikka + "|" + getJoukkue();
        
     } 
    
    
   /**
     * Selvitää pelaajan tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva pelaajanid.
     * @param rivi josta pelaajan tiedot otetaan
     * 
     *  
     * @example
     * <pre name="test">
     *   Pelaaja pelaaja = new Pelaaja();
     *   pelaaja.parse("   3  |  Erik Perrin   | Hyökkääjä");
     *   pelaaja.getPelaajaid() === 3;
     *   pelaaja.toString().startsWith("3|Erik Perrin|Hyökkääjä|") === true;
     *
     *   pelaaja.rekisteroi();
     *   int n = pelaaja.getPelaajaid();
     *   pelaaja.parse(""+(n+20));       
     *   pelaaja.rekisteroi();          
     *   pelaaja.getPelaajaid() === n+20;
     *     
     * </pre>
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getPelaajaid()));
        nimi = Mjonot.erota(sb, '|', nimi);
        pelipaikka = Mjonot.erota(sb, '|', pelipaikka);
        joukkueId = Mjonot.erota(sb, '|', getJoukkue());

    }
   
    
    /**
     * Tulostaa pelaajan tiedot
     * @param out Tietovirta mihin tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", pelaajanId) + " " + nimi + " " + pelipaikka);
            
    }
    
    /**
     * Mahdollisuus tulostaa erilaisiin tietovirtoihin
     * @param os tietovirta mihin tulostetaan
     */
    public void tulosta(OutputStream os) {
        
        tulosta(new PrintStream(os));
    }

    /**
     * Antaa pelaajille seuraavan rekisterinumeron
     * @return pelaajan uusi tunnusnumero
     * @example
     * <pre name="test">
     * Pelaaja pelaaja1 = new Pelaaja();
     * pelaaja1.getPelaajaid() === 0;
     * pelaaja1.rekisteroi();
     * Pelaaja pelaaja2 = new Pelaaja(); 
     * pelaaja2.rekisteroi();
     * int n1 = pelaaja1.getPelaajaid();
     * int n2 = pelaaja2.getPelaajaid();
     * n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        if (pelaajanId != 0 ) return pelaajanId;    //estää saman pelaajan rekisteröitymisen
        pelaajanId = seuraavaNro;
        seuraavaNro++;
        return pelaajanId;
    }
    
    
    /**
     * Palauttaa pelaajan rekisterinumeron
     * @return pelaajan rekisterinumeron
     */
    public int getPelaajaid() {
        return pelaajanId;
    }
    
    /**
     * Palauttaa pelaajan rekisterinumeron
     * @return pelaajan rekisterinumeron
     */
    public int getPelipaikkaid() {
        return pelipaikkaId;
    }
    
    /**
     * Palauttaa pelaajan joukkueen numeron
     * @return joukkuenumero
     */
    public int getJoukkue() {
        return joukkueId;
    }
    
    
    /**
     * Palauttaa pelaajan nimen
     * @return pelaajan nimi
     */
    public String getnimi() {
        return nimi;
    }
    
    
   /**
    * Eka kenttä joka on mielekäs kysyttäväksi
    * @return eknn kentän indeksi
    */
    public int ekaKentta() {
        return 1;
    }
    
    @Override
    public boolean equals(Object pelaaja) {
        if ( pelaaja == null) return false;
        return this.toString().equals(pelaaja.toString());
    }
    
    @Override
    public int hashCode() {
        return pelaajanId;
    }
    
    /**
     * Testiohjelma Pelaaja-luokalle
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Pelaaja erik = new Pelaaja();
        Pelaaja erik2 = new Pelaaja();
        erik.rekisteroi();
        erik2.rekisteroi();
        
        erik.vastaaErikPerrin();
        erik.tulosta(System.out);
        erik2.vastaaErikPerrin();
        erik2.tulosta(System.out);
    }


    /**
     * Asettaa pelaajan nimen
     * @param s pelaajalle laitettava nimi
     * @return null
     */
    public String setNimi(String s) {
        
        nimi = s;
        return null;
    }

    /**
     * Asettaa joukkueelle id:n
     * @param s pelaajalle laitettava nimi
     * @return null
     */
    public String setJoukkueId(int s) {
        joukkueId = s;
        return null;
    }
    

    
    /**
     * Antaa k:n kentän sisällön merkkijonona
     * @param k monenenko kentän sisältö palautetaan
     * @return kentän sisältö merkkijonona
     */
    public String anna(int k) {
        switch ( k ) {
        case 0: return "" + nimi;
        case 1: return "" + joukkueId;
        case 2: return "" + pelipaikka;
        default: return "Äääliö";
        }
    }


    /**
     * Antaa pelipaikan
     * @return pelipaikka
     */
    public String annaPelipaikka() {
        return pelipaikka;
    }


    /**
     * Asettaa pelipaikan
     * @param s merkkijono
     * 
     */
    public void setPelipaikka(String s) {
        pelipaikka = s;
        return; 
        
    }



}
