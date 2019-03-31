package SmarterPOS;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class security {

    public security() {
    }

    public String fuscating(String st) {
        Long res = Long.parseLong(st);
        res = Long.rotateLeft(res, 5);
        res = (res + 109010);
        res = Long.rotateRight(res, 9);
        res = Math.abs(res);

        return res + "";
    }

    public Boolean CheckSerialNumber(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\"" + drive + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber";  // see note
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result1 = fuscating(result.trim());
       try {
           File f = new File(path);
           if (!f.exists()) {
               f.createNewFile();
           }
       }catch(Exception ee)
        {

        }

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {

            String line;

            if ((line = reader.readLine()) != null) {
                if (result1.equals( line))
                    return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
      return   setpropmpt(result);

    }

    Boolean setpropmpt (String St)
    {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("تسجيل");
        dialog.setHeaderText("الرجاء تسجيل النسخة الجديدة (" + St + ") ");
        dialog.setContentText("تواصل مع شركة هاي نت للحلول الذكية لتزويدك برمز النسخة الجديدة:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            if( result1.equals(result.get()))
            {
                return SaveSerialFile(result.get());

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("خطأ");
                alert.setHeaderText("خطأ في التسجيل");
                alert.setContentText("رمز التسجيل المدخل غير صحيح , الرجاء التأكد و المحاولة من الجديد .");

                alert.showAndWait();
            }


        }
        return false;
// The Java 8 way to get the response value (with lambda expression).
        //result.ifPresent(name -> System.out.println("رمز النسخة : " + name));
    }
    String result1;
    String path = new File("").getAbsoluteFile() +  "\\SN.enc";

    Boolean SaveSerialFile(String code)
    {

        try
        {
            File f = new File(path);
            if(!f.exists()){
                f.createNewFile();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, false))) {

                bw.write(code);
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();

            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("تم");
            alert.setHeaderText("تم التسجيل بنجاح");

            alert.showAndWait();
            return true;
        }
        catch (Exception ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("خطأ");
            alert.setHeaderText("خطأ في التسجيل");
            alert.setContentText("حصلت مشكلة أثناء تسجيل النسخة , الرجاء إعادة المحاولة .");

            alert.showAndWait();
        }
        return false;


    }
}
