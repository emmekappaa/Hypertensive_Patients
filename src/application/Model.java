package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


public class Model {

    public void log(Object o){
        System.out.println(o);
    }

    private static Model single_instance = null;
    private Connection conn;
    public ObservableList<Person> people;

    public ObservableList<SimpleStringProperty> occupations = FXCollections.observableArrayList();


    public void connect() throws SQLException {

        String url = "jdbc:sqlite:table.db";
        conn = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite has been established.");

    }

    public ResultSet runQuery(String q) throws SQLException {

        ResultSet rs = null;
        Statement stmt  = conn.createStatement();
        rs = stmt.executeQuery(q);
        return rs;

    }

    public void runStatement(String s) throws SQLException {

        Statement stmt  = conn.createStatement();
        stmt.executeUpdate(s);

    }

    public int runStatementWithOutput(String s) throws SQLException {

        int r;
        Statement stmt  = conn.createStatement();
        r = stmt.executeUpdate(s);
        return r;
    }

    private Model() throws SQLException
    {
        connect();
        if (tableExists("doctor"))
        {
            log("doctor table exists");
        }
        else {
            log("doctor table DO NOT exists");
            resetDoctorTable();
        };

        if (tableExists("patient"))
        {
            log("patient table exists");
        }
        else {
            log("patient table DO NOT exists");
            resetPatientTable();
        };

         if (tableExists("patientDoctor"))
        {
            log("patientDoctor table exists");
        }
        else {
            log("patientDoctor table DO NOT exists");
            resetPatientDoctor();
        };

        if (tableExists("diagnosis"))
        {
            log("diagnosis table exists");
        }
        else {
            log("diagnosis table DO NOT exists");
            resetDiagnosis();
        };

        if (tableExists("symptoms"))
        {
            log("symptoms table exists");
        }
        else {
            log("symptoms table DO NOT exists");
            resetSymptoms();
        };

        if (tableExists("diagnosisSymptoms"))
        {
            log("diagnosisSymptoms table exists");
        }
        else {
            log("diagnosisSymptoms table DO NOT exists");
            resetDiagnosisSymptoms();
        };

        if (tableExists("pathology"))
        {
            log("pathology table exists");
        }
        else {
            log("pathology table DO NOT exists");
            resetPathology();
        };

        if (tableExists("pathologySymptoms"))
        {
            log("pathologySymptoms table exists");
        }
        else {
            log("pathologySymptoms table DO NOT exists");
            resetPathologySymptoms();
        };

        if (tableExists("patientPathology"))
        {
            log("patientPathology table exists");
        }
        else {
            log("patientPathology table DO NOT exists");
            resetPatientPathology();
        };

        if (tableExists("therapy"))
        {
            log("therapy table exists");
        }
        else {
            log("therapy table DO NOT exists");
            resetTherapy();
        };

        if (tableExists("drug"))
        {
            log("drug table exists");
        }
        else {
            log("drug table DO NOT exists");
            resetDrug();
        };

        if (tableExists("drugAssumptions"))
        {
            log("drugAssumptions table exists");
        }
        else {
            log("drugAssumptions table DO NOT exists");
            resetDrugAssumptions();
        };
        
        //BRO QUI POPOLI LE TABELLE E FAI ALTRE COSE CHE NON SAPPIAMO ANCORA FARE BHO ECCO
        
    }

    public static synchronized Model getInstance() throws SQLException
    {
        if (single_instance == null)
            single_instance = new Model();

        return single_instance;
    }

    public boolean tableExists(String table_name) throws SQLException {
        String q = "SELECT * FROM sqlite_master WHERE tbl_name = '" + table_name + "'";
        log(q);
        ResultSet rs = runQuery(q);
        return rs.next();
    }
    
    public void resetDoctorTable() throws SQLException{
        String s = "DROP TABLE IF EXISTS doctor;" +
                "CREATE TABLE Doctor (" +
                "CF VARCHAR(16) PRIMARY KEY, " +
                "Name VARCHAR(50) NOT NULL," +
                "Surname VARCHAR(50) NOT NULL, " +
                "Email VARCHAR(100) UNIQUE NOT NULL, " +
                "Password VARCHAR(255) NOT NULL" +
                ");";
        log(s);
        runStatement(s);
    }

