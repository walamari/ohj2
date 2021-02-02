
package liiga;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Liigan joukkueet, joka osaa lis‰t‰ uuden joukkuuen
 * 
 * @author Valtteri Peltoniemi & Konsta K‰ht‰v‰
 * @version 7.3.2019
 *
 */
public class Joukkueet implements Iterable<Joukkue> {
    
    private final Collection<Joukkue> alkiot = new ArrayList<Joukkue>(); // taulukko joukkueista
    private String tiedostonPerusNimi = ""; 
    private boolean muutettu = false;
    
    /**
     * Alustetaan Joukkueet
     */
    public Joukkueet() {
        // ei tarvitse k‰ytt‰‰ viel‰
    }
  

    
    /**
     * Lukee joukkueet tiedostosta
     * @param tiedosto  tiedoston perusnimi
     * @throws SailoException poikkeus
     * 
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     *  Joukkueet jouk = new Joukkueet();
     *  Joukkue Jyp = new Joukkue(); Jyp.vastaaJoukkue(1);
     *  Joukkue Assat = new Joukkue(); Assat.vastaaJoukkue(2);
     *  String tiedNimi = "TestiJoukkue";
     *  File ftied = new File(tiedNimi+".dat");
     *  ftied.delete();
     *  jouk.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  jouk.lisaa(Jyp);
     *  jouk.lisaa(Assat);
     *  jouk.tallenna();
     *  jouk = new Joukkueet();
     *  jouk.lueTiedostosta(tiedNimi);
     *  jouk.lisaa(Jyp);
     *  jouk.tallenna();
     *  ftied.delete() === true;
     *  File fbak = new File(tiedNimi+".bak");
     *  fbak.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String tiedosto) throws SailoException {
        
        setTiedostonPerusNimi(tiedosto);
        try (Scanner fi = new Scanner(new FileInputStream(new File(getTiedostonNimi())))) {  
            
            while ( fi.hasNext() ) {
                String rivi = fi.nextLine();
                rivi = rivi.trim();
                if ( rivi.equals("") || rivi.charAt(0) == ';' ) continue; 
                    Joukkue joukkue = new Joukkue();    
                    joukkue.parse(rivi); 
                    lisaa(joukkue); 
                
            }   
            
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
            
        }
         
    }
    
    /**
     * Tallentaa joukkueet tiedostoon.
     * @throws SailoException jos talletus ep‰onnistuu
     */
    public void tallenna() throws SailoException {
        if (muutettu == false ) return;
        
        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); 
        ftied.renameTo(fbak);
        
        try ( PrintStream fo = new PrintStream(new PrintStream(ftied.getCanonicalPath())) ) {
            for (Joukkue jouk : this) {
                fo.println(jouk.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }
        muutettu = false;
    }

    
    /** 
      * Luetaan aikaisemmin annetun nimisest‰ tiedostosta 
      * @throws SailoException jos tulee poikkeus 
      */ 
     public void lueTiedostosta() throws SailoException { 
         lueTiedostosta(getTiedostonPerusNimi()); 
     } 
    
     
     /** 
      * Palauttaa tiedoston nimen, jota k‰ytet‰‰n tallennukseen 
      * @return tallennustiedoston nimi 
      */ 
     public String getTiedostonPerusNimi() { 
         return tiedostonPerusNimi; 
     } 
     
     
     /** 
      * Asettaa tiedoston perusnimen ilan tarkenninta 
      * @param nimi tallennustiedoston perusnimi 
      */ 
     public void setTiedostonPerusNimi(String nimi) { 
         tiedostonPerusNimi = nimi; 
     } 
     
     
     /** 
      * Palauttaa tiedoston nimen, jota k‰ytet‰‰n tallennukseen 
      * @return tallennustiedoston nimi 
      */ 
     public String getTiedostonNimi() { 
         return getTiedostonPerusNimi() + ".dat"; 
     } 
     
    /**
     * Lis‰‰ uuden joukkueen tietorakenteeseen
     * @param jouk lis‰tt‰v‰ joukkue
     */
    public void lisaa(Joukkue jouk)  {
        alkiot.add(jouk);
        muutettu = true;
    }
    
    
    /**
     * Palauttaa Liigan joukkueiden lukum‰‰r‰n
     * @return joukkueiden lukum‰‰r‰n
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }
    
    /**
     * Haetaan pelaajan joukkue
     * @param joukkueid pelaajan ID, jolle joukkue haetaan
     * @return tietorakenne, jossa viitteet lˆydettyyn joukkueeseen
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     * Joukkueet joukkueet = new Joukkueet();
     * Joukkue Jyp = new Joukkue(1); joukkueet.lisaa(Jyp);      //lis‰‰ joukkueeseen joukkueen ja antaa sille id:N ja etsii kyseisen id:n
     * Joukkue Assat = new Joukkue(2); joukkueet.lisaa(Assat);
     * Joukkue Karpat = new Joukkue(3); joukkueet.lisaa(Karpat);
     * 
     * Joukkue haetut;
     * haetut = joukkueet.annaJoukkue(1);
     * haetut.getJoukkue().equals(Jyp) === false;                       
     * </pre>
     */
    public Joukkue annaJoukkue(int joukkueid) {  
        int i = 0;
        for(Joukkue jouk : alkiot) {
            if (i == joukkueid) return jouk;
            i++;
        }
        return null;
        
    }
    
    
    /**
     * Iteraattori kaikkien Joukkueiden l‰pik‰ymiseen
     * @return joukkueiteraattori
     * 
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  Joukkueet jouk = new Joukkueet();
     *  Joukkue Jyp = new Joukkue(2); jouk.lisaa(Jyp);
     *  Joukkue Assat = new Joukkue(1); jouk.lisaa(Assat);
     * 
     *  Iterator<Joukkue> i2= jouk.iterator();
     *  i2.next() === Jyp;
     *  i2.next() === Assat;
     *  
     *  int n = 0;
     *  int jnrot[] = {2,1};
     *  
     *  for ( Joukkue jou:jouk ) { 
     *    jou.getPelaajannumero() === jnrot[n]; n++;  
     *  }
     *  
     *  n === 2;
     *  
     * </pre>
     */
    @Override
    public Iterator<Joukkue> iterator() {
        return alkiot.iterator();
    }
    
    /**
     * @param args ei k‰ytˆss‰ 
     */
    public static void main(String[] args) {
    
    Joukkueet joukkueet = new Joukkueet();
    Joukkue Jyp1 = new Joukkue();
    Jyp1.vastaaJoukkue(1);
    Joukkue Jyp2 = new Joukkue();
    Jyp2.vastaaJoukkue(2);
    Joukkue Jyp3 = new Joukkue();
    Jyp3.vastaaJoukkue(3);
    
    joukkueet.lisaa(Jyp1);
    joukkueet.lisaa(Jyp2);
    joukkueet.lisaa(Jyp3);
    
    //System.out.println("=====================================");  //Tulostetaan viiva selkeytt‰m‰‰n tulostusta
    /**
    List<Joukkue> joukkue2 = joukkueet.annaJoukkueet(1);
        for (Joukkue jouk : joukkue2) {
            System.out.println(jouk.getPelaajannumero() + " "); // Tulostaa pelaajanid, jonka mukaan jatkossa voidaan m‰‰rittee joukkue
            jouk.tulosta(System.out);
            
        }
     */
    }

   

}
