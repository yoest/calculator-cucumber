package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Button runButton;

    @FXML
    private AnchorPane mainContent;


    @Override
    public AnchorPane start()  {
        return super.initController("/integerConfig.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runButton.setOnAction(actionEvent -> run());

    }

    private void run() {
        String input = inputTextField.getText();
        String output = outputTextField.getText();
        try {
            int inputInt = Integer.parseInt(input);
            int outputInt = Integer.parseInt(output);
            if (inputInt <= 1 || inputInt > 36 || outputInt <= 1 || outputInt > 36) {
                throw new NumberFormatException();
            }
            MainCalculatorPane.INPUT_RADIX = inputInt;
            MainCalculatorPane.OUTPUT_RADIX = outputInt;
            changeMainContent(new MainCalculatorPane(true));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input or output"); //TODO make a popup
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
