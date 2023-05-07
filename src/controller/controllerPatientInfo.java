package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Diagnosis;
import model.Info;
import model.Model;
import model.Pathology;
import model.Patient;
import model.Symptom;
import model.Therapy;

public class controllerPatientInfo implements Initializable {

	@FXML
	private TableView<Pathology> tablePathology;

	@FXML
	private TableColumn<Pathology, String> columnEnd;

	@FXML
	private TableColumn<Pathology, String> columnPathology;

	@FXML
	private TableColumn<Pathology, String> columnStart;

	@FXML
	private ChoiceBox<String> drugChoice;

	@FXML
	private ChoiceBox<String> assumptionChoice;

	@FXML
	private TextArea description;

	@FXML
	private TextArea quantity;

	@FXML
	private Button insertTerapyButton;

	@FXML
	private Button addInfo;

	@FXML
	private TextArea descriptionInfo;

	@FXML
	private Text labelCF;

	@FXML
	private ChoiceBox<String> choicePatologies;

	@FXML
	private ChoiceBox<String> statusChoice;

	@FXML
	private DatePicker endPath;

	@FXML
	private DatePicker startPath;

	@FXML
	private Button addPatologies;

	@FXML
	private Button exitButton;

	@FXML
	private TableView<Therapy> therapiesTable;

	@FXML
	private TableColumn<Therapy, String> drugColumn;

	@FXML
	private TableColumn<Therapy, Integer> qtyColumn;

	@FXML
	private TableColumn<Therapy, Integer> assumptionColumn;

	@FXML
	private TableColumn<Therapy, String> indicationColumn;

	@FXML
	private TableColumn<Therapy, String> statusColumn;

	@FXML
	private TableView<Info> infoTable;

	@FXML
	private TableColumn<Info, String> dateInfoColumn;

	@FXML
	private TableColumn<Info, String> infoInfoColumn;

	@FXML
	private TableColumn<Info, String> docInfoColumn;

	@FXML
	private LineChart<String, Number> chart;

	@FXML
	private TableView<Symptom> diagnosisTable;

	@FXML
	private TableColumn<String, String> dateDiagnosis;

	@FXML
	private TableColumn<String, String> patologyDiagnosis;

	@FXML
	private DatePicker startDateChart;

	@FXML
	private DatePicker endDateChart;

	@FXML
	private Button modifyTherapy;
	
	@FXML
	private Button modifyPathology;

	@FXML
	private Text statusLabel;
	
    @FXML
    private Text hypertensionInfo;
    
    @FXML
    private Text drugThearpy;

	private Patient infoPerson;
	Session session = Session.getInstance();
	Model model = null;

	boolean modify = false;
	boolean modify1 = false;
	
	@FXML
    void modifyPathology_clicked(ActionEvent event) {
		modify1 = !modify1;

		if (modify1 == true) {
			modifyTherapy.setText("Discard");
			addPatologies.setText("Modify therapy");
		} else {
			modifyTherapy.setText("Modify");
			addPatologies.setText("Insert therapy");

		}

		try {
			Pathology ph = tablePathology.getSelectionModel().getSelectedItem();
			System.out.println(ph.getID());

			if (!modify1) { // se schiaccio di nuovo modifica per annullare cambio i campi
				clearTherapiesField1();
			}
			else { // altrimenti devo settare i campi
				startPath.setValue(LocalDate.of(Integer.parseInt(ph.getStartDate().split("-")[0]),Integer.parseInt(ph.getStartDate().split("-")[1]),Integer.parseInt((ph.getStartDate().split("-")[2]))));
				try {
					endPath.setValue(LocalDate.of(Integer.parseInt(ph.getEndDate().split("-")[0]),Integer.parseInt(ph.getEndDate().split("-")[1]),Integer.parseInt((ph.getEndDate().split("-")[2]))));
				}
				catch(Exception e){
					endPath.valueProperty().set(null);
				}
				choicePatologies.valueProperty().set(ph.getID());
			}
		} catch (Exception e) {
			modify1 = !modify1;
			if (modify1 == true) {
				modifyPathology.setText("Discard changes");
				addPatologies.setText("Modify therapy");
			} else {
				modifyPathology.setText("Modify");
				addPatologies.setText("Insert therapy");
			}
			System.out.println("Devi selezionare una riga da modificare!");
		}	
    }

	
	@FXML
	void showDataChart_clicked(ActionEvent event) {
		
		showLineChart();
	}

