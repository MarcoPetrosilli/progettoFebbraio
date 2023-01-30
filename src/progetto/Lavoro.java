package progetto;

import java.util.ArrayList;

public class Lavoro {
    private String ID;
    private String tipo;
    private int durata;
    private int importo;

    private ArrayList<String> impiegatiAssegnati;

    public Lavoro(String ID, String tipo, int durata, int importo,ArrayList<String> impiegatiAssegnati){
        this.ID=ID;
        this.tipo=tipo;
        this.durata=durata;
        this.importo=importo;
        this.impiegatiAssegnati=impiegatiAssegnati;
    }

    public String getID() {
        return ID;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDurata() {
        return durata;
    }

    public int getImporto() {
        return importo;
    }

    public ArrayList<String> getImpiegatiAssegnati() {
        return impiegatiAssegnati;
    }
}
