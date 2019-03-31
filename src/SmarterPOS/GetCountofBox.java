package SmarterPOS;

import javafx.fxml.FXML;

import java.awt.*;
import java.sql.*;

public class GetCountofBox {

	static double GetCountofBox(String ED) {
		String where1 = "";
		String where2 = "";
		String where3 = "";
		String where4 = "";
		if (ED != null) {
			where1 = "where in_box_date <= '" + ED + "'";
			where2 = "where out_box_date <= '" + ED + "'";
			where3 = " where incomes_date <= '" + ED + "'";
			where4 = "where sp_date <= '" + ED + "'";
		}
		double count1, count2, count3, count4;
		count1 = count2 = count3 = count4 = 0;

		Connection con = null;
		Statement stmt = null;

		try {
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db", "hinet", "12345678");
				// Create Query
				String query = "Select sum(total) from input_box_hdr " + where1;
				stmt = con.createStatement();
				ResultSet set = stmt.executeQuery(query);
				if (set.next()) {
					String result = set.getString(1);
					if (result != null)
						count3 = Double.parseDouble(result.trim());
				}
				query = "Select sum(total) from output_box_hdr " + where2;
				stmt = con.createStatement();
				set = stmt.executeQuery(query);
				if (set.next()) {
					String result = set.getString(1);
					if (result != null)
						count4 = Double.parseDouble(result.trim());
				}
				query = "Select sum(amount) from incomes " + where3;
				stmt = con.createStatement();
				set = stmt.executeQuery(query);
				if (set.next()) {
					String result = set.getString(1);
					if (result != null)
						count1 = Double.parseDouble(result.trim());
				}
				query = "Select sum(amount) from spending " + where4;
				stmt = con.createStatement();
				set = stmt.executeQuery(query);
				if (set.next()) {
					String result = set.getString(1);
					if (result != null)
						count2 = Double.parseDouble(result.trim());
				}

				con.close();
			}
		} catch (Exception ex) {
			//Alreat Box For Error
			Material.alert("Connection Error", "Connection Info", ex.getMessage());

		}
		return (count1 + count3) - (count2 + count4);
	}

	static int getQuantityOfMaterial(String Material_id) {
		Connection con = null;
		Statement stmt = null;
		try {
			int count_in = 0, count_out = 0;
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db", "hinet", "12345678");
				String query = "select sum(in_strg_count) from inside_storage where id_mat=" + Material_id;
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				if (rs.next()) {
					if (rs.getRow() == 1) {
						count_in = rs.getInt(1);
					} else {
						Material.alert("Error", "Error in count", "......");
					}
				}
				rs = stmt.executeQuery("Select sum(out_strg_count) from outside_storage where id_mat=" + Material_id);
				if (rs.next()) {
					if (rs.getRow() == 1) {
						count_out = rs.getInt(1);
					} else {
						Material.alert("Error", "Error in count", "......");
					}
				}
			}
			return count_in - count_out;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
