package liigarekisteri;

import java.io.PrintStream;
import java.net.URL;

import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.ohj2.WildChars;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import liiga.Joukkue;
import liiga.Joukkueet;
import liiga.Liiga;
import liiga.Pelaaja;
import liiga.SailoException;




/**
 *  Luokka, jolla hoidetaan liiga k�ytt�liittym�n tapahtumia.
 * 
 * @author Valtteri Peltoniemi & Konsta K�ht�v�
 * @version 8.3.2019
 *
 */
public class FxliigaGUIController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
       alusta();       
    }
    

    @FXML private Button uusiPelaaja;
    @FXML private Button poistaPelaaja;
    @FXML private Button haeSeura;
    @FXML private ScrollPane panelPelaaja;
    @FXML private ListChooser<Pelaaja> chooserPelaajat;
    @FXML private TextField editJoukkueet;
    @FXML private TextField editKaupunki;
    @FXML private TextField editVuosi;
    @FXML private TextField editMestaruudet;
    @FXML private TextField editHopeat;
    @FXML private TextField editPronssit;
    @FXML private TextField editValmentaja;
    @FXML private TextField hakuehto;
    @FXML ComboBoxChooser<String> cbKentat;
    @FXML private TextField seuraNimi;
    
    

    @FXML void handleUusiPelaaja() {
       uusiPelaaja(); 
       //uusiJoukkue();
       //uusiPelipaikka();
      
    }


    @FXML private void handleEtsiJoukkue() {
        haeJoukkue(0);
   
    }


    @SuppressWarnings("unused")
    @FXML void handleTallennus( ActionEvent event) {
        tallenna();
    }
    
   
    
    @SuppressWarnings("unused")
    @FXML private void avaaMuokkausIkkuna(MouseEvent event) {
        muokkaa();
    }
    
    
    @FXML private void handlePoistaPelaaja() {
        poistaPelaaja();
        
    }
    
    @FXML private void handleTallenna() {
        tallenna();
    }

    @FXML private void handleTietoja() {
        Dialogs.showMessageDialog("Tekij�t: \n Valtteri & Konsta ");
    }
    
    @FXML private void handleHakuehto() {
       hae(0);
    }


