package org.example.objects;

public abstract class ObiektPlanszy {
    protected int x;
    protected int y;

    public ObiektPlanszy(int szerokoscPlanszy, int wysokoscPlanszy) {
        this.x = (int) (Math.random() * szerokoscPlanszy);  //losowanie współrzędnych
        this.y = (int) (Math.random() * wysokoscPlanszy);
    }

    public int getX() { return x; }//metoda zwracająca współrzędną x
    public int getY() { return y; }//metoda zwracająca współrzędną y
    public void setX(int x) { this.x = x; }//metoda ustawiająca konkretną współrzędną x
    public void setY(int y) { this.y = y; }//metoda ustawiająca konkretną współrzędną y

    public abstract void wyswietl();//metoda wyświetlająca symbole obiektów
}