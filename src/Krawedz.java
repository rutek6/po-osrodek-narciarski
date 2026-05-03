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

    public int getNumer() {
        return numer;
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
