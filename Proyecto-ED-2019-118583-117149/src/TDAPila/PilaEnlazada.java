package TDAPila;

import Exceptions.EmptyStackException;

public class PilaEnlazada<E> implements Stack<E> {
	protected Nodo<E> head;
	protected int size;
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E top() throws EmptyStackException {
		if(size == 0)
				throw new EmptyStackException("La pila se encuentra vacia");
		
		return head.getElement();
	}

	@Override
	public void push(E element) {
		Nodo<E> nuevo = new Nodo<E>(element);
		
		nuevo.setSiguiente(head);
		head = nuevo;
		
		size++;	
	}

	@Override
	public E pop() throws EmptyStackException {
		if(size == 0)
			throw new EmptyStackException("La pila se encuentra vacia");
		
		E element = head.getElement();
		head = head.getSiguiente();
		
		/*
		 * Queria que el head a eliminar no quede con referencias luego de realizar la funcion pop()
		head.setElement(null);
		Nodo<E> aux = head.getSiguiente();
		head.setSiguiente(null);
		head = aux;
		aux = null; */
			
		size--;
		return element;
	}
}
