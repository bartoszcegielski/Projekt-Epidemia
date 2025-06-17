package projekt_epidemia;
import java.util.Random;
/**
 * Klasa abstrakcyjna reprezentująca cywila w aplikacji Epidemia.
 * Cywil jest podklasą klasy Postac i dzieli się na Mezczyznę i Kobietę.
 */
public abstract class Cywil extends Postac {
    private static final Random losowa = new Random();
    /**
     * Konstruktor klasy Cywil.
     * Inicjalizuje wszystkie pola klasy Postac.
     * @param id unikalny identyfikator cywila.
     * @param hp punkty zdrowia cywila.
     * @param zarazony informacja, czy cywil jest zarażony.
     * @param zaszczepiony informacja, czy cywil jest zaszczepiony.
     * @param x współrzędna X cywila na planszy.
     * @param y współrzędna Y cywila na planszy.
     */
    public Cywil(int id, int hp, boolean zarazony, boolean zaszczepiony, int x, int y) {
        super(id, hp, zarazony, zaszczepiony, x, y);
    }

    /** 
     * Metoda, która sprawdza, czy cywil może zostać zarażony przez inną postać.
     * Jeśli cywil nie jest zarażony ani zaszczepiony, a inna postać jest zarażona,
     * to istnieje 50% szans, że cywil zostanie zarażony.
     * @param p postać, która może zarazić cywila.
     */
    public void CywilZarazenie(Postac p) {
        if (zarazony == false && zaszczepiony == false && p.isZarazony() == true) {
            if (losowa.nextBoolean() == true) {
                zarazony = true;
                Zapisywacz.zapis("Cywil ID " + id + " został zarażony.");
            }
            else
            {
                Zapisywacz.zapis("Cywil ID " + id + " nie został zarażony.");
            }
        }
    }
}