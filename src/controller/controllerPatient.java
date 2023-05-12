package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Drug;
import model.Model;
import model.Patient;

/**
 * 
 * Controller class for managing patient-related actions and interactions.
 */
public class controllerPatient implements Initializable {

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
	private TextArea infoDrugArea;

	@FXML
	private Button emailDoctor;

	@FXML
	private ListView<String> symptomList;

	// create a alert
	Alert alert1; // per le medicine
	Alert alertInput;

	ObservableList<String> symptoms;

	/**
	 * 
	 * Handles the event when the "Email Doctor" button is clicked. Sends an email
	 * to the patient's doctor.
	 * 
	 * @param event The action event
	 * @throws URISyntaxException If there is an error in the URI syntax
	 * @throws IOException        If there is an I/O error
	 * @throws SQLException       If there is an error in the SQL operation
	 */
	@FXML
	void emailDoctor_clicked(ActionEvent event) throws URISyntaxException, IOException, SQLException {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.MAIL)) {
				URI mailto = new URI(
						"mailto:" + model.getEmailDoctorByCF(infoPerson.getCF_doctor()) + "?subject=Need%20help");
				desktop.mail(mailto);

			}
		}
	}

	/**
	 * 
	 * Handles the event when the "Submit BPM" button is clicked. Submits the
	 * entered blood pressure measurements and selected symptoms.
	 * 
	 * @param event The action event
	 */
	@FXML
	void submitBPM_clicked(ActionEvent event) {

		if (!dbpField.getText().isEmpty() && !sbpField.getText().isEmpty()) {
			ObservableList<String> listone = symptomList.getSelectionModel().getSelectedItems();
			ObservableList<String> listoneWithoutduplicate = FXCollections.observableArrayList();

			// filtro i duplicati
			for (String s : listone) {
				if (!listoneWithoutduplicate.contains(s)) {
					listoneWithoutduplicate.add(s);
				}
			}

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String data = dtf.format(now);

			try {
				model.insertBPM(infoPerson.getCF(), dbpField.getText(), sbpField.getText(), data);
			} catch (SQLException e) {
			}

			if (!listoneWithoutduplicate.isEmpty()) {
				try {
					model.insertSymptom(listoneWithoutduplicate, model.getIdDiagnosi(infoPerson.getCF(), data));
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			dbpField.clear();
			sbpField.clear();
			symptomList.getSelectionModel().clearSelection();
		} else {
			alertInput.setTitle("Error insert BPM");
			alertInput.setHeaderText("Missing parameters (SDB/DBP)");
			// show the dialog
			alertInput.show();
		}

	}

	/**
	 * 
	 * Handles the event when the "Take Drugs" button is clicked. Records the
	 * patient's drug intake and updates the drug information display.
	 * 
	 * @param event The action event
	 */
	@FXML
	void takeDrugs_clicked(ActionEvent event) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			model.takeDrug(infoPerson.getCF(), choiceDrug.getValue(), dtf.format(now));
		} catch (SQLException e) {
		}

		// aggiorno infoBox
		try {
			infoDrugArea.setText(model.getInfoDrug(choiceDrug.getValue(), infoPerson.getCF()));
		} catch (Exception e) {
			System.out.println("Select drug first!");
		}
	}

	private Patient infoPerson;

	Model model = null;

	Session session = Session.getInstance();

	/**
	 * 
	 * Handles the event when the "Exit" button is clicked. Logs out the patient and
	 * redirects to the login interface.
	 * 
	 * @param event The action event
	 * @throws IOException If there is an I/O error
	 */
	@FXML
	void exitClicked(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoaderlogged = new FXMLLoader(controllerPatient.class.getResource("../view/login.fxml"));
		Stage currentStage = (Stage) exit.getScene().getWindow();
		Scene sceneLogOut = new Scene(fxmlLoaderlogged.load());
		currentStage.setTitle("Login");
		currentStage.setScene(sceneLogOut);
		currentStage.show();

	}

	/**
	 * 
	 * Initializes the controller and sets up the initial state of the interface.
	 * 
	 * @param arg0 The URL used to resolve relative paths
	 * 
	 * @param arg1 The resource bundle containing localized resources
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		alert1 = new Alert(AlertType.NONE);
		alertInput = new Alert(AlertType.NONE);

		DialogPane dialogPane = alertInput.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("../style/myDialog.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		DialogPane dialogPane1 = alert1.getDialogPane();
		dialogPane1.getStylesheets().add(getClass().getResource("../style/myDialog.css").toExternalForm());
		dialogPane1.getStyleClass().add("myDialog");

		ArrayList<Drug> lista = null;

		choiceDrug.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub
				// aggiorno infoBox
				try {
					infoDrugArea.setText(model.getInfoDrug(choiceDrug.getValue(), infoPerson.getCF()));
				} catch (Exception e) {
					System.out.println("Select drug first!");
				}
			}
		});

		try {
			model = Model.getInstance();
			infoPerson = (Patient) model.retrieveInfoByEmail(session.getMail(), "patient");
			lista = model.getDrugToBeTaken(infoPerson.getCF(), null);
			choiceDrug.getItems().addAll(model.getAvaiableDrugs(infoPerson.getCF()));
			choiceDrug.getSelectionModel().selectFirst();
			symptoms = model.getAllSymptom();
			symptomList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			symptomList.setItems(symptoms);
			namePatient1.setText(model.getSurnameDoctorByCF(infoPerson.getCF_doctor()));
		} catch (SQLException e) {

			e.printStackTrace();
		}
		for (Drug s : lista) {
			System.out.println(s.getDescription());
		}
		if (!lista.isEmpty()) {

			String testoAlert = "Drug to be taken:";
			for (Drug d : lista) {
				testoAlert += "\n";
				testoAlert += " - " + d.getDescription();
			}

			// set alert type
			alert1.setAlertType(AlertType.INFORMATION);
			alert1.setTitle("Drug Reminder");
			alert1.setHeaderText(testoAlert);
			alert1.setContentText("Remember to take 'em!");
			// show the dialog
			alert1.show();
		}

		// aggiorno infoBox
		try {
			infoDrugArea.setText(model.getInfoDrug(choiceDrug.getValue(), infoPerson.getCF()));
		} catch (Exception e) {
			System.out.println("Select drug first!");
		}

		alertInput.setAlertType(AlertType.ERROR);

		namePatient.setText(infoPerson.getName());

	}

}
