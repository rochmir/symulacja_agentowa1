package org.example.objects;

public class Mysz extends ObiektPlanszy {
    private int energia;
    private final int maxEnergia = 20;
    private int krokiDoZnikniecia;
    private final int maxKrokiDoZnikniecia = 10;

    public Mysz(int szerokoscPlanszy, int wysokoscPlanszy) {
        super(szerokoscPlanszy, wysokoscPlanszy);
        this.energia = maxEnergia;
        this.krokiDoZnikniecia = maxKrokiDoZnikniecia;
    }

    // Nowy konstruktor pozwalający na ustawienie konkretnej pozycji
    public Mysz(int szerokoscPlanszy, int wysokoscPlanszy, int x, int y) {
        super(szerokoscPlanszy, wysokoscPlanszy);
        this.x = x;
        this.y = y;
        this.energia = maxEnergia;
        this.krokiDoZnikniecia = maxKrokiDoZnikniecia;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = Math.min(energia, maxEnergia);
    }

    public boolean czyAktywna() {
        return krokiDoZnikniecia > 0;
    }

    public boolean czyNieaktywna() {
        return !czyAktywna();
    }

    public void poruszajSie(int szerokoscPlanszy, int wysokoscPlanszy) {
        if (czyAktywna()) {
            int dx = (int) (Math.random() * 3) - 1; // -1, 0, 1
            int dy = (int) (Math.random() * 3) - 1;

            int noweX = x + dx;
            int noweY = y + dy;

            if (noweX >= 0 && noweX < szerokoscPlanszy && noweY >= 0 && noweY < wysokoscPlanszy) {
                this.x = noweX;
                this.y = noweY;
            }
            krokiDoZnikniecia--;
        }
    }

    public void zjedzSer() {
        this.energia = maxEnergia;
        this.krokiDoZnikniecia = maxKrokiDoZnikniecia; // Resetuje czas życia po zjedzeniu sera
    }

    @Override
    public void wyswietl() {
        System.out.print("M");
    }
}