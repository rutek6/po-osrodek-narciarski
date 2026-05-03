public class StartWyciagu extends Zdarzenie {
    private Wyciag wyciag;
    private Czas obecnyCzas;

    public StartWyciagu(Wyciag w, Czas c) {
        wyciag = w;
        obecnyCzas = c;
    }

    @Override
    public void przetworz(KolejkaZdarzen kolejka) {
        int maxPasazerow = wyciag.getMax();
        Czas odstep = wyciag.getOdstep();
        int i = 1;
        Sportowiec sportowiec = wyciag.getKolejka().wez();
        while (sportowiec != null && i <= maxPasazerow) {
            Czas czasDotarcia = wyciag.getCzas().dodaj(obecnyCzas);
            PrzybycieDoWezla noweZdarzenie = new PrzybycieDoWezla(sportowiec, czasDotarcia, wyciag.getKoniec());
            kolejka.dodaj(noweZdarzenie);
            sportowiec = wyciag.getKolejka().wez();
        }
        StartWyciagu kolejnyStart = new StartWyciagu(wyciag, obecnyCzas.dodaj(odstep));
        kolejka.dodaj(kolejnyStart);
    }
}
