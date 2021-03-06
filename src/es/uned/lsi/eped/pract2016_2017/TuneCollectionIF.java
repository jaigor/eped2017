package es.uned.lsi.eped.pract2016_2017;

/**
 *  Representación del repositorio de canciones */
public interface TuneCollectionIF {
    /**
     * Devuelve el tamaño de la colección de canciones
     * @return      -devuelve un entero con el número de canciones */
    public int size();

    /**
     * Devuelve una canción a partir de su identificador
     * @param ID    -un entero con el identificador de la canción a recuperar
     * @pre         -0<=ID<this.size()
     * @return      -un objeto TuneIF con la canción con el identificador recibido */
    public TuneIF getTune(int ID);
}
