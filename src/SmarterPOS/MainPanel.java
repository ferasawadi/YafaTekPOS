package SmarterPOS;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTableCellBuilder;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import javafx.util.StringConverter;
import net.sf.dynamicreports.examples.complex.invoice.InvoiceDesign;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.stage.Stage;

public class MainPanel implements Initializable {
	Main obj = new Main();
	@FXML
	AnchorPane trafficAnchor = new AnchorPane();
	@FXML
	AnchorPane customersAnchor = new AnchorPane();
	@FXML
	AnchorPane inventoryAnchor = new AnchorPane();
	@FXML
	AnchorPane statisticesAnchor = new AnchorPane();
	@FXML
	AnchorPane returnedAnchor = new AnchorPane();
	@FXML
	AnchorPane BackupAnchor = new AnchorPane();
	@FXML
	AnchorPane settingsAnchor = new AnchorPane();
	@FXML
	AnchorPane aboutAnchor = new AnchorPane();
	@FXML
	AnchorPane salesBorderAnchor = new AnchorPane();
	@FXML
	AnchorPane addmatAnchor = new AnchorPane();
	//---------------------------------- inventory---------------------
	@FXML
	TextField mat_id = new TextField();
	@FXML
	TextField mat_barcode = new TextField();
	@FXML
	TextField mat_name = new TextField();
	@FXML
	TextField mat_unit = new TextField();
	@FXML
	JFXTextArea mat_notes = new JFXTextArea();
	@FXML
	JFXButton save_material = new JFXButton();
	@FXML
	JFXButton delete = new JFXButton();
	@FXML
	TableColumn m_id = new TableColumn();
	@FXML
	TableColumn m_name = new TableColumn();
	@FXML
	TableColumn m_unit = new TableColumn();
	@FXML
	TableColumn m_notes = new TableColumn();
	@FXML
	TableColumn m_barcode = new TableColumn();
	@FXML
	HBox hbox_reciptid = new HBox();
	@FXML
	HBox hbox_date = new HBox();
	//---------------------------- outside storage----------------
	@FXML
	TextField mat_name_out = new TextField();
	@FXML
	DatePicker mat_date_out = new DatePicker();
	@FXML
	TextField mat_quantity_out = new TextField();
	@FXML
	TableView out_table = new TableView();
	@FXML
	TableColumn id_out_col = new TableColumn();
	@FXML
	TableColumn mat_name_out_col = new TableColumn();
	@FXML
	TableColumn mat_date_out_col = new TableColumn();
	@FXML
	TableColumn mat_quantity_out_col = new TableColumn();
	@FXML
	TableColumn id_out = new TableColumn();
	//---------------- inside storage---------------------------
	@FXML
	TextField mat_name_in = new TextField();
	@FXML
	DatePicker mat_date_in = new DatePicker();
	@FXML
	TextField mat_quantity_in = new TextField();
	@FXML
	TableView in_table = new TableView();
	@FXML
	TableColumn id_in_col = new TableColumn();
	@FXML
	TableColumn mat_name_in_col = new TableColumn();
	@FXML
	TableColumn quantity_in_col = new TableColumn();
	@FXML
	TableColumn date_in_col = new TableColumn();
	@FXML
	TableColumn id_in = new TableColumn();

	@FXML
	AnchorPane add_mat_page = new AnchorPane();
	@FXML
	AnchorPane show_material = new AnchorPane();
	@FXML
	AnchorPane outpage = new AnchorPane();
	@FXML
	AnchorPane inpage = new AnchorPane();

	@FXML
	TableView<OrderRecord> TableOrder = new TableView<OrderRecord>();
	@FXML
	TableView material_table = new TableView();
	@FXML
	private TableColumn<OrderRecord, String> Bar;
	@FXML
	private TableColumn<OrderRecord, String> Uniname;
	@FXML
	private TableColumn<OrderRecord, String> QTY;
	@FXML
	private TableColumn<OrderRecord, String> Price;
	@FXML
	private TableColumn<OrderRecord, String> Subtotal;
	@FXML
	private TableColumn<OrderRecord, String> Discount;
	@FXML
	private TableColumn<OrderRecord, String> Total;

	static ObservableList<OrderRecord> data = FXCollections.observableArrayList();

	@FXML
	TableView<OrderRecord> TableOrder11 = new TableView<OrderRecord>();
	@FXML
	private TableColumn<OrderRecord, String> Bar11;
	@FXML
	private TableColumn<OrderRecord, String> Uniname11;
	@FXML
	private TableColumn<OrderRecord, String> QTY11;
	@FXML
	private TableColumn<OrderRecord, String> Price11;
	@FXML
	private TableColumn<OrderRecord, String> Subtotal11;
	@FXML
	private TableColumn<OrderRecord, String> Discount11;
	@FXML
	private TableColumn<OrderRecord, String> Total11;

	static ObservableList<OrderRecord> RePurChases = FXCollections.observableArrayList();


	@FXML
	Label totalmoneytoplabel = new Label();
	@FXML
	TextField ResiptID, CustomName;
	@FXML
	DatePicker DateTB;

	//------------------  Sales Management Buttons ----------------
	@FXML
	BorderPane salesPanel;
	@FXML
	BorderPane expensesPanel;
	@FXML
	BorderPane purchasesPanel;
	@FXML
	BorderPane addMaterialBorder;
	@FXML
	BorderPane showMaterialBorder;
	@FXML
	BorderPane returnedBorder;
	@FXML
	BorderPane inmatBorder;
	@FXML
	BorderPane outMatBorder;
	@FXML
	BorderPane salesreturned;
	@FXML
	BorderPane purchasesreturned;
	@FXML
	BorderPane statistceBorder;
	@FXML
	BorderPane TrafficSearchBorderPane;
	@FXML
	BorderPane IncomesPanel;
	@FXML
	BorderPane WelcomeBorder;
	@FXML
	BorderPane InventorySearchBorderPane;
	@FXML
	BorderPane settingsGeneral;
	@FXML
	BorderPane settingsBackup;
	@FXML
	BorderPane aboutBorder;
	@FXML
	BorderPane customersBorder;
	@FXML
	BorderPane addcustomerborder;
	@FXML
	BorderPane showcustomerborder;


	//-------------------search traffic----------------
	@FXML
	TextField customer_tf = new TextField();
	@FXML
	TextField reciptid_tf = new TextField();
	@FXML
	MenuButton reciptTypeButton = new MenuButton();
	@FXML
	DatePicker fromdate = new DatePicker();
	@FXML
	DatePicker todate = new DatePicker();
	@FXML
	TableView searchtraffictable = new TableView();
	@FXML
	RadioMenuItem all = new RadioMenuItem();
	@FXML
	MenuItem sales = new CheckMenuItem();
	@FXML
	MenuItem purchasess = new CheckMenuItem();
	@FXML
	MenuItem sales_return = new CheckMenuItem();
	@FXML
	MenuItem purchasess_return = new CheckMenuItem();
	@FXML
	MenuItem expenses = new CheckMenuItem();
	@FXML
	MenuItem incomes = new CheckMenuItem();
	@FXML
	TableColumn s_id = new TableColumn();
	@FXML
	TableColumn s_customer = new TableColumn();
	@FXML
	TableColumn s_total = new TableColumn();
	@FXML
	TableColumn s_type = new TableColumn();
	@FXML
	TableColumn s_date = new TableColumn();
	@FXML
	Button chartShowButton;
	//  --------------- Observable Lists Dicleration


	ObservableList<InOutStorage> in_Storage = FXCollections.observableArrayList();
	ObservableList<InOutStorage> in_Storage_tmp = FXCollections.observableArrayList();


	ObservableList<InOutStorage> out_storage = FXCollections.observableArrayList();
	ObservableList<InOutStorage> out_storage_tmp = FXCollections.observableArrayList();

	// observable list for save material in table view
	ObservableList<Material> data1 = FXCollections.observableArrayList();
	ObservableList<Material> data_tmp = FXCollections.observableArrayList();


	public void homeclick() {
		WelcomeBorder.setVisible(true);
		trafficAnchor.setVisible(false);
		inventoryAnchor.setVisible(false);
		customersAnchor.setVisible(false);
		returnedBorder.setVisible(false);
		BackupAnchor.setVisible(false);
		settingsAnchor.setVisible(false);
		aboutBorder.setVisible(false);
		statistceBorder.setVisible(false);
		customersBorder.setVisible(false);
		try {
			welcomeLabel();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void traficClick() {
		WelcomeBorder.setVisible(false);
		trafficAnchor.setVisible(true);
		inventoryAnchor.setVisible(false);
		customersAnchor.setVisible(false);
		returnedBorder.setVisible(false);
		BackupAnchor.setVisible(false);
		settingsAnchor.setVisible(false);
		aboutBorder.setVisible(false);
		statistceBorder.setVisible(false);
		customersBorder.setVisible(false);

	}

	public void inventoryClick() {
		WelcomeBorder.setVisible(false);
		trafficAnchor.setVisible(false);
		inventoryAnchor.setVisible(true);
		customersAnchor.setVisible(false);
		returnedBorder.setVisible(false);
		BackupAnchor.setVisible(false);
		settingsAnchor.setVisible(false);
		aboutBorder.setVisible(false);
		statistceBorder.setVisible(false);
		customersBorder.setVisible(false);

	}

	public void customersClick() {
		WelcomeBorder.setVisible(false);
		trafficAnchor.setVisible(false);
		inventoryAnchor.setVisible(false);
		customersAnchor.setVisible(true);
		returnedBorder.setVisible(false);
		BackupAnchor.setVisible(false);
		settingsAnchor.setVisible(false);
		aboutBorder.setVisible(false);
		statistceBorder.setVisible(false);
		customersBorder.setVisible(true);


	}

	public void statisticesClick() {
		WelcomeBorder.setVisible(false);
		trafficAnchor.setVisible(false);
		inventoryAnchor.setVisible(false);
		customersAnchor.setVisible(false);
		returnedBorder.setVisible(false);
		BackupAnchor.setVisible(false);
		settingsAnchor.setVisible(false);
		aboutBorder.setVisible(false);
		statistceBorder.setVisible(true);
		customersBorder.setVisible(false);


	}

	public void returnedClick() {
		WelcomeBorder.setVisible(false);
		trafficAnchor.setVisible(false);
		inventoryAnchor.setVisible(false);
		customersAnchor.setVisible(false);
		returnedBorder.setVisible(true);
		BackupAnchor.setVisible(false);
		settingsAnchor.setVisible(false);
		aboutBorder.setVisible(false);
		statistceBorder.setVisible(false);
		customersBorder.setVisible(false);

	}

	public void settingsClick() {
		WelcomeBorder.setVisible(false);
		trafficAnchor.setVisible(false);
		inventoryAnchor.setVisible(false);
		customersAnchor.setVisible(false);
		returnedBorder.setVisible(false);
		BackupAnchor.setVisible(false);
		settingsAnchor.setVisible(true);
		aboutBorder.setVisible(false);
		statistceBorder.setVisible(false);
		customersBorder.setVisible(false);


	}

	public void aboutClick() {
		WelcomeBorder.setVisible(false);
		trafficAnchor.setVisible(false);
		inventoryAnchor.setVisible(false);
		customersAnchor.setVisible(false);
		returnedBorder.setVisible(false);
		BackupAnchor.setVisible(false);
		settingsAnchor.setVisible(false);
		aboutBorder.setVisible(true);
		statistceBorder.setVisible(false);
		customersBorder.setVisible(false);
	}


	// --------------------------- Welcome Window ----------------


	//Traffic Sales Button Function

	public void salesClick(ActionEvent e) {
		salesPanel.setVisible(true);
		expensesPanel.setVisible(false);
		purchasesPanel.setVisible(false);
		TrafficSearchBorderPane.setVisible(false);
		IncomesPanel.setVisible(false);
		if (e != null)
			ClearBuyForm();


	}

	public void incomesButtonClick(ActionEvent e) {
		salesPanel.setVisible(false);
		expensesPanel.setVisible(false);
		purchasesPanel.setVisible(false);
		TrafficSearchBorderPane.setVisible(false);
		IncomesPanel.setVisible(true);
		if (e != null)
			CleaincoForm();


	}

	public void expensessClick(ActionEvent e) {
		salesPanel.setVisible(false);
		expensesPanel.setVisible(true);
		purchasesPanel.setVisible(false);
		TrafficSearchBorderPane.setVisible(false);
		IncomesPanel.setVisible(false);
		if (e != null)
			ClearexpForm();

	}

	public void purchasesClick(ActionEvent e) {
		salesPanel.setVisible(false);
		expensesPanel.setVisible(false);
		purchasesPanel.setVisible(true);
		TrafficSearchBorderPane.setVisible(false);
		IncomesPanel.setVisible(false);
		if (e != null)
			ClearPurForm();

	}

	public void trafficSearchButtonClick() {
		salesPanel.setVisible(false);
		expensesPanel.setVisible(false);
		purchasesPanel.setVisible(false);
		TrafficSearchBorderPane.setVisible(true);
		IncomesPanel.setVisible(false);

	}

	@FXML
	Label casherlabel;


	public void welcomeLabel() throws Exception {
		double boxcount = GetCountofBox.GetCountofBox(null);
		//  String res=Double.toString(boxcount);
		casherlabel.setText(boxcount + "");
	}


	//--------- Purchasses Add Button ---------

	public void purchasesAddclick() throws IOException {
		Main obj = new Main();
		obj.doPurchasessaddWindow();

	}

	//order Button Function
	public void orderButtonClick() throws IOException {


		Main obj = new Main();
		obj.doOrderWindow();
	}

	//pay button method
	public void payButtonClick() throws IOException {

		//Payment Button Clicked
		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);  //new alert object
		alert.setTitle("Confirmation");  //warning box title
		alert.setHeaderText("Payment Confirmation!");// Header
		alert.setContentText("Are You Sure You Want to Confrim the Payment?"); //Discription of warning
		alert.getDialogPane().setPrefSize(400, 200); //sets size of alert box
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			//yesbutton Clicked

			Savelist();
		} else {
			// ... user chose NO or closed the dialog
		}
	}

	public void payexpClick() throws IOException {

		//Payment Button Clicked
		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);  //new alert object
		alert.setTitle("Confirmation");  //warning box title
		alert.setHeaderText("Payment Confirmation!");// Header
		alert.setContentText("Are You Sure You Want to Confrim the Payment?"); //Discription of warning
		alert.getDialogPane().setPrefSize(400, 200); //sets size of alert box
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			//yesbutton Clicked

