/*
package test;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import controller.controllerLogin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;


public class controllerLoginTest {

    private controllerLogin loginController;
    private TextField usernameField;
    private TextField passwordField;
    private ChoiceBox<String> choiceField;
    private Map<String, String> testCredentials;
	
	
	//metodo per settare istanza di controllerLogin
    @Before
    public void setUp() 
    {
        loginController = new controllerLogin();
        
        // Inizializzazione dei componenti del controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            loginController.setUsernameTextField((TextField) scene.lookup("#usernameTextField"));
            loginController.setPasswordTextField((PasswordField) scene.lookup("#passwordTextField"));
            loginController.setLoginButton((Button) scene.lookup("#loginButton"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // ...
}


    @Test
    public void testLoginButtonClicked() throws Exception {
        usernameField.setText("test@test.com");
        passwordField.setText("password");
        choiceField.setValue("Doctor");
        ActionEvent event = new ActionEvent();
        loginController.loginButtonClicked(event);
        // assertion to check if the controller performs the expected behavior
        assertEquals("Benvenuto", ((Stage) loginController.loginButton.getScene().getWindow()).getTitle());
    }
    
}
*/