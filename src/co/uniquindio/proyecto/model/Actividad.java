package co.uniquindio.proyecto.model;

import java.io.Serializable;
import co.uniquindio.proyecto.listas.Cola;
import co.uniquindio.proyecto.listas.Nodo;
import javafx.scene.control.Alert;


public class Actividad implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String descripcion;
	private boolean obligatoria;
	private String obligatoriaS;
	private Cola<Tarea> colaTareas;
	private Cola<Tarea> colaAux;
	public String getObligatoriaS() {
		return obligatoriaS;
	}
	public void setObligatoriaS(String obligatoriaS) {
		this.obligatoriaS = obligatoriaS;
	}
	public Cola<Tarea> getColaTareas() {
		return colaTareas;
	}
	public void setColaTareas(Cola<Tarea> colaTareas) {
		this.colaTareas = colaTareas;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isObligatoria() {
		return obligatoria;
	}
	public void setObligatoria(boolean obligatoria) {
		this.obligatoria = obligatoria;
	}
	public Cola<Tarea> getListaTareas() {
		return colaTareas;
	}
	public void setListaTareas(Cola<Tarea> colaTareas) {
		this.colaTareas = colaTareas;
	}
	
	public Actividad(String nombre, String descripcion, boolean obligatoria, String obliS) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.obligatoria = obligatoria;
		colaTareas = new Cola<Tarea>();
		this.obligatoriaS = obliS;
	}
	@Override
	public String toString() {
		return "Actividad: [ " + nombre + " | " + descripcion + " | Obligatoria:" + obligatoriaS + " ]";
	}
	
	public void agregarTarea(String desc, boolean obliga, double dura, int tipoAgregacion, int posAgregar, String oblS) {
		Tarea tarea = new Tarea(desc, obliga, dura, oblS);
		if(colaTareas.estaVacia()) {
			colaTareas.encolar(tarea);
		}else {
			if(tipoAgregacion==0) {//Agregar al final
				if(colaTareas.getUltimo().getValorNodo().isObligatoria()==false && tarea.isObligatoria()==false) {
					imprimirWarning("No se puede agregar una tarea opcional justo despues de otra opcional\nOperacion cancelada");
				}else {
					colaTareas.encolar(tarea);
					String s = colaTareas.toString();
				}
			}
			if(tipoAgregacion==1) {//Agregar en posicion X
				int aux=0;
				boolean valido=true;
				colaAux = new Cola<Tarea>();
				Nodo<Tarea> tp = colaTareas.getPrimero();
				while(tp!=null && valido==true) {
					if(tp.getValorNodo().isObligatoria()==false && tarea.isObligatoria()==false) {
						imprimirWarning("No se puede agregar una tarea opcional justo despues de otra opcional\nOperacion cancelada");
						valido=false;
					}else {
						if(posAgregar==aux) {
							colaAux.encolar(tarea);
							colaAux.encolar(tp.getValorNodo());
						}else {
							colaAux.encolar(tp.getValorNodo());
						}
						tp=tp.getSiguienteNodo();
						aux++;
					}
				}
				if(posAgregar > colaTareas.getTamano() || posAgregar < 0) {
					imprimirWarning("Posicion invalida");
				}
				if(valido==true) {
					colaTareas=colaAux;
				}
			}
		}
		String s=colaTareas.toString();
		String print = "Lista de tareas de la actividad: \n";
		print+=s;
		imprimirInfo(print);
	}
	
	public int getMaxDuracion() {
		Nodo<Tarea> c=colaTareas.getPrimero();
 		int dura=0;
 		while(c!=null){
 			dura+=c.getValorNodo().getDuracion();
 			c=c.getSiguienteNodo();
 		}
 		return dura;
	}
	
	public int getMinDuracion() {
		Nodo<Tarea> c=colaTareas.getPrimero();
 		int dura=0;
 		while(c!=null){
 			if(c.getValorNodo().isObligatoria()) {
 				dura+=c.getValorNodo().getDuracion();
 			}
 			c=c.getSiguienteNodo();
 		}
 		return dura;
	}
	
	public String getTareas() {
		Nodo<Tarea> c=colaTareas.getPrimero();
 		String lista="";
 		while(c!=null){
 			lista+=c.getValorNodo().getDescripcion();
 			lista+="\n";
 			c=c.getSiguienteNodo();
 		}
 		return lista;
	}
	
	public void imprimirInfo(String mens) {
		Alert mensaje;
        mensaje = new Alert (Alert.AlertType.INFORMATION);
        mensaje.setContentText(mens);
        mensaje.showAndWait();
	}

	public void imprimirWarning(String mens) {
		Alert mensaje;
        mensaje = new Alert (Alert.AlertType.WARNING);
        mensaje.setContentText(mens);
        mensaje.showAndWait();
	}
}
