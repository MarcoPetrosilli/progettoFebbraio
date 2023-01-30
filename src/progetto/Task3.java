package progetto;

import java.util.ArrayList;

public class Task3 {
    public void eseguiTask(){

    }

    public boolean avviaSimulazione(ArrayList<Lavoro> serieLavori,int[] nm){
        boolean continua=true;
        String ultimoTipo=null;
        double ultimaDurata=0;
        double giorniTrascorsi=0;
        double giorniDaTrascorrere=0;
        double temp=0;
        double tempoTot=0;

        for(int i=0;i<serieLavori.size() && continua;i++){
            if(ultimoTipo!=null && ultimaDurata!=0){
                if(serieLavori.get(i).getTipo().equals("ritocco")){
                    //ROBA
                }
                else{
                    if(ultimoTipo.equals("costruzione")){
                        temp = 0.2*ultimaDurata;
                        giorniDaTrascorrere -= temp;
                        ultimaDurata -=temp;
                        giorniDaTrascorrere=max(giorniDaTrascorrere,ultimaDurata);
                        giorniTrascorsi += temp;
                        ultimoTipo=serieLavori.get(i).getTipo();
                        ultimaDurata=serieLavori.get(i).getDurata();
                    } else if (ultimoTipo.equals("ristrutturazione")) {
                        temp=0.3*ultimaDurata;
                        giorniDaTrascorrere -= temp;
                        ultimaDurata -=temp;
                        giorniDaTrascorrere=max(giorniDaTrascorrere,ultimaDurata);
                        giorniTrascorsi += temp;
                        ultimoTipo=serieLavori.get(i).getTipo();
                        ultimaDurata=serieLavori.get(i).getDurata();
                    }else{
                        //PER RITOCCO
                    }
                }
                if(i==(serieLavori.size()-1))
                    ultimaDurata=max(ultimaDurata,giorniDaTrascorrere);
                    tempoTot=calcolaTot(ultimaDurata,giorniTrascorsi);
            }
            else{
                ultimoTipo=serieLavori.get(i).getTipo();
                ultimaDurata=serieLavori.get(i).getDurata();
                giorniDaTrascorrere=ultimaDurata;
            }
        }
    }

    public double max(double a, double b){
        if(a>b) return a;
        return b;
    }

    public double calcolaTot(double ultimaDurata,double giorniTrascorsi){
        return ultimaDurata+giorniTrascorsi;
    }
}
