package struktury;

import osoby.Sportowiec;

public class KolejkaWyciagu {
    private class Wezel {
        private Sportowiec wartosc;
        private Wezel nast;

        public Wezel(Sportowiec x) {
            this.wartosc = x;
            this.nast = null;
        }

        public Wezel getNast() {
            return nast;
        }

        public void setNast(Wezel x) {
            nast = x;
        }

        public Sportowiec getWartosc() {
            return wartosc;
        }
    }

    private Wezel start;
    private Wezel koniec;

    public KolejkaWyciagu() {
        start = null;
        koniec = null;
    }

    public Sportowiec wez() {
        if (start == null)
            return null;
        Sportowiec x = start.getWartosc();
        start = start.getNast();
        if (start == null) {
            koniec = null;
        }
        return x;
    }

    public void wypiszWszystkie() {
        Wezel x = start;
        int i = 0;
        while (x != null) {
            System.out.println("Nr w kolejce: " + i + ", Nr sportowca: " + x.getWartosc().getNumer());
            x = x.getNast();
            i += 1;
        }
    }

    public void dodaj(Sportowiec x) {
        Wezel nowy = new Wezel(x);
        if (start == null) {
            start = nowy;
            koniec = nowy;
            return;
        }
        koniec.setNast(nowy);
        koniec = nowy;

    }

}
