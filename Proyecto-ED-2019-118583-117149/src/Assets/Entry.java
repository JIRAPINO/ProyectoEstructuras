package Assets;

/**
 * Interface Entry.
 * @author Juan Igancio Rapino y Matias Ilz.
 */

public interface Entry<K,V> {
	
	/**
	 * Consulta la clave de la entrada.
	 * 
	 * @return clave de la entrada.
	 */
	public K getKey(); 
	
	/**
	 * Consulta el valor de la entrada.
	 * 
	 * @return valor de la entrada.
	 */
	public V getValue(); 
}
