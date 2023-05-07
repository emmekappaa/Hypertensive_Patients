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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Drug;
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
    private TextArea infoDrugArea;


    @FXML
    private Button emailDoctor;
    
    
    
    @FXML
    private ListView<String> symptomList;
    
    // create a alert
    Alert alert1; //per le medicine
    Alert alertInput;
    
    ObservableList<String> symptoms;
    
    @FXML
    void emailDoctor_clicked(ActionEvent event) throws URISyntaxException, IOException, SQLException {
    	if (Desktop.isDesktopSupported()) {
    	    Desktop desktop = Desktop.getDesktop();
    	    if (desktop.isSupported(Desktop.Action.MAIL)) {
    	        URI mailto = new URI("mailto:"+model.getEmailDoctorByCF(infoPerson.getCF_doctor())+"?subject=Need%20help");
    	        desktop.mail(mailto);
    	        
    	    }
    	}
    }
    

    @FXML
    void submitBPM_clicked(ActionEvent event) {
    	
    	if(!dbpField.getText().isEmpty() && !sbpField.getText().isEmpty()) {
	    	ObservableList<String> listone = symptomList.getSelectionModel().getSelectedItems();
	    	ObservableList<String> listoneWithoutduplicate =  FXCollections.observableArrayList();
	    	
	    	//filtro i duplicati
	    	for(String s : listone) {
	    		if(!listoneWithoutduplicate.contains(s)) {
	    			listoneWithoutduplicate.add(s);
	    		}
	    	}
	    	
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			String data = dtf.format(now);
	    	
	    	try {
				model.insertBPM(infoPerson.getCF(),dbpField.getText(),sbpField.getText(),data);
			} catch (SQLException e) {
			}
	    	
	    	
	    	if(!listoneWithoutduplicate.isEmpty()) {
				try {
					model.insertSymptom(listoneWithoutduplicate,model.getIdDiagnosi(infoPerson.getCF(),data));
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			
	    	dbpField.clear();
	    	sbpField.clear();
	    	symptomList.getSelectionModel().clearSelection();
    	}
    	else {
    		alertInput.setTitle("Error insert BPM");
    		alertInput.setHeaderText("Missing parameters (SDB/DBP)");
            // show the dialog
    		alertInput.show();
    	}
	    	
    }



    @FXML
    void takeDrugs_clicked(ActionEvent event) {
    	try {
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
    		LocalDateTime now = LocalDateTime.now();   
			model.takeDrug(infoPerson.getCF(), choiceDrug.getValue(), dtf.format(now));
		}
    	catch (SQLException e) {}
    	
    	//aggiorno infoBox
    	try {
    		infoDrugArea.setText(model.getInfoDrug(choiceDrug.getValue(),infoPerson.getCF()));
    	}
    	catch(Exception e) {
    		System.out.println("Select drug first!");
    	}
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
    	
    	alert1 = new  Alert(AlertType.NONE);
    	alertInput = new  Alert(AlertType.NONE);
    	ArrayList<Drug> lista = null;
    	
    	choiceDrug.getSelectionModel().selectedIndexProperty().addListener(new
    			ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub
				//aggiorno infoBox
		    	try {
		    		infoDrugArea.setText(model.getInfoDrug(choiceDrug.getValue(),infoPerson.getCF()));
		    	}
		    	catch(Exception e) {
		    		System.out.println("Select drug first!");
		    	}
			}
    	});
    	
    	try {
			model = Model.getInstance();
			infoPerson = (Patient)model.retrieveInfoByEmail(session.getMail(),"patient");
			lista = model.getDrugToBeTaken(infoPerson.getCF(),null);
			choiceDrug.getItems().addAll(model.getAvaiableDrugs(infoPerson.getCF()));
			choiceDrug.getSelectionModel().selectFirst();
			symptoms = model.getAllSymptom();
			symptomList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			symptomList.setItems(symptoms);
	    	namePatient1.setText(model.getSurnameDoctorByCF(infoPerson.getCF_doctor()));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	for(Drug s: lista) {
    		System.out.println(s.getDescription());
    	}
    	if(!lista.isEmpty()) {
    		
    		String testoAlert = "Drug to be taken:";
    		for(Drug d : lista) {
    			testoAlert += "\n";
    			testoAlert += " - "+d.getDescription();
    		}
    		
    		// set alert type
        	alert1.setAlertType(AlertType.CONFIRMATION);
        	alert1.setTitle("Drug Reminder");
        	alert1.setHeaderText(testoAlert);
        	alert1.setContentText("Remember to take 'em!");
            // show the dialog
        	alert1.show();
    	}

		//aggiorno infoBox
    	try {
    		infoDrugArea.setText(model.getInfoDrug(choiceDrug.getValue(),infoPerson.getCF()));
    	}
    	catch(Exception e) {
    		System.out.println("Select drug first!");
    	}
    	
    	alertInput.setAlertType(AlertType.ERROR);
    	
    	namePatient.setText(infoPerson.getName());
    	
   	}



}
