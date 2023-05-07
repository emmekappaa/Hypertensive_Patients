package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Model;
import model.Patient;

public class controllerPatient implements Initializable{

    @FXML
    private ChoiceBox<String> choiceDrug;

    @FXML
    private TextArea dbpField;

    @FXML
    private Button exit;

    @FXML
    private Text namePatient;

    @FXML
    private Text namePatient1;

    @FXML
    private TextArea sbpField;

    @FXML
    private Button submitBPM;

    @FXML
    private Button takeDrugs;

    @FXML
    void submitBPM_clicked(ActionEvent event) {

    }

    @FXML
    void takeDrugs_clicked(ActionEvent event) {

    }
    
    private Patient infoPerson;

	Model model = null;

	Session session = Session.getInstance();
	
	@FXML
	void exitClicked(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoaderlogged = new FXMLLoader(controllerPatient.class.getResource("../view/login.fxml"));
		Stage currentStage = (Stage) exit.getScene().getWindow();
		Scene sceneLogOut = new Scene(fxmlLoaderlogged.load());
		currentStage.setTitle("Login");
		currentStage.setScene(sceneLogOut);
		currentStage.show();

	}
    
    @Override
   	public void initialize(URL arg0, ResourceBundle arg1) {
    	try {
			model = Model.getInstance();
			infoPerson = (Patient)model.retrieveInfoByEmail(session.getMail(),"patient");
			choiceDrug.getItems().addAll(model.getAvaiableDrugs(infoPerson.getCF()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	namePatient.setText(infoPerson.getName());
    	namePatient1.setText(infoPerson.getCF_doctor());
    	
    	
   	}


}
