package es.uned.lsi.eped.pract2016_2017;

import es.uned.lsi.eped.DataStructures.QueueIF;
import es.uned.lsi.eped.DataStructures.Queue;
import es.uned.lsi.eped.DataStructures.ListIF;
import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.IteratorIF;

/**
 * Implementación de la interfaz PlayBackQueueIF o
 * cola de reproducción
 *
 * @author  Igor Quintela
 * @version 26/03/2017.
 */
public class PlayBackQueue implements PlayBackQueueIF{

    private QueueIF<Integer> playBackQueue;

    public PlayBackQueue() {
        this.playBackQueue = new Queue<Integer>();
    }

    @Override
    public boolean isEmpty() {
        return playBackQueue.size() == 0;
    }

    @Override
    public ListIF<Integer> getContent() {

        ListIF<Integer> contentList = new List<Integer>();                           // se crea la lista a devolver
        Queue<Integer> auxQueue = new Queue<Integer>((Queue<Integer>)playBackQueue); // se copia la cola en otra auxiliar
        int pos = 1;                    // se inicia la posicion de la lista a 1
        while (!auxQueue.isEmpty()){                        // rellena la lista a devolver
            contentList.insert(auxQueue.getFirst(),pos);
            auxQueue.dequeue();
            pos++;
        }
        return contentList;
    }

    @Override
    public int getFirstTune() {
        if (playBackQueue.isEmpty()){
            throw new NullPointerException("Cola vacia");
        }
        return playBackQueue.getFirst();
    }

    @Override
    public void extractFirstTune() {
        if (playBackQueue.isEmpty()){
            throw new NullPointerException("Cola vacia");
        }
        playBackQueue.dequeue();
    }

    @Override
    public void clear() {
        playBackQueue.clear();
    }

    @Override
    public void addTunes(ListIF<Integer> lT) {

        IteratorIF<Integer> it = lT.iterator();     // recorre la lista completa
        while (it.hasNext()){
            playBackQueue.enqueue(it.getNext());    // añadiendo elementos a la cola
        }
    }
}
