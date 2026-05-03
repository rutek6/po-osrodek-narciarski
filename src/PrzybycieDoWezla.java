public class PrzybycieDoWezla extends Zdarzenie {

    private Sportowiec sportowiec;
    private Wezel wezel;

    public PrzybycieDoWezla(Sportowiec s, Czas c, Wezel w) {
        sportowiec = s;
        this.setCzas(c);
        wezel = w;
    }

    private Krawedz wybierzDroge() {
        Trasa[] trasy = wezel.getTrasy();
        Wyciag[] wyciagi = wezel.getWyciagi();
        if (this.getCzas().zamienNaSekundy() % 2 == 0)
            return trasy[0];
        else
            return wyciagi[0];
    }

    @Override
    public void przetworz(KolejkaZdarzen kolejka) {
        System.out.println("Sportowiec " + sportowiec.getNumer() + "przybył do węzła nr " + wezel.getNumer());
        Krawedz wybrana = wybierzDroge();
        wybrana.przetworzStart(sportowiec, this.getCzas(), kolejka);
    }
}
