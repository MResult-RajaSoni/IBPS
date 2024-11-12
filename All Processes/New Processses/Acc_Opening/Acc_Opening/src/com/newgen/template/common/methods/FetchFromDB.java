/*
 NEWGEN SOFTWARE TECHNOLOGIES LIMITED
 Group : CIG
 Product / Project : HCC CMM Implementation
 Module/Application : CMM
 Version No: 1.0
 File: FetchFromDB.java
 Description/Purpose : This code is written for writing Database Queries.
 Author : Karan Sharma
 Date Created : 24-Aug-2021
----------------------------------------------------------------------------
 CHANGE HISTORY
----------------------------------------------------------------------------
 Bug ID    Date Change                    Changed by           Change Description 
 
 

 ----------------------------------------------------------------------------
 */
package com.newgen.template.common.methods;

public class FetchFromDB {

    public String duplicateWICheckQuery(String sFromDate, String sToDate, String sCurrentWIName, String sTemplateID, String sRequestType, String sProduct, String sCustomerType) {
        String sCondition = "";
        if (!"New".equalsIgnoreCase(sRequestType)) {
            sCondition = " AND TemplateID ='" + sTemplateID + "' ";
        }
        String query = "SELECT TOP 1 a.ProcessInstanceID FROM WFINSTRUMENTTABLE (Nolock) a JOIN NG_HCC_EXT_Template (nolock) b ON a.ProcessInstanceID = b.TransactionID WHERE b.Product = '" + sProduct + "' AND b.CustomerType = '" + sCustomerType + "' And (b.ApplicableFrom BETWEEN '" + sFromDate + "' AND CASE WHEN '" + sToDate + "' = '' THEN Dateadd(YY,100,getdate()) ELSE '" + sToDate + "' END or ISNULL(Cast(ApplicableTo as Date),DateAdd(YY,100,getdate())) BETWEEN '" + sFromDate + "' AND CASE WHEN '" + sToDate + "' = '' THEN Dateadd(YY,100,getdate()) ELSE '" + sToDate + "' END) " + sCondition + " AND a.ActivityName NOT IN ('TemplateInitiation','Exit','Discard') AND a.ProcessInstanceID != '" + sCurrentWIName + "' ORDER BY a.Createddatetime DESC;";
        return query;
    }

    public String duplicateCheckQuery(String sProductType, String sCustomerType, String sFromDate, String sToDate) {
        String query = "SELECT Count(1) FROM NG_HCC_MST_LibraryTemplate(nolock) WHERE ISNULL(IsActive,'') != 'N' AND ISNull(IsDisabled,'') != 'Yes' AND Product = '" + sProductType + "' AND CustomerType = '" + sCustomerType + "' AND (ApplicableFrom BETWEEN '" + sFromDate + "' AND CASE WHEN '" + sToDate + "' = '' THEN Dateadd(YY,100,getdate()) ELSE '" + sToDate + "' END OR ISNULL(Cast(ApplicableTo as Date),DateAdd(YY,100,getdate())) BETWEEN '" + sFromDate + "' AND CASE WHEN '" + sToDate + "' = '' THEN Dateadd(YY,100,getdate()) ELSE '" + sToDate + "' END OR '" + sFromDate + "' BETWEEN ApplicableFrom AND ISNULL(Cast(ApplicableTo as Date),DateAdd(YY,100,getdate())) OR CASE WHEN '" + sToDate + "' = '' THEN Dateadd(YY,100,getdate()) ELSE '" + sToDate + "' END BETWEEN ApplicableFrom and ISNULL(Cast(ApplicableTo as Date),DateAdd(YY,100,getdate())))";
        return query;
    }

    public String updateDuplicateCheckQuery(String sProductType, String sCustomerType, String sTemplateID, String sFromDate, String sToDate) {
        String query = "SELECT Count(1) FROM NG_HCC_MST_LibraryTemplate(nolock) WHERE ISNULL(IsActive,'') != 'N' AND ISNull(IsDisabled,'') != 'Yes' AND Product = '" + sProductType + "' AND CustomerType = '" + sCustomerType + "' AND (ApplicableFrom BETWEEN '" + sFromDate + "' AND CASE WHEN '" + sToDate + "' = '' THEN Dateadd(YY,100,getdate()) ELSE '" + sToDate + "' END OR ISNULL(Cast(ApplicableTo as Date),DateAdd(YY,100,getdate())) BETWEEN '" + sFromDate + "' AND CASE WHEN '" + sToDate + "' = '' THEN Dateadd(YY,100,getdate()) ELSE '" + sToDate + "' END OR '" + sFromDate + "' BETWEEN ApplicableFrom AND ISNULL(Cast(ApplicableTo as Date),DateAdd(YY,100,getdate())) OR CASE WHEN '" + sToDate + "' = '' THEN Dateadd(YY,100,getdate()) ELSE '" + sToDate + "' END BETWEEN ApplicableFrom and ISNULL(Cast(ApplicableTo as Date),DateAdd(YY,100,getdate()))) AND TemplateID != '" + sTemplateID + "'";
        return query;
    }

