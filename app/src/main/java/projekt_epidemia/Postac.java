package projekt_epidemia;
import java.util.Random;
/**
 * Główna klasa abstrakcyjna reprezentująca wszystkie postaci w aplikacji Epidemia.
 * Postać jest podstawową klasą, z której dziedziczą inne klasy reprezentujące konkretne typy postaci.
 */

public abstract class Postac {
    /**
     * Unikalny identyfikator postaci.
     * Identyfikator jest generowany przy tworzeniu postaci.
     */
    protected int id;
    /**
     * Wiek postaci, losowany z przedziału 18-67 lat.
     * Wiek jest generowany losowo przy tworzeniu postaci.
     */
    protected int wiek;
    /**
     * Punkty zdrowia postaci.
     */
    protected int hp;
    /**
     * Maksymalne punkty zdrowia postaci.
     * Ustawiane na wartość początkową hp przy tworzeniu postaci.
     */
    protected int maxHp;
    /**
     * Informacja, czy postać jest zarażona.
     * Domyślnie ustawiana na false.
     */
    protected boolean zarazony;
    /**
     * Informacja, czy postać jest zaszczepiona.
     * Domyślnie ustawiana na false.
     */
    protected boolean zaszczepiony;
    /**
     * Informacja, czy postać jest żywa.
     * Domyślnie ustawiana na true.
     */
    protected boolean zywy;
    /**
     * Współrzędna X postaci na planszy.
     */
    protected int x;
    /**
     * Współrzędna Y postaci na planszy.
     */
    protected int y;
    /**
     * Postęp szczepionki.
     * Domyślnie ustawiana na 0.
     * Maksymalna wartość to 100.
     */
    protected static int szczepionka = 0;
    /**
     * Losowy generator liczb, używany do generowania wieku postaci oraz do losowania ruchów.
     */
    private static final Random losowa1 = new Random();
    /**
     * Konstruktor klasy Postac.
     * @param id unikalny identyfikator postaci
     * @param hp punkty zdrowia postaci
     * @param zarazony informacja, czy postać jest zarażona
     * @param zaszczepiony informacja, czy postać jest zaszczepiona
     * @param x współrzędna X na planszy
     * @param y współrzędna Y na planszy
     * * Konstruktor ten inicjalizuje wszystkie pola klasy Postac.
     */
    public Postac(int id, int hp, boolean zarazony, boolean zaszczepiony, int x, int y) {
        this.id = id;
        this.wiek = losowa1.nextInt(50) + 18;
        this.hp = hp;
        this.maxHp = hp;
        this.zarazony = zarazony;
        this.zaszczepiony = zaszczepiony;
        this.x = x;
        this.y = y;
        this.zywy = true;
    }
    /**
     * Metoda abstrakcyjna, służy do wypisywania informacji o postaci.
     */
    public abstract void wypiszInformacje();

    /** 
     * Metoda do wykonywania ruchu postaci na planszy.
     * Ruch jest losowy i może być w pionie lub poziomie (50% szans na każdy kierunek).
     * Jeśli pole jest zajęte, postać nie zmienia swojej pozycji.
     * @param plansza plansza, na której znajduje się postać.   
     */
    //x i y to aktualna pozycja postaci na planszy (np. x = 5, y = 3).
    //ruch to tablica int[2] reprezentująca jeden z możliwych ruchów
    public void ruch(Plansza plansza) {
        if (!zywy) return;
    
        int nowyX = x;
        int nowyY = y;
    
        if (losowa1.nextBoolean()) {
            // Ruch w pionie
            if (losowa1.nextBoolean()) {
                nowyX = x + 1;
            } else {
                nowyX = x - 1;
            }
        } else {
            // Ruch w poziomie
            if (losowa1.nextBoolean()) {
                nowyY = y + 1;
            } else {
                nowyY = y - 1;
            }
        }
        if (plansza.czyWolne(nowyX, nowyY)) {
            x = nowyX;
            y = nowyY;
        }
    }
    
    public int getId() { return id; }
    public int getWiek() { return wiek; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isZarazony() { return zarazony; }
    public boolean isZaszczepiony() { return zaszczepiony; }
    public boolean isZywy() { return zywy; }
    public static int getSzczepionka() { return szczepionka; }

    public void setHp(int hp) { this.hp = Math.min(hp, maxHp); }
    public void setZarazony(boolean zarazony) { this.zarazony = zarazony; }
    public void setZaszczepiony(boolean zaszczepiony) { this.zaszczepiony = zaszczepiony; }
    public void setZywy(boolean zywy) {this.zywy = zywy; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public static void setSzczepionka(int value) { szczepionka = Math.min(value, 100); }
}
