package controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Model;
import model.Patient;

public class controllerPatient implements Initializable  {

	@FXML
    private Button create_occupation;

    @FXML
    private Text namePatient;

    @FXML
    private TextField new_occupation_input;

    @FXML
    private TableView<?> occupations_table;
    
    @FXML
	private Scene myScene;
    
    @FXML
	private Button exit;
	
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	namePatient.setText(infoPerson.getName());
   	}


}
