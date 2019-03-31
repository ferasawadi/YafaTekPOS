package SmarterPOS;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.management.Notification;
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.PrivateKey;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {
	ResourceBundle bundle;
	@FXML
	TextField usertext, passtext;
	@FXML
	Button loginButton;
	@FXML
	public Label user;

	// ---------------- Close Login Dialog Function ---------------
	@FXML
	public void closeLoginWindow() {
		Stage stage = (Stage) loginButton.getScene().getWindow();
		stage.close();
	}

	// ---------------- end of Close Login Dialog Function ---------------
	//-------------------Login check  Function  ---------------------
	public void doConnection() throws SQLException, IOException {

		Connection con = null;
		PreparedStatement stmt = null;
		String query = "";
		try {
			//get data from user
			String user = usertext.getText();
			String pass = passtext.getText();
			// Create Query
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
			query = "Select * from users where username=?  and pass_word=?";
			//connection.DBConnection(con,query);
			stmt = con.prepareStatement(query);
			stmt.setString(1, user);
			stmt.setString(2, pass);
			//Result
			ResultSet set = stmt.executeQuery();
			if (set.next()) {
				//Method To Bring the Administration Panel
				Main obj = new Main();
				obj.doMainPage();
				stmt.close();
				con.close();
				closeLoginWindow();
			} else {
				Notifications notify = Notifications.create()
						.title("Connection Error")
						.text("Username and/or Password are Incorrect")
						.graphic(null)
						.hideAfter(Duration.seconds(5))
						.position(Pos.TOP_RIGHT);
				notify.show();
			}


		} catch (Exception ex) {
			//Alreat Box For Erroradmin admin
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Connection Error");
			alert.setHeaderText("Connection Info");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
	}
}

