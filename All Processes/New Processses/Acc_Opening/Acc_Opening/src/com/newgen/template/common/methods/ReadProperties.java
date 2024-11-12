/*
 NEWGEN SOFTWARE TECHNOLOGIES LIMITED
 Group : CIG
 Product / Project : NLL Implementation
 Module/Application : NLL
 Version No: 1.0
 File: readPropertyFile.java
 Description/Purpose : This code is written for reading property file.
 Author : Karan Sharma
 Date Created : 05-Oct-2020
----------------------------------------------------------------------------
 CHANGE HISTORY
----------------------------------------------------------------------------
 Bug ID    Date Change                    Changed by           Change Description 
 
 

 ----------------------------------------------------------------------------
 */
package com.newgen.template.common.methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadProperties {

    private Properties properties = null;
    //private static Logging log = null;

    private String sCabinetName = "";
    private String sServerIP = "";
    private String sServerPort = "";
    private String sOpenPattern = "";
    private String sClosePattern = "";
    private String sClauseInitiators = "";
    private String sAppLevel1 = "";
    private String sAppLevel2 = "";
    private String sUpload_directory = "";
    private String sDocPath = "";
    private String sDebug = "";

    public ReadProperties() {

        System.out.println("===Inside readPropertyFile Constructer CMM===");
        boolean bResult = readProperty();
        System.out.println("Read Property File result is " + bResult);
        if (bResult) {
            getPropertyValue();
        }
    }

    private boolean readProperty() {
        FileInputStream fin = null;
        try {
            properties = new Properties();
            String sFilePath = System.getProperty("user.dir") + File.separator + "omniflowconfiguration" + File.separator + CMMConstants.sCONFIG_FOLDER_NAME + File.separator + "ContentConfig.properties";
            System.out.println("FilePath is " + sFilePath);
            fin = new FileInputStream(sFilePath);
            properties.load(fin);
            return true;
        } catch (IOException e) {
            System.out.println("Exception occurred while reading property file: " + e);
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException ex) {
                    Logger.getLogger(ReadProperties.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public String getsCabinetName() {
        return sCabinetName;
    }

    public void setsCabinetName(String sCabinetName) {
        this.sCabinetName = sCabinetName;
    }

    public String getsServerIP() {
        return sServerIP;
    }

    public void setsServerIP(String sServerIP) {
        this.sServerIP = sServerIP;
    }

    public String getsServerPort() {
        return sServerPort;
    }

    public void setsServerPort(String sServerPort) {
        this.sServerPort = sServerPort;
    }

    public String getsOpenPattern() {
        return sOpenPattern;
    }

    public void setsOpenPattern(String sOpenPattern) {
        this.sOpenPattern = sOpenPattern;
    }

    public String getsClosePattern() {
        return sClosePattern;
    }

    public void setsClosePattern(String sClosePattern) {
        this.sClosePattern = sClosePattern;
    }

    public String getsClauseInitiators() {
        return sClauseInitiators;
    }

    public void setsClauseInitiators(String sClauseInitiators) {
        this.sClauseInitiators = sClauseInitiators;
    }

    public String getsAppLevel1() {
        return sAppLevel1;
    }

    public void setsAppLevel1(String sAppLevel1) {
        this.sAppLevel1 = sAppLevel1;
    }

    public String getsAppLevel2() {
        return sAppLevel2;
    }

    public void setsAppLevel2(String sAppLevel2) {
        this.sAppLevel2 = sAppLevel2;
    }

    public String getsUpload_directory() {
        return sUpload_directory;
    }

    public void setsUpload_directory(String sUpload_directory) {
        this.sUpload_directory = sUpload_directory;
    }

    public String getsDocPath() {
        return sDocPath;
    }

    public void setsDocPath(String sDocPath) {
        this.sDocPath = sDocPath;
    }

    public String getsDebug() {
        return sDebug;
    }

    public void setsDebug(String sDebug) {
        this.sDebug = sDebug;
    }

    private void getPropertyValue() {
        try {
            if (properties.getProperty("CabinetName") != null && !"".equalsIgnoreCase(properties.getProperty("CabinetName"))) {
                setsCabinetName(properties.getProperty("CabinetName"));
            } else {
                System.out.println("CabinetName Not Found in Property File.");
            }
            if (properties.getProperty("ServerIP") != null && !"".equalsIgnoreCase(properties.getProperty("ServerIP"))) {
                setsServerIP(properties.getProperty("ServerIP"));
            } else {
                System.out.println("ServerIP Not Found in Property File.");
            }
            if (properties.getProperty("ServerPort") != null && !"".equalsIgnoreCase(properties.getProperty("ServerPort"))) {
                setsServerPort(properties.getProperty("ServerPort"));
            } else {
                System.out.println("ServerPort Not Found in Property File.");
            }
            if (properties.getProperty("OpenPattern") != null && !"".equalsIgnoreCase(properties.getProperty("OpenPattern"))) {
                setsOpenPattern(properties.getProperty("OpenPattern"));
            } else {
                System.out.println("OpenPattern Not Found in Property File.");
            }
            if (properties.getProperty("ClosePattern") != null && !"".equalsIgnoreCase(properties.getProperty("ClosePattern"))) {
                setsClosePattern(properties.getProperty("ClosePattern"));
            } else {
                System.out.println("ClosePattern Not Found in Property File.");
            }
            if (properties.getProperty("INITIATORGROUP") != null && !"".equalsIgnoreCase(properties.getProperty("INITIATORGROUP"))) {
                setsClauseInitiators(properties.getProperty("INITIATORGROUP"));
            } else {
                System.out.println("INITIATORGROUP Not Found in Property File.");
            }
            if (properties.getProperty("APPROVERLEVEL1GROUP") != null && !"".equalsIgnoreCase(properties.getProperty("APPROVERLEVEL1GROUP"))) {
                setsAppLevel1(properties.getProperty("APPROVERLEVEL1GROUP"));
            } else {
                System.out.println("APPROVERLEVEL1GROUP Not Found in Property File.");
            }
            if (properties.getProperty("APPROVERLEVEL2GROUP") != null && !"".equalsIgnoreCase(properties.getProperty("APPROVERLEVEL2GROUP"))) {
                setsAppLevel2(properties.getProperty("APPROVERLEVEL2GROUP"));
            } else {
                System.out.println("APPROVERLEVEL2GROUP Not Found in Property File.");
            }
            if (properties.getProperty("UPLOAD_DIRECTORY") != null && !"".equalsIgnoreCase(properties.getProperty("UPLOAD_DIRECTORY"))) {
                setsUpload_directory(properties.getProperty("UPLOAD_DIRECTORY"));
            } else {
                System.out.println("UPLOAD_DIRECTORY Not Found in Property File.");
            }
            if (properties.getProperty("DocPath") != null && !"".equalsIgnoreCase(properties.getProperty("DocPath"))) {
                setsDocPath(properties.getProperty("DocPath"));
            } else {
                System.out.println("DocPath Not Found in Property File.");
            }
            if (properties.getProperty("Debug") != null && !"".equalsIgnoreCase(properties.getProperty("Debug"))) {
                setsDebug(properties.getProperty("Debug"));
            } else {
                System.out.println("Debug Not Found in Property File.");
            }
        } catch (Exception e) {
            System.out.println("Error Occurred getPropertyValue Method: " + e);
        }
    }
}
