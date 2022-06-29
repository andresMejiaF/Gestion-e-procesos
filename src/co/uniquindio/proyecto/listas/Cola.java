package co.uniquindio.proyecto.listas;

import java.io.Serializable;

public class Cola<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Nodo<T> nodoPrimero, nodoUltimo;
	public int tamanio;

	/**
	 * Agrega un elemento en la Cola
	 * @param dato elemento a guardar en la Cola
	 */
	public void encolar(T dato) {

		Nodo<T> nodo = new Nodo<>(dato);

		if(estaVacia()) {
			nodoPrimero = nodoUltimo = nodo;
		}else {
			nodoUltimo.setSiguienteNodo(nodo);
			nodoUltimo = nodo;
		}

		tamanio++;
	}

	/**
	 * Retorna y elimina el elemento que está al incio de la Cola
	 * @return Primer elemento de la Cola
	 */
	public T desencolar() {

		if(estaVacia()) {
			throw new RuntimeException("La Cola está vacía");
		}

		T dato = nodoPrimero.getValorNodo();
		nodoPrimero = nodoPrimero.getSiguienteNodo();

		if(nodoPrimero==null) {
			nodoUltimo = null;
		}

		tamanio--;
		return dato;
	}

	/**
	 * Verifica si la Cola está vacía
	 * @return true si está vacía
	 */
	public boolean estaVacia() {
		return nodoPrimero == null;
	}



	/**
	 * Borra completamente la Cola
	 */
	public void borrarCola() {
		nodoPrimero = nodoUltimo = null;
		tamanio = 0;
	}

	/**
	 * @return the primero
	 */
	public Nodo<T> getPrimero() {
		return nodoPrimero;
	}

	/**
	 * @return the ultimo
	 */
	public Nodo<T> getUltimo() {
		return nodoUltimo;
	}

	/**
	 * @return the tamano
	 */
	public int getTamano() {
		return tamanio;
	}
	
	public String toString(){
 		Nodo<T> c=nodoPrimero;
 		String s="";
 		while(c!=null){
 			s=s+c.getValorNodo();
 			c=c.getSiguienteNodo();
 			s += "\n";
 		}
	 return s;
 	}
	
	//Metodo insertarIndice
	public void insertarIndice(T dato, int indice){
		if(indiceValido(indice)) {
			
			if(indice==tamanio) {
				encolar(dato);
			}else {
				Nodo<T> nuevo=new Nodo<T>(dato);
				Nodo<T> actual = obtenerNodo(indice);
				nuevo.setSiguienteNodo(actual.getSiguienteNodo());
				actual.setSiguienteNodo(nuevo);
//				nodoUltimo.setSiguienteNodo(nuevo);
				nodoUltimo=nodoUltimo.getSiguienteNodo();
				tamanio++;
			}
		}
	}
	
	//Obtener nodo
	private Nodo<T> obtenerNodo(int indice) {
			
			if(indice>=0 && indice<tamanio) {
			
				Nodo<T> nodo = nodoPrimero;
				
				for (int i = 0; i < indice; i++) {
					nodo = nodo.getSiguienteNodo();
				}
			
				return nodo;			
			}
			
			return null;
		}
	
	//Verificar si indice es valido
		private boolean indiceValido(int indice) {		
			if( indice>=0 && indice<tamanio ) {
				return true;
			}			
			throw new RuntimeException("Índice no válido");
		}
		
		
}