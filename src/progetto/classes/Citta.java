package progetto.classes;

import progetto.enums.TipoCitta;

import java.util.ArrayList;
import java.util.Arrays;

public class Citta {
    private String nome;
    private TipoCitta grandezza;
    private ArrayList<String> lavoriSuTerritorio;

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
}
