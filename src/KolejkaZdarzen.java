public class KolejkaZdarzen {
    private class Wezel {
        private Zdarzenie wartosc;
        private Wezel pop;
        private Wezel nast;

        public Wezel(Zdarzenie x) {
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

        public Zdarzenie getWartosc() {
            return wartosc;
        }

        Wezel(Wezel poprzedni, Zdarzenie x) {
            this.wartosc = x;
            this.pop = poprzedni;
            this.nast = null;
        }

        Wezel(Wezel poprzedni, Zdarzenie x, Wezel nastepny) {
            this.wartosc = x;
            this.pop = poprzedni;
            this.nast = nastepny;
            this.pop.setNast(this);
            this.nast.setPop(this);
        }

    }

    private Wezel start;
    private Wezel koniec;

    public KolejkaZdarzen() {
        start = null;
        koniec = null;
    }

    public void dodaj(Zdarzenie x) {
        Wezel poprzedni = koniec;
        while (x.getCzas().compareTo(poprzedni.getWartosc().getCzas()) <= 0 && poprzedni != null) {
            poprzedni = poprzedni.getPop();
        }
        Wezel nowyWezel = new Wezel(poprzedni, x, poprzedni.getNast());
        if (nowyWezel.getNast() == null)
            koniec = nowyWezel;
        return;
    }

    public Zdarzenie wez() {
        if (start == null)
            return null;
        Zdarzenie wynik = start.getWartosc();
        start = start.getNast();
        return wynik;
    }
}
