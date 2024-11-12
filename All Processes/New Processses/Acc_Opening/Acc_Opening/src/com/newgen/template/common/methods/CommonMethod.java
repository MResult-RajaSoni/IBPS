package com.newgen.template.common.methods;


import com.newgen.iforms.custom.IFormReference;
import com.newgen.omni.wf.util.app.NGEjbClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.tess4j.Tesseract;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CommonMethod {

    private FetchFromDB db = null;
    IFormReference objIForm = null;
    ReadProperties objRPF = null;

    public CommonMethod(FetchFromDB db, IFormReference objIForm, ReadProperties objRPF) {
        this.db = db;
        this.objIForm = objIForm;
        this.objRPF = objRPF;

    }
    public void FetchPanNoOrAadharNO2(IFormReference objIForm)   {
        try{       
        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside PurchaseOrde 1 ===");
                String jarPath="C:\\Newgen\\jboss-eap-7.2\\standalone\\deployments\\Acc_Opening.war\\WEB-INF\\lib\\tess4j-3.4.8.jar";
                        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside PurchaseOrde 2 ===");

                CustomeClassLoader classloader=new CustomeClassLoader(new URL[]{}, CustomeClassLoader.class.getClassLoader());
                        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside PurchaseOrde 3 ===");

                classloader.addJarToClassPath(jarPath);
                        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside PurchaseOrde 4 ===");

                
                Class<?> tesseract=classloader.loadClass("net.sourceforge.tess4j.Tesseract");
                        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside PurchaseOrde 5 ===");
        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside PurchaseOrde 2 ===");

                Object tess=tesseract.newInstance();
                        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside PurchaseOrde 6 ===");

            Method doOcr = tesseract.getDeclaredMethod("doOCR", InputStream.class);
                    Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside PurchaseOrde 7 ===");

            tesseract.getMethod("setDatapath", String.class).invoke(tess, "D:\\New folder\\Tess4J-3.4.8-src (1)\\Tess4J\\tessdata");
                    Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside PurchaseOrde 8 ===");

            Object result = doOcr.invoke(tess, new File("D:\\New folder\\Document to test\\AadharCard.png"));
            
                    Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside PurchaseOrde 9 ===");

        }catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException|InstantiationException|IllegalAccessException | InvocationTargetException ex){
                                Logging.writeConsoleLog(objIForm.getCabinetName(),   ex.getMessage());

            ex.printStackTrace();
            
        }
                
    }
    

    public void FetchPanNoOrAadharNO(IFormReference objIForm) {

        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside FetchPanNoOrAadharNO ===");

       objIForm.setValue("textbox39", "9833-8327-6975");
       
            }
    
    public void FetchIdNumber(IFormReference objIForm) {

       Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside FetchPanNoOrAadharNO ===");
       objIForm.setValue("AO_IdentitiyNumber", "9833-8327-6975");
       
            }
    
    public void FetchPanNo(IFormReference objIForm) {

        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside FetchPanNO ===");

       objIForm.setValue("PanCardNumber", "AAAAA8888A");
       
            }
    
    	public static final Object lock = new Object();

     public void FetchPanNoOrAadharNO1(IFormReference objIForm) {
         
         

        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside fetch pan and adhar ===");
        
        		synchronized (lock) {
                                    Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside fetch pan and adhar lock ===");

			 	
			 try { 
				                                     Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside fetch pan and adhar try ===");

				 			 
			 String directoryPath = "D:\\New folder";

         // Create ProcessBuilder
         ProcessBuilder processBuilder = new ProcessBuilder();
   Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Process builder ===");

         // Set the working directory
         processBuilder.directory(new File(directoryPath));
         Path path=Paths.get("D:\\New folder\\tesseract-result123.txt");
         
           Logging.writeConsoleLog(objIForm.getCabinetName(), "=== path setting ===");

        

         // Command to execute in the directory
         String command = "tesseract panCard.png tesseract-result123"; // Example command: "dir" lists files in the current directory
           Logging.writeConsoleLog(objIForm.getCabinetName(), "===  command ===");

         // Set the command and arguments
         processBuilder.command("cmd", "/c", command);
	            // Command to execute
	                       Logging.writeConsoleLog(objIForm.getCabinetName(), "=== 1 command ===");

	            // Create ProcessBuilder

	            // Redirect error stream to output stream
	            processBuilder.redirectErrorStream(true);
           Logging.writeConsoleLog(objIForm.getCabinetName(), "=== 2 command ===");

	            // Start the process
	            Process process = processBuilder.start();
           Logging.writeConsoleLog(objIForm.getCabinetName(), "===  3 command ===");

	            // Read output from the command

	            // Wait for the process to finish
	            int exitCode = process.waitFor();

	            // Check if the process executed successfully
	           
                                   Logging.writeConsoleLog(objIForm.getCabinetName(), "=== 4 command ===");

	                System.out.println("Command executed successfully.");
                     
             FileInputStream fis=new FileInputStream("D:\\New folder\\tesseract-result123.txt");
	                
	                           Logging.writeConsoleLog(objIForm.getCabinetName(), "=== 5 command ===");

	                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

	                // Use a StringBuilder to store the contents of the InputStream
	                StringBuilder stringBuilder = new StringBuilder();
	                String line;

	                // Read each line from the InputStream and append it to the StringBuilder
	                while ((line = reader.readLine()) != null) {
	                    stringBuilder.append(line).append("\n");
	                }

	                // Convert the StringBuilder to a String
	                String result = stringBuilder.toString();
	                
	                //String content = Files.readString(Paths.get("C:\\Users\\Amol\\Desktop\\ap\\tesseract-result123.txt"));
		            System.out.println(result);
		            fis.close();
                            
                             Logging.writeConsoleLog(objIForm.getCabinetName(), "==== inside Pan Card ===");

                 Pattern panPattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

	        // Match the pattern against the OCR result
	     Matcher matcher = panPattern.matcher(result);
        String ss=null;
	        // Check if a match is found
	       if (matcher.find()) {
	      	 ss= matcher.group(0);
                
             Logging.writeConsoleLog(objIForm.getCabinetName(), "==== Matcher pan ==="+ss);

	       }
                objIForm.setValue("AO_IdentitiyNumber", ss);
                            
                            

		            
		            if(Files.exists(path)) {
		             	try {
		                     Files.delete(path);
		                     System.out.println("File deleted successfully.");
		                 } catch (IOException e) {
		                     System.err.println("Failed to delete the file: " + e.getMessage());
		                 }
		             } else {
		                 System.err.println("File does not exist.");
		             }
		            
		            

	            
	            
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
			 }

    

           

	                     }
    
    
      public void VerifyUID(IFormReference objIForm) {

        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside Verify UID ===");
        objIForm.setValue("N_UID_Authentication", "Verified");
      
    }
      
       public void VerifyPAN(IFormReference objIForm) {
           
        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside Verify PAN ===");

        objIForm.setValue("verify_Pan_Status", "Verified");
        
    }
         public void CheckCKYC(IFormReference objIForm) {

        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside Check KYC ===");
        
         objIForm.setValue("CKYC_Status", "Verified");
      
    }
         
          public void CheckAMLStatus(IFormReference objIForm) {

        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside Mismatch ===");
                    objIForm.setValue("AO_AML_Check", "Pass");
                     objIForm.setValue("AO_BlackListCheck", "Not Blacklisted");
                    
       
    }
          
          public void CreateFinnacleAccount(IFormReference objIForm) {

        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside create Finnacle Account ===");

          objIForm.setValue("Finnacle_Acc_Status", "Finacle account created successfully ");
                     objIForm.setValue("Finnacl_Acc_No", "678790456532");


    }
            public void fetchInvoiceLIneItem(IFormReference objIForm) {

        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside fetchInvoiceLIneItem ===");

        try {

            String invoiceNumber = objIForm.getValue("Invoice_Number").toString();
            
                   Logging.writeConsoleLog(objIForm.getCabinetName(), "===  invoice number ==="+invoiceNumber);
        
 
     
            String Query = "select ItemName,HSACode,quantity,unitRate,amount from sap_invoice where invoiceNumber ='" + invoiceNumber + "'";

            Logging.writeConsoleLog(objIForm.getCabinetName(), "Query*******" + Query);

            List<List> result = objIForm.getDataFromDB(Query);

            Logging.writeConsoleLog(objIForm.getCabinetName(), "Result****" + result);
            objIForm.clearTable("table27");

            if (result != null && result.size() > 0) {

                JSONArray poDetails = new JSONArray();

                for (int i = 0; i < result.size(); i++) {

                    JSONObject obj = new JSONObject();
              
                   obj.put("S. No.", i+1);

                    obj.put("Desc of Goods", result.get(i).get(0).toString());

                    obj.put("HSN/SAC Code", result.get(i).get(1).toString());

                    obj.put("Quantity", result.get(i).get(2).toString());

                    obj.put("Unit rate", result.get(i).get(3).toString());
                    obj.put("Amount", result.get(i).get(4).toString());

                    poDetails.add(obj);

                }
                Logging.writeConsoleLog(objIForm.getCabinetName(), "pclPartyDetails final grid" + poDetails);

                objIForm.addDataToGrid("table27", poDetails);
              //  objIForm.setValue(Query, "m");
               // objIForm.setStyle("id Column", "disable", "true");
                

            }

        } catch (Exception e) {

            Logging.writeConsoleLog(objIForm.getCabinetName(), " Exception in pclPartyDetails Details  " + e);

        }

    }
             public void fetchInvoiceAgainstPo(IFormReference objIForm) {

        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside fetchInvoiceAgainstPo ===");

        try {

            String poNumber = objIForm.getValue("PO_Number").toString();
            
                   Logging.writeConsoleLog(objIForm.getCabinetName(), "===  invoice number ==="+poNumber);
        
 
     
            String Query = "select invoiceNumber,invoiceDate,amount,vendorCode,status from invoiceAgainstPo where PoNumber ='" + poNumber + "'";

            Logging.writeConsoleLog(objIForm.getCabinetName(), "Query*******" + Query);

            List<List> result = objIForm.getDataFromDB(Query);
            objIForm.saveDataInDB(Query);

            Logging.writeConsoleLog(objIForm.getCabinetName(), "Result****" + result);
            objIForm.clearTable("table28");

            if (result != null && result.size() > 0) {

                JSONArray poDetails = new JSONArray();

                for (int i = 0; i < result.size(); i++) {

                    JSONObject obj = new JSONObject();
              
                   obj.put("S. No.", i+1);

                    obj.put("Invoice No.", result.get(i).get(0).toString());

                    obj.put("Invoice Date", result.get(i).get(1).toString());

                    obj.put("Amount", result.get(i).get(2).toString());

                    obj.put("Vendor Code", result.get(i).get(3).toString());
                    obj.put("Status", result.get(i).get(4).toString());

                    poDetails.add(obj);

                }
                Logging.writeConsoleLog(objIForm.getCabinetName(), "pclPartyDetails final grid" + poDetails);

                objIForm.addDataToGrid("table28", poDetails);
              //  objIForm.setValue(Query, "m");
               // objIForm.setStyle("id Column", "disable", "true");
                

            }

        } catch (Exception e) {

            Logging.writeConsoleLog(objIForm.getCabinetName(), " Exception in pclPartyDetails Details  " + e);

        }

    }
                public void DuplicateInvoice(IFormReference objIForm) {

        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside DuplicateInvoice ===");

        try {

            String poNumber = objIForm.getValue("PO_Number").toString();
            
                   Logging.writeConsoleLog(objIForm.getCabinetName(), "===  invoice number ==="+poNumber);
        
 
     
            String Query = "select vendorCode,invoiceNumber,invoiceDate,amount,RequestNO,weightage from invoiceAgainstPo where PoNumber ='" + poNumber + "'";

            Logging.writeConsoleLog(objIForm.getCabinetName(), "Query*******" + Query);

            List<List> result = objIForm.getDataFromDB(Query);
            
             Logging.writeConsoleLog(objIForm.getCabinetName(), "result****" + result);
            List<List> finalList=new ArrayList<>();
            for(int i=0;i<result.size();i++){
                
                     if(result.get(i).get(1).equals(result.get(0).get(1))){
                         finalList.add(result.get(i));
                     
                }
               
            }

            Logging.writeConsoleLog(objIForm.getCabinetName(), "finalList****" + finalList);
            objIForm.clearTable("table29");

            if (finalList != null && finalList.size() > 0) {

                JSONArray poDetails = new JSONArray();

                for (int i = 0; i < finalList.size(); i++) {

                    JSONObject obj = new JSONObject();
              
                   obj.put("S. No.", i+1);

                    obj.put("Vendor Code", finalList.get(i).get(0).toString());

                    obj.put("Invoice Number", finalList.get(i).get(1).toString());

                    obj.put("Invoice Date", finalList.get(i).get(2).toString());

                    obj.put("Invoice Amount", finalList.get(i).get(3).toString());
                    obj.put("Request No", finalList.get(i).get(4).toString());
                    obj.put("Weightage", finalList.get(i).get(5).toString());


                    poDetails.add(obj);

                }
                Logging.writeConsoleLog(objIForm.getCabinetName(), "pclPartyDetails final grid" + poDetails);

                objIForm.addDataToGrid("table29", poDetails);
              //  objIForm.setValue(Query, "m");
               // objIForm.setStyle("id Column", "disable", "true");
                

            }

        } catch (Exception e) {

            Logging.writeConsoleLog(objIForm.getCabinetName(), " Exception in pclPartyDetails Details  " + e);

        }

    }
                  public void createworkItem(IFormReference objIForm) {
        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside Test Method ===");

       
		 XMLParser xmlParser = null;
		 NGEjbClient ngejbclient;
		try {
			
			 ngejbclient=NGEjbClient.getSharedInstance();
			//ngejbclient.initialize(appServerIp ,appServerPort,appServerType);
			
			//read appServerIp ,appServerPort,appServerName from the property file.
			ngejbclient.initialize("127.0.0.1" ,"3333","JTS");
			String userName ="padmin";
			String passwrod="supervisor@2023";
			String cabinetName="mresultdev";
			String processDefId="23";
                                Logging.writeConsoleLog(objIForm.getCabinetName(), "-------------------Connect ---------------------");

			
		    String inputXml = "<?xml version=\"1.0\"?><WMConnect_Input><Option>WMConnect</Option>"
                    + "<EngineName>mresultdev</EngineName>"
                    + "<ApplicationInfo></ApplicationInfo><Participant><Name>" + userName + "</Name>"
                    + "<Password>" + passwrod + "</Password><Scope></Scope>"
                    + "<UserExist>N</UserExist><Locale>en-us</Locale><ParticipantType>U</ParticipantType>"
                    + "</Participant><Hook>No</Hook></WMConnect_Input>";
            String outputXml = ngejbclient.makeCall(inputXml);
            //parse the output XML             
            //SessionID is returned in response 
           String sessionId=null;
          // String date=null;
                    Logging.writeConsoleLog(objIForm.getCabinetName(), "out put session"+outputXml);

            System.out.println(outputXml);
            xmlParser = new XMLParser();
            xmlParser.setInputXML(outputXml);
            if (xmlParser.getValueOf("MainCode").equalsIgnoreCase("0")) {
                sessionId = xmlParser.getValueOf("SessionId");
                Logging.writeConsoleLog(objIForm.getCabinetName(), "Session ID is"+sessionId);

                System.out.println("Session ID is:-"+sessionId);
            } else {
       Logging.writeConsoleLog(objIForm.getCabinetName(), "Some Error occured in getting the session ID");

        System.out.println("Some Error occured in getting the session ID");
            }  
            
             String Query = "SELECT aadhar_no,address,email,name,pan_no,phone,id FROM EMP_SYSTEM1 where flage='Y';";

            Logging.writeConsoleLog(objIForm.getCabinetName(), "Query*******" + Query);

            List<List> result = objIForm.getDataFromDB(Query);
            
          
            Logging.writeConsoleLog(objIForm.getCabinetName(), "Result****" + result);
            
            List<List> finalList=new ArrayList<>();
            for(int i=0;i<result.size();i++){
                
                     if(result.get(i).get(1).equals(result.get(0).get(1))){
                         finalList.add(result.get(i));
                     
                }
               
            }
              if (finalList != null && finalList.size() > 0) {

                JSONArray poDetails = new JSONArray();

                for (int i = 0; i < finalList.size(); i++) {
                   String id= finalList.get(i).get(6).toString();

            String WF2  = "<?xml version=\"1.0\"?><WFUploadWorkItem_Input><Option>WFUploadWorkItem</Option>"
                    + "<EngineName>mresultdev</EngineName>"
            		+ "<SessionId>"+sessionId+"</SessionId>"
                    +  "<ProcessDefId>"+processDefId+"</ProcessDefId>"
                //    + "<QueueId>" + 159 + "</QueueId>"
                 //   +"<InitiateFromActivityName>Kyc_Process_Start Event_1</InitiateFromActivityName>"
                    + "<DataDefName></DataDefName>"
                    +"<Fields></Fields>"
                    + "<InitiateAlso>N</InitiateAlso>"
                     +"<VariantId>0</VariantId>"
                    + "<UserDefVarFlag>Y</UserDefVarFlag>"
                     +"<Attributes>"
                    +"<Name>"+finalList.get(i).get(3).toString()+"</Name>"
                     +"<Address>"+finalList.get(i).get(1).toString()+"</Address>"
                    +"<Email>"+finalList.get(i).get(2).toString()+"</Email>"
                     +"<aadhar>"+finalList.get(i).get(0).toString()+"</aadhar>"
                    +"<pan>"+finalList.get(i).get(4).toString()+"</pan>"
                      +"<Phone>"+finalList.get(i).get(5).toString()+"</Phone>"
                    +"</Attributes>"
                    +"<Documents>"
                     +"</Documents>"
                    +"</WFUploadWorkItem_Input>";
            
            String wfUploadWorkItemOutputXML2=ngejbclient.makeCall(WF2);
       Logging.writeConsoleLog(objIForm.getCabinetName(), ""+wfUploadWorkItemOutputXML2);

            System.out.println(wfUploadWorkItemOutputXML2);
           
            
            xmlParser = new XMLParser();
            xmlParser.setInputXML(wfUploadWorkItemOutputXML2);
            if (xmlParser.getValueOf("MainCode").trim().equals("0")) {
                String processId = xmlParser.getValueOf("ProcessInstanceId");
           Logging.writeConsoleLog(objIForm.getCabinetName(), "Workitem ID"+processId);
                
                System.out.println("Workitem ID :---"+processId);
                 String Query1= "update emp_system1 set flage='N'  where id ='" + id + "'";
                       int saveDataInDB = objIForm.saveDataInDB(Query1);
                        Logging.writeConsoleLog(objIForm.getCabinetName(), "rows updated"+saveDataInDB);
            } else {
    Logging.writeConsoleLog(objIForm.getCabinetName(), "Some error occured while uploading workitem");

               System.out.println("Some error occured while uploading workitem");
            }
                }
              }
               Logging.writeConsoleLog(objIForm.getCabinetName(), "---------------Disconnect --------");

            System.out.println("-------------------Disconnect ---------------------");
            //Get the session id from the outputXML 
            String disconnectXml="<?xml version=\"1.0\"?> <WMDisConnect_Input> <Option>WMDisConnect</Option> <EngineName>mresultdev</EngineName> <SessionID>"+sessionId+"</SessionID> <UnlockWorkitem></UnlockWorkitem> </WMDisConnect_Input>";
            
            String disoutputXml = ngejbclient.makeCall(disconnectXml);
            //parse the output XML             
            //SessionID is returned in response 
            Logging.writeConsoleLog(objIForm.getCabinetName(), ""+disoutputXml);

            System.out.println(disoutputXml);
            xmlParser = new XMLParser();
            xmlParser.setInputXML(disoutputXml);
            if (xmlParser.getValueOf("MainCode").equalsIgnoreCase("0")) {
                            Logging.writeConsoleLog(objIForm.getCabinetName(), "Session Disconnected");

                System.out.println("Session Disconnected");
            } else {
                Logging.writeConsoleLog(objIForm.getCabinetName(), "Some Error occured while disconnecting the session");

                 System.out.println("Some Error occured while disconnecting the session");
            } 

                } catch (Exception e) {
			// TODO: handle exception
		}
                                objIForm.setValue("textbox283", "WorkItem created");

                  }
                 public void createworkItem1(IFormReference objIForm) {
        Logging.writeConsoleLog(objIForm.getCabinetName(), "=== Inside Test Method ===");

       
		 XMLParser xmlParser = null;
		 NGEjbClient ngejbclient;
		try {
			
			 ngejbclient=NGEjbClient.getSharedInstance();
			//ngejbclient.initialize(appServerIp ,appServerPort,appServerType);
			
			//read appServerIp ,appServerPort,appServerName from the property file.
			ngejbclient.initialize("127.0.0.1" ,"3333","JTS");
			String userName ="padmin";
			String passwrod="supervisor@2023";
			String cabinetName="mresultdev";
			String processDefId="23";
                                Logging.writeConsoleLog(objIForm.getCabinetName(), "-------------------Connect ---------------------");

			
		    String inputXml = "<?xml version=\"1.0\"?><WMConnect_Input><Option>WMConnect</Option>"
                    + "<EngineName>mresultdev</EngineName>"
                    + "<ApplicationInfo></ApplicationInfo><Participant><Name>" + userName + "</Name>"
                    + "<Password>" + passwrod + "</Password><Scope></Scope>"
                    + "<UserExist>N</UserExist><Locale>en-us</Locale><ParticipantType>U</ParticipantType>"
                    + "</Participant><Hook>No</Hook></WMConnect_Input>";
            String outputXml = ngejbclient.makeCall(inputXml);
            //parse the output XML             
            //SessionID is returned in response 
           String sessionId=null;
          // String date=null;
                    Logging.writeConsoleLog(objIForm.getCabinetName(), "out put session"+outputXml);

            System.out.println(outputXml);
            xmlParser = new XMLParser();
            xmlParser.setInputXML(outputXml);
            if (xmlParser.getValueOf("MainCode").equalsIgnoreCase("0")) {
                sessionId = xmlParser.getValueOf("SessionId");
                Logging.writeConsoleLog(objIForm.getCabinetName(), "Session ID is"+sessionId);

                System.out.println("Session ID is:-"+sessionId);
            } else {
       Logging.writeConsoleLog(objIForm.getCabinetName(), "Some Error occured in getting the session ID");

        System.out.println("Some Error occured in getting the session ID");
            }  
            
             String Query = "SELECT aadhar_no,address,email,name,pan_no,phone,id FROM EMP_SYSTEM1 where flage='Y';";

            Logging.writeConsoleLog(objIForm.getCabinetName(), "Query*******" + Query);

            List<List> result = objIForm.getDataFromDB(Query);
            
          
            Logging.writeConsoleLog(objIForm.getCabinetName(), "Result****" + result);
            
            List<List> finalList=new ArrayList<>();
            for(int i=0;i<result.size();i++){
                
                     if(result.get(i).get(1).equals(result.get(0).get(1))){
                         finalList.add(result.get(i));
                     
                }
               
            }
              if (finalList != null && finalList.size() > 0) {

                JSONArray poDetails = new JSONArray();

                for (int i = 0; i < finalList.size(); i++) {
                   String id= finalList.get(i).get(6).toString();

            String WF2  = "<?xml version=\"1.0\"?><WFUploadWorkItem_Input><Option>WFUploadWorkItem</Option>"
                    + "<EngineName>mresultdev</EngineName>"
            		+ "<SessionId>"+sessionId+"</SessionId>"
                    +  "<ProcessDefId>"+processDefId+"</ProcessDefId>"
                   // + "<QueueId>" + 158 + "</QueueId>"
                   // +"<InitiateFromActivityName>Kyc_Process_Start Event_1</InitiateFromActivityName>"
                    + "<DataDefName></DataDefName>"
                    +"<Fields></Fields>"
                    + "<InitiateAlso>Y</InitiateAlso>"
                     +"<VariantId>0</VariantId>"
                    + "<UserDefVarFlag>Y</UserDefVarFlag>"
                     +"<Attributes>"
                    +"<Name>"+finalList.get(i).get(3).toString()+"</Name>"
                     +"<Address>"+finalList.get(i).get(1).toString()+"</Address>"
                    +"<Email>"+finalList.get(i).get(2).toString()+"</Email>"
                     +"<aadhar>"+finalList.get(i).get(0).toString()+"</aadhar>"
                    +"<pan>"+finalList.get(i).get(4).toString()+"</pan>"
                      +"<Phone>"+finalList.get(i).get(5).toString()+"</Phone>"
                    +"</Attributes>"
                    +"<Documents>"
                     +"</Documents>"
                    +"</WFUploadWorkItem_Input>";
            
            String wfUploadWorkItemOutputXML2=ngejbclient.makeCall(WF2);
       Logging.writeConsoleLog(objIForm.getCabinetName(), ""+wfUploadWorkItemOutputXML2);

            System.out.println(wfUploadWorkItemOutputXML2);
           
            
            xmlParser = new XMLParser();
            xmlParser.setInputXML(wfUploadWorkItemOutputXML2);
            if (xmlParser.getValueOf("MainCode").trim().equals("0")) {
                String processId = xmlParser.getValueOf("ProcessInstanceId");
           Logging.writeConsoleLog(objIForm.getCabinetName(), "Workitem ID"+processId);
                
                System.out.println("Workitem ID :---"+processId);
                 String Query1= "update emp_system1 set flage='N'  where id ='" + id + "'";
                       int saveDataInDB = objIForm.saveDataInDB(Query1);
                        Logging.writeConsoleLog(objIForm.getCabinetName(), "rows updated"+saveDataInDB);
            } else {
    Logging.writeConsoleLog(objIForm.getCabinetName(), "Some error occured while uploading workitem");

               System.out.println("Some error occured while uploading workitem");
            }
                }
              }
               Logging.writeConsoleLog(objIForm.getCabinetName(), "---------------Disconnect --------");

            System.out.println("-------------------Disconnect ---------------------");
            //Get the session id from the outputXML 
            String disconnectXml="<?xml version=\"1.0\"?> <WMDisConnect_Input> <Option>WMDisConnect</Option> <EngineName>mresultdev</EngineName> <SessionID>"+sessionId+"</SessionID> <UnlockWorkitem></UnlockWorkitem> </WMDisConnect_Input>";
            
            String disoutputXml = ngejbclient.makeCall(disconnectXml);
            //parse the output XML             
            //SessionID is returned in response 
            Logging.writeConsoleLog(objIForm.getCabinetName(), ""+disoutputXml);

            System.out.println(disoutputXml);
            xmlParser = new XMLParser();
            xmlParser.setInputXML(disoutputXml);
            if (xmlParser.getValueOf("MainCode").equalsIgnoreCase("0")) {
                            Logging.writeConsoleLog(objIForm.getCabinetName(), "Session Disconnected");

                System.out.println("Session Disconnected");
            } else {
                Logging.writeConsoleLog(objIForm.getCabinetName(), "Some Error occured while disconnecting the session");

                 System.out.println("Some Error occured while disconnecting the session");
            } 

                } catch (Exception e) {
			// TODO: handle exception
		}
                objIForm.setValue("textbox284", "WorkItem created");

                
                  }
}
