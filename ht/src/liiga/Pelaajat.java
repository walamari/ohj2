
package liiga;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;



/**
 * Liigan pelaajat joka osaa mm. lis�t� uuden pelaajan
 * @author Valtteri Peltoniemi & Konsta K�ht�v�
 * @version 7.3.2019
 *
 */
public class Pelaajat {
    private static final int MAX_PELAAJIA = 11;
    private Pelaaja[] alkiot = new Pelaaja[MAX_PELAAJIA];
    private int lkm;
    private int kasvatus = 5;
    private String tiedostonPerusNimi = "nimet";
    private boolean muutettu = false;
    
    
    /**
     * Lukee tiedostosta liigan tiedot
     * @param tiedosto  tiedoston perusnimi
     * @throws SailoException poikkeus
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     *  Pelaajat pelj = new Pelaajat();
     *  Pelaaja Eki = new Pelaaja(); Eki.vastaaErikPerrin();
     *  Pelaaja Aki = new Pelaaja(); Aki.vastaaErikPerrin();
     *  String hakemisto = "TestiJoukkue";
     *  String tiedNimi = hakemisto+"/nimet";
     *  File ftied = new File(tiedNimi+".dat");
     *  File dir = new File(hakemisto);
     *  dir.mkdir();
     *  ftied.delete();
     *  pelj.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  pelj.lisaa(Eki);
     *  pelj.lisaa(Aki);
     *  pelj.tallenna();
     *  pelj = new Pelaajat();
     *  pelj.lueTiedostosta(tiedNimi);
     *  pelj.lisaa(Eki);
     *  pelj.tallenna();
     *  ftied.delete() === true;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === true;
     *  dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String tiedosto) throws SailoException {
        
        setTiedostonPerusNimi(tiedosto);
        try (Scanner fi = new Scanner(new FileInputStream(new File(getTiedostonNimi())))) { 
            
            while ( fi.hasNext() ) {
                String rivi = fi.nextLine();
                rivi = rivi.trim();
                if ( rivi.equals("") || rivi.charAt(0) == ';' ) continue; 
                    Pelaaja pelaaja = new Pelaaja(); 
                    pelaaja.parse(rivi); 
                    lisaa(pelaaja);   
            } 
            
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea"); 
        }
    }
    
    
    /**
     * Tallentaa tiedostoon joukkueen tiedot
     * @throws SailoException poikkeus
     */
    public void tallenna() throws SailoException {
        if ( muutettu == false) return;
        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); 
        ftied.renameTo(fbak); 
        
        
        try ( PrintStream fo = new PrintStream(new PrintStream(ftied.getCanonicalPath())) ) {
            for (int i = 0; i < getLkm(); i++) {
                fo.println(alkiot[i].toString());
            }
      
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
            
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }
        muutettu = false;
    }
    
    /**
     * Lukee tiedoston
     * Luetaan annetun nimisest� tiedostosta
     * @throws SailoException poikkeus
     */
    public void lueTiedostosta() throws SailoException { 
        lueTiedostosta(getTiedostonPerusNimi()); 
    }
 
   
  /** 
   * Palauttaa tiedoston nimen, jota k�ytet��n tallennukseen 
   * @return tallennustiedoston nimi 
   */ 
  public String getTiedostonPerusNimi() { 
      return tiedostonPerusNimi; 
  } 
  
  
  /** 
   * Asettaa tiedoston perusnimen
   * @param nimi tallennustiedoston perusnimi 
   */ 
  public void setTiedostonPerusNimi(String nimi) { 
      tiedostonPerusNimi = nimi; 
  } 
  
  
  /** 
   * Palauttaa tiedoston nimen, jota k�ytet��n tallennukseen 
   * @return tallennustiedoston nimi 
   */ 
  public String getTiedostonNimi() { 
      return getTiedostonPerusNimi() + ".dat"; 
  } 
  
  /**
   * palauttaa vara tiedoston nimen
  * @return varakopitoitu tiedoston nimen
  */
