package SmarterPOS;

import javafx.scene.control.Alert;

import java.sql.*;

public class DBConnection {
    private Connection con;
    private  String query;
    private PreparedStatement stmt=null;
    public DBConnection(){
        this.con=null;
        this.query="";
    }

   public  void DBConnection(Connection con , String query){

        this.con=con;
        this.query=query;
    }

public  Connection excuteQuery( String query) throws SQLException{
        try {
            if (con == null) {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://81.8.2.7:3306/smarter_pos_db?useSSL=no", "hazem", "");
                //stmt=con.createStatement(query);
                stmt.executeQuery(query);
                //stmt= con.prepareStatement(query);
            }
            else {
                //Alreat Box For Error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Connection Error");
                alert.setHeaderText("Connection Error");
                alert.setContentText("Cannot Connect To DB");
                alert.showAndWait();
            }
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
 return con;
}


    /* public void createConnection() throws SQLException {


        try {
            if (con == null) {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db", "hazem", "456hN");

            } else {
                //Alreat Box For Error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Connection Error");
                alert.setHeaderText("Connection Error");
                alert.setContentText("Cannot Connect To DB");
                alert.showAndWait();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }*/
}
