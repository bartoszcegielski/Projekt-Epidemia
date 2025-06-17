package projekt_epidemia;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 * Klasa Menu służy do interakcji z użytkownikiem i uruchamiania symulacji.
 */
public class Menu {
    private static final Random losowa = new Random();

    /**
     * Metoda uruchamiająca menu i zbierająca dane wejściowe od użytkownika.
     * Tworzy postacie na podstawie podanych danych, sprawdza poprawność wprowadzonych wartości
     * i uruchamia symulację.
     */
    public void uruchom() {
        Scanner scanner = new Scanner(System.in);
        int sumaPostaci = 0;
        System.out.print("Witaj w symulatorze epidemii!\n");
        System.out.println("Wprowadź dane do symulacji:");
        System.out.println("Liczba postaci nie może przekraczać 500, a rozmiar mapy musi być z zakresu 10-50. Liczba tur powinna być z zakresu 1-10000.");
        System.out.print("Podaj rozmiar mapy: ");
        int rozmiar = scanner.nextInt();
        System.out.print("Podaj liczbę mężczyzn: ");
        int liczbaMezczyzn = scanner.nextInt();
        sumaPostaci = sumaPostaci + liczbaMezczyzn;
        System.out.print("Podaj liczbę kobiet: ");
        int liczbaKobiet = scanner.nextInt();
        sumaPostaci = sumaPostaci + liczbaKobiet;
        System.out.print("Podaj liczbę lekarzy: ");
        int liczbaLekarzy = scanner.nextInt();
        sumaPostaci = sumaPostaci + liczbaLekarzy;
        System.out.print("Podaj liczbę naukowców: ");
        int liczbaNaukowcow = scanner.nextInt();
        sumaPostaci = sumaPostaci + liczbaNaukowcow;
        System.out.print("Podaj liczbę czyścicieli: ");
        int liczbaCzyscicieli = scanner.nextInt();
        sumaPostaci = sumaPostaci + liczbaCzyscicieli;
        System.out.print("Podaj liczbę medyków: ");
        int liczbaMedykow = scanner.nextInt();
        sumaPostaci = sumaPostaci + liczbaMedykow;
        System.out.print("Podaj liczbę tur: ");
        int liczbaTur = scanner.nextInt();

        System.out.print(" ");

        sprawdzBledy(rozmiar, sumaPostaci, liczbaTur);

        List<Postac> postaci = new ArrayList<>();
        Plansza plansza = new Plansza(rozmiar, postaci);

        int id = 1;
        for (int i = 0; i < liczbaMezczyzn; i++) {
            int[] pozycja = losujWolnaPozycje(plansza, rozmiar);
            postaci.add(new Mezczyzna(id++, false, false, pozycja[0], pozycja[1]));
        }
        for (int i = 0; i < liczbaKobiet; i++) {
            int[] pozycja = losujWolnaPozycje(plansza, rozmiar);
            postaci.add(new Kobieta(id++, false, false, pozycja[0], pozycja[1]));
        }
        for (int i = 0; i < liczbaLekarzy; i++) {
            int[] pozycja = losujWolnaPozycje(plansza, rozmiar);
            postaci.add(new Lekarz(id++, false, false, pozycja[0], pozycja[1]));
        }
        for (int i = 0; i < liczbaCzyscicieli; i++) {
            int[] pozycja = losujWolnaPozycje(plansza, rozmiar);
            postaci.add(new Czysciciel(id++, false, false, pozycja[0], pozycja[1]));
        }
        for (int i = 0; i < liczbaMedykow; i++) {
            int[] pozycja = losujWolnaPozycje(plansza, rozmiar);
            postaci.add(new Medyk(id++, false, false, pozycja[0], pozycja[1]));
        }
        for (int i = 0; i < liczbaNaukowcow; i++) {
            int[] pozycja = losujWolnaPozycje(plansza, rozmiar);
            postaci.add(new Naukowiec(id++, false, false, pozycja[0], pozycja[1]));
        }

        Postac.setSzczepionka(0);

        pierwszyZarazony(scanner, postaci);

        wypiszStanPoczatkowy(postaci);
    
        Symulacja symulacja = new Symulacja(postaci, plansza);
        symulacja.uruchom(liczbaTur);

        scanner.close();

        wypiszStanKoncowy(postaci);
    }
    /**
     * Metoda która ustawia pierwszą zarażoną postać w grze.
     * Użytkownik wybiera klasę postaci, która ma zostać zarażona.
     * Losuje jedną postać z wybranej klasy i ustawia jej status zarażenia na true.
     * @param scanner Scanner do odczytu danych wejściowych od użytkownika.
     * @param postaci Lista wszystkich postaci w grze.
     */
    private void pierwszyZarazony(Scanner scanner, List<Postac> postaci) {
        System.out.println("Wybierz kto ma być pierwszym zarażonym:");
        System.out.println("1 - Mezczyzna");
        System.out.println("2 - Kobieta");
        System.out.println("3 - Lekarz");
        System.out.println("4 - Naukowiec");
        System.out.println("5 - Czysciciel");
        System.out.println("6 - Medyk");
        int wyborKlasy = scanner.nextInt();

        // Losowanie osoby z wybranej klasy do zarażenia
        boolean zarazono = false;
        while (!zarazono && !postaci.isEmpty()) {
            int losowyIndeks = losowa.nextInt(postaci.size());
            Postac p = postaci.get(losowyIndeks);
            switch (wyborKlasy) {
                case 1:
                    if (p instanceof Mezczyzna) {
                        p.setZarazony(true);
                        zarazono = true;
                    }
                    break;
                case 2:
                    if (p instanceof Kobieta) {
                        p.setZarazony(true);
                        zarazono = true;
                    }
                    break;
                case 3:
                    if (p instanceof Lekarz) {
                        p.setZarazony(true);
                        zarazono = true;
                    }
                    break;
                case 4:
                    if (p instanceof Naukowiec) {
                        p.setZarazony(true);
                        zarazono = true;
                    }
                    break;
                case 5:
                    if (p instanceof Czysciciel) {
                        p.setZarazony(true);
                        zarazono = true;
                    }
                    break;
                case 6:
                    if (p instanceof Medyk) {
                        p.setZarazony(true);
                        zarazono = true;
                    }
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór klasy!");
                    zarazono = true;
            }
        }
    }