public String getBakNimi() {
      return tiedostonPerusNimi + ".bak";
  }
   


    /**
     * Lis�� uuden pelaajan tietorakenteeseen.
     * @param pelaaja lis�tt�v�n pelaajan viite
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Pelaajat pelaajat = new Pelaajat();
     * Pelaaja pelaaja1 = new Pelaaja();
     * Pelaaja pelaaja2 = new Pelaaja();
     * pelaajat.getLkm() === 0;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 1;
     * pelaajat.lisaa(pelaaja2); pelaajat.getLkm() === 2;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 3;
     * pelaajat.anna(0) === pelaaja1;
     * pelaajat.anna(1) === pelaaja2;
     * pelaajat.anna(2) === pelaaja1;
     * pelaajat.anna(1) == pelaaja1 === false;
     * pelaajat.anna(1) == pelaaja2 === true;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 4;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 5;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 6;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 7;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 8;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 9;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 10;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 11;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 12;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 13;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 14;
     * pelaajat.lisaa(pelaaja1); pelaajat.getLkm() === 15;
     * </pre>
     */
    public void lisaa(Pelaaja pelaaja)  {
        if(lkm >= alkiot.length) {
            Pelaaja []  uusiTaulukko = new Pelaaja [MAX_PELAAJIA + kasvatus];
            System.arraycopy(alkiot, 0, uusiTaulukko, 0, alkiot.length);
            alkiot = uusiTaulukko;
            kasvatus += 5;
        }
        alkiot[lkm++] = pelaaja;
        muutettu = true;
    }
    
    
    /**
     * palauttaa liigan pelaajien lukum��r�n
     * @return pelaajien lukum��r�
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * Palauttaa viitteen i:teen j�seneen
     * @param i monennenko pelaajan viite kysyt��n
     * @return indeksiss� i olevan pelaajan viite
     * @throws IndexOutOfBoundsException jos indeksi i:t� ei ole m��ritetyll� alueella
     */
    public Pelaaja anna(int i) throws IndexOutOfBoundsException {
        if(i < 0 || lkm <= i) throw new IndexOutOfBoundsException("V��r� indeksi:" + " " + i);
        return alkiot[i];
    }
    
    
    /**
     * Korvaa tai lis�� pelaajan
     * @param pelaaja pelaaja
     * 
     * <pre name="test">
     * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
     * Pelaajat pelaajat = new Pelaajat();
     * Pelaaja eki = new Pelaaja(), tepa = new Pelaaja();
     * eki.rekisteroi(); tepa.rekisteroi();
     * pelaajat.getLkm() === 0;
     * pelaajat.korvaaTaiLisaa(eki); pelaajat.getLkm() === 1;
     * pelaajat.korvaaTaiLisaa(tepa); pelaajat.getLkm() === 2;
     * Pelaaja jake = eki.clone();
     * pelaajat.korvaaTaiLisaa(jake); pelaajat.getLkm() === 2;
     * </pre>
     */
    public void korvaaTaiLisaa(Pelaaja pelaaja) {
        int id = pelaaja.getPelaajaid();
        for(int i = 0; i<lkm; i++) {
            if(alkiot[i].getPelaajaid() == id) {
                alkiot[i] = pelaaja;
                muutettu = true;
                return;
            }
        }
        lisaa(pelaaja);
    }
     
    /**  
     * Poistaa pelaajan jolla on valittu pelaajaid   
     * @param id poistettavan pelaajan pelaajaid  
     * @return 1 jos poistettiin, 0 jos ei l�ydy  
     * @example  
     * <pre name="test">  
     * #THROWS SailoException   
     * Pelaajat pelaajat = new Pelaajat();  
     * Pelaaja joku1 = new Pelaaja(), joku2 = new Pelaaja(), joku3 = new Pelaaja();  
     * joku1.rekisteroi(); joku2.rekisteroi(); joku3.rekisteroi();  
     * int id1 = joku1.getPelaajaid();  
     * pelaajat.lisaa(joku1); pelaajat.lisaa(joku2); pelaajat.lisaa(joku3);  
     * pelaajat.poista(id1+1) === 1;  
     * pelaajat.annaId(id1+1) === null; pelaajat.getLkm() === 2;  
     * pelaajat.poista(id1) === 1; pelaajat.getLkm() === 1;  
     * pelaajat.poista(id1+3) === 0; pelaajat.getLkm() === 1;  
     * </pre>  
     *   
     */  
     public int poista(int id) {  
         int ind = etsiId(id);  
         if (ind < 0) return 0;  
         lkm--;  
         for (int i = ind; i < lkm; i++)  
             alkiot[i] = alkiot[i + 1];  
         alkiot[lkm] = null;  
         muutettu = true;  
         return 1;  
     }  
    
     /**  
       * Etsii pelaajan id:n perusteella  
       * @param id tunnusnumero, jonka mukaan etsit��n  
       * @return pelaaja jolla etsitt�v� id tai null  
       */  
      public Pelaaja annaId(int id) {  
          Pelaaja pelaaja = new Pelaaja();
          for (int i = 0; i< alkiot.length; i++) {  
              if (id == pelaaja.getPelaajaid()) return pelaaja;  
          }  
          return null;  
      }  
   
   
      /**  
       * Etsii pelaajan id:n perusteella  
       * @param id tunnusnumero, jonka mukaan etsit��n  
       * @return l�ytyneen pelaajan indeksi tai -1 jos ei l�ydy  
       * <pre name="test">  
       * #THROWS SailoException   
       * Pelaajat pelaajat = new Pelaajat();  
       * Pelaaja joku1 = new Pelaaja(), joku2 = new Pelaaja(), joku3 = new Pelaaja();   
       * joku1.rekisteroi(); joku2.rekisteroi(); joku3.rekisteroi();  
       * int id1 = joku1.getPelaajaid();  
       * pelaajat.lisaa(joku1); pelaajat.lisaa(joku2); pelaajat.lisaa(joku3); 
       * pelaajat.etsiId(id1+1) === 1;  
       * pelaajat.etsiId(id1+2) === 2;  
       * </pre>  
       */  
      public int etsiId(int id) {  
          for (int i = 0; i < lkm; i++)  
              if (id == alkiot[i].getPelaajaid()) return i;  
          return -1;  
      }  
   
     
    /**
     * testiohjelma pelaajille
     * @param args ei k�yt�ss�
     */
    public static void main(String[] args) {
        
        Pelaajat pelaajat = new Pelaajat();
        Pelaaja erik = new Pelaaja();
        Pelaaja erik2 = new Pelaaja(); 
        erik.rekisteroi(); erik.vastaaErikPerrin();
        erik2.rekisteroi(); erik2.vastaaErikPerrin();
    
    
            pelaajat.lisaa(erik);
            pelaajat.lisaa(erik2);
        
        System.out.println("=================== Pelaajat testi ===================="); //Selvennet��n tulostusta
        
        for (int i = 0; i < pelaajat.getLkm(); i++) {
            Pelaaja pelaaja = pelaajat.anna(i); 
            System.out.println("Pelaajan numero: " + i);
            pelaaja.tulosta(System.out);
        }
        
        Pelaaja erik3 = new Pelaaja();
        erik3.rekisteroi(); erik3.vastaaErikPerrin();   
        pelaajat.lisaa(erik3);
 
    
        System.out.println("=================== Pelaajat testi ====================");
        
        for (int i = 0; i < pelaajat.getLkm(); i++) {
            Pelaaja pelaaja = pelaajat.anna(i); 
            System.out.println("Pelaajan numero: " + i);
            pelaaja.tulosta(System.out);
        }

    }




}
