package progetto.classes;

import progetto.enums.TipoCitta;

import java.util.ArrayList;

public class Citta {

    //region ATTRIBUTI
    private final String nome;
    private final TipoCitta grandezza;
    private final ArrayList<String> lavoriSuTerritorio;
    //endregion

    //region METODI
    public Citta(String nome, TipoCitta grandezza, ArrayList<String> lavoriSuTerritorio){
        this.nome=nome;
        this.grandezza=grandezza;
        this.lavoriSuTerritorio=lavoriSuTerritorio;
    }

    public String getNome() {
        return nome;
    }

    public TipoCitta getGrandezza() {
        return grandezza;
    }

    public ArrayList<String> getLavoriSuTerritorio() {
        return lavoriSuTerritorio;
    }
    //endregion

}
