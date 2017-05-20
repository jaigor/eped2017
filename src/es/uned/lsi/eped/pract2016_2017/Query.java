package es.uned.lsi.eped.pract2016_2017;

/**
 * Representación de Query o Consulta,
 * clase usada para realizar
 * una búsqueda en el repositorio
 *
 * @author  Igor Quintela
 * @version 20/03/2017.
 */
public class Query implements QueryIF{

    private String t;
    private String a;
    private String g;
    private String al;
    private int minYear;
    private int maxYear;
    private int minDuration;
    private int maxDuration;

    /**
     * Criterios para realizar una búsqueda en el repositorio
     * @param t            título de la canción
     * @param a            autor de la canción
     * @param g            género de la canción
     * @param al           álbum al que pertenece la canción
     * @param minYear      primer año del intervalo en el que se publicó la canción
     * @param maxYear      último año del intervalo en el que se publicó la canción
     * @param minDuration  mínimo valor de la duración, en segundos, de la canción
     * @param maxDuration  máximo valor de la duración, en segundos, de la canción
     */
    Query(String t, String a, String g, String al, int minYear, int maxYear, int minDuration, int maxDuration) {

        this.minYear = minYear;
        this.maxYear = maxYear;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;

        /*
         * Comprobar si el campo rellenado se ha
         * dejado vacío, y si es así colocar
         * el contenido especificado ("" string y -1 a int)
         */
        if (emptyChain(t)){
            this.t = "";
        } else {
            this.t = t.toLowerCase();
        }
        if (emptyChain(a)){
            this.a = "";
        } else {
            this.a = a.toLowerCase();
        }
        if (emptyChain(g)){
            this.g = "";
        } else {
            this.g = g.toLowerCase();
        }
        if (emptyChain(al)){
            this.al = "";
        } else {
            this.al = al.toLowerCase();
        }
        if (lessZero(minYear)){
            this.minYear = -1;
        }
        if (lessZero(maxYear)){
            this.maxYear = -1;
        }
        if (lessZero(minDuration)){
            this.minDuration = -1;
        }
        if (lessZero(maxDuration)){
            this.maxDuration = -1;
        }
    }

    /**
     * Comprueba que la cadena es vacía o null
     * @param s cadena de caracteres a comprobar
     * @return  true si cadena vacia
     */
    public boolean emptyChain(String s){ return ((s==null) || s.equals("")); }

    /**
     * Comprueba si el numero es negativo
     * @param i número entero a comprobar
     * @return  true si es negativo
     */
    public boolean lessZero(int i){
        return i < 0;
    }

    /**
     * Implementación de los métodos get usados
     * para devolver los criterios
      */
    @Override
    public String getTitle() {
        return t;
    }

    @Override
    public String getAuthor() {
        return a;
    }

    @Override
    public String getGenre() {
        return g;
    }

    @Override
    public String getAlbum() {
        return al;
    }

    @Override
    public int getMin_year() {
        return minYear;
    }

    @Override
    public int getMax_year() {
        return maxYear;
    }

    @Override
    public int getMin_duration() {
        return minDuration;
    }

    @Override
    public int getMax_duration() {
        return maxDuration;
    }

}
