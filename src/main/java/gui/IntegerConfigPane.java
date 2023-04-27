package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.ResourceBundle;

public class IntegerConfigPane extends ContentPane implements Initializable {

    @FXML
    private TextField inputTextField;

    @FXML
    private TextField outputTextField;

    @FXML
    private TextField sizeTextField;

    @FXML
    private Button runButton;

    @FXML
    private AnchorPane mainContent;


    @Override
    public AnchorPane start()  {
        return super.initController("integerConfig.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runButton.setOnAction(actionEvent ->  run());
        String regex = "[^\\d]";
        inputTextField.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.matches("\\d*")) {
                inputTextField.setText(t1.replaceAll(regex, ""));
            }
        });
        outputTextField.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.matches("\\d*")) {
                outputTextField.setText(t1.replaceAll(regex, ""));
            }
        });
        sizeTextField.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.matches("\\d*")) {
                sizeTextField.setText(t1.replaceAll(regex, ""));
            }
        });
    }

    private void run() {
        String input = inputTextField.getText();
        String output = outputTextField.getText();
        String size = sizeTextField.getText();
        try {
            int inputInt = Integer.parseInt(input);
            int outputInt = Integer.parseInt(output);
            int sizeInt = Integer.parseInt(size);
            if (inputInt <= 1 || inputInt > 36 || outputInt <= 1 || outputInt > 36 || sizeInt < 10 || sizeInt > 1000) {
                throw new NumberFormatException();
            }
            MainCalculatorPane.setInputRadix(inputInt);
            MainCalculatorPane.setOutputRadix(outputInt);
            MainCalculatorPane.setMaxMemorySize(sizeInt);
            changeMainContent(new MainCalculatorPane(true));
        } catch (NumberFormatException e) {
            //Create a dialog to show the error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please enter a valid input and output radix.");
            alert.showAndWait();
            //reset the text fields
            inputTextField.setText("");
            outputTextField.setText("");
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
