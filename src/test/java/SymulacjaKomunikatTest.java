import org.example.Plansza;
import org.example.Symulacja;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class SymulacjaKomunikatTest {

    private String przechwycKomunikat(Plansza plansza, int krok) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream oryginalny = System.out;
        System.setOut(new PrintStream(out));
        try {
            // użycie refleksji, bo metoda jest prywatna
            var metoda = Symulacja.class.getDeclaredMethod("wyswietlKomunikatZakonczenia", Plansza.class, int.class);
            metoda.setAccessible(true);
            metoda.invoke(null, plansza, krok);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(oryginalny);
        }
        return out.toString();
    }

    @Test
    void testKomunikat_KotyNie_MyszyTak() {
        Plansza plansza = new Plansza(10, 10, 1, 0, 0); // 1 norka, 0 kotów
        String komunikat = przechwycKomunikat(plansza, 5);
        assertTrue(komunikat.contains("wszystkie koty zniknely po 5 krokach"));
    }

    @Test
    void testKomunikat_KotyTak_MyszyNie() {
        Plansza plansza = new Plansza(10, 10, 0, 1, 0); // 0 norek, 1 kot
        String komunikat = przechwycKomunikat(plansza, 7);
        assertTrue(komunikat.contains("wszystkie myszy zniknely po 7 krokach"));
    }

    @Test
    void testKomunikat_KotyNie_MyszyNie() {
        Plansza plansza = new Plansza(10, 10, 0, 0, 0); // 0 norek, 0 kotów
        String komunikat = przechwycKomunikat(plansza, 3);
        assertTrue(komunikat.contains("Symulacja zakonczona po 3 krokach"));
    }
}