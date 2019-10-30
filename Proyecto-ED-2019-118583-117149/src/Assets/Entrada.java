package Assets;

/**
 * Clase Entrada.
 * @author Juan Igancio Rapino y Matias Ilz.
 * 
 * @param <K>.
 * @param <V>.
 */
public class Entrada<K,V> implements Entry<K,V> {
	protected K key;
	protected V value;
	
	/**
	 * Crea una nueva entrada con clave y valor dados.
	 * 
	 * @param k Clave.
	 * @param v Valor.
	 */
	public Entrada(K k, V v){
		key = k;
		value = v;
	}
	
	/**
	 * Consulta la clave de la entrada.
	 * 
	 * @return Clave de la entrada.
	 */
	@Override
	public K getKey() {
		return key;
	}
	
	/**
	 * Consulta el valor de la entrada.
	 * 
	 * @return Valor de la entrada.
	 */
	@Override
	public V getValue() {
		return value;
	}
	
	/**
	 * Establece la clave de la entrada.
	 * 
	 * @param k Clave.
	 */
	public void setKey(K k) {
		key = k;
	}
	
	/**
	 * Establece el valor de la entrada.
	 * 
	 * @param v Valor.
	 */
	public void setValue(V v) {
		value = v;
	}
	
}
