package projekt_epidemia;
import java.util.Random;
/**
 * Klasa abstrakcyjna reprezentująca Eksperta w aplikacji Epidemia.
 * Ekspert jest podklasą klasy Postac i dzieli się na różne typy ekspertów czyli lekarzy oraz naukowców.
 */
public abstract class Ekspert extends Postac {
    private static final Random losowa = new Random();

    /**
     * Konstruktor klasy Ekspert.
     * Zawiera wszystkie pola klasy Postac.
     * @param id unikalny identyfikator eksperta.
     * @param hp punkty zdrowia eksperta.
     * @param zarazony informacja, czy ekspert jest zarażony.   
     * @param zaszczepiony informacja, czy ekspert jest zaszczepiony.
     * @param x współrzędna X eksperta na planszy.
     * @param y współrzędna Y eksperta na planszy.
     */
    public Ekspert(int id, int hp, boolean zarazony, boolean zaszczepiony, int x, int y) {
        super(id, hp, zarazony, zaszczepiony, x, y);
    }

    /** 
     * Metoda, która sprawdza, czy ekspert może zostać zarażony przez inną postać.
     * Jeśli ekspert nie jest zarażony ani zaszczepiony, a inna postać jest zarażona,
     * to istnieje 20% szans, że ekspert zostanie zarażony.
     * W przypadku zarażenia, informacja jest zapisywana w logu.
     * @param p postać, która może zarazić eksperta.
     */
    public void EkspertZarazenie(Postac p) {
        if (zarazony == false && zaszczepiony == false && p.isZarazony() == true) {
            double szansa = losowa.nextDouble(); // losuje z przedziału 0.0, 1.0
            if (szansa < 0.2) {
                zarazony = true;
                Zapisywacz.zapis("Ekspert ID " + id + " został zarażony.");
            }
            else
            {
                Zapisywacz.zapis("Ekspert ID " + id + " nie został zarażony.");
            }
        }
    }
}
