public class StartWyciagu extends Zdarzenie {
    private Wyciag wyciag;

    public StartWyciagu(Wyciag w, Czas c) {
        wyciag = w;
        this.setCzas(c);
    }

    @Override
    public void przetworz(KolejkaZdarzen kolejka) {
        int maxPasazerow = wyciag.getMax();
        Czas odstep = wyciag.getOdstep();
        int i = 1;
        Sportowiec sportowiec = wyciag.getKolejka().wez();
        while (sportowiec != null && i <= maxPasazerow) {
            Czas czasDotarcia = wyciag.getCzas().dodaj(this.getCzas());
            PrzybycieDoWezla noweZdarzenie = new PrzybycieDoWezla(sportowiec, czasDotarcia, wyciag.getKoniec());
            kolejka.dodaj(noweZdarzenie);
            if (sportowiec.getCzySlezdony()) {
                System.out.println(
                        "["
                                + this.getCzas()
                                + "]"
                                + " Sportowiec "
                                + sportowiec.getNumer()
                                + " rusza wyciągiem nr "
                                + wyciag.getNumer());
            }
            i += 1;
            sportowiec = wyciag.getKolejka().wez();
        }
        System.out.println("[" + this.getCzas() + "]" + " Rusza wyciąg nr " + wyciag.getNumer());
        StartWyciagu kolejnyStart = new StartWyciagu(wyciag, this.getCzas().dodaj(odstep));
        kolejka.dodaj(kolejnyStart);
    }
}
