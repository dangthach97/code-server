package com.academy.jtravel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Client extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("View/sample.fxml"));
		primaryStage.setTitle("ĐĂNG KÍ TOUR DU DỊCH");
		primaryStage.setScene(new Scene(root));

		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (KeyCode.F11.equals(event.getCode())) {
				primaryStage.setFullScreen(!primaryStage.isFullScreen());
			}
		});
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}