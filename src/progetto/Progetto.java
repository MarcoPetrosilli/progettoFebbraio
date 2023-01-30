package progetto;

import java.util.ArrayList;
import java.util.List;


public class Progetto {
    public static void main(String[] args) {
        //Dichiarazione variabili e oggetti necessari
        LeggiFile lettore = new LeggiFile();
        //Task1 esecutore1=new Task1();
        //Task2 esecutore2=new Task2();
        //Task3 esecutore3=new Task3();
        List<String> lines;
        int[] ImpiegatiCittaLavori;
        IntBoxer index = new IntBoxer(0);

        //AQUISIZIONE DATI
        lines=lettore.inizializzazioneLines();

        //LETTURA
        ImpiegatiCittaLavori=lettore.leggiNumeri(lines,index);   //Leggi numeri

        Impiegato[] impiegati = new Impiegato[ImpiegatiCittaLavori[0]];   //Leggi Impiegati
        lettore.popolaImpiegati(ImpiegatiCittaLavori[0],lines,index,impiegati);

        Citta[] citta = new Citta[ImpiegatiCittaLavori[1]];   //Leggi Citta
        lettore.popolaCitta(ImpiegatiCittaLavori[1],lines,index,citta);

        Lavoro[] lavori = new Lavoro[ImpiegatiCittaLavori[2]];   //Prodotti
        lettore.popolaLavori(ImpiegatiCittaLavori[2],lines,index,lavori,impiegati);


        //ESECUZIONE TASK
        switch(lettore.leggiTask(lines,index)){
            case "TASK1":
                Task1 t1=new Task1();
                t1.eseguiTask(citta,impiegati,lavori);
                break;
            case "TASK2":
                Task2 t2=new Task2();
                int[] pqr = lettore.leggiNumeriTask("TASK2",lines,index);
                t2.eseguiTask(pqr,lavori,citta,impiegati);
                break;
            case "TASK3":
                int[] nm = lettore.leggiNumeriTask("TASK3",lines,index);
                ArrayList<Lavoro> serielavori=lettore.leggiSerieLavori(lines,index,nm[1],lavori);
                break;
        }
    }
}