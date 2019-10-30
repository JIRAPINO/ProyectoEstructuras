package TDAColaCP;

/**
 * Clase Comparador
 * @author Juan Igancio Rapino y Matias Ilz
 *
 * @param <E extends Comparable<E>>
 */
public class Comparador <E extends Comparable<E>> implements java.util.Comparator<E> {
	
	/**
	 * Compara dos elementos y devuelve un numero positivo si el primer elemento es mayor al segundo. 
	 * Devuelve un numero negativo si el primer elemento es menor al segundo.
	 * Si son iguales, devuelve 0.
	 * 	
	 * @param a Primer elemento.
	 * @param b Segundo elemento.
	 * 
	 * @return un numero positivo si el primer elemento es mayor al segundo; o negativo si el primer elemento es 
	 * menor al segundo; o 0 si son iguales.
	 */
	public int compare( E a, E b ) {
			return a.compareTo( b );
	}
}


