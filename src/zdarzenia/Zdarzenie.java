package zdarzenia;

import osrodek.Czas;
import struktury.KolejkaZdarzen;

public abstract class Zdarzenie {
    private Czas czas;

    public Czas getCzas() {
        return czas;
    }

    public void setCzas(Czas x) {
        czas = x;
    }

    public String toString() {
        return "\nZdarzenie, Czas: " + czas;
    }

    public abstract void przetworz(KolejkaZdarzen kolejka);
}
