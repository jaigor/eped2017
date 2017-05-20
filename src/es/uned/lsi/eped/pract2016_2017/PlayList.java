package es.uned.lsi.eped.pract2016_2017;

import es.uned.lsi.eped.DataStructures.ListIF;
import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.IteratorIF;

/**
 * Implementación de la interfaz PlayListIF o lista
 * de reproducción
 * @author  Igor Quintela
 * @version 23/03/2017.
 */
public class PlayList implements PlayListIF {

    private ListIF<Integer> playList;
    private String playListID;

    /**
     * Creación de la lista de reproducción
     * @param playListID  -una cadena de caracteres no
     *                    vacia que identifica a la lista
     */
    public PlayList(String playListID) {
        this.playList = new List<Integer>();
        this.playListID = playListID;
    }

    @Override
    public ListIF<Integer> getPlayList() {
        return playList;
    }

    @Override
    public void removeTune(int tuneID) {

        if (playList.isEmpty()){
            throw new NullPointerException("Lista vacia");
        } else {
            IteratorIF<?> iterator = playList.iterator();
            int i = 1; // posicion inicializada a 1

            while (iterator.hasNext()){ // el iterador recorre la lista entera
                if (iterator.getNext().equals(tuneID)){ // buscando la cancion
                    playList.remove(i);                 // y la/s elimina/s
                    i--; // decrementamos el iterador porque el nodo siguiente
                         // se ha reposicionado al actual, ejecutando remove
                }
                i++;
            }
        }

    }

    @Override
    public void addListOfTunes(ListIF<Integer> lT) {

        IteratorIF<Integer> iterator = lT.iterator();
        while (iterator.hasNext()){
            playList.insert(iterator.getNext(),playList.size()+1); // el contenido se concatena
                                                                        // a la lista de reproduccion
        }

    }

    /**
     * Devuelve el identificador de la lista de reproducción
     * @return la cadena de caracteres que identifica la lista
     */
    public String getPlayListID() {
        return playListID;
    }

}
