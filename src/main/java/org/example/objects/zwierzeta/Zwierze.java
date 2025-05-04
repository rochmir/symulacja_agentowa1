package org.example.objects.zwierzeta;

import org.example.objects.przedmioty.ObiektPlanszy;

public abstract class Zwierze extends ObiektPlanszy {
    protected int energia;

    public Zwierze(int szerokoscPlanszy, int wysokoscPlanszy) {
        super(szerokoscPlanszy, wysokoscPlanszy);
        this.energia = 0; // Domyślna energia, klasy potomne powinny ją ustawić
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public abstract void poruszajSie(int szerokoscPlanszy, int wysokoscPlanszy);
    public abstract boolean czyZywy();
}