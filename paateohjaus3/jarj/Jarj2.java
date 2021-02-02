package jarj;
import java.util.Scanner; // tarvitaan tietojen syöttöön
public class Jarj2 {

    private static final Scanner inputScanner = new Scanner(System.in);

    /**
     * Kysyy käyttäjältä merkkijonon annetulla tulosteella.
     * @param prompt lause, joka käyttäjä näkee ennen tietojen syöttöä
     * @return käyttäjän syöttämä merkkijono ilman rivinvaihtoa
     */
    public static String kysy(String prompt) {
        System.out.print(prompt);
        return inputScanner.nextLine();
    }

    /**
     * Pääohjelma
     *
     * @param args Komentorivillä annetut parametetrit
     */
    public static void main(String[] args) {
        String s1, s2, t;

        s1 = kysy("Anna 1. merkkijono >");
        s2 = kysy("Anna 2. merkkijono >");

        if ( s1.compareTo(s2) > 0 ) { // Vaihdetaan merkkijonot tarvittaessa
            t = s1;
            s1 = s2;
            s2 = t;
        }

        System.out.println("Jonot " + s1 + ", " + s2 + ".");
    }

}