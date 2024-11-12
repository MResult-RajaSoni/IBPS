/*
 NEWGEN SOFTWARE TECHNOLOGIES LIMITED
 Group : CIG
 Product / Project : HCC CMM Implementation
 Module/Application : CMM
 Version No: 1.0
 File: TemplateInitiation.java
 Description/Purpose : This code has been written for handling all request coming to Template
 Author : Deepa Choudhary
 Date Created : 06-Sep-2021
----------------------------------------------------------------------------
 CHANGE HISTORY
----------------------------------------------------------------------------
 Bug ID     Date Change                      Changed by          Change Description 
 

 ----------------------------------------------------------------------------
 */
package com.newgen.iforms.user.worksteps;

import com.newgen.template.common.methods.CommonMethod;
import com.newgen.template.common.methods.Logging;
import com.newgen.template.common.methods.ReadProperties;
import com.newgen.iforms.EControl;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.mvcbeans.model.WorkdeskModel;
import java.io.File;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

public class CSR_Checker implements IFormServerEventHandler {

    CommonMethod objCommon = null;
    ReadProperties objRPF = null;

    public CSR_Checker(CommonMethod objCommon, ReadProperties objRPF) {
        this.objCommon = objCommon;
        this.objRPF = objRPF;
    }

    @Override
    public void beforeFormLoad(FormDef fd, IFormReference objIForm) {
       
                
        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== BespokeClause Initiation :--> Before Form Load Exit===");
    }

    @Override
    public String setMaskedValue(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONArray executeEvent(FormDef fd, IFormReference ifr, String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String executeServerEvent(IFormReference objIForm, String controlName, String eventType, String data) {
        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside  Invoice_Initiation Execute Server Event Method Enter===");
        Logging.writeConsoleLog(objIForm.getCabinetName(), "executeServerEvent() Event Type: " + eventType + "; Control Name: " + controlName + "; Data: " + data);
        String webApiResponse = "";


        switch (eventType) {
            case "Change": {
                switch (controlName) {
                  case "fetchPanNoOrAdharNo": {
                     objCommon.FetchPanNoOrAadharNO1(objIForm);
                    }
                }
                break;
            }
                case "Load": {
                switch (controlName) {
                    case "fetchPanNoOrAdharNo": {
                     objCommon.FetchPanNoOrAadharNO1(objIForm);
                    }
             
                }
                break;
            }
            case "Click": {
                switch (controlName) {
                    case "VerifyUID": {
                     objCommon.VerifyUID(objIForm);
                     break;
                    }
                     case "VerifyPAN": {
                     objCommon.VerifyPAN(objIForm);
                     break;
                    }
                      case "CheckCKYC": {
                     objCommon.CheckCKYC(objIForm);
                     break;
                    }
                      
                        
                        
                        
                      
                      
                    
                    
                    
                     
                                       
                }
            }
        }
      return webApiResponse;
    }

    @Override
    public String onChangeEventServerSide(IFormReference ifr, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONArray validateSubmittedForm(FormDef fd, IFormReference ifr, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String executeCustomService(FormDef fd, IFormReference ifr, String string, String string1, String string2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCustomFilterXML(FormDef fd, IFormReference ifr, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String generateHTML(EControl ec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1, WorkdeskModel wm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDataInWidget(IFormReference ifr, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String validateDocumentConfiguration(String string, String string1, File file, Locale locale) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postHookOnDocumentUpload(IFormReference ifr, String string, String string1, File file, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean introduceWorkItemInSpecificProcess(IFormReference ifr, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String postHookExportToPDF(IFormReference ifr, File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

