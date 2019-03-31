package SmarterPOS;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.controlsfx.control.textfield.TextFields;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Material {
    String material_id;
    String material_name;
    String material_unit;
    String material_notes;
    String material_barcode;
    Connection con = null;
    Statement stmt = null;
    ResultSet rs1;

    public Material() {
    }

    public Material(String material_id, String material_name, String material_unit, String material_notes, String material_barcode) {
        this.material_id = material_id;
        this.material_name = material_name;
        this.material_unit = material_unit;
        this.material_notes = material_notes;
        this.material_barcode = material_barcode;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getMaterial_unit() {
        return material_unit;
    }

    public void setMaterial_unit(String material_unit) {
        this.material_unit = material_unit;
    }

    public String getMaterial_notes() {
        return material_notes;
    }

    public void setMaterial_notes(String material_notes) {
        this.material_notes = material_notes;
    }

    public String getMaterial_barcode() {
        return material_barcode;
    }

    public void setMaterial_barcode(String material_barcode) {
        this.material_barcode = material_barcode;
    }


    //--------------------------- add material to database---------------
    public boolean addMaterial(String name, String unit, String notes, String barcode) {
        if (name.isEmpty() || unit.isEmpty() || barcode.isEmpty()) {
            alert("Error","Error in fiiling data","please fill name and unit and barcode");

            return false;
        } else
            try {
                Class.forName("com.mysql.jdbc.Driver");
                if (con == null) {
//                        con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useSSL=false", "hazem", "456hN");
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");

                    //--------  check if name and barcode of material not same in database
                    String sql = "Select * from material";
                    stmt = con.createStatement();
                    rs1 = stmt.executeQuery(sql);
                    String name_tmp = null;
                    String barcode_tmp = null;

                    while (rs1.next()) {

                        if (rs1.getString("mat_name").compareTo(name) == 0) {
                            name_tmp = name;
                            alert("Error","خطأ في اسم المادة"," الرجاء تغير اسم المادة " + name + " الى اسم آخر ");

                            break;

                        } else if (rs1.getString("barcode").compareTo(barcode) == 0) {
                            barcode_tmp = barcode;
                            alert("Error","خطأ في الباركود"," الرجاء تغيير الباركود " + barcode + "الى قيمة أخرى ");

                            break;
                        }

                    }
                    stmt.close();

                    // ---- if not same in database insert a new data
                    if (name_tmp == null && barcode_tmp == null) {
                        try {
                            Integer.parseInt(barcode);
                           // String query = "INSERT INTO material(mat_name, unit, notes, barcode) VALUES ( name ,unit , barcode)";
                            String query = "INSERT INTO material(mat_name, unit, notes, barcode) VALUES " +
                                    "('" + name + "','" +unit + "','" + notes + "','" + barcode + "')";
                            stmt = con.createStatement();
                            stmt.executeUpdate(query);
                            return true;
                        } catch (NumberFormatException ex) {
                            alert("Error","خطأ في الباركود","يجب ان يكون   " + barcode + "مكون من ارقام فقط");

                        } catch (SQLException e) {

                        }

                    }

                }

                stmt.close();
                con.close();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return false;
    }

    ResultSet rs2;

    public void update_material(String id, String name, String colname) {
//        String update_material="UPDATE `material` SET `mat_name`="+event.getNewValue()+" WHERE `id_mat`="+Integer.parseInt(mat.getMaterial_id())+"";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Statement stt;
            String name_tmp = null;
            String barcode_tmp = null;


            if (colname.equals("mat_name")) {

                if (con == null)
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
                stt = con.createStatement();

                rs2 = stt.executeQuery("select  * from  material");
                while (rs2.next()) {
                    if (rs2.getString("mat_name").compareTo(name) == 0) {

                        name_tmp = name;


                    }

                }

            } else if (colname.equals("barcode")) {
                Class.forName("com.mysql.jdbc.Driver");
                if (con == null)
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");

                stt = con.createStatement();
                rs2 = stt.executeQuery("select  * from  material");
                while (rs2.next()) {

                    if (rs2.getString("barcode").compareTo(name) == 0) {

                        barcode_tmp = name;


                    }
                }
            }

            if (name_tmp == null && barcode_tmp == null) {
                String update_material = "UPDATE `material` SET " + colname + "='" + name + "' WHERE `id_mat`=" + Integer.parseInt(id) + "";

                executequery(update_material);
            } else {
//                alert("Error","There are same material !!","please change " + colname + "  of material  " + name + "to another name");
                alert("Error","خطأ في اسم المادة"," الرجاء تغير اسم المادة " + name + " الى اسم آخر ");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public   void deletMaterial(String id) {
        String deleteMaterial = "DELETE FROM material WHERE id_mat=" + Integer.parseInt(id) + "";
        executequery(deleteMaterial);
    }

    public  void  executequery(String sql) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (con == null) {
//                        con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useSSL=false", "hazem", "123456hN");
                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");

            }
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
//            con.close();
//            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void save_inside_storage(TableView in_table,String id_in, TextField mat_name_in, DatePicker mat_date_in, TextField mat_quantity_in,
                                           ObservableList in_Storage, ObservableList in_Storage_tmp) {
        in_Storage.clear();
        Connection con = null;
        Statement stmt = null, st = null;
        ResultSet rs = null;
        if (mat_name_in.getText().isEmpty() || mat_date_in.getValue() == null || mat_quantity_in.getText().isEmpty()) {
            alert("Error","لا توجد بيانات كافية ","بعض الحقول فارغة يرجى تعبئتها");

        } else
            try {
                Class.forName("com.mysql.jdbc.Driver");
                if (con == null) {
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");

                    // Create Query
                    String query = "select id_mat from  material where mat_name ='" + mat_name_in.getText() + "'";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    int id;
                    //--get id_mat
                    if (rs.next()) {
                        in_Storage_tmp = in_table.getItems();
                        id = rs.getInt("id_mat");

                        st = con.createStatement();
                        //-- insert date
                        st.executeUpdate("insert  into  inside_storage( id_mat, in_strg_date, in_strg_count) VALUES ("+id+", ' " + mat_date_in.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " ', " + Integer.parseInt(mat_quantity_in.getText())+ ")");
                        //get last_record
                        rs=st.executeQuery("SELECT  * from inside_storage order by in_strg_id desc limit 1");
                        if(rs.next()) {
                            if(rs.getString(1)!=null)
                                id_in= rs.getInt(1) + "";
                        }else{
                            id_in= 0+"";
                        }

                        in_Storage.add(
                                new InOutStorage((Integer.parseInt(id_in))+"", mat_name_in.getText(), mat_date_in.getValue().toString(),Integer.parseInt(mat_quantity_in.getText())+"")
                        );
                        in_table.setItems(in_Storage_tmp);
                        in_table.setItems(in_Storage);
                        mat_name_in.setText("");
                        mat_date_in.setValue(LocalDate.now());
                        mat_quantity_in.setText("");
                    } else {
                        alert("Error","لا يوجد مادة","لا يوجد مادة بهذا الاسم يرجى تغيير اسم المادة...");

                    }


                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
            alert("Error","الكمية يجب ان كون رقما  ","الرجاء تغيير الكمية الى قيمة رقمية...");
            }


    }

    public static void save_outside_storage(TableView out_table,String id_out ,TextField mat_name_out, DatePicker mat_date_out, TextField mat_quantity_out,
                                            ObservableList out_storage, ObservableList out_storage_tmp) {
//        out_storage.clear();
        Connection con = null;
        Statement stmt = null, st = null;
        ResultSet rs = null;
        if (mat_name_out.getText().isEmpty() || mat_date_out.getValue() == null || mat_quantity_out.getText().isEmpty()) {
//            alert("Error","Please fill all information","some of fields are impty please fill it...");
            alert("Error","لا توجد بيانات كافية ","بعض الحقول فارغة يرجى تعبئتها");

        } else
            try {

                Class.forName("com.mysql.jdbc.Driver");
                if (con == null) {
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
                    // Create Query
                    String query = "select id_mat from  material where mat_name ='" + mat_name_out.getText() + "'";


                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    int id;


                    if (rs.next()) {
                        out_storage_tmp = out_table.getItems();
                        id = rs.getInt("id_mat");
                        int countofmaterial=MainPanel.GetCountofMaterial(id);

                        if( countofmaterial <0 || countofmaterial < Integer.parseInt(mat_quantity_out.getText())){
                            alert("Error","كمية المادة لا تكفي","لديك من هذه المادة  "+ countofmaterial +"وحدة");


                        } else {
                            st=con.createStatement();


                            st=con.createStatement();
                            //-- insert data
                            st.executeUpdate("insert  into  outside_storage( id_mat,out_strg_date, out_strg_count) VALUES ( " + id + ",' " + mat_date_out.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " ', " + Integer.parseInt(mat_quantity_out.getText()) + ")");
                            //-- get last record
                            rs=st.executeQuery("SELECT  * from outside_storage order by out_strg_id desc limit 1");
                            if(rs.next()) {
                                if(rs.getString(1)!=null)
                                    id_out = rs.getInt(1) + "";
                            }else{
                                id_out= 0+"";
                            }
                            out_storage.add(
                                    new InOutStorage((Integer.parseInt(id_out))+"", mat_name_out.getText(), mat_date_out.getValue().toString(), mat_quantity_out.getText())
                            );

                            out_table.setItems(out_storage_tmp);
                            out_table.setItems(out_storage);

                            mat_name_out.setText("");
                            mat_date_out.setValue(LocalDate.now());
                            mat_quantity_out.setText("");
                        }
                    } else {
                        alert("Error","لا توجد مادة بهذا الام","الرجاء اضافة مادة اولا ...");

                    }


                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
//                alert("Error","Quantity Must Be a Number ","Please Change Quantity Value");
                alert("Error","الكمية يجب ان كون رقما  ","الرجاء تغيير الكمية الى قيمة رقمية...");

            }



    }
    String id;
    public static  void saveMaterial( TableView material_table , TextField mat_name, TextField mat_unit, TextField mat_barcode, TextArea mat_notes,
                                      ObservableList data1, ObservableList data_tmp){

        Material material=new Material();
        data_tmp=material_table.getItems();
        boolean added=material.addMaterial(mat_name.getText(),mat_unit.getText(),mat_notes.getText(),mat_barcode.getText());
        if(added==true) {
            Connection con=null;
            Statement stmt=null;
            ResultSet rs=null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                if (con == null) {
//                        con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useSSL=false", "hazem", "123456hN");
                    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
                    stmt=con.createStatement();
                    rs=stmt.executeQuery("select * from material where mat_name='"+mat_name.getText()+"'");
                    if(rs.next()){

                        data1.add(new Material(
                                rs.getString("id_mat"),
                                rs.getString("mat_name"),
                                rs.getString("unit"),
                                rs.getString("notes"),
                                rs.getString("barcode")
                        ));
                    }
                    //--------  check if name and barcode of material not same in database
                    String sql = "Select * from material";
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            material_table.setItems(data_tmp);
            material_table.setItems(data1);

            mat_name.setText("");
            mat_unit.setText("");
            mat_barcode.setText("");
            mat_notes.setText("");
//        showData();
        }

    }
    //-------------------------------- auto complete -----------------

    public static void autocomplete(TextField name){

        List<String> autocomplete=new ArrayList<String>();
        try {

            Connection con = null;
            Statement stmt=null;
            ResultSet rs=null;
            Class.forName("com.mysql.jdbc.Driver");

                con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarter_pos_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "hinet", "12345678");
                // Create Query
                String query = "select mat_name from  material ";
                stmt = con.createStatement();
                rs=stmt.executeQuery(query);
                while (rs.next()){
                    autocomplete.add(rs.getString("mat_name"));

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        TextFields.bindAutoCompletion(name,autocomplete);

    }
//    ---------------------------alert--------------
    public  static  void alert(String title, String header, String content){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();

    }

}
