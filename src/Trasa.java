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

    public double getAtrakcyjnosc(Sportowiec sportowiec) {
        double d;
        double pt = trudnosc;
        double pn = sportowiec.getPoziom();
        if (pt >= pn + 5) {
            d = 0;
        } else if (pn + 5 > pt && pt >= pn) {
            d = 1 - (pt - pn) / 5;
        } else {
            d = Math.max(0.2, 1 - (pn - pt) / 7);
        }

        double w = bazowaAtrakcyjnosc + (1 - bazowaAtrakcyjnosc) * Math.pow(odpornosc, this.getLiczbaPrzejazdow());

        double prefW = sportowiec.getPrefNawierzchnia();
        double prefD = sportowiec.getPrefTrudnosc();

        return prefD * d + prefW * w;
    }

    @Override
    public void przetworzStart(Sportowiec sportowiec, Czas obecnyCzas, KolejkaZdarzen kolejka) {
        StartTrasy noweZdarzenie = new StartTrasy(sportowiec, obecnyCzas, this);
        kolejka.dodaj(noweZdarzenie);
    }

}
