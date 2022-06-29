package co.uniquindio.proyecto.model;

import java.io.Serializable;
import java.util.Iterator;

import co.uniquindio.proyecto.listas.ListaSimple;

public class Proyecto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ListaSimple<Proceso> listaProcesos;
	private Proceso proceso;
	
	public ListaSimple<Proceso> getListaProcesos() {
		return listaProcesos;
	}

	public void setListaProcesos(ListaSimple<Proceso> listaProcesos) {
		this.listaProcesos = listaProcesos;
	}
	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Proyecto() {
		listaProcesos = new ListaSimple<Proceso>();
	}
	
	public boolean crearProceso(String id, String nombre) {
		Proceso proceso =  new Proceso(id, nombre);
		listaProcesos.agregarfinal(proceso);
		return true;
	}
	
	public void modificarProceso(String idNueva, String nombreNuevo, String procSelec) {
		Iterator listProc = getListaProcesos().iterator();
		while (listProc.hasNext()) { 
			Proceso pro = (Proceso) listProc.next();
			if(procSelec.equals(pro.toString())) {
				pro.setId(idNueva);
				pro.setNombre(nombreNuevo);
			}
        }
	}
	
	public boolean crearActividad(String proceSelec, String descrip, String nombre, boolean oblig, int tipoAgregacion, Actividad actPrece, String obliS) {
		Iterator listProc = getListaProcesos().iterator();
		while (listProc.hasNext()) { 
			Proceso pro = (Proceso) listProc.next();
			if(proceSelec.equals(pro.toString())) {
				pro.agregarActividad(nombre, descrip, oblig, tipoAgregacion, actPrece, obliS);
			}
        }
		return true;
	}
	
	public boolean modificarActividad(String proceSelec, String actSelec, String descripNueva, String nombreNuevo, boolean obligNuevo, String obliSNuevo) {
		
		Iterator listProc = getListaProcesos().iterator();
		Proceso procesoA = null;
		Actividad activTarea = null;
		while (listProc.hasNext()) { 
			Proceso pro = (Proceso) listProc.next();
			if(proceSelec.equals(pro.toString())) {
				procesoA = pro;
				Iterator listActi = procesoA.getListaActividades().iterator();
				while (listActi.hasNext()) { 
					Actividad Act = (Actividad) listActi.next();
					if(actSelec.equals(Act.toString())) {
						Act.setDescripcion(descripNueva);
						Act.setNombre(nombreNuevo);
						Act.setObligatoria(obligNuevo);
						Act.setObligatoriaS(obliSNuevo);
					}
		        }	
			}
        }
		return true;
	}
	
	public boolean crearTarea(String proceSelec, Actividad actSelec, String desc, boolean obliga, double dura, int tipoAgregacion, int posAgregar, String oblS) {
		Iterator listProc = getListaProcesos().iterator();
		while (listProc.hasNext()) { 
			Proceso pro = (Proceso) listProc.next();
			if(proceSelec.equals(pro.toString())) {
				pro.agregarTarea(actSelec, desc, obliga, dura, tipoAgregacion, posAgregar, oblS);
			}
        }
		return true;
	}

	public boolean intercambiarCompleto(String procSelec, String act1selec, String act2selec) {
		Iterator listProc = getListaProcesos().iterator();
		while (listProc.hasNext()) { 
			Proceso pro = (Proceso) listProc.next();
			if(procSelec.equals(pro.toString())) {
				pro.intercambiarCompleto(act1selec, act2selec);
			}
        }
		return true;
	}

	public boolean intercambiarSinTareas(String procSelec, String act1selec, String act2selec) {
		Iterator listProc = getListaProcesos().iterator();
		while (listProc.hasNext()) { 
			Proceso pro = (Proceso) listProc.next();
			if(procSelec.equals(pro.toString())) {
				pro.intercambiarSinTareas(act1selec, act2selec);
			}
        }
		return true;
	}
}
