package org.example.objects.zwierzeta;

public class Mysz extends Zwierze {
    private static final int MAX_ENERGIA = 10; //maksymalna energia jaką może mieć mysz
    private boolean aktywna = false;

    public Mysz(int szerokoscPlanszy, int wysokoscPlanszy) {//inicjalizacja myszy z max energią, jest w norce
        super(szerokoscPlanszy, wysokoscPlanszy);
        this.energia = MAX_ENERGIA;
    }

    public Mysz(int szerokoscPlanszy, int wysokoscPlanszy, int x, int y) {//mysz po wyjsciu z norki
        super(szerokoscPlanszy, wysokoscPlanszy);
        this.x = x;
        this.y = y;
        this.energia = MAX_ENERGIA;
        this.aktywna = true;
    }

    public boolean czyAktywna() { return aktywna; }
    public void ustawAktywna(boolean aktywna) { this.aktywna = aktywna; }

    @Override
    public void poruszajSie(int szerokoscPlanszy, int wysokoscPlanszy) {
        int dx = (int) (Math.random() * 3) - 1;
        int dy = (int) (Math.random() * 3) - 1;
        int noweX = x + dx;
        int noweY = y + dy;
        if (noweX >= 0 && noweX < szerokoscPlanszy && noweY >= 0 && noweY < wysokoscPlanszy) {//sprawdzanie czy mysz nie wyjdzie poza plansze
            this.x = noweX;
            this.y = noweY;
            this.aktywna = true;
        }
        energia--;
    }

    @Override
    public boolean czyZywy() {//sprawdzenie czy mysz jest żywa
        return energia > 0;
    }

    public void zjedzSer() {//skutek zjedzenia sera
        this.energia = MAX_ENERGIA;
    }

    /*public boolean czyNieaktywna() {
        return energia <= 0;
    }*/

    @Override
    public void wyswietl() {
        System.out.print("M");
    }
}