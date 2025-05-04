package org.example.objects.zwierzeta;

public class Mysz extends Zwierze {
    private boolean aktywna = false; // Domyślnie mysz nie jest aktywna

    public Mysz(int szerokoscPlanszy, int wysokoscPlanszy) {
        super(szerokoscPlanszy, wysokoscPlanszy);
        this.energia = 5; // Początkowa energia myszy
    }

    public Mysz(int szerokoscPlanszy, int wysokoscPlanszy, int x, int y) {
        super(szerokoscPlanszy, wysokoscPlanszy);
        this.x = x;
        this.y = y;
        this.aktywna = true; // Mysz stworzona poza norką jest aktywna
        this.energia = 5; // Początkowa energia myszy
    }

    public boolean czyAktywna() {
        return aktywna;
    }

    public void ustawAktywna(boolean aktywna) {
        this.aktywna = aktywna;
    }

    @Override
    public void poruszajSie(int szerokoscPlanszy, int wysokoscPlanszy) {
        int dx = (int) (Math.random() * 3) - 1;
        int dy = (int) (Math.random() * 3) - 1;
        int noweX = x + dx;
        int noweY = y + dy;

        if (noweX >= 0 && noweX < szerokoscPlanszy && noweY >= 0 && noweY < wysokoscPlanszy) {
            this.x = noweX;
            this.y = noweY;
            this.aktywna = true; // Mysz staje się aktywna po pierwszym ruchu
        }
        energia--;
    }

    @Override
    public boolean czyZywy() {
        return energia > 0;
    }

    public void zjedzSer() {
        this.energia += 10;
    }

    public boolean czyNieaktywna() {
        return energia <= 0;
    }

    @Override
    public void wyswietl() {
        System.out.print("M");
    }
}