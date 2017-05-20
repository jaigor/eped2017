package es.uned.lsi.eped.pract2016_2017;

import es.uned.lsi.eped.DataStructures.QueueIF;
import es.uned.lsi.eped.DataStructures.Queue;
import es.uned.lsi.eped.DataStructures.ListIF;
import es.uned.lsi.eped.DataStructures.List;

/**
 * Implementación de la interfaz RecentlyPlayedIF o
 * lista de últimas canciones reproducidas
 *
 * @author  Igor Quintela
 * @version 26/03/2017.
 */
public class RecentlyPlayed implements RecentlyPlayedIF {

    private int maxSize;
    private QueueIF<Integer> recentlyPlayed;

    /**
     * Constructor de la cola de últimas canciones reproducidas
     * con tamaño acotado
     *
     * @param maxSize -tamaño máximo (entero) de
     *                la cola de canciones
     */
    public RecentlyPlayed(int maxSize) {
        this.maxSize = maxSize;
        this.recentlyPlayed = new Queue<Integer>();
    }

    @Override
    public ListIF<Integer> getContent() {
        ListIF<Integer> auxList = new List<Integer>();   // se crean las listas (2),
        ListIF<Integer> queueList = new List<Integer>(); // la auxiliar y la a devolver
        // se duplica la cola para usarla como auxiliar
        Queue<Integer> copyQueue = new Queue<Integer>((Queue<Integer>)recentlyPlayed);
        // insertamos las canciones en el orden actual en la lista auxiliar
        while (!copyQueue.isEmpty()){
            auxList.insert(copyQueue.getFirst(),auxList.size() + 1);
            copyQueue.dequeue();
        }
        // invertimos el orden de la lista a devolver
        int pos = 1;
        for (int i = auxList.size(); i > 0; i--){
            queueList.insert(auxList.get(i),pos);
            pos++;
        }
        return queueList;
    }

    @Override
    public void addTune(int tuneID) {

        if (!recentlyPlayed.contains(tuneID)) {     // comprueba que la canción no esta en la lista

            if (recentlyPlayed.size() >= maxSize){ // si el tamaño es superior al MAX
                recentlyPlayed.dequeue();          // se elimina la primera cancion de la lista
            }
            recentlyPlayed.enqueue(tuneID);        // añade la siguiente
        }
    }
}
