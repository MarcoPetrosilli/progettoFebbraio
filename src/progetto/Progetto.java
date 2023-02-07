package progetto;

import progetto.classes.Citta;
import progetto.classes.Impiegato;
import progetto.classes.Lavoro;
import progetto.interfaces.ITaskStructure;
import progetto.utilities.IntBoxer;
import progetto.utilities.LeggiFile;
import progetto.tasks.Task1;
import progetto.tasks.Task2;
import progetto.tasks.Task3;

import java.util.ArrayList;
import java.util.List;


public class Progetto {
    public static void main(String[] args) {

        //region DICHIARAZIONI
        LeggiFile lettore = new LeggiFile();
        List<String> lines;
        int[] ImpiegatiCittaLavori;
        IntBoxer index = new IntBoxer(0);
        ITaskStructure taskExecutor = null;
        //endregion

        //region AQUISIZIONE DATI
        lines=lettore.inizializzazioneLines();
        //endregion

        //region LETTURA DEI DATI AQUISITI
        ImpiegatiCittaLavori=lettore.leggiNumeri(lines,index);

        Impiegato[] impiegati = new Impiegato[ImpiegatiCittaLavori[0]];
        lettore.popolaImpiegati(ImpiegatiCittaLavori[0],lines,index,impiegati);

        Citta[] citta = new Citta[ImpiegatiCittaLavori[1]];
        lettore.popolaCitta(ImpiegatiCittaLavori[1],lines,index,citta);

        Lavoro[] lavori = new Lavoro[ImpiegatiCittaLavori[2]];
        lettore.popolaLavori(ImpiegatiCittaLavori[2],lines,index,lavori,impiegati);
        //endregion

        //region ESECUZIONE TASK
        var task=lettore.leggiTask(lines, index);
        switch (task) {
            case TASK1 -> taskExecutor = new Task1(impiegati, citta, lavori);
            case TASK2 -> {
                int[] pqr = lettore.leggiNumeriTask(task, lines, index);
                taskExecutor = new Task2(pqr,impiegati, citta, lavori);
            }
            case TASK3 -> {
                int[] nm = lettore.leggiNumeriTask(task, lines, index);
                ArrayList<Lavoro> serielavori = lettore.leggiSerieLavori(lines, index, nm[1], lavori);
                taskExecutor = new Task3(serielavori,nm[0]);
            }
        }
        taskExecutor.eseguiTask();
        //endregion

    }
}