package zdarzenia;

import infrastruktura.Krawedz;
import infrastruktura.Wezel;
import osoby.Sportowiec;
import osrodek.Czas;
import struktury.KolejkaZdarzen;

public class ZejscieZWyciagu extends PrzybycieDoWezla {
    public ZejscieZWyciagu(Sportowiec s, Czas c, Wezel w) {
        super(s, c, w);
    }

    @Override
    public void przetworz(KolejkaZdarzen kolejka) {
        if (sportowiec.getCzySledzony()) {
            System.out.println(
                    this.getCzas() + ":" + " Sportowiec " + sportowiec.getNumer()
                            + " zszedł z wyciągu i przybył do węzła "
                            + wezel.getNumer());
        }

        Krawedz wybrana;
        if (sportowiec.czyLosowac()) {
            wybrana = wybierzDrogeLosowo();
        } else
            wybrana = wybierzDroge();
        if (this.getCzas().compareTo(new Czas(15, 0, 0)) < 0)
            wybrana.przetworzStart(sportowiec, this.getCzas(), kolejka);

    }
}
