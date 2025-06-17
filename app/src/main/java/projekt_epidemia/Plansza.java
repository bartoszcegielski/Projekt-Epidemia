package projekt_epidemia;
import java.util.List;
/**
 * Klasa reprezentująca planszę w aplikacji Epidemia.
 * Plansza jest odpowiedzialna za zarządzanie pozycjami postaci oraz ich interakcjami.
 */
public class Plansza {
    private int rozmiar;
    private List<Postac> postaci; //Agregacja - Plansza posiada listę postaci (List<Postac>), ale postacie mogą istnieć niezależnie od planszy .
    private char[][] mapa; // Kompozycja - plansza "składa się" z mapy, a mapa nie ma sensu bez planszy.

    /**
     * Konstruktor klasy Plansza.
     * Inicjalizuje rozmiar planszy, listę postaci oraz tworzy mapę.
     * @param rozmiar rozmiar planszy.
     * @param postaci lista postaci znajdujących się na planszy.
     */
    public Plansza(int rozmiar, List<Postac> postaci) {
        this.rozmiar = rozmiar;
        this.postaci = postaci;
        this.mapa = new char[rozmiar][rozmiar];
        czyszczenie();
    }

    /**
     * Metoda aktualizująca stan planszy.
     * Czyści mapę i umieszcza na niej postacie w ich aktualnych pozycjach.
     */
    public void aktualizacja() {
        czyszczenie();
        for (Postac obiekt : postaci) { //po wszystkich postaciach
            if (obiekt.isZywy() == true) {
                int x = obiekt.getX();
                int y = obiekt.getY();
                if (x >= 0 && x < rozmiar && y >= 0 && y < rozmiar) {
                    if (obiekt instanceof Mezczyzna) mapa[x][y] = 'M';
                    else if (obiekt instanceof Kobieta) mapa[x][y] = 'K';
                    else if (obiekt instanceof Lekarz) mapa[x][y] = 'L';
                    else if (obiekt instanceof Czysciciel) mapa[x][y] = 'C';
                    else if (obiekt instanceof Medyk) mapa[x][y] = 'D';
                    else if (obiekt instanceof Naukowiec) mapa[x][y] = 'N';
                }
            }
        }
    }
    /**
     * Metoda do resetowania stanu planszy przed aktualizacją.
     */
    private void czyszczenie() {
        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                mapa[i][j] = '.';
            }
        }
    }

    /**
     * Metoda do wyświetlania aktualnego stanu planszy.
     * Wypisuje mapę wraz z liczbą postaci i postępem badań nad szczepionką.
     * @param tura numer aktualnej tury.
     */
    public void wyswietlMape(int tura) {
        System.out.println("\n");
        System.out.println("=== Tura " + tura + " ===");

        int liczbaPostaci = 0;

        for (int i = 0; i < rozmiar; i++) {
            for (int j = 0; j < rozmiar; j++) {
                char znak = mapa[i][j];
                boolean czyZarazony = false;
                boolean czyZaszczepiony = false;
                for (Postac p : postaci) {
                    if (p.isZywy() && p.getX() == i && p.getY() == j) {
                        if (p.isZarazony()) {
                            czyZarazony = true;
                        }
                        if (p.isZaszczepiony()) {
                            czyZaszczepiony = true;
                        }
                    }
                }
                if (znak != '.') liczbaPostaci++;
                if (czyZarazony) {
                    System.out.print("\u001B[31m" + znak + "\u001B[0m "); // czerwony
                } else if (czyZaszczepiony) {
                    System.out.print("\u001B[34m" + znak + "\u001B[0m "); // niebieski
                } else {
                    System.out.print(znak + " ");
                }
            }
            System.out.println();
        }

        System.out.println("Liczba postaci: " + liczbaPostaci);
        System.out.println("Postęp badań nad szczepionką: " + Postac.getSzczepionka());
    }
    
    /**
     * Metoda sprawdzająca, czy dana pozycja na planszy jest wolna.
     * @param x współrzędna X pozycji.
     * @param y współrzędna Y pozycji.
     * @return true, jeśli pozycja jest wolna, false w przeciwnym przypadku.
     */
    public boolean czyWolne(int x, int y) {
        if (x < 0 || x >= rozmiar || y < 0 || y >= rozmiar) return false;
        for (Postac inny : postaci) {
            if (inny.isZywy() && inny.getX() == x && inny.getY() == y) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metoda zwracająca rozmiar planszy.
     * @return rozmiar planszy.
     */
    public List<Postac> getPostaci() {
        return postaci;
    }
}