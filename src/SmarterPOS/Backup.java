package SmarterPOS;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Backup {
    public Backup() {
    }

    public static void Backupdbtosql()  {
        try{
            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = Backup.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();


            /*NOTE: Creating Database Constraints*/
            String dbName = "smarter_pos_db";
            String dbUser = "hinet";
            String dbPass = "12345678";

            /*NOTE: Creating Path Constraints for folder saving*/
            /*NOTE: Here the backup folder is created for saving inside it*/
            String folderPath = jarDir + "/backup";
            System.out.println(folderPath);

            /*NOTE: Creating Folder if it does not exist*/
//            File f1 = new File(folderPath);
//            f1.mkdirs();

//            f1.createNewFile(LocalDate.now().toString()+".sql");

            /*NOTE: Creating Path Constraints for backup saving*/
            /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/

            Date date = new Date() ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH") ;
            File file = new File(folderPath+"/"+dateFormat.format(date) + ".sql") ;
            file.createNewFile();
            String savePath = "" + folderPath + "/" + file.getName();
//    System.out.println(folderPath);
            /*NOTE: Used to create a cmd command*/
            String executeCmd = "C:\\xampp\\mysql\\bin\\mysqldump.exe -u " +dbUser + " -p" + dbPass + "  " + dbName + " -r " + savePath;
//            Material.alert("","",executeCmd);
//            String executeCmd = "C:\\xampp\\mysql\\bin\\mysql.exe -u " + dbUser + " -p" + dbPass + " select * from smarter_pos_db.users";
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
//
//
//            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                Material.alert("","","Backup ok");
            } else {
//                System.out.println("Backup Failure"+ processComplete);
                Material.alert("","","Backup Failure");

            }


            Runtime rt = Runtime.getRuntime();
//    String[] commands = {"system.exe","-get t"};
            Process proc = rt.exec(executeCmd);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

// read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

// read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

        } catch (URISyntaxException | IOException ex) {
//            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
            ex.getMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    public static void importdbtosql(String path) throws Exception  {
//        try{

        /*NOTE: Getting path to the Jar file being executed*/
        /*NOTE: YourImplementingClass-> replace with the class executing the code*/
        CodeSource codeSource = Backup.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();


        /*NOTE: Creating Database Constraints*/
        String dbName = "smarter_pos_db";
        String dbUser = "hinet";
        String dbPass = "12345678";

        /*NOTE: Creating Path Constraints for folder saving*/
        /*NOTE: Here the backup folder is created for saving inside it*/
        String folderPath = jarDir + "\\backup";
        System.out.println(folderPath);

        /*NOTE: Creating Folder if it does not exist*/
        File f1 = new File(folderPath);
        f1.mkdir();

        /*NOTE: Creating Path Constraints for backup saving*/
        /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
        String savePath = "\"" + jarDir + "\\backup\\" + "backup.sql\"";

        /*NOTE: Used to create a cmd command*/
        String executeCmd1 = "C:\\xampp\\mysql\\bin\\mysql.exe -u " +dbUser + " -p" + dbPass + " " + " -e \"create database "+dbName+"\"";
//        System.out.println(executeCmd1);
        String executeCmd = "C:\\xampp\\mysql\\bin\\mysql.exe -u hinet -p12345678 "+dbName+" -e " +" \" source "+ path +"\" ";



        /*NOTE: Executing the command here*/
        Process runtimeProcess1 = Runtime.getRuntime().exec(executeCmd1);
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);

//            Process runtimeProcess2 = Runtime.getRuntime().exec(executeCmd2);
//            System.out.println(runtimeProcess1);
        int processComplete = runtimeProcess1.waitFor();
//            System.out.println(processComplete);
//            System.out.println(processComplete);
//
//            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/

        if(!path.isEmpty()){
            if (processComplete == 0) {
                Material.alert("","","Backup Complete");
            } else {
                Material.alert("","","Backup Complete");
            }
        }else{
            Material.alert("","","Backup Failure");

        }



        Runtime rt = Runtime.getRuntime();
//  String[] commands = {"system.exe","-get t"};
        Process proc = rt.exec(executeCmd);

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));

// read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }

// read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }

    }


}
