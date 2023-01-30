package progetto;

import java.util.ArrayList;
import java.util.Arrays;

public class Task1 {

    ////////////////////////////////////////////////// ESEGUI TASK

    public void eseguiTask(Citta[] citta, Impiegato[] impiegati, Lavoro[] lavori){
        this.stampa1(impiegati);
        this.stampa2(citta);
        this.stampa3(impiegati,lavori);
        this.stampa4(impiegati,lavori);
        this.stampa5(impiegati,lavori,citta);
        this.stampa6(impiegati,lavori,citta);
        this.stampa7(impiegati,lavori,citta);
    }

    ////////////////////////////////////////////////// PUNTO 1
    public void stampa1(Impiegato[] impiegati){
        int n=0;
        for(Impiegato impiegato:impiegati)
            if(!(impiegato.isAbilitato())) n++;
        System.out.println(n);
    }

    ////////////////////////////////////////////////// PUNTO 2
    public void stampa2(Citta[] citta){
        System.out.println(this.trovaCittaMinLavori(citta).getNome());
    }

    public Citta trovaCittaMinLavori(Citta[] citta){
        Citta cittaMinLavori=citta[0];
        for(Citta cittaX:citta)
            if(cittaX.getLavoriSuTerritorio().size()<=cittaMinLavori.getLavoriSuTerritorio().size()){
                if(cittaX.getLavoriSuTerritorio().size()==cittaMinLavori.getLavoriSuTerritorio().size()){
                    if(cittaX.getNome().compareToIgnoreCase(cittaMinLavori.getNome())<0)
                        cittaMinLavori=cittaX;
                }
                else
                    cittaMinLavori=cittaX;
            }
        return cittaMinLavori;
    }

    ////////////////////////////////////////////////// PUNTO 3
    public void stampa3(Impiegato[] impiegati, Lavoro[] lavori){
        int[] counterImpiegati = new int[impiegati.length];

        counterImpiegati=this.contaMolteplicitaImpiegati(impiegati,lavori,counterImpiegati);

        System.out.println(impiegati[this.trovaIndexMaxMolteplicitaImpiegati(counterImpiegati,impiegati)].getID());
    }

