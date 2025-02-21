package liiga;

import java.io.File;



/**
 * Liiga luokka, mik� huolehtii kaikista pelaajista
 * @author Valtteri Peltoniemi & Konsta K�ht�v�
 * @version 7.3.2019
 *
 */
public class Liiga implements Cloneable {
    
    
    private Pelaajat pelaajat = new Pelaajat();
    private Joukkueet joukkueet = new Joukkueet();
    private Pelaaja pelaajaKohdalla;
    private String[] pelipaikkoja = { "hy�kk��j�", "puolustaja", "maalivahti"};
    //private Pelipaikat pelipaikat = new Pelipaikat();
    
    @Override
    public  Liiga clone() throws CloneNotSupportedException {
        Liiga uusi;
        uusi = (Liiga) super.clone();
        return uusi;
    } 
    
    /**
     * Lukee tiedostosta
     * @param nimi tiedoston nimi
     * @throws SailoException poikkeus 
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        
        pelaajat = new Pelaajat();
        joukkueet = new Joukkueet();
        
        setTiedosto(nimi);
        pelaajat.lueTiedostosta();
        joukkueet.lueTiedostosta();
    }
    
    /**
     * Asettaa tiedostojen perusnimet
     * @param nimi uusi nimi
     */
    public void setTiedosto(String nimi) {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "";
        if ( !nimi.isEmpty() ) hakemistonNimi = nimi +"/";
        pelaajat.setTiedostonPerusNimi(hakemistonNimi + "pelaajat");
        joukkueet.setTiedostonPerusNimi(hakemistonNimi + "joukkueet");
    }
    
 
    
    
    /**
     * Palauttaa liigan pelaajien m��r�n
     * @return pelajien m��r�n
     */
    public int getPelaajia() {
        return pelaajat.getLkm(); 
    }
    
    /**
     * Palauttaa joukkueen m��r�n
     * @return joukkueiden m��r�
     */
    public int getJoukkueita() {
        return joukkueet.getLkm();
    }
    
    /**
     * Palautta pelaajan, joka on indeksiss� i
     * @param i pelaajan indeksi, joka palautetaan
     * @return indeksiss� i olevan pelaajan viite
     * @throws IndexOutOfBoundsException jos annettu indeksi i on v��r�
     */
    public Pelaaja annaPelaaja(int i) throws IndexOutOfBoundsException {
        return pelaajat.anna(i);
    }
    
    /**
     * Antaa joukkueen nimen joukkueiden joukosta
     * @param joukkue tietty joukkue
     * @return joukkueen tai null
     */
    public Joukkue annaJoukkue(String joukkue) {
        for (int i = 0; i < joukkueet.getLkm(); i++) {
            if(joukkueet.annaJoukkue(i).getJoukkue().equals(joukkue)) return joukkueet.annaJoukkue(i);
            
            
        }
        return null;
        
    }
    
    /**
     * Lis�� uuden pelajaan tietorakenteeseen. Ottaa pelaajan omistukseensa.
     * @param pelaaja lis�tt�v� pelaaja
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Liiga liiga = new Liiga();
     * Pelaaja pelaaja1 = new Pelaaja();
     * Pelaaja pelaaja2 = new Pelaaja();
     * pelaaja1.rekisteroi();
     * pelaaja2.rekisteroi();
     * liiga.getPelaajia() === 0;
     * liiga.lisaa(pelaaja1); liiga.getPelaajia() === 1;
     * liiga.lisaa(pelaaja2); liiga.getPelaajia() === 2;
     * liiga.lisaa(pelaaja1); liiga.getPelaajia() === 3;
     * liiga.getPelaajia() === 3;
     * liiga.annaPelaaja(0) === pelaaja1;
     * liiga.annaPelaaja(1) === pelaaja2;
     * liiga.annaPelaaja(2) === pelaaja1;
     */
    public void lisaa(Pelaaja pelaaja){
        pelaajat.lisaa(pelaaja);
    }
    
    /**
     * Lis�� uuden joukkueen liigaan
     * @param jouk lis�tt�v� joukkue
     */
    public void lisaa(Joukkue jouk) {
        joukkueet.lisaa(jouk);
    }
    
    
    /**
     * Lis�� uuden pelipaikan liigaan
     * @param paikka lis�tt�v� pelipaikka
     
    public void lisaa(Pelipaikka paikka) {
        pelipaikat.lisaa(paikka);
    }
    */
    
    /**
     * Haetaan pelaajan joukkue
     * @param joukkueid  pelaajalla oleva joukkue id
     * @return tietorakenne, jossa viite l�ydettyyn joukkueeseen
     */
    public Joukkue annaJoukkue(int joukkueid) {
        return joukkueet.annaJoukkue(joukkueid);
    }
    

    /**
     * Palauttaa pelaajan joukkueen
     * @param pelaaja tietty pelaaja
     * @return pelaajan joukkueid:n
     */
    public Joukkue annaJoukkue(Pelaaja pelaaja) {
        return annaJoukkue(pelaaja.getJoukkue());
    }
    
    /*
    public Joukkue getJoukkue(Pelaaja pelaaja) {
        return 
    }
    */
    
