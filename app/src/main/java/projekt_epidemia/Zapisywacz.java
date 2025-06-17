package projekt_epidemia;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
/**
 * Klasa Zapisywacz służy do zapisywania logów do pliku.
 * Umożliwia zapis pojedynczych wpisów oraz czyszczenie pliku logów.
 */
public class Zapisywacz {
    private static final String FILE = "log.txt";

    /**
     * Metoda zapisuje pojedynczy wpis do pliku logów.
     * @param log tekst, który ma zostać zapisany w logu.
     */
    public static void zapis(String log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE, true))) {
            writer.println(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda czyści plik logów, pozostawiając jedynie nagłówek "LOGI:".
     */
    public static void wyczysc() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE, false))) {
            writer.println("LOGI:");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}