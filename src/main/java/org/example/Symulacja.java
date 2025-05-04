package org.example;

import java.util.Scanner;

public class Symulacja {
    private static final int CZAS_MIEDZY_KROKAMI = 500; // Czas w milisekundach

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj szerokosc planszy: ");
        int szerokosc = scanner.nextInt();
        System.out.print("Podaj wysokosc planszy: ");
        int wysokosc = scanner.nextInt();
        System.out.print("Podaj poczatkowa liczbe myszy: ");
        int liczbaNorek = scanner.nextInt();
        System.out.print("Podaj poczatkowa liczbe kotow: ");
        int liczbaKotow = scanner.nextInt();

        Plansza plansza = new Plansza(szerokosc, wysokosc, liczbaNorek, liczbaKotow);
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