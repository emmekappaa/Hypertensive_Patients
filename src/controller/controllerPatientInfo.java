package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Model;
import model.PatientwithPathology;
import javafx.beans.property.SimpleStringProperty;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private Text labelCF;
	
	ObservableList<PatientwithPathology> patients;

	Session session = Session.getInstance();
	Model model = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// System.out.println(session.getCF_shmem());
		labelCF.setText(session.getCF_shmem());
		
		
		try {
			model = Model.getInstance();
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
