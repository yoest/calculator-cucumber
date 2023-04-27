package gui;

import calculator.GuiStarter;
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
        FXMLLoader loader = Loader.load(filename, GuiStarter.class);
        loader.setController(this);
        try {
            return loader.load();
        } catch (IOException e) {
            return null;
        }
    }

    /** Start this controller.
     *
     * @return an AnchorPane object to set as the main content.
     */
    public abstract AnchorPane start();
}