    /**
     * Metoda wypisująca początkowy stan postaci.
     * Zapisuje informacje o każdej postaci do pliku.
     * @param postaci Lista postaci, których stan ma być wypisany.
     */
    private void wypiszStanPoczatkowy(List<Postac> postaci) {
        Zapisywacz.wyczysc();
        Zapisywacz.zapis("====== Początkowy stan postaci ======");
        for (Postac p : postaci) {
            p.wypiszInformacje();
        }
        Zapisywacz.zapis("====== Symulacja ======");
    }

    /**
     * Metoda wypisująca końcowy stan postaci.
     * Zapisuje informacje o każdej postaci do pliku po zakończeniu symulacji.
     * @param postaci Lista postaci, których stan ma być wypisany.
     */
    private void wypiszStanKoncowy(List<Postac> postaci) {
        Zapisywacz.zapis("====== Końcowy stan postaci ======");
        for (Postac p : postaci) {
            p.wypiszInformacje();
        }
        Zapisywacz.zapis(" ");
        Zapisywacz.zapis("Postęp badań nad szczepionką: " + Postac.getSzczepionka());
        Zapisywacz.zapis("====== Koniec symulacji ======");
    }
    
    /**
     * Metoda losująca wolną pozycję na planszy.
     * Sprawdza, czy dana pozycja jest wolna i zwraca jej współrzędne.
     * @param plansza Plansza, na której szukamy wolnej pozycji.
     * @return Tablica z współrzędnymi [x, y] wolnej pozycji.
     */
    private int[] losujWolnaPozycje(Plansza plansza, int rozmiar) {
        int x, y;
        do {
            x = losowa.nextInt(rozmiar);
            y = losowa.nextInt(rozmiar);
        } while (!plansza.czyWolne(x, y));
        return new int[]{x, y};
    }

    /**
     * Metoda sprawdzająca błędy wprowadzonych danych.
     * @param rozmiar rozmiar planszy
     * @param sumaPostaci suma wszystkich postaci
     * @param liczbaTur liczba tur
     */
    private static void sprawdzBledy(int rozmiar, int sumaPostaci, int liczbaTur) {
        if (rozmiar < 10 || rozmiar > 50) {
            System.out.println("Error: Rozmiar musi być z zakresu 10-50!");
            System.exit(-1);
        }
        if (sumaPostaci > 500 || sumaPostaci < 1) {
            System.out.println("Error: Liczba postaci powinna być z zakresu 1-500!");
            System.exit(-2);
        }
        if (liczbaTur < 1 || liczbaTur > 10000) {
            System.out.println("Error: Liczba tur powinna być z zakresu 1-10000!");
            System.exit(-3);
        }
    }
}