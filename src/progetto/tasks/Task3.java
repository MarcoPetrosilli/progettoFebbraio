package progetto.tasks;

import progetto.classes.Lavoro;
import progetto.enums.TipoLavoro;
import progetto.interfaces.ITaskStructure;

import java.util.ArrayList;

public class Task3 implements ITaskStructure {

    //region ATTRIBUTI E COSTRUTTORE
    private final ArrayList<Lavoro> serieLavori;
    int tempoDisponibile;

    public Task3(ArrayList<Lavoro> serieLavori,int tempoDisponibile){
        this.serieLavori=serieLavori;
        this.tempoDisponibile=tempoDisponibile;
    }
    //endregion

    //region ESEGUI TASK
    @Override
    public void eseguiTask() {
        if(this.avviaSimulazione())
            System.out.println("VALID");
        else
            System.out.println("NOT VALID");
    }
    //endregion

    //region METODI DI APPOGGIO
    public boolean avviaSimulazione(){
        TipoLavoro ultimoTipo=null;
        double ultimaDurata=0;
        double giorniTrascorsi=0;
        double giorniDaTrascorrere=0;
        int temp;
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
        System.out.println(tempoTot);
        return tempoDisponibile>=tempoTot;
    }

    public double max(double a, double b){
        return Math.max(a, b);
    }

    public double calcolaTot(double ultimaDurata,double giorniTrascorsi){
        return ultimaDurata+giorniTrascorsi;
    }

    public int calcolaTemp(TipoLavoro ultimoTipo,double ultimaDurata){
        double coeffPercentuale=0;
        if(ultimoTipo.equals(TipoLavoro.costruzione))
            coeffPercentuale=0.2;

        else if (ultimoTipo.equals(TipoLavoro.ristrutturazione))
            coeffPercentuale=0.3;
        var contoNonArr=coeffPercentuale*ultimaDurata;
        if((int)contoNonArr<contoNonArr)
            return ((int)contoNonArr)+1;
        return (int)contoNonArr;
    }
    //endregion

}
