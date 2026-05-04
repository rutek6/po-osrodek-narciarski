import java.util.Random;

public class Sportowiec {
    private int nr;
    private int poziom; // 0 - 10
    private Wezel start;
    private Czas czasStartu;
    private boolean czySledzony = false;
    private double spontanicznosc;
    private double prefTrudnosc;
    private double prefNawierzchnia;
    private final Random random = new Random();

    public Sportowiec(int nr,
            int poziom,
            Wezel start,
            Czas czasStartu,
            double spontanicznosc,
            double prefTrudnosc,
            double prefNawierzchnia,
            boolean s) {
        this.nr = nr;
        this.poziom = poziom;
        this.start = start;
        this.czasStartu = czasStartu;
        this.spontanicznosc = spontanicznosc;
        this.prefTrudnosc = prefTrudnosc;
        this.prefNawierzchnia = prefNawierzchnia;
        this.czySledzony = s;
    }

    public boolean czyLosowac() {
        return random.nextFloat() < spontanicznosc;
    }

    public int getNumer() {
        return nr;
    }

    public Czas getCzas() {
        return czasStartu;
    }

    public int getPoziom() {
        return poziom;
    }

    public Wezel getStart() {
        return start;
    }

    public double getSpontanicznosc() {
        return spontanicznosc;
    }

    public double getPrefTrudnosc() {
        return prefTrudnosc;
    }

    public double getPrefNawierzchnia() {
        return prefNawierzchnia;
    }

    public boolean getCzySlezdony() {
        return czySledzony;
    }

    public String toString() {
        return "-----SPORTOWIEC----- \n"
                + "Numer: "
                + this.getNumer()
                + "\nNr Start: "
                + this.getStart().getNumer()
                + "\nPozioim zaawansowania: "
                + poziom
                + "\nSpontanicznosc: "
                + spontanicznosc
                + "\nPreferencja Trudnosci: "
                + prefTrudnosc
                + "\nPreferencja Nawierzchni: "
                + prefNawierzchnia
                + "\nCzas startu: "
                + this.getCzas()
                + "\nCzy sledzony?: "
                + czySledzony
                + "\n";
    }
}
