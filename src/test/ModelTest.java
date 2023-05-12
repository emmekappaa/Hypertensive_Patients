package test;

import static org.junit.Assert.*;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import java.io.ByteArrayOutputStream;

import model.Model;

public class ModelTest {

	/**
	 * Tests the logging functionality of the Model class.
	 *
	 * @throws SQLException if an SQL exception occurs
	 */
	@Test
	public void testLog() throws SQLException {

		var m = Model.getInstance();
		// intercetto output della console e lo catturo
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));
		// lancio la funzione
		Object testObject = "Test message";
		m.log(testObject);
		String printedOutput = outputStream.toString().trim();
		String expectedOutput = testObject.toString();
		assertEquals(expectedOutput, printedOutput);
	}

	/**
	 * Tests the database connection functionality of the Model class.
	 *
	 * @throws SQLException if a SQL exception occurs
	 */
	@Test
	public void testConnect() throws SQLException {
		String url = "jdbc:sqlite:table.db";
		Connection conn = DriverManager.getConnection(url);
		// verifico connessione non sia null
		assertNotNull("Connection is null", conn);
		// verifico che la connessione non sia chiusa
		assertTrue("Connection is closed", !conn.isClosed());
	}

	/**
	 * Tests the execution of a database query using the Model class.
	 *
	 * @throws SQLException if a SQL exception occurs
	 */
	@Test
	public void testRunQuery() throws SQLException {
		var m = Model.getInstance();
		String query = "SELECT * FROM doctor";
		ResultSet rs = m.runQuery(query);
		// verifico risultato query non sia null
		assertNotNull("ResultSet is null", rs);
		// verifico risultato contenga almeno una riga
		assertTrue("ResultSet is empty", rs.next());
	}

	/**
	 * Tests the getInstance() method of the Model class.
	 *
	 * @throws SQLException if an SQL exception occurs
	 */
	@Test
	public void testGetInstance() throws SQLException {

		// Due istanze model guardo non siano null, e che siano la stessa
		// Considerando che sono singleton
		var m1 = Model.getInstance();
		assertNotNull("Model instance is null", m1);
		var m2 = Model.getInstance();
		assertNotNull("Model instance is null", m2);
		assertSame("Model instances are not the same object", m1, m2);
	}

	/**
	 * Tests the tableExists() method of the Model class.
	 *
	 * @throws SQLException if an SQL exception occurs
	 */
	@Test
	public void testTableExists() throws SQLException {
		var m = Model.getInstance();

		// Testo se tabella esiste nel DB
		assertTrue("Table does not exist", m.tableExists("doctor"));

		// Testo se tabella non esiste nel DB
		assertFalse("Table exists", m.tableExists("tommaso"));
	}

	/**
	 * Tests the checkLogin() method of the Model class.
	 */
	@Test
	public void testCheckLogin() {

		// Verify:
		// - When the login credentials are correct
		// - When the password entered is incorrect

		// Creazione dell'istanza del modello
		Model model = null;
		try {
			model = Model.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Errore durante l'inizializzazione del modello");
		}

		// credenziali corrette
		String email = "mail@mail.com";
		String psw = "d4a1ecebe00a30d870e88bf18828709d";

		// Verifica del login con credenziali corrette
		boolean loginResult = model.checkLogin(email, "wirecard", psw);
		assertTrue(loginResult);

		// Verifica del login con password errata
		loginResult = model.checkLogin(email, "wrongpassword", psw);
		assertFalse(loginResult);
	}

}
