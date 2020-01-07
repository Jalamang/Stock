package com.jbbwebsolutions.stocks.fxml;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StockMain extends Application {

	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.
					 load(getClass().getResource("StockView.fxml"));
			
			stage.setTitle("Stocks");
			Scene scene = new Scene(root); 
			stage.setScene(scene);

		
			URL url = this.getClass().getResource("Stockviewlooks.css");
			
			if(url == null) {
				System.out.println("Resources not found Aborting");
				System.exit(-1);
			}
			
			
			
			String css = url .toExternalForm();
			scene.getStylesheets().add(css);
			stage.setResizable(false);
		    stage.show();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] args) {
		Application.launch(StockMain.class, args);
	}
}
