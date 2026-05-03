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

    @Override
    public void przetworzStart(KolejkaZdarzen kolejka, Czas obecnyCzas, Sportowiec sportowiec) {
        Czas czasDotarcia = this.getCzasDotarcia(obecnyCzas);
        oczekujacy.dodaj(sportowiec);

        PrzybycieDoWezla noweZdarzenie = new PrzybycieDoWezla();
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

}
