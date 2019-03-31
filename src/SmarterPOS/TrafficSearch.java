package SmarterPOS;

import javafx.collections.ObservableList;

import javax.swing.text.TableView;
import java.sql.*;

public class TrafficSearch {
    String s_name;
    int s_recipid;
    double s_total;
    String s_date;
    String s_recipt_type;

    public TrafficSearch() {
    }

    public TrafficSearch(String s_customer, int s_recipid, double s_total, String s_date, String s_recipt_type) {
        this.s_name = s_customer;
        this.s_recipid = s_recipid;
        this.s_total = s_total;
        this.s_date = s_date;
        this.s_recipt_type = s_recipt_type;
    }

    public String getS_customer() {
        return s_name;
    }

    public void setS_customer(String s_customer) {
        this.s_name = s_customer;
    }

    public int getS_recipid() {
        return s_recipid;
    }

    public void setS_recipid(int s_recipid) {
        this.s_recipid = s_recipid;
    }

    public double getS_total() {
        return s_total;
    }

    public void setS_total(double s_total) {
        this.s_total = s_total;
    }

    public String getS_date() {
        return s_date;
    }

    public void setS_date(String s_date) {
        this.s_date = s_date;
    }

    public String getS_recipt_type() {
        return s_recipt_type;
    }

    public void setS_recipt_type(String s_recipt_type) {
        this.s_recipt_type = s_recipt_type;
    }

    public static void search(String name, String recipt_id, String recipt_type, String from_date, String to_date, ObservableList<TrafficSearch> trafficSearches,
                              javafx.scene.control.TableView searchtable) {
        Connection con;
        Statement stmt;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&useSSL=false", "hinet", "12345678");
            stmt = con.createStatement();
            String query = "select * from input_box_hdr where customer_name= ' " + name + "'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                name = rs.getString("customer_name");

                trafficSearches.add(new TrafficSearch(name, 0, 0, null, null));

            }
            searchtable.setItems(trafficSearches);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getSalesTraffic(String id) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (con == null) {
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");

                // Create Query
                String query = "select material.barcode,material.mat_name , input_box.mat_count ,input_box.mat_price , input_box.sub_discount" +
                        "   from input_box , material  where " +
                        "material.id_mat= input_box.id_mat and input_box.id_in_box="+Integer.parseInt(id);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                String bar,m_name,count,price,s_discount,subtotal,totalprice;
                MainPanel mp=new MainPanel();
                //--get id_mat
                while (rs.next()) {
                    bar=rs.getString(1);
                    m_name=rs.getString(2);
                    count=rs.getString(3);
                    price=rs.getString(4);
                    s_discount=rs.getString(5);

                    mp.AddOrderRowTable(m_name,count,price,s_discount,bar);


                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getPurchasessTraffic(String id){

            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                if (con == null) {
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");

                    // Create Query
                    String query = "select material.barcode,material.mat_name , output_box.mat_count ,output_box.mat_price , output_box.sub_discount" +
                            "   from output_box , material  where " +
                            "material.id_mat= output_box.id_mat and output_box.id_out_box="+Integer.parseInt(id);
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    String bar,m_name,count,price,s_discount,subtotal,totalprice;
                    MainPanel mp=new MainPanel();
                    //--get id_mat
                    while (rs.next()) {
                        bar=rs.getString(1);
                        m_name=rs.getString(2);
                        count=rs.getString(3);
                        price=rs.getString(4);
                        s_discount=rs.getString(5);

                        mp.AddPurchaceRowTable(m_name,count,price,s_discount,bar);


                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    public static void getRePurchasessTraffic(String id){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (con == null) {
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");

                // Create Query
                String query = "select material.barcode,material.mat_name , input_box.mat_count ,input_box.mat_price , input_box.sub_discount" +
                        "   from input_box , material  where " +
                        "material.id_mat= input_box.id_mat and input_box.id_in_box="+Integer.parseInt(id);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                String bar,m_name,count,price,s_discount,subtotal,totalprice;
                MainPanel mp=new MainPanel();
                //--get id_mat
                while (rs.next()) {
                    bar=rs.getString(1);
                    m_name=rs.getString(2);
                    count=rs.getString(3);
                    price=rs.getString(4);
                    s_discount=rs.getString(5);

                    mp.AddRePurRowTable(m_name,count,price,s_discount,bar);


                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getReSalesTraffic(String id){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (con == null) {
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");

                // Create Query
                String query = "select material.barcode,material.mat_name , output_box.mat_count ,output_box.mat_price , output_box.sub_discount" +
                        "   from output_box , material  where " +
                        "material.id_mat= output_box.id_mat and output_box.id_out_box="+Integer.parseInt(id);
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
                String bar,m_name,count,price,s_discount,subtotal,totalprice;
                MainPanel mp=new MainPanel();
                //--get id_mat
                while (rs.next()) {
                    bar=rs.getString(1);
                    m_name=rs.getString(2);
                    count=rs.getString(3);
                    price=rs.getString(4);
                    s_discount=rs.getString(5);

                    mp.AddReSalesRowTable(m_name,count,price,s_discount,bar);


                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteRecord(String id,String idname,String table ){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (con == null) {
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");

                // Create Query
                String query1 = "SELECT MAX( "+idname+" ) FROM `table` ; " +
                        "ALTER TABLE `table` AUTO_INCREMENT = number";
                String query = "delete from "+table+" where "+idname+"="+id;
                stmt = con.createStatement();
                stmt.executeUpdate(query);

            }
        } catch (ClassNotFoundException e) {
            Material.alert("Erorr","An Erorr", e.getMessage());
        } catch (SQLException e) {
            Material.alert("Erorr","An Erorr", e.getMessage());
        }
    }

    }

