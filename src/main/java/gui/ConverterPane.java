package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConverterPane extends ContentPane implements Initializable {

    @FXML
    private ChoiceBox<String> unitChoice;

    @FXML
    private ChoiceBox<String> fromChoice;

    @FXML
    private ChoiceBox<String> toChoice;

    @FXML
    private TextField inputTextField;

    @FXML
    private TextField outputTextField;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public AnchorPane start() {
        return super.initController("/converter.fxml");
    }
}
