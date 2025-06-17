package projekt_epidemia;
/**
 * Klasa reprezentująca Medyka w aplikacji Epidemia.
 * Medyk jest podklasą klasy Zolnierz i dziedziczy jej właściwości.
 * Medyk ma zdolność do leczenia innych postaci, przywracając im część zdrowia.
 */
public class Medyk extends Zolnierz {
    /**
     * Konstruktor klasy Medyk.
     * Inicjalizuje wszystkie pola klasy Postac.
     * Ustawia domyślną wartość hp na 60.
     * @param id unikalny identyfikator medyka.
     * @param zarazony informacja, czy medyk jest zarażony.
     * @param zaszczepiony informacja, czy medyk jest zaszczepiony.
     * @param x współrzędna X medyka na planszy.
     * @param y współrzędna Y medyka na planszy.
     */
    public Medyk(int id, boolean zarazony, boolean zaszczepiony, int x, int y) {
        super(id, 60, zarazony, zaszczepiony, x, y);
    }

    /**
     * Metoda wypisująca informacje o medyku.
     */
    @Override
    public void wypiszInformacje() {
        Zapisywacz.zapis("Medyk ID " + id + ", wiek " + wiek + ", HP " + hp + ", zarażony: " + zarazony + ", zaszczepiony: " + zaszczepiony + ", zyje: " + isZywy());
    }

    /**
     * Metoda, która sprawdza, czy medyk spotkał postać.
     * Jeśli postać jest żywa i ma mniej zdrowia niż maksymalne,
     * medyk przywraca do 10 punktów HP.
     * @param p postać, którą medyk spotyka.
     */
    public void spotkajPostac(Postac p) {
        if (p.isZywy() == true && p.getHp() < p.getMaxHp()) {
            int noweHp = p.getHp() + 10;
            p.setHp(noweHp);
            Zapisywacz.zapis("Medyk ID " + id + " podleczył osobe ID " + p.getId() + ".");
        }
    }
}