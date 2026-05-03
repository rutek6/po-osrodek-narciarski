public class WjazdWyciagiem extends Zdarzenie {
    public Sportowiec sportowiec;
    public Wyciag wyciag;

    public WjazdWyciagiem(Sportowiec s, Czas c, Wyciag w) {
        this.setCzas(c);
        sportowiec = s;
        wyciag = w;
    }

    @Override
    public void przetworz(KolejkaZdarzen kolejka) {
        return;
    }
}
