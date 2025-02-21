package liiga;
// Generated by ComTest BEGIN
import java.io.File;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2019.05.08 11:20:38 // Generated by ComTest
 *
 */
public class JoukkueetTest {



  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta43 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta43() throws SailoException {    // Joukkueet: 43
    Joukkueet jouk = new Joukkueet(); 
    Joukkue Jyp = new Joukkue(); Jyp.vastaaJoukkue(1); 
    Joukkue Assat = new Joukkue(); Assat.vastaaJoukkue(2); 
    String tiedNimi = "TestiJoukkue"; 
    File ftied = new File(tiedNimi+".dat"); 
    ftied.delete(); 
    try {
    jouk.lueTiedostosta(tiedNimi); 
    fail("Joukkueet: 52 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    jouk.lisaa(Jyp); 
    jouk.lisaa(Assat); 
    jouk.tallenna(); 
    jouk = new Joukkueet(); 
    jouk.lueTiedostosta(tiedNimi); 
    jouk.lisaa(Jyp); 
    jouk.tallenna(); 
    assertEquals("From: Joukkueet line: 60", true, ftied.delete()); 
    File fbak = new File(tiedNimi+".bak"); 
    assertEquals("From: Joukkueet line: 62", true, fbak.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaJoukkue178 */
  @Test
  public void testAnnaJoukkue178() {    // Joukkueet: 178
    Joukkueet joukkueet = new Joukkueet(); 
    Joukkue Jyp = new Joukkue(1); joukkueet.lisaa(Jyp);  //lis�� joukkueeseen joukkueen ja antaa sille id:N ja etsii kyseisen id:n
    Joukkue Assat = new Joukkue(2); joukkueet.lisaa(Assat); 
    Joukkue Karpat = new Joukkue(3); joukkueet.lisaa(Karpat); 
    Joukkue haetut; 
    haetut = joukkueet.annaJoukkue(1); 
    assertEquals("From: Joukkueet line: 188", false, haetut.getJoukkue().equals(Jyp)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testIterator207 */
  @Test
  public void testIterator207() {    // Joukkueet: 207
    Joukkueet jouk = new Joukkueet(); 
    Joukkue Jyp = new Joukkue(2); jouk.lisaa(Jyp); 
    Joukkue Assat = new Joukkue(1); jouk.lisaa(Assat); 
    Iterator<Joukkue> i2= jouk.iterator(); 
    assertEquals("From: Joukkueet line: 216", Jyp, i2.next()); 
    assertEquals("From: Joukkueet line: 217", Assat, i2.next()); 
    int n = 0; 
    int jnrot[] = { 2,1} ; 
    for ( Joukkue jou:jouk ) {
    assertEquals("From: Joukkueet line: 223", jnrot[n], jou.getPelaajannumero()); n++; 
    }
    assertEquals("From: Joukkueet line: 226", 2, n); 
  } // Generated by ComTest END
}