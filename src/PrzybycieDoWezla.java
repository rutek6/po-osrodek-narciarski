import java.util.Random;

public class PrzybycieDoWezla extends Zdarzenie {

    private Sportowiec sportowiec;
    private Wezel wezel;

    public PrzybycieDoWezla(Sportowiec s, Czas c, Wezel w) {
        sportowiec = s;
        this.setCzas(c);
        wezel = w;
    }

    private Krawedz wybierzDroge() {
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

    @Override
    public void przetworz(KolejkaZdarzen kolejka) {
        if (sportowiec.getCzySlezdony()) {
            System.out.println(
                    "[" + this.getCzas() + "]" + " Sportowiec " + sportowiec.getNumer() + " przybył do węzła nr "
                            + wezel.getNumer());
        }
        Krawedz wybrana = wybierzDroge();
        wybrana.przetworzStart(sportowiec, this.getCzas(), kolejka);
    }
}
