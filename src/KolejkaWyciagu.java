public class KolejkaWyciagu {
    private class Wezel {
        private Sportowiec wartosc;
        private Wezel pop;
        private Wezel nast;

        public Wezel(Sportowiec x) {
            this.wartosc = x;
            this.pop = null;
            this.nast = null;
        }

        public Wezel getNast() {
            return nast;
        }

        public Sportowiec getWartosc() {
            return wartosc;
        }

        Wezel(Wezel poprzedni, Sportowiec x) {
            this.wartosc = x;
            this.pop = poprzedni;
            this.nast = null;
        }

    }

    private Wezel start;
    private Wezel koniec;

    public KolejkaWyciagu() {
        start = null;
        koniec = null;
    }

    public void dodaj(Sportowiec x) {
        Wezel nowyWezel = new Wezel(koniec, x);
        koniec = nowyWezel;
    }

    public Sportowiec wez() {
        Sportowiec wynik = start.getWartosc();
        start = start.getNast();
        return wynik;
    }

}
