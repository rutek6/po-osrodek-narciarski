import java.util.Scanner;

public class Main {

    public static void parser(Osrodek osrodek) {
        Scanner sc = new Scanner(System.in);

        // Węzły
        int lWezlow = sc.nextInt();
        osrodek.setLiczbaWezlow(lWezlow);
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
            osrodek.dodajWezel(nowyWezel);
        }

        // Wyciągi
        int lWyciagow = sc.nextInt();
        sc.nextLine();
        osrodek.setLiczbaWyciagow(lWyciagow);
        for (int i = 0; i < lWyciagow; i++) {
            String linia = sc.nextLine().trim();
            String[] fragmenty = linia.split("\\s+");

            int nrStart = Integer.parseInt(fragmenty[0]);
            int nrKoniec = Integer.parseInt(fragmenty[1]);
            int odstep = Integer.parseInt(fragmenty[2]);
            int maxPas = Integer.parseInt(fragmenty[3]);
            int czas = Integer.parseInt(fragmenty[4]);

            Wyciag nowyWyciag = new Wyciag(i, osrodek.getWezel(nrStart), osrodek.getWezel(nrKoniec), new Czas(czas),
                    new Czas(odstep), maxPas);
            osrodek.dodajWyciag(nowyWyciag);
        }

        // Trasy
        int lTras = sc.nextInt();
        sc.nextLine();
        osrodek.setLiczbaTras(lTras);
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
                    osrodek.getWezel(nrStart),
                    osrodek.getWezel(nrKoniec),
                    new Czas(czasPrzejazdu),
                    trudnosc,
                    bazowaAtrakcyjnosc,
                    odpornosc);
            osrodek.dodajTrasa(nowaTrasa);
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
                    osrodek.getWezel(nrStart),
                    new Czas(godzinaStartu),
                    spontanicznosc,
                    prefPoziom,
                    prefPWyrownanie,
                    sledzenie);
            listaGrup[i] = nowaGrupa;
            odstepy[i] = odstep;
        }
        osrodek.setLiczbaSportowcow(ileSportowcow);
        System.out.println("LSPORT: " + ileSportowcow);

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
            boolean czySledzony = grupa.getCzySlezdony();
            for (int j = 0; j < lSportowcow; j++) {
                Sportowiec nowySportowiec = new Sportowiec(indeksSportowcow, poziom, wezelStartowy,
                        new Czas(czasStartu + j * odstep), spontanicznosc, prefTrudnosc, prefNawierzchnia, czySledzony);
                osrodek.dodajSportowiec(nowySportowiec);
                indeksSportowcow++;
            }
        }
        sc.close();
        for (int i = 0; i < lWezlow; i++) {
            System.out.println(osrodek.getWezel(i));
        }
        for (int i = 0; i < lWyciagow; i++) {
            System.out.println(osrodek.getWyciag(i));
        }
        for (int i = 0; i < lTras; i++) {
            System.out.println(osrodek.getTrasa(i));
        }
        for (int i = 0; i < lTras; i++) {
            System.out.println(osrodek.getTrasa(i));
        }
        for (int i = 0; i < ileSportowcow; i++) {
            System.out.println(osrodek.getSportowiec(i));
        }
    }

    public static void main(String[] args) {

        Osrodek osrodek = new Osrodek();
        parser(osrodek);
        osrodek.przeprowadzSymulacje();
    }
}
