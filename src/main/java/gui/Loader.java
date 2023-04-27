package gui;

import javafx.fxml.FXMLLoader;

import java.io.File;
import java.net.MalformedURLException;

public class Loader {
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
