package org.example.objects.zwierzeta;

public class Kot extends Zwierze {
    private static final int MAX_ENERGIA = 20;

    public Kot(int szerokoscPlanszy, int wysokoscPlanszy) {
        super(szerokoscPlanszy, wysokoscPlanszy);
        this.energia = MAX_ENERGIA;
    }

    @Override
    public void poruszajSie(int szerokoscPlanszy, int wysokoscPlanszy) {
        int dx = (int) (Math.random() * 5) - 2;
        int dy = (int) (Math.random() * 5) - 2;
        int noweX = x + dx;
        int noweY = y + dy;
        if (noweX >= 0 && noweX < szerokoscPlanszy && noweY >= 0 && noweY < wysokoscPlanszy) {
            this.x = noweX;
            this.y = noweY;
            energia--;
        }
    }

    public void zjedzMysz() {
        this.energia = MAX_ENERGIA;
    }

    @Override
    public boolean czyZywy() {
        return energia > 0;
    }

    @Override
    public char wyswietl() {
        return 'K'; // Zwraca znak reprezentujący kota
    }
}