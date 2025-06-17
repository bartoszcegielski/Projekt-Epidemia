package projekt_epidemia;
import java.util.Random;

/**
 * Klasa reprezentująca Lekarza w aplikacji Epidemia.
 * Lekarz jest podklasą klasy Ekspert i posiada unikalne zdolności do leczenia innych postaci.
 */
public class Lekarz extends Ekspert {
    private static final Random losowa = new Random();

    /**
     * Konstruktor klasy Lekarz.
     * Inicjalizuje wszystkie pola klasy Postac.
     * Ustawia domyślną wartość hp na 50.
     * @param id unikalny identyfikator lekarza.
     * @param zarazony informacja, czy lekarz jest zarażony.
     * @param zaszczepiony informacja, czy lekarz jest zaszczepiony.
     * @param x współrzędna X lekarza na planszy.
     * @param y współrzędna Y lekarza na planszy.
     */
    public Lekarz(int id, boolean zarazony, boolean zaszczepiony, int x, int y) {
        super(id, 50, zarazony, zaszczepiony, x, y);
    }

    /**
     * Metoda wypisująca informacje o lekarzu.
     */
    @Override
    public void wypiszInformacje() {
        Zapisywacz.zapis("Lekarz ID " + id + ", wiek " + wiek + ", HP " + hp + ", zarażony: " + zarazony + ", zaszczepiony: " + zaszczepiony + ", zyje: " + isZywy());
    }

    /**
        * Metoda, która sprawdza, czy lekarz spotkał zarażoną postać.
        * Jeśli postać jest zarażona i postęp szczepionki jest wystarczający,
        * lekarz ma szansę na wyleczenie tej postaci.
        * Szansa na wyleczenie to procent postępu szczepionki.
        * @param p postać, którą lekarz spotyka.
        */
    public void spotkajPostac(Postac p) {
        if (p.isZywy() && p.isZarazony()) {
            int postep = Postac.getSzczepionka();
            boolean szansaWyleczenia = false;
            if (postep >= 50) {
                int los = losowa.nextInt(100) + 1;
                if (los <= postep) {
                    szansaWyleczenia = true;
                } else {
                    szansaWyleczenia = false;
                }
            }
            if (szansaWyleczenia) {
                p.setZarazony(false);
                Zapisywacz.zapis("Lekarz ID " + id + " wyleczył osobę ID " + p.getId() + ".");
            }
            else {
                Zapisywacz.zapis("Lekarz ID " + id + " nie wyleczył osoby ID " + p.getId() + ".");
            }
        }
    }
}