 package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

	private static Model single_instance = null;
	private Connection conn;

	/**
	 * Executes an SQL statement that does not return a result set.
	 * 
	 * @param s a String representing the SQL statement to be executed
	 * @throws SQLException if a database access error occurs or the statement fails
	 *                      to execute
	 */
	public void connect() throws SQLException {

		String url = "jdbc:sqlite:table.db";
		conn = DriverManager.getConnection(url);
		System.out.println("Connection to SQLite has been established.");

	}

	/**
	 * 
	 * Logs the given object to the console.
	 * 
	 * @param o the object to be logged
	 */
	public void log(Object o) {
		System.out.println(o);
	}

	/**
	 * 
	 * Executes an SQL statement that returns the number of rows affected.
	 * 
	 * @param s a String representing the SQL statement to be executed
	 * @return an integer value indicating the number of rows affected by the
	 *         statement
	 * @throws SQLException if a database access error occurs or the statement fails
	 *                      to execute
	 */
	public ResultSet runQuery(String q) throws SQLException {

		ResultSet rs = null;
		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(q);
		return rs;

	}

	/**
	 * 
	 * Executes an SQL statement that does not return a result set.
	 * 
	 * @param s a String representing the SQL statement to be executed
	 * @throws SQLException if a database access error occurs or the statement fails
	 *                      to execute
	 */
	public void runStatement(String s) throws SQLException {

		Statement stmt = conn.createStatement();
		stmt.executeUpdate(s);

	}

	/**
	 * 
	 * Constructor method for the Model class. Initializes a new instance of the
	 * database connection and checks if all required tables exist. If any of the
	 * tables does not exist, this method invokes a corresponding reset method to
	 * recreate the table.
	 * 
	 * @throws SQLException if there is an error with the database connection or SQL
	 *                      statements.
	 */
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

	}

	/*
	 * Return singleton Model instance
	 * 
	 * @return Model
	 * 
	 */
	public static synchronized Model getInstance() throws SQLException {
		if (single_instance == null)
			single_instance = new Model();

		return single_instance;
	}

	/*
	 * check if table db exist
	 * 
	 * @param q query string to be fermormed
	 * 
	 * @return ResultSet
	 * 
	 */
	public boolean tableExists(String table_name) throws SQLException {
		String q = "SELECT * FROM sqlite_master WHERE tbl_name = '" + table_name + "'";
		// log(q);
		ResultSet rs = runQuery(q);
		return rs.next();
	}

	/**
	 * 
	 * Resets the doctor table, dropping it if it exists and creating a new table
	 * with the following columns:
	 * <ul>
	 * <li>CF VARCHAR(16) PRIMARY KEY</li>
	 * <li>Name VARCHAR(50) NOT NULL</li>
	 * <li>Surname VARCHAR(50) NOT NULL</li>
	 * <li>Email VARCHAR(100) UNIQUE NOT NULL</li>
	 * <li>Password VARCHAR(255) NOT NULL</li>
	 * </ul>
	 * 
	 * @throws SQLException if an error occurs while executing the SQL statements
	 */
	public void resetDoctorTable() throws SQLException {
		String s = "DROP TABLE IF EXISTS doctor;" + "CREATE TABLE doctor (" + "CF VARCHAR(16) PRIMARY KEY, "
				+ "Name VARCHAR(50) NOT NULL," + "Surname VARCHAR(50) NOT NULL, "
				+ "Email VARCHAR(100) UNIQUE NOT NULL, " + "Password VARCHAR(255) NOT NULL" + ");";
		log(s);
		runStatement(s);
	}

	/**
	 * 
	 * Resets the patient table, dropping it if it exists and creating a new table
	 * with the following columns:
	 * <ul>
	 * <li>CF VARCHAR(16) PRIMARY KEY</li>
	 * <li>Name VARCHAR(50) NOT NULL</li>
	 * <li>Surname VARCHAR(50) NOT NULL</li>
	 * <li>Email VARCHAR(100) UNIQUE NOT NULL</li>
	 * <li>Password VARCHAR(255) NOT NULL</li>
	 * <li>CF_doctor VARCHAR(16) NOT NULL, FOREIGN KEY (CF_doctor) REFERENCES
	 * doctor(CF)</li>
	 * </ul>
	 * 
	 * @throws SQLException if an error occurs while executing the SQL statements
	 */
	public void resetPatientTable() throws SQLException {
		String s = "DROP TABLE IF EXISTS patient;" + "CREATE TABLE patient( " + "CF VARCHAR(16) PRIMARY KEY, "
				+ "Name VARCHAR(50) NOT NULL, " + "Surname VARCHAR(50) NOT NULL, "
				+ "Email VARCHAR(100) UNIQUE NOT NULL, " + "Password VARCHAR(255) NOT NULL,"
				+ "CF_doctor VARCHAR(16) NOT NULL," + "FOREIGN KEY (CF_doctor) REFERENCES doctor(CF) " + ");";
		log(s);
		runStatement(s);
	}

	/**
	 * 
	 * Resets the patientDoctor table, dropping it if it exists and creating a new
	 * table with the following columns:
	 * <ul>
	 * <li>CF_Doctor VARCHAR(16) NOT NULL, FOREIGN KEY (CF_Doctor) REFERENCES
	 * doctor(CF)</li>
	 * <li>CF_Patient VARCHAR(16) NOT NULL, FOREIGN KEY (CF_Patient) REFERENCES
	 * patient(CF)</li>
	 * <li>Info_Date DATE NOT NULL</li>
	 * <li>Info VARCHAR(255) NOT NULL</li>
	 * </ul>
	 * 
	 * @throws SQLException if an error occurs while executing the SQL statements
	 */
	public void resetPatientDoctor() throws SQLException {
		String s = "DROP TABLE IF EXISTS patientDoctor;" + "CREATE TABLE patientDoctor( "
				+ "CF_Doctor VARCHAR(16) NOT NULL, " + "CF_Patient VARCHAR(16) NOT NULL, " + "Info_Date DATE NOT NULL, "
				+ "Info VARCHAR(255) NOT NULL, " + "PRIMARY KEY (CF_Doctor, CF_Patient, Info_Date), "
				+ "FOREIGN KEY (CF_Doctor) REFERENCES doctor(CF)," + "FOREIGN KEY (CF_Patient) REFERENCES patient(CF) "
				+ ");";
		log(s);
		runStatement(s);
	}

	/**
	 * 
	 * Resets the diagnosis table by dropping it if it exists and creating a new
	 * table with columns ID, CF_Patient, Date, Time, SBP, and DBP. Throws an
	 * SQLException if an error occurs while executing the SQL statements.
	 * 
	 * @throws SQLException if an error occurs while executing the SQL statements.
	 */
	public void resetDiagnosis() throws SQLException {
		String s = "DROP TABLE IF EXISTS diagnosis;" + "CREATE TABLE diagnosis( "
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "CF_Patient VARCHAR(16) NOT NULL, "
				+ "Date DATE NOT NULL, " + "Time TIME NOT NULL, " + "SBP INTEGER NOT NULL, " + "DBP INTEGER NOT NULL,"
				+ "FOREIGN KEY (CF_Patient) REFERENCES patient(CF) " + ");";
		log(s);
		runStatement(s);
	}

	/**
	 * 
	 * Resets the symptom table by dropping it if it exists and creating a new table
	 * with columns ID and Description. Throws an SQLException if an error occurs
	 * while executing the SQL statements.
	 * 
	 * @throws SQLException if an error occurs while executing the SQL statements.
	 */
	public void resetSymptoms() throws SQLException {
		String s = "DROP TABLE IF EXISTS symptom ;" + "CREATE TABLE symptom( "
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "Description VARCHAR(255) NOT NULL " + ");";
		log(s);
		runStatement(s);
	}

	/**
	 * 
	 * Resets the diagnosisSymptoms table by dropping it if it exists and creating a
	 * new table with columns ID, ID_Diagnosis, and ID_Symptom. The table has
	 * foreign keys that reference the diagnosis and symptom tables. Throws an
	 * SQLException if an error occurs while executing the SQL statements.
	 * 
	 * @throws SQLException if an error occurs while executing the SQL statements.
	 */
	public void resetDiagnosisSymptoms() throws SQLException {
		String s = "DROP TABLE IF EXISTS diagnosisSymptoms ;" + "CREATE TABLE diagnosisSymptoms( "
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "ID_Diagnosis INTEGER NOT NULL, "
				+ "ID_Symptom INTEGER NOT NULL, " + "FOREIGN KEY (ID_Diagnosis) REFERENCES diagnosis(ID), "
				+ "FOREIGN KEY (ID_Symptom) REFERENCES symptom(ID) " + ");";
		log(s);
		runStatement(s);
	}

	/**
	 * 
	 * Resets the 'pathology' table in the database. Drops the table if it exists
	 * and recreates it with a single column 'Description' of type VARCHAR(255).
	 * 
	 * @throws SQLException if there is an error accessing the database
	 */
	public void resetPathology() throws SQLException {
		String s = "DROP TABLE IF EXISTS pathology ;" + "CREATE TABLE pathology( "
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "Description VARCHAR(255) NOT NULL " + ");";
		log(s);
		runStatement(s);
	}

	/**
	 * 
	 * Resets the 'patientPathology' table in the database. Drops the table if it
	 * exists and recreates it with columns 'ID_Pathology' (integer), 'CF_Patient'
	 * (VARCHAR(16)), 'Start_Date' (DATE), and 'End_Date' (DATE). Sets a composite
	 * primary key on 'ID_Pathology' and 'CF_Patient', and creates foreign key
	 * constraints on 'ID_Pathology' and 'CF_Patient' referencing the 'ID' column of
	 * 'pathology' table and 'CF' column of 'patient' table, respectively.
	 * 
	 * @throws SQLException if there is an error accessing the database
	 */
	public void resetPatientPathology() throws SQLException {
		String s = "DROP TABLE IF EXISTS patientPathology;" + "CREATE TABLE patientPathology ("
				+ "ID_Pathology INTEGER NOT NULL," + "CF_Patient VARCHAR(16) NOT NULL," + "Start_Date DATE NOT NULL,"
				+ "End_Date DATE," + "PRIMARY KEY (ID_Pathology, CF_Patient),"
				+ "FOREIGN KEY (ID_Pathology) REFERENCES pathology(ID),"
				+ "FOREIGN KEY (CF_Patient) REFERENCES patient(CF)" + ");";
		log(s);
		runStatement(s);
	}

	/**
	 * 
	 * Resets the 'therapy' table in the database. Drops the table if it exists and
	 * recreates it with columns 'ID' (integer), 'CF_Patient' (VARCHAR(16)),
	 * 'CF_Doctor' (VARCHAR(16)), 'ID_Drug' (integer), 'Quantity' (integer),
	 * 'Assumptions' (integer), 'Indication' (VARCHAR(255)), and 'Status'
	 * (VARCHAR(50)). Creates foreign key constraints on 'CF_Patient' and
	 * 'CF_Doctor' columns referencing the 'CF' column of 'patient' and 'doctor'
	 * tables, respectively, and creates a foreign key constraint on 'ID_Drug'
	 * referencing the 'ID' column of 'drug' table.
	 * 
	 * @throws SQLException if there is an error accessing the database
	 */
	public void resetTherapy() throws SQLException {
		String s = "DROP TABLE IF EXISTS therapy;" + "CREATE TABLE therapy (" + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "CF_Patient VARCHAR(16) NOT NULL," + "CF_Doctor VARCHAR(16) NOT NULL," + "ID_Drug INTEGER NOT NULL,"
				+ "Quantity INTEGER NOT NULL," + "Assumptions INTEGER NOT NULL," + "Indication VARCHAR(255) NOT NULL,"
				+ "Status VARCHAR(50) NOT NULL," + "FOREIGN KEY (CF_Patient) REFERENCES patient(CF),"
				+ "FOREIGN KEY (CF_Doctor) REFERENCES doctor(CF)," + "FOREIGN KEY (ID_Drug) REFERENCES drug(ID)" + ");";
		log(s);
		runStatement(s);
	}

	/**
	 * 
	 * Resets the 'drug' table in the database. Drops the table if it exists and
	 * recreates it with a single column 'Description' of type VARCHAR(255).
	 * 
	 * @throws SQLException if there is an error accessing the database
	 */
	public void resetDrug() throws SQLException {
		String s = "DROP TABLE IF EXISTS drug;" + "CREATE TABLE drug (" + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "Description VARCHAR(255) NOT NULL" + ");";
		log(s);
		runStatement(s);
	}

	/**
	 * 
	 * Resets the drugAssumptions table by dropping it if it exists and then
	 * creating a new table with the same structure. The table contains information
	 * about the assumptions of drugs made by patients, including the ID of the
	 * patient and the drug, the date and time of the assumption, and the quantity
	 * of the drug assumed.
	 * 
	 * @throws SQLException if there is an error in executing the SQL statements to
	 *                      reset the table
	 */
	public void resetDrugAssumptions() throws SQLException {
		String s = "DROP TABLE IF EXISTS drugAssumptions;" + "CREATE TABLE drugAssumptions ("
				+ "ID INTEGER PRIMARY KEY AUTOINCREMENT," + "Patient_ID VARCHAR(16)," + "Drug_ID INTEGER,"
				+ "Date DATE," + "Time TIME," + "Quantity FLOAT," + "FOREIGN KEY (Patient_ID) REFERENCES patient(CF),"
				+ "FOREIGN KEY (Drug_ID) REFERENCES drug(ID)" + ");";
		log(s);
		runStatement(s);
	}

	/**
	 * 
	 * Checks if the provided plain-text password matches the hashed password stored
	 * in the database.
	 * 
	 * @param email      the email associated with the user
	 * @param psw        the plain-text password entered by the user
	 * @param criptedPsw the hashed password stored in the database
	 * @return true if the plain-text password matches the hashed password, false
	 *         otherwise
	 */
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

	/**
	 * 
	 * Retrieves a map containing email and password for all users of the specified
	 * table.
	 * 
	 * @param table the name of the table containing the users
	 * @return a map where each entry maps an email to its corresponding password
	 * @throws SQLException if a database access error occurs
	 */
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

	/**
	 * 
	 * Retrieves the information associated with a user identified by their email.
	 * 
	 * @param email the email associated with the user
	 * @param table the name of the table containing the user information
	 * @return an object representing the user information, either a Doctor or a
	 *         Patient object
	 * @throws SQLException if a database access error occurs
	 */
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

	/**
	 * 
	 * Retrieves information from a database table for a given CF and returns it as
	 * an Object.
	 * 
	 * @param cf    The CF to search for in the table.
	 * @param table The name of the table to search in.
	 * @return An Object containing the retrieved information.
	 * @throws SQLException if there is an error executing the SQL query.
	 */
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

	/**
	 * 
	 * Retrieves a list of patients from the database who have a specific doctor.
	 * 
	 * @param doctorCF The CF of the doctor to search for.
	 * @return An ObservableList of Patient objects who have the specified doctor.
	 * @throws SQLException if there is an error executing the SQL query.
	 */
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

	/**
	 * 
	 * Retrieves a list of therapies for a given patient from the database.
	 * 
	 * @param CF The CF of the patient to search for.
	 * @return An ObservableList of Therapy objects for the specified patient.
	 * @throws SQLException if there is an error executing the SQL query.
	 */
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

			Therapy therapy = new Therapy(id, cf_patient, cf_doctor, id_drug, quantity, assumption, indication, status);
			// Therapy therapy = new Therapy(1,"","","",1,1,"","");
			list.add(therapy);
		}
		return list;
	}

	/**
	 * 
	 * Retrieves a list of information notes for a given patient from the database.
	 * 
	 * @param CF The CF of the patient to search for.
	 * @return An ObservableList of Info objects for the specified patient.
	 * @throws SQLException if there is an error executing the SQL query.
	 */
	public ObservableList<Info> getPatientInfos(String CF) throws SQLException {

		ObservableList<Info> list = FXCollections.observableArrayList();
		String query = "SELECT * FROM patientDoctor INNER JOIN doctor ON patientDoctor.CF_Doctor=doctor.CF WHERE CF_Patient ='"
				+ CF + "'";
		log(query);
		ResultSet rs = runQuery(query);

		while (rs.next()) {
			String CF_Doctor = rs.getString("Surname");
			String infoText = rs.getString("Info");
			String infoDate = rs.getString("Info_Date");

			Info info_tmp = new Info(CF_Doctor, infoText, infoDate);
			list.add(info_tmp);
		}
		return list;
	}

	/**
	 * Returns a list of pathologies associated with a patient.
	 * 
	 * @param CF the patient's tax code.
	 * @return an ObservableList containing the patient's pathologies.
	 * @throws SQLException if an error occurs while querying the database.
	 */
	public ObservableList<Pathology> getPatientPathologies(String CF) throws SQLException {

		ObservableList<Pathology> list = FXCollections.observableArrayList();
		String query = "SELECT * FROM patientPathology WHERE CF_Patient ='" + CF + "'";
		log(query);
		ResultSet rs = runQuery(query);

		while (rs.next()) {
			String id = rs.getString("ID_Pathology");
			String start = rs.getString("Start_Date");
			String end = rs.getString("End_Date");
			Pathology pathology = new Pathology(id, "", start, end);
			list.add(pathology);
		}

		return list;
	}

	/**
	 * 
	 * Returns the ID of a therapy with a certain set of parameters.
	 * 
	 * @param idDoctor    the tax code of the doctor.
	 * @param idPatient   the tax code of the patient.
	 * @param idDrug      the ID of the drug.
	 * @param qnty        the quantity of the drug.
	 * @param Assumptions the drug's assumptions.
	 * @param Indication  the drug's indication.
	 * @return the ID of the therapy with the specified parameters.
	 * @throws SQLException if an error occurs while querying the database.
	 */
	public int getIDtherapyByAll(String idDoctor, String idPatient, String idDrug, String qnty, String Assumptions,
			String Indication) throws SQLException {
		String query = "SELECT ID FROM therapy WHERE CF_Patient='" + idPatient + "' and CF_Doctor='" + idDoctor
				+ "' and ID_Drug='" + idDrug + "' and Quantity=" + qnty + " and Assumptions=" + Assumptions
				+ " and Indication='" + Indication + "' and Status='ongoing'";
		log(query);
		ResultSet rs = runQuery(query);
		return rs.getInt("ID");
	}

	/**
	 * 
	 * Returns the tax code of a doctor associated with a patient.
	 * 
	 * @param patientCF the tax code of the patient.
	 * @return the tax code of the doctor.
	 * @throws SQLException if an error occurs while querying the database.
	 */
	public String getCfDoctorByCfPatient(String patientCF) throws SQLException {

		String query = "SELECT CF_doctor FROM patient WHERE CF ='" + patientCF + "'";
		log(query);
		ResultSet rs = runQuery(query);

		// senza il next perche questa funzione viene usata in un punto dove il dottore
		// entra nel suo paziente, quindi per forza di cose questa query DOVRA dare
		// sempre una e una sola riga
		return rs.getString("CF_doctor");

	}

	/**
	 * 
	 * Returns a list of symptoms associated with a diagnosis.
	 * 
	 * @param ID the ID of the diagnosis.
	 * @return an ObservableList containing the symptoms of the diagnosis.
	 * @throws SQLException if an error occurs while querying the database.
	 */
	public ObservableList<Symptom> getDiagnosiSymptom(int ID) throws SQLException {

		ObservableList<Symptom> list = FXCollections.observableArrayList();
		String query = "SELECT diagnosis.Date, ds.ID_Symptom FROM diagnosis INNER JOIN diagnosisSymptoms ds ON diagnosis.ID = ds.ID_Diagnosis WHERE diagnosis.ID='"
				+ ID + "'";
		log(query);
		ResultSet rs = runQuery(query);
		while (rs.next()) {
			String id = rs.getString("ID_Symptom");
			String date = rs.getString("Date");
			Symptom symptom = new Symptom(id, date);
			list.add(symptom);
		}
		return list;
	}

	/**
	 * 
	 * Retrieves a list of Diagnosis objects that occurred within a specific date
	 * range.
	 * 
	 * @param start A string representing the start date in the format "yyyy-MM-dd".
	 * @param end   A string representing the end date in the format "yyyy-MM-dd".
	 * @return An ObservableList of Diagnosis objects that occurred within the
	 *         specified date range.
	 * @throws SQLException If there was an error executing the SQL query.
	 */
	public ObservableList<Diagnosis> getDiagnosis(String start, String end) throws SQLException {

		ObservableList<Diagnosis> list = FXCollections.observableArrayList();
		String query = "SELECT * FROM diagnosis WHERE DATE BETWEEN '" + start + "' and '" + end + "'";
		log(query);
		ResultSet rs = runQuery(query);
		while (rs.next()) {
			int id = rs.getInt("ID");
			String cf_patient = rs.getString("CF_Patient");
			String date = rs.getString("Date");
			int SBP = rs.getInt("SBP");
			int DBP = rs.getInt("DBP");
			Diagnosis diag = new Diagnosis(id, cf_patient, date, SBP, DBP);
			list.add(diag);
		}
		return list;
	}

	/**
	 * 
	 * Retrieves a list of all drug IDs from the database.
	 * 
	 * @return A List of Strings containing all drug IDs.
	 * @throws SQLException If there was an error executing the SQL query.
	 */
	public List<String> getAllDrugs() throws SQLException {

		List<String> lista = new ArrayList<String>();
		String query = "SELECT ID FROM drug";
		// log(query);
		ResultSet rs = runQuery(query);
		while (rs.next()) {
			String result = rs.getString("ID");
			lista.add(result);
		}
		return lista;
	}

	/**
	 * 
	 * Retrieves a list of all pathology IDs from the database.
	 * 
	 * @return A List of Strings containing all pathology IDs.
	 * @throws SQLException If there was an error executing the SQL query.
	 */
	public List<String> getAllPathologies() throws SQLException {

		List<String> lista = new ArrayList<String>();
		String query = "SELECT ID FROM pathology";
		// log(query);
		ResultSet rs = runQuery(query);
		while (rs.next()) {
			String result = rs.getString("ID");
			lista.add(result);
		}
		return lista;
	}

	/**
	 * 
	 * Returns a list of all symptoms present in the database.
	 * 
	 * @return an ObservableList of strings containing all the symptom IDs present
	 *         in the database
	 * @throws SQLException if there is an error in the query execution
	 */
	public ObservableList<String> getAllSymptom() throws SQLException {

		ObservableList<String> lista = FXCollections.observableArrayList();
		String query = "SELECT * FROM symptom";
		// log(query);
		ResultSet rs = runQuery(query);
		while (rs.next()) {
			lista.add(rs.getString("ID"));
		}
		return lista;
	}

	/**
	 * 
	 * Returns a list of all drugs currently available to a given patient based on
	 * their CF.
	 * 
	 * @param cf the CF of the patient
	 * @return a List of strings containing the IDs of all drugs currently available
	 *         to the patient
	 * @throws SQLException if there is an error in the query execution
	 */
	public List<String> getAvaiableDrugs(String cf) throws SQLException {

		List<String> lista = FXCollections.observableArrayList();
		String query = "SELECT ID_Drug FROM therapy WHERE CF_Patient='" + cf + "' AND status='ongoing'";
		// log(query);
		ResultSet rs = runQuery(query);
		while (rs.next()) {
			String result = rs.getString("ID_Drug");
			lista.add(result);
		}
		return lista;
	}

	/**
	 * 
	 * Returns information about a given drug for a given patient, including the
	 * number of times it has been taken today.
	 * 
	 * @param drug       the ID of the drug
	 * @param cf_patient the CF of the patient
	 * @return a String containing information about the drug and its current usage
	 * @throws SQLException if there is an error in the query execution
	 */
	public String getInfoDrug(String drug, String cf_patient) throws SQLException {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();

		String info = "";
		String query = "SELECT * FROM therapy WHERE CF_Patient='" + cf_patient + "' and Status='ongoing' and ID_Drug='"
				+ drug + "'";

		String query1 = "SELECT COUNT(*) FROM drugAssumptions WHERE Patient_ID='" + cf_patient + "' and Drug_ID='"
				+ drug + "' and Date BETWEEN '" + dtf.format(now) + "' AND '" + dtf.format(now) + " 23:59:59" + "'";
		// log(query1);
		ResultSet rs = runQuery(query);
		ResultSet rs1 = runQuery(query1);

		while (rs.next()) {
			info += rs.getString("ID_Drug") + ":\n";
			info += "Assumption: " + rs.getString("Assumptions") + "\n";
			info += "Quantity for assumption: " + rs.getString("Quantity") + " " + rs.getString("Indication") + "\n";
			info += "\nDaily assumption: " + rs1.getString("COUNT(*)") + "/" + rs.getString("Assumptions");

		}
		return info;
	}

	/**
	 * 
	 * Returns the email address of the doctor with a given CF.
	 * 
	 * @param cf the CF of the doctor
	 * @return a String containing the email address of the doctor
	 * @throws SQLException if there is an error in the query execution
	 */
	public String getEmailDoctorByCF(String cf) throws SQLException {

		String query = "SELECT Email FROM doctor WHERE CF='" + cf + "'";
		// log(query);
		String email = "mail@mail.com";
		ResultSet rs = runQuery(query);
		try {
			// String stringa = rs.getString("avgData");
			email = rs.getString("Email");
		} catch (Exception e) {
			// System.out.println(e);
		}
		return email;
	}

	/**
	 * 
	 * Returns the surname of the doctor identified by the given CF (fiscal code).
	 * 
	 * @param cf the fiscal code of the doctor
	 * @return the surname of the doctor
	 * @throws SQLException if there's an error executing the SQL query
	 */
	public String getSurnameDoctorByCF(String cf) throws SQLException {

		String query = "SELECT Surname FROM doctor WHERE CF='" + cf + "'";
		// log(query);
		String name = "mail@mail.com";
		ResultSet rs = runQuery(query);
		try {
			// String stringa = rs.getString("avgData");
			name = rs.getString("Surname");
		} catch (Exception e) {
			// System.out.println(e);
		}
		return name;
	}

	/**
	 * 
	 * Checks whether the patient identified by the given CF (fiscal code) has taken
	 * drugs prescribed within the last three days.
	 * 
	 * @param cf the fiscal code of the patient
	 * @return true if the patient hasn't taken drugs within the last three days,
	 *         false otherwise
	 * @throws SQLException if there's an error executing the SQL query
	 */
	public boolean checkFollowPatient(String cf) throws SQLException {
		LocalDateTime now = LocalDateTime.now();

		LocalDateTime d1 = now.minusDays(1);
		LocalDateTime d2 = now.minusDays(2);
		LocalDateTime d3 = now.minusDays(2);

		if (getDrugToBeTaken(cf, d1).isEmpty() && getDrugToBeTaken(cf, d2).isEmpty()
				&& getDrugToBeTaken(cf, d3).isEmpty())
			return true;

		return false;
	}

	/**
	 * 
	 * Returns the drugs that should be taken by the patient identified by the given
	 * CF (fiscal code) on the specified date. The drugs are returned as an
	 * ArrayList of Drug objects.
	 * 
	 * @param cf   the fiscal code of the patient
	 * @param date the date for which the drugs should be returned. If null, the
	 *             current date is used.
	 * @return the drugs that should be taken by the patient on the specified date
	 * @throws SQLException if there's an error executing the SQL query
	 */
	public ArrayList<Drug> getDrugToBeTaken(String cf, LocalDateTime date) throws SQLException {

		ArrayList<Drug> listaAssumption = new ArrayList<>();

		HashMap<String, Integer> listDrug = new HashMap<String, Integer>();
		HashMap<String, Integer> listDrugTaken = new HashMap<String, Integer>();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = null;
		if (date == null) {
			now = LocalDateTime.now();
		} else {
			now = date;
		}

		String query = "SELECT DISTINCT ID_Drug as drug, Assumptions as qty From therapy WHERE CF_Patient='" + cf
				+ "' and Status='ongoing'";
		ResultSet rs = runQuery(query);
		// log(query);
		String query1 = "SELECT Drug_ID as drug,sum(Quantity) as qty FROM drugAssumptions WHERE Patient_ID='" + cf
				+ "' and Date BETWEEN '" + dtf.format(now) + "' and '" + dtf.format(now)
				+ " 23:59:59' GROUP BY Drug_ID";
		// log(query1);
		ResultSet rs1 = runQuery(query1);

		// popolo hasmap con medicine che dovrei prendere giornalmente
		while (rs.next()) {
			listDrug.put(rs.getString("drug"), rs.getInt("qty"));
		}

		while (rs1.next()) {
			listDrugTaken.put(rs1.getString("drug"), rs1.getInt("qty"));
			// System.out.println(rs1.getString("drug"));
			// System.out.println(rs1.getString("qty"));
		}

		for (String s : listDrug.keySet()) {

			if (listDrugTaken.get(s) != null) {
				if (listDrug.get(s) - listDrugTaken.get(s) > 0) { // se devo ancora prendere altre assunzioni di questo
																	// medicinale
					listaAssumption.add(new Drug(listDrugTaken.get(s), listDrug.get(s) - listDrugTaken.get(s), s));
					// System.out.println("qu");
				}

				// System.out.println("q");
			} else {
				listaAssumption.add(new Drug(0, listDrug.get(s), s)); // aggiungo medicina mai presa di oggi
				// System.out.println("qua");
			}

		}
		return listaAssumption;
	}

	/**
	 * 
	 * Retrieves the average value of the specified column from the diagnosis table
	 * for the patient with the given CF code in the past four weeks from the
	 * specified date and returns the average as an integer.
	 * 
	 * @param column the column name to retrieve the average value from
	 * @param CF     the CF code of the patient to retrieve the data for
	 * @param now    the current date and time as a LocalDateTime object
	 * @return the average value of the specified column for the patient in the past
	 *         four weeks
	 * @throws SQLException if there is an error in the SQL query execution
	 */
	public int getBPM(String column, String CF, LocalDateTime now) throws SQLException {

		long average = 0;
		LocalDateTime past = now.minusWeeks(4);

		String query = "SELECT AVG(" + column + ") as avgData FROM diagnosis WHERE CF_Patient='" + CF
				+ "' and Date BETWEEN '" + past.toString() + "' and '" + now.toString() + "'";
		// log(query);
		ResultSet rs = runQuery(query);
		try {
			// String stringa = rs.getString("avgData");
			average = rs.getInt("avgData");
		} catch (Exception e) {
			// System.out.println(e);
		}

		// System.out.println(average);
		return (int) average;
	}

	/**
	 * 
	 * Retrieves the ID of the diagnosis record for the patient with the given CF
	 * code and the specified date from the diagnosis table and returns the ID as a
	 * string.
	 * 
	 * @param cf   the CF code of the patient to retrieve the data for
	 * @param date the date of the diagnosis as a string in the format "yyyy-MM-dd
	 *             HH:mm:ss"
	 * @return the ID of the diagnosis record for the patient with the given CF code
	 *         and date
	 * @throws SQLException if there is an error in the SQL query execution
	 */
	public String getIdDiagnosi(String cf, String date) throws SQLException {

		String query = "SELECT ID FROM diagnosis WHERE CF_Patient='" + cf + "' and Date='" + date + "'";
		log(query);
		@SuppressWarnings("unused")
		ResultSet rs = runQuery(query);
		while (rs.next()) {
			return rs.getString("ID");
		}
		return "";
	}

	/**
	 * 
	 * Inserts the list of symptoms into the diagnosisSymptoms table for the
	 * diagnosis record with the specified ID.
	 * 
	 * @param lista the list of symptoms to insert
	 * @param id    the ID of the diagnosis record to insert the symptoms for
	 * @throws SQLException if there is an error in the SQL query execution
	 */
	public void insertSymptom(ObservableList<String> lista, String id) throws SQLException {

		for (String symptom : lista) {
			try {
				String query = "INSERT INTO diagnosisSymptoms (ID_Diagnosis, ID_Symptom) VALUES (" + id + ", '"
						+ symptom + "')";
				log(query);
				@SuppressWarnings("unused")
				ResultSet rs = runQuery(query);
			} catch (Exception e) {
			}

		}

	}

	/**
	 * 
	 * Inserts a new therapy record into the therapy table for the specified
	 * patient, doctor, drug, quantity, assumptions, and indication with the status
	 * of "ongoing".
	 * 
	 * @param idDoctor    the ID of the doctor prescribing the therapy
	 * @param idPatient   the ID of the patient receiving the therapy
	 * @param idDrug      the ID of the drug being prescribed for the therapy
	 * @param qnty        the quantity of the drug being prescribed for the therapy
	 * @param Assumptions the assumptions related to the therapy as a string
	 * @param Indication  the indication related to the therapy as a string
	 * @throws SQLException if there is an error in the SQL query execution
	 */
	public void insertTherapy(String idDoctor, String idPatient, String idDrug, String qnty, String Assumptions,
			String Indication) throws SQLException {
		String query = "INSERT INTO therapy (CF_Patient, CF_Doctor, ID_Drug, Quantity, Assumptions, Indication, Status) VALUES ('"
				+ idPatient + "', '" + idDoctor + "', '" + idDrug + "', " + qnty + ", " + Assumptions + ", '"
				+ Indication + "', 'ongoing')";
		log(query);
		@SuppressWarnings("unused")
		ResultSet rs = runQuery(query);
	}

	/**
	 * Adds a new drug assumption to the database for the specified patient, drug,
	 * and date.
	 *
	 * @param idPatient the ID of the patient to add the drug assumption for
	 * @param idDrug    the ID of the drug that was assumed
	 * @param date      the date when the drug was assumed
	 * @throws SQLException if there was an error executing the database query
	 */
	public void takeDrug(String idPatient, String idDrug, String date) throws SQLException {
		String query = "INSERT INTO drugAssumptions (Patient_ID, Drug_ID, Date, Quantity) VALUES ('" + idPatient
				+ "', '" + idDrug + "', '" + date + "', 1)";
		log(query);
		@SuppressWarnings("unused")
		ResultSet rs = runQuery(query);
	}

	/**
	 * Updates the information related to a therapy, such as the doctor, patient,
	 * drug, quantity, assumptions, indication, and status.
	 *
	 * @param ID          the ID of the therapy to update
	 * @param idDoctor    the ID of the doctor associated with the therapy
	 * @param idPatient   the ID of the patient associated with the therapy
	 * @param idDrug      the ID of the drug associated with the therapy
	 * @param qnty        the quantity of the drug for the therapy
	 * @param Assumptions the assumptions related to the therapy
	 * @param Indication  the indication for the therapy
	 * @param status      the status of the therapy
	 * @throws SQLException if there was an error executing the database query
	 */
	public void updateTherapy(Integer ID, String idDoctor, String idPatient, String idDrug, String qnty,
			String Assumptions, String Indication, String status) throws SQLException {
		String query = "UPDATE therapy SET CF_Doctor='" + idDoctor + "', CF_Patient='" + idPatient + "', ID_Drug='"
				+ idDrug + "', Quantity=" + qnty + ", Assumptions=" + Assumptions + ", Indication='" + Indication
				+ "', Status='" + status + "' WHERE ID=" + ID;
		log(query);
		@SuppressWarnings("unused")
		ResultSet rs = runQuery(query);
	}

	/**
	 * Inserts a new pathology to the database for the specified patient, pathology,
	 * start date, and end date (if available).
	 *
	 * @param id_pat the ID of the pathology
	 * @param cf     the CF code of the patient associated with the pathology
	 * @param start  the start date of the pathology
	 * @param end    the end date of the pathology (if available), or null if the
	 *               pathology is still ongoing
	 * @throws SQLException if there was an error executing the database query
	 */
	public void insertPathology(String id_pat, String cf, String start, String end) throws SQLException {
		String query;
		if (end == null) {
			query = "INSERT INTO patientPathology (ID_Pathology, CF_Patient, Start_Date) VALUES ('" + id_pat + "', '"
					+ cf + "', '" + start + "')";
		} else {
			query = "INSERT INTO patientPathology (ID_Pathology, CF_Patient, Start_Date, End_Date) VALUES ('" + id_pat
					+ "', '" + cf + "', '" + start + "', '" + end + "')";
		}
		log(query);
		@SuppressWarnings("unused")
		ResultSet rs = runQuery(query);
	}

	/**
	 * Inserts a new blood pressure measurement to the database for the specified
	 * patient, diastolic blood pressure, systolic blood pressure, and date.
	 *
	 * @param cf   the CF code of the patient associated with the blood pressure
	 *             measurement
	 * @param DBP  the diastolic blood pressure value
	 * @param SBP  the systolic blood pressure value
	 * @param data the date when the blood pressure was measured
	 * @throws SQLException if there was an error executing the database query
	 */
	public void insertBPM(String cf, String DBP, String SBP, String data) throws SQLException {

		String query = "INSERT INTO diagnosis (CF_Patient, Date, SBP, DBP) VALUES ('" + cf + "', '" + data + "', " + SBP
				+ ", " + DBP + ")";
		log(query);
		@SuppressWarnings("unused")
		ResultSet rs = runQuery(query);
	}

	/**
	 * Updates the information related to a pathology, such as the ID of the
	 * pathology, patient, start date, and end date (if available).
	 *
	 * @param id_pat the new ID of the pathology
	 * @param cf     the new CF code of the patient associated with the pathology
	 * @param start  the new start date of the pathology
	 * @param end    the new end date of the pathology (if available), or null if
	 *               the pathology is still ongoing
	 * @throws SQLException if there was an error executing the database query
	 */
	public void updatePathology(String id_pat, String cf, String start, String end) throws SQLException {

		String query;
		if (end != null) {
			query = "UPDATE patientPathology SET ID_Pathology='" + id_pat + "', CF_Patient='" + cf + "', Start_Date='"
					+ start + "', End_Date='" + end + "' WHERE ID_Pathology='" + id_pat + "' AND Start_Date='" + start
					+ "' AND CF_Patient='" + cf + "'";
		} else {
			query = "UPDATE patientPathology SET ID_Pathology='" + id_pat + "', CF_Patient='" + cf + "', Start_Date='"
					+ start + "' WHERE ID_Pathology='" + id_pat + "' AND Start_Date='" + start + "' AND CF_Patient='"
					+ cf + "'";
		}
		log(query);
		@SuppressWarnings("unused")
		ResultSet rs = runQuery(query);
	}

	/**
	 * 
	 * Inserts a new generic information about a patient into the database.
	 * 
	 * @param idDoctor  The ID of the doctor who is adding the information.
	 * @param idPatient The ID of the patient the information is about.
	 * @param info      The information to be added.
	 * @throws SQLException If an error occurs while accessing the database.
	 */
	public void insertGenericInfo(String idDoctor, String idPatient, String info) throws SQLException {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String query = "INSERT INTO patientDoctor (CF_Doctor, CF_Patient, Info_Date, Info) VALUES ('" + idDoctor
				+ "', '" + idPatient + "','" + dtf.format(now) + "', '" + info + "')";
		log(query);
		@SuppressWarnings("unused")
		ResultSet rs = runQuery(query);
	}

}