			Saveexpense();
		} else {
			// ... user chose NO or closed the dialog
		}
	}

	/////////////////////////////// income /////////////////////////
	public void payIncomeClick() throws IOException {

		//Payment Button Clicked
		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);  //new alert object
		alert.setTitle("Confirmation");  //warning box title
		alert.setHeaderText("Payment Confirmation!");// Header
		alert.setContentText("Are You Sure You Want to Confrim the Payment?"); //Discription of warning
		alert.getDialogPane().setPrefSize(400, 200); //sets size of alert box
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) {
			//yesbutton Clicked
			Saveincome();
		} else {
			// ... user chose NO or closed the dialog
		}
	}

	@FXML
	TextField ExpenseName1, Expenseamount1;
	@FXML
	DatePicker DateExpens, incomesdate;
	@FXML
	TextArea Expensearea1;

	void CleaincoForm() {
		ExpenseName1.setText("");
		Expenseamount1.setText("");
		Expensearea1.setText("");
	}

	public void Saveincome() {
		if (ExpenseName1.getText().isEmpty() || Expenseamount1.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("لا يمكن حفظ الفاتورة");
			alert.setContentText("تأكد من إدخال جميع المعلومات.");
			alert.showAndWait();
			return;
		}
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				String query = "INSERT INTO  incomes (name ,amount ,Notes,incomes_date) VALUES ('" + ExpenseName1.getText() + "','" + Expenseamount1.getText() + "','" + Expensearea1.getText() + "','" + incomesdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "')";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();

				Alert yesalert = new Alert(AlertType.CONFIRMATION);
				yesalert.setTitle("Payment Status");
				yesalert.setHeaderText(null);
				yesalert.setContentText("تم إدخال الفاتوة إلى قاعدة البيانات بنجاح");
				yesalert.showAndWait();
				CleaincoForm();
			}
		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("خطأ في البيانات المدخلة");
			alert.setContentText("الرجاء التأكد من إدخال جميع بيانات الفاتورة.");
			alert.showAndWait();
		}
	}

	////////////////////////////////////////////////////////////////
	//  -------------------------------------------------Returned Methods ---------------
	public void retSalesAddButtonClick() throws IOException {
		obj.doRetSalesAddWindow();

	}

	public void retPurchAddButtonClick() throws IOException {
		obj.doRetPurchAddWindow();

	}

	//  --------------- Returned Buttons Click ------
	public void salesReturnedClick(ActionEvent e) {


		salesreturned.setVisible(true);
		purchasesreturned.setVisible(false);
		if (e != null) ClearReSalesForm();

	}

	public void purchasesReturnedClick(ActionEvent e) {

		salesreturned.setVisible(false);
		purchasesreturned.setVisible(true);
		if (e != null) CleaRePurForm();


	}

	public void datecheck() {
		st_datecheck.setSelected(true);
		st_materialcheck.setSelected(false);
		st_stockingcheck.setSelected(false);
	}

	public void materialcheck() {
		st_datecheck.setSelected(false);
		st_materialcheck.setSelected(true);
		st_stockingcheck.setSelected(false);
	}

	public void stockingcheck() {
		st_datecheck.setSelected(false);
		st_materialcheck.setSelected(false);
		st_stockingcheck.setSelected(true);
	}

	// print method

	//   ------------------------ Statistices Charts ---------------------
	@FXML
	TextField st_material = new TextField();
	@FXML
	JFXRadioButton st_datecheck = new JFXRadioButton();
	@FXML
	JFXRadioButton st_materialcheck = new JFXRadioButton();
	@FXML
	JFXRadioButton st_stockingcheck = new JFXRadioButton();
	@FXML
	DatePicker st_datestocking = new DatePicker();

	public void chartShowButtonClick() {

		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();

		BarChart chart = new BarChart(xAxis, yAxis);
		chart.setTitle("Stocking Charts ");
		double sales = Statistices.getsales(st_datefrom.getValue(), st_dateto.getValue());
		double purchases = Statistices.getpurchasess(st_datefrom.getValue(), st_dateto.getValue());
		double incomes = Statistices.getincomes(st_datefrom.getValue(), st_dateto.getValue());
		double spending = Statistices.getspending(st_datefrom.getValue(), st_dateto.getValue());
		double total = (sales + incomes) - (purchases + spending);
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Sales & purcaheses");
		series1.getData().add(new XYChart.Data("Sales", Statistices.getsales(st_datefrom.getValue(), st_dateto.getValue())));
		series1.getData().add(new XYChart.Data("Purchases", Statistices.getpurchasess(st_datefrom.getValue(), st_dateto.getValue())));


		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Sales for material: " + st_material.getText());
		series2.getData().add(new XYChart.Data("Sales for Material", Statistices.getSalesForMaterial(st_datefrom.getValue(), st_dateto.getValue(), st_material.getText())));

		XYChart.Series series4 = new XYChart.Series();
		series2.setName("Purchases for material: " + st_material.getText());
		series4.getData().add(new XYChart.Data("Purchases  for Material", Statistices.getPurchasesForMaterial(st_datefrom.getValue(), st_dateto.getValue(), st_material.getText())));

		XYChart.Series series3 = new XYChart.Series();
		series3.setName("Expenses & Incomes");
		series3.getData().add(new XYChart.Data("Spending", Statistices.getspending(st_datefrom.getValue(), st_dateto.getValue())));
		series3.getData().add(new XYChart.Data("incomes", Statistices.getincomes(st_datefrom.getValue(), st_dateto.getValue())));

//       series3.getData().add(new XYChart.Data("Purchases", Statistices.getPurchasesForMaterial(st_datefrom.getValue(),st_dateto.getValue(),st_material.getText())));
		XYChart.Series series5 = new XYChart.Series();
		series5.setName("Total");
		series5.getData().add(new XYChart.Data("total", total));

		XYChart.Series series6 = new XYChart.Series();
		series6.setName("Stocking");
		try {
			if (st_stockingcheck.isSelected())
				series6.getData().add(new XYChart.Data("Stocking", GetCountofBox.GetCountofBox(st_datestocking.getValue().toString())));
		} catch (NullPointerException e) {
			Material.alert("error", "Error In Date ",
					"please fill the date text");
		}
		XYChart.Series series66 = new XYChart.Series();

		statistceBorder.setCenter(chart);
		if (st_datecheck.isSelected()) {
			chart.getData().addAll(series1, series3, series5);
		} else if (st_materialcheck.isSelected()) {
			chart.getData().addAll(series2, series4);
		} else if (st_stockingcheck.isSelected()) {
			chart.getData().addAll(series6, series66);
		}
	}

	// --------------------------Settings Methods ------------
	// --------------General Button Click ----------
	public void settingsGeneralButton() {
		settingsGeneral.setVisible(true);
		settingsBackup.setVisible(false);
	}

	// ------------------------  Backup Button Click -----------
	public void settingsBackupButton() {
		settingsGeneral.setVisible(false);
		settingsBackup.setVisible(true);
	}

	//   ------------------------
	@FXML
	TextField ExpenseName, Expenseamount;
	@FXML
	TextArea Expensearea;

	void ClearexpForm() {
		ExpenseName.setText("");
		Expenseamount.setText("");
		Expensearea.setText("");
	}

	public void Saveexpense() {
		if (ExpenseName.getText().isEmpty() || Expenseamount.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("لا يمكن حفظ الفاتورة");
			alert.setContentText("تأكد من إدخال جميع المعلومات.");
			alert.showAndWait();
			return;
		}
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				String query = "INSERT INTO spending (name ,amount ,Notes,sp_date) VALUES ('" + ExpenseName.getText() + "','" + Expenseamount.getText() + "','" + Expensearea.getText() + "', '" + DateExpens.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "')";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();

				Alert yesalert = new Alert(AlertType.CONFIRMATION);
				yesalert.setTitle("Payment Status");
				yesalert.setHeaderText(null);
				yesalert.setContentText("تم إدخال الفاتوة إلى قاعدة البيانات بنجاح");
				yesalert.showAndWait();
				ClearexpForm();
			}
		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("خطأ في البيانات المدخلة");
			alert.setContentText("الرجاء التأكد من إدخال جميع بيانات الفاتورة.");
			alert.showAndWait();
		}
	}

	//Inventory Buttons Click
	public void addMaterialButtonClick() {
		addMaterialBorder.setVisible(true);
		showMaterialBorder.setVisible(false);
		inmatBorder.setVisible(false);
		outMatBorder.setVisible(false);
//            material_table.getItems().clear();
		InventorySearchBorderPane.setVisible(false);
	}

	public void showMaterialButtonClick() {
		showMaterialBorder.setVisible(true);
		addMaterialBorder.setVisible(false);
		inmatBorder.setVisible(false);
		outMatBorder.setVisible(false);
	}

	public void inMatButtonClick() {
		Material.autocomplete(mat_name_in);
		inmatBorder.setVisible(true);
		showMaterialBorder.setVisible(false);
		addMaterialBorder.setVisible(false);
		outMatBorder.setVisible(false);
		InventorySearchBorderPane.setVisible(false);
		in_Storage.clear();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
		Date date = new Date();
		mat_date_in.setValue(LocalDate.now());
	}

	public void outMatButtonClick() {
		outMatBorder.setVisible(true);
		inmatBorder.setVisible(false);
		showMaterialBorder.setVisible(false);
		addMaterialBorder.setVisible(false);
		InventorySearchBorderPane.setVisible(false);
		Material.autocomplete(mat_name_out);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
		Date date = new Date();
		mat_date_out.setValue(LocalDate.now());
	}

	//  ---------------------- Inventory Search Button ------------
	public void searchInventoryButton() {
		outMatBorder.setVisible(false);
		inmatBorder.setVisible(false);
		showMaterialBorder.setVisible(false);
		addMaterialBorder.setVisible(false);
		InventorySearchBorderPane.setVisible(true);
		in_Storage.clear();
		Material.autocomplete(matnamesearch);
	}


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Material.autocomplete(st_material);
		autocompletecusromer(customer_tf);
		autocompletecusromer(CustomName);
		autocompletecusromer(ResiptID11);
		autocompletecusromer(ResiptID111);
		autocompletecusromer(BuyerName);
		try {
			welcomeLabel();
		} catch (Exception e) {
			e.printStackTrace();
		}

		TableOrder.setEditable(true);
		Bar.setCellValueFactory(new PropertyValueFactory<>("Bar"));
		Uniname.setCellValueFactory(new PropertyValueFactory<>("Uniname"));
		QTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
		Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
		Subtotal.setCellValueFactory(new PropertyValueFactory<>("Subtotal"));
		Discount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
		Total.setCellValueFactory(new PropertyValueFactory<>("Total"));

		QTY.setCellFactory(TextFieldTableCell.forTableColumn());
		Price.setCellFactory(TextFieldTableCell.forTableColumn());
		Discount.setCellFactory(TextFieldTableCell.forTableColumn());
		TableOrder.setItems(data);
		getMaxinBoxID();

		data.addListener(new ListChangeListener<OrderRecord>() {

			@Override
			public void onChanged(Change<? extends OrderRecord> pChange) {
				while (pChange.next()) {
					CalculateResultTotal();

				}
			}
		});
		Purchasesdata.addListener(new ListChangeListener<PurchaseRecord>() {
			@Override
			public void onChanged(Change<? extends PurchaseRecord> pChange) {
				while (pChange.next()) {
					CalculatePurTotal();
				}
			}
		});
		ReSalesdata.addListener(new ListChangeListener<PurchaseRecord>() {

			@Override
			public void onChanged(Change<? extends PurchaseRecord> pChange) {
				while (pChange.next()) {
					CalculateReSalesTotal();
				}
			}
		});
		RePurChases.addListener(new ListChangeListener<OrderRecord>() {

			@Override
			public void onChanged(Change<? extends OrderRecord> pChange) {
				while (pChange.next()) {
					CalculateRePurchasesTotal();
				}
			}
		});

		////////////////////--------------------- initialize RePurchases Table
		TableOrder11.setEditable(true);
		Bar11.setCellValueFactory(new PropertyValueFactory<>("Bar"));
		Uniname11.setCellValueFactory(new PropertyValueFactory<>("Uniname"));
		QTY11.setCellValueFactory(new PropertyValueFactory<>("QTY"));
		Price11.setCellValueFactory(new PropertyValueFactory<>("Price"));
		Subtotal11.setCellValueFactory(new PropertyValueFactory<>("Subtotal"));
		Discount11.setCellValueFactory(new PropertyValueFactory<>("Discount"));
		Total11.setCellValueFactory(new PropertyValueFactory<>("Total"));

		QTY11.setCellFactory(TextFieldTableCell.forTableColumn());
		Price11.setCellFactory(TextFieldTableCell.forTableColumn());
		Discount11.setCellFactory(TextFieldTableCell.forTableColumn());
		TableOrder11.setItems(RePurChases);
		getMaxinBoxID();
		////////////////////---------------------- initialize Purchases Table
		TableOrder2.setEditable(true);
		PBar.setCellValueFactory(new PropertyValueFactory<>("PBar"));
		PUniname.setCellValueFactory(new PropertyValueFactory<>("PUniname"));
		PQTY.setCellValueFactory(new PropertyValueFactory<>("PQTY"));
		PPrice.setCellValueFactory(new PropertyValueFactory<>("PPrice"));
		PSubtotal.setCellValueFactory(new PropertyValueFactory<>("PSubtotal"));
		PDiscount.setCellValueFactory(new PropertyValueFactory<>("PDiscount"));
		PTotal.setCellValueFactory(new PropertyValueFactory<>("PTotal"));

		PQTY.setCellFactory(TextFieldTableCell.forTableColumn());
		PPrice.setCellFactory(TextFieldTableCell.forTableColumn());
		PDiscount.setCellFactory(TextFieldTableCell.forTableColumn());
		TableOrder2.setItems(Purchasesdata);
		getMaxoutBoxID();

		//////////////////// -------------------------initialize return sales Table
		TableOrder1.setEditable(true);
		Bar1.setCellValueFactory(new PropertyValueFactory<>("PBar"));
		Uniname1.setCellValueFactory(new PropertyValueFactory<>("PUniname"));
		QTY1.setCellValueFactory(new PropertyValueFactory<>("PQTY"));
		Price1.setCellValueFactory(new PropertyValueFactory<>("PPrice"));
		Subtotal1.setCellValueFactory(new PropertyValueFactory<>("PSubtotal"));
		Discount1.setCellValueFactory(new PropertyValueFactory<>("PDiscount"));
		Total1.setCellValueFactory(new PropertyValueFactory<>("PTotal"));

		QTY1.setCellFactory(TextFieldTableCell.forTableColumn());
		Price1.setCellFactory(TextFieldTableCell.forTableColumn());
		Discount1.setCellFactory(TextFieldTableCell.forTableColumn());
		TableOrder1.setItems(ReSalesdata);
