package liiga;
// Generated by ComTest BEGIN
import java.io.File;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2019.05.08 12:55:22 // Generated by ComTest
 *
 */
public class PelaajatTest {



  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta35 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta35() throws SailoException {    // Pelaajat: 35
    Pelaajat pelj = new Pelaajat(); 
    Pelaaja Eki = new Pelaaja(); Eki.vastaaErikPerrin(); 
    Pelaaja Aki = new Pelaaja(); Aki.vastaaErikPerrin(); 
    String hakemisto = "TestiJoukkue"; 
    String tiedNimi = hakemisto+"/nimet"; 
    File ftied = new File(tiedNimi+".dat"); 
    File dir = new File(hakemisto); 
    dir.mkdir(); 
    ftied.delete(); 
    try {
    pelj.lueTiedostosta(tiedNimi); 
    fail("Pelaajat: 47 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    pelj.lisaa(Eki); 
    pelj.lisaa(Aki); 
    pelj.tallenna(); 
    pelj = new Pelaajat(); 
    pelj.lueTiedostosta(tiedNimi); 
    pelj.lisaa(Eki); 
    pelj.tallenna(); 
    assertEquals("From: Pelaajat line: 55", true, ftied.delete()); 
    File fbak = new File(tiedNimi+".bak"); 
    assertEquals("From: Pelaajat line: 57", true, fbak.delete()); 
    assertEquals("From: Pelaajat line: 58", true, dir.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLisaa157 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa157() throws SailoException {    // Pelaajat: 157
    Pelaajat pelaajat = new Pelaajat(); 
    Pelaaja pelaaja1 = new Pelaaja(); 
    Pelaaja pelaaja2 = new Pelaaja(); 
    assertEquals("From: Pelaajat line: 162", 0, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 163", 1, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja2); assertEquals("From: Pelaajat line: 164", 2, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 165", 3, pelaajat.getLkm()); 
    assertEquals("From: Pelaajat line: 166", pelaaja1, pelaajat.anna(0)); 
    assertEquals("From: Pelaajat line: 167", pelaaja2, pelaajat.anna(1)); 
    assertEquals("From: Pelaajat line: 168", pelaaja1, pelaajat.anna(2)); 
    assertEquals("From: Pelaajat line: 169", false, pelaajat.anna(1) == pelaaja1); 
    assertEquals("From: Pelaajat line: 170", true, pelaajat.anna(1) == pelaaja2); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 171", 4, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 172", 5, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 173", 6, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 174", 7, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 175", 8, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 176", 9, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 177", 10, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 178", 11, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 179", 12, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 180", 13, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 181", 14, pelaajat.getLkm()); 
    pelaajat.lisaa(pelaaja1); assertEquals("From: Pelaajat line: 182", 15, pelaajat.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testKorvaaTaiLisaa222 
   * @throws SailoException when error
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testKorvaaTaiLisaa222() throws SailoException,CloneNotSupportedException {    // Pelaajat: 222
    Pelaajat pelaajat = new Pelaajat(); 
    Pelaaja eki = new Pelaaja(), tepa = new Pelaaja(); 
    eki.rekisteroi(); tepa.rekisteroi(); 
    assertEquals("From: Pelaajat line: 228", 0, pelaajat.getLkm()); 
    pelaajat.korvaaTaiLisaa(eki); assertEquals("From: Pelaajat line: 229", 1, pelaajat.getLkm()); 
    pelaajat.korvaaTaiLisaa(tepa); assertEquals("From: Pelaajat line: 230", 2, pelaajat.getLkm()); 
    Pelaaja jake = eki.clone(); 
    pelaajat.korvaaTaiLisaa(jake); assertEquals("From: Pelaajat line: 232", 2, pelaajat.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoista252 
   * @throws SailoException when error
   */
  @Test
  public void testPoista252() throws SailoException {    // Pelaajat: 252
    Pelaajat pelaajat = new Pelaajat(); 
    Pelaaja joku1 = new Pelaaja(), joku2 = new Pelaaja(), joku3 = new Pelaaja(); 
    joku1.rekisteroi(); joku2.rekisteroi(); joku3.rekisteroi(); 
    int id1 = joku1.getPelaajaid(); 
    pelaajat.lisaa(joku1); pelaajat.lisaa(joku2); pelaajat.lisaa(joku3); 
    assertEquals("From: Pelaajat line: 259", 1, pelaajat.poista(id1+1)); 
    assertEquals("From: Pelaajat line: 260", null, pelaajat.annaId(id1+1)); assertEquals("From: Pelaajat line: 260", 2, pelaajat.getLkm()); 
    assertEquals("From: Pelaajat line: 261", 1, pelaajat.poista(id1)); assertEquals("From: Pelaajat line: 261", 1, pelaajat.getLkm()); 
    assertEquals("From: Pelaajat line: 262", 0, pelaajat.poista(id1+3)); assertEquals("From: Pelaajat line: 262", 1, pelaajat.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testEtsiId295 
   * @throws SailoException when error
   */
  @Test
  public void testEtsiId295() throws SailoException {    // Pelaajat: 295
    Pelaajat pelaajat = new Pelaajat(); 
    Pelaaja joku1 = new Pelaaja(), joku2 = new Pelaaja(), joku3 = new Pelaaja(); 
    joku1.rekisteroi(); joku2.rekisteroi(); joku3.rekisteroi(); 
    int id1 = joku1.getPelaajaid(); 
    pelaajat.lisaa(joku1); pelaajat.lisaa(joku2); pelaajat.lisaa(joku3); 
    assertEquals("From: Pelaajat line: 302", 1, pelaajat.etsiId(id1+1)); 
    assertEquals("From: Pelaajat line: 303", 2, pelaajat.etsiId(id1+2)); 
  } // Generated by ComTest END
}