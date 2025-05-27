package org.example.objects;

public class Norka extends ObiektPlanszy {
    private boolean czyMyszWyszla = false;

    public Norka(int szerokoscPlanszy, int wysokoscPlanszy) {
        super(szerokoscPlanszy, wysokoscPlanszy);
    }

    public boolean czyMyszWyszla() {
        return czyMyszWyszla;
    }

    public void ustawMyszWyszla(boolean wyszla) {
        this.czyMyszWyszla = wyszla;
    }

    @Override
    public void wyswietl() {
        System.out.print("N");
    }
}