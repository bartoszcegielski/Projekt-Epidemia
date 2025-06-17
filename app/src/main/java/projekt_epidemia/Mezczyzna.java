package projekt_epidemia;
/**
 * Klasa reprezentująca Mężczyznę w aplikacji Epidemia.
 * Mężczyzna jest podklasą klasy Cywil i dziedziczy jej właściwości.
 */
public class Mezczyzna extends Cywil {
    /**
     * Konstruktor klasy Mezczyzna.
     * Inicjalizuje wszystkie pola klasy Postac.
     * Ustawia domyślną wartość hp na 50.
     * @param id unikalny identyfikator mężczyzny.
     * @param zarazony informacja, czy mężczyzna jest zarażony.
     * @param zaszczepiony informacja, czy mężczyzna jest zaszczepiony.
     * @param x współrzędna X mężczyzny na planszy.
     * @param y współrzędna Y mężczyzny na planszy.
     */
    public Mezczyzna(int id, boolean zarazony, boolean zaszczepiony, int x, int y) {
        super(id, 50, zarazony, zaszczepiony, x, y);
    }

    /**
     * Metoda wypisująca informacje o mężczyźnie.
     */
    @Override
    public void wypiszInformacje() {
        Zapisywacz.zapis("Mężczyzna ID " + id + ", wiek " + wiek + ", HP " + hp + ", zarażony: " + zarazony + ", zaszczepiony: " + zaszczepiony + ", zyje: " + isZywy());
    }
}