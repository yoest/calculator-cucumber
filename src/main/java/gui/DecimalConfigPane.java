package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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


    @Override
    public AnchorPane start()  {
        return super.initController("/decimalConfig.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roundingChoiceBox.getItems().addAll(Rounding.values());
        roundingChoiceBox.setValue(Rounding.ROUND_HALF_UP);
        runButton.setOnAction(actionEvent -> run());
    }

    private void run() {
        String input = inputTextField.getText();
        Rounding rounding = roundingChoiceBox.getValue();
        try {
            int inputInt = Integer.parseInt(input);
            if (inputInt <= 0 || inputInt > 20) {
                throw new NumberFormatException();
            }
            MainCalculatorPane.PRECISION = inputInt;
            MainCalculatorPane.ROUNDING = rounding;
            changeMainContent(new MainCalculatorPane(false));
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
