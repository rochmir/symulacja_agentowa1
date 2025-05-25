package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVWriter {
    private final String nazwaPliku;
    private static final String NAGLOWEK = "Krok,Myszy na planszy,Myszy zywe,Koty,Sery\n";
    private FileWriter writer;

    public CSVWriter() {
        LocalDateTime teraz = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        // Zapisz plik w katalogu projektu
        this.nazwaPliku = System.getProperty("user.dir") + "/symulacja_" + teraz.format(format) + ".csv";
        try {
            this.writer = new FileWriter(nazwaPliku);
            writer.write(NAGLOWEK);
            writer.flush();
        } catch (IOException e) {
            System.err.println("Błąd podczas tworzenia pliku CSV: " + e.getMessage());
        }
    }

    public void zapiszKrok(int krok, int myszyNaPlanszy, int myszyZywe, int koty, int sery) {
        try {
            String wiersz = String.format("%d,%d,%d,%d,%d\n",
                    krok, myszyNaPlanszy, myszyZywe, koty, sery);
            writer.write(wiersz);
            writer.flush();
        } catch (IOException e) {
            System.err.println("Blad podczas zapisywania do pliku CSV: " + e.getMessage());
        }
    }

    public void zamknij() {
        try {
            writer.close();
        } catch (IOException e) {
            System.err.println("Blad podczas zamykania pliku CSV: " + e.getMessage());
        }
    }
}