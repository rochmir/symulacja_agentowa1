package org.example.objects;

public class Ser extends ObiektPlanszy {
    public Ser(int szerokoscPlanszy, int wysokoscPlanszy) {
        super(szerokoscPlanszy, wysokoscPlanszy); //dostęp do zmiennych klasy nadrzędnej
    }

    @Override
    public void wyswietl() {
        System.out.print("S");//wyświetlenie symbolu sera
    }
}