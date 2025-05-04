package org.example.objects;

public class Kot extends ObiektPlanszy {
    private int energia;
    private final int maxEnergia = 20;

    public Kot(int szerokoscPlanszy, int wysokoscPlanszy) {
        super(szerokoscPlanszy, wysokoscPlanszy);
        this.energia = maxEnergia;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = Math.min(energia, maxEnergia);
    }

    public void poruszajSie(int szerokoscPlanszy, int wysokoscPlanszy) {
        int dx = (int) (Math.random() * 5) - 2; // -2, -1, 0, 1, 2
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
        this.energia = maxEnergia;
    }

    public boolean czyZywy() {
        return energia > 0;
    }

    @Override
    public void wyswietl() {
        System.out.print("K"); // Symbol kota na planszy
    }
}