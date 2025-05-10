package org.example.objects.zwierzeta;

import org.example.objects.ObiektPlanszy;

public abstract class Zwierze extends ObiektPlanszy {
    protected int energia;

    public Zwierze(int szerokoscPlanszy, int wysokoscPlanszy) {
        super(szerokoscPlanszy, wysokoscPlanszy);
    }

    public int getEnergia() { return energia; }
    public void setEnergia(int energia) { this.energia = energia; }

    public abstract void poruszajSie(int szerokoscPlanszy, int wysokoscPlanszy);
    public abstract boolean czyZywy();
}