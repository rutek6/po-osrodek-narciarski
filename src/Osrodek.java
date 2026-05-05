public class Osrodek {
    private Wezel[] wezly;
    private int indeksWezlow = 0;
    private Trasa[] trasy;
    private int indeksTras = 0;
    private Wyciag[] wyciagi;
    private int indeksWyciagow = 0;
    private Sportowiec[] sportowcy;
    private int indeksSportowcow = 0;
    private int liczbaWezlow = 0;
    private int liczbaTras = 0;
    private int liczbaWyciagow = 0;
    private int liczbaSportowcow = 0;
    private KolejkaZdarzen kolejka = new KolejkaZdarzen();

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

    public void setLiczbaWezlow(int x) {
        liczbaWezlow = x;
        if (wezly == null)
            wezly = new Wezel[liczbaWezlow];
    }

    public void setLiczbaTras(int x) {
        liczbaTras = x;
        if (trasy == null)
            trasy = new Trasa[liczbaTras];
    }

    public void setLiczbaWyciagow(int x) {
        liczbaWyciagow = x;
        if (wyciagi == null)
            wyciagi = new Wyciag[liczbaWyciagow];
    }

    public void setLiczbaSportowcow(int x) {
        liczbaSportowcow = x;
        if (sportowcy == null)
            sportowcy = new Sportowiec[liczbaSportowcow];
    }

    public void dodajWezel(Wezel x) {
        if (wezly == null)
            return;
        wezly[indeksWezlow] = x;
        indeksWezlow++;
    }

    public void dodajTrasa(Trasa x) {
        if (trasy == null)
            return;
        trasy[indeksTras] = x;
        indeksTras++;
        x.getPoczatek().dodajTrase(x);
        Trasa y = x.getPoczatek().getTrasy()[0];
        System.out.println(y);

    }

    public void dodajWyciag(Wyciag x) {
        if (wyciagi == null)
            return;
        wyciagi[indeksWyciagow] = x;
        indeksWyciagow++;
        x.getPoczatek().dodajWyciag(x);
        Wyciag y = x.getPoczatek().getWyciagi()[0];
        System.out.println(y);
        StartWyciagu noweZdarzenie = new StartWyciagu(x, new Czas(9, 0, 0));
        kolejka.dodaj(noweZdarzenie);
    }

    public void dodajSportowiec(Sportowiec x) {
        if (sportowcy == null)
            return;
        sportowcy[indeksSportowcow] = x;
        indeksSportowcow++;
        PrzybycieDoWezla noweZdarzenie = new PrzybycieDoWezla(x, x.getCzas(), x.getStart());
        kolejka.dodaj(noweZdarzenie);
    }

    public void przeprowadzSymulacje() {
        Zdarzenie x = kolejka.wez();
        while (x != null && x.getCzas().compareTo(new Czas(14, 59, 59)) <= 0) {
            // while (x != null) {
            x.przetworz(kolejka);
            // System.out.println(x);
            x = kolejka.wez();
        }
        for (int i = 0; i < indeksTras; i++) {
            System.out.println("Trasa nr: " + i + ", l. przejazdów: " + trasy[i].getLiczbaPrzejazdow());
        }

        for (int i = 0; i < indeksWyciagow; i++) {
            System.out.println("Wyciag nr: " + i + ", l. przejazdów: " + wyciagi[i].getLiczbaPrzejazdow());
        }
    }
}
