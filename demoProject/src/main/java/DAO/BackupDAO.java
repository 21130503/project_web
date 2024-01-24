package DAO;

import Services.Connect;

import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BackupDAO {
    public boolean backup(String path) {
        try {
            /*NOTE: Creating Database Constraints*/
            String dbName = "project";
            String dbUser = "root";
            String dbPass = "";

            /*NOTE: Creating Path Constraints for folder saving*/
            /*NOTE: Here the backup folder is created for saving inside it*/
            String folderPath = path + "\\backup";

            /*NOTE: Creating Folder if it does not exist*/
            File f1 = new File(folderPath);
            f1.mkdir();

            /*NOTE: Creating Path Constraints for backup saving*/
            /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
            String savePath = path + "\\backup\\" + "backup1.sql";


            /*NOTE: Used to create a cmd command*/
            String executeCmd = "E:\\XAMPP\\mysql\\bin\\mysqldump.exe --verbose -u" + dbUser + " -p" + dbPass + " --databases " + dbName + " -r " + savePath;

            /*NOTE: Executing the command here*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);

            // Wait for the process to complete
//            int processComplete = runtimeProcess.waitFor();
            int processComplete;
            try {
                processComplete = runtimeProcess.exitValue();
            } catch (IllegalThreadStateException e) {
                // Process is still running
                processComplete = -1;
            }
            System.out.println(processComplete);

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                System.out.println("Backup Complete");
                return true;
            } else {
                System.out.println("Backup failed with exit code: " + processComplete);
                return false;
            }
        } catch (IOException  ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
            ex.printStackTrace();
        }
        return false;
    }


}
