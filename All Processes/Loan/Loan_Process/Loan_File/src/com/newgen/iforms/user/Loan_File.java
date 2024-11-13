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

import com.newgen.iforms.user.worksteps.Credit;
import com.newgen.iforms.user.worksteps.Verifier;
import com.newgen.iforms.user.worksteps.Underwriter;
import com.newgen.iforms.user.worksteps.Front_officer;
import com.newgen.template.common.methods.CommonMethod;
import com.newgen.template.common.methods.FetchFromDB;
import com.newgen.template.common.methods.Logging;
import com.newgen.template.common.methods.ReadProperties;
import com.newgen.iforms.custom.IFormListenerFactory;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.iforms.user.worksteps.Manager_Decision;


public class Loan_File implements IFormListenerFactory {

    @Override
    public IFormServerEventHandler getClassInstance(IFormReference objIForm) {
      Logging.writeConsoleLog(objIForm.getCabinetName(), "===Inside  Loan Process Main File===");
        System.out.println("Inside  Loan Process Main File=" );
        try {
            String sActivityName = objIForm.getActivityName();
            Logging.writeConsoleLog(objIForm.getCabinetName(), "Activity Name For Loan Process : " + sActivityName);
            ReadProperties objRPF = new ReadProperties();
            FetchFromDB db = new FetchFromDB();
            CommonMethod objCommon = new CommonMethod(db, objIForm, objRPF);

            switch (sActivityName.trim()) {
                case "Front_Officer":
                    return new Front_officer(objCommon, objRPF);
                case "Underwriter":
                    return new Underwriter(objCommon, objRPF);
                case "Manager_Decision":
                    return new Manager_Decision(objCommon, objRPF);
                case "Credit":
                    return new Credit(objCommon, objRPF);
                case "Verifier":
                    return new Verifier(objCommon, objRPF);

                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Some Error Occurred in Main Class " + e);
        }
        return null;
    }

}
