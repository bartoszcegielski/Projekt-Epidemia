package projekt_epidemia;
import java.util.Random;
/**
 * Klasa abstrakcyjna reprezentująca żołnierza w aplikacji Epidemia.
 * Żołnierz jest podklasą klasy Postac i dzieli się na różne typy żołnierzy, czyli medyków i czyścicieli.
 */
public abstract class Zolnierz extends Postac {
    private static final Random losowa = new Random();

    /**
     * Konstruktor klasy Zolnierz.
     * Zawiera wszystkie pola klasy Postac.
     * @param id unikalny identyfikator żołnierza.
     * @param hp punkty zdrowia żołnierza.
     * @param zarazony informacja, czy żołnierz jest zarażony.
     * @param zaszczepiony informacja, czy żołnierz jest zaszczepiony.
     * @param x współrzędna X żołnierza na planszy.
     * @param y współrzędna Y żołnierza na planszy.
     */
    public Zolnierz(int id, int hp, boolean zarazony, boolean zaszczepiony, int x, int y) {
        super(id, hp, zarazony, zaszczepiony, x, y);
    }

    /** 
     * Metoda, która sprawdza, czy żołnierz może zostać zarażony przez inną postać.
     * Jeśli żołnierz nie jest zarażony ani zaszczepiony, a inna postać jest zarażona,
     * to istnieje 20% szans, że żołnierz zostanie zarażony.
     * @param p postać, która może zarazić żołnierza.
     */
    public void ZolnierzZarazenie(Postac p) {
        if (zarazony == false && zaszczepiony == false && p.isZarazony() == true) {
            double szansa = losowa.nextDouble(); // losuje z przedziału 0.0, 1.0
            if (szansa < 0.2) {
                zarazony = true;
                Zapisywacz.zapis("Zolnierz ID " + id + " został zarażony.");
            }
            else
            {
                Zapisywacz.zapis("Zolnierz ID " + id + " nie został zarażony.");
            }
        }
    }
}
