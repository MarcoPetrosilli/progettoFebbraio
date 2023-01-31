package progetto;

import java.util.ArrayList;

public class Task3 {
    public void eseguiTask(ArrayList<Lavoro> serieLavori,int tempoDisponibile){
        if(this.avviaSimulazione(serieLavori,tempoDisponibile))
            System.out.println("VALID");
        else
            System.out.println("NOT VALID");
    }

    public boolean avviaSimulazione(ArrayList<Lavoro> serieLavori, int tempoDisponibile){
        String ultimoTipo=null;
        double ultimaDurata=0;
        double giorniTrascorsi=0;
        double giorniDaTrascorrere=0;
        double temp;
        double tempoTot=0;


        for(int i=0;i<serieLavori.size();i++){
            if(ultimoTipo!=null && ultimaDurata!=0){
                //Assegna temp in base a ultimoTipo
                temp=this.calcolaTemp(ultimoTipo,ultimaDurata);
                //Trascorri giorni e aggiorna daTrascorrere
                giorniDaTrascorrere -= temp;
                ultimaDurata -=temp;
                giorniDaTrascorrere=max(giorniDaTrascorrere,ultimaDurata);
                //Aggiorna trascorsi
                giorniTrascorsi += temp;
                //Aggiorna ultimoTipo e ultimaDurata
                ultimoTipo=serieLavori.get(i).getTipo();
                ultimaDurata=serieLavori.get(i).getDurata();
                //Controlla se è ultima iterazione
                if(i==(serieLavori.size()-1)){
                    ultimaDurata=max(ultimaDurata,giorniDaTrascorrere);
                    tempoTot=calcolaTot(ultimaDurata,giorniTrascorsi);
                }
            }
            //Inizializza per prima iterazione
            else{
                ultimoTipo=serieLavori.get(i).getTipo();
                ultimaDurata=serieLavori.get(i).getDurata();
                giorniDaTrascorrere=ultimaDurata;
            }
        }
        return tempoDisponibile>tempoTot;
    }

    public double max(double a, double b){
        if(a>b) return a;
        return b;
    }

    public double calcolaTot(double ultimaDurata,double giorniTrascorsi){
        return ultimaDurata+giorniTrascorsi;
    }

    public double calcolaTemp(String ultimoTipo,double ultimaDurata){
        double coeffPercentuale=0;
        if(ultimoTipo.equals("costruzione"))
            coeffPercentuale=0.2;

        else if (ultimoTipo.equals("ristrutturazione"))
            coeffPercentuale=0.3;
        return coeffPercentuale*ultimaDurata;
    }
}