    public String setEndorsementQuery() {
        String query = "Select EndorsementKey, EndorsementCode, EndorsementName, Format(DueDate, 'dd/MM/yyyy') DueDate from NG_HCC_MST_Endorsement (Nolock) Where IsActive='Y' order by EndorsementCode";
        return query;
    }

    public String userGroupMappingQuery(String sApp1Group, String sApp2Group, String sUserName) {
        String query = "Select '1' FROM PDBGroupMember(nolock) WHERE GroupIndex in (SELECT GroupIndex FROM PDBGroup(nolock) WHERE GroupName in ('" + sApp1Group + "','" + sApp2Group + "')) AND UserIndex = (SELECT UserIndex FROM PDBUser(nolock) WHERE UserName = '" + sUserName + "');";
        return query;
    }

    public String entryDateTimeQuery(String sProcessInstanceID) {
        String query = "SELECT EntryDATETIME FROM WFINSTRUMENTTABLE(nolock) WHERE ProcessInstanceID = '" + sProcessInstanceID + "';";
        return query;
    }

    public String searchRecordsQuery(String sEventType, String sWhereCondition) {
        String query = "";
        if (sEventType.equalsIgnoreCase("OnLoad")) {
            query = "Select TemplateID, ExcludeEndorsements, Product, CustomerType, Format(ApplicableFrom, 'dd/MM/yyyy'), Format(ApplicableTo, 'dd/MM/yyyy'),TransactionID, TemplateName from NG_HCC_MST_LibraryTemplate(nolock) where IsNull(IsDisabled,'') != 'Yes' AND ISNULL(ISActive,'') != 'N' And TemplateID is not null order by TemplateID";
        } else if (sEventType.equalsIgnoreCase("Click")) {
            query = "Select TemplateID, ExcludeEndorsements, Product, CustomerType, Format(ApplicableFrom, 'dd/MM/yyyy'), Format(ApplicableTo, 'dd/MM/yyyy'),TransactionID, TemplateName from NG_HCC_MST_LibraryTemplate(nolock) where IsNull(IsDisabled,'') != 'Yes' AND ISNULL(ISActive,'') != 'N' And TemplateID is not null " + sWhereCondition + " order by TemplateID";
        }
        return query;
    }

    public String TemplateDataFetchQuery(String sTID) {
        String query = "select TemplateID,Product,CustomerType,LineOFBusiness,ApplicableFrom,ApplicableTo,LastUpdatedDate,IsDisabled,EndorsementCode,WordingSequence,WordingText,TransactionID, TemplateName, RuleType, IsRuleDisabled, ExcludeEndorsements from NG_HCC_MST_LibraryTemplate(nolock) where IsNull(IsDisabled,'') != 'Yes' AND ISNULL(ISActive,'') != 'N' And TransactionID = '" + sTID + "' Order By Cast(SUBSTRING(WordingSequence,Charindex('-',WordingSequence)+1,LEN(WordingSequence)) as int)";
        return query;
    }

    public String TemplateWordingSeq(String sTranasctionID, String ws) {
        String query = "SELECT top 1 WordingText FROM NG_HCC_CMPLX_WordingRules_Template(nolock) WHERE TransactionID='" + sTranasctionID + "' AND WordingSequence ='" + ws + "'; ";
        return query;
    }

    public String checkLatestTemplateInLibrary(String sTemplateID) {
        String query = "select distinct TemplateID, TemplateName, TransactionID from NG_HCC_MST_LibraryTemplate(nolock) where TemplateID = '" + sTemplateID + "' and ISNULL(IsActive, '') != 'N'";
        return query;
    }

    public String checkInProcessTemplateStatus(String sTemplateID, String sProcessInstanceID) {
        String query = "Select Top 1 ProcessInstanceID, ActivityName from WFINSTRUMENTTABLE(nolock) where ProcessName = 'Template' and ActivityName not in ('Exit', 'Discard') and ProcessInstanceID != '" + sProcessInstanceID + "' and ProcessInstanceID in (select TransactionID from NG_HCC_EXT_Template(nolock) where TemplateID = '" + sTemplateID + "') order by Createddatetime desc";
        return query;
    }

    public String getEndCodeListQuery(String sProductType, String sPolicyType) {
        String query = "Select Distinct EndorsementCode from NG_HCC_MST_Endorsement (Nolock) Where ISNULL(IsActive,'') != 'N' And ProductType = '" + sProductType + "' and PolicyType = '" + sPolicyType + "' order by EndorsementCode";
        return query;
    }
}
