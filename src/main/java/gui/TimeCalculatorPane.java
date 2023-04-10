package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TimeCalculatorPane extends ContentPane implements Initializable {

    @FXML
    private Button leftButton;

    @FXML
    private Button rightButton;

    @FXML
    private Button button0;

    @FXML
    private Button doublePointButton;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button buttonEval;

    @FXML
    private TextField calculatorField;

    @FXML
    private Button closeMarkButton;

    @FXML
    private Button pmButton;
    @FXML
    private ListView<String> historyListView;

    @FXML
    private Button minusButton;

    @FXML
    private Button openMarkButton;

    @FXML
    private Button plusButton;

    @FXML
    private Button amButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button lastValueButton;

    @FXML
    private TextField resultField;


    @Override
    public AnchorPane start()  {
        return super.initController("/time_calculator.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