//        -------------------------------------------material search----------------
		searchmaterialtableview.setEditable(true);
		sh_id.setCellValueFactory(new PropertyValueFactory<>("id_in"));
		sh_name.setCellValueFactory(new PropertyValueFactory<>("mat_name"));
//        sh_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		sh_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		searchmaterialtableview.setItems(in_Storage);


		//---------------------- update order list -----------------
		QTY.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OrderRecord, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<OrderRecord, String> event) {
				String newValue = event.getNewValue();
				if (!ISNum(newValue) || newValue.equals("0")) {

					OrderRecord old_row = event.getRowValue();
					old_row.setQTY(event.getOldValue());
					int index = data.indexOf(old_row);
					data.get(index).setQTY(event.getOldValue());
					TableOrder.refresh();
					return;
				}
				int stock = GetCountofMaterial(Integer.parseInt(Matname2ID(event.getRowValue().Uniname)));
				if (stock - Integer.parseInt(event.getNewValue()) < 0) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("خطأ في الكمية");
					alert.setContentText("الكيمة المتبقية في المستودع للمادة المدخلة هي : " + stock);
					alert.showAndWait();
					newValue = stock + "";
					event.getRowValue().QTY = newValue;
				}

				OrderRecord old_row = event.getRowValue();
				old_row.setQTY(event.getOldValue());

				int index = data.indexOf(old_row);
				data.get(index).setQTY(newValue);

				TableOrder.refresh();
				CalculateResultTotal();


			}

		});
		Price.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OrderRecord, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<OrderRecord, String> event) {
				String newValue = event.getNewValue();
				if (!ISNum(newValue)) {

					OrderRecord old_row = event.getRowValue();
					old_row.setPrice(event.getOldValue());
					int index = data.indexOf(old_row);
					data.get(index).setPrice(event.getOldValue());
					TableOrder.refresh();
					return;
				}
				OrderRecord old_row = event.getRowValue();
				old_row.setPrice(event.getOldValue());
				int index = data.indexOf(old_row);
				data.get(index).setPrice(newValue);
				TableOrder.refresh();
				CalculateResultTotal();
			}

		});
		Discount.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OrderRecord, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<OrderRecord, String> event) {
				String newValue = event.getNewValue();
				if (!ISNum(newValue)) {
					OrderRecord old_row = event.getRowValue();
					old_row.setDiscount(event.getOldValue());
					int index = data.indexOf(old_row);
					data.get(index).setDiscount(event.getOldValue());
					TableOrder.refresh();
					return;
				}
				OrderRecord old_row = event.getRowValue();
				old_row.setDiscount(event.getOldValue());
				int index = data.indexOf(old_row);
				data.get(index).setDiscount(newValue);

				TableOrder.refresh();
				CalculateResultTotal();
			}

		});
		QTY11.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OrderRecord, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<OrderRecord, String> event) {
				String newValue = event.getNewValue();
				if (!ISNum(newValue) || newValue.equals("0")) {

					OrderRecord old_row = event.getRowValue();
					old_row.setQTY(event.getOldValue());
					int index = RePurChases.indexOf(old_row);
					RePurChases.get(index).setQTY(event.getOldValue());
					TableOrder11.refresh();
					return;
				}
				int stock = GetCountofMaterial(Integer.parseInt(Matname2ID(event.getRowValue().Uniname)));
				if (stock - Integer.parseInt(event.getNewValue()) < 0) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("خطأ في الكمية");
					alert.setContentText("الكيمة المتبقية في المستودع للمادة المدخلة هي : " + stock);
					alert.showAndWait();
					newValue = stock + "";
					event.getRowValue().QTY = newValue;
				}
				OrderRecord old_row = event.getRowValue();
				old_row.setQTY(event.getOldValue());
				int index = RePurChases.indexOf(old_row);
				RePurChases.get(index).setQTY(newValue);
				TableOrder11.refresh();
				CalculateRePurchasesTotal();
			}
		});
		Price11.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OrderRecord, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<OrderRecord, String> event) {
				String newValue = event.getNewValue();
				if (!ISNum(newValue)) {
					OrderRecord old_row = event.getRowValue();
					old_row.setPrice(event.getOldValue());
					int index = RePurChases.indexOf(old_row);
					RePurChases.get(index).setPrice(event.getOldValue());
					TableOrder11.refresh();
					return;
				}
				OrderRecord old_row = event.getRowValue();
				old_row.setPrice(event.getOldValue());
				int index = RePurChases.indexOf(old_row);
				RePurChases.get(index).setPrice(newValue);
				TableOrder11.refresh();
				CalculateRePurchasesTotal();
			}
		});
		Discount11.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OrderRecord, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<OrderRecord, String> event) {
				String newValue = event.getNewValue();
				if (!ISNum(newValue)) {
					OrderRecord old_row = event.getRowValue();
					old_row.setDiscount(event.getOldValue());
					int index = RePurChases.indexOf(old_row);
					RePurChases.get(index).setDiscount(event.getOldValue());
					TableOrder11.refresh();
					return;
				}
				OrderRecord old_row = event.getRowValue();
				old_row.setDiscount(event.getOldValue());
				int index = RePurChases.indexOf(old_row);
				RePurChases.get(index).setDiscount(newValue);
				TableOrder11.refresh();
				CalculateRePurchasesTotal();
			}
		});
		PDiscount.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PurchaseRecord, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<PurchaseRecord, String> event) {
				String newValue = event.getNewValue();
				if (!ISNum(newValue)) {
					PurchaseRecord old_row = event.getRowValue();
					old_row.setPDiscount(event.getOldValue());
					int index = Purchasesdata.indexOf(old_row);
					Purchasesdata.get(index).setPDiscount(event.getOldValue());
					TableOrder2.refresh();
					return;
				}
				PurchaseRecord old_row = event.getRowValue();
				old_row.setPDiscount(event.getOldValue());
				int index = Purchasesdata.indexOf(old_row);
				Purchasesdata.get(index).setPDiscount(newValue);
				TableOrder2.refresh();
				CalculatePurTotal();
			}

		});
		PQTY.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PurchaseRecord, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<PurchaseRecord, String> event) {
				String newValue = event.getNewValue();
				if (!ISNum(newValue) || newValue.equals("0")) {
					PurchaseRecord old_row = event.getRowValue();
					old_row.setPQTY(event.getOldValue());
					int index = Purchasesdata.indexOf(old_row);
					Purchasesdata.get(index).setPQTY(event.getOldValue());
					TableOrder2.refresh();
					return;
				}
				PurchaseRecord old_row = event.getRowValue();
				old_row.setPQTY(event.getOldValue());
				int index = Purchasesdata.indexOf(old_row);
				Purchasesdata.get(index).setPQTY(newValue);
				TableOrder2.refresh();
				CalculatePurTotal();
			}
		});
		PPrice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PurchaseRecord, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<PurchaseRecord, String> event) {
				String newValue = event.getNewValue();
				if (!ISNum(newValue)) {
					PurchaseRecord old_row = event.getRowValue();
					old_row.setPPrice(event.getOldValue());
					int index = Purchasesdata.indexOf(old_row);
					Purchasesdata.get(index).setPPrice(event.getOldValue());
					TableOrder2.refresh();
					return;
				}
				PurchaseRecord old_row = event.getRowValue();
				old_row.setPPrice(event.getOldValue());
				int index = Purchasesdata.indexOf(old_row);
				Purchasesdata.get(index).setPPrice(newValue);
				TableOrder2.refresh();
				CalculatePurTotal();
			}
		});
		Discount1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PurchaseRecord, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<PurchaseRecord, String> event) {
				String newValue = event.getNewValue();
				if (!ISNum(newValue)) {
					PurchaseRecord old_row = event.getRowValue();
					old_row.setPDiscount(event.getOldValue());
					int index = ReSalesdata.indexOf(old_row);
					ReSalesdata.get(index).setPDiscount(event.getOldValue());
					TableOrder1.refresh();
					return;
				}
				PurchaseRecord old_row = event.getRowValue();
				old_row.setPDiscount(event.getOldValue());
				int index = ReSalesdata.indexOf(old_row);
				ReSalesdata.get(index).setPDiscount(newValue);
				TableOrder1.refresh();
				CalculateReSalesTotal();
			}
		});
		QTY1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PurchaseRecord, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<PurchaseRecord, String> event) {
				String newValue = event.getNewValue();
				if (!ISNum(newValue) || newValue.equals("0")) {
					PurchaseRecord old_row = event.getRowValue();
					old_row.setPQTY(event.getOldValue());
					int index = ReSalesdata.indexOf(old_row);
					ReSalesdata.get(index).setPQTY(event.getOldValue());
					TableOrder1.refresh();
					return;
				}
				PurchaseRecord old_row = event.getRowValue();
				old_row.setPQTY(event.getOldValue());
				int index = ReSalesdata.indexOf(old_row);
				ReSalesdata.get(index).setPQTY(newValue);
				TableOrder1.refresh();
				CalculateReSalesTotal();
			}
		});
		Price1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<PurchaseRecord, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<PurchaseRecord, String> event) {
				String newValue = event.getNewValue();
				if (!ISNum(newValue)) {
					PurchaseRecord old_row = event.getRowValue();
					old_row.setPPrice(event.getOldValue());
					int index = ReSalesdata.indexOf(old_row);
					ReSalesdata.get(index).setPPrice(event.getOldValue());
					TableOrder1.refresh();
					return;
				}
				PurchaseRecord old_row = event.getRowValue();
				old_row.setPPrice(event.getOldValue());
				int index = ReSalesdata.indexOf(old_row);
				ReSalesdata.get(index).setPPrice(newValue);
				TableOrder1.refresh();
				CalculateReSalesTotal();
			}

		});
		//-------------- inventory privacy
		m_id.setCellValueFactory(new PropertyValueFactory<>("material_id"));
		m_name.setCellValueFactory(new PropertyValueFactory<>("material_name"));
		m_unit.setCellValueFactory(new PropertyValueFactory<>("material_unit"));
		m_notes.setCellValueFactory(new PropertyValueFactory<>("material_notes"));
		m_barcode.setCellValueFactory(new PropertyValueFactory<>("material_barcode"));
//--------------------------------------- in storage
		id_in_col.setCellValueFactory(new PropertyValueFactory<>("id_in"));
		mat_name_in_col.setCellValueFactory(new PropertyValueFactory<>("mat_name"));
		quantity_in_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		date_in_col.setCellValueFactory(new PropertyValueFactory<>("date"));
		id_in.setCellValueFactory(new PropertyValueFactory<>("id_in"));
////--------------------------------------- out storage
		id_out_col.setCellValueFactory(new PropertyValueFactory<>("id_in"));
		mat_name_out_col.setCellValueFactory(new PropertyValueFactory<>("mat_name"));
		mat_date_out_col.setCellValueFactory(new PropertyValueFactory<>("date"));
		mat_quantity_out_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		id_out.setCellValueFactory(new PropertyValueFactory<>("id_in"));
		//----------------------------------------------- initialize traffic search table----
		searchtraffictable.setEditable(true);
		s_id.setCellValueFactory(new PropertyValueFactory<>("s_recipid"));
		s_customer.setCellValueFactory(new PropertyValueFactory<>("s_customer"));
		s_total.setCellValueFactory(new PropertyValueFactory<>("s_total"));
		s_type.setCellValueFactory(new PropertyValueFactory<>("s_recipt_type"));
		s_date.setCellValueFactory(new PropertyValueFactory<>("s_date"));

