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
        // System.out.println("KOLEJKA PRZED PETLA: ");
        // wyciag.getKolejka().wypiszWszystkie();

        for (int i = 0; i < maxPasazerow; i++) {
            Sportowiec sportowiec = wyciag.getKolejka().wez();

            if (sportowiec == null) {
                // System.out.println("BREAK");
                break;
            }
            // System.out.println("SPORTOWIEC W PĘTLI: " + sportowiec.getNumer());
            // System.out.println("i = " + i);
            Czas czasDotarcia = wyciag.getCzas().dodaj(this.getCzas());
            PrzybycieDoWezla noweZdarzenie = new PrzybycieDoWezla(sportowiec, czasDotarcia, wyciag.getKoniec());
            kolejka.dodaj(noweZdarzenie);
            wyciag.zwiekszLicznikPrzejazdow();
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
        }
        // System.out.println("[" + this.getCzas() + "]" + " Rusza wyciąg nr " +
        // wyciag.getNumer());
        StartWyciagu kolejnyStart = new StartWyciagu(wyciag, this.getCzas().dodaj(odstep));
        kolejka.dodaj(kolejnyStart);
    }
}
