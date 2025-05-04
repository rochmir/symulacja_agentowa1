package org.example.objects.zwierzeta;

public class Mysz extends Zwierze {
    private final int maxEnergia = 20;
    private int krokiDoZnikniecia;
    private final int maxKrokiDoZnikniecia = 10;
    private boolean aktywna = true;

    public Mysz(int szerokoscPlanszy, int wysokoscPlanszy) {
        super(szerokoscPlanszy, wysokoscPlanszy);
        this.energia = maxEnergia;
        this.krokiDoZnikniecia = maxKrokiDoZnikniecia;
    }

    public Mysz(int szerokoscPlanszy, int wysokoscPlanszy, int x, int y) {
        super(szerokoscPlanszy, wysokoscPlanszy);
        this.x = x;
        this.y = y;
        this.energia = maxEnergia;
        this.krokiDoZnikniecia = maxKrokiDoZnikniecia;
    }

    public boolean czyAktywna() {
        return aktywna && krokiDoZnikniecia > 0;
    }

    public boolean czyNieaktywna() {
        return !czyAktywna();
    }

    @Override
    public void poruszajSie(int szerokoscPlanszy, int wysokoscPlanszy) {
        if (czyAktywna()) {
            int dx = (int) (Math.random() * 3) - 1;
            int dy = (int) (Math.random() * 3) - 1;

            int noweX = x + dx;
            int noweY = y + dy;

            if (noweX >= 0 && noweX < szerokoscPlanszy && noweY >= 0 && noweY < wysokoscPlanszy) {
                this.x = noweX;
                this.y = noweY;
            }
            krokiDoZnikniecia--;
            if (krokiDoZnikniecia <= 0) {
                aktywna = false;
            }
        }
    }

    public void zjedzSer() {
        this.energia = maxEnergia;
        this.krokiDoZnikniecia = maxKrokiDoZnikniecia;
        this.aktywna = true;
    }

    @Override
    public boolean czyZywy() {
        return aktywna;
    }

    @Override
    public void wyswietl() {
        System.out.print("M");
    }
}