package es.uned.lsi.eped.pract2016_2017;

import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.ListIF;

/**
 * Representación de Player,
 * clase usada para gestionar o
 * controlar los elementos del
 * reproductor de música
 *
 * @author  Igor Quintela
 * @version 29/03/2017.
 */
public class Player implements PlayerIF{

    private TuneCollectionIF    tc;
    private PlayListManager     plm;
    private PlayBackQueue       pbq;
    private RecentlyPlayed      rp;

    /**
     * Constructor de la clase que implementa PlayerIF
     * @param T                  -una colección de canciones como un objeto TuneCollectionIF
     * @param maxRecentlyPlayed  -un entero representando el número máximo de canciones
     *                            reproducidas que se pueden almacenar */
    Player(TuneCollectionIF T,int maxRecentlyPlayed){
        // Se inicializan los TAD (todos vacíos menos TuneCollection)
        this.tc = T;
        this.plm = new PlayListManager();
        this.pbq = new PlayBackQueue();
        this.rp = new RecentlyPlayed(maxRecentlyPlayed);
    }

    @Override
    public ListIF<String> getPlayListIDs() {
        return plm.getIDs();
    }

    @Override
    public ListIF<Integer> getPlayListContent(String playListID) {
        // Se crea una lista vacía por si no existe en el reproductor
        ListIF<Integer> playListContent = new List<Integer>();

        if (plm.contains(playListID)) {                     // si existe la lista de reproducción
            playListContent = plm.getPlayList(playListID).getPlayList(); // se copia
        }                                                   // si no, no hace nada
        return playListContent;
    }

    @Override
    public ListIF<Integer> getPlayBackQueue() {
        return pbq.getContent();
    }

    @Override
    public ListIF<Integer> getRecentlyPlayed() {
        return rp.getContent();
    }

    @Override
    public void createPlayList(String playListID) {
        plm.createPlayList(playListID);
    }

    @Override
    public void removePlayList(String playListID) {
        plm.removePlayList(playListID);
    }

    @Override
    public void addListOfTunesToPlayList(String playListID, ListIF<Integer> lT) {

        if (plm.contains(playListID)) {                     // si existe la lista de reproducción
            plm.getPlayList(playListID).addListOfTunes(lT); // se añaden
        }                                                   // si no, no hace nada
    }

    @Override
    public void addSearchToPlayList(String playListID,
                                    String t, String a, String g,
                                    String al, int min_y, int max_y,
                                    int min_d, int max_d) {

        // crea la consulta con los parámetros buscados
        QueryIF tuneQuery = new Query(t,a,g,al,min_y,max_y,min_d,max_d);
        ListIF<Integer> lT = new List<Integer>();  // se crea la lista de identificadores

        for (int i = 0; i < tc.size(); i++){    // recorre el repositorio completo
            if (((TuneCollection)tc).getTune(i).match(tuneQuery)){
                lT.insert(i,lT.size()+1);  // se guardan temporalmente aquellos que encajen
                // con la búsqueda concatenados segun el orden de llegada
            }
        }

        if (plm.contains(playListID)) {                     // si existe la lista de reproducción
            plm.getPlayList(playListID).addListOfTunes(lT); // se añaden finalmente a la lista de reproduccion
        }                                                   // si no, no hace nada

    }

    @Override
    public void removeTuneFromPlayList(String playListID, int tuneID) {

        if (plm.contains(playListID)) {                     // si existe la lista de reproducción
            plm.getPlayList(playListID).removeTune(tuneID); // se elimina la canción
        }                                                   // si no, no hace nada

    }

    @Override
    public void addListOfTunesToPlayBackQueue(ListIF<Integer> lT) {
        pbq.addTunes(lT);
    }

    @Override
    public void addSearchToPlayBackQueue(String t, String a, String g,
                                         String al, int min_y, int max_y,
                                         int min_d, int max_d) {

        // crea la consulta con los parámetros buscados
        QueryIF tuneQuery = new Query(t,a,g,al,min_y,max_y,min_d,max_d);
        ListIF<Integer> lT = new List<Integer>();     // se crea la lista de identificadores

        for (int i = 0; i < tc.size(); i++){    // recorre el repositorio completo
            if (((TuneCollection)tc).getTune(i).match(tuneQuery)){
                lT.insert(i,lT.size()+1);  // se guardan temporalmente aquellos que encajen
                                                // con la búsqueda concatenados segun el orden de llegada
            }
        }
        pbq.addTunes(lT); // se añaden finalmente a la cola de reproduccion
    }

    @Override
    public void addPlayListToPlayBackQueue(String playListID) {

        if (plm.contains(playListID)){      // si existe la lista, la añade
            pbq.addTunes(plm.getPlayList(playListID).getPlayList());
        }                                   // si no, no hace nada
    }

    @Override
    public void clearPlayBackQueue() {
        pbq.clear();
    }

    @Override
    public void play() {
        if (!pbq.isEmpty()){                     // si no está vacía la cola,
            int tuneToMove = pbq.getFirstTune(); // se extrae una cancion y
            pbq.extractFirstTune();              // se elimina (primer elemento)
            rp.addTune(tuneToMove);              // y la añade a las recientes
        }                                        // si no, no hace nada
    }
}
