import java.util.Random;

public class PrzybycieDoWezla extends Zdarzenie {

    protected Sportowiec sportowiec;
    protected Wezel wezel;

    public PrzybycieDoWezla(Sportowiec s, Czas c, Wezel w) {
        sportowiec = s;
        this.setCzas(c);
        wezel = w;
    }

    protected Krawedz wybierzDrogeLosowo() {
        Random generator = new Random();
        Trasa[] trasy = wezel.getTrasy();
        Wyciag[] wyciagi = wezel.getWyciagi();

        int ileTras = wezel.getIndeksTras();
        int ileWyciagow = wezel.getIndeksWyciagow();

        boolean maTrasy = ileTras > 0;
        boolean maWyciagi = ileWyciagow > 0;

        // 1. Jeśli są i trasy, i wyciągi - losujemy pół na pół
        if (maTrasy && maWyciagi) {
            if (generator.nextInt(150) % 2 == 0) {
                return trasy[generator.nextInt(ileTras)];
            } else {
                return wyciagi[generator.nextInt(ileWyciagow)];
            }
        }
        // 2. Jeśli są tylko trasy (np. szczyt góry) - jedziemy trasą
        else if (maTrasy) {
            return trasy[generator.nextInt(ileTras)];
        }
        // 3. Jeśli są tylko wyciągi (np. sam dół góry) - jedziemy wyciągiem
        else if (maWyciagi) {
            return wyciagi[generator.nextInt(ileWyciagow)];
        }

        // 4. Martwy punkt (nie powinno wystąpić w poprawnym grafie ośrodka)
        return null;
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
                    "[" + this.getCzas() + "]" + " Sportowiec " + sportowiec.getNumer() + " przybył do węzła nr "
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
