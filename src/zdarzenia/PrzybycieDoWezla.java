package zdarzenia;

import java.util.Random;

import infrastruktura.Krawedz;
import infrastruktura.Trasa;
import infrastruktura.Wezel;
import infrastruktura.Wyciag;
import osoby.Sportowiec;
import osrodek.Czas;
import struktury.KolejkaZdarzen;

public class PrzybycieDoWezla extends Zdarzenie {

    protected Sportowiec sportowiec;
    protected Wezel wezel;

    public PrzybycieDoWezla(Sportowiec s, Czas c, Wezel w) {
        sportowiec = s;
        this.setCzas(c);
        wezel = w;
    }

    protected Krawedz wybierzDrogeLosowo(Random generator) {
        Trasa[] trasy = wezel.getTrasy();
        Wyciag[] wyciagi = wezel.getWyciagi();

        int ileTras = wezel.getIndeksTras();
        int ileWyciagow = wezel.getIndeksWyciagow();

        int sumaKrawedzi = ileTras + ileWyciagow;
        if (sumaKrawedzi == 0) {
            return null;
        }

        int wylosowanyIndeks = generator.nextInt(sumaKrawedzi);

        if (wylosowanyIndeks < ileTras) {
            return trasy[wylosowanyIndeks];
        } else {
            return wyciagi[wylosowanyIndeks - ileTras];
        }
    }

    protected Krawedz wybierzDroge() {
        Trasa[] trasy = wezel.getTrasy();
        Wyciag[] wyciagi = wezel.getWyciagi();

        Trasa wybrana = null;
        Wyciag posredni = null;
        double amax = 0;
        for (int i = 0; i < trasy.length; i++) {
            if (trasy[i] == null)
                break;
            double a = trasy[i].getAtrakcyjnosc(sportowiec);
            if (a > amax) {
                wybrana = trasy[i];
                amax = a;
            }
        }
        for (int j = 0; j < wyciagi.length; j++) {
            if (wyciagi[j] == null)
                break;
            Trasa[] trasyNaGorze = wyciagi[j].getKoniec().getTrasy();
            for (int i = 0; i < trasyNaGorze.length; i++) {
                if (trasyNaGorze[i] == null)
                    break;
                double a = trasyNaGorze[i].getAtrakcyjnosc(sportowiec);
                if (a > amax) {
                    wybrana = trasyNaGorze[i];
                    posredni = wyciagi[j];
                    amax = a;
                }
            }
        }
        if (posredni != null) {
            return posredni;
        }
        return wybrana;
    }

    @Override
    public void przetworz(KolejkaZdarzen kolejka) {
        if (sportowiec.getCzySlezdony()) {
            System.out.println(
                    this.getCzas() + ":" + " Sportowiec " + sportowiec.getNumer() + " przybył do węzła nr "
                            + wezel.getNumer());
        }

        Krawedz wybrana;
        if (sportowiec.czyLosowac()) {
            wybrana = wybierzDrogeLosowo(sportowiec.getGenerator());
        } else
            wybrana = wybierzDroge();
        if (this.getCzas().compareTo(new Czas(15, 0, 0)) < 0)
            wybrana.przetworzStart(sportowiec, this.getCzas(), kolejka);

    }
}