	@FXML
	void modifyTherapy_clicked(ActionEvent event) {
		modify = !modify;

		if (modify == true) {
			modifyTherapy.setText("Discard");
			insertTerapyButton.setText("Modify therapy");
			statusChoice.setVisible(true);
			statusLabel.setVisible(true);
		} else {
			modifyTherapy.setText("Modify");
			insertTerapyButton.setText("Insert therapy");
			statusChoice.setVisible(false);
			statusLabel.setVisible(false);

		}

		try {
			Therapy th = therapiesTable.getSelectionModel().getSelectedItem();
			System.out.println(th.getID_Drug());

			if (!modify) { // se schiaccio di nuovo modifica per annullare cambio i campi
				clearTherapiesField();
			} else { // altrimenti devo settare i campi
				drugChoice.valueProperty().set(th.getID_Drug());
				assumptionChoice.valueProperty().set(Integer.toString(th.getAssumptions()));
				statusChoice.valueProperty().set(th.getStatus());
				quantity.setText(Integer.toString(th.getQuantity()));
				description.setText(th.getIndication());
			}
		} catch (Exception e) {
			modify = !modify;
			if (modify == true) {
				modifyTherapy.setText("Discard changes");
				insertTerapyButton.setText("Modify therapy");
				statusChoice.setVisible(true);
				statusLabel.setVisible(true);
			} else {
				modifyTherapy.setText("Modify");
				insertTerapyButton.setText("Insert therapy");
				statusChoice.setVisible(false);
				statusLabel.setVisible(false);

			}
			alertInput.setTitle("Error Input");
    		alertInput.setHeaderText("You need to select a row first from the table");
            // show the dialog
    		alertInput.show();
			//System.out.println("Devi selezionare una riga da modificare!");
		}
	}
	
