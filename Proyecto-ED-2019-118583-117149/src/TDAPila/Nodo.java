package TDAPila;

/**
 * Clase Nodo.
 * @author Juan Igancio Rapino y Matias Ilz.
 *
 * @param <E>.
 */
public class Nodo<E> {	
	
	private Nodo<E> siguiente;
	private E elemento;
	
	/**
	 * Crea un nuevo nodo con elemento y nodo dados.
	 * 
	 * @param elem Elemnto.
	 * @param sig Nodo sucesor.
	 */
	public Nodo(E elem, Nodo<E> sig) {
		siguiente = sig;
		elemento = elem;
	}
	
	/**
	 * Crea un nuevo nodo con un elemento dado.
	 * 
	 * @param elem Elemento.
	 */
	public Nodo(E elem) {
		this(elem, null);
	}
	
	/**
	 * Consulta el nodo sucesor a este.
	 * 
	 * @return Nodo sucesor a este.
	 */
	public Nodo<E> getSiguiente() {
		return siguiente;
	}
	
	/**
	 * Consulta el elemento del nodo.
	 * 
	 * @return elemento del nodo.
	 */
	public E getElement() {
		return elemento;
	}
	
	/**
	 * Establece el nodo sucesor a este.
	 * 
	 * @param sig Siguiente.
	 */
	public void setSiguiente(Nodo<E> sig) {
		siguiente = sig;
	}
	
	/**
	 * Establece el elemento del nodo
	 * 
	 * @param elem Elemento.
	 */
	public void setElement(E elem) {
		elemento = elem;
	}
}
