package progetto.tasks;

import progetto.classes.Citta;
import progetto.classes.Impiegato;
import progetto.classes.Lavoro;
import progetto.enums.TipoCitta;
import progetto.interfaces.ITaskStructure;

import java.util.ArrayList;

public class Task1 implements ITaskStructure {

    private final Impiegato[] impiegati;
    private final Citta[] citta;
    private final Lavoro[] lavori;

    public Task1(Impiegato[] impiegati,Citta[] citta,Lavoro[] lavori){
        this.impiegati=impiegati;
        this.citta=citta;
        this.lavori=lavori;
    }
    ////////////////////////////////////////////////// ESEGUI TASK

    @Override
    public void eseguiTask() {
        this.stampa1();
        this.stampa2();
        this.stampa3();
        this.stampa4();
        this.stampa5();
        this.stampa6();
        this.stampa7();
    }

    ////////////////////////////////////////////////// PUNTO 1
    public void stampa1(){
        int n=0;
        for(Impiegato impiegato:impiegati)
            if(!(impiegato.isAbilitato())) n++;
        System.out.println(n);
    }

    ////////////////////////////////////////////////// PUNTO 2
    public void stampa2(){
        System.out.println(this.trovaCittaMinLavori().getNome());
    }

    public Citta trovaCittaMinLavori(){
        Citta cittaMinLavori=citta[0];
        for(Citta cittaX:citta) {
            if (cittaX.getLavoriSuTerritorio().size() == 0) return cittaX;
            if (cittaX.getLavoriSuTerritorio().size() <= cittaMinLavori.getLavoriSuTerritorio().size()) {
                if (cittaX.getLavoriSuTerritorio().size() == cittaMinLavori.getLavoriSuTerritorio().size()) {
                    if (cittaX.getNome().compareToIgnoreCase(cittaMinLavori.getNome()) < 0)
                        cittaMinLavori = cittaX;
                } else
                    cittaMinLavori = cittaX;
            }
        }
        return cittaMinLavori;
    }

    ////////////////////////////////////////////////// PUNTO 3
    public void stampa3(){
        int[] counterImpiegati = new int[impiegati.length];

        counterImpiegati=this.contaMolteplicitaImpiegati(counterImpiegati);

        System.out.println(impiegati[this.trovaIndexMaxMolteplicitaImpiegati(counterImpiegati)].getID());
    }

    public int[] contaMolteplicitaImpiegati(int[] counterImpiegati){
        int i=0;
        for(Impiegato impiegato:impiegati){
            for(Lavoro lavoro:lavori){
                if(lavoro.getImpiegatiAssegnati().contains(impiegato.getID()))
                    counterImpiegati[i]++;
            }
            i++;
        }
        return counterImpiegati;
    }

    public int trovaIndexMaxMolteplicitaImpiegati(int[] counterImpiegati){
        int max=counterImpiegati[0];
        int index=0;
        for(int j=0;j<counterImpiegati.length;j++){
            if(counterImpiegati[j]>=max){
                if(counterImpiegati[j]==max) {
                    if (impiegati[j].getID().compareToIgnoreCase(impiegati[index].getID()) < 0) {
                        max = counterImpiegati[j];
                        index = j;
                    }
                }
                else{
                    max = counterImpiegati[j];
                    index = j;
                }
            }
        }
        return index;
    }

    ////////////////////////////////////////////////// PUNTO 4
    public void stampa4(){
        int[] counterImpiegati = new int[impiegati.length];

        counterImpiegati=this.contaImportiImpiegati(counterImpiegati);

        System.out.println(impiegati[this.trovaIndexMaxImporto(counterImpiegati)].getID());
    }

    public int[] contaImportiImpiegati(int[] counterImpiegati){
        int i=0;
        for(Impiegato impiegato:impiegati){
            for(Lavoro lavoro:lavori){
                if(lavoro.getImpiegatiAssegnati().contains(impiegato.getID()))
                    counterImpiegati[i]=counterImpiegati[i]+ lavoro.getImporto();
            }
            i++;
        }
        return counterImpiegati;
    }

    public int trovaIndexMaxImporto(int[] counterImpiegati){
        int max=counterImpiegati[0];
        int index=0;
        for(int j=0;j<counterImpiegati.length;j++){
            if(counterImpiegati[j]>=max){
                if(counterImpiegati[j]==max)
                    if(impiegati[j].getID().compareToIgnoreCase(impiegati[index].getID())<0){
                        max=counterImpiegati[j];
                        index=j;
                    }
                    else{
                        max = counterImpiegati[j];
                        index = j;
                    }
            }
        }
        return index;
    }

    ////////////////////////////////////////////////// PUNTO 5
    public void stampa5(){
        int[] counterCitta = new int[citta.length];
        int index;
        ArrayList<String> impiegatiCitta=new ArrayList<>();

        this.contaImpiegati(impiegatiCitta,counterCitta);
        index=this.trovaIndexMinImpiegati(counterCitta);
        if(counterCitta[index]==0)
            System.out.println(" ");
        else
            System.out.println(citta[this.trovaIndexMinImpiegati(counterCitta)].getGrandezza());
    }

