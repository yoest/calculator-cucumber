package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import real.Rounding;

import java.net.URL;
import java.util.ResourceBundle;

public class DecimalConfigPane extends ContentPane implements Initializable {

    @FXML
    private TextField inputTextField;

    @FXML
    private ChoiceBox<Rounding> roundingChoiceBox;

    @FXML
    private Button runButton;

    @FXML
    private AnchorPane mainContent;


    /**
     * Init the controller and return the pane.
     * @return AnchorPane object of the pane.
     */
    @Override
    public AnchorPane start()  {
        return super.initController("decimalConfig.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roundingChoiceBox.getItems().addAll(Rounding.values()); //Add all the rounding options to the choice box
        roundingChoiceBox.setValue(Rounding.ROUND_HALF_UP); //Set the default rounding option
        runButton.setOnAction(actionEvent -> run()); //Set the run button to run the program
        inputTextField.textProperty().addListener((observableValue, s, t1) -> {
            //Make sure the input is a number
            if (!t1.matches("\\d*")) {
                inputTextField.setText(t1.replaceAll("[^\\d]", ""));
            }
        });
    }

    /**
     * Run the program with the given precision and rounding option.
     */
    private void run() {
        String input = inputTextField.getText(); //Get the input from the text field
        Rounding rounding = roundingChoiceBox.getValue(); //Get the rounding option from the choice box
        try {
            int inputInt = Integer.parseInt(input);
            //Make sure the input is between 1 and 20
            if (inputInt <= 0 || inputInt > 20) {
                throw new NumberFormatException();
            }
            //Set the precision and rounding options
            MainCalculatorPane.setPRECISION(inputInt);
            MainCalculatorPane.setROUNDING(rounding);
            //Change the main content to the main calculator pane
            changeMainContent(new MainCalculatorPane(false));
        } catch (NumberFormatException e) {
            //Create a dialog to show the error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please enter a valid number of decimal places.");
            alert.showAndWait();
            //reset the text fields
            inputTextField.setText("");
        }
    }

    /** Set the main content of the screen to a new pane.
     *
     * @param newContent: ContentPane object to set as the main content.
     */
    private void changeMainContent(ContentPane newContent) {
        if(mainContent.getChildren().size() > 0)
            mainContent.getChildren().remove(0);
        mainContent.getChildren().add(newContent.start());
    }
}
