package progetto.Tasks;

import progetto.Classes.Citta;
import progetto.Classes.Impiegato;
import progetto.Classes.Lavoro;

import java.util.ArrayList;

public class Task2 {

    ////////////////////////////////////////////////// PUNTO 1

    public void eseguiTask(int[] pqr, Lavoro[] lavori, Citta[] citta, Impiegato[] impiegati){
        if(this.controllo(pqr,lavori,citta,impiegati))
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    public boolean controllo(int[] pqr,Lavoro[] lavori,Citta[] citta,Impiegato[] impiegati){
        return (this.controlla1(pqr,lavori) && this.controlla2(pqr,lavori,impiegati,citta) && this.controlla3(citta,lavori) && this.controlla4(impiegati,lavori));
    }

    ////////////////////////////////////////////////// PUNTO 1

    public boolean controlla1(int[] pqr,Lavoro[] lavori){
        int counter=0;
        for(Lavoro lavoro:lavori){
            if(lavoro.getDurata()>pqr[0]){
                counter++;
                if(counter>pqr[1]) return false;
            }
        }
        return true;
    }

    ////////////////////////////////////////////////// PUNTO 2

    public boolean controlla2(int[] pqr,Lavoro[] lavori,Impiegato[] impiegati,Citta[] citta){
        int counterCittaMaggiori=0;
        int counterCittaMinori=0;
        ArrayList<Integer> totCostoInPiuCitta = new ArrayList<>();
        ArrayList<Lavoro> lavoriImpiegato= new ArrayList<>();

        for(Impiegato impiegato:impiegati){
            //Calcola costo totale in tutte le citta che coinvolgono impiegato
            this.popolaTotCostoInPiuCitta(citta,lavori,impiegato,lavoriImpiegato,totCostoInPiuCitta);
            //Controlla condizione
            for(Integer sum : totCostoInPiuCitta){
                if(sum>pqr[2]){
                    counterCittaMaggiori++;
                    if(counterCittaMaggiori>1) return false;
                }
                if(sum<pqr[2]){
                    counterCittaMinori++;
                    if(counterCittaMinori==totCostoInPiuCitta.size()) return false;
                }
            }
            totCostoInPiuCitta.clear();
            counterCittaMaggiori=0;
            counterCittaMinori=0;
        }
        return true;
    }

    public void trovaLavoriImpiegato(Lavoro[] lavori,Citta elemento,Impiegato impiegato,ArrayList<Lavoro> lavoriImpiegato){
        for(Lavoro lavoro:lavori){
            if(elemento.getLavoriSuTerritorio().contains(lavoro.getID()) && lavoro.getImpiegatiAssegnati().contains(impiegato.getID()))
                lavoriImpiegato.add(lavoro);
        }
    }

    public int calcolaSommaLavori(ArrayList<Lavoro> lavoriImpiegato){
        int sommaCostoLavori=0;
        for(Lavoro lavoro:lavoriImpiegato){
            sommaCostoLavori+=lavoro.getImporto();
        }
        return sommaCostoLavori;
    }

    public void popolaTotCostoInPiuCitta(Citta[] citta,Lavoro[] lavori,Impiegato impiegato,ArrayList<Lavoro> lavoriImpiegato,ArrayList<Integer> totCostoInPiuCitta){
        int sommaCostoLavori;
        for(Citta elemento:citta){
            //Trova lavori di Impiegato in Citta
            this.trovaLavoriImpiegato(lavori,elemento,impiegato,lavoriImpiegato);
            //Calcola valore tot lavori in Citta e se diversa da 0 aggiungi
            sommaCostoLavori=this.calcolaSommaLavori(lavoriImpiegato);
            if(sommaCostoLavori!=0)
                totCostoInPiuCitta.add(sommaCostoLavori);
            lavoriImpiegato.clear();
        }
    }

    ////////////////////////////////////////////////// PUNTO 3

    public boolean controlla3(Citta[] citta,Lavoro[] lavori){
        int ristrutturazione = 0;
        int ritocco = 0;
        int costruzione = 0;
        ArrayList<Lavoro> lavoriInCitta = new ArrayList<>();

        for(Citta elemento:citta){
            //popolo vettore con lavori della citta
            this.trovaLavoriInCitta(lavori,elemento,lavoriInCitta);
            //controllo tipologie
            for(Lavoro lavoro:lavoriInCitta){
                switch (lavoro.getTipo()) {
                    case "ristrutturazione" -> ristrutturazione++;
                    case "ritocco" -> ritocco++;
                    case "costruzione" -> costruzione++;
                }
                if(ristrutturazione>1 || ritocco>1 || costruzione>1) return false;
            }
            ristrutturazione = 0;
            ritocco = 0;
            costruzione = 0;
            lavoriInCitta.clear();
        }
        return true;
    }

    public void trovaLavoriInCitta(Lavoro[] lavori, Citta elemento, ArrayList<Lavoro> lavoriInCitta){
        for(Lavoro lavoro:lavori){
            if(elemento.getLavoriSuTerritorio().contains(lavoro.getID()))
                lavoriInCitta.add(lavoro);
        }
    }

    ////////////////////////////////////////////////// PUNTO 4

    public boolean controlla4(Impiegato[] impiegati,Lavoro[] lavori){
        ArrayList<Impiegato> impiegatiLavoro = new ArrayList<>();
        int ingegnere = 0;
        int architetto = 0;
        int geometra = 0;

        for(Lavoro lavoro:lavori){
            //popolo vettore con impiegati del lavoro
            this.trovaImpiegatiLavoro(impiegati,lavoro,impiegatiLavoro);
            //controllo tipologie
            for(Impiegato impiegato:impiegatiLavoro){
                switch (impiegato.getTipo()) {
                    case "ingegnere" -> ingegnere++;
                    case "architetto" -> architetto++;
                    case "geometra" -> geometra++;
                }
                if(ingegnere>1 || architetto>1 || geometra>1) return false;
            }
            ingegnere = 0;
            architetto = 0;
            geometra = 0;
            impiegatiLavoro.clear();
        }
        return true;
    }

    public void trovaImpiegatiLavoro(Impiegato[] impiegati, Lavoro lavoro, ArrayList<Impiegato> impiegatiLavoro){
        for(Impiegato impiegato:impiegati){
            if(lavoro.getImpiegatiAssegnati().contains(impiegato.getID()))
                impiegatiLavoro.add(impiegato);
        }
    }
}
