package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent; //DEVE ESSERE IL PRIMO IMPORTATO
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;

public class controllerLogin implements Initializable {

	@FXML
	private ChoiceBox<String> choiceField;

	@FXML
	private Button loginButton;

	@FXML
	private TextField paswordField;

	@FXML
	private TextField usernameField;

	@FXML
	private Scene myScene;

	private Map<String, String> doctorCredentials;
	private Map<String, String> patientCredentials;
	private Map<String, String> credentials;

	Model model = null;

	@FXML
	void loginButtonClicked(ActionEvent event) throws IOException {
		String choiced = (String) choiceField.getValue();

		if (choiced.equals("Doctor")) {
			credentials = doctorCredentials;
		} else {
			credentials = patientCredentials;
		}

		if (usernameField.getText().isEmpty() || paswordField.getText().isEmpty()) {
			System.out.println("Inserisci credenziali");
		}
		if (!credentials.containsKey(usernameField.getText())) {
			System.out.println("email non presente");
		} else {
			// controlliamo la password

			if (model.checkLogin(usernameField.getText(), paswordField.getText(),
					credentials.get(usernameField.getText()))) {
				System.out.println("ti sei loggato");

				// singleton per condivisione email
				Session session = Session.getInstance();
				session.setMail(usernameField.getText());

				FXMLLoader fxmlLoaderlogged;
				if (choiced.equals("Doctor")) {
					fxmlLoaderlogged = new FXMLLoader(controllerPatient.class.getResource("../view/doctor.fxml"));
				} else {
					fxmlLoaderlogged = new FXMLLoader(controllerPatient.class.getResource("../view/patient.fxml"));
				}

				Stage currentStage = (Stage) loginButton.getScene().getWindow();
				Scene sceneAfterLogin = new Scene(fxmlLoaderlogged.load());

				currentStage.setTitle("Benvenuto");
				currentStage.setScene(sceneAfterLogin);
				currentStage.show();

			} else {
				System.out.println("Password errata");
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// inizializzo il choice box con in due parametri interessati
		String[] choice = { "Doctor", "Patient" };
		choiceField.getItems().addAll(choice);

		try {
			model = Model.getInstance();
			doctorCredentials = model.getCredentials("doctor");
			patientCredentials = model.getCredentials("patient");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
