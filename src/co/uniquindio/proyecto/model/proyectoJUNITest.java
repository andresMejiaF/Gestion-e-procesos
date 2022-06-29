package co.uniquindio.proyecto.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class proyectoJUNITest {

	Proyecto proyecto = new Proyecto();
	
	@Test
	public void testCrearProceso() {
		
		boolean expected = proyecto.crearProceso("001", "Primer proceso");
		boolean actual = true;
		assertEquals(expected, actual);
		
	}

	@Test
	public void testCrearActividad() {
		boolean expected = proyecto.crearActividad("Proceso [ 001 | Primer proceso ]", "Descripcion test", "NombreActTest", true, 0, null, "SI");
		boolean actual = true;
		assertEquals(expected, actual);
	}

	@Test
	public void testCrearTarea() {
		Actividad actividad = new Actividad("NombreActTest", "Descripcion test", true, "SI");
		boolean expected = proyecto.crearTarea("Proceso [ 001 | Primer proceso ]", actividad, "SI", true, 1.0, 0, 0, "SI");
		boolean actual = true;
		assertEquals(expected, actual);
	}

	@Test
	public void testIntercambiarCompleto() {
		boolean expected = proyecto.intercambiarCompleto("Proceso [ 001 | Primer proceso ]", "Actividad [ NombreActTest | Descripcion test | Obligatoria:SI ]", "Actividad [ NombreActTest1 | Descripcion test1 | Obligatoria:SI ]");
		boolean actual = true;
		assertEquals(expected, actual);
	}

	@Test
	public void testIntercambiarSinTareas() {
		boolean expected = proyecto.intercambiarSinTareas("Proceso [ 001 | Primer proceso ]", "Actividad [ NombreActTest | Descripcion test | Obligatoria:SI ]", "Actividad [ NombreActTest1 | Descripcion test1 | Obligatoria:SI ]");
		boolean actual = true;
		assertEquals(expected, actual);
	}

}
