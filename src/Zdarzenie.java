public abstract class Zdarzenie {
    private Czas czas;

    public Czas getCzas() {
        return czas;
    }

    public void setCzas(Czas x) {
        czas = x;
    }

    public abstract void przetworz(KolejkaZdarzen kolejka);
}
