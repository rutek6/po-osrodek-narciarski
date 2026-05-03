public class PrzybycieDoWezla extends Zdarzenie {

    private Sportowiec sportowiec;
    private Wezel wezel;

    public PrzybycieDoWezla(Sportowiec s, Czas c, Wezel w) {
        sportowiec = s;
        this.setCzas(c);
        wezel = w;
    }

    private Krawedz wybierzDroge() {
        return new Trasa(1, null, null, null, 1, 1, 1);
    }

    @Override
    public void przetworz(KolejkaZdarzen kolejka) {
        Krawedz wybrana = wybierzDroge();
        Zdarzenie noweZdarzenie = new StartTrasy(sportowiec, this.getCzas(), wybrana);
        kolejka.dodaj(noweZdarzenie);
    }
}
