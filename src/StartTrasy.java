public class StartTrasy extends Zdarzenie {
    private Sportowiec sportowiec;
    private Trasa trasa;

    public StartTrasy(Sportowiec s, Czas c, Trasa t) {
        sportowiec = s;
        trasa = t;
        this.setCzas(c);
    }

    @Override
    public void przetworz(KolejkaZdarzen kolejka) {
        Czas czasDotarcia = this.getCzas().dodaj(trasa.getCzas());
        if (sportowiec.getCzySlezdony()) {
            System.out.println(
                    "["
                            + this.getCzas()
                            + "]"
                            + " Sportowiec "
                            + sportowiec.getNumer()
                            + " rusza trasą nr "
                            + trasa.getNumer());
        }

        PrzybycieDoWezla noweZdarzenie = new PrzybycieDoWezla(sportowiec, czasDotarcia, trasa.getKoniec());
        kolejka.dodaj(noweZdarzenie);
    }

}
