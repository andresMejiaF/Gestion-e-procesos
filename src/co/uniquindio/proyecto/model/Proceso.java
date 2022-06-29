package co.uniquindio.proyecto.model;

import java.io.Serializable;
import java.util.Iterator;

import co.uniquindio.proyecto.listas.ListaDoble;
import co.uniquindio.proyecto.listas.ListaSimple;

public class Proceso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String nombre;
	private Actividad ultimaActividad;
	private ListaDoble<Actividad> listaActividades;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ListaDoble<Actividad> getListaActividades() {
		return listaActividades;
	}
	public void setListaActividades(ListaDoble<Actividad> listaActividades) {
		this.listaActividades = listaActividades;
	}
	
	public Proceso(String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		listaActividades = new ListaDoble<Actividad>();
	}
	@Override
	public String toString() {
		return "Proceso [ " + id + " | " + nombre + " ]";
	}
	
	public void agregarActividad(String nombre, String descripcion, boolean oblig, int tipoAgregacion, Actividad actPrece, String obliS) {
		Actividad actividad = new Actividad(nombre, descripcion, oblig, obliS);
		if(tipoAgregacion==0) { //Al final
			listaActividades.agregarfinal(actividad);
		}
		
		if(tipoAgregacion==1) { //Despues de la ultima creada
			if(listaActividades.estaVacia()) {
				listaActividades.agregarfinal(actividad);
			}else if(ultimaActividad.equals(listaActividades.obtenerValorNodo(listaActividades.getTamanio()-1))) {
					listaActividades.agregarfinal(actividad);
				}else {
					int pos = listaActividades.obtenerPosicionNodo(ultimaActividad);
					pos++;
					listaActividades.agregar(actividad, pos);
				}

		}
		
		if(tipoAgregacion==2) { //Precede
			if(listaActividades.estaVacia()) {
				listaActividades.agregarfinal(actividad);
			}else if(actPrece.equals(listaActividades.obtenerValorNodo(listaActividades.getTamanio()-1))) {
				listaActividades.agregarfinal(actividad);
				}else {
					int pos = listaActividades.obtenerPosicionNodo(actPrece);
					pos++;
					listaActividades.agregar(actividad, pos);
				}
		}
		ultimaActividad = actividad;
	}
	public void agregarTarea(Actividad actSelec, String desc, boolean obliga, double dura, int tipoAgregacion, int posAgregar, String oblS) {
		//Ahora buscara la actividad dentro de la lista de actividades de este proceso seleccionado
		Iterator listaActi = listaActividades.iterator();
		while (listaActi.hasNext()) { 
			Actividad act = (Actividad) listaActi.next();
			if(actSelec.toString().equals(act.toString())) {
				//En este punto ya se tiene la actividad correcta seleccionada.
				act.agregarTarea(desc, obliga, dura, tipoAgregacion, posAgregar, oblS);
			}
        }
	}
	
	public void intercambiarCompleto(String act1selec, String act2selec) {
		Actividad act1Inter = null;
		Actividad act2Inter = null;
		Actividad aux =null;
		int posAct1=0;
		int posAct2=0;
		Iterator listaActi = listaActividades.iterator();
		while (listaActi.hasNext()) { 
			Actividad act = (Actividad) listaActi.next();
			if(act1selec.toString().equals(act.toString())) {
				act1Inter=act;
				aux=act;
				posAct1=listaActividades.obtenerPosicionNodo(act);
			}
			if(act2selec.toString().equals(act.toString())) {
				act2Inter=act;
				posAct2=listaActividades.obtenerPosicionNodo(act);
			}
        }
		act1Inter=act2Inter;
		act2Inter=aux;
		listaActividades.modificarNodo(posAct1, act1Inter);
		listaActividades.modificarNodo(posAct2, act2Inter);
	}
	public void intercambiarSinTareas(String act1selec, String act2selec) {
		Actividad act1Inter = null;
		Actividad act2Inter = null;
		Actividad aux =null;
		String nombre="";
		String desc="";
		String obS="";
		boolean ob=false;
		int posAct1=0;
		int posAct2=0;
		Iterator listaActi = listaActividades.iterator();
		while (listaActi.hasNext()) { 
			Actividad act = (Actividad) listaActi.next();
			if(act1selec.toString().equals(act.toString())) {
				act1Inter=act;
				nombre=act.getNombre();
				desc=act.getDescripcion();
				obS=act.getObligatoriaS();
				ob=act.isObligatoria();
				posAct1=listaActividades.obtenerPosicionNodo(act);
			}
			
			if(act2selec.toString().equals(act.toString())) {
				act2Inter=act;
				posAct2=listaActividades.obtenerPosicionNodo(act);
			}
        }
		aux=act1Inter;
		act1Inter.setNombre(act2Inter.getNombre());
		act1Inter.setDescripcion(act2Inter.getDescripcion());
		act1Inter.setObligatoria(act2Inter.isObligatoria());
		act1Inter.setObligatoriaS(act2Inter.getObligatoriaS());
		
		act2Inter.setNombre(nombre);
		act2Inter.setDescripcion(desc);
		act2Inter.setObligatoria(ob);
		act2Inter.setObligatoriaS(obS);
		
		listaActividades.modificarNodo(posAct1, act1Inter);
		listaActividades.modificarNodo(posAct2, act2Inter);
	}
}
