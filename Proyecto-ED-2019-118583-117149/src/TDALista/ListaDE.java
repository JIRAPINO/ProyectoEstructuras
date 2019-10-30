package TDALista;

import java.util.Iterator;

import Assets.Position;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

public class ListaDE<E> implements PositionList<E> {
	protected DNodo<E> header;
	protected DNodo<E> trailer;
	protected int size;
	
	public ListaDE() {
		header = new DNodo<E>(null);
		trailer = new DNodo<E>(null);
		header.setSiguiente(trailer);
		trailer.setAnterior(header);
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if(size == 0)
				throw new EmptyListException("La lista se encuentra vacia");
		
		return header.getSiguiente();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if(size == 0)
			throw new EmptyListException("La lista se encuentra vacia");
		
		return trailer.getAnterior();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if(size == 0)
				throw new InvalidPositionException("La lista se encuentra vacia");
		
		DNodo<E> pos = checkPosition(p);
		
		if(pos == trailer.getAnterior())
				throw new BoundaryViolationException("No es posible pedir el nodo siguiente al ultimo nodo de la lista");
		
		return pos.getSiguiente();
	}

	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		if(size == 0)
				throw new InvalidPositionException("La lista se encuentra vacia");
		
		DNodo<E> pos = checkPosition(p);
		
		if(pos == header.getSiguiente())
				throw new BoundaryViolationException("No es posible pedir el nodo anterior al primer nodo de la lista");
		
		return pos.getAnterior();
	}

	@Override
	public void addFirst(E element) {
		DNodo<E> nuevo = new DNodo<E>(element);
		DNodo<E> siguiente = header.getSiguiente();
		
		nuevo.setAnterior(header);
		nuevo.setSiguiente(siguiente);
		header.setSiguiente(nuevo);
		siguiente.setAnterior(nuevo);		
		size++;
	}

	@Override
	public void addLast(E element) {
		DNodo<E> nuevo = new DNodo<E>(element);
		DNodo<E> anterior = trailer.getAnterior();
		
		nuevo.setSiguiente(trailer);
		nuevo.setAnterior(anterior);
		trailer.setAnterior(nuevo);
		anterior.setSiguiente(nuevo);	
		size++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		if(size == 0)
				throw new InvalidPositionException("La lista se encuentra vacia");
		
		DNodo<E> pos = checkPosition(p);
		DNodo<E> siguiente = pos.getSiguiente();
		DNodo<E> nuevo = new DNodo<E>(element);

		
		nuevo.setAnterior(pos);
		nuevo.setSiguiente(siguiente);
		pos.setSiguiente(nuevo);
		siguiente.setAnterior(nuevo);			
		size++;
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		if(size == 0)
				throw new InvalidPositionException("La lista se encuentra vacia");
		
		DNodo<E> pos = checkPosition(p);
		DNodo<E> nuevo = new DNodo<E>(element);
		DNodo<E> anterior = pos.getAnterior();
		
		nuevo.setSiguiente(pos);
		nuevo.setAnterior(anterior);
		pos.setAnterior(nuevo);
		anterior.setSiguiente(nuevo);	
		size++;
	}

	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		if(size == 0)
				throw new InvalidPositionException("La lista se encuentra vacia");
		
		DNodo<E> pos = checkPosition(p);
		DNodo<E> anterior = pos.getAnterior();
		DNodo<E> siguiente = pos.getSiguiente();
		E elemRemovido = pos.element();
		
		anterior.setSiguiente(siguiente);
		siguiente.setAnterior(anterior);
		
		pos.setElement(null);
		pos.setAnterior(null);
		pos.setSiguiente(null);
		size--;
		
		return elemRemovido;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		if(size == 0)
				throw new InvalidPositionException("La lista se encuentra vacia");
		
		DNodo<E> pos = checkPosition(p);
		E elemCambiado = pos.element();
		
		pos.setElement(element);
		
		return elemCambiado;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> listaIterable = new ListaDE<Position<E>>();
		
		try {
			
				if(size != 0) {
						Position<E> pos = first();
						
						while(pos != last()) {
								listaIterable.addLast(pos);
								pos = next(pos);
						}
						
						listaIterable.addLast(pos);
				}
				
		} catch(InvalidPositionException | BoundaryViolationException | EmptyListException e) {
			System.out.println("e: "+e.getMessage());
		}
		
		return listaIterable;
	}

	
									//---------------CHECK POSITON----------------//
	
	protected DNodo<E> checkPosition(Position<E> pos) throws InvalidPositionException{
		if(pos == null)
				throw new InvalidPositionException("La posicion pasada por parametro es nula");
		if(pos == header || pos == trailer)
				throw new InvalidPositionException("La posicion pasado por parametro hace referencia a los centinelas");
		
		DNodo<E> aRetornar = null;
		
		try {
				aRetornar = (DNodo<E>) pos;
		} catch(ClassCastException e) {
				e.printStackTrace();
		}
		
		return aRetornar;
	}
}
