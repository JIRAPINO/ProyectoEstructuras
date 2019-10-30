package TDALista;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Assets.Position;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;

public class ElementIterator<E> implements Iterator<E> {
	protected PositionList<E> list;
	protected Position<E> cursor;
	
	public ElementIterator(PositionList<E> l) {
		list = l;
		
		if(list.isEmpty())
				cursor = null;
		
		else {
			
				try {
						cursor = list.first();
				} catch(EmptyListException e){
						System.out.println("e: "+ e.getMessage());
				}				
		}		
	}
	
	@Override
	public boolean hasNext() {
		return cursor != null;
	}

	@Override
	public E next() throws NoSuchElementException{
		if(cursor == null)
				throw new NoSuchElementException("No hay siguiente");
		
		E aRetornar = cursor.element();
		
			try {
					cursor = (cursor == list.last()) ? null : list.next(cursor);
			} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
					System.out.println("e: "+ e.getMessage());
			}
			
		return aRetornar;
	}

}
