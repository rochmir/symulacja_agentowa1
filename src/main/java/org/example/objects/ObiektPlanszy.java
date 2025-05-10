package org.example.objects;

public abstract class ObiektPlanszy {
    protected int x;
    protected int y;

    public ObiektPlanszy(int szerokoscPlanszy, int wysokoscPlanszy) {
        this.x = (int) (Math.random() * szerokoscPlanszy);
        this.y = (int) (Math.random() * wysokoscPlanszy);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public abstract void wyswietl();
}