package liigarekisteri;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import liiga.Joukkue;
import liiga.Joukkueet;
import liiga.Liiga;
import liiga.Pelaaja;


/**
 * @author Valtteri Peltoniemi & Konsta K�ht�v�
 * @version 8.3.2019
 *
 */
public class PelaajanMuokkausGUIController implements ModalControllerInterface<Liiga>, Initializable {

    
    @FXML private ScrollPane panelPelaaja;
    @FXML private TextField editNimi;
    @FXML private ComboBoxChooser<Joukkue> editJoukkue;
    @FXML private ComboBoxChooser<String> editPelipaikka;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }


    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    @FXML private void handlePeruuta() {
        ModalController.closeStage(editNimi);
    }
    
 //===========================================================================================   
    
    private Pelaaja pelaajaKohdalla;
    private TextField edits[];  
    private Liiga liiga;
    private String[] pelipaikkoja = { "hy�kk��j�", "puolustaja", "maalivahti"};
    /**
     * tyhjent�� tekstikent�t
     */
    public void tyhjenna() {
        for (TextField edit : edits)
            edit.setText("");
    }
    

    
    /**
     * N�ytt�� pelaajan muokkausikkunassa
     * @param pelaaja n�ytett�v� pelaaja
     * @param liiga liiga
     */
    public void naytaPelaaja(Pelaaja pelaaja, Liiga liiga) {
        if ( pelaaja == null) return;
        
        TextField[] edit = new TextField[] {editNimi};
        naytaPelaaja(edit, pelaaja, liiga);

    } 
    
    /**
     * N�ytt�� v�liaikaisesti valitun pelaajan tiedot  tekstikentt��n.
     * @param edit teksfield taulukko
     * @param pelaaja pelaaja
     * @param liiga liiga
     */
    public static void naytaPelaaja(TextField[] edit, Pelaaja pelaaja, Liiga liiga) {
        if ( pelaaja == null) return;
        edit[0].setText(pelaaja.getnimi());
        //if (liiga.annaJoukkue(pelaaja.getJoukkue())==null) return;
        //edit[1].setText(liiga.annaJoukkue(pelaaja.getJoukkue()).getJoukkue());

    } 
  
    
    /**
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle 
     * @param oletus mit� dataa n�ytet��n oletuksena
     * @return null jos painetaan cancel
     */
    public static Liiga kysyPelaaja(Stage modalityStage, Liiga oletus) {
        return ModalController.showModal(
                PelaajanMuokkausGUIController.class.getResource("PelaajanMuokkausGUIView.fxml"),"Liiga", modalityStage,oletus,null);
        
    }

    /**
     * tekee tarvittavat alustukset
     */
    protected void alusta() {
        edits = new TextField[]{editNimi};
        int i = 0;
        for (TextField edit : edits) {
            final int k = ++i;
            edit.setOnKeyReleased( e -> kasitteleMuutosPelaajaan(k, (TextField)(e.getSource())));
        }
       
        
       
    }

    /**
     * Tekee muutoksen pelaajan tietoihin
     * @param k indeksi, jonka mukaan muutetaan
     * @param edit tekstikentt� johon kirjoitetaan tieto
     */
    private void kasitteleMuutosPelaajaan(int k, TextField edit) {
        if(pelaajaKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        switch (k) {
            case 1 : virhe = pelaajaKohdalla.setNimi(s); break;
        default:
            
            
        }
        if(virhe == null) {
            Dialogs.setToolTipText(edit, "");
            edit.getStyleClass().removeAll("virhe");
            //Dialogs.showMessageDialog("ei onnistu");
        } else {
            Dialogs.setToolTipText(edit, virhe);
            edit.getStyleClass().add("virhe");
            Dialogs.showMessageDialog("ei onnistu ei");
        }
        
    }




    @Override
    public Liiga getResult() {
        return liiga;
    }

    /**
     * mit� tehd��n kun dialogi on n�ytetty
     */
    @Override
    public void handleShown() {
        editNimi.requestFocus();
        
    }

    /**
     * oletusasetukset pelaajalle
     */
    @Override
    public void setDefault(Liiga oletus) {
        liiga = oletus;
        pelaajaKohdalla = liiga.getPelaajaKohdalla();
        Joukkueet joukkueet = liiga.annaJoukkueet(); 
        editJoukkue.clear();
        editPelipaikka.clear();
        
        for (int j = 0; j < joukkueet.getLkm(); j++) {
            if (joukkueet.annaJoukkue(j)==null) continue;
            editJoukkue.add(joukkueet.annaJoukkue(j).getJoukkue(), joukkueet.annaJoukkue(j));
            
            if (pelaajaKohdalla==null) continue;
            if (liiga.annaJoukkue(pelaajaKohdalla.getJoukkue())==joukkueet.annaJoukkue(j))editJoukkue.setSelectedIndex(j);
        }
        
        
        for (int j = 0; j < 3; j++) {
            // if (pelaajaKohdalla.annaPelipaikka() == null) continue;
            editPelipaikka.add(pelipaikkoja[j]);
            if (pelaajaKohdalla == null ) continue;
            String paikka = pelaajaKohdalla.annaPelipaikka();
            if (paikka == null) continue;
            if (paikka.equals(pelipaikkoja[j]))editPelipaikka.setSelectedIndex(j);
        }
       

        naytaPelaaja(edits, pelaajaKohdalla, liiga);
        if (pelaajaKohdalla == null) editNimi.setText("");
    }

    /**
     * tallentaa muutokset muokkausikkunassa
     */
    public void tallenna() {
        
        if(pelaajaKohdalla == null) {
            pelaajaKohdalla = new Pelaaja();
            liiga.setPelaajaKohdalla(pelaajaKohdalla); 
            pelaajaKohdalla.rekisteroi();
            liiga.lisaa(pelaajaKohdalla);
            pelaajaKohdalla.setNimi(editNimi.getText());
          }
            
            if ( pelaajaKohdalla != null && pelaajaKohdalla.getnimi().trim().equals("") ) {
                Dialogs.showMessageDialog("Nimi ei voi olla tyhj�!");
                return;
            }
            
            Joukkue joukkue = editJoukkue.getSelectedObject();
            if (joukkue == null) {
            Dialogs.showMessageDialog("Joukkue ei voi olla tyhj�!");
            return;
            }
          
            String pelipaikka = editPelipaikka.getSelectedObject();
            if (pelipaikka == null) {
                Dialogs.showMessageDialog("pelipaikka ei voi olla tyhj�!");
                return;   
            }
            
            pelaajaKohdalla.setJoukkueId(joukkue.getJoukkuenumero());
            pelaajaKohdalla.setPelipaikka(pelipaikka);
            
            ModalController.closeStage(editNimi); 
    }


}