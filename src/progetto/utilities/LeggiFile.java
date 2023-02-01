package progetto.utilities;

import progetto.classes.Citta;
import progetto.classes.Impiegato;
import progetto.classes.Lavoro;
import progetto.enums.TipoCitta;
import progetto.enums.TipoImpiegato;
import progetto.enums.TipoLavoro;
import progetto.enums.TipoTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LeggiFile {

    //region GESTIONE LINES / INDEX
    public List<String> inizializzazioneLines(){
        ArrayList<String> lines=new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String riga;
        while(in.hasNextLine()){
            riga=in.nextLine();
            if(riga.isEmpty())
                break;
            lines.add(riga);
        }
        in.close();
        return lines;
    }

    public Integer incrementaIndex(List<String> lines,IntBoxer index){
        if(index.value<lines.size()) return (index.value+1);
        else return 0;
    }
    //endregion

    //region LETTURA NUMERI QUANTITA ELEMENTI
    public int[] leggiNumeri(List<String> lines,IntBoxer index){
        String[] tokenizedLine=lines.get(index.value).split(" ");
        int[] n = new int[tokenizedLine.length];
        for(int i=0;i<n.length;i++){
            n[i]=Integer.parseInt(tokenizedLine[i]);
        }
        return n;
    }
    //endregion

    //region LETTURA IMPIEGATI
    public void popolaImpiegati(int n, List<String> lines, IntBoxer index, Impiegato[] impiegati){
        for(int i=0;i<n;i++){
            index.value=this.incrementaIndex(lines,index);
            impiegati[i]=this.leggiImpiegato(lines,index);
        }
    }

    public Impiegato leggiImpiegato(List<String> lines,IntBoxer index){
        String[] tokenizedLine=lines.get(index.value).split(" ");
        String ID = tokenizedLine[0];
        TipoImpiegato tipo = TipoImpiegato.valueOf(tokenizedLine[1]);
        String nome = tokenizedLine[2];
        String cognome = tokenizedLine[3];
        return new Impiegato(ID,nome,cognome,tipo);
    }
    //endregion

    //region LETTURA CITTA
    public void popolaCitta(int n, List<String> lines, IntBoxer index, Citta[] citta){
        for(int i=0;i<n;i++){
            index.value=this.incrementaIndex(lines,index);
            citta[i]=this.leggiCitta(lines,index);
        }
    }

    public Citta leggiCitta(List<String> lines,IntBoxer index){
        String[] tokenizedLine=lines.get(index.value).split(" ");
        String ID=tokenizedLine[0];
        TipoCitta grandezza = TipoCitta.valueOf(tokenizedLine[1]);
        String linesDaSplittare = tokenizedLine[2];
        String[] Array=linesDaSplittare.split(",");
        ArrayList<String> lavori = new ArrayList<>();
        if(!(Array[0].equals("-"))){
            lavori.addAll(Arrays.asList(Array));
        }
        return new Citta(ID,grandezza,lavori);
    }
    //endregion

    //region LETTURA LAVORI
    public void popolaLavori(int n, List<String> lines, IntBoxer index, Lavoro[] lavori, Impiegato[] impiegati){
        for(int i=0;i<n;i++){
            index.value=this.incrementaIndex(lines,index);
            lavori[i]=this.leggiLavoro(lines,index);
        }
        for(Impiegato impiegato:impiegati)
            impiegato.setAbilitato(lavori);
    }

    public Lavoro leggiLavoro(List<String> lines,IntBoxer index){
        String[] tokenizedLine=lines.get(index.value).split(" ");
        String ID=tokenizedLine[0];
        String linesDaSplittare = tokenizedLine[1];
        String[] Array = linesDaSplittare.split(",");
        ArrayList<String> impiegatiAssegnati = new ArrayList<>(Arrays.asList(Array));
        TipoLavoro tipo = TipoLavoro.valueOf(tokenizedLine[2]);
        int durata = Integer.parseInt(tokenizedLine[3]);
        int importo = Integer.parseInt(tokenizedLine[4]);
        return new Lavoro(ID,tipo,durata,importo,impiegatiAssegnati);
    }
    //endregion

    //region LETTURA TASK E PARAMETRI CARATTERIZZANTI TASK
    public TipoTask leggiTask(List<String> lines,IntBoxer index){
        index.value=this.incrementaIndex(lines,index);
        String[] tokenizedLine=lines.get(index.value).split(" ");
        return TipoTask.valueOf(tokenizedLine[0]);
    }

    public int[] leggiNumeriTask(TipoTask task, List<String> lines, IntBoxer index){
        String[] tokenizedLine;
        int[] temp;
        switch (task) {
            case TASK2 -> {
                temp = new int[3];
                tokenizedLine = lines.get(index.value).split(" ");
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = Integer.parseInt(tokenizedLine[i + 1]);
                }
                return temp;
            }
            case TASK3 -> {
                temp = new int[2];
                tokenizedLine = lines.get(index.value).split(" ");
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = Integer.parseInt(tokenizedLine[i + 1]);
                }
                return temp;
            }
        }
        temp=new int[1];
        return temp;
    }
    //endregion

    //region LETTURA SERIE LAVORI PER TASK3
    public ArrayList<Lavoro> leggiSerieLavori(List<String> lines,IntBoxer index, int n,Lavoro[] lavori){
        ArrayList<Lavoro> serielavori = new ArrayList<>();
        String lavoroDaCercare;
        for(int i=0;i<n;i++){
            index.value=incrementaIndex(lines,index);
            lavoroDaCercare=lines.get(index.value);
            serielavori.add(this.cercaLavoro(lavoroDaCercare,lavori));
        }
        return serielavori;
    }

    public Lavoro cercaLavoro(String lavoroDaCercare,Lavoro[] lavori){
        for(Lavoro lavoro:lavori)
            if(lavoro.getID().equals(lavoroDaCercare)) return lavoro;
        return null;
    }
    //endregion

}
