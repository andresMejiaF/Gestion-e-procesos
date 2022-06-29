package co.uniquindio.proyecto.persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import co.uniquindio.proyecto.model.Proyecto;

public class Persistencia {

	public static final String RUTA_ARCHIVO_XML = "C:/td/persistencia/proyecto/proyecto.xml";
	public static final String RUTA_ARCHIVO_BINARIO = "C:/td/persistencia/proyecto/ProyectoBinario.txt";
	
	public static Proyecto cargarModelo() throws IOException {
		Object objeto = ArchivoUtil.cargarArchivo(RUTA_ARCHIVO_BINARIO);
		Proyecto proyecto = (Proyecto) objeto;
		return proyecto;
	}
	
	public static void serializarBinario(String ruta, Object objeto) throws IOException {
		FileOutputStream fileOutputS = new FileOutputStream(ruta);
		ObjectOutputStream objectOutputS = new ObjectOutputStream(fileOutputS);
		objectOutputS.writeObject(objeto);
		fileOutputS.close();
		objectOutputS.close();
	}
	
	public static Proyecto cargarProyecto() {
		Proyecto proyectAux = null;
		try {
			proyectAux = (Proyecto) ArchivoUtil.cargarObjeto(RUTA_ARCHIVO_BINARIO);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return proyectAux;
	}
}