//==================== Ei k�ytt�liittym��n liittyv�� suoraa koodia =============================\\
    

    /**
     * avaa virheikkunan
     */
    public void avaaVirheikkuna() {
        ModalController.showModal(FxliigaGUIController.class.getResource("VirheIkkunaGUIView.fxml"), "Joukkue haku", null, "");
        return;
    }
    
    private Liiga liiga;

    private Pelaaja pelaajakohdalla;
    private Joukkue joukkueKohdalla;
    private String joku;
    private static Pelaaja apupelaaja = new Pelaaja();
    private boolean seuraHaettu = false;
    

    
    
    /**
     * Tekee tarvittavat alustukset ja vaihtaa Scrollpanen p��lle ison teksti alueen, jonka
     * p��lle tulostetaan tietoja.
     */
    private void alusta() {

        chooserPelaajat.clear();
        chooserPelaajat.addSelectionListener(e-> naytaPelaaja());
        chooserPelaajat.setOnMouseClicked(e -> { if ( e.getClickCount() > 1 ) muokkaa(); });
        //chooserPelaajat.addSelectionListener(e-> naytaJoukkue());
       // edits = new TextField[]{editJoukkue}; 
        
    }
    
    /**
     * Kysyt��n tiedoston nimi ja luetaan se
     * @return true jos onnistui, false jos ei
     */
    public boolean avaa() {
        String uusinimi = joku;
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);
        return true;
    }
    
    /**
     * N�ytt�� valitun pelaajan k�ytt�liittym�ss�
     */
    private void naytaPelaaja() {
        pelaajakohdalla = chooserPelaajat.getSelectedObject(); 
        if (pelaajakohdalla == null) return;
        naytaJoukkue(); 
         
      }  

    /**
     * N�ytt�� valitun Joukkueen tiedot k�ytt�liittym�ss�
     */
    private void naytaJoukkue() {
        
        joukkueKohdalla = liiga.annaJoukkue(pelaajakohdalla);

        if (joukkueKohdalla == null) return; 
        
        editJoukkueet.setText(joukkueKohdalla.getJoukkue());
        editKaupunki.setText(joukkueKohdalla.getKaupunki());
        editVuosi.setText(joukkueKohdalla.getVuosi());
        editMestaruudet.setText(joukkueKohdalla.getMestaruudet()); 
        editHopeat.setText(joukkueKohdalla.getHopeat());
        editPronssit.setText(joukkueKohdalla.getPronssit());
        editValmentaja.setText(joukkueKohdalla.getValmentaja());

        
    }
    
    
    /**
     * Alustaa liigan lukemalla sen valitun nimisest� tiedostosta
     * @param nimi tiedosto josta kerhon tiedot luetaan
     */
    protected void lueTiedosto(String nimi) {
        
        try {
            liiga.lueTiedostosta(nimi);
            hae(0);
           
        } catch (SailoException e) {
            hae(0);
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
           
        }
     }

    
    /**
     * Luo uuden pelaajan, jolle t�ytet��n halutut tiedot.
     */
   private void uusiPelaaja() {
      /*
       Pelaaja tyyppi = new Pelaaja();
       Liiga apuLiiga;
       liiga.setPelaajaKohdalla(tyyppi); 
       
       */
       liiga.setPelaajaKohdalla(null); 
       Liiga apuLiiga;
       
       
       apuLiiga = PelaajanMuokkausGUIController.kysyPelaaja(null, liiga);
       if (apuLiiga==null) return;
       liiga = apuLiiga;
      
       /*
       tyyppi = liiga.getPelaajaKohdalla();
       if (tyyppi == null || tyyppi.getnimi() == "" ) return;
       rek
       //tyyppi.vastaaErikPerrin();   //todo korvaa ett� aukeaa uusi dialogi
       liiga.lisaa(tyyppi);
       */
       Pelaaja tyyppi = liiga.getPelaajaKohdalla();
       if (tyyppi != null) pelaajakohdalla = tyyppi;
       hae(pelaajakohdalla.getPelaajaid());
       
   }
   
   
   /**
    * Tekee uuden tyhj�n joukkueen muokkaamista varten
    
   private void uusiJoukkue() {       
       if (pelaajakohdalla == null) return; 
       Joukkue tiimi = new Joukkue();
       tiimi.rekisteroi();
       tiimi.vastaaJoukkue(pelaajakohdalla.getPelaajaid()); 
       liiga.lisaa(tiimi);
       hae(tiimi.getPelaajannumero());
       
   }
   
   */
   
   /**
    * Muokataan olemassa olevan pelaajan tietoja
    */
   private void muokkaa() {
       //ModalController.showModal(FxliigaGUIController.class.getResource("PelaajanMuokkausGUIView.fxml"), "Joukkue haku", null, "");
       if(pelaajakohdalla == null) return;
       try {
           Pelaaja pelaaja;
           Liiga apuLiiga;
           liiga.setPelaajaKohdalla(pelaajakohdalla);
           apuLiiga = PelaajanMuokkausGUIController.kysyPelaaja(null, liiga.clone());
           if(apuLiiga==null) return;
           liiga = apuLiiga;
           pelaaja = liiga.getPelaajaKohdalla();
           if(pelaaja == null) return;
           
           liiga.korvaaTaiLisaa(pelaaja);
           if(!seuraHaettu)hae(pelaaja.getPelaajaid());
       } catch (CloneNotSupportedException e) {
           //
       } catch (SailoException e) {
           Dialogs.showMessageDialog(e.getMessage());
       }
   }
   
   
   
   /**
    * Tietojen tallennus
    */
   private void tallenna() {
       try {
           liiga.tallenna();
           return;
       } catch (SailoException ex) {
           Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
           
       }
   }
   
   /**
    * Tekee uuden tyhj�n pelipaikan muokkaamista varten
   
   private void uusiPelipaikka() {
       
       if (pelaajakohdalla == null) return; 
       Pelipaikka paikka = new Pelipaikka();
       paikka.rekisteroi();
       paikka.vastaaPelipaikka(pelaajakohdalla.getPelaajaid()); 
       liiga.lisaa(paikka);
       hae(paikka.getPelipaikkanumero());
       
   }


   /**
    * Hakee pelaajien tiedot 
    * @param pnr pelaajan numero
    */
   private void hae(int pnr) {
      
      
       int pnro = pnr;
       chooserPelaajat.clear();
       if(pnro <= 0) {
           Pelaaja kohdalla = pelaajakohdalla;
           if ( kohdalla != null) pnro = kohdalla.getPelaajaid();   
       }
       String haku = hakuehto.getText();
       int k = cbKentat.getSelectedIndex(); //+ apupelaaja.ekaKentta();
       
       int indeksi = 0;
       
       String ehto = "*";  
       if ( haku != null && haku.length() > 0 ) ehto = haku;  
       int hk = k;  
       if ( hk < 0 ) hk = 1; 
       if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*";  
       
       for (int i = 0; i < liiga.getPelaajia(); i++) {
           Pelaaja pelaaja = liiga.annaPelaaja(i);
           if (pelaaja.getPelaajaid() == pnro) indeksi = i;
           
           if (WildChars.onkoSamat(pelaaja.anna(hk), ehto)) chooserPelaajat.add(pelaaja.getnimi(), pelaaja);
           
         
           
       }
       
       chooserPelaajat.getSelectionModel().select(indeksi); // muutosviesti, joka n�ytt�� j�senen
       
   }
   
   /**
    * Hakee halutun joukkueen tiedot k�ytt�liittym��n 
    * @param pnr indeksi, jonka mukaan haetaan
    */
   private void haeJoukkue(int pnr) {
           int pnro = pnr;
           chooserPelaajat.clear();
           
           String haku = hakuehto.getText();
           int k = cbKentat.getSelectedIndex(); //+ apupelaaja.ekaKentta();
           
           int indeksi = 0;
           
           String ehto = "*";  
           if ( haku != null && haku.length() > 0 ) ehto = haku;  
           int hk = k;  
           if ( hk < 0 ) hk = 1; 
           if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*";  
           Joukkue joukkue = liiga.annaJoukkue(seuraNimi.getText());
           
           if(joukkue == null) {
               avaaVirheikkuna();
               seuraHaettu = false;
               hae(0);
               return;
           }
           
           for (int i = 0; i < liiga.getPelaajia(); i++) {
               Pelaaja pelaaja = liiga.annaPelaaja(i);
               if (pelaaja.getPelaajaid() == pnro) indeksi = i;
               
               if (WildChars.onkoSamat(pelaaja.anna(hk), ehto)&&joukkue.equals(liiga.annaJoukkue(pelaaja)))  chooserPelaajat.add(pelaaja.getnimi(), pelaaja);
               
           }
           

           chooserPelaajat.getSelectionModel().select(indeksi); // muutosviesti, joka n�ytt�� j�senen
           seuraHaettu = true;
       }
   
   

   
   /**
   * Tulostaa pelaajan tiedot 
   * @param os tietovirta, johon tulostetaan
   * @param pelaaja tulostettava pelaaja
   */
