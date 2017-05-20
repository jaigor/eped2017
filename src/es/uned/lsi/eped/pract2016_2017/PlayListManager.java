package es.uned.lsi.eped.pract2016_2017;

import es.uned.lsi.eped.DataStructures.BTree;
import es.uned.lsi.eped.DataStructures.BTreeIterator;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.ListIF;
import es.uned.lsi.eped.DataStructures.List;

/**
 * Implementación de la interfaz PlayListManagerIF o
 * gestor de listas de reproducción
 *
 * @author  Igor Quintela
 * @version 25/03/2017.
 */
public class PlayListManager implements PlayListManagerIF {

    private BTree<PlayList> playListBTree;

    public PlayListManager() {
        this.playListBTree = new BTree<PlayList>();
    }

    @Override
    public boolean contains(String playListID) {

        if (find(playListID,playListBTree) != null){ // si devuelve un nodo
            return true;                             // devuelve true
        }
        return false;                               // si no, devuelve false
    }

    @Override
    public PlayListIF getPlayList(String playListID) {

        BTree<PlayList> auxTree = find(playListID,playListBTree);
        if (auxTree != null){
            return (auxTree.getRoot());
        }
        return null;

    }

    /**
     * Busca un nodo (lista de reproducción) en el camino o árbol
     * y lo devuelve
     * @param playListID cadena de caracteres del nodo (lista) a insertar
     * @param node       nodo raíz con el camino a recorrer
     * @return Devuelve el nodo buscado
     */
    protected BTree<PlayList> find(String playListID, BTree<PlayList> node){

        while (!node.isEmpty()) {
            if ((playListID.compareTo(node.getRoot().getPlayListID())) < 0) {       // si es menor el buscado,
                node =((BTree<PlayList>)node.getLeftChild());                       // continua por el nodo izquierdo
            } else if ((playListID.compareTo(node.getRoot().getPlayListID())) > 0){ // si es mayor,
                node =((BTree<PlayList>)node.getRightChild());                      // continua por el nodo derecho
            } else {
                return node;                                                        // si la encuentra, la devuelve
            }
        }
        return null;                                                                // si no la encuentra, devuelve null
    }

    @Override
    public ListIF<String> getIDs() {

        ListIF<String> stringList = new List<String>();  // se crea la lista a devolver y
        // se crea el iterador para recorrerla entera (tipo Preorden)
        IteratorIF<PlayList> itPreorder = new BTreeIterator<PlayList>(playListBTree,0);
        while(itPreorder.hasNext()){                    // recorre la lista e inserta cada nodo
            stringList.insert(itPreorder.getNext().getPlayListID(),stringList.size()+1);
        }
        return stringList;
    }

    @Override
    public void createPlayList(String playListID) {

        if (playListBTree.getRoot() == null){                // si el árbol o nodo raíz esta vacío,
            playListBTree.setRoot(new PlayList(playListID)); // crea la lista en la raíz
        } else {
            BTree<PlayList> auxTree;
            auxTree = insert(playListID,playListBTree);    // si no, busca donde insertar
            if (auxTree != null){                          // se crea una lista auxiliar por si el
                playListBTree = auxTree;                   // el elemento se encuentra duplicado
            }                                              // si es asi, no hace nada
        }

    }

    /**
     * Inserta un nodo nuevo (lista de reproducción) y actualiza
     * el camino (árbol) con el nodo correspondiente y lo devuelve
     * @param playListID cadena de caracteres del nodo (lista) a insertar
     * @param node       nodo raíz con el camino a recorrer
     * @return Devuelve el árbol/camino actualizado
     */
    protected BTree<PlayList> insert(String playListID, BTree<PlayList> node){

        if (node.isEmpty()){                                                            // cuando no hay mas nodos,
            node.setRoot(new PlayList(playListID));                                     // lo inserta
        } else if(playListID.compareTo(node.getRoot().getPlayListID()) < 0){            // si es menor el nodo buscado,
            node.setLeftChild(insert(playListID,(BTree<PlayList>)node.getLeftChild())); // recorre la izquierda
        } else if(playListID.compareTo(node.getRoot().getPlayListID()) > 0){            // si es mayor,
            node.setRightChild(insert(playListID,(BTree<PlayList>)node.getRightChild()));// recorre la derecha
        } else {
            node = null;                                                                // elemento duplicado
        }
        return node;
    }

    @Override
    public void removePlayList(String playListID) {

        BTree<PlayList> auxTree;
        auxTree = remove(playListID,playListBTree);    // se crea una lista auxiliar por si
        if (auxTree != null){                          // no encuentra el elemento
            playListBTree = auxTree;                   // si no es asi, se elimina
        }

    }

    /**
     * Elimina un nodo buscado (lista de reproducción) y actualiza
     * el camino (árbol) con el nodo correspondiente y lo devuelve
     * @param playListID cadena de caracteres del nodo (lista) a eliminar
     * @param node       nodo raíz con el camino a recorrer
     * @return  Devuelve el árbol/camino actualizado
     */
    protected BTree<PlayList> remove(String playListID, BTree<PlayList> node){

        if (node.isEmpty()){                                                              // No existe el Elemento
            node = null;
        }else if(playListID.compareTo(node.getRoot().getPlayListID()) < 0){               // solo tiene hijo izquierdo,
            node.setLeftChild(remove(playListID,(BTree<PlayList>)node.getLeftChild()));   // y lo fija
        } else if(playListID.compareTo(node.getRoot().getPlayListID()) > 0){              // solo tiene hijo derecho,
            node.setRightChild(remove(playListID,(BTree<PlayList>)node.getRightChild())); // y lo fija
        } else if (!node.getLeftChild().isEmpty() && !node.getRightChild().isEmpty() ){   // tiene dos hijos,
            node.setRoot(findMin((BTree<PlayList>)node.getRightChild()).getRoot());       // escoge el menor del
            node.setRightChild(removeMin((BTree<PlayList>)node.getRightChild()));         // nodo derecho
        } else {
            // escoge el nodo que no está vacío
            node = (!node.getLeftChild().isEmpty()) ? (BTree<PlayList>)node.getLeftChild() : (BTree<PlayList>)node.getRightChild();
        }
        return node;
    }

    /**
     * Devuelve el nodo menor de un camino o árbol
     * @param node nodo raíz con el camino a recorrer
     * @return Devuelve el nodo menor
     */
    protected BTree<PlayList> findMin(BTree<PlayList> node){

        while(!node.getLeftChild().isEmpty()){              // si tiene hijo izquierdo,
            node = (BTree<PlayList>)node.getLeftChild();    // lo recorre hasta que no haya mas
        }
        return node;
    }

    /**
     * Elimina el nodo menor de un camino o árbol
     * @param node nodo raíz con el camino a recorrer
     * @return Devuelve el árbol/camino actualizado
     */
    protected BTree<PlayList> removeMin(BTree<PlayList> node){

        if(!node.getLeftChild().isEmpty()){                                     // si tiene hijo izquierdo,
            node.setLeftChild(removeMin((BTree<PlayList>)node.getLeftChild())); // lo recorre hasta que no haya mas
        } else {
            node = (BTree<PlayList>)node.getRightChild();                       // si no, escoge el derecho
        }
        return node;
    }
}
