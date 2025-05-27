import org.example.Plansza;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlanszaTest {

    @Test
    void testPustaPlansza() {
        Plansza plansza = new Plansza(10, 10, 0, 0, 0);
        assertEquals(0, plansza.getKoty().size(), "Na planszy nie powinno być kotów");
        assertEquals(0, plansza.getMyszy().size(), "Na planszy nie powinno być myszy");
        assertEquals(0, plansza.getNorki().size(), "Na planszy nie powinno być norek");
        assertEquals(0, plansza.getSery().size(), "Na planszy nie powinno być sera");
        assertEquals(0, plansza.getLiczbaZywychMyszy(), "Nie powinno być żywych myszy");
    }
}