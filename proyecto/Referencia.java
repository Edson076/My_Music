package proyecto;

import java.util.Objects;

/**
 * 
 * @author Yonathan
 */
public class Referencia implements Comparable<Referencia> {
    private String nombrebuscar;
    private long posicion;
    
    
    /**
     * constructor 
     * @param nombrebuscar 
     */
    public Referencia(String nombrebuscar) {
        this(nombrebuscar,0l);
    }
    
    /**
     * Constructor
     * @param nombrebuscar
     * @param posicion
     * @throws NullPointerException 
     */
    public Referencia(String nombrebuscar, long posicion) throws NullPointerException{
       this.nombrebuscar = nombrebuscar;
       this.posicion = posicion;
    }
    
    /**
     * Recibe el nombre por buscar
     * @return 
     */
    public String getNombrebuscar() {
        return nombrebuscar;
    }
    
    /**
     * Actualiza el nombre
     * @param nombrebuscar 
     */
    public void setNombrebuscar(String nombrebuscar) {
        this.nombrebuscar = nombrebuscar;
    }
    
    /**
     * recibe la posicion
     * @return 
     */
    public long getPosicion() {
        return posicion;
    }

    /**
     * actualiza la posicion
     * @param posicion 
     */
    public void setPosicion(long posicion) {
        this.posicion = posicion;
    }

    /**
     *  COMPARA EL NOMBRE
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Referencia o) {
        return this.nombrebuscar.compareTo(o.getNombrebuscar());
    }

    /**
     * VERIFICA QUE SEAN IGUALES
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Referencia) {
            return this.nombrebuscar.equals(((Referencia)o).getNombrebuscar());
        }            
        else if (o instanceof String) {
            return this.nombrebuscar.equals((String)o);
        }
        else
            return false;
    }
    
    /**
     * HASH
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.nombrebuscar);
        return hash;
    }
}
