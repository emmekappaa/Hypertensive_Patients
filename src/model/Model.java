package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
	// test per michele che è bravo
	public void log(Object o) {
		System.out.println(o);
	}

	private static Model single_instance = null;
	private Connection conn;

	/*
	 * DA ELIMINARE QUESTE DUE QUI SOTTO
	 */
	public ObservableList<SimpleStringProperty> occupations = FXCollections.observableArrayList();

	public void connect() throws SQLException {

		String url = "jdbc:sqlite:table.db";
		conn = DriverManager.getConnection(url);
		System.out.println("Connection to SQLite has been established.");

	}

	public ResultSet runQuery(String q) throws SQLException {

		ResultSet rs = null;
		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(q);
		return rs;

	}

	public void runStatement(String s) throws SQLException {

		Statement stmt = conn.createStatement();
		stmt.executeUpdate(s);

	}

	public int runStatementWithOutput(String s) throws SQLException {

		int r;
		Statement stmt = conn.createStatement();
		r = stmt.executeUpdate(s);
		return r;
	}

	private Model() throws SQLException {
		connect();
		if (tableExists("doctor")) {
			log("doctor table exists");
		} else {
			log("doctor table DO NOT exists");
			resetDoctorTable();
		}
		;

		if (tableExists("patient")) {
			log("patient table exists");
		} else {
			log("patient table DO NOT exists");
			resetPatientTable();
		}
		;

		if (tableExists("patientDoctor")) {
			log("patientDoctor table exists");
		} else {
			log("patientDoctor table DO NOT exists");
			resetPatientDoctor();
		}
		;

		if (tableExists("diagnosis")) {
			log("diagnosis table exists");
		} else {
			log("diagnosis table DO NOT exists");
			resetDiagnosis();
		}
		;

		if (tableExists("symptom")) {
			log("symptom table exists");
		} else {
			log("symptom table DO NOT exists");
			resetSymptoms();
		}
		;

		if (tableExists("diagnosisSymptoms")) {
			log("diagnosisSymptoms table exists");
		} else {
			log("diagnosisSymptoms table DO NOT exists");
			resetDiagnosisSymptoms();
		}
		;

		if (tableExists("pathology")) {
			log("pathology table exists");
		} else {
			log("pathology table DO NOT exists");
			resetPathology();
		}
		;

		if (tableExists("patientPathology")) {
			log("patientPathology table exists");
		} else {
			log("patientPathology table DO NOT exists");
			resetPatientPathology();
		}
		;

		if (tableExists("therapy")) {
			log("therapy table exists");
		} else {
			log("therapy table DO NOT exists");
			resetTherapy();
		}
		;

		if (tableExists("drug")) {
			log("drug table exists");
		} else {
			log("drug table DO NOT exists");
			resetDrug();
		}
		;

		if (tableExists("drugAssumptions")) {
			log("drugAssumptions table exists");
		} else {
			log("drugAssumptions table DO NOT exists");
			resetDrugAssumptions();
		}
		;

		// BRO QUI POPOLI LE TABELLE E FAI ALTRE COSE CHE NON SAPPIAMO ANCORA FARE BHO
		// ECCO

	}

	public static synchronized Model getInstance() throws SQLException {
		if (single_instance == null)
			single_instance = new Model();

		return single_instance;
	}

	public boolean tableExists(String table_name) throws SQLException {
		String q = "SELECT * FROM sqlite_master WHERE tbl_name = '" + table_name + "'";
		// log(q);
		ResultSet rs = runQuery(q);
		return rs.next();
	}

	public void resetDoctorTable() throws SQLException {
		String s = "DROP TABLE IF EXISTS doctor;" + "CREATE TABLE doctor (" + "CF VARCHAR(16) PRIMARY KEY, "
				+ "Name VARCHAR(50) NOT NULL," + "Surname VARCHAR(50) NOT NULL, "
				+ "Email VARCHAR(100) UNIQUE NOT NULL, " + "Password VARCHAR(255) NOT NULL" + ");";
		log(s);
		runStatement(s);
	}

	public void resetPatientTable() throws SQLException {
		String s = "DROP TABLE IF EXISTS patient;" + "CREATE TABLE patient( " + "CF VARCHAR(16) PRIMARY KEY, "
				+ "Name VARCHAR(50) NOT NULL, " + "Surname VARCHAR(50) NOT NULL, "
				+ "Email VARCHAR(100) UNIQUE NOT NULL, " + "Password VARCHAR(255) NOT NULL,"
				+ "CF_doctor VARCHAR(16) NOT NULL," + "FOREIGN KEY (CF_doctor) REFERENCES doctor(CF) " + ");";
		log(s);
		runStatement(s);
	}

	public void resetPatientDoctor() throws SQLException {
		String s = "DROP TABLE IF EXISTS patientDoctor;" + "CREATE TABLE patientDoctor( "
				+ "CF_Doctor VARCHAR(16) NOT NULL, " + "CF_Patient VARCHAR(16) NOT NULL, " + "Info_Date DATE NOT NULL, "
				+ "Info VARCHAR(255) NOT NULL, " + "PRIMARY KEY (CF_Doctor, CF_Patient, Info_Date), "
				+ "FOREIGN KEY (CF_Doctor) REFERENCES doctor(CF)," + "FOREIGN KEY (CF_Patient) REFERENCES patient(CF) "
				+ ");";
		log(s);
		runStatement(s);
	}

	public void resetDiagnosis() throws SQLException {
		String s = "DROP TABLE IF EXISTS diagnosis;" + "CREATE TABLE diagnosis( "
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "CF_Patient VARCHAR(16) NOT NULL, "
				+ "Date DATE NOT NULL, " + "Time TIME NOT NULL, " + "SBP INTEGER NOT NULL, " + "DBP INTEGER NOT NULL,"
				+ "FOREIGN KEY (CF_Patient) REFERENCES patient(CF) " + ");";
		log(s);
		runStatement(s);
	}

	public void resetSymptoms() throws SQLException {
		String s = "DROP TABLE IF EXISTS symptom ;" + "CREATE TABLE symptom( "
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "Description VARCHAR(255) NOT NULL " + ");";
		log(s);
		runStatement(s);
	}

	public void resetDiagnosisSymptoms() throws SQLException {
		String s = "DROP TABLE IF EXISTS diagnosisSymptoms ;" + "CREATE TABLE diagnosisSymptoms( "
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "ID_Diagnosis INTEGER NOT NULL, "
				+ "ID_Symptom INTEGER NOT NULL, " + "FOREIGN KEY (ID_Diagnosis) REFERENCES diagnosis(ID), "
				+ "FOREIGN KEY (ID_Symptom) REFERENCES symptom(ID) " + ");";
		log(s);
		runStatement(s);
	}

	public void resetPathology() throws SQLException {
		String s = "DROP TABLE IF EXISTS pathology ;" + "CREATE TABLE pathology( "
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "Description VARCHAR(255) NOT NULL " + ");";
		log(s);
		runStatement(s);
	}

	public void resetPatientPathology() throws SQLException {
		String s = "DROP TABLE IF EXISTS patientPathology;" + "CREATE TABLE patientPathology ("
				+ "ID_Pathology INTEGER NOT NULL," + "CF_Patient VARCHAR(16) NOT NULL," + "Start_Date DATE NOT NULL,"
				+ "End_Date DATE," + "PRIMARY KEY (ID_Pathology, CF_Patient),"
				+ "FOREIGN KEY (ID_Pathology) REFERENCES pathology(ID),"
				+ "FOREIGN KEY (CF_Patient) REFERENCES patient(CF)" + ");";
		log(s);
		runStatement(s);
	}

	public void resetTherapy() throws SQLException {
		String s = "DROP TABLE IF EXISTS therapy;" + "CREATE TABLE therapy (" + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "CF_Patient VARCHAR(16) NOT NULL," + "CF_Doctor VARCHAR(16) NOT NULL," + "ID_Drug INTEGER NOT NULL,"
				+ "Quantity INTEGER NOT NULL," + "Assumptions INTEGER NOT NULL," + "Indication VARCHAR(255) NOT NULL,"
				+ "Status VARCHAR(50) NOT NULL," + "FOREIGN KEY (CF_Patient) REFERENCES patient(CF),"
				+ "FOREIGN KEY (CF_Doctor) REFERENCES doctor(CF)," + "FOREIGN KEY (ID_Drug) REFERENCES drug(ID)" + ");";
		log(s);
		runStatement(s);
	}

	public void resetDrug() throws SQLException {
		String s = "DROP TABLE IF EXISTS drug;" + "CREATE TABLE drug (" + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "Description VARCHAR(255) NOT NULL" + ");";
		log(s);
		runStatement(s);
	}

	public void resetDrugAssumptions() throws SQLException {
		String s = "DROP TABLE IF EXISTS drugAssumptions;" + "CREATE TABLE drugAssumptions ("
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT," + "Patient_ID VARCHAR(16)," + "Drug_ID INTEGER,"
				+ "Date DATE," + "Time TIME," + "Quantity FLOAT," + "FOREIGN KEY (Patient_ID) REFERENCES patient(CF),"
				+ "FOREIGN KEY (Drug_ID) REFERENCES drug(ID)" + ");";
		log(s);
		runStatement(s);
	}

	public boolean checkLogin(String email, String psw, String criptedPsw) {

		String psw_stored_DB = criptedPsw;
		String encryptedpassword = null;
		try {

			/* MessageDigest instance for MD5. */
			MessageDigest m = MessageDigest.getInstance("MD5");

			/* Add plain-text password bytes to digest using MD5 update() method. */
			m.update(psw.getBytes());

			/* Convert the hash value into bytes */
			byte[] bytes = m.digest();

			/*
			 * The bytes array has bytes in decimal form. Converting it into hexadecimal
			 * format.
			 */
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			/* Complete hashed password in hexadecimal format */
			encryptedpassword = s.toString();

			if (encryptedpassword.equals(psw_stored_DB)) {
				return true;
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return false;
	}

	public Map<String, String> getCredentials(String table) throws SQLException {

		Map<String, String> credentials = new HashMap<>();
		String query = "SELECT Email, Password FROM " + table;
		// log(query);
		ResultSet rs = runQuery(query);

		while (rs.next()) {
			String email = rs.getString("Email");
			String password = rs.getString("Password");
			credentials.put(email, password);
		}

		return credentials;
	}

	public Object retrieveInfoByEmail(String email, String table) throws SQLException {
		String q = "SELECT * FROM " + table + " WHERE email='" + email + "'";
		log(q);
		ResultSet rs = runQuery(q);
		Object tmp = null;
		String CF = " ";
		String name = " ";
		String surname = " ";
		String password = " ";
		String cf_doctor = " ";

		if (rs.next()) {
			// Riempio i campi dell'oggetto Doctor con le informazioni ottenute dalla query
			CF = rs.getString("CF");
			name = rs.getString("name");
			surname = rs.getString("surname");
			password = rs.getString("password");
			if (table.equals("patient")) {
				cf_doctor = rs.getString("CF_doctor");
			}

		}

		if (table.equals("doctor")) {
			tmp = new Doctor(CF, name, surname, email, password);
		} else {
			tmp = new Patient(CF, name, surname, email, password, cf_doctor);
		}

		return tmp;
	}
	
	public Object retrieveInfoByCF(String cf, String table) throws SQLException {
		String q = "SELECT * FROM " + table + " WHERE CF='" + cf + "'";
		log(q);
		ResultSet rs = runQuery(q);
		Object tmp = null;
		String name = " ";
		String surname = " ";
		String password = " ";
		String cf_doctor = " ";
		String email = " ";

		if (rs.next()) {
			// Riempio i campi dell'oggetto Doctor con le informazioni ottenute dalla query
			name = rs.getString("name");
			surname = rs.getString("surname");
			password = rs.getString("password");
			email = rs.getString("email");
			if (table.equals("patient")) {
				cf_doctor = rs.getString("CF_doctor");
			}

		}

		if (table.equals("doctor")) {
			tmp = new Doctor(cf, name, surname, email, password);
		} else {
			tmp = new Patient(cf, name, surname, email, password, cf_doctor);
		}

		return tmp;
	}

	public ObservableList<Patient> getPatientsByDoctor(String doctorCF) throws SQLException {

		ObservableList<Patient> patientList = FXCollections.observableArrayList();
		String query = "SELECT * FROM patient WHERE CF_doctor ='" + doctorCF + "'";
		log(query);
		ResultSet rs = runQuery(query);

		while (rs.next()) {
			String CF = rs.getString("CF");
			String name = rs.getString("Name");
			String surname = rs.getString("Surname");
			String email = rs.getString("Email");
			String password = rs.getString("Password");
			String CF_doctor = rs.getString("CF_doctor");
			Patient patient = new Patient(CF, name, surname, email, password, CF_doctor);
			patientList.add(patient);
		}
		
		return patientList;
	}
	
	public ObservableList<Therapy> getPatientTherapies(String CF) throws SQLException {

		ObservableList<Therapy> list = FXCollections.observableArrayList();
		String query = "SELECT * FROM therapy WHERE CF_Patient ='" + CF + "'";
		log(query);
		ResultSet rs = runQuery(query);

		while (rs.next()) {
			int id = rs.getInt("ID");
			String cf_patient = rs.getString("CF_Patient");
			String cf_doctor = rs.getString("CF_Patient");
			String id_drug = rs.getString("ID_Drug");
			int quantity = rs.getInt("Quantity");
			int assumption = rs.getInt("Assumptions");
			String indication = rs.getString("Indication");
			String status = rs.getString("Status");
			
			Therapy therapy = new Therapy(id,cf_patient,cf_doctor,id_drug,quantity,assumption,indication,status);
			//Therapy therapy = new Therapy(1,"","","",1,1,"","");
			list.add(therapy);
		}
		return list;
	}
	
	public ObservableList<Info> getPatientInfos(String CF) throws SQLException {
		
		ObservableList<Info> list = FXCollections.observableArrayList();
		String query = "SELECT * FROM patientDoctor WHERE CF_Patient ='" + CF + "'";
		log(query);
		ResultSet rs = runQuery(query);

		while (rs.next()) {
			String CF_Doctor = rs.getString("CF_Doctor");
			String infoText = rs.getString("Info");
			String infoDate = rs.getString("Info_Date");
			
			Info info_tmp = new Info(CF_Doctor,infoText,infoDate);
			list.add(info_tmp);
		}
		return list;
	}
	
	
	
	public ObservableList<Pathology> getPatientPathologies(String CF) throws SQLException {

		ObservableList<Pathology> list = FXCollections.observableArrayList();
		String query = "SELECT * FROM patientPathology WHERE CF_Patient ='" + CF + "'";
		log(query);
		ResultSet rs = runQuery(query);

		while (rs.next()) {
			String id = rs.getString("ID_Pathology");
			String start = rs.getString("Start_Date");
			String end = rs.getString("End_Date");
			Pathology pathology = new Pathology(id, "",start, end);
			list.add(pathology);
		}
		
		return list;
	}
	
	public String getCfDoctorByCfPatient(String patientCF) throws SQLException {

		String query = "SELECT CF_doctor FROM patient WHERE CF ='" + patientCF + "'";
		log(query);
		ResultSet rs = runQuery(query);
		
		//senza il next perche questa funzione viene usata in un punto dove il dottore entra nel suo paziente, quindi per forza di cose questa query DOVRA dare sempre una e una sola riga 
		return rs.getString("CF_doctor");
		
	}
	
	public ObservableList<Symptom> getDiagnosiSymptom(int ID) throws SQLException {

		ObservableList<Symptom> list = FXCollections.observableArrayList();
		String query = "SELECT diagnosis.Date, ds.ID_Symptom FROM diagnosis INNER JOIN diagnosisSymptoms ds ON diagnosis.ID = ds.ID_Diagnosis WHERE diagnosis.ID='"+ID+"'";
		log(query);
		ResultSet rs = runQuery(query);
		while (rs.next()) {
			String id = rs.getString("ID_Symptom");
			String date = rs.getString("Date");
			Symptom symptom = new Symptom(id,date);
			list.add(symptom);
		}
		return list;
	}
	
	
	
	
	
	public ObservableList<Diagnosis> getDiagnosis(String start,String end) throws SQLException {

		ObservableList<Diagnosis> list = FXCollections.observableArrayList();
		String query = "SELECT * FROM diagnosis WHERE DATE BETWEEN '"+start+"' and '"+end+"'";
		log(query);
		ResultSet rs = runQuery(query);
		while (rs.next()) {
			int id = rs.getInt("ID");
			String cf_patient = rs.getString("CF_Patient");
			String date = rs.getString("Date");
			int SBP = rs.getInt("SBP");
			int DBP = rs.getInt("DBP");
			Diagnosis diag = new Diagnosis(id,cf_patient,date,SBP,DBP);
			list.add(diag);
		}
		return list;
	}

	
	
	public List<String> getAllDrugs() throws SQLException {

		List<String> lista = new ArrayList<String>();
		String query = "SELECT ID FROM drug";
		//log(query);
		ResultSet rs = runQuery(query);
		while (rs.next()) {
			String result = rs.getString("ID");
			lista.add(result);
		}
		return lista;
	}
	
	public List<String> getAllPathologies() throws SQLException {

		List<String> lista = new ArrayList<String>();
		String query = "SELECT ID FROM pathology";
		//log(query);
		ResultSet rs = runQuery(query);
		while (rs.next()) {
			String result = rs.getString("ID");
			lista.add(result);
		}
		return lista;
	}
	
	public List<String> getAvaiableDrugs(String cf) throws SQLException {

		List<String> lista = FXCollections.observableArrayList();
		String query = "SELECT ID_Drug FROM therapy WHERE CF_Patient='"+cf+"' AND status='ongoing'";
		//log(query);
		ResultSet rs = runQuery(query);
		while (rs.next()) {
			String result = rs.getString("ID_Drug");
			lista.add(result);
		}
		return lista;
	}
	
	
	public void insertTherapy(String idDoctor,String idPatient,String idDrug,String qnty,String Assumptions,String Indication) throws SQLException {
		String query = "INSERT INTO therapy (CF_Patient, CF_Doctor, ID_Drug, Quantity, Assumptions, Indication, Status) VALUES ('"+idDoctor+"', '"+idPatient+"', '"+idDrug+"', "+qnty+", "+Assumptions+", '"+Indication+"', 'ongoing')";
		log(query);
		@SuppressWarnings("unused")
		ResultSet rs = runQuery(query);
	}
	
	
	public void updateTherapy(Integer ID, String idDoctor,String idPatient,String idDrug,String qnty,String Assumptions,String Indication,String status) throws SQLException {
		String query = "UPDATE therapy SET CF_Doctor='" + idDoctor + "', CF_Patient='" + idPatient + "', ID_Drug='" + idDrug + "', Quantity=" + qnty + ", Assumptions=" + Assumptions + ", Indication='" + Indication + "', Status='" + status + "' WHERE ID=" + ID;
		log(query);
		@SuppressWarnings("unused")
		ResultSet rs = runQuery(query);
	}
	
	
	public void insertPathology(String id_pat,String cf,String start, String end) throws SQLException {
		String query;
		if(end == null) {
			query = "INSERT INTO patientPathology (ID_Pathology, CF_Patient, Start_Date) VALUES ('"+id_pat+"', '"+cf+"', '"+start+"')";
		}
		else {
			query = "INSERT INTO patientPathology (ID_Pathology, CF_Patient, Start_Date, End_Date) VALUES ('"+id_pat+"', '"+cf+"', '"+start+"', '"+end+"')";		}
		log(query);
		@SuppressWarnings("unused")
		ResultSet rs = runQuery(query);
	}
	
	public void updatePathology(String id_pat,String cf,String start, String end) throws SQLException{
		
		String query;
		if(end != null)
		{
			query = "UPDATE patientPathology SET ID_Pathology='" + id_pat + "', CF_Patient='" + cf + "', Start_Date='" + start + "', End_Date='" + end + "' WHERE ID_Pathology='" + id_pat + "' AND Start_Date='"+start+"' AND CF_Patient='"+cf+"'";
		}
		else
		{
			query = "UPDATE patientPathology SET ID_Pathology='" + id_pat + "', CF_Patient='" + cf + "', Start_Date='" + start + "' WHERE ID_Pathology='" + id_pat + "' AND Start_Date='"+start+"' AND CF_Patient='"+cf+"'";
		}
		log(query);
		@SuppressWarnings("unused")
		ResultSet rs = runQuery(query);
	}
	
	public void insertGenericInfo(String idDoctor,String idPatient, String info) throws SQLException {
		String query = "INSERT INTO patientDoctor (CF_Doctor, CF_Patient, Info_Date, Info) VALUES ('"+idDoctor+"', '"+idPatient+"','"+java.time.LocalDate.now()+"', '"+info+"')";
		log(query);
		@SuppressWarnings("unused")
		ResultSet rs = runQuery(query);
	}

	

}
