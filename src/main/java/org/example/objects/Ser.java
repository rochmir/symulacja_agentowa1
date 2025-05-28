package org.example.objects;

public class Ser extends ObiektPlanszy {
    public Ser(int szerokoscPlanszy, int wysokoscPlanszy) {
        super(szerokoscPlanszy, wysokoscPlanszy); //dostęp do zmiennych klasy nadrzędnej
    }

    @Override
    public char wyswietl() {
        return 'S';//wyświetlenie symbolu sera
    }
}