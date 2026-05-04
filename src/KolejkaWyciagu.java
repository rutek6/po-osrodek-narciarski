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

        public Wezel getPop() {
            return pop;
        }

        public void setNast(Wezel x) {
            nast = x;
        }

        public void setPop(Wezel x) {
            pop = x;
        }

        public Sportowiec getWartosc() {
            return wartosc;
        }
    }

    private Wyciag wyciag;
    private Wezel start;
    private Wezel koniec;

    public KolejkaWyciagu(Wyciag w) {
        wyciag = w;
        start = null;
        koniec = null;
    }

    public Sportowiec wez() {
        if (start == null)
            return null;
        Sportowiec x = start.getWartosc();
        start = start.getNast();
        if (start != null) {
            start.setPop(null);
        } else {
            koniec = null;
        }
        return x;
    }

    public void dodaj(Sportowiec x) {
        Wezel nowy = new Wezel(x);
        if (start == null) {
            start = nowy;
            koniec = nowy;
            return;
        }
        nowy.setPop(koniec);
        koniec.setNast(nowy);
        koniec = nowy;
    }

}
