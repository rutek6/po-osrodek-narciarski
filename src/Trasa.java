public class Trasa extends Krawedz {
    private int trudnosc;
    private double bazowaAtrakcyjnosc;
    private double odpornosc;

    public Trasa(int nr, Wezel p, Wezel k, Czas c, int t, double bA, double o) {
        super(p, k, c, nr);
        trudnosc = t;
        bazowaAtrakcyjnosc = bA;
        odpornosc = o;
    }

    public String toString() {
        return "-----TRASA----- \n"
                + "Numer: "
                + this.getNumer()
                + "\nNr Start: "
                + this.getPoczatek().getNumer()
                + "\nNr Koniec: "
                + this.getKoniec().getNumer()
                + "\nTrudnosc: "
                + trudnosc
                + "\nBazowa atrakcyjnosc: "
                + bazowaAtrakcyjnosc
                + "\nOdpornosc: "
                + odpornosc
                + "\nCzas przejazdu: "
                + this.getCzas()
                + "\n";
    }

}
