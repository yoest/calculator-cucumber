package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private MenuItem autoSaveButton;

    @FXML
    private MenuItem decimal10Button;

    @FXML
    private MenuItem decimalOtherButton;

    @FXML
    private MenuItem intOtherButton;

    @FXML
    private MenuItem saveButton;

    @FXML
    private MenuItem integerButton = new MenuItem();

    @FXML
    private MenuItem timeButton;

    @FXML
    private MenuItem runConverterButton;

    @FXML
    private AnchorPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        integerButton.setOnAction(mouseEvent -> changeMainContent(new IntegerConfigPane()));
        decimal10Button.setOnAction(mouseEvent -> changeMainContent(new MainCalculatorPane(false)));
        timeButton.setOnAction(mouseEvent -> changeMainContent(new TimeCalculatorPane()));
        runConverterButton.setOnAction(mouseEvent -> changeMainContent(new ConverterPane()));
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
