package osrodek;

public class Czas {
    private int godziny;
    private int minuty;
    private int sekundy;

    public Czas(int h, int m, int s) {
        godziny = h;
        minuty = m;
        sekundy = s;
    }

    public Czas(String str) {
        String[] czesci = str.split(":");
        int h = Integer.parseInt(czesci[0]);
        int m = Integer.parseInt(czesci[1]);
        int s = Integer.parseInt(czesci[2]);

        this(h, m, s);
    }

    public Czas(int s) {
        int h = s / 3600;
        s = s - 3600 * h;
        int m = s / 60;
        s = s - 60 * m;
        this(h, m, s);
    }

    public Czas dodaj(Czas x) {
        int sekundy_this = zamienNaSekundy(this);
        int sekundy_x = zamienNaSekundy(x);
        int wynik = sekundy_this + sekundy_x;
        return new Czas(wynik);
    }

    public int roznica(Czas x) {
        int sekundy_this = zamienNaSekundy(this);
        int sekundy_x = zamienNaSekundy(x);
        int wynik = sekundy_this - sekundy_x;
        if (wynik < 0)
            wynik = -wynik;
        return wynik;
    }

    public static Czas stringNaCzas(String str) {
        String[] czesci = str.split(":");
        int h = Integer.parseInt(czesci[0]);
        int m = Integer.parseInt(czesci[1]);
        int s = Integer.parseInt(czesci[2]);

        return new Czas(h, m, s);
    }

    public int compareTo(Czas x) {
        int sekundy_x = x.zamienNaSekundy();
        int sekundy_this = this.zamienNaSekundy();
        if (sekundy_this < sekundy_x) {
            return -1;
        } else if (sekundy_this == sekundy_x)
            return 0;
        else {
            return 1;
        }
    }

    public int getGodziny() {
        return godziny;
    }

    public int getMinuty() {
        return minuty;
    }

    public int getSekundy() {
        return sekundy;
    }

    public static int zamienNaSekundy(Czas x) {
        return 3600 * x.getGodziny() + 60 * x.getMinuty() + x.getSekundy();
    }

    public int zamienNaSekundy() {
        return 3600 * godziny + 60 * minuty + sekundy;
    }

    public String toString() {
        return String.format("%02d", godziny) + ":" + String.format("%02d", minuty) + ":"
                + String.format("%02d", sekundy);
    }
}
