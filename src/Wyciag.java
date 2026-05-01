public class Wyciag extends Krawedz {
    private Czas odstep;
    private int maxPasazerow;
    private Kolejka oczekujacy;

    public Wyciag(int nr, Wezel p, Wezel k, Czas c, Czas od, int max) {
        super(p, k, c, nr);
        odstep = od;
        maxPasazerow = max;
        oczekujacy = new Kolejka();
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
