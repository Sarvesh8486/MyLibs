
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sarvesh.Tank
 */
public class BackupAndRestore {

    public static void Backupdbtosql(String backupLocation) {
        try {
            String dbName = "YourDBName";
            String dbUser = "YourUserName";
            String dbPass = "YourUserPassword";

            String executeCmd = "mysqldump -u" + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + backupLocation;

            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                System.out.println("Backup Complete");
            } else {
                System.out.println("Backup Failure");
            }

        } catch (InterruptedException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
    }
    
    private String selectFile(String extnType, String extn){
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select File");
        chooser.setAcceptAllFileFilterUsed(false);    
        FileNameExtensionFilter filter = new FileNameExtensionFilter(extnType, extn);
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
            return chooser.getSelectedFile().getAbsolutePath();
        }
        else {
            return null;
        }
    }

    
    public String saveFile(String extnType, String extn){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(extnType, extn);
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath()+"."+extn;
        }else{
            return null;
        }
    }
    
    
    public static void Restoredbfromsql(String backupFile) {
        try {
            String dbName = "YourDBName";
            String dbUser = "YourUserName";
            String dbPass = "YourUserPassword";

            String[] executeCmd = new String[]{"mysql", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + backupFile};

            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Successfully restored from SQL : " + backupFile);
            } else {
                JOptionPane.showMessageDialog(null, "Error at restoring");
            }

        } catch (IOException | InterruptedException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        }

    }
}
