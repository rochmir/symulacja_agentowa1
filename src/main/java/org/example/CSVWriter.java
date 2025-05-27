package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    private FileWriter writer;

    public CSVWriter(String filePath) {
        try {
            writer = new FileWriter(filePath);
            writer.append("krok;liczbaKotow;liczbaMyszy;\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void zapisz(int krok, int liczbaKotow, int liczbaMyszy) {
        try {
            writer.append(String.format("%d;%d;%d\n", krok, liczbaKotow, liczbaMyszy));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void zamknij() {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}