package progetto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Citta {
    private String nome;
    private String grandezza;
    private ArrayList<String> lavoriSuTerritorio;

    public static ArrayList<String> dimensioniPossibili = new ArrayList<String>(Arrays.asList("piccola","media","grande"));

    public Citta(String nome, String grandezza, ArrayList<String> lavoriSuTerritorio){
        this.nome=nome;
        this.grandezza=grandezza;
        this.lavoriSuTerritorio=lavoriSuTerritorio;
    }

    public String getNome() {
        return nome;
    }

    public String getGrandezza() {
        return grandezza;
    }

    public ArrayList<String> getLavoriSuTerritorio() {
        return lavoriSuTerritorio;
    }
}
