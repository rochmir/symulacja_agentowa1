package org.example.objects;

public abstract class ObiektPlanszy {
    protected int x;
    protected int y;

    public ObiektPlanszy(int szerokoscPlanszy, int wysokoscPlanszy) {
        this.x = (int) (Math.random() * szerokoscPlanszy);  //[0-1] losowanie współrzędnych obiektow planszy
        this.y = (int) (Math.random() * wysokoscPlanszy);
    }

    public int getX() { return x; }//pobiera aktualną współrzędną x
    public int getY() { return y; }//pobiera aktualną współrzędną y

    public abstract char  wyswietl();//metoda wyświetlająca symbole obiektów
}