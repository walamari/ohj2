
package liiga;

/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille
 * 
 * @author Valtteri Peltoniemi & Konsta K‰ht‰v‰
 * @version 7.3.2019
 *
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    
    /**
     * poikkeuksen muodostaja
     * @param viesti joka tuodaan muodostajalle
     */
    public SailoException(String viesti) {      
        super(viesti);          // superia k‰ytet‰‰n kun viitataan yliluokan metodeihin, joille on aliluokassa oma m‰‰rittely
    }

}
