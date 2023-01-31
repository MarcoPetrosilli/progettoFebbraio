package progetto.Classes;

import progetto.Utilities.IntBoxer;
import progetto.Utilities.LeggiFile;
import progetto.Tasks.Task1;
import progetto.Tasks.Task2;
import progetto.Tasks.Task3;

import java.util.ArrayList;
import java.util.List;


public class Progetto {
    public static void main(String[] args) {

        ////////////////////////////////////////////////// DICHIARAZIONE VARIABILI E OGGETTI NECESSARI
        LeggiFile lettore = new LeggiFile();
        List<String> lines;
        int[] ImpiegatiCittaLavori;
        IntBoxer index = new IntBoxer(0);

        ////////////////////////////////////////////////// AQUISIZIONE DATI

        lines=lettore.inizializzazioneLines();

        ////////////////////////////////////////////////// LETTURA DEI DATI AQUISITI

        ImpiegatiCittaLavori=lettore.leggiNumeri(lines,index);   //Leggi numeri

        Impiegato[] impiegati = new Impiegato[ImpiegatiCittaLavori[0]];   //Leggi Impiegati
        lettore.popolaImpiegati(ImpiegatiCittaLavori[0],lines,index,impiegati);

        Citta[] citta = new Citta[ImpiegatiCittaLavori[1]];   //Leggi Citta
        lettore.popolaCitta(ImpiegatiCittaLavori[1],lines,index,citta);

        Lavoro[] lavori = new Lavoro[ImpiegatiCittaLavori[2]];   //Prodotti
        lettore.popolaLavori(ImpiegatiCittaLavori[2],lines,index,lavori,impiegati);

        ////////////////////////////////////////////////// ESECUZIONE TASK

        switch (lettore.leggiTask(lines, index)) {
            case "TASK1" -> {
                Task1 t1 = new Task1();
                t1.eseguiTask(citta, impiegati, lavori);
            }
            case "TASK2" -> {
                Task2 t2 = new Task2();
                int[] pqr = lettore.leggiNumeriTask("TASK2", lines, index);
                t2.eseguiTask(pqr, lavori, citta, impiegati);
            }
            case "TASK3" -> {
                Task3 t3 = new Task3();
                int[] nm = lettore.leggiNumeriTask("TASK3", lines, index);
                ArrayList<Lavoro> serielavori = lettore.leggiSerieLavori(lines, index, nm[1], lavori);
                t3.eseguiTask(serielavori, nm[0]);
            }
        }
    }
}