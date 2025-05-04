package org.example.objects.przedmioty;

public class Ser extends ObiektPlanszy {

    public Ser(int szerokoscPlanszy, int wysokoscPlanszy) {
        super(szerokoscPlanszy, wysokoscPlanszy);
    }

    @Override
    public void wyswietl() {
        System.out.print("S"); // Symbol sera na planszy
    }
}