    public void resetPatientTable() throws SQLException{
        String s = "DROP TABLE IF EXISTS patient;" +
                "CREATE TABLE patient( " +
                "CF VARCHAR(16) PRIMARY KEY, " +
                "Name VARCHAR(50) NOT NULL, " +
                "Surname VARCHAR(50) NOT NULL, " +
                "Email VARCHAR(100) UNIQUE NOT NULL, " +
                "Password VARCHAR(255) NOT NULL" +
                ");";
        log(s);
        runStatement(s);
    }
    
    public void resetPatientDoctor() throws SQLException{
        String s = "DROP TABLE IF EXISTS patientDoctor;" +
                "CREATE TABLE patientDoctor( " +
                "CF_Doctor VARCHAR(16) NOT NULL, " +
                "CF_Patient VARCHAR(16) NOT NULL, " +
                "Info_Date DATE NOT NULL, " +
                "Info VARCHAR(255) NOT NULL, " +
                "PRIMARY KEY (CF_Doctor, CF_Patient, Info_Date), " +
                "FOREIGN KEY (CF_Doctor) REFERENCES Doctor(CF)," +
                "FOREIGN KEY (CF_Patient) REFERENCES Patient(CF) " +
                ");";
        log(s);
        runStatement(s);
    }
    
    

    public void resetDiagnosis() throws SQLException{
        String s = "DROP TABLE IF EXISTS diagnosis;" +
        		"CREATE TABLE diagnosis( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CF_Patient VARCHAR(16) NOT NULL, " +
                "Date DATE NOT NULL, " +
                "Time TIME NOT NULL, " +
                "SBP INTEGER NOT NULL, " +
                "DBP INTEGER NOT NULL," +
                "FOREIGN KEY (CF_Patient) REFERENCES Patient(CF) " +
        		");";
        log(s);
        runStatement(s);
    }

    public void resetSymptoms() throws SQLException{
        String s = "DROP TABLE IF EXISTS symptoms ;" +
        		"CREATE TABLE symptoms( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Description VARCHAR(255) NOT NULL " +
                ");";
        log(s);
        runStatement(s);
    }

    public void resetDiagnosisSymptoms() throws SQLException{
        String s = "DROP TABLE IF EXISTS diagnosisSymptoms ;" +
        		"CREATE TABLE diagnosisSymptoms( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ID_Diagnosis INTEGER NOT NULL, " +
                "ID_Symptom INTEGER NOT NULL, " +
                "FOREIGN KEY (ID_Diagnosis) REFERENCES Diagnosis(ID), " +
                "FOREIGN KEY (ID_Symptom) REFERENCES Symptoms(ID) " +
                ");";
        log(s);
        runStatement(s);
    }

    public void resetPathology() throws SQLException{
        String s = "DROP TABLE IF EXISTS pathology ;" +
        		"CREATE TABLE pathology( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Description VARCHAR(255) NOT NULL " +
                ");";
        log(s);
        runStatement(s);
    }

     public void resetPathologySymptoms() throws SQLException{
        String s = "DROP TABLE IF EXISTS pathologySymptoms ;" +
        		"CREATE TABLE pathologySymptoms( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ID_Pathology INTEGER NOT NULL, " +
                "ID_Symptom INTEGER NOT NULL, " +
                "FOREIGN KEY (ID_Pathology) REFERENCES pathology(ID), " +
                "FOREIGN KEY (ID_Symptom) REFERENCES symptoms(ID) " +
                ");";
        log(s);
        runStatement(s);
    }

    public void resetPatientPathology() throws SQLException{
        String s = "DROP TABLE IF EXISTS patientPathology;"+
                   "CREATE TABLE patientPathology ("+
                   "ID_Pathology INTEGER NOT NULL,"+
                   "CF_Patient VARCHAR(16) NOT NULL,"+
                   "Start_Date DATE NOT NULL,"+
                   "End_Date DATE,"+
                   "PRIMARY KEY (ID_Pathology, CF_Patient),"+
                   "FOREIGN KEY (ID_Pathology) REFERENCES pathology(ID),"+
                   "FOREIGN KEY (CF_Patient) REFERENCES patient(CF)"+
                   ");";
        log(s);
        runStatement(s);
    }

