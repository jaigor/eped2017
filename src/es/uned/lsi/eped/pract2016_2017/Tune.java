package es.uned.lsi.eped.pract2016_2017;

/**
 * Representación de la clase correspondiente a Tune
 * @author  Igor Quintela
 * @version 20/03/2017. */
public class Tune implements TuneIF{

    private String t;
    private String a;
    private String g;
    private String al;
    private int y;
    private int d;

    /**
     * Constructor de Tune con los atributos relacionados con la canción
     * @param t     título de la canción
     * @param a     autor de la canción
     * @param g     género de la canción
     * @param al    nombre del álbum al que pertenece la canción
     * @param y     año de publicación de la canción
     * @param d     duración, en segundos, de la canción
     * @pre -t != “” && a != “” && g != “” && al != “” && y > 0 && d > 0*/
    Tune(String t, String a, String g, String al, int y, int d) {

        // Si alguno de los parametros pasados está vacío o null,
        // lanza excepcion
        if (emptyChain(t)) {
            throw new NullPointerException("Titulo");
        }
        if (emptyChain(a)) {
            throw new NullPointerException("Autor");
        }
        if (emptyChain(g)) {
            throw new NullPointerException("Genero");
        }
        if (emptyChain(al)) {
            throw new NullPointerException("Album");
        }
        if (lessZero(y)) {
            throw new NullPointerException("Año");
        }
        if (lessZero(d)) {
            throw new NullPointerException("Duracion");
        }
        this.t = t.toLowerCase();
        this.a = a.toLowerCase();
        this.g = g.toLowerCase();
        this.al = al.toLowerCase();
        this.y = y;
        this.d = d;
    }

    /**
     * Comprueba que la cadena es vacía o null
     * @param s cadena de caracteres a comprobar
     * @return  true si cadena vacia o null
     */
    public boolean emptyChain(String s){ return ((s==null) || s.equals("")); }

    /**
     * Comprueba si el numero es negativo
     * @param i número entero a comprobar
     * @return  true si es negativo
     */
    public boolean lessZero(int i){ return i < 0; }

    @Override
    /**
    * Implementación de la Consulta que, dados unos criterios
    * de búsqueda (ver Query), indica si la canción
    * los cumple todos o no. */
    public boolean match(QueryIF q) {
        // se inicia a true la búsqueda
        boolean isMatch = true;
        // si se encuentra que alguna no encaja, se marca false
        if (!emptyChain(q.getTitle()) && !t.equals(q.getTitle())){
            isMatch = false;
        }
        if (!emptyChain(q.getAuthor()) && !a.equals(q.getAuthor())){
            isMatch = false;
        }
        if (!emptyChain(q.getGenre()) && !g.equals(q.getGenre())){
            isMatch = false;
        }
        if (!emptyChain(q.getAlbum()) && !al.equals(q.getAlbum())){
            isMatch = false;
        }
        if (!lessZero(q.getMin_year()) && q.getMin_year() > y){
            isMatch = false;
        }
        if (!lessZero(q.getMax_year()) && q.getMax_year() < y){
            isMatch = false;
        }
        if (!lessZero(q.getMin_duration()) && q.getMin_duration() > d){
            isMatch = false;
        }
        if (!lessZero(q.getMax_duration()) && q.getMax_duration() < d){
            isMatch = false;
        }
        return isMatch;
    }

}
