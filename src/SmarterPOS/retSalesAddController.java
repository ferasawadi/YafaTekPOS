package SmarterPOS;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class retSalesAddController implements Initializable {
	@FXML
	Button retcancelButton;
	@FXML
	TextField UnitNameTB = new TextField();
	@FXML
	TextField
			QtyTB,
			PriceTB,
			SubtotalTB,
			discountTB,
			barcode;
	MainPanel MP = new MainPanel();

	@FXML
	public void AddPurchace() {
		try {
			if (UnitNameTB.getText().isEmpty() || QtyTB.getText().isEmpty() || PriceTB.getText().isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("خطأ في البيانات المدخلة");
				alert.setContentText("يرجى ملء القيم الضرورية (اسم المادة - الكمية - السعر).");
				alert.showAndWait();
				return;

			}
			if (Double.parseDouble(QtyTB.getText()) == 0) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("خطأ في البيانات المدخلة");
				alert.setContentText("لا يمكن أن تكون قيمة الكمية صفر");
				alert.showAndWait();
				return;

			}
			if (Double.parseDouble(PriceTB.getText()) == 0) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("خطأ في البيانات المدخلة");
				alert.setContentText("لا يمكن أن تكون قيمة السعر صفر");
				alert.showAndWait();
				return;

			}
			if (discountTB.getText().isEmpty())
				discountTB.setText("0");
			if (!MP.AddReSalesRowTable(UnitNameTB.getText(), QtyTB.getText(), PriceTB.getText(), discountTB.getText(), barcode.getText()))
				return;
			ClearForm();
		} catch (Exception ex) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("لم يتم إضافة المادة");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();
		}
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("تم");
		alert.setHeaderText("تم إضافة المادة بنجاح");
		alert.showAndWait();

	}

	void ClearForm() {
		UnitNameTB.setText("");
		QtyTB.setText("");
		PriceTB.setText("");
		discountTB.setText("");
		barcode.setText("");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		autocomplete(UnitNameTB);
		barcode.disableProperty().set(true);
		UnitNameTB.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					if (UnitNameTB.getText().isEmpty())
						return;
					String matID = MP.Matname2ID(UnitNameTB.getText());
					if (matID == null) {
						UnitNameTB.setText("");
					} else
						barcode.setText(MP.Matname2barcode(UnitNameTB.getText()));
				}
			}
		});

		QtyTB.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					try {
						if (QtyTB.getText().isEmpty())
							return;
						double d = Double.parseDouble(QtyTB.getText());
					} catch (NumberFormatException nfe) {
						QtyTB.setText("");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("خطأ في البيانات المدخلة");
						alert.setContentText("الرجاء إدخال قيمة رقمية.");
						alert.showAndWait();
					}
				}
			}
		});

		PriceTB.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					try {
						if (PriceTB.getText().isEmpty())
							return;
						double d = Double.parseDouble(PriceTB.getText());
					} catch (NumberFormatException nfe) {
						PriceTB.setText("");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("خطأ في البيانات المدخلة");
						alert.setContentText("الرجاء إدخال قيمة رقمية.");
						alert.showAndWait();
					}
				}
			}
		});

		discountTB.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
				if (!newPropertyValue) {
					try {
						if (discountTB.getText().isEmpty())
							return;
						double d = Double.parseDouble(discountTB.getText());
					} catch (NumberFormatException nfe) {
						discountTB.setText("");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("خطأ في البيانات المدخلة");
						alert.setContentText("الرجاء إدخال قيمة رقمية.");
						alert.showAndWait();
					}
				}
			}
		});


	}

	public void autocomplete(TextField name) {

		List<String> autocomplete = new ArrayList<>();
		try {

			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			Class.forName("com.mysql.jdbc.Driver");
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				// Create Query
				String query = "select mat_name from  material ";
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					autocomplete.add(rs.getString("mat_name"));
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TextFields.bindAutoCompletion(name, autocomplete);
	}

	@FXML
	public void retcancelButtonClick() {
		Stage st = (Stage) retcancelButton.getScene().getWindow();
		st.close();
	}
}
