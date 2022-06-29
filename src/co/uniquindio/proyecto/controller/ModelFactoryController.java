package co.uniquindio.proyecto.controller;

import java.io.IOException;

import co.uniquindio.proyecto.model.Proceso;
import co.uniquindio.proyecto.model.Proyecto;
import co.uniquindio.proyecto.persistencia.Persistencia;

public class ModelFactoryController {
	Proyecto proyect;
	
	private static class SingletonHolder { 
		// El constructor de Singleton puede ser llamado desde aquí al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Método para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
	public ModelFactoryController() {
		inicializarDatos();
	}

	private void inicializarDatos() {
		// TODO Auto-generated method stub
		proyect = new Proyecto();
		proyect = Persistencia.cargarProyecto();
	}

	public Proyecto getProyect() {
		return proyect;
	}

	public void setProyect(Proyecto proyect) {
		this.proyect = proyect;
	}
	
}
