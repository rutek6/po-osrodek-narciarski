package osrodek;

import infrastruktura.*;
import osoby.Sportowiec;
import struktury.KolejkaZdarzen;
import zdarzenia.*;

public class Osrodek {
    private Wezel[] wezly;
    private Trasa[] trasy;
    private Wyciag[] wyciagi;
    private Sportowiec[] sportowcy;

    private KolejkaZdarzen kolejka = new KolejkaZdarzen();
    private static final Czas CZAS_ZAMKNIECIA = new Czas(16, 0, 0);

    public Osrodek(Wezel[] wezly, Wyciag[] wyciagi, Trasa[] trasy, Sportowiec[] sportowcy) {
        this.wezly = wezly;

        // Podpinanie wyciągów
        this.wyciagi = wyciagi;
        for (Wyciag w : wyciagi) {
            w.getPoczatek().dodajWyciag(w);
            StartWyciagu noweZdarzenie = new StartWyciagu(w, new Czas(9, 0, 0));
            kolejka.dodaj(noweZdarzenie);
        }

        // Podpinanie tras
        this.trasy = trasy;
        for (Trasa t : trasy) {
            t.getPoczatek().dodajTrase(t);
        }

        // Podpinanie sportowców
        this.sportowcy = sportowcy;
        for (Sportowiec s : sportowcy) {
            PrzybycieDoWezla noweZdarzenie = new PrzybycieDoWezla(s, s.getCzas(), s.getStart());
            kolejka.dodaj(noweZdarzenie);
        }
    }

    public Wezel getWezel(int numer) {
        return wezly[numer];
    }

    public Wyciag getWyciag(int numer) {
        return wyciagi[numer];
    }

    public Trasa getTrasa(int numer) {
        return trasy[numer];
    }

    public Sportowiec getSportowiec(int numer) {
        return sportowcy[numer];
    }

    public void przeprowadzSymulacje() {
        while (!kolejka.czyPusta()) {
            Zdarzenie x = kolejka.wez();
            if (x.getCzas().compareTo(CZAS_ZAMKNIECIA) >= 0) {
                break;
            }
            x.przetworz(kolejka);
        }
        for (int i = 0; i < trasy.length; i++) {
            System.out.println("Trasa nr: " + i + ", liczba przejazdów: " + trasy[i].getLiczbaPrzejazdow());
        }

        for (int i = 0; i < wyciagi.length; i++) {
            System.out.println("Wyciag nr: " + i + ", liczba przejazdów: " + wyciagi[i].getLiczbaPrzejazdow());
        }
    }
}
