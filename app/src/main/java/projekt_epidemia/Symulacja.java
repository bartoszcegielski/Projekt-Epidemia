package projekt_epidemia;
import java.util.List;
/**
 * Klasa Symulacja odpowiada za zarządzanie przebiegiem symulacji w aplikacji Epidemia.
 */
public class Symulacja {
    private List<Postac> postaci;
    private Plansza plansza;
    private boolean szczepionkaOgloszona = false;

    /**
     * Konstruktor klasy Symulacja.
     * Inicjalizuje listę postaci i planszę.
     * @param postaci lista postaci biorących udział w symulacji
     * @param plansza plansza, na której odbywa się symulacja
     */
    public Symulacja(List<Postac> postaci, Plansza plansza) {
        this.postaci = postaci;
        this.plansza = plansza;
    }

    /**
     * Metoda uruchamiająca symulację na określoną liczbę tur.
     * W każdej turze postacie wykonują ruch, następują interakcje i aktualizacja stanu planszy.
     * Jeśli postać jest zarażona, traci 1 punkt zdrowia.
     * @param liczbaTur liczba tur.
     */
    public void uruchom(int liczbaTur) {
        int zarazeni = 0;
        for (int tura = 1; tura <= liczbaTur; tura++) {
            if (Postac.getSzczepionka() >= 50 && !szczepionkaOgloszona) {
            Zapisywacz.zapis("!!!POWSTAŁA SZCZEPIONKA!!!");
            zaszczepNaukowcow();
            szczepionkaOgloszona = true;
        }
            // Ruch wszystkich postaci, jeśli zarazony hp-1
            for (Postac p : postaci) {
                if (p.isZarazony() && p.isZywy()) 
                {
                    p.setHp(p.getHp() - 1);
                }
                p.ruch(plansza);
            }

            for (Postac p1 : postaci) {
                if (!p1.isZywy()) continue;
                if(p1.getHp() == 0)
                {
                    p1.setZywy(false);
                    Zapisywacz.zapis("Postac o ID " + p1.getId() + " umiera z powodu choroby.");
                }
                for (Postac p2 : postaci) {
                    if (p1 == p2 || !p2.isZywy()) continue;
                    // Interakcja, jeśli postaci są na sąsiednich polach (góra, dół, lewo, prawo)
                    int dx = Math.abs(p1.getX() - p2.getX());
                    int dy = Math.abs(p1.getY() - p2.getY());
                    if ((dx == 1 && dy == 0) || (dx == 0 && dy == 1)) {
                        if (p1 instanceof Mezczyzna || p1 instanceof Kobieta) {
                            ((Cywil) p1).CywilZarazenie(p2);
                        }
                        if (p1 instanceof Lekarz) {
                            ((Ekspert) p1).EkspertZarazenie(p2);
                            ((Lekarz) p1).spotkajPostac(p2); //Polimorfizm - Różne klasy (Lekarz, Naukowiec, Mezczyzna itd.) mogą mieć własną implementację tej metody.
                        } else if (p1 instanceof Czysciciel) {
                            ((Zolnierz) p1).ZolnierzZarazenie(p2);
                            ((Czysciciel) p1).spotkajPostac(p2);
                        } else if (p1 instanceof Medyk) {
                            ((Zolnierz) p1).ZolnierzZarazenie(p2);
                            ((Medyk) p1).spotkajPostac(p2);
                        } else if (p1 instanceof Naukowiec) {
                            ((Ekspert) p1).EkspertZarazenie(p2);
                            ((Naukowiec) p1).spotkajPostac(p2);
                        }
                    }
                }
            }
            // Aktualizacja i wyświetlenie mapy
            plansza.aktualizacja();
            plansza.wyswietlMape(tura);

            // Dodanie opóźnienia 1 sekundy
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Przerwano symulację: " + e.getMessage());
            }
        }
        for (Postac p : postaci) {
            if (p.isZarazony() && p.isZywy()) {
                zarazeni++;
            }
            }
        if (zarazeni == 0) {
            Zapisywacz.zapis(" ");
            Zapisywacz.zapis("Choroba została wytępiona!");
            Zapisywacz.zapis(" ");
        }
        else {
            Zapisywacz.zapis(" ");
            Zapisywacz.zapis("Choroba nie została wytępiona.");
            Zapisywacz.zapis(" ");
        }
    }

    private void zaszczepNaukowcow() {
        for (Postac postac : postaci) {
            if (postac instanceof Naukowiec && !postac.isZaszczepiony()) {
                postac.setZaszczepiony(true);
                Zapisywacz.zapis("Naukowiec ID " + postac.getId() + " został zaszczepiony.");
            }
        }
    }
}