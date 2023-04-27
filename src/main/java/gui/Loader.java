package gui;

import javafx.fxml.FXMLLoader;

import java.io.File;
import java.net.MalformedURLException;

/**
 * This class is used to load FXML files from the src folder if the program is run from an IDE, or from the jar if the
 * program is run from the jar.
 */
public class Loader {
    /**
     * This method loads an FXML file from the src folder if the program is run from an IDE, or from the jar if the
     * @param fileName the path to the FXML file (relative to the src folder) or the name of the FXML file if it is in the resources folder
     * @param classInPackage the class of the controller of the FXML file
     * @return the FXMLLoader object
     */
    public static FXMLLoader load(String fileName, Class classInPackage) {
        FXMLLoader loader;
        try { // try to load the file from the src folder
            loader = new FXMLLoader(classInPackage.getClassLoader().getResource(fileName));
        } catch (Exception e) { // if the file is not found, try to load it from the jar
            try {
                loader = new FXMLLoader((new File("src/main/resources/" + fileName).toURI().toURL()));
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return loader;
    }
}
