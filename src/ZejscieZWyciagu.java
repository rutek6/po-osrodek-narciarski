public class ZejscieZWyciagu extends PrzybycieDoWezla {
    public ZejscieZWyciagu(Sportowiec s, Czas c, Wezel w) {
        super(s, c, w);
    }

    @Override
    public void przetworz(KolejkaZdarzen kolejka) {
        if (sportowiec.getCzySlezdony()) {
            System.out.println(
                    "[" + this.getCzas() + "]" + " Sportowiec " + sportowiec.getNumer()
                            + " zszedł z wyciągu i przybył do węzła "
                            + wezel.getNumer());
        }

        Krawedz wybrana;
        if (sportowiec.czyLosowac()) {
            wybrana = wybierzDrogeLosowo();
        } else
            wybrana = wybierzDroge();
        wybrana.przetworzStart(sportowiec, this.getCzas(), kolejka);

    }
}