//---------------------------------------- search test --------------------
		//------------------------context menu  delete record ---------------------------
		searchtraffictable.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.SECONDARY) {
					ContextMenu contextMenu = new ContextMenu();
					MenuItem menuItem1 = new CheckMenuItem("Delete");
					contextMenu.getItems().addAll(menuItem1);
					searchtraffictable.setContextMenu(contextMenu);
					contextMenu.show(searchtraffictable.getClip(), 80, 140);
					menuItem1.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							ObservableList<TrafficSearch> trafficSearch = searchtraffictable.getSelectionModel().getSelectedItems();
							if (trafficSearch.get(0) == null) {
								Material.alert("Erorr", "An Erorr", "Please Select An Record...");
							} else {
								if (trafficSearch.get(0).getS_recipt_type() == "sales") {
									Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);  //new alert object
									alert.setTitle("Confirmation");  //warning box title
									alert.setHeaderText("Delete Confirmation!");// Header
									alert.setContentText("Are You Sure You Want to Delete This Record?"); //Discription of warning
									alert.getDialogPane().setPrefSize(400, 200); //sets size of alert box
									Optional<ButtonType> result = alert.showAndWait();
									if (result.get() == ButtonType.YES) {
										String id = trafficSearch.get(0).getS_recipid() + "";
										TrafficSearch.deleteRecord(id, "id_in_box", "input_box");
										TrafficSearch.deleteRecord(id, "id_in_box", "input_box_hdr");
										searchtraffictable.getItems().remove(searchtraffictable.getSelectionModel().getSelectedItem());
										searchtraffictable.refresh();
									}
								} else if (trafficSearch.get(0).getS_recipt_type() == "purchasess") {
									Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);  //new alert object
									alert.setTitle("Confirmation");  //warning box title
									alert.setHeaderText("Delete Confirmation!");// Header
									alert.setContentText("Are You Sure You Want to Delete This Record?"); //Discription of warning
									alert.getDialogPane().setPrefSize(400, 200); //sets size of alert box
									Optional<ButtonType> result = alert.showAndWait();
									if (result.get() == ButtonType.YES) {
										String id = trafficSearch.get(0).getS_recipid() + "";
										TrafficSearch.deleteRecord(id, "id_out_box", "output_box");
										TrafficSearch.deleteRecord(id, "id_out_box", "output_box_hdr");
										searchtraffictable.getItems().remove(searchtraffictable.getSelectionModel().getSelectedItem());
										searchtraffictable.refresh();
									}
								} else if (trafficSearch.get(0).getS_recipt_type() == "purchasess_return") {
									Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);  //new alert object
									alert.setTitle("Confirmation");  //warning box title
									alert.setHeaderText("Delete Confirmation!");// Header
									alert.setContentText("Are You Sure You Want to Delete This Record?"); //Discription of warning
									alert.getDialogPane().setPrefSize(400, 200); //sets size of alert box
									Optional<ButtonType> result = alert.showAndWait();
									if (result.get() == ButtonType.YES) {
										String id = trafficSearch.get(0).getS_recipid() + "";
										TrafficSearch.deleteRecord(id, "id_in_box", "input_box");
										TrafficSearch.deleteRecord(id, "id_in_box", "input_box_hdr");
										searchtraffictable.getItems().remove(searchtraffictable.getSelectionModel().getSelectedItem());
										searchtraffictable.refresh();
									}
								} else if (trafficSearch.get(0).getS_recipt_type() == "sales_return") {
//                                    Material.alert("ok","ok","Please Select An Record...");
									Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);  //new alert object
									alert.setTitle("Confirmation");  //warning box title
									alert.setHeaderText("Delete Confirmation!");// Header
									alert.setContentText("Are You Sure You Want to Delete This Record?"); //Discription of warning
									alert.getDialogPane().setPrefSize(400, 200); //sets size of alert box
									Optional<ButtonType> result = alert.showAndWait();
									if (result.get() == ButtonType.YES) {
										String id = trafficSearch.get(0).getS_recipid() + "";
										TrafficSearch.deleteRecord(id, "id_out_box", "output_box");
										TrafficSearch.deleteRecord(id, "id_out_box", "output_box_hdr");
										searchtraffictable.getItems().remove(searchtraffictable.getSelectionModel().getSelectedItem());
										searchtraffictable.refresh();
									}
								} else if (trafficSearch.get(0).getS_recipt_type() == "expenses") {
//                                    Material.alert("ok","ok","Please Select An Record...");
									Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);  //new alert object
									alert.setTitle("Confirmation");  //warning box title
									alert.setHeaderText("Delete Confirmation!");// Header
									alert.setContentText("Are You Sure You Want to Delete This Record?"); //Discription of warning
									alert.getDialogPane().setPrefSize(400, 200); //sets size of alert box
									Optional<ButtonType> result = alert.showAndWait();
									if (result.get() == ButtonType.YES) {
										String id = trafficSearch.get(0).getS_recipid() + "";
										TrafficSearch.deleteRecord(id, "id_sp", "spending");
										searchtraffictable.getItems().remove(searchtraffictable.getSelectionModel().getSelectedItem());
										searchtraffictable.refresh();
									}
								} else if (trafficSearch.get(0).getS_recipt_type() == "incomes") {
//                                    Material.alert("ok","ok","Please Select An Record...");
									Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);  //new alert object
									alert.setTitle("Confirmation");  //warning box title
									alert.setHeaderText("Delete Confirmation!");// Header
									alert.setContentText("Are You Sure You Want to Delete This Record?"); //Discription of warning
									alert.getDialogPane().setPrefSize(400, 200); //sets size of alert box
									Optional<ButtonType> result = alert.showAndWait();
									if (result.get() == ButtonType.YES) {
										String id = trafficSearch.get(0).getS_recipid() + "";
										TrafficSearch.deleteRecord(id, "id_in", "incomes");
										searchtraffictable.getItems().remove(searchtraffictable.getSelectionModel().getSelectedItem());
										searchtraffictable.refresh();
									}
								}
							}
						}
					});
				} else if ((event.getButton() == MouseButton.PRIMARY)) {
					if (event.getClickCount() == 2) {
						ObservableList<TrafficSearch> trafficSearches = searchtraffictable.getSelectionModel().getSelectedItems();
						if (trafficSearches.get(0).getS_recipt_type().equals("sales")) {
							salesClick(null);
							ResiptID.setText(trafficSearches.get(0).getS_recipid() + "");
							DateTB.setValue(LocalDate.parse(trafficSearches.get(0).getS_date()));
							CustomName.setText(trafficSearches.get(0).getS_customer());
							TableOrder.getItems().clear();
							TrafficSearch.getSalesTraffic(ResiptID.getText());

						} else if (trafficSearches.get(0).getS_recipt_type().equals("purchasess")) {
							purchasesClick(null);
							BuyerName.setText(trafficSearches.get(0).getS_customer());
							ResiptID1.setText(trafficSearches.get(0).getS_recipid() + "");
							DateTB1.setValue(LocalDate.parse(trafficSearches.get(0).getS_date()));
							TableOrder2.getItems().clear();
							TrafficSearch.getPurchasessTraffic(ResiptID1.getText());
						} else if (trafficSearches.get(0).getS_recipt_type().equals("purchasess_return")) {
//                    TrafficSearchBorderPane.setVisible(false);
							returnedClick();
							purchasesReturnedClick(null);
							ResiptID11.setText(trafficSearches.get(0).getS_customer());
							CustomName111.setText(trafficSearches.get(0).getS_recipid() + "");
							TableOrder11.getItems().clear();
							DateTB111.setValue(LocalDate.parse(trafficSearches.get(0).getS_date()));

							TrafficSearch.getRePurchasessTraffic(CustomName111.getText());
						} else if (trafficSearches.get(0).getS_recipt_type().equals("sales_return")) {
							returnedClick();
							salesReturnedClick(null);
							ResiptID111.setText(trafficSearches.get(0).getS_customer());
							OriginID.setText(trafficSearches.get(0).getS_recipid() + "");
							DateTB11.setValue(LocalDate.parse(trafficSearches.get(0).getS_date()));
							TableOrder1.getItems().clear();
							TrafficSearch.getReSalesTraffic(OriginID.getText());
						}
					}
				}
			}
		});
//-----------------------------------------------------------
		material_table.setEditable(true);
		m_name.setCellFactory(TextFieldTableCell.forTableColumn());
		m_barcode.setCellFactory(TextFieldTableCell.forTableColumn());
		m_unit.setCellFactory(TextFieldTableCell.forTableColumn());
		m_notes.setCellFactory(TextFieldTableCell.forTableColumn());
		//----------------------- initialize  menue button --------
//    System.out.println(reciptTypeButton.getItems());
		all.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				reciptTypeButton.setId(all.getId());
				reciptTypeButton.setText(all.getId());
			}
		});
		sales.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				reciptTypeButton.setId(sales.getId());
				reciptTypeButton.setText(sales.getId());
			}
		});
		purchasess.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				reciptTypeButton.setId(purchasess.getId());
				reciptTypeButton.setText(purchasess.getId());
			}
		});
		purchasess_return.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				reciptTypeButton.setId(purchasess_return.getId());
				reciptTypeButton.setText(purchasess_return.getId());
			}
		});
		sales_return.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				reciptTypeButton.setId(sales_return.getId());
				reciptTypeButton.setText(sales_return.getId());
			}
		});
		expenses.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				reciptTypeButton.setId(expenses.getId());
				reciptTypeButton.setText(expenses.getId());
			}
		});
		incomes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				reciptTypeButton.setId(incomes.getId());
				reciptTypeButton.setText(incomes.getId());
			}
		});
		//---------------------- update material name -----------------
		m_name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Material, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Material, String> event) {
				Material mat = event.getRowValue();
				mat.update_material(mat.getMaterial_id(), event.getNewValue(), "mat_name");
				Material old_material = event.getRowValue();
				int index = data1.indexOf(old_material);
				data1.get(index).setMaterial_name(event.getNewValue());
			}
		});
		m_barcode.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Material, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Material, String> event) {
				Material mat = event.getRowValue();
				mat.update_material(mat.getMaterial_id(), event.getNewValue(), "barcode");
				Material old_material = event.getRowValue();
				int index = data1.indexOf(old_material);
				data1.get(index).setMaterial_barcode(event.getNewValue());
			}
		});
		m_unit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Material, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Material, String> event) {
				Material mat = event.getRowValue();
				mat.update_material(mat.getMaterial_id(), event.getNewValue(), "unit");
				Material old_material = event.getRowValue();
				int index = data1.indexOf(old_material);
				data1.get(index).setMaterial_unit(event.getNewValue());
			}
		});
		m_notes.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Material, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Material, String> event) {
				Material mat = event.getRowValue();
				mat.update_material(mat.getMaterial_id(), event.getNewValue(), "notes");
				Material old_material = event.getRowValue();
				int index = data1.indexOf(old_material);
				data1.get(index).setMaterial_notes(event.getNewValue());
			}
		});

		in_table.setEditable(true);
		quantity_in_col.setCellFactory(TextFieldTableCell.forTableColumn());
		quantity_in_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<InOutStorage, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<InOutStorage, String> event) {
				try {
					InOutStorage inout = event.getRowValue();
					Integer.parseInt(event.getNewValue());
					int id = InOutStorage.getid("inside_storage", "in_strg_count", 2);
					InOutStorage.updateQuantity("inside_storage", "in_strg_count", event.getNewValue(), inout.getId_in(), "in_strg_id");
					InOutStorage old_inout = event.getRowValue();
					int index = in_Storage.indexOf(old_inout);
					in_Storage.get(index).setQuantity(event.getNewValue());

				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		});

		out_table.setEditable(true);
		mat_quantity_out_col.setCellFactory(TextFieldTableCell.forTableColumn());
		mat_quantity_out_col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<InOutStorage, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<InOutStorage, String> event) {
				int id = InOutStorage.getid("outside_storage", "out_strg_count", 2);
				InOutStorage old_inout = event.getRowValue();
				int index = out_storage.indexOf(old_inout);
				int countofmaterial = GetCountofMaterial(id);
				System.out.println(countofmaterial);
				try {
					if (countofmaterial < 0 || countofmaterial + Integer.parseInt(event.getOldValue()) < Integer.parseInt(event.getNewValue())) {
						int i = countofmaterial + Integer.parseInt(event.getOldValue());
						Material.alert("Error", "كمية المادة لا تكفي", "لديك من هذه المادة  " + i + "وحدة");
						out_table.refresh();
					} else {
						out_storage.get(index).setQuantity(event.getNewValue());
						InOutStorage.updateQuantity("outside_storage", "out_strg_count", event.getNewValue(), event.getRowValue().getId_in(), "out_strg_id");
					}
				} catch (NumberFormatException e) {
					Material.alert("Error", "الكمية يجب ان كون رقما  ", "الرجاء تغيير الكمية الى قيمة رقمية...");
				}
			}
		});
		choose_file();
	}
