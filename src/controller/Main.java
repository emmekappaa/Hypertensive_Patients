package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 
 * Main class for the application.
 */
public class Main extends Application {

	public static String session = " ";

	@Override
	public void start(Stage stage) throws IOException, SQLException, ParseException {

		FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("../view/login.fxml"));
		Scene sceneLogin = new Scene(fxmlLoader2.load());

		stage.setTitle("Login");
		stage.getIcons().add(new Image("/images/logos.png"));
		stage.setResizable(false);
		stage.setScene(sceneLogin);
		stage.show();

	}

	public static void main(String[] args) {
		launch();
	}
}
