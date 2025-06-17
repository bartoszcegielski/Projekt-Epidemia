package projekt_epidemia;
/**
 * Klasa reprezentująca Kobietę w aplikacji Epidemia.
 * Kobieta jest podklasą klasy Cywil i dziedziczy jej właściwości.
 */
public class Kobieta extends Cywil {
    /**
     * Konstruktor klasy Kobieta.
     * Inicjalizuje wszystkie pola klasy Postac.
     * Ustawia domyślną wartość hp na 40.
     * @param id unikalny identyfikator kobiety.
     * @param zarazony informacja, czy kobieta jest zarażona.
     * @param zaszczepiony informacja, czy kobieta jest zaszczepiona.
     * @param x współrzędna X kobiety na planszy.
     * @param y współrzędna Y kobiety na planszy.
     */
    public Kobieta(int id, boolean zarazony, boolean zaszczepiony, int x, int y) {
        super(id, 40, zarazony, zaszczepiony, x, y);
    }

    /**
     * Metoda wypisująca informacje o kobiecie.
     */
    @Override
    public void wypiszInformacje() {
        Zapisywacz.zapis("Kobieta ID " + id + ", wiek " + wiek + ", HP " + hp + ", zarażona: " + zarazony + ", zaszczepiony: " + zaszczepiony + ", zyje: " + isZywy());
    }
}
