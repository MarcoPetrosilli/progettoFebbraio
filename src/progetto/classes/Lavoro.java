package progetto.classes;

import progetto.enums.TipoLavoro;

import java.util.ArrayList;

public class Lavoro {
    private String ID;
    private TipoLavoro tipo;
    private int durata;
    private int importo;

    private ArrayList<String> impiegatiAssegnati;

    public Lavoro(String ID, TipoLavoro tipo, int durata, int importo,ArrayList<String> impiegatiAssegnati){
        this.ID=ID;
        this.tipo=tipo;
        this.durata=durata;
        this.importo=importo;
        this.impiegatiAssegnati=impiegatiAssegnati;
    }

    public String getID() {
        return ID;
    }

    public TipoLavoro getTipo() {
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
