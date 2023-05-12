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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Model;

/**
 * 
 * This class is the controller for the login functionality of the application.
 * It handles user login and navigation to different views based on user role.
 */
public class controllerLogin implements Initializable {

	@FXML
	private ChoiceBox<String> choiceField;

	@FXML
	private Button loginButton;

	@FXML
	private ImageView logo;

	@FXML
	private PasswordField paswordField;

	@FXML
	private TextField usernameField;

	@FXML
	private Scene myScene;

	private Map<String, String> doctorCredentials;
	private Map<String, String> patientCredentials;
	private Map<String, String> credentials;

	Model model = null;

	/**
	 * Handles the action when the login button is clicked.
	 *
	 * @param event the event triggered by clicking the login button
	 * @throws IOException if an I/O error occurs during loading of the new scene
	 */
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
			alertInput.setTitle("ERROR LOGIN");
			alertInput.setHeaderText("PLEASE INSERT EMAIL AND PASSWORD FIELD!");
			// show the dialog
			alertInput.show();
		}
		if (!credentials.containsKey(usernameField.getText())) {
			// System.out.println("email non presente");
			alertInput.setTitle("ERROR LOGIN");
			alertInput.setHeaderText("WRONG EMAIL!");
			// show the dialog
			alertInput.show();
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
				// System.out.println("Password errata");
				alertInput.setTitle("ERROR LOGIN");
				alertInput.setHeaderText("WRONG PASSWORD");
				// show the dialog
				alertInput.show();
			}
		}
	}

	Alert alertInput;

	/**
	 * 
	 * Initializes the controller class. Sets up the choice box with "Doctor" and
	 * "Patient" options. Configures the alert dialog for displaying errors.
	 * Initializes the model and retrieves credentials from the database.
	 * 
	 * @param arg0 The URL of the location used to resolve relative paths.
	 * @param arg1 The ResourceBundle that contains locale-specific objects.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// inizializzo il choice box con in due parametri interessati
		String[] choice = { "Doctor", "Patient" };
		choiceField.getStylesheets().add(getClass().getResource("../style/style1.css").toExternalForm());
		choiceField.getItems().addAll(choice);
		choiceField.getSelectionModel().selectFirst();

		alertInput = new Alert(AlertType.NONE);
		alertInput.setAlertType(AlertType.ERROR);

		DialogPane dialogPane = alertInput.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("../style/myDialog.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		try {
			model = Model.getInstance();
			doctorCredentials = model.getCredentials("doctor");
			patientCredentials = model.getCredentials("patient");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
