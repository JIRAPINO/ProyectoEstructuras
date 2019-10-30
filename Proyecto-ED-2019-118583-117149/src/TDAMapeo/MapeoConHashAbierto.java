package TDAMapeo;

import java.util.Iterator;

import Assets.Entrada;
import Assets.Entry;

import Assets.Position;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDE;
import TDALista.PositionList;

public class MapeoConHashAbierto<K,V> implements Map<K,V>{
	protected PositionList<Entrada<K,V>> [] arregloBuckets;
	protected int size;
	protected int cantBuckets;
	protected static final float factorDeCarga = 0.8f;
	
	@SuppressWarnings("unchecked")
	public  MapeoConHashAbierto() {
		size = 0;
		cantBuckets = 11;		
		arregloBuckets = (PositionList<Entrada<K,V>> []) new ListaDE[cantBuckets] ;
		
		for(int i = 0;   i<cantBuckets;   i++) 
				arregloBuckets[i] = new ListaDE<Entrada<K,V>>();
		
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
	public V get(K key) throws InvalidKeyException {
		checkKey(key);
		
		int posBucket = funcionHash(key);
		boolean seguir = (arregloBuckets[posBucket].isEmpty()) ? false : true;
		V valor = null;
		
		if(seguir) {
				Iterator<Entrada<K,V>> it = arregloBuckets[posBucket].iterator();
				Entrada<K,V> entradaActual = null;
				
				while(it.hasNext() && seguir) {
						entradaActual = it.next();
						
						if(entradaActual.getKey().equals(key)) {
								seguir = false;
								valor = entradaActual.getValue();								
						}
						
					}
		}
		
		return valor;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		
		int posBucket = funcionHash(key);
		boolean seguir = (arregloBuckets[posBucket].isEmpty()) ? false : true; 
		V valorRetornar = null;
		Entrada<K,V> entradaNueva = new Entrada<K,V>(key, value);
		
		if(seguir) {
				Iterator<Position<Entrada<K,V>>> it = arregloBuckets[posBucket].positions().iterator();
				Position<Entrada<K,V>> entradaActual = null;
				
				while(it.hasNext() && seguir) {
						entradaActual = it.next();
						
						if(entradaActual.element().getKey().equals(key)) {	
							
								try {
										seguir = false;
										valorRetornar = entradaActual.element().getValue();
										arregloBuckets[posBucket].set(entradaActual, entradaNueva);
										
								} catch(InvalidPositionException e) {
										System.out.println("e: "+ e.getMessage());
								}
								
						}
				}
				
				if(seguir) {
						arregloBuckets[posBucket].addLast(entradaNueva);	
						size++;
				}
		}
		
		else {
				arregloBuckets[posBucket].addLast(entradaNueva);
				size++;
		}
		
		if( (size/cantBuckets) > factorDeCarga)
			reHash();
		
		return valorRetornar;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		
		int posBucket = funcionHash(key);
		boolean seguir = (arregloBuckets[posBucket].isEmpty()) ? false : true; 
		V valorRetornar = null;
		
		if(seguir) {
			
				Iterator<Position<Entrada<K,V>>> it = arregloBuckets[posBucket].positions().iterator();
				Position<Entrada<K,V>> entradaActual = null;
				
				while(it.hasNext() && seguir) {
						entradaActual = it.next();
						if(entradaActual.element().getKey().equals(key)) {
							
							try {
									seguir = false;
									valorRetornar = arregloBuckets[posBucket].remove(entradaActual).getValue();
									size--;
							} catch(InvalidPositionException e) {
									System.out.println("e: "+ e.getMessage());
							}
							
						}
				}
		}
			
		return valorRetornar;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> listaIterable = new ListaDE<K>();
		
		if( size != 0 ) {
			
				for(int i = 0;   i< cantBuckets;   i++) {
						for(Position<Entrada<K,V>> pos : arregloBuckets[i].positions()) {
								listaIterable.addLast(pos.element().getKey());
						}
				}
		}
		
		return listaIterable;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> listaIterable = new ListaDE<V>();
		
		if( size != 0 ) {
			
				for(int i = 0;   i< cantBuckets;   i++) {
						for(Position<Entrada<K,V>> pos : arregloBuckets[i].positions()) {
								listaIterable.addLast(pos.element().getValue());
						}
				}
		}
		
		return listaIterable;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> listaIterable = new ListaDE<Entry<K,V>>();
		
		if( size != 0 ) {
			
				for(int i = 0;   i< cantBuckets;   i++) {
						for(Position<Entrada<K,V>> pos : arregloBuckets[i].positions()) {
								listaIterable.addLast(pos.element());
						}
				}
		}
		
		return listaIterable;
	}
	
	
						//----------------- Metodos privados--------------------//
	
	private void checkKey(K key) throws InvalidKeyException {
		if(key == null)
				throw new InvalidKeyException("La clave pasada por aprametro es nula");
	}
	
	
	private int funcionHash(K key) throws InvalidKeyException {
		checkKey(key);
		return Math.abs(key.hashCode() % cantBuckets);
	}
	
	@SuppressWarnings("unchecked")
	private void reHash() {
		cantBuckets *= 2;
		PositionList<Entrada<K,V>> [] hashViejo = arregloBuckets;
		arregloBuckets =  (PositionList<Entrada<K,V>> []) new ListaDE [cantBuckets];
		
		for(int i = 0;   i<cantBuckets;   i++) 
				arregloBuckets[i] = new ListaDE<Entrada<K,V>>();
		
		for(int j = 0;   j<hashViejo.length;   j++ ) {
			
				PositionList<Entrada<K,V>> bucket = hashViejo[j];
				
				try {
					
						for(Position<Entrada<K,V>> pos : bucket.positions()) {
									Entrada<K,V> entrada = bucket.remove(pos);
									int posBucketNuevo = funcionHash(entrada.getKey());
									arregloBuckets[posBucketNuevo].addLast(entrada);
						}
						
				} catch(InvalidPositionException | InvalidKeyException e) {
						System.out.println("e: "+ e.getMessage());				
				}
				
		}
	}
	
	
	
}
