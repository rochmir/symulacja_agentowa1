package org.example.objects.przedmioty;

public class Norka extends ObiektPlanszy {

    public Norka(int szerokoscPlanszy, int wysokoscPlanszy) {
        super(szerokoscPlanszy, wysokoscPlanszy);
    }

    @Override
    public void wyswietl() {
        System.out.print("N");
    }
}