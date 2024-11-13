/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.newgen.iforms.user.worksteps;

import com.newgen.iforms.EControl;
import com.newgen.iforms.FormDef;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.mvcbeans.model.WorkdeskModel;
import com.newgen.template.common.methods.CommonMethod;
import com.newgen.template.common.methods.Logging;
import com.newgen.template.common.methods.ReadProperties;
import java.io.File;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

/**
 *
 * @author Raja Soni
 */
public class Front_officer implements IFormServerEventHandler {

    CommonMethod objCommon = null;
    ReadProperties objRPF = null;
    
    public Front_officer(CommonMethod objCommon, ReadProperties objRPF) {
        
        Logging.writeConsoleLog("***=== Hello Front Officer", "===*** Inside  Front_officer Class ***===");
        
        this.objCommon = objCommon;
        this.objRPF = objRPF;
    }

    @Override
    public void beforeFormLoad(FormDef fd, IFormReference ifr) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String setMaskedValue(String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public JSONArray executeEvent(FormDef fd, IFormReference ifr, String string, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public String onChangeEventServerSide(IFormReference ifr, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public JSONArray validateSubmittedForm(FormDef fd, IFormReference ifr, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String executeCustomService(FormDef fd, IFormReference ifr, String string, String string1, String string2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCustomFilterXML(FormDef fd, IFormReference ifr, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String generateHTML(EControl ec) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String postHookExportToPDF(IFormReference ifr, File file) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String introduceWorkItemInWorkFlow(IFormReference ifr, HttpServletRequest hsr, HttpServletResponse hsr1, WorkdeskModel wm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateDataInWidget(IFormReference ifr, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String validateDocumentConfiguration(String string, String string1, File file, Locale locale) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postHookOnDocumentUpload(IFormReference ifr, String string, String string1, File file, int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean introduceWorkItemInSpecificProcess(IFormReference ifr, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
     public String executeServerEvent(IFormReference objIForm, String controlName, String eventType, String data) {
        Logging.writeConsoleLog(objIForm.getCabinetName(), "===*** Inside  Front Officer executeServerEvent Event Method Enter===");
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
                    case "CheckProbability": {
                        objCommon.CheckProbability(objIForm);
                       
                    }
                    case "CheckOne": {
                        System.out.print("Inside Check One");
                        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside Front Officer Check One===");  
                    }
                                       
                }
                 break;
            }
        }
        return webApiResponse;
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
