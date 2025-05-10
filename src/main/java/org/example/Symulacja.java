package org.example;

import java.util.Scanner;

public class Symulacja {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int szerokoscPlanszy = wczytajLiczbe(scanner, "Podaj szerokosc planszy(liczba>0): ", 1, 100);
        int wysokoscPlanszy = wczytajLiczbe(scanner, "Podaj wysokosc planszy(liczba>0): ", 1, 100);

        // Maksymalna liczba obiektow to polowa pol planszy
        int maxObiekty = (szerokoscPlanszy * wysokoscPlanszy) / 2;

        int liczbaNorek = wczytajLiczbe(scanner, "Podaj liczbe myszy: ", 0, maxObiekty);
        int liczbaKotow = wczytajLiczbe(scanner, "Podaj liczbe kotow: ", 0, maxObiekty);
        int liczbaSera = wczytajLiczbe(scanner, "Podaj liczbe sera: ", 0, maxObiekty);

        // Sprawdzenie czy suma obiektow nie przekracza liczby pol
        if (liczbaNorek + liczbaKotow + liczbaSera > szerokoscPlanszy * wysokoscPlanszy) {
            System.out.println("Za duzo obiektow w stosunku do rozmiaru planszy!");
            return;
        }

        try {
            Plansza plansza = new Plansza(szerokoscPlanszy, wysokoscPlanszy, liczbaNorek, liczbaKotow, liczbaSera);
            uruchomSymulacje(plansza);
        } catch (Exception e) {
            System.out.println("Wystapil blad podczas tworzenia planszy: " + e.getMessage());
        }
    }

    private static void uruchomSymulacje(Plansza plansza) {
        System.out.println("Rozpoczynam symulacje...");
        int krok = 0;
        while (!plansza.getKoty().isEmpty() && plansza.getLiczbaZywychMyszy() > 0) {
            System.out.println("Krok: " + krok++);
            plansza.wyswietlPlansze();
            plansza.symulujKrok();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Symulacja zostala przerwana.");
                break;
            }
        }
        wyswietlKomunikatZakonczenia(plansza);
    }

    private static int wczytajLiczbe(Scanner scanner, String komunikat, int min, int max) {
        int liczba;
        while (true) {
            System.out.print(komunikat);
            try {
                if (scanner.hasNextInt()) {
                    liczba = scanner.nextInt();
                    if (liczba >= min && liczba <= max) {
                        break;
                    }
                    System.out.printf("Podaj liczbe z zakresu %d-%d!\n", min, max);
                } else {
                    System.out.println("To nie jest liczba calkowita!");
                    scanner.next();
                }
            } catch (Exception e) {
                System.out.println("Wystapil blad podczas wczytywania liczby.");
                scanner.next();
            }
        }
        return liczba;
    }

    private static void wyswietlKomunikatZakonczenia(Plansza plansza) {
        if (plansza.getKoty().isEmpty() && (plansza.getLiczbaZywychMyszy() != 0)) {
            System.out.println("Symulacja zakonczona: wszystkie koty zniknely.");
        } else if (plansza.getLiczbaZywychMyszy() == 0 && !plansza.getKoty().isEmpty()) {
            System.out.println("Symulacja zakonczona: wszystkie myszy zniknely.");
        } else {
            System.out.println("Symulacja zakonczona.");
        }
    }
}