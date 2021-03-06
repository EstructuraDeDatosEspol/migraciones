package espol.edu.ec.main;

import espol.edu.ec.moduloTurnos.PaneModulo1;
import espol.edu.ec.moduloDeRegistro.PantallaOpciones;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OptionsWindow_Controller {

    private Parent rootProcesamiento;
    private Stage addTurnStage, adminRegStage, stageProcesamiento;
    private PantallaOpciones pantallaOpciones;

    @FXML
    void handle_addTurn() throws IOException {

        addTurnStage = new Stage();
        addTurnStage.setResizable(false);
        addTurnStage.initModality(Modality.APPLICATION_MODAL);
        addTurnStage.initStyle(StageStyle.UTILITY); 
        addTurnStage.setScene(new Scene(new PaneModulo1().getRoot()));
        addTurnStage.show();
    }

    @FXML
    void handle_AdminReg() throws IOException {
        
        adminRegStage = new Stage();
        pantallaOpciones = new PantallaOpciones(adminRegStage);
        adminRegStage.show();
    }

    @FXML
    void handle_showModuloProcesamiento()throws Exception{
        stageProcesamiento=new Stage();
        stageProcesamiento.setTitle("Contador de Registros");

        rootProcesamiento = FXMLLoader.load(getClass().getResource("../moduloProcesamiento/ModuloProcesamiento.fxml"));
        stageProcesamiento.setScene(new Scene(rootProcesamiento));

        stageProcesamiento.show();
    }
}
