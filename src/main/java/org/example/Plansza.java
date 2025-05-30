package org.example;

import org.example.objects.Norka;
import org.example.objects.ObiektPlanszy;
import org.example.objects.Ser;
import org.example.objects.zwierzeta.Kot;
import org.example.objects.zwierzeta.Mysz;

import java.util.*;

public class Plansza {
    private final int szerokosc;
    private final int wysokosc;
    public List<Mysz> getMyszy() { return myszy; }
    public List<Ser> getSery() { return sery; }
    private final List<Norka> norki = new ArrayList<>();
    private final List<Mysz> myszy = new ArrayList<>();
    private final List<Ser> sery = new ArrayList<>();
    private final List<Kot> koty = new ArrayList<>();
    private int liczbaZywychMyszy;
    private final Random random = new Random();

    public Plansza(int szerokosc, int wysokosc, int liczbaNorek, int liczbaKotow, int liczbaSerow) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        inicjalizujNorki(liczbaNorek);
        inicjalizujSery(liczbaSerow);
        inicjalizujKoty(liczbaKotow);
        this.liczbaZywychMyszy = liczbaNorek;
    }

    private void inicjalizujNorki(int liczbaNorek) {
        for (int i = 0; i < liczbaNorek; i++) {
            Norka norka;
            do {
                norka = new Norka(szerokosc, wysokosc);
            } while (czyPoleZajete(norka.getX(), norka.getY()));
            norki.add(norka);
        }
    }

    private void inicjalizujSery(int liczbaSerow) {
        for (int i = 0; i < liczbaSerow; i++) {
            Ser ser;
            do {
                ser = new Ser(szerokosc, wysokosc);
            } while (czyPoleZajete(ser.getX(), ser.getY()));
            sery.add(ser);
        }
    }

    private void inicjalizujKoty(int liczbaKotow) {
        for (int i = 0; i < liczbaKotow; i++) {
            Kot kot;
            do {
                kot = new Kot(szerokosc, wysokosc);
            } while (czyPoleZajete(kot.getX(), kot.getY()));
            koty.add(kot);
        }
    }

    private boolean czyPoleZajete(int x, int y) {
        for (ObiektPlanszy o : norki) if (o.getX() == x && o.getY() == y) return true;
        for (ObiektPlanszy o : sery) if (o.getX() == x && o.getY() == y) return true;
        for (ObiektPlanszy o : myszy) if (o.getX() == x && o.getY() == y) return true;
        for (ObiektPlanszy o : koty) if (o.getX() == x && o.getY() == y) return true;
        return false;
    }

    public void symulujKrok() {
        // Dodawanie myszy z prawdopodobieństwem 0.5 przy każdej norki
        for (Norka norka : norki) {
            if (!norka.czyMyszWyszla() && random.nextDouble() < 0.5 && liczbaZywychMyszy > 0) {
                int[] dx = {-1, 0, 1, 0, -1, 1, -1, 1};
                int[] dy = {0, -1, 0, 1, -1, -1, 1, 1};
                List<int[]> wolne = new ArrayList<>();
                for (int i = 0; i < dx.length; i++) {
                    int nx = norka.getX() + dx[i];
                    int ny = norka.getY() + dy[i];
                    if (nx >= 0 && nx < szerokosc && ny >= 0 && ny < wysokosc && !czyPoleZajete(nx, ny)) {
                        wolne.add(new int[]{nx, ny});
                    }
                }
                if (!wolne.isEmpty()) {
                    int[] pos = wolne.get(random.nextInt(wolne.size()));
                    myszy.add(new Mysz(szerokosc, wysokosc, pos[0], pos[1]));
                    liczbaZywychMyszy--;
                    norka.ustawMyszWyszla(true); // oznacz, że z tej norki już wyszła mysz
                }
            }
        }

        // Ruch myszy
        for (Mysz mysz : new ArrayList<>(myszy)) {
            mysz.poruszajSie(szerokosc, wysokosc);
        }

        // Ruch kotów (nie mogą wejść na norkę)
        for (Kot kot : new ArrayList<>(koty)) {
           kot.poruszajSie(szerokosc, wysokosc);
        }

        // Zjadanie myszy przez koty
        List<Mysz> zjedzoneMyszy = new ArrayList<>();
        for (Kot kot : koty) {
            for (Mysz mysz : myszy) {
                if (kot.getX() == mysz.getX() && kot.getY() == mysz.getY() && mysz.czyAktywna() && kot.czyZywy()) {
                    kot.zjedzMysz();
                    zjedzoneMyszy.add(mysz);
                }
            }
        }
        myszy.removeAll(zjedzoneMyszy);

        // Zjadanie sera przez myszy i koty
        Set<Ser> zjedzonySer = new HashSet<>();
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
        for (Kot kot : koty) {
            for (Ser ser : sery) {
                if (kot.getX() == ser.getX() && kot.getY() == ser.getY()) {
                    zjedzonySer.add(ser);
                }
            }
        }
        sery.removeAll(zjedzonySer);
        for (int i = 0; i < zjedzonySer.size(); i++) {
            Ser nowySer;
            do {
                nowySer = new Ser(szerokosc, wysokosc);
            } while (czyPoleZajete(nowySer.getX(), nowySer.getY()));
            sery.add(nowySer);
        }

        // Usuwanie martwych myszy i kotów
        myszy.removeIf(m -> !m.czyZywy());
        koty.removeIf(k -> !k.czyZywy());
    }

    public void wyswietlPlansze() {
        char[][] mapa = new char[wysokosc][szerokosc];
        for (int i = 0; i < wysokosc; i++)
            for (int j = 0; j < szerokosc; j++)
                mapa[i][j] = '.';
        for (Norka n : norki) mapa[n.getY()][n.getX()] = n.wyswietl();
        for (Ser s : sery) mapa[s.getY()][s.getX()] = s.wyswietl();
        for (Mysz m : myszy) if (m.czyAktywna()) mapa[m.getY()][m.getX()] = m.wyswietl();
        for (Kot k : koty) if (k.czyZywy()) mapa[k.getY()][k.getX()] = k.wyswietl();
        for (int i = 0; i < wysokosc; i++) {
            for (int j = 0; j < szerokosc; j++)
                System.out.print(mapa[i][j] + " ");
            System.out.println();
        }
        System.out.println("Myszy na planszy: " + myszy.size());
        System.out.println("Myszy zywe (na planszy + w norce): " + (myszy.size() + liczbaZywychMyszy));
        System.out.println("Koty: " + koty.size());
        System.out.println("Sery: " + sery.size());
        System.out.println("Norki: " + norki.size());
    }
    public List<Norka> getNorki() { return norki;}
    public List<Kot> getKoty() { return koty; }
    public int getLiczbaZywychMyszy() { return liczbaZywychMyszy + myszy.size(); }
}