    public void resetTherapy() throws SQLException{
        String s = "DROP TABLE IF EXISTS therapy;"+
                   "CREATE TABLE therapy (" +
                   "ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "CF_Patient VARCHAR(16) NOT NULL,"+
                    "CF_Doctor VARCHAR(16) NOT NULL,"+
                    "ID_Drug INTEGER NOT NULL,"+
                    "Quantity INTEGER NOT NULL,"+
                    "Assumptions INTEGER NOT NULL,"+
                    "Indication VARCHAR(255) NOT NULL,"+
                    "Status VARCHAR(50) NOT NULL,"+
                    "FOREIGN KEY (CF_Patient) REFERENCES patient(CF),"+
                    "FOREIGN KEY (CF_Doctor) REFERENCES doctor(CF),"+
                    "FOREIGN KEY (ID_Drug) REFERENCES drug(ID)"+
                    ");";
        log(s);
        runStatement(s);
    }

    public void resetDrug() throws SQLException{
        String s = "DROP TABLE IF EXISTS drug;" +
                   "CREATE TABLE drug (" +
                   "ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                   "Description VARCHAR(255) NOT NULL" +
        		   ");";	
        log(s);
        runStatement(s);
    }

    public void resetDrugAssumptions() throws SQLException{
        String s = "DROP TABLE IF EXISTS drugAssumptions;" +
                "CREATE TABLE drugAssumptions (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Patient_ID VARCHAR(16),"+
                "Drug_ID INTEGER,"+
                "Date DATE,"+
                "Time TIME,"+
                "Quantity FLOAT,"+
                "FOREIGN KEY (Patient_ID) REFERENCES Patients(CF),"+
                "FOREIGN KEY (Drug_ID) REFERENCES Drugs(ID)" +
                ");";
        log(s);
        runStatement(s);
    }


//Qua sotto cose vecchie


    
    public void setPersonName(int id, String name) throws SQLException{
        String s = "UPDATE people SET name = '" + name + "' WHERE id = " + id;
        log(s);
        runStatement(s);
    }

    public void loadPeople() throws SQLException{
        people = FXCollections.<Person>observableArrayList(
                person -> new Observable[] {
                        person.nameProperty(), person.birthdateProperty()  }
        );
        String q = "SELECT * FROM people ORDER BY id";
        log(q);
        ResultSet rs = runQuery(q);
        while (rs.next()){
            people.add(new Person(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDate("birthdate") == null ? null :  rs.getDate("birthdate").toLocalDate()));
        }
    }

    public void loadOccupations() throws SQLException{
        String q = "SELECT name FROM occupations ORDER BY name";
        log(q);
        ResultSet rs = runQuery(q);
        while (rs.next()){
            String r = rs.getString("name");
            if(occupations.filtered(p -> p.getValue().equals(r)).isEmpty())
                occupations.add(new SimpleStringProperty(r));
        }
    }

    public TableColumn<Person, String> getPeopleNames() {
        TableColumn<Person, String> r = new TableColumn<>("name");
        r.setCellValueFactory(new PropertyValueFactory<>("name"));
        r.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        r.setOnEditCommit(e -> {
            try{
                setPersonName(e.getRowValue().getId(), e.getNewValue());
            }
            catch( SQLException sql_e ){
                log("error updating person" + e.getRowValue().getId());
            }
            e.getRowValue().setName(e.getNewValue());
        });
        return r;
    }

    public TableColumn<Person, LocalDate> getPeopleBirthdates(){
        TableColumn<Person, LocalDate> r = new TableColumn<>("birthdate");
        r.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        return r;
    }

    public void create_occupation(String name) throws SQLException{
        String q = "INSERT INTO Occupations(name)\n" +
                "VALUES ('"+ name +"');";
        log(q);
        runStatement(q);
    }

    public Person create_person(String name, LocalDate birthdate) throws SQLException, ParseException {

        log(birthdate.toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(((Date) df.parse(birthdate.toString())).getTime());
        Long bdate = df.parse(birthdate.toString()).getTime();
        String q = "INSERT INTO People(name, birthdate)\n" +
                "VALUES ('"+ name +"', "+ bdate +")\n" +
                "RETURNING id;";
        int id = runStatementWithOutput(q);
        return new Person(id, name, birthdate);



    }


}
