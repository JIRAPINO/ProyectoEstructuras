package TDACola;

import Exceptions.EmptyQueueException;

public class ColaConArregloCircular<E> implements Queue<E> {
	protected E [] cola;
	protected int primero, ultimo;
	protected static final int longitud = 20;
	
	@SuppressWarnings("unchecked")
	public ColaConArregloCircular() {
		cola = (E[]) new Object[longitud];
		primero = 0;
		ultimo = 0;
	}
	
	@Override
	public int size() {
		return (cola.length - primero  + ultimo) % cola.length ;
	}

	@Override
	public boolean isEmpty() {
		return primero == ultimo;
	}

	@Override
	public E front() throws EmptyQueueException {
		if(primero == ultimo)
				throw new EmptyQueueException("La cola se encuentra vacia");
		
		return cola[primero];
	}

	@Override
	public void enqueue(E element) {
		if(((cola.length - primero  + ultimo) % cola.length) == (cola.length -1)) {
				E[] aux = reSize(primero);
				ultimo = (cola.length - primero  + ultimo) % cola.length;
				primero = 0;
				cola = aux;
		}
		
		cola[ultimo] = element;
		ultimo = (ultimo + 1) % cola.length;		
	}

	@Override
	public E dequeue() throws EmptyQueueException {
		if(primero == ultimo)
				throw new EmptyQueueException("La cola se encuentra vacia");
		
		E aux = cola[primero];
		cola[primero] = null;
		primero = (primero + 1) % cola.length;
		
		return aux;
	}

	private E[] reSize(int ini) {
		@SuppressWarnings("unchecked")
		E[] aux = (E[]) new Object[cola.length * 2];
		
		for(int k = 0;  k<(cola.length - primero  + ultimo) % cola.length;   k++) {
				aux[k] = cola[ini];
				cola[ini] = null;
				ini = (ini + 1) % cola.length;			
		}
		
		return aux;
	}
}
