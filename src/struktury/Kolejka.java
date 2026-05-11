package struktury;

import zdarzenia.Zdarzenie;

public interface Kolejka {
    public void dodaj(Zdarzenie x);

    public Zdarzenie wez();

    public boolean czyPusta();
}
