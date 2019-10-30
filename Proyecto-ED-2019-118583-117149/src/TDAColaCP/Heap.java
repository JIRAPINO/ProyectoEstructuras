package TDAColaCP;

import java.util.Comparator;


import Assets.Entrada;
import Assets.Entry;
import Exceptions.EmptyPriorityQueueException;
import Exceptions.InvalidKeyException;

/**
 * Clase Heap.
 * @author Juan Igancio Rapino y Matias Ilz.
 *
 * @param <K>.
 * @param <V>.
 */
public class Heap<K,V> implements PriorityQueue<K,V> {
		
	protected  Entrada<K,V> [] elems;
	protected Comparator<K> comp;
	protected int size;
	
	/**
	 * Crea un nuevo Heap con Comparador y cantidad de entradas dados.
	 * 
	 * @param comp Comparador.
	 * @param maxEntradas Cantidad de entradas.
	 */
	@SuppressWarnings("unchecked")
	public  Heap(Comparator<K> comp, int maxEntradas) {
		elems = (Entrada<K,V> []) new Entrada[maxEntradas];
		this.comp = comp;			
		size = 0;
	}	
	
	/**
	 * Crea un nuevo Heap con un comparador dado y un maximo de 1000 entradas.
	 * 
	 * @param comp Comparador.
	 */
	public Heap(Comparator<K> comp) {
		this(comp, 1000);
	}
	
	/**
	 * Consulta la cantidad de elementos de la cola.
	 * 
	 * @return Cantidad de elementos de la cola.
	 */
	@Override
	public int size() {
		return size;
	}
	
	/** Consulta si la cola esta vacia.
	 * 
	 * @return Verdadero si la cola esta vacia, falso en caso contrario.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Consulta por la entrada con menor prioridad de la cola.
	 * 
	 * @return Entrada con menor prioridad.
	 * @throws EmptyPriorityQueueException si la cola se encuentra vacia.
	 */
	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if(isEmpty())
				throw new EmptyPriorityQueueException("El heap se encuentra vacio");
		
		return elems[1];
	}
	
	/**
	 * Inserta un par clave-valor y devuelve la entrada creada.
	 * 
	 * @param key Clave de la entrada a insertar.
	 * @param value Valor de la entrada a insertar.
	 * 
	 * @return Entrada creada.
	 * @throws InvalidKeyException si la clave pasada por parametro es invalida.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		
		if (size == elems.length - 1) {
			Entrada<K, V>[] A = (Entrada<K, V>[]) new Entrada[elems.length * 2];
			
			for (int i = 1; i < elems.length; i++)
					A[i] = elems[i];
			
			elems = A;
		}
		
		Entrada<K,V> entrada = new Entrada<K,V>(key, value);
		size++;
		elems[size] = entrada;
		
		int i = size;
		boolean seguir = true;
		
		while(i>1 && seguir) {
				Entrada<K,V> elemActual = elems[i];
				Entrada<K,V> elemPadre= elems[i/2];
				
				if(comp.compare(elemActual.getKey(), elemPadre.getKey()) < 0) {
						Entrada<K,V> aux = elems[i];
						elems[i] = elems[i/2];
						elems[i/2] = aux;
						i /= 2;
				}				
				else 
						seguir = false;						
		}
		
		return entrada;
	}
	
	/**
	 * Remueve y devuelve la entrada de menor prioridad de la cola.
	 * 
	 * @return Entrada con menos prioridad.
	 * @throws EmptyPriorityQueueException si la cola esta vacia.
	 */
	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {		
		Entry<K,V> entrada = min();
		
		if(size == 1) {
				elems[1] = null;
				size--;
		}
		else {
				elems[1] = elems[size];
				elems[size] = null;
				size--;
				
				int i = 1;
				boolean seguir = true;
				
				while(seguir) {
						int posHijoIzquierdo = i*2;
						int posHijoDerecho = i*2+1;
						boolean tieneHijoIzquierdo = posHijoIzquierdo <=  size;
						boolean tieneHijoDerecho = posHijoDerecho <= size;
						
						if(!tieneHijoIzquierdo) 
								seguir = false;
						else {
								int minimo;
								
								if(tieneHijoDerecho) {		
									
										if(comp.compare(elems[posHijoIzquierdo].getKey(), elems[posHijoDerecho].getKey()) < 0) 
												minimo = posHijoIzquierdo;
										else
												minimo = posHijoDerecho;
										
								}
								else
										minimo = posHijoIzquierdo;
															
								if(comp.compare(elems[i].getKey(), elems[minimo].getKey()) > 0) {
										Entrada<K,V> aux = elems[i];
										elems[i] = elems[minimo];
										elems[minimo] = aux;
										i = minimo;
								}
								else
										seguir = false;
								
						}
				}		
		}	
		
		return entrada;
	}

	//------------------------ Metodos privados -----------------------//
	
	private void checkKey(K key) throws InvalidKeyException {
		if(key == null)
				throw new InvalidKeyException("La clave pasada por parametro es nula");
	}
}
