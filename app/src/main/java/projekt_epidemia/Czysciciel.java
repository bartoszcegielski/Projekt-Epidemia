package projekt_epidemia;
/**
 * Klasa reprezentująca Czyściciela w aplikacji Epidemia.
 * Czyściciel jest podklasą klasy Zolnierz i dziedziczy jej właściwości.
 * Czyściciel ma specjalną zdolność do zabijania zarażonych postaci.
 */
public class Czysciciel extends Zolnierz {
    /**
     * Konstruktor klasy Czysciciel.
     * Inicjalizuje wszystkie pola klasy Postac.
     * Ustawia domyślną wartość hp na 60.
     * @param id unikalny identyfikator czyściciela.
     * @param zarazony informacja, czy czyściciel jest zarażony.
     * @param zaszczepiony informacja, czy czyściciel jest zaszczepiony.
     * @param x współrzędna X czyściciela na planszy.
     * @param y współrzędna Y czyściciela na planszy.
     */
    public Czysciciel(int id, boolean zarazony, boolean zaszczepiony, int x, int y) {
        super(id, 60, zarazony, zaszczepiony, x, y);
    }

    /**
     * Metoda wypisująca informacje o czyścicielu.
     */
    @Override
    public void wypiszInformacje() {
        Zapisywacz.zapis("Czysciciel ID " + id + ", wiek " + wiek + ", HP " + hp + ", zarażony: " + zarazony + ", zaszczepiony: " + zaszczepiony + ", zyje: " + isZywy());
    }

    /**
     * Metoda, która sprawdza, czy czyściciel spotkał zarażoną postać.
     * Jeśli postać jest zarażona, czyściciel ją likwiduje.
     * Ustawia hp postaci na -1 jeśli została zabita przez czyściciela.
     * @param p postać, którą czyściciel spotyka.
     */
    public void spotkajPostac(Postac p) {
        if (p.isZarazony() == true) {
            p.setZywy(false);
            p.setHp(-1);
            Zapisywacz.zapis("Czyściciel ID " + id + " zabił zarażoną osobę ID " + p.getId() + ".");
        }
    }
}