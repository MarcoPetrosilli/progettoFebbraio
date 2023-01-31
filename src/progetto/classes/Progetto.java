package progetto.classes;

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

        ////////////////////////////////////////////////// DICHIARAZIONE VARIABILI E OGGETTI NECESSARI
        LeggiFile lettore = new LeggiFile();
        List<String> lines;
        int[] ImpiegatiCittaLavori;
        IntBoxer index = new IntBoxer(0);
        ITaskStructure taskExecutor = null;

        ////////////////////////////////////////////////// AQUISIZIONE DATI

        lines=lettore.inizializzazioneLines();

        ////////////////////////////////////////////////// LETTURA DEI DATI AQUISITI

        ImpiegatiCittaLavori=lettore.leggiNumeri(lines,index);                  //Leggi numeri

        Impiegato[] impiegati = new Impiegato[ImpiegatiCittaLavori[0]];         //Leggi Impiegati
        lettore.popolaImpiegati(ImpiegatiCittaLavori[0],lines,index,impiegati);

        Citta[] citta = new Citta[ImpiegatiCittaLavori[1]];                     //Leggi Citta
        lettore.popolaCitta(ImpiegatiCittaLavori[1],lines,index,citta);

        Lavoro[] lavori = new Lavoro[ImpiegatiCittaLavori[2]];                  //Prodotti
        lettore.popolaLavori(ImpiegatiCittaLavori[2],lines,index,lavori,impiegati);

        ////////////////////////////////////////////////// ESECUZIONE TASK

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
    }
}