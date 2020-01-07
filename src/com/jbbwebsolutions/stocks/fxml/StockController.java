package com.jbbwebsolutions.stocks.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jbbwebsolutions.stocks.dao.CategoryDAO;
import com.jbbwebsolutions.stocks.dao.IQuery;
import com.jbbwebsolutions.stocks.dao.SectorDAO;
import com.jbbwebsolutions.stocks.dao.StockDBDAO;
import com.jbbwebsolutions.stocks.model.Sector;
import com.jbbwebsolutions.stocks.model.Stock;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StockController implements Initializable {

	@FXML
	private AnchorPane apStock;

	@FXML
	private TableView<Stock> tvStock;

	@FXML
	private TableColumn<Stock, String> colSymbol;

	@FXML
	private TableColumn<Stock, String> colName;

	@FXML
	private TableColumn<Stock, Float> colPrice;

	@FXML
	private TableColumn<Stock, Float> colNet;

	@FXML
	private TableColumn<?, ?> colCategory;

	@FXML
	private TableColumn<Stock, Float> colDividend;

	@FXML
	private TableColumn<Stock, Float> colSector;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txtField;

	@FXML
	private Button btnSearch;

	@FXML
	private Label lblMessage;

	@FXML
	private RadioButton rbSimple;

	@FXML
	private ToggleGroup tgSearchMode;

	@FXML
	private RadioButton rbAdvanced;

	@FXML
	private RadioButton rbAdvancedfilter;

	@FXML
	private ComboBox<String> cbCategroy;

	@FXML
	private FlowPane fpSector;

	@FXML
	private static int stockReturn;

	@FXML
	private RadioButton rbPiechart;

	@FXML
	private AnchorPane apLayout;

	@FXML
	private VBox vbLayout;

	@FXML
	private PieChart pieProduct;

	// why put sector as both types
	private static List<Sector> sectors = new ArrayList<Sector>();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		if (cbCategroy != null) {

			CategoryDAO dao = new CategoryDAO();
			List<String> categories = dao.findAll();
			cbCategroy.getItems().addAll("All");
			cbCategroy.getItems().addAll(categories);
			cbCategroy.setValue("All");

		}

		if (fpSector != null) {
			SectorDAO sectdao = new SectorDAO();
			sectors = sectdao.findAll();

			for (Sector sector : sectors) {
				CheckBox chkBox = new CheckBox(sector.getSector() + " - " + sector.getSectorDescription());

				chkBox.getStyleClass().add("sector");
				chkBox.setUserData(sector.getSector());
				fpSector.getChildren().add(chkBox);

			}

		}

	}

	@FXML
	void onKeyReleased(KeyEvent event) {
		System.out.println(event);
		int size = txtField.getText().length();
		if (size == 0) {
			btnSearch.setDisable(true);
		} else {
			btnSearch.setDisable(false);
		}

	}

	@FXML
	void search(ActionEvent event) {
		if (rbSimple.isSelected()) {
			this.simpleSearch();
		} else if (rbAdvanced.isSelected()) {
			this.advanceSearch();

		} else if (rbAdvancedfilter.isSelected()) {
			this.advanceFilterSearch();

		}

	}

	@FXML
	void searchMode(ActionEvent event) throws IOException {

		Object o = event.getSource();
		String message = "";

		try {
			Stage stage = null;
			Parent root = null;

			if (o == rbSimple) {
				message = "simple mode selected ";
				stage = (Stage) rbAdvanced.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("StockView.fxml"));
			} else if (o == rbAdvanced) {
				message = "advanced mode selected ";
				stage = (Stage) rbAdvanced.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("StockAdvancedView.fxml"));
			} else if (o == rbAdvancedfilter) {
				message = "advanced filter mode selected ";
				stage = (Stage) rbAdvancedfilter.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("StockAdvancedWithCheckBoxesView.fxml"));
			}

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

		lblMessage.setText(message + Math.random());
	}

	private void simpleSearch() {
		ObservableList<Stock> stocks = tvStock.getItems();
		tvStock.getItems().clear();
		
		IQuery<Stock> dao = new StockDBDAO();

		Map<String, String> map = new HashMap<>();
		String symbol = txtField.getText();
		map.put("symbol", "eq:" + symbol);

		List<Stock> allStocks = dao.findBy(map);

		for (Stock stock : allStocks) {
			stocks.add(stock);
		}
		int row = stocks.size();
		lblMessage.setText("Number of stocks returned: " + row);
	}
	

	private void advanceSearch() {
		ObservableList<Stock> stocks = tvStock.getItems();
		
		tvStock.getItems().clear();
		 

		IQuery<Stock> dao = new StockDBDAO();

		Map<String, String> map = new HashMap<>();
		String symbol = txtField.getText(); // just added
		
		String category = cbCategroy != null ? cbCategroy.getValue() : "All";

		String price = txtField.getText();

		map.put("price", "gt:" + price);
		map.put("category", "eq:" + category);
		map.put("symbol", "eq:" + symbol);
		List<Stock> allStocks = dao.findBy(map);
		
		stocks.addAll(allStocks);
		int row = stocks.size();
		lblMessage.setText("Number of stocks returned: " + row);
		
		
	}

	private void advanceFilterSearch() {
		ObservableList<Stock> stocks = tvStock.getItems();
		tvStock.getItems().clear();

		IQuery<Stock> dao = new StockDBDAO();

		Map<String, String> map = new HashMap<>();
		String symbol = txtField.getText(); 
		String price = txtField.getText();

		ObservableList<Node> selectedfilter = fpSector.getChildren();
		ArrayList<String> items = new ArrayList<>();
		for (Node node : selectedfilter) {

			CheckBox cb = (CheckBox) node;
			if (cb.isSelected()) {
				items.add(cb.getUserData().toString());

			}

		}

		String[] selectedFilter = items.stream().toArray(String[]::new);

		System.out.println(items);

		String categories = String.join(",", selectedFilter);

		map.put("categories", categories);
		map.put("price", "gt:" + price);
		map.put("symbol", "gt:" + symbol);
		List<Stock> allStocks = dao.findBy(map);

		stocks.addAll(allStocks);

	}

}