    /**
     * Haetaan pelaajan pelipaikka
     * @param pelaaja pelaaja, jonka pelipaikka haetaan
     * @return tietorakenne, jossa viite l�ydettyyn pelipaikkaan
    
    public List<Pelipaikka> annaPelipaikat(Pelaaja pelaaja) {
        return pelipaikat.annaPelipaikat(pelaaja.getPelaajaid());
    }
     */
    
    /**
     * Tallenttaa liigan tiedot tiedostoon. 
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            pelaajat.tallenna();
        } catch ( SailoException ex ) {
            virhe = ex.getMessage();
        }
        try {
            joukkueet.tallenna();
        } catch ( SailoException ex ) {
            virhe = ex.getMessage();
        }
        if ( "".equals(virhe) ) return;
        throw new SailoException(virhe);
    }

    /**
     * Korvaa tai lis�� pelaajan
     * @param pelaaja pelaaja
     * @throws SailoException poikkeus
     * 
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * Liiga liiga = new Liiga ();
     * Pelaaja eki = new Pelaaja(); eki.vastaaErikPerrin(); eki.rekisteroi();
     * Pelaaja aki = new Pelaaja(); aki.vastaaErikPerrin(); aki.rekisteroi();
     * liiga.lisaa(eki);
     * liiga.getPelaajia() === 1;
     * liiga.korvaaTaiLisaa(aki);
     * liiga.getPelaajia() === 2;
     * </pre>
     */
    public void korvaaTaiLisaa(Pelaaja pelaaja) throws SailoException {
        pelaajat.korvaaTaiLisaa(pelaaja);
        
    }


    
    
  /**
   * Poistaa pelaajan liigarekisterist�
   * @param pelaaja pelaaja
   * @return poistettu 
   * 
   * @example
   * <pre name="test">
   * #THROWS Exception
   * 
   * Liiga liiga = new Liiga();
   * Pelaaja eki = new Pelaaja(); eki.vastaaErikPerrin(); eki.rekisteroi();
   * Pelaaja aki = new Pelaaja(); aki.vastaaErikPerrin(); aki.rekisteroi();
   * liiga.lisaa(eki);
   * liiga.lisaa(aki);
   * liiga.getPelaajia() === 2;
   * liiga.poista(eki);
   * liiga.getPelaajia() === 1;
   * liiga.poista(aki);
   * liiga.getPelaajia() === 0;
   * </pre>
    */
    public int poista(Pelaaja pelaaja) { 
        if ( pelaaja == null ) return 0; 
        int k = pelaaja.getPelaajaid();
        int ret = pelaajat.poista(k);
        return ret;  
    } 
  
    /**
     * Palauttaa pelaajakohdalla
     * @return pelaajakohdalla
     */
    public Pelaaja getPelaajaKohdalla() {
        return pelaajaKohdalla;
    }

    /**
     * Asettaa pelaajakohdalle oikeaan kohtaan
     * @param pelaaja pelaaja
     */
    public void setPelaajaKohdalla(Pelaaja pelaaja) {
        pelaajaKohdalla = pelaaja;
    }
    
   /**
    * Antaa pelipaikan taukukosta
    * @param i indeksi
    * @return pelipaikka
    */
    public String annaPelipaikka(int i) {
       return pelipaikkoja[i];
   }
    
    /**
     * Palauttaa joukkueen
     * @return joukkueet
     */
    public Joukkueet annaJoukkueet() {
        return joukkueet;
        
    }
   
    /**
     * @param args ei k�yt�ss�
     */
    public static void main(String[] args) {
    
        Liiga liiga = new Liiga();
        
        Pelaaja erik = new Pelaaja();
        Pelaaja erik2 = new Pelaaja();
        
        erik.rekisteroi(); 
        erik.vastaaErikPerrin();
        erik2.rekisteroi(); 
        erik2.vastaaErikPerrin();
        
    
            liiga.lisaa(erik);
            liiga.lisaa(erik2);
            int id1 = erik.getPelaajaid();
            int id2 = erik2.getPelaajaid();
           // int pid1 = erik.getPelaajaid();
            //int pid2 = erik2.getPelaajaid();
            Joukkue jyp1 = new Joukkue(id1); jyp1.vastaaJoukkue(id1); liiga.lisaa(jyp1);
            Joukkue jyp2 = new Joukkue(id2); jyp2.vastaaJoukkue(id2); liiga.lisaa(jyp2);
            
            //Pelipaikka hyokkaaja = new Pelipaikka(); //hyokkaaja.vastaaPelipaikka(pid1);
            //liiga.lisaa(hyokkaaja);
           // Pelipaikka puolustaja = new Pelipaikka(); //puolustaja.vastaaPelipaikka(pid2);
            //liiga.lisaa(puolustaja);
        

        
        System.out.println("=================== Pelaajat testi ====================");
        
        
    /**   
        for (int i = 0; i < liiga.getPelaajia();i++) {
            Pelaaja pelaaja = liiga.annaPelaaja(i);
            System.out.println("Pelaaja paikassa: " + i);
            pelaaja.tulosta(System.out);
            List<Joukkue> loydetyt = liiga.annaJoukkue(pelaaja);
                for (Joukkue jouk : loydetyt) 
                    jouk.tulosta(System.out);
       
        }

   */
        
   
    }



}
