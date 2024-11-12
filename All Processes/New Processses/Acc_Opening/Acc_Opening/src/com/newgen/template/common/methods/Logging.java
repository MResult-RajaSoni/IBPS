/*
 NEWGEN SOFTWARE TECHNOLOGIES LIMITED
 Group : CIG
 Product / Project : HCC CMM Implementation
 Module/Application : CMM
 Version No: 1.0
 File: Log.java
 Description/Purpose : This code is written for writing logs.
 Author : Karan Sharma
 Date Created : 24-Aug-2021
----------------------------------------------------------------------------
 CHANGE HISTORY
----------------------------------------------------------------------------
 Bug ID    Date Change                    Changed by           Change Description 
 
 

 ----------------------------------------------------------------------------
 */
package com.newgen.template.common.methods;

import com.newgen.commonlogger.NGUtil;

public class Logging {

    static final String LOG_FOLDER_NAME = "Acc_Opening";

    public static void writeConsoleLog(String cabinateName, String message) {
        NGUtil.writeConsoleLog(cabinateName, LOG_FOLDER_NAME, message);
    }

    public static void writeErrorLog(String cabinateName, String message) {
        NGUtil.writeErrorLog(cabinateName, LOG_FOLDER_NAME, message);
    }

    public static void writeErrorsLog(String cabinateName, String message, Exception e) {
        NGUtil.writeErrorLog(cabinateName, LOG_FOLDER_NAME, message, e);
    }

    public static void writeXmlLog(String cabinateName, String title, String message) {
        NGUtil.writeXmlLog(cabinateName, LOG_FOLDER_NAME, title, message);
    }

    public static void writeTransactionLog(String cabinateName, String message) {
        NGUtil.writeTransactionLog(cabinateName, LOG_FOLDER_NAME, message);
    }
}
