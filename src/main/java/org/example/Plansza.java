package org.example;

import org.example.objects.przedmioty.Norka;
import org.example.objects.przedmioty.ObiektPlanszy;
import org.example.objects.przedmioty.Ser;
import org.example.objects.zwierzeta.Kot;
import org.example.objects.zwierzeta.Mysz;
import org.example.objects.zwierzeta.Zwierze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plansza {
    private final int szerokosc;
    private final int wysokosc;
    private final List<Norka> norki;
    private final List<Mysz> myszy;
    private final List<Ser> sery;
    private final List<Kot> koty;
    private int liczbaZywychMyszy; // Liczba żywych myszy (w norach i na planszy)
    private final int poczatkowaLiczbaKotow;
    private int krokSymulacji = 0;
    private final Random random = new Random();

    public Plansza(int szerokosc, int wysokosc, int liczbaNorek, int liczbaKotow) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.norki = new ArrayList<>();
        this.myszy = new ArrayList<>();
        this.sery = new ArrayList<>();
        this.koty = new ArrayList<>();
        inicjalizujNorki(liczbaNorek);
        this.liczbaZywychMyszy = liczbaNorek; // Początkowo tyle żywych myszy, ile norek
        inicjalizujSery(liczbaNorek); // Początkowa liczba serów
        this.poczatkowaLiczbaKotow = liczbaKotow;
        inicjalizujKoty(liczbaKotow);
    }

    public int getSzerokosc() {
        return szerokosc;
    }

    public int getWysokosc() {
        return wysokosc;
    }

    public List<Norka> getNorki() {
        return norki;
    }

    public List<Mysz> getMyszy() {
        return myszy;
    }

    public List<Ser> getSery() {
        return sery;
    }

    public List<Kot> getKoty() {
        return koty;
    }

    public int getLiczbaZywychMyszy() {
        return liczbaZywychMyszy;
    }

    private void inicjalizujNorki(int liczbaNorek) {
        for (int i = 0; i < liczbaNorek; i++) {
            dodajLosowyObiekt(new Norka(szerokosc, wysokosc), norki, null, null, null);
        }
    }

    private void inicjalizujKoty(int liczbaKotow) {
        for (int i = 0; i < liczbaKotow; i++) {
            dodajLosowyObiekt(new Kot(szerokosc, wysokosc), norki, myszy, sery, koty);
        }
    }

    private void inicjalizujSery(int liczbaSerow) {
        for (int i = 0; i < liczbaSerow; i++) {
            dodajLosowyObiekt(new Ser(szerokosc, wysokosc), norki, myszy, sery, koty);
        }
    }

    private void dodajLosowyObiekt(ObiektPlanszy obiekt, List<? extends ObiektPlanszy>... lists) {
        int x, y;
        boolean zajete;
        do {
            x = random.nextInt(szerokosc);
            y = random.nextInt(wysokosc);
            obiekt.setX(x);
            obiekt.setY(y);
            zajete = false;
            for (List<? extends ObiektPlanszy> list : lists) {
                if (list != null) {
                    for (ObiektPlanszy element : list) {
                        if (element.getX() == x && element.getY() == y) {
                            zajete = true;
                            break;
                        }
                    }
                }
                if (zajete) break;
            }
        } while (zajete);

        if (obiekt instanceof Norka) {
            norki.add((Norka) obiekt);
        } else if (obiekt instanceof Ser) {
            sery.add((Ser) obiekt);
        } else if (obiekt instanceof Mysz) {
            myszy.add((Mysz) obiekt);
        } else if (obiekt instanceof Kot) {
            koty.add((Kot) obiekt);
        }
    }

    private boolean dodajLosowaMysz(Norka norka) {
        int norkaX = norka.getX();
        int norkaY = norka.getY();
        int prob = 0;
        boolean udaloSie = false;
        while (prob < 10 && !udaloSie) {
            int dx = random.nextInt(3) - 1;
            int dy = random.nextInt(3) - 1;
            if (dx == 0 && dy == 0) continue;

            int noweX = norkaX + dx;
            int noweY = norkaY + dy;

            if (noweX >= 0 && noweX < szerokosc && noweY >= 0 && noweY < wysokosc) {
                boolean zajete = false;
                for (Norka n : norki) {
                    if (n.getX() == noweX && n.getY() == noweY) zajete = true;
                }
                for (Mysz m : myszy) {
                    if (m.getX() == noweX && m.getY() == noweY) zajete = true;
                }
                for (Ser s : sery) {
                    if (s.getX() == noweX && s.getY() == noweY) zajete = true;
                }
                for (Kot c : koty) {
                    if (c.getX() == noweX && c.getY() == noweY) zajete = true;
                }
                if (!zajete) {
                    myszy.add(new Mysz(szerokosc, wysokosc, noweX, noweY));
                    udaloSie = true;
                }
            }
            prob++;
        }
        if (udaloSie) {
            return true;
        }
        return false;
    }

    private Ser stworzLosowySer() {
        Ser nowySer;
        boolean zajete;
        do {
            nowySer = new Ser(szerokosc, wysokosc);
            zajete = false;
            for (Norka n : norki) {
                if (n.getX() == nowySer.getX() && n.getY() == nowySer.getY()) zajete = true;
            }
            for (Mysz m : myszy) {
                if (m.getX() == nowySer.getX() && m.getY() == nowySer.getY()) zajete = true;
            }
            for (Ser s : sery) {
                if (s.getX() == nowySer.getX() && s.getY() == nowySer.getY()) zajete = true;
            }
            for (Kot c : koty) {
                if (c.getX() == nowySer.getX() && c.getY() == nowySer.getY()) zajete = true;
            }
        } while (zajete);
        return nowySer;
    }

    public void symulujKrok() {
        krokSymulacji++;
        boolean nowaMyszDodanaWKroku = false;

        if (krokSymulacji > 1) {
            if (!nowaMyszDodanaWKroku && random.nextDouble() < 0.5 && !norki.isEmpty() && liczbaZywychMyszy > 0 && myszy.size() < norki.size()) {
                Norka losowaNorka = norki.get(random.nextInt(norki.size()));
                if (dodajLosowaMysz(losowaNorka)) {
                    liczbaZywychMyszy--;
                    nowaMyszDodanaWKroku = true;
                }
            }

            for (Mysz mysz : myszy) {
                mysz.poruszajSie(szerokosc, wysokosc);
            }

            for (Kot kot : koty) {
                kot.poruszajSie(szerokosc, wysokosc);
            }

            List<Mysz> zjedzoneMyszy = new ArrayList<>();
            for (Kot kot : koty) {
                for (Mysz mysz : myszy) {
                    if (kot.getX() == mysz.getX() && kot.getY() == mysz.getY() && mysz.czyAktywna() && kot.czyZywy()) {
                        kot.zjedzMysz();
                        zjedzoneMyszy.add(mysz);
                        liczbaZywychMyszy--;
                        break;
                    }
                }
            }
            myszy.removeAll(zjedzoneMyszy);

            List<Ser> zjedzonySer = new ArrayList<>();
            for (Mysz mysz : myszy) {
                if (mysz.czyAktywna()) {
                    for (Ser ser : sery) {
                        if (mysz.getX() == ser.getX() && mysz.getY() == ser.getY()) {
                            mysz.zjedzSer();
                            zjedzonySer.add(ser);
                            break;
                        }
                    }
                }
            }
            sery.removeAll(zjedzonySer);
            for (int i = 0; i < zjedzonySer.size(); i++) {
                sery.add(stworzLosowySer());
            }

            myszy.removeIf(Mysz::czyNieaktywna);
            koty.removeIf(kot -> !kot.czyZywy());
        }
    }

    public void wyswietlPlansze() {
        char[][] mapa = new char[wysokosc][szerokosc];
        for (int i = 0; i < wysokosc; i++) {
            for (int j = 0; j < szerokosc; j++) {
                mapa[i][j] = '.';
            }
        }

        for (Norka norka : norki) {
            if (norka.getY() < wysokosc && norka.getX() < szerokosc) {
                mapa[norka.getY()][norka.getX()] = 'N';
            }
        }

        for (Mysz mysz : myszy) {
            if (mysz.getY() < wysokosc && mysz.getX() < szerokosc && mysz.czyAktywna()) {
                mapa[mysz.getY()][mysz.getX()] = 'M';
            }
        }

        for (Ser ser : sery) {
            if (ser.getY() < wysokosc && ser.getX() < szerokosc) {
                mapa[ser.getY()][ser.getX()] = 'S';
            }
        }

        for (Kot kot : koty) {
            if (kot.getY() < wysokosc && kot.getX() < szerokosc && kot.czyZywy()) {
                mapa[kot.getY()][kot.getX()] = 'K';
            }
        }

        for (int i = 0; i < wysokosc; i++) {
            for (int j = 0; j < szerokosc; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Krok symulacji: " + krokSymulacji);
        System.out.println("Liczba norek: " + norki.size());
        System.out.println("Liczba myszy na planszy: " + myszy.size());
        System.out.println("Liczba żywych myszy: " + liczbaZywychMyszy);
        System.out.println("Liczba sera: " + sery.size());
        System.out.println("Liczba kotów: " + koty.size());
        System.out.println("--------------------");
    }
}