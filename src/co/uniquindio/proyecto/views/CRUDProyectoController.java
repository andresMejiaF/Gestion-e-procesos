package co.uniquindio.proyecto.views;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import co.uniquindio.proyecto.application.ProyectoApplication;
import co.uniquindio.proyecto.controller.ModelFactoryController;
import co.uniquindio.proyecto.listas.Nodo;
import co.uniquindio.proyecto.model.Actividad;
import co.uniquindio.proyecto.model.Proceso;
import co.uniquindio.proyecto.model.Proyecto;
import co.uniquindio.proyecto.model.Tarea;
import co.uniquindio.proyecto.persistencia.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CRUDProyectoController {
	private boolean banderaActividad = true;
	private boolean banderaActividadMod = true;
	private boolean banderaActividadTarea = true;
	private boolean banderaActividadConsuTarea = true;
	private boolean banderaActividadIntercam = true;
	private ProyectoApplication proyectoApplication;
	private ModelFactoryController modelFactoryController;

	public CRUDProyectoController() {
		modelFactoryController = new ModelFactoryController();
	}
	
	public void setMain(ProyectoApplication proyectoApplication) {
		this.proyectoApplication = proyectoApplication;
	}
	
	@FXML
	private void initialize() {
		idP.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombreP.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		ListaProcesos.setItems(ltvProceso);
		actualizarTablaProcesos();
		obligaA.setCellValueFactory(new PropertyValueFactory<>("ObligatoriaS"));
		nombreA.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		descrA.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		ListaActividades.setItems(ltvActividades);
		cbObligatoria.getItems().addAll("SI", "NO");
		cbObligatoria.getSelectionModel().select("SI");
		cbObligTarea.getItems().addAll("SI", "NO");
		cbObligTarea.getSelectionModel().select("SI");
		cbObligaMod.getItems().addAll("SI", "NO");
		cbObligaMod.getSelectionModel().select("SI");
		cbAgregarAl.getItems().addAll("Al final", "Despues de la ultima creada", "Actividad que precede");
		cbAgregarAl.getSelectionModel().select("Al final");
		cbDondeAgTarea.getItems().addAll("Al final","Posicion...");
		cbDondeAgTarea.getSelectionModel().select("Al final");
		cbMetodoTar.getItems().addAll("Desde el inicio","Desde actividad seleccionada");
		cbMetodoTar.getSelectionModel().select("Desde el inicio");
		actualizarComboProcesos();
	}
	
	////////////////////////////////////////
	////////////CODIGO GENERICO/////////////
	////////////////////////////////////////
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
	
	//Actualizaciones
	private void actualizarTablaProcesos() {
		ListaProcesos.getItems().clear();
		Iterator listPro = modelFactoryController.getProyect().getListaProcesos().iterator();
		while (listPro.hasNext()) { 
				Proceso proc = (Proceso) listPro.next();
				ltvProceso.add(proc);
        }	
	}
	
	public void actualizarComboProcesos() {
		ObservableList<String> procesos= FXCollections.observableArrayList();
		Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
		while (listProc.hasNext()) { 
				Proceso pro = (Proceso) listProc.next();
				String info = pro.toString();
				procesos.add(info);
        }
		cbModProces.setItems(procesos);
		cbPerteProce.setItems(procesos);
		cbProcTarea.setItems(procesos);
		cbProceModAct.setItems(procesos);
		cbBuscarTarPro.setItems(procesos);
		cbProcTiempo.setItems(procesos);
		cbInterProc.setItems(procesos);
	}
	
	public void actualizarComboActividades(Proceso proceSelec) {
		ObservableList<String> actividades= FXCollections.observableArrayList();
		Iterator listActi = proceSelec.getListaActividades().iterator();
		while (listActi.hasNext()) { 
			Actividad act = (Actividad) listActi.next();
			String info2 = act.toString();
			actividades.add(info2);	
        }	
		cbPrecedidaPor.setItems(actividades);
		cbActivTarea.setItems(actividades);
		cbBuscarTarAct.setItems(actividades);
		cbInterAct1.setItems(actividades);
		cbInterAct2.setItems(actividades);
	}
	
	public void actualizarComboActividadesMod(Proceso proceSelec) {
		ObservableList<String> actividades= FXCollections.observableArrayList();
		Iterator listActi = proceSelec.getListaActividades().iterator();
		while (listActi.hasNext()) { 
			Actividad act = (Actividad) listActi.next();
			String info2 = act.toString();
			actividades.add(info2);	
        }	
		cbActModAct.setItems(actividades);
	}
	
	public void actualizarComboActividadesIntercambiar(Proceso proceSelec) {
		ObservableList<String> actividades= FXCollections.observableArrayList();
		Iterator listActi = proceSelec.getListaActividades().iterator();
		while (listActi.hasNext()) { 
			Actividad act = (Actividad) listActi.next();
			String info2 = act.toString();
			actividades.add(info2);	
        }	
		cbInterAct1.setItems(actividades);
		cbInterAct2.setItems(actividades);
	}
	
	public void actualizarComboActividadesBusTar(Proceso proceSelec) {
		ObservableList<String> actividades= FXCollections.observableArrayList();
		Iterator listActi = proceSelec.getListaActividades().iterator();
		while (listActi.hasNext()) { 
			Actividad act = (Actividad) listActi.next();
			String info2 = act.toString();
			actividades.add(info2);	
        }	
		cbBuscarTarAct.setItems(actividades);
	}
	
	public void actualizarComboActividadesBusActivTarea(Proceso proceSelec) {
		ObservableList<String> actividades= FXCollections.observableArrayList();
		Iterator listActi = proceSelec.getListaActividades().iterator();
		while (listActi.hasNext()) { 
			Actividad act = (Actividad) listActi.next();
			String info2 = act.toString();
			actividades.add(info2);	
        }	
		cbActivTarea.setItems(actividades);
	}
	
	
	//Utilidad
	public void limpiarTextosCrearProceso() {
		tfId.setText("");
		tfNombreProceso.setText("");
	}
	
	public void limpiarTextosModProceso() {
		tfModId.setText("");
		tfModNombreProceso.setText("");
	}
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	
	//FXML de crear proceso
	@FXML private TextField tfId;
	@FXML private TextField tfNombreProceso;
	
	public void crearProceso() {
		String id = tfId.getText();
		String nombre = tfNombreProceso.getText();
		
		modelFactoryController.getProyect().crearProceso(id, nombre);
		imprimirInfo("El proceso se creo con exito.");
		actualizarComboProcesos();
		actualizarTablaProcesos();
		limpiarTextosCrearProceso();
		serializarBinario(modelFactoryController.getProyect(), Persistencia.RUTA_ARCHIVO_BINARIO);
	}
	
	private void serializarBinario(Proyecto proyect, String rutaArchivoBinario) {
//		try {
//			Persistencia.serializarBinario(rutaArchivoBinario, proyect);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	//FXML Tabla de procesos
	@FXML
	private ObservableList<Proceso> ltvProceso = FXCollections.observableArrayList();
	@FXML
	private TableView<Proceso> ListaProcesos;
	@FXML
	private TableColumn<Proceso, String> nombreP;
	@FXML
	private TableColumn<Proceso, String> idP;
	
	//FXML Mod procesos
	@FXML 
	private ComboBox<String> cbModProces = new ComboBox<String>();
	@FXML 
	private TextField tfModId;
	@FXML 
	private TextField tfModNombreProceso;
	public void modificarProceso() {
		String procSelec = cbModProces.getValue().toString();
		String idMod = tfModId.getText();
		String nomMod = tfModNombreProceso.getText();
		
		modelFactoryController.getProyect().modificarProceso(idMod, nomMod, procSelec);
		
		actualizarComboProcesos();
		actualizarTablaProcesos();
		limpiarTextosModProceso();
		imprimirInfo("Se ha modificado el proceso con exito.");
		serializarBinario(modelFactoryController.getProyect(), Persistencia.RUTA_ARCHIVO_BINARIO);
	}
	
	@FXML 
	private ComboBox<String> cbProcTiempo = new ComboBox<String>();
	public void consultarTiempoProceso() {
		String procSel = cbProcTiempo.getValue();
		String res="";
		int duraMax=0;
		int duraMin=0;
		Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
		Proceso procesoA = null;
		Actividad actividadPrece = null;
		while (listProc.hasNext()) { 
			Proceso pro = (Proceso) listProc.next();
			if(procSel.equals(pro.toString())) {
				procesoA = pro;
				Iterator listActi = procesoA.getListaActividades().iterator();
				while (listActi.hasNext()) { 
					Actividad Act = (Actividad) listActi.next();
					duraMax+=Act.getMaxDuracion();
					duraMin+=Act.getMinDuracion();
		        }	
			}
			
		}	
		res+="El proceso: " + procSel + "\nTiene una duracion maxima de: " + duraMax + " minutos" + "\nY una duracion minima de: " + duraMin + " minutos.";
		imprimirInfo(res);
	}
	//////
	//Buscar actividad
	//////
	@FXML private TextField actividadBuscar;
	
	public void BuscarActividad() {
		String activBus = actividadBuscar.getText();
		String resul="";
		Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
		Proceso procesoA = null;
		Actividad actividadPrece = null;
		while (listProc.hasNext()) { 
			Proceso pro = (Proceso) listProc.next();
			procesoA = pro;
			Iterator listActi = procesoA.getListaActividades().iterator();
			while (listActi.hasNext()) { 
				Actividad Act = (Actividad) listActi.next();
				if(activBus.equals(Act.getNombre())) {
					resul+="\n\nActividad encontrada en el proceso: " + procesoA.getNombre();
					resul+="\nNombre: " + Act.getNombre();
					resul+="\nDescripcion: " + Act.getDescripcion();
					resul+="\nObligatoria: " + Act.getObligatoriaS();
					resul+="\nDuracion minima: " + Act.getMinDuracion();
					resul+="\nDuracion maxima: " + Act.getMaxDuracion();
					resul+="\nTareas: " + Act.getTareas();
					
				}
	        }	
        }
		if(resul.equals("")) {
			imprimirWarning("No se ha encontrado la actividad");
		}else {
			imprimirInfo(resul);
		}
	}
	
	
	///////////////////////////////////////////
	//////////CODIGO CRUD ACTIVIDAD////////////
	///////////////////////////////////////////
	@FXML
	private TextField tfNombreAct;
	@FXML 
	private TextField tfDescripAct;
	@FXML private ComboBox<String> cbObligatoria;
	@FXML 
	private ComboBox<String> cbPerteProce = new ComboBox<String>();
	@FXML private ComboBox<String> cbAgregarAl;
	@FXML 
	private ComboBox<String> cbPrecedidaPor = new ComboBox<String>();
	
	public void crearActividad() {
		String nombreAct = tfNombreAct.getText();
		String descripAct = tfDescripAct.getText();
		String procSelecAct = cbPerteProce.getValue();
		String actSelec = " ";
		Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
		Proceso procesoA = null;
		Actividad actividadPrece = null;
		while (listProc.hasNext()) { 
			Proceso pro = (Proceso) listProc.next();
			if(procSelecAct.equals(pro.toString())) {
				procesoA = pro;
				actSelec = cbPrecedidaPor.getValue();
				if(actSelec==null) {
					actSelec = " ";
				}
				Iterator listActi = procesoA.getListaActividades().iterator();
				while (listActi.hasNext()) { 
					Actividad Act = (Actividad) listActi.next();
					if(actSelec.equals(Act.toString())) {
						actividadPrece = Act;
					}
		        }	
			}
        }
		
		boolean obligatoria = true;
		int bTipo= cbObligatoria.getSelectionModel().getSelectedIndex();
		String obliS = "";
		if(bTipo==0) {
			obligatoria = true;
			obliS = "SI";
		}
		if(bTipo==1) {
			obligatoria = false;
			obliS = "NO";
		}
		
		int bTipoAgregacion= cbAgregarAl.getSelectionModel().getSelectedIndex();

		//Buscar actividad
		if(banderaActividad==true) {
			modelFactoryController.getProyect().crearActividad(procSelecAct, descripAct, nombreAct, obligatoria, bTipoAgregacion, actividadPrece, obliS);
			banderaActividad=false;
			serializarBinario(modelFactoryController.getProyect(), Persistencia.RUTA_ARCHIVO_BINARIO);
			imprimirInfo("Se ha creado la actividad con exito!");
		}else {
			imprimirWarning("Por favor, pulse (Actualizar) para mostrar las actividades del proceso seleccionado");
		}
		actualizarComboActividades(procesoA);
	}
	
	@FXML
	private TextField tfNuevoNomAct;
	@FXML
	private TextField tfNuevaDescAct;
	
	@FXML private ComboBox<String> cbObligaMod;
	@FXML 
	private ComboBox<String> cbProceModAct = new ComboBox<String>();
	@FXML 
	private ComboBox<String> cbActModAct = new ComboBox<String>();
	
	
	public void modificarActividad() {
		String nuevoNomAct = tfNuevoNomAct.getText();
		String nuevaDescAct = tfNuevaDescAct.getText();
		String procSelecAct = cbProceModAct.getValue();
		
		String actSelec = " ";
		Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
		Proceso procesoA = null;
		Actividad actividadPrece = null;
		while (listProc.hasNext()) { 
			Proceso pro = (Proceso) listProc.next();
			if(procSelecAct.equals(pro.toString())) {
				procesoA = pro;
				///
				actSelec = cbActModAct.getValue();
				if(actSelec==null) {
					actSelec = " ";
				}
				Iterator listActi = procesoA.getListaActividades().iterator();
				while (listActi.hasNext()) { 
					Actividad Act = (Actividad) listActi.next();
					if(actSelec.equals(Act.toString())) {
						actividadPrece = Act;
					}
		        }	
			}
        }
		
		boolean obligatoria = true;
		int bTipo= cbObligaMod.getSelectionModel().getSelectedIndex();
		String obliS = "";
		if(bTipo==0) {
			obligatoria = true;
			obliS = "SI";
		}
		if(bTipo==1) {
			obligatoria = false;
			obliS = "NO";
		}
		
		if(banderaActividadMod==true) {
			modelFactoryController.getProyect().modificarActividad(procSelecAct, actSelec, nuevaDescAct, nuevoNomAct, obligatoria, obliS);
			banderaActividadMod=false;
			serializarBinario(modelFactoryController.getProyect(), Persistencia.RUTA_ARCHIVO_BINARIO);
			imprimirInfo("Se ha modificado la actividad con exito!");
		}else {
			imprimirWarning("Por favor, pulse (Actualizar) para mostrar las actividades del proceso seleccionado");
		}

	}
	
	
	//INTERCAMBIO DE ACTIVIDADES
	
	@FXML 
	private ComboBox<String> cbInterProc = new ComboBox<String>();
	@FXML 
	private ComboBox<String> cbInterAct1 = new ComboBox<String>();
	@FXML 
	private ComboBox<String> cbInterAct2 = new ComboBox<String>();
	
	public void intercambiarCompleto() {
		String procSelec = cbInterProc.getValue();
		String act1selec = cbInterAct1.getValue();
		String act2selec = cbInterAct2.getValue();
		if(act1selec.equals(act2selec)) {
			imprimirWarning("No se puede intercambiar una actividad con ella misma.\nOperacion cancelada.");
		}else {
			if(banderaActividadIntercam==true) {
				modelFactoryController.getProyect().intercambiarCompleto(procSelec, act1selec, act2selec);
				banderaActividadIntercam=false;
				serializarBinario(modelFactoryController.getProyect(), Persistencia.RUTA_ARCHIVO_BINARIO);
				imprimirInfo("Se han intercambiado las actividades con exito\nPor favor pulse actualizar para verlas en la tabla.");
			}else {
				imprimirWarning("Por favor, pulse (Actualizar) para mostrar las actividades del proceso seleccionado");
			}
		}
	}
	
	public void intercambiarSinTareas() {
		String procSelec = cbInterProc.getValue();
		String act1selec = cbInterAct1.getValue();
		String act2selec = cbInterAct2.getValue();
		if(act1selec.equals(act2selec)) {
			imprimirWarning("No se puede intercambiar una actividad con ella misma.\nOperacion cancelada.");
		}else {
			if(banderaActividadIntercam==true) {
				modelFactoryController.getProyect().intercambiarSinTareas(procSelec, act1selec, act2selec);
				banderaActividadIntercam=false;
				serializarBinario(modelFactoryController.getProyect(), Persistencia.RUTA_ARCHIVO_BINARIO);
				imprimirInfo("Se han intercambiado las actividades con exito\nPor favor pulse actualizar para verlas en la tabla.");
			}else {
				imprimirWarning("Por favor, pulse (Actualizar) para mostrar las actividades del proceso seleccionado");
			}
		}
	}
	
	
	
	//Este proceso actualiza el combo de actividades dependiendo del proceso seleccionado
	public void update() {//Crear actividad
		String procSelecAct = cbPerteProce.getValue();
		Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
		Proceso procesoA = null;
		while (listProc.hasNext()) { 
			Proceso pro = (Proceso) listProc.next();
			if(procSelecAct.equals(pro.toString())) {
				procesoA = pro;
				ListaActividades.getItems().clear();
				Iterator listAct = procesoA.getListaActividades().iterator();
				
				while (listAct.hasNext()) { 
						Actividad act = (Actividad) listAct.next();
						ltvActividades.add(act);
		        }
			}
        }
		actualizarComboActividades(procesoA);
		banderaActividad=true;
	}
	
	//Este proceso actualiza el combo de actividades dependiendo del proceso seleccionado
		public void updateModAct() {
			String procSelecAct = cbProceModAct.getValue();
			Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
			Proceso procesoA = null;
			while (listProc.hasNext()) { 
				Proceso pro = (Proceso) listProc.next();
				if(procSelecAct.equals(pro.toString())) {
					procesoA = pro;
					ListaActividades.getItems().clear();
					Iterator listAct = procesoA.getListaActividades().iterator();
					
					while (listAct.hasNext()) { 
							Actividad act = (Actividad) listAct.next();
							ltvActividades.add(act);
			        }
				}
	        }
			actualizarComboActividadesMod(procesoA);
			banderaActividadMod=true;
		}
	
	//Table actividades
	//FXML Tabla de procesos
		@FXML private ObservableList<Actividad> ltvActividades = FXCollections.observableArrayList();
//		private ObservableList<Actividad> ltvActividades = FXCollections.emptyObservableList();

		@FXML private TableView<Actividad> ListaActividades;
		@FXML private TableColumn<Actividad, String> nombreA;
		@FXML private TableColumn<Actividad, String> descrA;
		@FXML private TableColumn<Actividad, String> obligaA;
		
		///////////////////////////////////////////
		//////////CODIGO CRUD TAREA////////////////
		///////////////////////////////////////////
		
		@FXML
		private TextField tfDescTarea;
		@FXML
		private TextField tfDuraTarea;
		@FXML
		private TextField tfPosAgTarea;
		@FXML 
		private ComboBox<String> cbProcTarea;
		@FXML private ComboBox<String> cbActivTarea;
		@FXML private ComboBox<String> cbObligTarea;
		@FXML private ComboBox<String> cbDondeAgTarea;
		
		public void agregarTarea() {
			String descripcionTarea = tfDescTarea.getText();
			int duraMin = Integer.parseInt(tfDuraTarea.getText());
			int posAg=0;
			if(tfPosAgTarea.getText().equals("")) {
				posAg=0;
			}else {
				posAg = Integer.parseInt(tfPosAgTarea.getText());
			}
			String procSelecAct = cbProcTarea.getValue();
			String actSelec = " ";
			Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
			Proceso procesoA = null;
			Actividad activTarea = null;
			while (listProc.hasNext()) { 
				Proceso pro = (Proceso) listProc.next();
				if(procSelecAct.equals(pro.toString())) {
					procesoA = pro;
					///
					actSelec = cbActivTarea.getValue();
					if(actSelec==null) {
						actSelec = " ";
					}
					Iterator listActi = procesoA.getListaActividades().iterator();
					while (listActi.hasNext()) { 
						Actividad Act = (Actividad) listActi.next();
						if(actSelec.equals(Act.toString())) {
							activTarea = Act;
						}
			        }	
				}
	        }
			int bTipAgTarea= cbDondeAgTarea.getSelectionModel().getSelectedIndex();
			boolean obligatoria = true;
			int bTipo= cbObligTarea.getSelectionModel().getSelectedIndex();
			String obliS = "";
			if(bTipo==0) {
				obligatoria = true;
				obliS = "SI";
			}
			if(bTipo==1) {
				obligatoria = false;
				obliS = "NO";
			}
			if(banderaActividadTarea==true) {
				modelFactoryController.getProyect().crearTarea(procSelecAct, activTarea, descripcionTarea, obligatoria, duraMin, bTipAgTarea, posAg, obliS);
				banderaActividadTarea=false;
				serializarBinario(modelFactoryController.getProyect(), Persistencia.RUTA_ARCHIVO_BINARIO);
			}else {
				imprimirWarning("Por favor, pulse (Actualizar) para mostrar las actividades del proceso seleccionado");
			}
		}
		
		@FXML
		private TextField tfTareaBuscar;
		@FXML 
		private ComboBox<String> cbMetodoTar;
		@FXML private ComboBox<String> cbBuscarTarPro;
		@FXML private ComboBox<String> cbBuscarTarAct;
		
		public void buscarTarea() {
			String tareaBuscar = tfTareaBuscar.getText();
			String procSelecAct = cbBuscarTarPro.getValue();
			String actSelec = cbBuscarTarAct.getValue();
			String res = "";
			if(cbMetodoTar.getSelectionModel().getSelectedIndex()==0) {//Busqueda desde el inicio.
				Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
				Proceso procesoA = null;
				Actividad actividadPrece = null;
				while (listProc.hasNext()) { 
					Proceso pro = (Proceso) listProc.next();
					procesoA = pro;
					Iterator listActi = procesoA.getListaActividades().iterator();
					while (listActi.hasNext()) { 
						Actividad Act = (Actividad) listActi.next();
						Nodo<Tarea> tarea = Act.getColaTareas().getPrimero();
						while(tarea!=null) {
							if(tarea.getValorNodo().getDescripcion().equals(tareaBuscar)) {
								res += "\n\nDescripcion de la tarea: " + tarea.getValorNodo().getDescripcion();
								res += "\nObligatoria: " + tarea.getValorNodo().getObligatoriaS();
								res += "\nDuracion: " + tarea.getValorNodo().getDuracion() + " minutos.";
							}
							tarea=tarea.getSiguienteNodo();
						}
			        }	
		        }
			}else {//Busqueda por la actividad
				Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
				Proceso procesoA = null;
				Actividad actividadPrece = null;
				while (listProc.hasNext()) { 
					Proceso pro = (Proceso) listProc.next();
					procesoA = pro;
					Iterator listActi = procesoA.getListaActividades().iterator();
					while (listActi.hasNext()) { 
						Actividad Act = (Actividad) listActi.next();
						if(actSelec.equals(Act.toString())) {
							Nodo<Tarea> tarea = Act.getColaTareas().getPrimero();
							while(tarea!=null) {
								if(tarea.getValorNodo().getDescripcion().equals(tareaBuscar)) {
									res += "\nTarea encontrada en la actividad: " + Act.getNombre();
									res += "\nDescripcion de la tarea: " + tarea.getValorNodo().getDescripcion();
									res += "\nObligatoria: " + tarea.getValorNodo().getObligatoriaS();
									res += "\nDuracion: " + tarea.getValorNodo().getDuracion() + " minutos.";
								}
								tarea=tarea.getSiguienteNodo();
							}
						}
			        }	
		        }
			}
			if(banderaActividadConsuTarea==true) {
				if(res.equals("")==false) {
					imprimirInfo(res);
				}else {
					imprimirWarning("No se ha encontrado la tarea.");
				}
				banderaActividadConsuTarea=false;
			}else {
				imprimirWarning("Por favor, pulse (Actualizar) para mostrar las actividades del proceso seleccionado");
			}
			
		}
		
		//Este proceso actualiza el combo de actividades dependiendo del proceso seleccionado
		public void updateTar() {//Modificar actividad
			String procSelecAct = cbProcTarea.getValue();
			Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
			Proceso procesoA = null;
			while (listProc.hasNext()) { 
				Proceso pro = (Proceso) listProc.next();
				if(procSelecAct.equals(pro.toString())) {
					procesoA = pro;
					ListaActividades.getItems().clear();
					Iterator listAct = procesoA.getListaActividades().iterator();
					
					while (listAct.hasNext()) { 
							Actividad act = (Actividad) listAct.next();
							ltvActividades.add(act);
			        }
				}
	        }
			actualizarComboActividades(procesoA);
			banderaActividadMod=true;
		}
		
		public void updateTarBuscar() {//BuscarTarea
			String procSelecAct = cbBuscarTarPro.getValue();
			Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
			Proceso procesoA = null;
			while (listProc.hasNext()) { 
				Proceso pro = (Proceso) listProc.next();
				if(procSelecAct.equals(pro.toString())) {
					procesoA = pro;
					ListaActividades.getItems().clear();
					Iterator listAct = procesoA.getListaActividades().iterator();
					while (listAct.hasNext()) { 
							Actividad act = (Actividad) listAct.next();
							ltvActividades.add(act);
			        }
				}
	        }
			actualizarComboActividadesBusTar(procesoA);
			banderaActividadConsuTarea=true;
		}
		
		public void updateIntercambiar() {//Intercambiar
			String procSelecAct = cbInterProc.getValue();
			Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
			Proceso procesoA = null;
			while (listProc.hasNext()) { 
				Proceso pro = (Proceso) listProc.next();
				if(procSelecAct.equals(pro.toString())) {
					procesoA = pro;
					ListaActividades.getItems().clear();
					Iterator listAct = procesoA.getListaActividades().iterator();
					while (listAct.hasNext()) { 
							Actividad act = (Actividad) listAct.next();
							ltvActividades.add(act);
			        }
				}
	        }
			actualizarComboActividadesIntercambiar(procesoA);
			banderaActividadIntercam=true;
		}
		
		public void updateTarCrear() {//Crear tarea
			String procSelecAct = cbProcTarea.getValue();
			Iterator listProc = modelFactoryController.getProyect().getListaProcesos().iterator();
			Proceso procesoA = null;
			while (listProc.hasNext()) { 
				Proceso pro = (Proceso) listProc.next();
				if(procSelecAct.equals(pro.toString())) {
					procesoA = pro;
					ListaActividades.getItems().clear();
					Iterator listAct = procesoA.getListaActividades().iterator();
					while (listAct.hasNext()) { 
							Actividad act = (Actividad) listAct.next();
							ltvActividades.add(act);
			        }
				}
	        }
			actualizarComboActividadesBusActivTarea(procesoA);
			banderaActividadTarea=true;
		}
}
