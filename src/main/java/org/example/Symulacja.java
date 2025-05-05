package org.example;

import java.util.Scanner;

public class Symulacja {
    private static final int CZAS_MIEDZY_KROKAMI = 500; // Czas w milisekundach

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int szerokosc = 0, wysokosc = 0;

        while (szerokosc <= 0) {
            System.out.print("Podaj szerokosc planszy (liczba > 0): ");
            if (scanner.hasNextInt()) {
                szerokosc = scanner.nextInt();
                if (szerokosc <= 0) {
                    System.out.println("Szerokosc musi byc liczba wieksza od 0.");
                }
            } else {
                System.out.println("Niepoprawny format. Podaj liczbe calkowita.");
                scanner.next(); // Wyczysc bledne dane
            }
        }

        while (wysokosc <= 0) {
            System.out.print("Podaj wysokosc planszy (liczba > 0): ");
            if (scanner.hasNextInt()) {
                wysokosc = scanner.nextInt();
                if (wysokosc <= 0) {
                    System.out.println("Wysokosc musi byc liczba wieksza od 0.");
                }
            } else {
                System.out.println("Niepoprawny format. Podaj liczbe calkowita.");
                scanner.next(); // Wyczysc bledne dane
            }
        }

        int maksymalnaLiczbaPol = szerokosc * wysokosc;

        System.out.print("Podaj poczatkowa liczbe myszy: ");
        int liczbaMyszy = scanner.nextInt();
        System.out.print("Podaj poczatkowa liczbe kotow: ");
        int liczbaKotow = scanner.nextInt();
        System.out.print("Podaj poczatkowa liczbe sera: ");
        int liczbaSera = scanner.nextInt();

        if (liczbaMyszy + liczbaKotow + liczbaSera > maksymalnaLiczbaPol) {
            System.out.println("Suma obiektow (myszy, kotow i sera) przekracza liczbe dostepnych pol na planszy. Zmniejsz liczby obiektow.");
            return;
        }

        Plansza plansza = new Plansza(szerokosc, wysokosc, liczbaMyszy, liczbaKotow);
        int kroki = 0;

        System.out.println("Uruchomiono symulacje. Trwa do momentu wyginiecia wszystkich zywych myszy lub kotow.");

        while (!plansza.getKoty().isEmpty() && plansza.getLiczbaZywychMyszy() > 0) {
            System.out.println("Krok: " + kroki);
            plansza.wyswietlPlansze();
            plansza.symulujKrok();
            kroki++;
            try {
                Thread.sleep(CZAS_MIEDZY_KROKAMI);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("\nSymulacja przerwana.");
                break;
            }
        }

        System.out.println("--------------------");
        System.out.println("Symulacja zakonczona po " + kroki + " krokach.");
        if (plansza.getLiczbaZywychMyszy() == 0) {
            System.out.println("Wszystkie zywe myszy zniknely!");
        } else if (plansza.getKoty().isEmpty()) {
            System.out.println("Wszystkie koty zniknely!");
        }
        scanner.close();
    }
}