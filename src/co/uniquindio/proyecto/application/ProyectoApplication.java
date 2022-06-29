package co.uniquindio.proyecto.application;

import java.io.IOException;

import co.uniquindio.proyecto.controller.ModelFactoryController;
import co.uniquindio.proyecto.model.Proyecto;
import co.uniquindio.proyecto.views.CRUDProyectoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProyectoApplication extends Application{

	private Stage primaryStage;
	private BorderPane rootLayout;
	private AnchorPane login;
	ModelFactoryController modelFactoryController;
	Proyecto proyect;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public ProyectoApplication() {
		modelFactoryController = new ModelFactoryController();
		proyect=modelFactoryController.getProyect();
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Proyecto");
		initRootLayout();
		showPersonOverview();
	} 

	public void initRootLayout() {
		try { 
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ProyectoApplication.class.getResource("/co/uniquindio/proyecto/views/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout,700,525);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showPersonOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ProyectoApplication.class.getResource("/co/uniquindio/proyecto/views/ProyectoView.fxml"));
			AnchorPane bancoOV = (AnchorPane) loader.load();
			rootLayout.setCenter(bancoOV);
			CRUDProyectoController controller= loader.getController();
			controller.setMain(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
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
