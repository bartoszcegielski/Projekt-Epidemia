package projekt_epidemia;
/**
 * Główna klasa aplikacji Epidemia.
 * Ta klasa zawiera metodę main, która uruchamia menu symulatora.
 */
public class App {
    /** 
     * Główna metoda programu, która uruchamia menu symulatora.
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        Menu symulator = new Menu();
        symulator.uruchom();
    }
}