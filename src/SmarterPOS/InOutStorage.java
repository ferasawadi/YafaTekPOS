package SmarterPOS;

import java.sql.*;


public class InOutStorage {
	String id_in, id_out;
	String id;
	String mat_name;
	String date;
	String quantity;

	public InOutStorage() {
	}

	public InOutStorage(String id_in, String mat_name, String date, String quantity) {
		this.id_in = id_in;
		this.id = id;
		this.mat_name = mat_name;
		this.date = date;
		this.quantity = quantity;
	}

	public String getId_in() {
		return id_in;
	}

	public void setId_in(String id_in) {
		this.id_in = id_in;
	}

	public String getId_out() {
		return id_out;
	}

	public void setId_out(String id_out) {
		this.id_out = id_out;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMat_name() {
		return mat_name;
	}

	public void setMat_name(String mat_name) {
		this.mat_name = mat_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public static void updateQuantity(String tablename, String colmunName, String value, String Col_id, String id) {
		try {
			Connection con = null;
			Statement stt;
//            ResultSet rs;
			Class.forName("com.mysql.jdbc.Driver");
			if (con == null)
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8", "hinet", "12345678");
			stt = con.createStatement();
			String update_quantity = "UPDATE " + tablename + " SET " + colmunName + "=" + value + " WHERE " + id + " = " + Integer.parseInt(Col_id) + "";
//            System.out.println(update_quantity);
			stt.executeUpdate(update_quantity);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getid(String tablename, String col_name) {
		String id = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (con == null) {
//                        con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useSSL=false", "hazem", "");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT  " + col_name + " FROM " + tablename + "  ORDER by " + col_name + " DESC  LIMIT 1");
				if (rs.next()) {
					id = rs.getString(col_name);
					System.out.println();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static int getid(String tablename, String col_name, int return_value) {
		int id = 0;
		Statement stmt = null;
		Connection con = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if (con == null) {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8", "hinet", "12345678");
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT  * FROM " + tablename + "  ORDER by " + col_name + " DESC  LIMIT 1");
				if (rs.next()) {
					id = rs.getInt(return_value);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
}
