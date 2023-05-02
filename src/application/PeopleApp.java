package application;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javafx.beans.binding.Bindings;
public class PeopleApp extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException, ParseException  {
        Model model = Model.getInstance();

        FXMLLoader fxmlLoader = new FXMLLoader(PeopleApp.class.getResource("view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PeopleApp");
        stage.setScene(scene);

        // people tab

        DatePicker birthdate_input   =    (DatePicker) scene.lookup("#pick_birthdate");
        Button create_person =    (Button) scene.lookup("#person_submit");
        ChoiceBox<String> person_occupation_input = (ChoiceBox<String>) scene.lookup("#pick_occupation");

        create_person.setOnMouseClicked(e -> {
            System.out.println(birthdate_input.getValue());
            System.out.println(person_occupation_input.getValue());
        });

        // occupation tab

        TextField occupation_input =  (TextField) scene.lookup("#new_occupation_input");
        Button create_occupation =    (Button) scene.lookup("#create_occupation");
        TableView<SimpleStringProperty> occupations_table =  (TableView<SimpleStringProperty>)  scene.lookup("#occupations_table");
        occupations_table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("value"));
        Bindings.bindContent(occupations_table.getItems(), model.occupations);

        create_occupation.setOnMouseClicked(e -> {
            System.out.println(occupation_input.getText());
            try { model.create_occupation(occupation_input.getText());}
            catch (SQLException exc) { System.out.println(exc); }
            occupation_input.clear();
            try {model.loadOccupations();}catch(SQLException exc){System.out.println(exc);}
        });



        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}