public void tulosta(PrintStream os, Pelaaja pelaaja) {
    
    pelaaja.tulosta(os); 
    
    /*
     * List<Joukkue> joukkueet = 
    for(Joukkue jouk : joukkueet)
    */
    
    Joukkue pelaajanJoukkue = liiga.annaJoukkue(pelaaja.getJoukkue());
    pelaajanJoukkue.tulosta(os);
    
    //List<Pelipaikka> pelipaikat = liiga.annaPelipaikat(pelaaja);
    //for(Pelipaikka paikka : pelipaikat)
    //paikka.tulosta(os);
  
}
   
    
    /**
     * Asetetaan viite liigaan
     * @param liiga k�ytett�v� liiga
     **/
    public void setLiiga(Liiga liiga) {
        this.liiga = liiga;
    }
    
   /**
    * Poistetaan listalta valittu j�sen 
    */ 
    private void poistaPelaaja() { 
        Pelaaja pelaaja = pelaajakohdalla; 
        if ( pelaaja == null ) return; 
        if ( !Dialogs.showQuestionDialog("Poisto", "Haluatko poistaa pelaajan: " + pelaaja.getnimi(), "Kyll�", "Ei") ) 
            return; 
        liiga.poista(pelaaja); 
        int index = chooserPelaajat.getSelectedIndex(); 
        hae(0); 
        chooserPelaajat.setSelectedIndex(index); 
    } 
    
    
}