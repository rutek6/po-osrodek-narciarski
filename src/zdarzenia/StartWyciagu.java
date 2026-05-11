package zdarzenia;

import infrastruktura.Wyciag;
import osoby.Sportowiec;
import osrodek.Czas;
import struktury.KolejkaZdarzen;

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

        for (int i = 0; i < maxPasazerow; i++) {
            Sportowiec sportowiec = wyciag.getKolejka().wez();

            if (sportowiec == null) {
                break;
            }

            Czas czasDotarcia = wyciag.getCzas().dodaj(this.getCzas());
            ZejscieZWyciagu noweZdarzenie = new ZejscieZWyciagu(sportowiec, czasDotarcia, wyciag.getKoniec());
            kolejka.dodaj(noweZdarzenie);
            wyciag.zwiekszLicznikPrzejazdow();
            if (sportowiec.getCzySlezdony()) {
                System.out.println(
                        this.getCzas()
                                + ":"
                                + " Sportowiec "
                                + sportowiec.getNumer()
                                + " rusza wyciągiem nr "
                                + wyciag.getNumer());
            }
        }
        if (this.getCzas().compareTo(new Czas(16, 0, 0)) < 0) {
            StartWyciagu kolejnyStart = new StartWyciagu(wyciag, this.getCzas().dodaj(odstep));
            kolejka.dodaj(kolejnyStart);
        }
    }
}
