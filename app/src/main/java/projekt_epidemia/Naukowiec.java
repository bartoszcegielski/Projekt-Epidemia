package projekt_epidemia;
/**
 * Klasa reprezentująca Naukowca w aplikacji Epidemia.
 * Naukowiec jest podklasą klasy Ekspert i posiada unikalne właściwości czyli prowadzenie badań nad szczepionką.
 */
public class Naukowiec extends Ekspert {
    /**
     * Konstruktor klasy Naukowiec.
     * Inicjalizuje wszystkie pola klasy Postac.
     * Ustawia domyślną wartość hp na 50.
     * @param id unikalny identyfikator naukowca.
     * @param zarazony informacja, czy naukowiec jest zarażony.
     * @param zaszczepiony informacja, czy naukowiec jest zaszczepiony.
     * @param x współrzędna X naukowca na planszy.
     * @param y współrzędna Y naukowca na planszy.
     */
    public Naukowiec(int id, boolean zarazony, boolean zaszczepiony, int x, int y) {
        super(id, 50, zarazony, zaszczepiony, x, y);
    }

    /**
     * Metoda wypisująca informacje o naukowcu.
     */
    @Override
    public void wypiszInformacje() {
        Zapisywacz.zapis("Naukowiec ID " + id + ", wiek " + wiek + ", HP " + hp + ", zarażony: " + zarazony + ", zaszczepiony: " + zaszczepiony + ", zyje: " + isZywy());
    }

    /**
     * Metoda, która sprawdza, czy naukowiec spotkał zarażoną postać.
     * Jeśli postać jest zarażona i żyje, naukowiec ją bada i zwiększa postęp badań.
     * Postęp badań zwiększa się o 1 za każdym razem, gdy naukowiec spotyka zarażoną osobę.
     * Jeśli postęp badań osiągnie 50%, to powstaje szczepionka.
     * Jeśli postęp badań >= 50%, naukowiec szczepi spotkaną osobę.
     * @param p postać, którą naukowiec spotyka.
     */
    public void spotkajPostac(Postac p) {
        if (p.isZywy() == true) {
            if (szczepionka < 100 && p.isZarazony() == true) {
                szczepionka = szczepionka + 1;
                Zapisywacz.zapis("Naukowiec ID " + id + " spotyka zarażoną osobę ID " + p.getId() + ". Postęp badań: " + szczepionka);
            }
            if (szczepionka >= 50 && p.isZaszczepiony() == false) {
                p.setZaszczepiony(true);
                Zapisywacz.zapis("Naukowiec ID " + id + " zaszczepił osobę ID " + p.getId() + ".");
            }
        }
    }
}