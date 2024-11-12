/*
 NEWGEN SOFTWARE TECHNOLOGIES LIMITED
 Group : CIG
 Product / Project : HCC CMM Implementation
 Module/Application : CMM
 Version No: 1.0
 File: Template.java
 Description/Purpose : This is the main Process file.
 Author : Deepa Choudhary
 Date Created : 06-Sep-2021
----------------------------------------------------------------------------
 CHANGE HISTORY
----------------------------------------------------------------------------
 Bug ID     Date Change                      Changed by          Change Description 
 

 ----------------------------------------------------------------------------
 */
package com.newgen.iforms.user;

import com.newgen.template.common.methods.CommonMethod;
import com.newgen.template.common.methods.FetchFromDB;
import com.newgen.template.common.methods.Logging;
import com.newgen.template.common.methods.ReadProperties;
import com.newgen.iforms.custom.IFormListenerFactory;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.iforms.user.worksteps.CPC_Maker;
import com.newgen.iforms.user.worksteps.CSR_Checker;

import com.newgen.iforms.user.worksteps.Default;

import com.newgen.iforms.user.worksteps.Scan;

public class Acc_Opening implements IFormListenerFactory {

    @Override
    public IFormServerEventHandler getClassInstance(IFormReference objIForm) {
      Logging.writeConsoleLog(objIForm.getCabinetName(), "===Inside  AccountsPayable Process Main File===");
        System.out.println("Inside  AccountsPayable Process Main File=" );
        try {
            String sActivityName = objIForm.getActivityName();
            Logging.writeConsoleLog(objIForm.getCabinetName(), "Activity Name For AccountsPayable : " + sActivityName);
            ReadProperties objRPF = new ReadProperties();
            FetchFromDB db = new FetchFromDB();
            CommonMethod objCommon = new CommonMethod(db, objIForm, objRPF);

            switch (sActivityName.trim()) {
                case "Scan":
                    return new Scan(objCommon, objRPF);
                case "CSR_Checker":
                    return new CSR_Checker(objCommon, objRPF);
                case "CPC_Maker":
                    return new CPC_Maker(objCommon, objRPF);
                     
                    
               
                default:
                    return new Default(objCommon, objRPF);
            }
        } catch (Exception e) {
            System.out.println("Some Error Occurred in Main Class " + e);
        }
        return null;
    }

}
