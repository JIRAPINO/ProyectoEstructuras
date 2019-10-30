package TDALista;

import Assets.Position;

public class DNodo<E> implements Position<E> {	
	private DNodo<E> anterior;
	private DNodo<E> siguiente;
	private E elemento;
	
	public DNodo(E elem, DNodo<E> ant, DNodo<E> sig) {
		anterior = ant;
		siguiente = sig;
		elemento = elem;
	}
	
	public DNodo(E elem) {
		this(elem, null, null);
	}
	
	@Override
	public E element() {
		return elemento;
	}
	
	public DNodo<E> getSiguiente() {
		return siguiente;
	}
	
	public DNodo<E> getAnterior() {
		return anterior;
	}
	
	public void setSiguiente(DNodo<E> sig) {
		siguiente = sig;
	}
	
	public void setAnterior(DNodo<E> ant) {
		anterior = ant;
	}
	
	public void setElement(E elem) {
		elemento = elem;
	}
}
