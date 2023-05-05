package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Doctor;
import model.Model;
import model.Patient;

public class controllerDoctor implements Initializable {

	@FXML
	private Button create_occupation;
	@FXML
	private Text nameDoctor;

	@FXML
	private TextField new_occupation_input;
	@FXML
	private Text numPazienti;

	@FXML
	private Scene myScene;

	private Doctor infoPerson;

	@FXML
	private Button exit;

	@FXML
	private Button findButton;

	@FXML
	private TableView<Patient> tablePatient;
	@FXML
	private TableColumn<Patient, String> email;
	@FXML
	private TableColumn<Patient, String> name;
	@FXML
	private TableColumn<Patient, String> surname;
	@FXML
	private TableColumn<Patient, String> cf;

	ObservableList<Patient> patients;

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

	@FXML
	void findClicked(ActionEvent event) throws IOException 
	{
		FXMLLoader fxmlLoaderlogged = new FXMLLoader(controllerPatient.class.getResource("../view/patientInfo.fxml"));
		Stage currentStage = (Stage) exit.getScene().getWindow();
		Scene scenePatientInfo = new Scene(fxmlLoaderlogged.load());
		currentStage.setTitle("Patient Info");
		currentStage.setScene(scenePatientInfo);
		currentStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			model = Model.getInstance();
			infoPerson = (Doctor) model.retrieveInfoByEmail(session.getMail(), "doctor");
			patients = model.getPatientsByDoctor(infoPerson.getCF());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		nameDoctor.setText(infoPerson.getName());
		numPazienti.setText(Integer.toString(patients.size()));

		// tabella contenente pazienti presi dal dottore
		tablePatient.setItems(patients);
		cf.setCellValueFactory(new PropertyValueFactory<>("CF"));
		name.setCellValueFactory(new PropertyValueFactory<>("Name"));
		surname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
		email.setCellValueFactory(new PropertyValueFactory<>("Email"));

	}
}