	@SuppressWarnings({ "unlikely-arg-type", "unchecked" })
	private void showLineChart() {
		try {
			String inizio = "2000-01-01";
			String fine = "2040-01-01";
			LocalDate startDate = LocalDate.of(2000, 1, 1);
			LocalDate endDate = LocalDate.of(2040, 1, 1);
			try {
				inizio = startDateChart.getValue().toString();
				fine = endDateChart.getValue().toString();
				startDate = startDateChart.getValue();
				endDate = endDateChart.getValue();
			}
			catch(Exception e){	}
			ObservableList<Diagnosis> Diagnosi = model.getDiagnosis(inizio,fine);
			ObservableList<Symptom> symptom = FXCollections.observableArrayList();

			for (Diagnosis d : Diagnosi) {
				ObservableList<Symptom> sy_list = d.getSymptoms();
				if (!sy_list.isEmpty())
					symptom.addAll(sy_list);
			}

			// recupera le date di inizio e fine dall'interfaccia grafica

			// diagnosisTable.clear();
			diagnosisTable.setItems(symptom);
			dateDiagnosis.setCellValueFactory(new PropertyValueFactory<>("date"));
			patologyDiagnosis.setCellValueFactory(new PropertyValueFactory<>("ID"));

			// crea la serie dati del grafico
			XYChart.Series<String, Number> series = new XYChart.Series<>();
			XYChart.Series<String, Number> series1 = new XYChart.Series<>();
			// riempie la serie con i dati nell'intervallo di tempo specificato
			for (Diagnosis element : Diagnosi) {
				// controlla che la data sia nell'intervallo specificato
				String dateTimeString = element.getDate();
				String[] parts = dateTimeString.split(" "); // splitta la stringa in data e ora
				int d1 = Integer.parseInt(parts[0].split("-")[0]); // anno
				int d2 = Integer.parseInt(parts[0].split("-")[1]); // mese
				int d3 = Integer.parseInt(parts[0].split("-")[2]); // giorno
				int d4 = Integer.parseInt(parts[1].split(":")[0]); // ore
				int d5 = Integer.parseInt(parts[1].split(":")[1]); // minuti
				int d6 = Integer.parseInt(parts[1].split(":")[2]); // secondi
				LocalDateTime dateTime = LocalDateTime.of(d1, d2, d3, d4, d5, d6);

				if (dateTime.toLocalDate().isAfter(startDate.minusDays(1)) && dateTime.toLocalDate().isBefore(endDate.plusDays(1))) {
					// series.getData().add(new XYChart.Data<>(dateTime.toString(),
					// element.getSBP()));
					series.getData().add(new XYChart.Data<>(dateTime.toString(), element.getSBP()));
					series1.getData().add(new XYChart.Data<>(dateTime.toString(), element.getDBP()));
				}
			}
			
			series.setName("SBP");
			series1.setName("DBP");

			//chart.getData().clear();
			chart.getData().removeAll(Collections.singleton(chart.getData().setAll()));
			chart.setLegendSide(Side.TOP);
			chart.getData().add(series);
			chart.getData().add(series1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}

	@FXML
	void exitButton_clicked(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoaderlogged = new FXMLLoader(controllerPatient.class.getResource("../view/doctor.fxml"));
		Stage currentStage = (Stage) exitButton.getScene().getWindow();
		Scene sceneLogOut = new Scene(fxmlLoaderlogged.load());
		currentStage.setTitle("Login");
		currentStage.setScene(sceneLogOut);
		currentStage.show();
	}

	@FXML
	void addPatologies_clicked(ActionEvent event) throws SQLException {
		String id_pat = (String) choicePatologies.getValue();
		String start = startPath.getValue().toString();
		String end = null;
		
		
		try {
			end = endPath.getValue().toString();
		} catch (Exception e) {
		}
		try {
			if(modify1) {
				try {
					end = endPath.getValue().toString();
				}
				catch(Exception e){
				}
				model.updatePathology(id_pat, infoPerson.getCF(), start, end);
			}
			else {
				model.insertPathology(id_pat, infoPerson.getCF(), start, end);
			}
		} catch (SQLException e) {
		}
		tablePathology.getItems().add(new Pathology(id_pat, "", start, end));
		// System.out.println(id_pat+"start: "+start+" end: "+end);
		
		//rimuovo il record vecchio la tabella
		ObservableList<Pathology> allPh, singlePh;
		allPh = tablePathology.getItems();
		singlePh = tablePathology.getSelectionModel().getSelectedItems();
		singlePh.forEach(allPh::remove);
		
		if (modify1 == true) {
			modify1 = false;
		}
		if (modify1 == false) {
			addPatologies.setText("Insert therapy");
			modifyPathology.setText("Modify");
		}

		clearTherapiesField1();
	}

	@FXML
	void insertTerapyButton_clicked(ActionEvent event) {

		
		if(!quantity.getText().isEmpty() && !description.getText().isEmpty()){
			String CF_doctor = (String) infoPerson.getCF_doctor();
			String CF_patient = session.getCF_shmem();
			String Drug = drugChoice.getValue();
			Integer Quantity = Integer.parseInt(quantity.getText());
			Integer Assumption = Integer.parseInt(assumptionChoice.getValue());
			String Indication = description.getText();

			if (modify == true) {

				Therapy th = therapiesTable.getSelectionModel().getSelectedItem();
				try {
					model.updateTherapy(th.getID(), infoPerson.getCF_doctor(), session.getCF_shmem(), drugChoice.getValue(),
							quantity.getText(), (String) assumptionChoice.getValue(), description.getText(),statusChoice.getValue());
				} catch (SQLException e) {
				}
				
				
				therapiesTable.getItems()
						.add(new Therapy(1, CF_patient, CF_doctor, Drug, Quantity, Assumption, Indication, statusChoice.getValue()));
				ObservableList<Therapy> allTh, singleTh;
				allTh = therapiesTable.getItems();
				singleTh = therapiesTable.getSelectionModel().getSelectedItems();
				singleTh.forEach(allTh::remove);
			} else {
				try {
					model.insertTherapy(infoPerson.getCF_doctor(), session.getCF_shmem(), drugChoice.getValue(),
							quantity.getText(), (String) assumptionChoice.getValue(), description.getText());
				} catch (SQLException e) {
				}
				therapiesTable.getItems()
						.add(new Therapy(1, CF_patient, CF_doctor, Drug, Quantity, Assumption, Indication, "ongoing"));
			}

			if (modify == true) {
				modify = false;
			}
			if (modify == false) {
				insertTerapyButton.setText("Insert therapy");
				modifyTherapy.setText("Modify");
				statusChoice.setVisible(false);
				statusLabel.setVisible(false);
			}

			clearTherapiesField();
		}
		else {
			alertInput.setTitle("Error Input");
    		alertInput.setHeaderText("You need to fill all the fields before!");
            // show the dialog
    		alertInput.show();
		}
		

		// aggiorna tabella terapia
	}

	@FXML
	void addInfo_clicked(ActionEvent event) {

		if(!descriptionInfo.getText().isEmpty()) {
			String CF_doctor = (String) infoPerson.getCF_doctor();
			String infoText = descriptionInfo.getText();
	
			try {
				model.insertGenericInfo(infoPerson.getCF_doctor(), session.getCF_shmem(), descriptionInfo.getText());
				// model.insertTherapy(model.getCfDoctorByCfPatient(session.getCF_shmem()),session.getCF_shmem(),"1",quantity.getText(),(String)
				// assumptionChoice.getValue(),description.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			
			infoTable.getItems().add(new Info(CF_doctor, infoText, dtf.format(now).toString()));
	
			descriptionInfo.clear();
		}
		else {
			alertInput.setTitle("Error Input");
    		alertInput.setHeaderText("Please fill info fields!");
            // show the dialog
    		alertInput.show();
		}

	}
	
	Alert alertInput;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		alertInput = new  Alert(AlertType.NONE);
		alertInput.setAlertType(AlertType.ERROR);

		// System.out.println(session.getCF_shmem());

		String[] choice = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		assumptionChoice.getItems().addAll(choice);
		assumptionChoice.getSelectionModel().selectFirst();

		String[] choice1 = { "ongoing", "ended" };
		statusChoice.getItems().addAll(choice1);
		statusChoice.setVisible(false);
		statusLabel.setVisible(false);

		startDateChart.setValue(LocalDate.of(2000,1,1));
		endDateChart.setValue(LocalDate.of(2040,1,1));
		startPath.setValue(LocalDate.of(2010,1,1));
		
		

		try {
			model = Model.getInstance();
			infoPerson = (Patient) model.retrieveInfoByCF(session.getCF_shmem(), "patient");
			drugChoice.getItems().addAll(model.getAllDrugs());
			drugChoice.getSelectionModel().selectFirst();
			choicePatologies.getItems().addAll(model.getAllPathologies());
			choicePatologies.getSelectionModel().selectFirst();
			hypertensionInfo.setText(infoPerson.getHypertension());
			if(model.checkFollowPatient(infoPerson.getCF())){
				drugThearpy.setText("followed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		labelCF.setText(infoPerson.getName());
		setTablePath();

		// forzo il campo ad accettare solo int
		quantity.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					quantity.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		
		showLineChart();

	}

	private void clearTherapiesField() {
		drugChoice.valueProperty().set(null);
		assumptionChoice.valueProperty().set(null);
		therapiesTable.getSelectionModel().clearSelection(-1);
		quantity.clear();
		description.clear();
	}
	
	private void clearTherapiesField1() {
		startPath.valueProperty().set(null);
		endPath.valueProperty().set(null);
		choicePatologies.valueProperty().set(null);
	}

	private void setTablePath() {

		// tabella info
		infoTable.setItems(infoPerson.getInfos());
		dateInfoColumn.setCellValueFactory(new PropertyValueFactory<>("infoDate"));
		infoInfoColumn.setCellValueFactory(new PropertyValueFactory<>("Info"));
		docInfoColumn.setCellValueFactory(new PropertyValueFactory<>("CF_Doctor"));
		therapiesTable.setEditable(true);

		// tabella therapy
		therapiesTable.setItems(infoPerson.getTherapies());
		drugColumn.setCellValueFactory(new PropertyValueFactory<>("ID_Drug"));
		qtyColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
		assumptionColumn.setCellValueFactory(new PropertyValueFactory<>("Assumptions"));
		indicationColumn.setCellValueFactory(new PropertyValueFactory<>("Indication"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));
		therapiesTable.setEditable(true);

		// tabella pathology
		tablePathology.setItems(infoPerson.getPathologies());
		columnPathology.setCellValueFactory(new PropertyValueFactory<>("ID"));
		columnStart.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
		columnEnd.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
	}

}