    public int[] contaMolteplicitaImpiegati(Impiegato[] impiegati, Lavoro[] lavori,int[] counterImpiegati){
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

    public int trovaIndexMaxMolteplicitaImpiegati(int[] counterImpiegati, Impiegato[] impiegati){
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
    public void stampa4(Impiegato[] impiegati, Lavoro[] lavori){
        int[] counterImpiegati = new int[impiegati.length];

        counterImpiegati=this.contaImportiImpiegati(impiegati,lavori,counterImpiegati);

        System.out.println(impiegati[this.trovaIndexMaxImporto(counterImpiegati,impiegati)].getID());
    }

    public int[] contaImportiImpiegati(Impiegato[] impiegati,Lavoro[] lavori,int[] counterImpiegati){
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

    public int trovaIndexMaxImporto(int[] counterImpiegati,Impiegato[] impiegati){
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
    public void stampa5(Impiegato[] impiegati, Lavoro[] lavori, Citta[] citta){
        int[] counterCitta = new int[citta.length];
        ArrayList<String> impiegatiCitta=new ArrayList<>();

        this.contaImpiegati(citta,lavori,impiegati,impiegatiCitta,counterCitta);

        System.out.println(citta[this.trovaIndexMinImpiegati(counterCitta,citta)].getGrandezza());
    }

    public void contaImpiegati(Citta[] citta,Lavoro[] lavori,Impiegato[] impiegati, ArrayList<String> impiegatiCitta, int[] counterCitta){
        int i=0;
        for(Citta element:citta){
            for(String lavoroString:element.getLavoriSuTerritorio()){
                for(Lavoro lavoro:lavori)
                    if(lavoroString.equals(lavoro.getID())) impiegatiCitta.addAll(lavoro.getImpiegatiAssegnati());

            }
            for(Impiegato impiegato:impiegati)
                if(impiegatiCitta.contains(impiegato.getID())) counterCitta[i]++;
            impiegatiCitta.clear();
            i++;
        }
    }

    public int equalsGrandezzaCitta(String grandezza1, String grandezza2){
        int[] pesiGrandezza = {0,1,2};
        String[] grandezzePossibili = {"piccola","media","grande"};

        for(int i=0;i<grandezzePossibili.length;i++){
            if(grandezza1.equals(grandezzePossibili[i]))
                for(int j=0;j<grandezzePossibili.length;j++)
                    if(grandezza2.equals(grandezzePossibili[j]))
                        return pesiGrandezza[i]-pesiGrandezza[j];
        }
        return 0;
    }

    public int trovaIndexMinImpiegati(int[] counterCitta,Citta[] citta){
        int min=counterCitta[0];
        int index=0;
        for(int j=0;j<counterCitta.length;j++){
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
        return index;
    }

    ////////////////////////////////////////////////// PUNTO 6
    public void stampa6(Impiegato[] impiegati, Lavoro[] lavori, Citta[] citta){
        Citta max=citta[0];
        ArrayList<String> impiegatiCitta = new ArrayList<>();

        max = this.trovaCittaMaxLavori(citta,max);

       this.popolaImpiegatiCitta(max,lavori,impiegatiCitta);

        System.out.println(this.contaImpiegatiOutput(impiegati,impiegatiCitta));
    }

    public Citta trovaCittaMaxLavori(Citta[] citta,Citta max){
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

    public void popolaImpiegatiCitta(Citta max,Lavoro[] lavori,ArrayList<String> impiegatiCitta){
        for(String lavoroString:max.getLavoriSuTerritorio()){
            for(Lavoro lavoro:lavori)
                if(lavoroString.equals(lavoro.getID())) impiegatiCitta.addAll(lavoro.getImpiegatiAssegnati());

        }
    }

    public int contaImpiegatiOutput(Impiegato[] impiegati,ArrayList<String>impiegatiCitta){
        int counterCitta=0;
        for(Impiegato impiegato:impiegati)
            if(impiegatiCitta.contains(impiegato.getID())) counterCitta++;
        return counterCitta;
    }

    ////////////////////////////////////////////////// PUNTO 7
    public void stampa7(Impiegato[] impiegati, Lavoro[] lavori, Citta[] citta){
        int[] counterImpiegati = new int[Citta.dimensioniPossibili.size()];

        counterImpiegati=this.contaPerDimensione(citta,lavori,impiegati,counterImpiegati);
        System.out.println(counterImpiegati[0]+" "+counterImpiegati[1]+" "+counterImpiegati[2]);
    }

    public int[] contaPerDimensione(Citta[] citta,Lavoro[] lavori,Impiegato[] impiegati,int[] counterImpiegati){
        ArrayList<Citta> cittaGrandezzaCorrente = new ArrayList<Citta>();
        ArrayList<String> impiegatiCitta = new ArrayList<String>();
        int i=0;

        for(String dimensione:Citta.dimensioniPossibili){
            for(Citta element:citta){
                if(element.getGrandezza().equals(dimensione))
                    cittaGrandezzaCorrente.add(element);
            }
            for(Citta elemento: cittaGrandezzaCorrente){
                for(String lavoroString:elemento.getLavoriSuTerritorio()){
                    for(Lavoro lavoro:lavori)
                        if(lavoroString.equals(lavoro.getID())) impiegatiCitta.addAll(lavoro.getImpiegatiAssegnati());

                }
                for(Impiegato impiegato:impiegati)
                    if(impiegatiCitta.contains(impiegato.getID())) counterImpiegati[i]++;
            }
            cittaGrandezzaCorrente.clear();
            impiegatiCitta.clear();
            i++;
        }
        return counterImpiegati;
    }
}
