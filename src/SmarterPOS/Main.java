package SmarterPOS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
	Security instance = new Security();

	@Override
	public void start(Stage primaryStage) throws Exception {
		/**
		 *    Security Check
		 */
		if (!instance.CheckSerial()) {
			return;
		}
		ResourceBundle resources = ResourceBundle.getBundle("translations", new Locale("ar", "JO"));
		Parent root = FXMLLoader.load(getClass().getResource("/Layout/Login.fxml"), resources);
		Stage stage = new Stage();
		stage.setTitle("Login");
		Scene loginscene = new Scene(root, 500, 400);
		stage.setScene(loginscene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	// ------- Initializing  MAIN Panel --------
	public void doMainPage() throws IOException {

		Parent root1 = FXMLLoader.load(getClass().getResource("/Layout/MainPanel.fxml"));
		Stage stage = new Stage();
		stage.setTitle("HiNet SmarterPOS");
		Scene mainscene = new Scene(root1, 1100, 674);
		stage.setScene(mainscene);
		stage.show();
	}

	// ------- Initializing Order Panel --------
	public void doOrderWindow() throws IOException {
		ResourceBundle resources = ResourceBundle.getBundle("translations", new Locale("en", "UK"));
		Parent root2 = FXMLLoader.load(getClass().getResource("/Layout/Order.fxml"), resources);
		Stage stage = new Stage();
		stage.setTitle("Order Panel");
		Scene orderscene = new Scene(root2, 700, 500);
		stage.setScene(orderscene);
		stage.show();
	}

	// ---------- Initializing Purchases Add Panel ----
	public void doPurchasessaddWindow() throws IOException {
		ResourceBundle resources = ResourceBundle.getBundle("translations", new Locale("en", "UK"));
		Parent root3 = FXMLLoader.load(getClass().getResource("/Layout/purchasesform.fxml"), resources);
		Stage stage = new Stage();
		stage.setTitle("Add Purchases Item ");
		Scene purchasesaddscene = new Scene(root3, 650, 550);
		stage.setScene(purchasesaddscene);
		stage.show();
	}

	// ------- Initializing Return Add Panel --------
	public void doRetSalesAddWindow() throws IOException {
		ResourceBundle resources = ResourceBundle.getBundle("translations", new Locale("en", "UK"));
		Parent root3 = FXMLLoader.load(getClass().getResource("/Layout/retSalesAdd.fxml"), resources);
		Stage stage = new Stage();
		stage.setTitle("Returned Sales Add");
		Scene retaddscene = new Scene(root3, 700, 500);
		stage.setScene(retaddscene);
		stage.show();
	}

	// ------- Initializing Return Purchases Add Panel --------
	public void doRetPurchAddWindow() throws IOException {
		ResourceBundle resources = ResourceBundle.getBundle("translations", new Locale("en", "UK"));
		Parent root4 = FXMLLoader.load(getClass().getResource("/Layout/retPurchadd.fxml"), resources);
		Stage stage = new Stage();
		stage.setTitle("Returned Purchases Add");
		Scene retaddscene = new Scene(root4, 700, 500);
		stage.setScene(retaddscene);
		stage.show();
	}
}
