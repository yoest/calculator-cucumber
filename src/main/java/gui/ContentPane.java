package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public abstract class ContentPane extends AnchorPane {

    /** Initialize the link between this class and the FXML file.
     *
     * @param filename: name of the FXML file to load.
     * @return an AnchorPane object to set as the main content.
     */
    protected AnchorPane initController(String filename) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainCalculatorPane.class.getResource(filename)
            );
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Start this controller.
     *
     * @return an AnchorPane object to set as the main content.
     */
    public abstract AnchorPane start();
}
