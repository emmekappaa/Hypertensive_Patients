package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Model;
import model.Patient;
import model.PatientwithPathology;

public class controllerPatientInfo implements Initializable {
	

    @FXML
    private TableView<PatientwithPathology> tablePathology;
    
	@FXML
	private TableColumn<PatientwithPathology, String> columnEnd;

	@FXML
	private TableColumn<PatientwithPathology, String> columnPathology;

	@FXML
	private TableColumn<PatientwithPathology, String> columnStart;
	
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
	
	ObservableList<PatientwithPathology> patients;

	Session session = Session.getInstance();
	Model model = null;
	
	@FXML
	void insertTerapyButton_clicked(ActionEvent event){
		//inserisci terapia
		//model.insertTherapy("F33S",session.getCF_shmem(),(String) drugChoice.getValue(),quantity.getText(),(String) assumptionChoice.getValue(),description.getText());
		try {
			model.insertTherapy(model.getCfDoctorByCfPatient(session.getCF_shmem()),session.getCF_shmem(),"1",quantity.getText(),(String) assumptionChoice.getValue(),description.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		//aggiorna tabella terapia
		//DA FARE
	}
	
	@FXML
    void addInfo_clicked(ActionEvent event) {
		try {
			model.insertGenericInfo(model.getCfDoctorByCfPatient(session.getCF_shmem()),session.getCF_shmem(),descriptionInfo.getText());
			//model.insertTherapy(model.getCfDoctorByCfPatient(session.getCF_shmem()),session.getCF_shmem(),"1",quantity.getText(),(String) assumptionChoice.getValue(),description.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
    }
	
	
	

	 
	 
	 
	 
	 
	 
	 
	 
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// System.out.println(session.getCF_shmem());
		labelCF.setText(session.getCF_shmem());
		
		String[] choice = { "1", "2","3", "4","5","6","7","8", "9","10",};
		assumptionChoice.getItems().addAll(choice);
		
		//forzo il campo ad accettare solo int
		quantity.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	quantity.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		
		try {
			model = Model.getInstance();
			drugChoice.getItems().addAll(model.getAllDrugs());
			patients = model.getPatientsWithPathology(session.getCF_shmem());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tablePathology.setItems(patients);
		columnEnd.setCellValueFactory(cellData -> {
		    SimpleStringProperty property = new SimpleStringProperty();
		    if (cellData.getValue().getEndDate() != null) {
		        long timestamp = Long.parseLong(cellData.getValue().getEndDate());
		        Date date = new Date(timestamp);
		        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        property.setValue(dateFormat.format(date));
		    }
		    return property;
		});
		columnPathology.setCellValueFactory(new PropertyValueFactory<>("pathologyDescription"));
		
		columnStart.setCellValueFactory(cellData -> {
		    SimpleStringProperty property = new SimpleStringProperty();
		    if (cellData.getValue().getStartDate() != null) {
		        long timestamp = Long.parseLong(cellData.getValue().getStartDate());
		        Date date = new Date(timestamp);
		        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        property.setValue(dateFormat.format(date));
		    }
		    return property;
		});

	}
}
