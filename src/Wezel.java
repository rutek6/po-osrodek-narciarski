public class Wezel {
    private int nr;
    private int wysokosc;
    private int x;
    private int y;
    private boolean czySkomunikowany;
    private Trasa[] trasy = new Trasa[10];
    private Wyciag[] wyciagi = new Wyciag[10];
    private int indeksTras = 0;
    private int indeksWyciagow = 0;

    public Wezel(int nr, int wysokosc, int x, int y) {
        this.nr = nr;
        this.wysokosc = wysokosc;
        this.x = x;
        this.y = y;
        this.czySkomunikowany = false;
    }

    public Wezel(int nr, int wysokosc, int x, int y, boolean s) {
        this.nr = nr;
        this.wysokosc = wysokosc;
        this.x = x;
        this.y = y;
        this.czySkomunikowany = s;
    }

    public void dodajTrase(Trasa x) {
        if (indeksTras == trasy.length) {
            Trasa[] nowa = new Trasa[trasy.length * 2];
            for (int i = 0; i < trasy.length; i++)
                nowa[i] = trasy[i];
            trasy = nowa;
        }
        trasy[indeksTras] = x;
        indeksTras += 1;
    }

    public void dodajWyciag(Wyciag x) {
        if (indeksWyciagow == wyciagi.length) {
            Wyciag[] nowa = new Wyciag[trasy.length * 2];
            for (int i = 0; i < wyciagi.length; i++)
                nowa[i] = wyciagi[i];
            wyciagi = nowa;
        }
        wyciagi[indeksTras] = x;
        indeksWyciagow += 1;
    }

    public int getIndeksTras() {
        return indeksTras;
    }

    public int getIndeksWyciagow() {
        return indeksWyciagow;
    }

    public Trasa[] getTrasy() {
        return trasy.clone();
    }

    public Wyciag[] getWyciagi() {
        return wyciagi.clone();
    }

    public int getNumer() {
        return nr;
    }

    public String toString() {
        return "-----WEZEL----- \n" + "Numer: " + nr + "\nWysokosc: " + wysokosc + "\nx: " + x + " y: " + y
                + "\n Czy Skommunikowany: " + czySkomunikowany + "\n";
    }
}
