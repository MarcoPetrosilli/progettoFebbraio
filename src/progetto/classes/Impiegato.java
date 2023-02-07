package progetto.classes;

import progetto.enums.TipoImpiegato;
import progetto.enums.TipoLavoro;

import java.util.ArrayList;
import java.util.Arrays;

public class Impiegato {

    //region ATTRIBUTI
    private final String ID;
    private final String nome;
    private final String cognome;
    private final TipoImpiegato tipo;
    private boolean abilitato;
    //endregion

    //region ATTRIBUTI STATIC
    public static ArrayList<TipoLavoro> lavoriGeometra = new ArrayList<>(Arrays.asList(TipoLavoro.ritocco, TipoLavoro.ristrutturazione));
    public static ArrayList<TipoLavoro> lavoriArchitetto = new ArrayList<>(Arrays.asList(TipoLavoro.ritocco, TipoLavoro.costruzione));
    public static ArrayList<TipoLavoro> lavoriIngegneri = new ArrayList<>(Arrays.asList(TipoLavoro.ristrutturazione, TipoLavoro.costruzione));
    //endregion

    //region METODI
    public Impiegato(String ID, String nome, String cognome, TipoImpiegato tipo){
        this.ID=ID;
        this.nome=nome;
        this.cognome=cognome;
        this.tipo=tipo;
        this.abilitato=true;
    }

    public String getID() {
        return ID;
    }

    public TipoImpiegato getTipo() {
        return tipo;
    }

    public boolean isAbilitato() {
        return abilitato;
    }

    public void setAbilitato(Lavoro[] lavori){
        for(Lavoro lavoro:lavori){
            if(lavoro.getImpiegatiAssegnati().contains(this.ID)){
                switch (this.tipo){
                    case geometra:
                        if(!(lavoriGeometra.contains(lavoro.getTipo())))
                            this.abilitato=false;
                        break;
                    case architetto:
                        if(!(lavoriArchitetto.contains(lavoro.getTipo())))
                            this.abilitato=false;
                        break;
                    case ingegnere:
                        if(!(lavoriIngegneri.contains(lavoro.getTipo())))
                            this.abilitato=false;
                        break;
                }
            }
        }
    }
    //endregion
}
