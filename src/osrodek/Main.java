package osrodek;

import java.util.Scanner;

import osoby.Sportowiec;
import infrastruktura.Wezel;
import infrastruktura.Wyciag;
import infrastruktura.Trasa;

public class Main {

    public static Osrodek parser() {
        Scanner sc = new Scanner(System.in);

        // Węzły
        int lWezlow = sc.nextInt();
        Wezel[] wezly = new Wezel[lWezlow];
        sc.nextLine();
        for (int i = 0; i < lWezlow; i++) {
            String linia = sc.nextLine().trim();
            String[] fragmenty = linia.split("\\s+");

            int h = Integer.parseInt(fragmenty[0]);
            int x = Integer.parseInt(fragmenty[1]);
            int y = Integer.parseInt(fragmenty[2]);
            boolean komunik = false;
            if (fragmenty.length > 3 && "s".equals(fragmenty[3]))
                komunik = true;
            Wezel nowyWezel = new Wezel(i, h, x, y, komunik);
            wezly[i] = nowyWezel;
        }

        // Wyciągi
        int lWyciagow = sc.nextInt();
        sc.nextLine();
        Wyciag[] wyciagi = new Wyciag[lWyciagow];
        for (int i = 0; i < lWyciagow; i++) {
            String linia = sc.nextLine().trim();
            String[] fragmenty = linia.split("\\s+");

            int nrStart = Integer.parseInt(fragmenty[0]);
            int nrKoniec = Integer.parseInt(fragmenty[1]);
            int odstep = Integer.parseInt(fragmenty[2]);
            int maxPas = Integer.parseInt(fragmenty[3]);
            int czas = Integer.parseInt(fragmenty[4]);

            Wyciag nowyWyciag = new Wyciag(i, wezly[nrStart], wezly[nrKoniec], new Czas(czas),
                    new Czas(odstep), maxPas);
            wyciagi[i] = nowyWyciag;
        }

        // Trasy
        int lTras = sc.nextInt();
        sc.nextLine();
        Trasa[] trasy = new Trasa[lTras];
        for (int i = 0; i < lTras; i++) {
            String linia = sc.nextLine().trim();
            String[] fragmenty = linia.split("\\s+");

            int nrStart = Integer.parseInt(fragmenty[0]);
            int nrKoniec = Integer.parseInt(fragmenty[1]);
            int trudnosc = Integer.parseInt(fragmenty[2]);
            int czasPrzejazdu = Integer.parseInt(fragmenty[3]);
            double bazowaAtrakcyjnosc = Double.parseDouble(fragmenty[4]);
            double odpornosc = Double.parseDouble(fragmenty[5]);

            Trasa nowaTrasa = new Trasa(
                    i,
                    wezly[nrStart],
                    wezly[nrKoniec],
                    new Czas(czasPrzejazdu),
                    trudnosc,
                    bazowaAtrakcyjnosc,
                    odpornosc);
            trasy[i] = nowaTrasa;
        }

        // Grupy Sportowców
        int ileSportowcow = 0;
        int lGrup = sc.nextInt();
        sc.nextLine();
        Sportowiec[] listaGrup = new Sportowiec[lGrup];
        int[] odstepy = new int[lGrup];
        for (int i = 0; i < lGrup; i++) {
            String linia = sc.nextLine().trim();
            String[] fragmenty = linia.split("\\s+");

            int lSportowcow = Integer.parseInt(fragmenty[0]);
            ileSportowcow += lSportowcow;
            int poziom = Integer.parseInt(fragmenty[1]);
            double spontanicznosc = Double.parseDouble(fragmenty[2]);
            boolean sledzenie = false;
            if (fragmenty.length > 3 && "s".equals(fragmenty[3]))
                sledzenie = true;

            linia = sc.nextLine().trim();
            fragmenty = linia.split("\\s+");
            double prefPoziom = Double.parseDouble(fragmenty[0]);
            double prefPWyrownanie = Double.parseDouble(fragmenty[1]);

            linia = sc.nextLine().trim();
            fragmenty = linia.split("\\s+");
            int nrStart = Integer.parseInt(fragmenty[0]);
            String godzinaStartu = fragmenty[1];
            int odstep = 0;
            if (lSportowcow > 1)
                odstep = Integer.parseInt(fragmenty[2]);
            Sportowiec nowaGrupa = new Sportowiec(
                    lSportowcow,
                    poziom,
                    wezly[nrStart],
                    new Czas(godzinaStartu),
                    spontanicznosc,
                    prefPoziom,
                    prefPWyrownanie,
                    sledzenie);
            listaGrup[i] = nowaGrupa;
            odstepy[i] = odstep;
        }

        // Sportowcy
        Sportowiec[] sportowcy = new Sportowiec[ileSportowcow];
        int indeksSportowcow = 0;
        for (int i = 0; i < lGrup; i++) {
            Sportowiec grupa = listaGrup[i];
            int lSportowcow = grupa.getNumer();
            int odstep = odstepy[i];
            int czasStartu = Czas.zamienNaSekundy(grupa.getCzas());
            int poziom = grupa.getPoziom();
            Wezel wezelStartowy = grupa.getStart();
            double spontanicznosc = grupa.getSpontanicznosc();
            double prefTrudnosc = grupa.getPrefTrudnosc();
            double prefNawierzchnia = grupa.getPrefNawierzchnia();
            boolean czySledzony = grupa.getCzySledzony();
            for (int j = 0; j < lSportowcow; j++) {
                Sportowiec nowySportowiec = new Sportowiec(indeksSportowcow, poziom, wezelStartowy,
                        new Czas(czasStartu + j * odstep), spontanicznosc, prefTrudnosc, prefNawierzchnia, czySledzony);
                sportowcy[indeksSportowcow] = nowySportowiec;
                indeksSportowcow++;
            }
        }

        sc.close();
        return new Osrodek(wezly, wyciagi, trasy, sportowcy);
    }

    public static void main(String[] args) {

        Osrodek osrodek = parser();
        osrodek.przeprowadzSymulacje();
    }
}
