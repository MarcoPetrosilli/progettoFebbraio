package progetto.Tasks;

import progetto.Classes.Citta;
import progetto.Classes.Impiegato;
import progetto.Classes.Lavoro;

import java.util.ArrayList;

public class Task2 implements taskStructure{

    private final int[] pqr;
    private final Impiegato[] impiegati;
    private final Citta[] citta;
    private final Lavoro[] lavori;

    public Task2(int[] pqr,Impiegato[] impiegati,Citta[] citta,Lavoro[] lavori){
        this.pqr=pqr;
        this.impiegati=impiegati;
        this.citta=citta;
        this.lavori=lavori;
    }
    ////////////////////////////////////////////////// ESEGUI TASK

    @Override
    public void eseguiTask() {
        if(this.controllo())
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    public boolean controllo(){
        return (this.controlla1() && this.controlla2() && this.controlla3() && this.controlla4());
    }

    ////////////////////////////////////////////////// PUNTO 1

    public boolean controlla1(){
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

    public boolean controlla2(){
        int counterCittaMaggiori=0;
        int counterCittaMinori=0;
        ArrayList<Integer> totCostoInPiuCitta = new ArrayList<>();
        ArrayList<Lavoro> lavoriImpiegato= new ArrayList<>();

        for(Impiegato impiegato:impiegati){
            //Calcola costo totale in tutte le citta che coinvolgono impiegato
            this.popolaTotCostoInPiuCitta(impiegato,lavoriImpiegato,totCostoInPiuCitta);
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

    public void trovaLavoriImpiegato(Citta elemento,Impiegato impiegato,ArrayList<Lavoro> lavoriImpiegato){
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

    public void popolaTotCostoInPiuCitta(Impiegato impiegato,ArrayList<Lavoro> lavoriImpiegato,ArrayList<Integer> totCostoInPiuCitta){
        int sommaCostoLavori;
        for(Citta elemento:citta){
            //Trova lavori di Impiegato in Citta
            this.trovaLavoriImpiegato(elemento,impiegato,lavoriImpiegato);
            //Calcola valore tot lavori in Citta e se diversa da 0 aggiungi
            sommaCostoLavori=this.calcolaSommaLavori(lavoriImpiegato);
            if(sommaCostoLavori!=0)
                totCostoInPiuCitta.add(sommaCostoLavori);
            lavoriImpiegato.clear();
        }
    }

    ////////////////////////////////////////////////// PUNTO 3

    public boolean controlla3(){
        int ristrutturazione = 0;
        int ritocco = 0;
        int costruzione = 0;
        ArrayList<Lavoro> lavoriInCitta = new ArrayList<>();

        for(Citta elemento:citta){
            //popolo vettore con lavori della citta
            this.trovaLavoriInCitta(elemento,lavoriInCitta);
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

    public void trovaLavoriInCitta(Citta elemento, ArrayList<Lavoro> lavoriInCitta){
        for(Lavoro lavoro:lavori){
            if(elemento.getLavoriSuTerritorio().contains(lavoro.getID()))
                lavoriInCitta.add(lavoro);
        }
    }

    ////////////////////////////////////////////////// PUNTO 4

    public boolean controlla4(){
        ArrayList<Impiegato> impiegatiLavoro = new ArrayList<>();
        int ingegnere = 0;
        int architetto = 0;
        int geometra = 0;

        for(Lavoro lavoro:lavori){
            //popolo vettore con impiegati del lavoro
            this.trovaImpiegatiLavoro(lavoro,impiegatiLavoro);
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

    public void trovaImpiegatiLavoro(Lavoro lavoro, ArrayList<Impiegato> impiegatiLavoro){
        for(Impiegato impiegato:impiegati){
            if(lavoro.getImpiegatiAssegnati().contains(impiegato.getID()))
                impiegatiLavoro.add(impiegato);
        }
    }
}
