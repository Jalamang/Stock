package com.jbbwebsolutions.stocks.driver;

import java.util.List;

import com.jbbwebsolutions.stocks.dao.stockPieDAO;
import com.jbbwebsolutions.stocks.model.Stock;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PieChartExperiments2 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("My First JavaFX App");

		PieChart pieChart = new PieChart();

		
		// now dynamic
		stockPieDAO dao = new stockPieDAO();
		List<Stock> list = dao.findAll();
		for (Stock product : list) {
			PieChart.Data slice1 = new PieChart.Data(product.getName(), product.getPrice());
			pieChart.getData().add(slice1);

		}

// static way with set value
//        PieChart.Data slice1 = new PieChart.Data("Desktop", 213);
//        PieChart.Data slice2 = new PieChart.Data("Phone"  , 67);
//        PieChart.Data slice3 = new PieChart.Data("Tablet" , 36);
//
//        pieChart.getData().add(slice1);
//        pieChart.getData().add(slice2);
//        pieChart.getData().add(slice3);

		VBox vbox = new VBox(pieChart);

		Scene scene = new Scene(vbox, 500, 500);

		primaryStage.setScene(scene);
		primaryStage.setHeight(600);
		primaryStage.setWidth(600);

		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
