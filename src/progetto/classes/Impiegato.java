package progetto.classes;

import java.util.ArrayList;
import java.util.Arrays;

public class Impiegato {
    private String ID;
    private String nome;
    private String cognome;
    private String tipo;
    private boolean abilitato;
    public static ArrayList<String> lavoriGeometra = new ArrayList<String>(Arrays.asList("ritocco","ristrutturazione"));
    public static ArrayList<String> lavoriArchitetto = new ArrayList<String>(Arrays.asList("ritocco","costruzione"));
    public static ArrayList<String> lavoriIngegneri = new ArrayList<String>(Arrays.asList("ristrutturazione","costruzione"));

    public Impiegato(String ID, String nome, String cognome, String tipo){
        this.ID=ID;
        this.nome=nome;
        this.cognome=cognome;
        this.tipo=tipo;
        this.abilitato=true; //Da determinare poi quando utilizzare il settaAbilitato
    }

    public String getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isAbilitato() {
        return abilitato;
    }

    public void setAbilitato(Lavoro[] lavori){
        for(Lavoro lavoro:lavori){
            if(lavoro.getImpiegatiAssegnati().contains(this.ID)){
                switch (this.tipo){
                    case "geometra":
                        if(!(lavoriGeometra.contains(lavoro.getTipo())))
                            this.abilitato=false;
                        break;
                    case "architetto":
                        if(!(lavoriArchitetto.contains(lavoro.getTipo())))
                            this.abilitato=false;
                        break;
                    case "ingegnere":
                        if(!(lavoriIngegneri.contains(lavoro.getTipo())))
                            this.abilitato=false;
                        break;
                }
            }
        }
    }
}
