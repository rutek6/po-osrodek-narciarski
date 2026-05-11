package infrastruktura;

import osoby.Sportowiec;
import osrodek.Czas;
import struktury.KolejkaWyciagu;
import struktury.KolejkaZdarzen;

public class Wyciag extends Krawedz {
    private Czas odstep;
    private int maxPasazerow;
    private KolejkaWyciagu oczekujacy;

    public Wyciag(int nr, Wezel p, Wezel k, Czas c, Czas od, int max) {
        super(p, k, c, nr);
        odstep = od;
        maxPasazerow = max;
        oczekujacy = new KolejkaWyciagu();
    }

    public int getMax() {
        return maxPasazerow;
    }

    public Czas getOdstep() {
        return odstep;
    }

    public KolejkaWyciagu getKolejka() {
        return oczekujacy;
    }

    public String toString() {
        return "-----WYCIAG----- \n"
                + "Numer: "
                + this.getNumer()
                + "\nNr Start: "
                + this.getPoczatek().getNumer()
                + "\nNr Koniec: "
                + this.getKoniec().getNumer()
                + "\nMax Pasażerów: "
                + maxPasazerow
                + "\nOdstep: "
                + odstep
                + "\nCzas przejazdu: "
                + this.getCzas()
                + "\n";
    }

    @Override
    public void przetworzStart(Sportowiec s, Czas c, KolejkaZdarzen k) {
        oczekujacy.dodaj(s);
        if (s.getCzySledzony()) {
            System.out.println(
                    c
                            + ":"
                            + " Sportowiec "
                            + s.getNumer()
                            + " staje w kolejce do wyciągu nr "
                            + this.getNumer());
        }
    }

}
