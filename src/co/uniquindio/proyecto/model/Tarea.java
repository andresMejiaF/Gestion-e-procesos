package co.uniquindio.proyecto.model;

import java.io.Serializable;

public class Tarea implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descripcion;
	private boolean obligatoria;
	private String obligatoriaS;
	private double duracion;
	
	public String getObligatoriaS() {
		return obligatoriaS;
	}
	public void setObligatoriaS(String obligatoriaS) {
		this.obligatoriaS = obligatoriaS;
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
	public double getDuracion() {
		return duracion;
	}
	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}
	public Tarea(String descripcion, boolean obligatoria, double duracion, String oblS) {
		super();
		this.descripcion = descripcion;
		this.obligatoria = obligatoria;
		this.duracion = duracion;
		this.obligatoriaS = oblS;
	}
	@Override
	public String toString() {
		return "Tarea: [ " + descripcion + " | Duracion: " + duracion + " minutos | Obligatoria: " + obligatoriaS + " ]";
	}
}
