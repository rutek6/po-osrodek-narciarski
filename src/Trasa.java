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

    @Override
    public Czas getCzasDotarcia(Czas obecnyCzas) {
        Czas wynik;
        wynik = obecnyCzas.dodaj(this.getCzas());
        return wynik;
    }

    @Override
    public void przetworzStart(KolejkaZdarzen kolejka, Czas obecnyCzas, Sportowiec sportowiec) {
        Czas czasDotarcia = obecnyCzas.dodaj(this.getCzas());
        PrzybycieDoWezla noweZdarzenie = new PrzybycieDoWezla(sportowiec, czasDotarcia, this.getKoniec());
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
