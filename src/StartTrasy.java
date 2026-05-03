public class StartTrasy extends Zdarzenie {
    private Sportowiec sportowiec;
    private Trasa trasa;
    private Wyciag wyciag;

    public StartTrasy(Sportowiec s, Czas c, Trasa t) {
        sportowiec = s;
        this.setCzas(c);
        trasa = t;
        wyciag = null;
    }

    public StartTrasy(Sportowiec s, Czas c, Wyciag w) {
        sportowiec = s;
        this.setCzas(c);
        trasa = null;
        wyciag = w;
    }

    @Override
    public void przetworz(KolejkaZdarzen kolejka) {
        if (wyciag != null) {
            Czas czasDotarcia = wyciag.getCzasDotarcia(this.getCzas());
            Wezel wezelDocelowy = wyciag.getKoniec();
            PrzybycieDoWezla noweZdarzenie = new PrzybycieDoWezla(sportowiec, czasDotarcia, wezelDocelowy);
            kolejka.dodaj(noweZdarzenie);
        } else if (trasa != null) {
            Czas czasDotarcia = trasa.getCzasDotarcia(this.getCzas());
            Wezel wezelDocelowy = trasa.getKoniec();
            PrzybycieDoWezla noweZdarzenie = new PrzybycieDoWezla(sportowiec, czasDotarcia, wezelDocelowy);
            kolejka.dodaj(noweZdarzenie);
        }
    }
}