    public void contaImpiegati( ArrayList<String> impiegatiCitta, int[] counterCitta){
        int i=0;
        for(Citta element:citta){
            if(element.getLavoriSuTerritorio().size()!=0){
                for(String lavoroString:element.getLavoriSuTerritorio()){
                    for(Lavoro lavoro:lavori)
                        if(lavoroString.equals(lavoro.getID())) impiegatiCitta.addAll(lavoro.getImpiegatiAssegnati());

                }
                for(Impiegato impiegato:impiegati)
                    if(impiegatiCitta.contains(impiegato.getID())) counterCitta[i]++;
                impiegatiCitta.clear();
            }
            else
                counterCitta[i]=0;
            i++;
        }
    }

    public int equalsGrandezzaCitta(TipoCitta grandezza1, TipoCitta grandezza2){
        int[] pesiGrandezza = {0,1,2};

        for(int i=0;i<TipoCitta.values().length;i++){
            if(grandezza1.equals(TipoCitta.values()[i]))
                for(int j=0;j<TipoCitta.values().length;j++)
                    if(grandezza2.equals(TipoCitta.values()[j]))
                        return pesiGrandezza[i]-pesiGrandezza[j];
        }
        return 0;
    }

    public int trovaIndexMinImpiegati(int[] counterCitta){
        int min=counterCitta[0];
        int index=0;
        for(int j=0;j<counterCitta.length;j++){
            if(counterCitta[j]!=0){
                if(counterCitta[j]<=min){
                    if(counterCitta[j]==min){
                        if(this.equalsGrandezzaCitta(citta[j].getGrandezza(),citta[index].getGrandezza())<0) {
                            min=counterCitta[j];
                            index=j;
                        }
                    }
                    else{
                        min=counterCitta[j];
                        index=j;
                    }
                }
            }
        }
        return index;
    }

    ////////////////////////////////////////////////// PUNTO 6
    public void stampa6(){
        Citta max=citta[0];
        ArrayList<String> impiegatiCitta = new ArrayList<>();

        max = this.trovaCittaMaxLavori(max);

       this.popolaImpiegatiCitta(max,impiegatiCitta);

        System.out.println(this.contaImpiegatiOutput(impiegatiCitta));
    }

    public Citta trovaCittaMaxLavori(Citta max){
        for(Citta element:citta){
            if(element.getLavoriSuTerritorio().size()>=max.getLavoriSuTerritorio().size()) {
                if (element.getLavoriSuTerritorio().size() == max.getLavoriSuTerritorio().size()) {
                    if (element.getNome().compareToIgnoreCase(max.getNome()) < 0)
                        max = element;
                } else
                    max = element;
            }
        }
        return max;
    }

    public void popolaImpiegatiCitta(Citta max,ArrayList<String> impiegatiCitta){
        for(String lavoroString:max.getLavoriSuTerritorio()){
            for(Lavoro lavoro:lavori)
                if(lavoroString.equals(lavoro.getID())) impiegatiCitta.addAll(lavoro.getImpiegatiAssegnati());

        }
    }

    public int contaImpiegatiOutput(ArrayList<String> impiegatiCitta){
        int counterCitta=0;
        for(Impiegato impiegato:impiegati)
            if(impiegatiCitta.contains(impiegato.getID())) counterCitta++;
        return counterCitta;
    }

    ////////////////////////////////////////////////// PUNTO 7
    public void stampa7(){
        int[] counterImpiegati = new int[TipoCitta.values().length];

        this.contaPerDimensione(counterImpiegati);
        System.out.println(counterImpiegati[0]+" "+counterImpiegati[1]+" "+counterImpiegati[2]);
    }

    public void contaPerDimensione(int[] counterImpiegati){
        ArrayList<Citta> cittaGrandezzaCorrente = new ArrayList<>();
        ArrayList<String> impiegatiCitta = new ArrayList<>();
        int i=0;

        for(TipoCitta dimensione:TipoCitta.values()){
            for(Citta element:citta){
                if(element.getGrandezza().equals(dimensione) && element.getLavoriSuTerritorio().size()!=0)
                    cittaGrandezzaCorrente.add(element);
            }

            for(Citta elemento: cittaGrandezzaCorrente){
                for(String lavoroString:elemento.getLavoriSuTerritorio()){
                    for(Lavoro lavoro:lavori)
                        if(lavoroString.equals(lavoro.getID())) impiegatiCitta.addAll(lavoro.getImpiegatiAssegnati());

                }
            }

            for(Impiegato impiegato:impiegati)
                if(impiegatiCitta.contains(impiegato.getID()))
                    counterImpiegati[i]++;

            cittaGrandezzaCorrente.clear();
            impiegatiCitta.clear();
            i++;
        }
    }
}
