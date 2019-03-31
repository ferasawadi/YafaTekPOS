package SmarterPOS;


import java.sql.*;
import java.time.LocalDate;


public class Statistices  {


    static double getsales(LocalDate datefrom,LocalDate dateto){
        Connection con;
        Statement stmt;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&useSSL=false", "hinet", "12345678");
            stmt = con.createStatement();
            String query = "Select sum(input_box_hdr.total) as sales from input_box_hdr " +
                    " where in_box_date BETWEEN '" + datefrom + "' AND '" + dateto + "' and re_purchases=0 ";
            String query1 = "Select SUM(output_box_hdr.total)as resales from output_box_hdr " +
                    " where out_box_date BETWEEN '" + datefrom + "' AND '" + dateto + "' and  re_sales!=0 ";

            rs=stmt.executeQuery(query);
            double sales=0;
            if(rs.next()){
                sales=rs.getDouble(1);

            }
            rs=stmt.executeQuery(query1);
            double resales=0;
            if(rs.next()){

                resales=rs.getDouble(1);
            }


          return   sales-resales;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    static double getpurchasess(LocalDate datefrom,LocalDate dateto){
        Connection con;
        Statement stmt;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&useSSL=false", "hinet", "12345678");
            stmt = con.createStatement();
            String query = "Select sum(output_box_hdr.total) as purchases from output_box_hdr " +
                    " where out_box_date BETWEEN '" + datefrom + "' AND '" + dateto + "' and re_sales=0";
            String query1 = "Select SUM(input_box_hdr.total)as repurchases from input_box_hdr " +
                    " where in_box_date BETWEEN '" + datefrom + "' AND '" + dateto + "' and  re_purchases!=0 ";
            rs=stmt.executeQuery(query);
            double purchases=0,repurchases=0;
            if(rs.next()){
                purchases=rs.getDouble(1);
            }
            rs=stmt.executeQuery(query1);

            if(rs.next()){
                repurchases=rs.getDouble(1);
            }

            return   purchases-repurchases;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  0;
    }
    //------------------------------------------------------------------------
    static double getSalesForMaterial(LocalDate datefrom,LocalDate dateto,String matName){
        Connection con;
        Statement stmt;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&useSSL=false", "hinet", "12345678");
            stmt = con.createStatement();
            String sql="Select id_mat from material where mat_name ='"+matName+"'";

            rs=stmt.executeQuery(sql);
            int id=0;
            if(rs.next()){
                id=rs.getInt(1);

            }
            String query = "Select Sum(mat_count) from input_box ,input_box_hdr where input_box.id_in_box=input_box_hdr.id_in_box and re_purchases=0 and id_mat="+id+
                    " and in_box_date BETWEEN '" + datefrom + "' AND '" + dateto + "'  ";
            String query1 = "Select Sum(mat_count) from output_box ,output_box_hdr where output_box.id_out_box=output_box_hdr.id_out_box and re_sales!=0  and id_mat="+id+
                    " and out_box_date BETWEEN '" + datefrom + "' AND '" + dateto + "' ";


            rs=stmt.executeQuery(query);
            int materialsales=0;
            if(rs.next()){
                materialsales=rs.getInt(1);

            }
            rs=stmt.executeQuery(query1);
            double materialresales=0;
            if(rs.next()){

                materialresales=rs.getDouble(1);
            }


            return  materialsales-materialresales;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    static double getPurchasesForMaterial(LocalDate datefrom,LocalDate dateto,String matName){
        Connection con;
        Statement stmt;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&useSSL=false", "hinet", "12345678");
            stmt = con.createStatement();
            String sql="Select id_mat from material where mat_name ='"+matName+"'";
            rs=stmt.executeQuery(sql);
            int id=0;
            if(rs.next()){
                id=rs.getInt(1);

            }
            String query = "Select Sum(mat_count) from output_box ,output_box_hdr where output_box.id_out_box=output_box_hdr.id_out_box and re_sales=0  and id_mat="+id+"" +
                    " and out_box_date BETWEEN '" + datefrom + "' AND '" + dateto + "' ";
            String query1 = "Select Sum(mat_count) from input_box ,input_box_hdr where input_box.id_in_box=input_box_hdr.id_in_box and re_purchases!=0  and id_mat="+id+"" +
                    " and in_box_date BETWEEN '" + datefrom + "' AND '" + dateto + "'  ";


            rs=stmt.executeQuery(query);
            int materialsales=0;
            if(rs.next()){
                materialsales=rs.getInt(1);

            }
            rs=stmt.executeQuery(query1);
            double materialresales=0;
            if(rs.next()){

                materialresales=rs.getDouble(1);
            }


            return  materialsales-materialresales;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }
    static double getincomes(LocalDate datefrom,LocalDate dateto){
        Connection con;
        Statement stmt;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&useSSL=false", "hinet", "12345678");
            stmt = con.createStatement();
            String query = "Select sum(amount) from incomes " +
                    " where incomes_date BETWEEN '" + datefrom + "' AND '" + dateto + "'  ";



            rs=stmt.executeQuery(query);
            double amount=0;
            if(rs.next()){
                amount=rs.getDouble(1);

            }


            return   amount;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    static double getspending(LocalDate datefrom,LocalDate dateto){
        Connection con;
        Statement stmt;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&useSSL=false", "hinet", "12345678");
            stmt = con.createStatement();
            String query = "Select sum(amount) from spending " +
                    " where sp_date BETWEEN '" + datefrom + "' AND '" + dateto + "'  ";


            rs=stmt.executeQuery(query);
            double amount=0;
            if(rs.next()){
                amount=rs.getDouble(1);

            }



            return   amount;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }



    }

