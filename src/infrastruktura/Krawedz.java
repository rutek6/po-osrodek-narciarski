package infrastruktura;

import osoby.Sportowiec;
import osrodek.Czas;
import struktury.KolejkaZdarzen;

public abstract class Krawedz {
    private Wezel poczatek;
    private Wezel koniec;
    private Czas czasPrzejazdu;
    private int liczbaPrzejazdow;
    private int numer;

    public Krawedz(Wezel p, Wezel k, Czas c, int nr) {
        poczatek = p;
        koniec = k;
        czasPrzejazdu = c;
        liczbaPrzejazdow = 0;
        numer = nr;
    }

    public void zwiekszLicznikPrzejazdow() {
        liczbaPrzejazdow += 1;
    }

    public int getNumer() {
        return numer;
    }

    public int getLiczbaPrzejazdow() {
        return liczbaPrzejazdow;
    }

    public Wezel getPoczatek() {
        return poczatek;
    }

    public Wezel getKoniec() {
        return koniec;
    }

    public Czas getCzas() {
        return czasPrzejazdu;
    }

    public abstract void przetworzStart(Sportowiec s, Czas c, KolejkaZdarzen k);
}