//    ---------------------- choose file ------------------------------

	Boolean ISNum(String S) {
		try {

			double r = Double.parseDouble(S);

		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("خطأ في البيانات المدخلة");
			alert.setContentText("الرجاء إدخال قيمة رقمية.");
			alert.showAndWait();
			return false;
		}
		return true;
	}

	static ObservableList<PurchaseRecord> ReSalesdata = FXCollections.observableArrayList();
	@FXML
	TableView TableOrder1; // return sales
	@FXML
	private TableColumn<PurchaseRecord, String> Bar1;
	@FXML
	private TableColumn<PurchaseRecord, String> Uniname1;
	@FXML
	private TableColumn<PurchaseRecord, String> QTY1;
	@FXML
	private TableColumn<PurchaseRecord, String> Price1;
	@FXML
	private TableColumn<PurchaseRecord, String> Subtotal1;
	@FXML
	private TableColumn<PurchaseRecord, String> Discount1;
	@FXML
	private TableColumn<PurchaseRecord, String> Total1;


	@FXML
	void deleteOrderitem() {

		if (data.size() > 0) {
			int index = TableOrder.getSelectionModel().getSelectedIndex();
			if (index < 0)
				return;
			data.remove(index);
			TableOrder.refresh();
		}
	}

	@FXML
	void deletepuritem() {
		if (Purchasesdata.size() > 0) {
			int index = TableOrder2.getSelectionModel().getSelectedIndex();
			if (index < 0)
				return;
			Purchasesdata.remove(index);
			TableOrder2.refresh();
		}
	}

	@FXML
	void deleteRePuritem() {
		if (RePurChases.size() > 0) {
			int index = TableOrder11.getSelectionModel().getSelectedIndex();
			if (index < 0)
				return;
			RePurChases.remove(index);
			TableOrder11.refresh();
		}
	}

	@FXML
	void deleteReSalesitem() {
		if (ReSalesdata.size() > 0) {
			int index = TableOrder1.getSelectionModel().getSelectedIndex();
			if (index < 0)
				return;
			ReSalesdata.remove(index);
			TableOrder1.refresh();
		}
	}

	public void getMaxoutBoxID() {
		Connection con = null;
		Statement stmt = null;

		try {

			ResiptID1.setText("1");
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");

				// Create Query
				String query = "Select Max(id_out_box ) from output_box_hdr ";
				stmt = con.createStatement();
				ResultSet set = stmt.executeQuery(query);
				if (set.next()) {
					String result = set.getString(1);
					if (result != null) {
						int x = Integer.parseInt(result.trim());
						ResiptID1.setText(x + 1 + "");
						CustomName1.setText(x + 1 + "");//for resales .. this ResiptID TextBox not customerrname
					}
					con.close();
				}
			}
		} catch (Exception ex) {
			//Alreat Box For Error
			Material.alert("Connection Error", "Connection Info", ex.getMessage());
		}
	}

	public void getMaxinBoxID() {
		Connection con = null;
		Statement stmt = null;

		try {

			ResiptID.setText("1");
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				// Create Query
				String query = "Select Max(id_in_box) from input_box_hdr ";
				stmt = con.createStatement();
				ResultSet set = stmt.executeQuery(query);
				if (set.next()) {
					String result = set.getString(1);
					if (result != null) {
						int x = Integer.parseInt(result.trim());
						ResiptID.setText(x + 1 + "");
						CustomName11.setText(x + 1 + ""); // ID not customer
					}
					con.close();
				}
			}
		} catch (Exception ex) {
			//Alreat Box For Error
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Connection Error");
			alert.setHeaderText("Connection Info");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
	}

	void CalculateResultTotal() {
		double res = 0;
		for (int i = 0; i < data.size(); i++) {
			res += Double.parseDouble(data.get(i).Total);
		}
		totalmoneytoplabel.setText(String.format("%.2f", res));
	}

	void CalculatePurTotal() {
		double res = 0;
		for (int i = 0; i < Purchasesdata.size(); i++) {
			res += Double.parseDouble(Purchasesdata.get(i).PTotal);
		}
		totalpurtoplabel.setText(String.format("%.2f", res));
	}

	void CalculateReSalesTotal() {
		double res = 0;
		for (int i = 0; i < ReSalesdata.size(); i++) {
			res += Double.parseDouble(ReSalesdata.get(i).PTotal);
		}
		totalmoneytoplabel1.setText(String.format("%.2f", res));
	}

	void CalculateRePurchasesTotal() {
		double res = 0;
		for (int i = 0; i < RePurChases.size(); i++) {
			res += Double.parseDouble(RePurChases.get(i).Total);
		}
		totalmoneytoplabel11.setText(String.format("%.2f", res));
	}

	@FXML
	Label totalpurtoplabel;
	@FXML
	TextField ResiptID1;
	@FXML
	TextField BuyerName;
	@FXML
	DatePicker DateTB1;

	Boolean CheckpurRedundunt(String MatName) {

		for (int i = 0; i < Purchasesdata.size(); i++) {
			if (Purchasesdata.get(i).PUniname.matches(MatName)) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("لا يمكن إضافة المادة");
				alert.setContentText("المادة المدخلة موجودة مسبقاً ضمن قائمة المشتريات");
				alert.showAndWait();
				return true;
			}
		}
		return false;
	}

	Boolean CheckRedundunt(String MatName) {

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).Uniname.matches(MatName)) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("لا يمكن إضافة المادة");
				alert.setContentText("المادة المدخلة موجودة مسبقاً ضمن قائمة المشتريات");
				alert.showAndWait();
				return true;
			}
		}
		return false;
	}


	boolean AddOrderRowTable(String Uniname, String QTY, String Price, String Discount, String Bar) {
		if (CheckRedundunt(Uniname)) return false;
		String matID = Matname2ID(Uniname);
		if (matID != null) {
			int stock = GetCountofMaterial(Integer.parseInt(matID));

			if (stock - Integer.parseInt(QTY) < 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("خطأ في الكمية");
				alert.setContentText("الكيمة المتبقية في المستودع للمادة المدخلة هي : " + stock);
				alert.showAndWait();

				QTY = stock + "";
			}
			OrderRecord record = new OrderRecord(Uniname, QTY, Price, Discount, Bar);
			data.add(record);
		}
		return true;

	}

	static public String Matname2ID(String name) {
		Connection con = null;
		Statement stmt = null;

		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");

				// Create Query
				String query = "Select id_mat from material where mat_name = '" + name + "'";
				stmt = con.createStatement();
				//Result
				ResultSet set = stmt.executeQuery(query);
				if (set.next()) {
					String result = set.getString("id_mat");
					con.close();
					return result;
				} else {
					//Alreat Box For Error
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText("المادة المدخلة غير موجودة ضمن قاعدة البيانات");
					alert.showAndWait();
					con.close();
				}
			}
		} catch (Exception ex) {
			//Alreat Box For Error
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Connection Error");
			alert.setHeaderText("Connection Info");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
		return null;
	}

	static public String Matname2barcode(String name) {
		Connection con = null;
		Statement stmt = null;

		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				// Create Query
				String query = "Select barcode from material where mat_name = '" + name + "'";
				stmt = con.createStatement();
				//Result
				ResultSet set = stmt.executeQuery(query);
				if (set.next()) {
					String result = set.getString("barcode");
					con.close();
					return result;
				} else {
					//Alreat Box For Error
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");

					alert.setContentText("المادة المدخلة غير موجودة ضمن قاعدة البيانات");
					alert.showAndWait();
					con.close();
				}
			}
		} catch (Exception ex) {
			//Alreat Box For Error
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Connection Error");
			alert.setHeaderText("Connection Info");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
		return null;
	}

	void ClearBuyForm() {
		data.clear();
		totalmoneytoplabel.setText("0");
		getMaxinBoxID();
		CustomName.setText("");
	}

	void updateSales() {

		Connection con = null;
		PreparedStatement stmt = null;
		boolean Addedheader = false;
		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				String query = "UPDATE input_box_hdr SET customer_name = '" + CustomName.getText() + "',in_box_date = '" + DateTB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' ,total = '" + totalmoneytoplabel.getText() + "' where id_in_box = " + ResiptID.getText();
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				Addedheader = true;
				query = "delete from input_box where id_in_box = " + ResiptID.getText();
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				for (int i = 0; i < data.size(); i++) {
					// Create Query

					query = "INSERT INTO input_box (id_in_box  ,id_mat ,sub_discount ,mat_price ,mat_count) VALUES (" + ResiptID.getText() + "," + Matname2ID(data.get(i).Uniname) + "," + data.get(i).Discount + "," + data.get(i).Price + "," + data.get(i).QTY + ")";
					stmt = con.prepareStatement(query);
					stmt.executeUpdate();

				}
				Alert yesalert = new Alert(AlertType.CONFIRMATION);
				yesalert.setTitle("Payment Status");
				yesalert.setHeaderText(null);
				yesalert.setContentText("تم إدخال الفاتوة إلى قاعدة البيانات بنجاح");
				yesalert.showAndWait();
				ClearBuyForm();
			}
		} catch (Exception ex) {


			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("خطأ في البيانات المدخلة");
			alert.setContentText("الرجاء التأكد من إدخال جميع بيانات الفاتورة.");
			alert.showAndWait();
		}
	}

	public void Savelist() {
		if (data.size() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("لا يمكن حفظ الفاتورة");
			alert.setContentText("لا توجد مواد ضمن الفاتورة المدخلة.");
			alert.showAndWait();
			return;
		}
		Connection con = null;
		PreparedStatement stmt = null;
		boolean Addedheader = false;
		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				// Create Query
				String query = "Select * from input_box_hdr where id_in_box = " + ResiptID.getText();
				Statement stmt1 = con.createStatement();
				ResultSet set1 = stmt1.executeQuery(query);
				if (set1.next()) {
					updateSales();
					return;
				}
				query = "INSERT INTO input_box_hdr (id_in_box ,customer_name ,in_box_date ,discount ,total ,re_purchases) VALUES (" + ResiptID.getText() + ",'" + CustomName.getText() + "','" + DateTB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'," + 0 + "," + totalmoneytoplabel.getText() + ", 0)";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				Addedheader = true;
				for (int i = 0; i < data.size(); i++) {
					// Create Query

					query = "INSERT INTO input_box (id_in_box  ,id_mat ,sub_discount ,mat_price ,mat_count) VALUES (" + ResiptID.getText() + "," + Matname2ID(data.get(i).Uniname) + "," + data.get(i).Discount + "," + data.get(i).Price + "," + data.get(i).QTY + ")";
					stmt = con.prepareStatement(query);
					stmt.executeUpdate();
				}
				Alert yesalert = new Alert(AlertType.CONFIRMATION);
				yesalert.setTitle("Payment Status");
				yesalert.setHeaderText(null);
				yesalert.setContentText("تم إدخال الفاتوة إلى قاعدة البيانات بنجاح");
				yesalert.showAndWait();
				ClearBuyForm();
			}
		} catch (Exception ex) {
			try {
				if (Addedheader) {
					String query = "delete input_box_hdr where  id_in_box =" + ResiptID.getText();
					stmt = con.prepareStatement(query);
					stmt.executeUpdate();
				}
			} catch (Exception ex1) {
			}
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("خطأ في البيانات المدخلة");
			alert.setContentText("الرجاء التأكد من إدخال جميع بيانات الفاتورة.");
			alert.showAndWait();
		}
	}

	static public int GetCountofMaterial(int id_mat) {
		int count1, count2, count3, count4;
		count1 = count2 = count3 = count4 = 0;
		Connection con = null;
		Statement stmt = null;
		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				// Create Query
				String query = "Select sum(mat_count ) from input_box where id_mat = " + id_mat;
				stmt = con.createStatement();
				ResultSet set = stmt.executeQuery(query);
				if (set.next()) {
					String result = set.getString(1);
					if (result != null)
						count3 = Integer.parseInt(result.trim());
				}
				query = "Select sum(out_strg_count ) from outside_storage where id_mat = " + id_mat;
				stmt = con.createStatement();
				set = stmt.executeQuery(query);
				if (set.next()) {
					String result = set.getString(1);
					if (result != null)
						count4 = Integer.parseInt(result.trim());
				}
				query = "Select sum(mat_count ) from output_box where id_mat = " + id_mat;
				stmt = con.createStatement();
				set = stmt.executeQuery(query);
				if (set.next()) {
					String result = set.getString(1);
					if (result != null)
						count1 = Integer.parseInt(result.trim());
				}
				query = "Select sum(in_strg_count ) from inside_storage where id_mat = " + id_mat;
				stmt = con.createStatement();
				set = stmt.executeQuery(query);
				if (set.next()) {
					String result = set.getString(1);
					if (result != null)
						count2 = Integer.parseInt(result.trim());
				}
				con.close();
			}
		} catch (Exception ex) {
			//Alreat Box For Error
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Connection Error");
			alert.setHeaderText("Connection Info");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
		return (count1 + count2) - (count3 + count4);

	}

	/////////////////////////// purchases
	static boolean Purchacesopen = false;

	@FXML
	TableView<PurchaseRecord> TableOrder2 = new javafx.scene.control.TableView<PurchaseRecord>();
	@FXML
	private TableColumn<PurchaseRecord, String> PBar;
	@FXML
	private TableColumn<PurchaseRecord, String> PUniname;
	@FXML
	private TableColumn<PurchaseRecord, String> PQTY;
	@FXML
	private TableColumn<PurchaseRecord, String> PPrice;
	@FXML
	private TableColumn<PurchaseRecord, String> PSubtotal;
	@FXML
	private TableColumn<PurchaseRecord, String> PDiscount;
	@FXML
	private TableColumn<PurchaseRecord, String> PTotal;
	static ObservableList<PurchaseRecord> Purchasesdata = FXCollections.observableArrayList();

	void updatePur() {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean Addedheader = false;
		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				String query = "UPDATE output_box_hdr SET customer_name = '" + BuyerName.getText() + "',out_box_date = '" + DateTB1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' ,total = '" + totalpurtoplabel.getText() + "' where id_out_box = " + ResiptID1.getText();
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				Addedheader = true;
				query = "delete from output_box where id_out_box = " + ResiptID1.getText();
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				stmt.executeUpdate();
				for (int i = 0; i < Purchasesdata.size(); i++) {
					// Create Query
					query = "INSERT INTO output_box (id_out_box  ,id_mat ,sub_discount ,mat_price ,mat_count) VALUES (" + ResiptID1.getText() + "," + Matname2ID(Purchasesdata.get(i).PUniname) + "," + Purchasesdata.get(i).PDiscount + "," + Purchasesdata.get(i).PPrice + "," + Purchasesdata.get(i).PQTY + ")";
					stmt = con.prepareStatement(query);
					stmt.executeUpdate();
				}
				Alert yesalert = new Alert(AlertType.CONFIRMATION);
				yesalert.setTitle("Payment Status");
				yesalert.setHeaderText(null);
				yesalert.setContentText("تم إدخال الفاتوة إلى قاعدة البيانات بنجاح");
				yesalert.showAndWait();
				ClearBuyForm();
			}
		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("خطأ في البيانات المدخلة");
			alert.setContentText("الرجاء التأكد من إدخال جميع بيانات الفاتورة.");
			alert.showAndWait();
		}
	}

	public void SavePurlist() {
		if (Purchasesdata.size() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("لا يمكن حفظ الفاتورة");
			alert.setContentText("لا توجد مواد ضمن الفاتورة المدخلة.");
			alert.showAndWait();
			return;
		}
		Connection con = null;
		PreparedStatement stmt = null;

		boolean Addedheader = false;
		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				String query = "Select * from output_box_hdr where id_out_box = " + ResiptID1.getText();
				Statement stmt1 = con.createStatement();
				ResultSet set1 = stmt1.executeQuery(query);
				if (set1.next()) {
					updatePur();
					return;
				}
				query = "INSERT INTO output_box_hdr (id_out_box , customer_name ,out_box_date ,discount ,total ,re_sales) VALUES (" + ResiptID1.getText() + ",'" + BuyerName.getText() + "','" + DateTB1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'," + 0 + "," + totalpurtoplabel.getText() + ", 0)";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				Addedheader = true;
				for (int i = 0; i < Purchasesdata.size(); i++) {
					// Create Query
					query = "INSERT INTO output_box (id_out_box  ,id_mat ,sub_discount ,mat_price ,mat_count) VALUES (" + ResiptID1.getText() + "," + Matname2ID(Purchasesdata.get(i).PUniname) + "," + Purchasesdata.get(i).PDiscount + "," + Purchasesdata.get(i).PPrice + "," + Purchasesdata.get(i).PQTY + ")";
					stmt = con.prepareStatement(query);
					stmt.executeUpdate();
				}
				Alert yesalert = new Alert(AlertType.CONFIRMATION);
				yesalert.setTitle("Payment Status");
				yesalert.setHeaderText(null);
				yesalert.setContentText("تم إدخال الفاتوة إلى قاعدة البيانات بنجاح");
				yesalert.showAndWait();
				ClearPurForm();
			}
		} catch (Exception ex) {
			try {
				if (Addedheader) {
					String query = "delete output_box_hdr where  id_out_box =" + ResiptID1.getText();
					stmt = con.prepareStatement(query);
					stmt.executeUpdate();
				}
			} catch (Exception ex1) {
			}
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("خطأ في البيانات المدخلة");
			alert.setContentText("الرجاء التأكد من إدخال جميع بيانات الفاتورة.");
			alert.showAndWait();
		}
	}

	void ClearPurForm() {
		Purchasesdata.clear();
		totalpurtoplabel.setText("0");
		getMaxoutBoxID();
		BuyerName.setText("");
	}

	boolean AddPurchaceRowTable(String Uniname, String QTY, String Price, String Discount, String Bar) {
		if (CheckpurRedundunt(Uniname)) return false;
		String matID = Matname2ID(Uniname);
		if (matID != null) {
			PurchaseRecord record = new PurchaseRecord(Uniname, QTY, Price, Discount, Bar);
			Purchasesdata.add(record);
		}
		return true;
	}
	/////////////////////////// End Purchases
	/////////////////////////// ReSales Code

	@FXML
	TextField CustomName1, ResiptID111, OriginID;
	@FXML
	DatePicker DateTB11;
	@FXML
	Label totalmoneytoplabel1;

	Boolean CheckReSalesRedundunt(String MatName) {

		for (int i = 0; i < ReSalesdata.size(); i++) {
			if (ReSalesdata.get(i).PUniname.matches(MatName)) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("لا يمكن إضافة المادة");
				alert.setContentText("المادة المدخلة موجودة مسبقاً ضمن قائمة المشتريات");
				alert.showAndWait();
				return true;
			}
		}
		return false;
	}

	public boolean AddReSalesRowTable(String Uniname, String QTY, String Price, String Discount, String Bar) {
		if (CheckReSalesRedundunt(Uniname)) return false;
		String matID = Matname2ID(Uniname);
		if (matID != null) {
			PurchaseRecord record = new PurchaseRecord(Uniname, QTY, Price, Discount, Bar);
			ReSalesdata.add(record);
		}
		return false;
	}

	void updateretSales() {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean Addedheader = false;
		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				String query = "UPDATE output_box_hdr SET customer_name = '" + ResiptID111.getText() + "',out_box_date = '" + DateTB11.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' ,total = '" + totalmoneytoplabel1.getText() + "' where id_out_box = " + CustomName1.getText();
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				Addedheader = true;
				query = "delete from output_box where id_out_box = " + CustomName1.getText();
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				Addedheader = true;
				for (int i = 0; i < ReSalesdata.size(); i++) {
					// Create Query
					query = "INSERT INTO output_box (id_out_box  ,id_mat ,sub_discount ,mat_price ,mat_count) VALUES (" + CustomName1.getText() + "," + Matname2ID(ReSalesdata.get(i).PUniname) + "," + ReSalesdata.get(i).PDiscount + "," + ReSalesdata.get(i).PPrice + "," + ReSalesdata.get(i).PQTY + ")";
					stmt = con.prepareStatement(query);
					stmt.executeUpdate();
				}
				Alert yesalert = new Alert(AlertType.CONFIRMATION);
				yesalert.setTitle("Payment Status");
				yesalert.setHeaderText(null);
				yesalert.setContentText("تم إدخال التعديلات إلى قاعدة البيانات بنجاح");
				yesalert.showAndWait();
				ClearReSalesForm();
			}
		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("خطأ في البيانات المدخلة");
			alert.setContentText("الرجاء التأكد من إدخال جميع بيانات الفاتورة.");
			alert.showAndWait();
		}
	}

	public void retSalesSaveButtonClick() {
		if (ReSalesdata.size() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("لا يمكن حفظ الفاتورة");
			alert.setContentText("لا توجد مواد ضمن الفاتورة المدخلة.");
			alert.showAndWait();
			return;
		}
		Connection con = null;
		PreparedStatement stmt = null;
		boolean Addedheader = false;
		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				String query = "Select * from output_box_hdr where id_out_box = " + CustomName1.getText();
				Statement stmt1 = con.createStatement();
				ResultSet set1 = stmt1.executeQuery(query);
				if (set1.next()) {
					updateretSales();
					return;
				}
				query = "INSERT INTO output_box_hdr (id_out_box , customer_name ,out_box_date ,discount ,total ,re_sales) VALUES (" + CustomName1.getText() + ",'" + ResiptID111.getText() + "','" + DateTB11.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'," + 0 + "," + totalmoneytoplabel1.getText() + "," + OriginID.getText() + ")";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				Addedheader = true;
				for (int i = 0; i < ReSalesdata.size(); i++) {
					// Create Query
					query = "INSERT INTO output_box (id_out_box  ,id_mat ,sub_discount ,mat_price ,mat_count) VALUES (" + CustomName1.getText() + "," + Matname2ID(ReSalesdata.get(i).PUniname) + "," + ReSalesdata.get(i).PDiscount + "," + ReSalesdata.get(i).PPrice + "," + ReSalesdata.get(i).PQTY + ")";
					stmt = con.prepareStatement(query);
					stmt.executeUpdate();
				}
				Alert yesalert = new Alert(AlertType.CONFIRMATION);
				yesalert.setTitle("Payment Status");
				yesalert.setHeaderText(null);
				yesalert.setContentText("تم إدخال الفاتوة إلى قاعدة البيانات بنجاح");
				yesalert.showAndWait();
				ClearReSalesForm();
			}
		} catch (Exception ex) {
			try {
				if (Addedheader) {
					String query = "delete output_box_hdr where  id_out_box =" + ResiptID111.getText();
					stmt = con.prepareStatement(query);
					stmt.executeUpdate();
				}
			} catch (Exception ex1) {
			}
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("خطأ في البيانات المدخلة");
			alert.setContentText("الرجاء التأكد من إدخال جميع بيانات الفاتورة.");
			alert.showAndWait();
		}
	}

	void ClearReSalesForm() {
		ReSalesdata.clear();
		totalmoneytoplabel1.setText("0");
		getMaxoutBoxID();
		ResiptID111.setText("");
		OriginID.setText("");
	}

	/////////////////////////// End ReSales
	/////////////////////////// RePurchases
	@FXML
	TextField CustomName11, ResiptID11, CustomName111;
	@FXML
	DatePicker DateTB111;
	@FXML
	Label totalmoneytoplabel11;

	Boolean CheckRePurRedundunt(String MatName) {
		for (int i = 0; i < RePurChases.size(); i++) {
			if (RePurChases.get(i).Uniname.matches(MatName)) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("لا يمكن إضافة المادة");
				alert.setContentText("المادة المدخلة موجودة مسبقاً ضمن قائمة المشتريات");
				alert.showAndWait();
				return true;
			}
		}
		return false;
	}

	public boolean AddRePurRowTable(String Uniname, String QTY, String Price, String Discount, String Bar) {
		if (CheckRePurRedundunt(Uniname)) return false;
		String matID = Matname2ID(Uniname);
		if (matID != null) {
			int stock = GetCountofMaterial(Integer.parseInt(matID));
			if (stock - Integer.parseInt(QTY) < 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("خطأ في الكمية");
				alert.setContentText("الكيمة المتبقية في المستودع للمادة المدخلة هي : " + stock);
				alert.showAndWait();
				QTY = stock + "";
			}
			OrderRecord record = new OrderRecord(Uniname, QTY, Price, Discount, Bar);
			RePurChases.add(record);
		}
		return true;
	}

	void updateretPurch() {
		Connection con = null;
		PreparedStatement stmt = null;
		boolean Addedheader = false;
		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				String query = "UPDATE input_box_hdr SET customer_name = '" + ResiptID11.getText() + "',in_box_date = '" + DateTB111.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' ,total = '" + totalmoneytoplabel11.getText() + "' where id_in_box = " + CustomName11.getText();
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				query = "delete from input_box where id_in_box = " + CustomName11.getText();
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				for (int i = 0; i < RePurChases.size(); i++) {
					// Create Query
					query = "INSERT INTO input_box (id_in_box  ,id_mat ,sub_discount ,mat_price ,mat_count) VALUES (" + CustomName11.getText() + "," + Matname2ID(RePurChases.get(i).Uniname) + "," + RePurChases.get(i).Discount + "," + RePurChases.get(i).Price + "," + RePurChases.get(i).QTY + ")";
					stmt = con.prepareStatement(query);
					stmt.executeUpdate();
				}
				Alert yesalert = new Alert(AlertType.CONFIRMATION);
				yesalert.setTitle("Payment Status");
				yesalert.setHeaderText(null);
				yesalert.setContentText("تم إدخال التعديلات إلى قاعدة البيانات بنجاح");
				yesalert.showAndWait();
				CleaRePurForm();
			}
		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("خطأ في البيانات المدخلة");
			alert.setContentText("الرجاء التأكد من إدخال جميع بيانات الفاتورة.");
			alert.showAndWait();
		}
	}

	public void retPurchSaveButtonClick() {
		if (RePurChases.size() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("لا يمكن حفظ الفاتورة");
			alert.setContentText("لا توجد مواد ضمن الفاتورة المدخلة.");
			alert.showAndWait();
			return;
		}
		Connection con = null;
		PreparedStatement stmt = null;
		boolean Addedheader = false;
		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				String query = "Select * from input_box_hdr where id_in_box = " + CustomName11.getText();
				Statement stmt1 = con.createStatement();
				ResultSet set1 = stmt1.executeQuery(query);
				if (set1.next()) {
					updateretPurch();
					return;
				}
				query = " INTO input_box_hdr (id_in_box ,customer_name ,in_box_date ,discount ,total ,re_purchases) VALUES (" + CustomName11.getText() + ",'" + ResiptID11.getText() + "','" + DateTB111.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'," + 0 + "," + totalmoneytoplabel11.getText() + "," + CustomName111.getText() + ")";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				Addedheader = true;
				for (int i = 0; i < RePurChases.size(); i++) {
					// Create Query
					query = "INSERT INTO input_box (id_in_box  ,id_mat ,sub_discount ,mat_price ,mat_count) VALUES (" + CustomName11.getText() + "," + Matname2ID(RePurChases.get(i).Uniname) + "," + RePurChases.get(i).Discount + "," + RePurChases.get(i).Price + "," + RePurChases.get(i).QTY + ")";
					stmt = con.prepareStatement(query);
					stmt.executeUpdate();
				}
				Alert yesalert = new Alert(AlertType.CONFIRMATION);
				yesalert.setTitle("Payment Status");
				yesalert.setHeaderText(null);
				yesalert.setContentText("تم إدخال الفاتوة إلى قاعدة البيانات بنجاح");
				yesalert.showAndWait();
				CleaRePurForm();
			}
		} catch (Exception ex) {
			try {
				if (Addedheader) {
					String query = "delete input_box_hdr where  id_in_box =" + ResiptID11.getText();
					stmt = con.prepareStatement(query);
					stmt.executeUpdate();
				}
			} catch (Exception ex1) {
			}
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("خطأ في البيانات المدخلة");
			alert.setContentText("الرجاء التأكد من إدخال جميع بيانات الفاتورة.");
			alert.showAndWait();
		}
	}

	void CleaRePurForm() {

		totalmoneytoplabel11.setText("0");
		getMaxinBoxID();
		CustomName111.setText("");
		ResiptID11.setText("");
		RePurChases.clear();
	}

	/////////////////////////// End RePurchases
	public void save() {
		if (addMaterialBorder.isVisible()) {
			Material.saveMaterial(material_table, mat_name, mat_unit, mat_barcode, mat_notes, data1, data_tmp);
		} else if (inmatBorder.isVisible()) {
			String id_value = InOutStorage.getid("inside_storage", "in_strg_id");
			Material.save_inside_storage(in_table, id_value, mat_name_in, mat_date_in, mat_quantity_in, in_Storage, in_Storage_tmp);
		} else if (outMatBorder.isVisible()) {
			String id_value = InOutStorage.getid("outside_storage", "out_strg_id");
			Material.save_outside_storage(out_table, id_value, mat_name_out, mat_date_out, mat_quantity_out, out_storage, out_storage_tmp);
		}
	}

	public void delete() {
		if (add_mat_page.isVisible()) {
			try {
				deleteMaterial();
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("No Item Selected");
				alert.setContentText("PLease select an item to delete it");
				alert.showAndWait();
			}
		}
	}

	public void deleteMaterial() {
		ObservableList<Material> DeleteMaterial = material_table.getSelectionModel().getSelectedItems();
		ObservableList<Material> AllMaterials = material_table.getItems();
		Material m = DeleteMaterial.get(0);
		DeleteMaterial.forEach(AllMaterials::remove);
		m.deletMaterial(m.getMaterial_id());
	}

	public void show() {
		if (add_mat_page.isVisible()) {
			showData();
		}
	}


	// --------------------------show materials------------------
	String id;

	public void showData() {
		material_table.getItems().clear();
		Connection con;
		Statement stmt;
		ResultSet rs;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&useSSL=false", "hinet", "12345678");
			stmt = con.createStatement();
			String query = "select * from material ";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				id = rs.getString("id_mat");
				data1.add(new Material(
						rs.getString("id_mat"),
						rs.getString("mat_name"),
						rs.getString("unit"),
						rs.getString("notes"),
						rs.getString("barcode")
				));
				material_table.setItems(data_tmp);
				material_table.setItems(data1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}


	// ------------------ Exit Button Method ------------
	public void exitButtonClick() {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);  //new alert object
			alert.setTitle("تأكيد");  //warning box title
			alert.setHeaderText("هل تريد الخروج ؟");// Header
			alert.setContentText("هل فعلا تريد الخروج من البرنامج ؟"); //Discription of warning
			alert.getDialogPane().setPrefSize(400, 200); //sets size of alert box
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.YES) {
				//yesbutton Clicked
				Platform.exit();
			} else {

			}
		} catch (Exception ex) {
			Notifications notify = Notifications.create()
					.title("عذرا..خطا في البرنامج")
					.text("يرجى التوصل مع شركة هاي نت لحل المشكلة")
					.graphic(null)
					.hideAfter(Duration.seconds(5))
					.position(Pos.TOP_RIGHT);
			notify.show();
		}
	}

	//--------------------------- search button-----------------
	public void search() {


		if (TrafficSearchBorderPane.isVisible()) {
			if (reciptTypeButton.getId().equals("reciptTypeButton")) {
				Material.alert("Error", "check the information", "please select type of search...");
			}
			ObservableList<TrafficSearch> trafficSearches = FXCollections.observableArrayList();
			try {
				if ((!customer_tf.getText().isEmpty() || !reciptid_tf.getText().isEmpty())
						&& (todate.getValue() == null && fromdate.getValue() == null)) {
					String textField;
					String where = null;
					if (customer_tf.getText().isEmpty()) {
						textField = reciptid_tf.getText();
						String split[] = textField.split("-");
						textField = Arrays.toString(split).substring(1, Arrays.toString(split).length() - 1);
						if (reciptTypeButton.getText().equals("sales")) {
//                              where = "id_in_box =" + Integer.parseInt(textField);
							where = "id_in_box in (" + textField + ")";
						} else if (reciptTypeButton.getText().equals("purchasess")) {
							where = "id_out_box  in (" + textField + ")";
						}
					} else {
						textField = customer_tf.getText();
						where = " customer_name like '%" + textField + "%'";
					}
					Connection con;
					Statement stmt;
					ResultSet rs;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&useSSL=false", "hinet", "12345678");
						stmt = con.createStatement();
						String query = null;
						//----------------------------sales-----------------
						if (reciptTypeButton.getText().equals("sales")) {
							query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
									"input_box_hdr.id_in_box from  input_box_hdr where " + where + " &&  re_purchases = 0";
//                                          " && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_in_box");
								date = rs.getString("in_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//------------------------------ purchasess----------------
						} else if (reciptTypeButton.getText().equals("purchasess")) {
							query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
									"output_box_hdr.id_out_box from  output_box_hdr where " + where + " && re_sales =0 ";
//                                          "  out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_out_box");
								date = rs.getString("out_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}

							//------------------------------ re purchasess---------------
						} else if (reciptTypeButton.getText().equals("purchasess_return")) {
							query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
									"input_box_hdr.id_in_box from  input_box_hdr where " + where + " && re_purchases !=0 ";
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_in_box");
								date = rs.getString("in_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));

							}
							//------------------------re sales------------------------
						} else if (reciptTypeButton.getText().equals("sales_return")) {
							query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
									"output_box_hdr.id_out_box from  output_box_hdr where " + where + " && re_sales !=0";
//                                          " && out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;

							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_out_box");
								date = rs.getString("out_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//--------------------------- expensess---------------

						} else if (reciptTypeButton.getText().equals("expenses")) {
							if (customer_tf.getText().equals("*")) {
//                                  System.out.println("ok");
								query = "select * from spending";
							} else if (!customer_tf.getText().isEmpty() && reciptid_tf.getText().isEmpty()) {
								query = "select * from spending where spending.name  like '%" + customer_tf.getText() + "%'";
							} else if (customer_tf.getText().isEmpty() && !reciptid_tf.getText().isEmpty()) {
								query = "select * from spending where spending.id_sp in (" + textField + ")";
							}
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;
							while (rs.next()) {
								name = rs.getString("name");
								total = rs.getDouble("amount");
								id = rs.getInt("id_sp");
								date = rs.getString("sp_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//------------------------incomes-------------------
						} else if (reciptTypeButton.getText().equals("incomes")) {
							if (!customer_tf.getText().isEmpty() && reciptid_tf.getText().isEmpty()) {
								query = "select * from incomes where incomes.name ='" + customer_tf.getText() + "'";
							} else if (customer_tf.getText().isEmpty() && !reciptid_tf.getText().isEmpty()) {
								query = "select * from incomes where incomes.id_in =  " + reciptid_tf.getText() + "";
							}
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;
							while (rs.next()) {
								name = rs.getString("name");
								total = rs.getDouble("amount");
								id = rs.getInt("id_in");
								date = rs.getString("incomes_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//----------------------all--------------------
						} else if (reciptTypeButton.getText().equals("all")) {

							//--------------------sales------------------------

							if (!reciptid_tf.getText().isEmpty() && customer_tf.getText().isEmpty()) {
								where = "id_in_box in (" + textField + ")";
								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where " + where + " &&  re_purchases =0";
//                                      " && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {

								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where " + where + " &&  re_purchases =0";
							}
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_in_box");
								date = rs.getString("in_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "sales"));

							}
							//----------------------------purchasess--------------------
							if (!reciptid_tf.getText().isEmpty() && customer_tf.getText().isEmpty()) {
								where = "id_out_box in (" + textField + ")";
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where " + where + " && re_sales =0 ";
//                                      "  out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where " + where + " && re_sales =0 ";
							}
							rs = stmt.executeQuery(query);
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_out_box");
								date = rs.getString("out_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "purchasess"));
							}
							//---------------------------re purchasess---------------------------
							if (!reciptid_tf.getText().isEmpty() && customer_tf.getText().isEmpty()) {
								where = "id_in_box in (" + textField + ")";
								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where " + where + " && re_purchases !=0 ";
//                                      " && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {
								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where " + where + " && re_purchases !=0 ";
							}
							rs = stmt.executeQuery(query);
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_in_box");
								date = rs.getString("in_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "purchasess_return"));
							}
							//--------------------------------- re sales--------------------
							if (!reciptid_tf.getText().isEmpty() && customer_tf.getText().isEmpty()) {
								where = "id_out_box in (" + textField + ")";
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where " + where + " && re_sales !=0";
//                                      " && out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where " + where + " && re_sales !=0";
							}
							rs = stmt.executeQuery(query);
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_out_box");
								date = rs.getString("out_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "sales_return"));
							}
							//------------------------- expensess -------------------
							if (!customer_tf.getText().isEmpty() && reciptid_tf.getText().isEmpty()) {
								query = "select * from spending where spending.name like '%" + customer_tf.getText() + "%'";


							} else if (customer_tf.getText().isEmpty() && !reciptid_tf.getText().isEmpty()) {
								query = "select * from spending where spending.id_sp in (" + textField + ")";

							} else {
								query = "select * from spending where spending.name like '%" + customer_tf.getText() + "%'";
							}
							rs = stmt.executeQuery(query);
//                          String name,date;
//                          double total;

							while (rs.next()) {
								name = rs.getString("name");
								total = rs.getDouble("amount");
								id = rs.getInt("id_sp");
								date = rs.getString("sp_date");

								trafficSearches.add(new TrafficSearch(name, id, total, date, "expenses"));
							}
							//------------------------- incomes------------------
							if (!customer_tf.getText().isEmpty() && reciptid_tf.getText().isEmpty()) {
								query = "select * from incomes where incomes.name like'%" + customer_tf.getText() + "%'";
							} else if (customer_tf.getText().isEmpty() && !reciptid_tf.getText().isEmpty()) {
								query = "select * from incomes where incomes.id_in in (" + textField + ")";
							} else {
								query = "select * from incomes where incomes.name like'%" + customer_tf.getText() + "%'";
							}
							rs = stmt.executeQuery(query);
							while (rs.next()) {
								name = rs.getString("name");
								total = rs.getDouble("amount");
								id = rs.getInt("id_in");
								date = rs.getString("incomes_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "incomes"));
							}
						}
						searchtraffictable.setItems(trafficSearches);

					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (NullPointerException e) {
						Material.alert("Error", "Error", "Please Select Once of MenuButton");
					}
				} else if ((!customer_tf.getText().isEmpty() || !reciptid_tf.getText().isEmpty()) &&
						(!fromdate.getValue().toString().isEmpty() && !todate.getValue().toString().isEmpty())) {
					String textField;
					String where = null;
					if (customer_tf.getText().isEmpty()) {
						textField = reciptid_tf.getText();
						String split[] = textField.split("-");
						textField = Arrays.toString(split).substring(1, Arrays.toString(split).length() - 1);
						if (reciptTypeButton.getText().equals("sales")) {
							where = "id_in_box in (" + textField + ")";
						} else if (reciptTypeButton.getText().equals("purchasess")) {
							where = "id_out_box in (" + textField + ")";
						}
					} else {
						textField = customer_tf.getText();
						where = " customer_name like '%" + textField + "%'";
					}
					Connection con;
					Statement stmt;
					ResultSet rs;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&useSSL=false", "hinet", "12345678");
						stmt = con.createStatement();
						String query = null;
						//----------------------------sales-----------------
						if (reciptTypeButton.getText().equals("sales")) {
							query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
									"input_box_hdr.id_in_box from  input_box_hdr where " + where + " &&  re_purchases =0" +
									" && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_in_box");
								date = rs.getString("in_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//------------------------------ purchasess----------------
						} else if (reciptTypeButton.getText().equals("purchasess")) {
							query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
									"output_box_hdr.id_out_box from  output_box_hdr where " + where + " && re_sales =0 &&" +
									"  out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_out_box");
								date = rs.getString("out_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//------------------------------ re purchasess---------------
						} else if (reciptTypeButton.getText().equals("purchasess_return")) {
							query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
									"input_box_hdr.id_in_box from  input_box_hdr where " + where + " && re_purchases !=0 " +
									" && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_in_box");
								date = rs.getString("in_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//------------------------re sales------------------------
						} else if (reciptTypeButton.getText().equals("sales_return")) {
							query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
									"output_box_hdr.id_out_box from  output_box_hdr where " + where + " && re_sales !=0" +
									" && out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_out_box");
								date = rs.getString("out_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//--------------------------- expensess---------------

						} else if (reciptTypeButton.getText().equals("expenses")) {
							if (customer_tf.getText().equals("*")) {
								query = "select * from spending";
							} else if (!customer_tf.getText().isEmpty() && reciptid_tf.getText().isEmpty()) {
								query = "select * from spending where spending.name  like '%" + customer_tf.getText() + "%'";


							} else if (customer_tf.getText().isEmpty() && !reciptid_tf.getText().isEmpty()) {
								query = "select * from spending where spending.id_sp  in(" + textField + ")";
							}
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;
							while (rs.next()) {
								name = rs.getString("name");
								total = rs.getDouble("amount");
								id = rs.getInt("id_sp");
								date = rs.getString("sp_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//------------------------incomes-------------------
						} else if (reciptTypeButton.getText().equals("incomes")) {

							if (!customer_tf.getText().isEmpty() && reciptid_tf.getText().isEmpty()) {
								query = "select * from incomes where incomes.name ='" + customer_tf.getText() + "'";
							} else if (customer_tf.getText().isEmpty() && !reciptid_tf.getText().isEmpty()) {
								query = "select * from incomes where incomes.id_in in(" + textField + ")";
							}
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;
							while (rs.next()) {
								name = rs.getString("name");
								total = rs.getDouble("amount");
								id = rs.getInt("id_in");
								date = rs.getString("incomes_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//----------------------all--------------------
						} else if (reciptTypeButton.getText().equals("all")) {
							//--------------------sales------------------------
							if (customer_tf.getText().isEmpty() && !reciptid_tf.getText().isEmpty()) {
								where = "id_in_box in (" + textField + ")";
								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where " + where + " &&  re_purchases =0" +
										" && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {
								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where " + where + " &&  re_purchases =0" +
										" && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							}
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;

							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_in_box");
								date = rs.getString("in_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "sales"));

							}
							//----------------------------purchasess--------------------
							if (customer_tf.getText().isEmpty() && !reciptid_tf.getText().isEmpty()) {
								where = "id_out_box in (" + textField + ")";
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where " + where + " && re_sales =0 &&" +
										"  out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where " + where + " && re_sales =0 &&" +
										"  out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							}
							rs = stmt.executeQuery(query);
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_out_box");
								date = rs.getString("out_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "purchasess"));
							}
							//---------------------------re purchasess---------------------------
							if (customer_tf.getText().isEmpty() && !reciptid_tf.getText().isEmpty()) {
								where = "id_in_box in (" + textField + ")";
								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where " + where + " && re_purchases !=0 " +
										" && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {
								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where " + where + " && re_purchases !=0 " +
										" && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							}
							rs = stmt.executeQuery(query);
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_in_box");
								date = rs.getString("in_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "purchasess_return"));
							}
							//--------------------------------- re sales--------------------
							if (customer_tf.getText().isEmpty() && !reciptid_tf.getText().isEmpty()) {
								where = "id_out_box in (" + textField + ")";
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where " + where + " && re_sales !=0" +
										" && out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where " + where + " && re_sales !=0" +
										" && out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							}
							rs = stmt.executeQuery(query);
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_out_box");
								date = rs.getString("out_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "sales_return"));
							}
							//------------------------- expensess -------------------
							if (!customer_tf.getText().isEmpty() && reciptid_tf.getText().isEmpty()) {
								query = "select * from spending where spending.name like '%" + customer_tf.getText() + "%'";


							} else if (customer_tf.getText().isEmpty() && !reciptid_tf.getText().isEmpty()) {
								query = "select * from spending where spending.id_sp  in (" + textField + ")";

							} else {
								query = "select * from spending where spending.name like '%" + customer_tf.getText() + "%'";
							}
							rs = stmt.executeQuery(query);
							while (rs.next()) {
								name = rs.getString("name");
								total = rs.getDouble("amount");
								id = rs.getInt("id_sp");
								date = rs.getString("sp_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "expenses"));
							}
							//------------------------- incomes------------------
							if (!customer_tf.getText().isEmpty() && reciptid_tf.getText().isEmpty()) {
								query = "select * from incomes where incomes.name like'%" + customer_tf.getText() + "%'";
							} else if (customer_tf.getText().isEmpty() && !reciptid_tf.getText().isEmpty()) {
								query = "select * from incomes where incomes.id_in in(" + textField + ")";
							} else {
								query = "select * from incomes where incomes.name like'%" + customer_tf.getText() + "%'";
							}
							rs = stmt.executeQuery(query);
							while (rs.next()) {
								name = rs.getString("name");
								total = rs.getDouble("amount");
								id = rs.getInt("id_in");
								date = rs.getString("incomes_date");

								trafficSearches.add(new TrafficSearch(name, id, total, date, "incomes"));
							}
						}
						searchtraffictable.setItems(trafficSearches);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
//                      Material.alert("Error","Error","Please Select Once of MenuButton");
						e.printStackTrace();
					} catch (NullPointerException e) {
						Material.alert("Error", "Error", "Please Select Once of MenuButton");
					}
				}
				//--------------------------- empty texts---------------

				else if ((customer_tf.getText().isEmpty() && reciptid_tf.getText().isEmpty())) {
//                    (!fromdate.getValue().toString().isEmpty()&& !todate.getValue().toString().isEmpty() ) ) {
					String textField;
					String where = null;
					if (customer_tf.getText().isEmpty()) {
						textField = reciptid_tf.getText();
						String split[] = textField.split("-");
						textField = Arrays.toString(split).substring(1, Arrays.toString(split).length() - 1);
						if (reciptTypeButton.getText().equals("sales")) {
							where = "id_in_box in (" + textField + ")";
						} else if (reciptTypeButton.getText().equals("purchasess")) {
							where = "id_out_box in (" + textField + ")";
						}
					} else {
						textField = customer_tf.getText();
						where = " customer_name like '%" + textField + "%'";
					}
					Connection con;
					Statement stmt;
					ResultSet rs;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&useSSL=false", "hinet", "12345678");
						stmt = con.createStatement();
						String query = null;
						//----------------------------sales-----------------
						if (reciptTypeButton.getText().equals("sales")) {
							if (fromdate.getValue() == null && todate.getValue() == null) {
								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where   re_purchases =0";
//                                " && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {
								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where   re_purchases =0" +
										" && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";

							}
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_in_box");
								date = rs.getString("in_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//------------------------------ purchasess----------------
						} else if (reciptTypeButton.getText().equals("purchasess")) {
							if (fromdate.getValue() == null && todate.getValue() == null) {
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where  re_sales =0 ";
//                                "  out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where   re_sales =0 &&" +
										"  out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							}
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;

							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_out_box");
								date = rs.getString("out_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//------------------------------ re purchasess---------------
						} else if (reciptTypeButton.getText().equals("purchasess_return")) {
							if (fromdate.getValue() == null && todate.getValue() == null) {

								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where   re_purchases !=0 ";
//                                    " && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {
								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where  re_purchases !=0 " +
										" && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";

							}
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;

							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_in_box");
								date = rs.getString("in_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));

							}
							//------------------------re sales------------------------
						} else if (reciptTypeButton.getText().equals("sales_return")) {
							if (fromdate.getValue() == null && todate.getValue() == null) {
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where  re_sales !=0";
//                                    " && out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";

							} else {
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where  re_sales !=0" +
										" && out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";

							}
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;

							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_out_box");
								date = rs.getString("out_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//--------------------------- expensess---------------

						} else if (reciptTypeButton.getText().equals("expenses")) {

							if (fromdate.getValue() == null && todate.getValue() == null) {

								query = "select * from spending ";
							} else {
								query = "select * from spending " +
										"where " +
										"  sp_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
								;
							}
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;

							while (rs.next()) {
								name = rs.getString("name");
								total = rs.getDouble("amount");
								id = rs.getInt("id_sp");
								date = rs.getString("sp_date");

								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//------------------------incomes-------------------
						} else if (reciptTypeButton.getText().equals("incomes")) {
							if (fromdate.getValue() == null && todate.getValue() == null) {
								query = "select * from incomes";
							} else {
								query = "select * from incomes " +
										"where " +
										"  incomes_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";

							}

							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;

							while (rs.next()) {
								name = rs.getString("name");
								total = rs.getDouble("amount");
								id = rs.getInt("id_in");
								date = rs.getString("incomes_date");

								trafficSearches.add(new TrafficSearch(name, id, total, date, reciptTypeButton.getText()));
							}
							//----------------------all--------------------
						} else if (reciptTypeButton.getText().equals("all")) {
							//--------------------sales------------------------
							if (fromdate.getValue() == null && todate.getValue() == null) {
								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where   re_purchases =0";
//                                " && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {
								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where   re_purchases =0" +
										" && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";

							}
							rs = stmt.executeQuery(query);
							String name, date;
							double total;
							int id;

							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_in_box");
								date = rs.getString("in_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "sales"));

							}
							//----------------------------purchasess--------------------
							if (fromdate.getValue() == null && todate.getValue() == null) {
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where  re_sales =0 ";
//                                "  out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where   re_sales =0 &&" +
										"  out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";

							}
							rs = stmt.executeQuery(query);


							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_out_box");
								date = rs.getString("out_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "purchasess"));

							}
							//---------------------------re purchasess---------------------------
							if (fromdate.getValue() == null && todate.getValue() == null) {

								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where   re_purchases !=0 ";
//                                    " && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							} else {
								query = "select input_box_hdr.customer_name , input_box_hdr.total , input_box_hdr.in_box_date , " +
										"input_box_hdr.id_in_box from  input_box_hdr where  re_purchases !=0 " +
										" && in_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";

							}
							rs = stmt.executeQuery(query);
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_in_box");
								date = rs.getString("in_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "purchasess_return"));
							}
							//--------------------------------- re sales--------------------
							if (fromdate.getValue() == null && todate.getValue() == null) {
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where  re_sales !=0";
							} else {
								query = "select output_box_hdr.customer_name , output_box_hdr.total , output_box_hdr.out_box_date , " +
										"output_box_hdr.id_out_box from  output_box_hdr where  re_sales !=0" +
										" && out_box_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
							}
							rs = stmt.executeQuery(query);
							while (rs.next()) {
								name = rs.getString("customer_name");
								total = rs.getDouble("total");
								id = rs.getInt("id_out_box");
								date = rs.getString("out_box_date");
								trafficSearches.add(new TrafficSearch(name, id, total, date, "sales_return"));
							}
							//------------------------- expensess -------------------
							if (fromdate.getValue() == null && todate.getValue() == null) {

								query = "select * from spending ";
							} else {
								query = "select * from spending " +
										"where " +
										"  sp_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";
								;
							}
							rs = stmt.executeQuery(query);
							while (rs.next()) {
								name = rs.getString("name");
								total = rs.getDouble("amount");
								id = rs.getInt("id_sp");
								date = rs.getString("sp_date");

								trafficSearches.add(new TrafficSearch(name, id, total, date, "expenses"));
							}
							//------------------------- incomes------------------
							if (fromdate.getValue() == null && todate.getValue() == null) {
								query = "select * from incomes";
							} else {
								query = "select * from incomes " +
										" where incomes_date BETWEEN '" + fromdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "' AND '" + todate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'";

							}
							rs = stmt.executeQuery(query);
							while (rs.next()) {
								name = rs.getString("name");
								total = rs.getDouble("amount");
								id = rs.getInt("id_in");
								date = rs.getString("incomes_date");

								trafficSearches.add(new TrafficSearch(name, id, total, date, "incomes"));
							}
						}
						searchtraffictable.setItems(trafficSearches);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
//                      Material.alert("Error","Error","Please Select Once of MenuButton");
						e.printStackTrace();
					} catch (NullPointerException e) {
						Material.alert("Error", "Error", "Please Select Once of MenuButton");
					}
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
//                  Material.alert("Error","Error","Please fill the dates");
			} catch (NumberFormatException e) {
				Material.alert("Error", "Error", "Please fill the Number");
			}
		} else if (showMaterialBorder.isVisible()) {
			Material.alert("Error", "Error", "Please fill the Number");
		}
	}

	@FXML
	DatePicker st_datefrom = new DatePicker();
	@FXML
	DatePicker st_dateto = new DatePicker();

	public void showStatistices() {
		System.out.println(Statistices.getsales(st_datefrom.getValue(), st_dateto.getValue()));
		System.out.println(Statistices.getpurchasess(st_datefrom.getValue(), st_dateto.getValue()));
	}

	//    -----------------------test-------------
	@FXML
	JFXButton settingsBackupButton = new JFXButton();
	@FXML
	JFXButton settingsRestoreButton = new JFXButton();

	public void choose_file() {
		//        ----------------------choose file ----------------------------
		settingsBackupButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Backup.Backupdbtosql();
			}
		});

		settingsRestoreButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String path = null;
				try {
					FileChooser fileChooser = new FileChooser();
					File file;
					//Set extension filter
					FileChooser.ExtensionFilter extFilter = new
							FileChooser.ExtensionFilter("ALL files (*.*)", "*.*");
					fileChooser.getExtensionFilters().add(extFilter);
					//Show open file dialog
					file = fileChooser.showOpenDialog(null);
					String pathsInfo = "";
					try {
						pathsInfo += file.getPath() + "\n";
					} catch (NullPointerException e) {
					}
					path = pathsInfo;
					Backup.importdbtosql(path);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//-------------------------------search inventory------------------------
	@FXML
//      inventorysearch
			JFXTextField matnamesearch = new JFXTextField();
	@FXML
	JFXTextField barcodesearch = new JFXTextField();
	@FXML
	TableView searchmaterialtableview = new TableView();
	@FXML
	TableColumn sh_id = new TableColumn();
	@FXML
	TableColumn sh_name = new TableColumn();
	@FXML
	TableColumn sh_quantity = new TableColumn();

	public void materialinventorysearch() {
		searchmaterialtableview.getItems().clear();
		Connection con;
		Statement stmt;
		ResultSet rs;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&useSSL=false", "hinet", "12345678");
			stmt = con.createStatement();
			if (!matnamesearch.getText().isEmpty()) {
				String query = "Select * from material where mat_name = '" + matnamesearch.getText() + "'";
				rs = stmt.executeQuery(query);
				String idmat = null, matname = null;
				if (rs.next()) {
					if (rs.getRow() == 1) {
						idmat = rs.getString(1);
						matname = rs.getString(2);
					} else {
						Material.alert("Error", "No Material found", "Please check the material name ");
					}
				}
				in_Storage.add(new InOutStorage(idmat, matname, null, GetCountofMaterial(Integer.parseInt(idmat)) + ""));
				searchmaterialtableview.setItems(in_Storage);
			} else if (matnamesearch.getText().isEmpty()) {
				String query = "Select * from material ";
				rs = stmt.executeQuery(query);
				String idmat = null, matname = null;
				while (rs.next()) {
					idmat = rs.getString(1);
					matname = rs.getString(2);
					in_Storage.add(new InOutStorage(idmat, matname, null, GetCountofMaterial(Integer.parseInt(idmat)) + ""));
				}
				searchmaterialtableview.setItems(in_Storage);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// ------------ ----------------------------------------------------------------Printing Inovice  ----------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------- Print Sales Invoice --------------------------------------------------------------------------------

	public void salesPrint() throws IOException, Exception {
		String label = "فاتورة مبيعات";
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);  //new alert object
			alert.setTitle("Confirmation");  //warning box title
			alert.setHeaderText("عملية تاكيد المبيع");// Header
			alert.setContentText("هل تريد إتمام عملية المبيع ؟"); //Discription of warning
			alert.getDialogPane().setPrefSize(400, 200); //sets size of alert box
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.YES) {
				//yesbutton Clicked

				new OrderPrinting(1, label, DateTB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), CustomName.getText(), ResiptID.getText(), "", totalmoneytoplabel.getText());
				if (data.size() == 0) {
					Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setTitle("Error");
					alert2.setHeaderText("لا يمكن حفظ الفاتورة");
					alert2.setContentText("لا توجد مواد ضمن الفاتورة المدخلة.");
					alert2.showAndWait();
					return;
				}
				Connection con = null;
				PreparedStatement stmt = null;
				boolean Addedheader = false;
				try {
					if (con == null) {
						con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
						// Create Query
						String query = "Select * from input_box_hdr where id_in_box = " + ResiptID.getText();
						Statement stmt1 = con.createStatement();

						ResultSet set1 = stmt1.executeQuery(query);
						if (set1.next()) {
							updateSales();
							return;
						}
						query = "INSERT INTO input_box_hdr (id_in_box ,customer_name ,in_box_date ,discount ,total ,re_purchases) VALUES (" + ResiptID.getText() + ",'" + CustomName.getText() + "','" + DateTB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "'," + 0 + "," + totalmoneytoplabel.getText() + ", 0)";
						stmt = con.prepareStatement(query);
						stmt.executeUpdate();
						Addedheader = true;
						for (int i = 0; i < data.size(); i++) {
							// Create Query
							query = "INSERT INTO input_box (id_in_box  ,id_mat ,sub_discount ,mat_price ,mat_count) VALUES (" + ResiptID.getText() + "," + Matname2ID(data.get(i).Uniname) + "," + data.get(i).Discount + "," + data.get(i).Price + "," + data.get(i).QTY + ")";
							stmt = con.prepareStatement(query);
							stmt.executeUpdate();
						}
					}
				} catch (Exception ex) {
					try {
						if (Addedheader) {
							String query = "delete input_box_hdr where  id_in_box =" + ResiptID.getText();
							stmt = con.prepareStatement(query);
							stmt.executeUpdate();
						}
					} catch (Exception ex1) {
					}

					Alert alert1 = new Alert(AlertType.ERROR);
					alert1.setTitle("Error");
					alert1.setHeaderText("خطأ في البيانات المدخلة");
					alert1.setContentText("الرجاء التأكد من إدخال جميع بيانات الفاتورة.");
					alert1.showAndWait();
				}
			} else {
				Notifications notify = Notifications.create()
						.title("عذرا..خطا في الطباعة")
						.text("عذرا لم تتم الطباعة")
						.graphic(null)
						.hideAfter(Duration.seconds(5))
						.position(Pos.TOP_RIGHT);
				notify.show();
			}
		} catch (Exception ex) {
			Notifications notify = Notifications.create()
					.title("عذرا..خطا في الطباعة")
					.text("يرجى التاكد من مليء الحقول المطلوبة قبل الطباعة")
					.graphic(null)
					.hideAfter(Duration.seconds(5))
					.position(Pos.TOP_RIGHT);
			notify.show();

		}
	}

	// ---------------------------------------------------------------------------- Print Returned Purshases Invoice --------------------------------------------------------------------------------

	public void purRetPrint() {
		String label = "فاتورة مرتجع مشتريات";
		if (ResiptID11 == null) {
			Notifications notify = Notifications.create()
					.title("عذرا..خطا في الطباعة")
					.text("يرجى التاكد من مليء الحقول المطلوبة قبل الطباعة")
					.graphic(null)
					.hideAfter(Duration.seconds(5))
					.position(Pos.TOP_RIGHT);
			notify.show();


		} else {

			try {
				Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);  //new alert object
				alert.setTitle("Confirmation");  //warning box title
				alert.setHeaderText("Payment Confirmation!");// Header
				alert.setContentText("Are You Sure You Want to Confrim the Payment?"); //Discription of warning
				alert.getDialogPane().setPrefSize(400, 200); //sets size of alert box
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.YES) {
					//yesbutton Clicked

					//new OrderPrinting("","","","","","")
					//
					new OrderPrinting(2, label, DateTB111.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), ResiptID11.getText(), CustomName111.getText(), CustomName11.getText(), totalmoneytoplabel1.getText());
					//payButtonClick();
				} else {
					Notifications notify = Notifications.create()
							.title("عذرا..خطا في الطباعة")
							.text("يرجى التاكد من مليء الحقول المطلوبة قبل الطباعة")
							.graphic(null)
							.hideAfter(Duration.seconds(5))
							.position(Pos.TOP_RIGHT);
					notify.show();

				}
			} catch (Exception ex) {
				Notifications notify = Notifications.create()
						.title("عذرا..خطا في الطباعة")
						.text("يرجى التاكد من مليء الحقول المطلوبة قبل الطباعة")
						.graphic(null)
						.hideAfter(Duration.seconds(5))
						.position(Pos.TOP_RIGHT);
				notify.show();

			}
		}
	}

	//  -------------- ---------------------------------------------------------------Printing Purchases Invoice --------------------------------------------------------------------------
	public void purchasesPrint() {
		try {
			String label = "فاتورة المشتريات";
			Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);  //new alert object
			alert.setTitle("Confirmation");  //warning box title
			alert.setHeaderText("Payment Confirmation!");// Header
			alert.setContentText("Are You Sure You Want to Confrim the Payment?"); //Discription of warning
			alert.getDialogPane().setPrefSize(400, 200); //sets size of alert box
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.YES) {
				//new PurchasesPrinting("","","","","","")
				new PurchasesPrinting(1, label, DateTB1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), BuyerName.getText(), ResiptID1.getText(), "", totalpurtoplabel.getText());
				payexpClick();
			} else {
				Notifications notify = Notifications.create()
						.title("عذرا..خطا في الطباعة")
						.text("يرجى التاكد من مليء الحقول المطلوبة قبل الطباعة")
						.graphic(null)
						.hideAfter(Duration.seconds(5))
						.position(Pos.TOP_RIGHT);
				notify.show();

			}
		} catch (Exception ex) {
			Notifications notify = Notifications.create()
					.title("عذرا..خطا في الطباعة")
					.text("يرجى التاكد من مليء الحقول المطلوبة قبل الطباعة")
					.graphic(null)
					.hideAfter(Duration.seconds(5))
					.position(Pos.TOP_RIGHT);
			notify.show();
		}
	}

	//  -------------- --------------------------------------------------------------------Print Returned Sales Invoice ---------------------------------------------------------------------
	public void retSalesPrint() {
		try {
			String label = "فاتورة مرتجع المبيعات";
			//new PurchasesPrinting("","","","","","","")
			new PurchasesPrinting(2, label, DateTB11.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), ResiptID111.getText(), CustomName1.getText(), OriginID.getText(), totalmoneytoplabel1.getText());

		} catch (Exception ex) {
			Notifications notify = Notifications.create()
					.title("عذرا..خطا في الطباعة")
					.text("يرجى التاكد من مليء الحقول المطلوبة قبل الطباعة")
					.graphic(null)
					.hideAfter(Duration.seconds(5))
					.position(Pos.TOP_RIGHT);
			notify.show();
		}
	}

	public static void autocompletecusromer(TextField name) {

		List<String> autocomplete = new ArrayList<String>();
		try {

			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
			// Create Query
			String query = "select customer_name  from  input_box_hdr ";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (!autocomplete.contains(rs.getString("customer_name")))
					autocomplete.add(rs.getString("customer_name"));
			}
			query = "select customer_name  from  output_box_hdr ";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				if (!autocomplete.contains(rs.getString("customer_name")))
					autocomplete.add(rs.getString("customer_name"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TextFields.bindAutoCompletion(name, autocomplete);
	}


	// ------------------------ Customers Panel --------------
	public void addCustomerButton() {
		addcustomerborder.setVisible(true);
		showcustomerborder.setVisible(false);
	}

	public void showCustomerButton() {
		addcustomerborder.setVisible(false);
		showcustomerborder.setVisible(true);
	}
}
