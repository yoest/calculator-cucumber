package calculator;

import gui.Loader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 * This class is used to start the GUI. (it's the main class extending Application)
 * It doesn't work if you run it from the jar
 */
public class GuiStarter extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = Loader.load("menu.fxml", getClass());
		Scene scene = new Scene(loader.load());
		stage.setScene(scene);
		stage.setTitle("Calculator");
		stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
