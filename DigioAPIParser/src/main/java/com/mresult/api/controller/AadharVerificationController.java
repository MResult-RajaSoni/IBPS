package com.mresult.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mresult.api.entity.Response_Entity;
import com.mresult.api.service.Request_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")  // Allow All origins
@RequestMapping("/api")
public class AadharVerificationController {

    @Autowired
    private Request_Service service;
    
    private static final String AUTH_KEY = "Basic QUNLMjQwOTI0MTcxODU1MTc5WkZERlFQSkJONTJIM1A6TzJXTEFKSzQzNjk2U05TQ1lYM1VXV0NGRklDU0VFMko=";

    private static final String DIGIO_API_URL_KYCReponse = "https://ext.digio.in:444/client/kyc/v2/%s/response";
    
    private static final String DIGIO_API_URL_Manager = "https://ext.digio.in:444/client/kyc/v2/request/%s/manage_approval";

    @PostMapping("/verify-aadhar")
    public ResponseEntity<String> verifyAadhar(@RequestParam String aadhar,
                                               @RequestParam String customerIdentifier,
                                               @RequestParam String templateName) {
        String response = sendPostRequest(aadhar, customerIdentifier, templateName);
        if (response != null) {
            // Save the response into the database
        	Response_Entity request = mapJsonToCustomerRequest(response);
//            request.setFullJsonResponse(response); // Set the full JSON response as a string
            service.saveRequest(request);
        }
        return ResponseEntity.ok(response);
    }

    private String sendPostRequest(String aadhar, String customerIdentifier, String templateName) {
        String url = "https://ext.digio.in:444/client/kyc/v2/request/with_template";

        // Construct the JSON payload
        String jsonInputString = String.format("{\n" +
                "    \"request_details\": {\n" +
                "        \"aadhar number\": \"%s\"\n" +
                "    },\n" +
                "    \"customer_identifier\": \"%s\",\n" +
                "    \"customer_name\": \"\",\n" +
                "    \"template_name\": \"%s\"\n" +
                "}", aadhar, customerIdentifier, templateName);

        // Debugging: Print the JSON payload
        System.out.println("JSON Payload: " + jsonInputString);

        try {
            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", AUTH_KEY);

            // Create the request entity
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonInputString, headers);

            // Use RestTemplate to send the request
            RestTemplate restTemplate = new RestTemplate();

            try {
                ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
                System.out.println("Response Entity: " + responseEntity.toString());
                return responseEntity.getBody();
            } catch (HttpClientErrorException e) {
                System.err.println("Error response: " + e.getResponseBodyAsString());
                throw e;  // Rethrow or handle as needed
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
    private Response_Entity mapJsonToCustomerRequest(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);

            Response_Entity request = new Response_Entity();
            request.setId(jsonNode.get("id").asText());
//            request.setCreatedAt(jsonNode.get("created").asText());
            request.setStatus(jsonNode.get("status").asText());
            request.setCustomerIdentifier(jsonNode.get("customer_identifier").asText());
            request.setReferenceId(jsonNode.get("reference_id").asText());
            request.setTransactionId(jsonNode.get("transaction_id").asText());
            request.setCustomerName(jsonNode.get("customer_name").asText());
//            request.setAadhar_number(jsonNode.get("aadhar number").asText());
            request.setExpireInDays(jsonNode.get("expire_in_days").asInt());
            request.setReminderRegistered(jsonNode.get("reminder_registered").asBoolean());
            request.setWorkflowName(jsonNode.get("workflow_name").asText());
            request.setAutoApproved(jsonNode.get("auto_approved").asBoolean());
            request.setTemplateId(jsonNode.get("template_id").asText());

            // Map AccessToken
            JsonNode accessTokenNode = jsonNode.get("access_token");
            Response_Entity.AccessToken accessToken = new Response_Entity.AccessToken();
            accessToken.setEntityId(accessTokenNode.get("entity_id").asText());
            accessToken.setAccessTokenId(accessTokenNode.get("id").asText());
            accessToken.setValidTill(LocalDateTime.parse(accessTokenNode.get("valid_till").asText().replace(" ", "T")));
            accessToken.setCreatedAt(LocalDateTime.parse(accessTokenNode.get("created_at").asText().replace(" ", "T")));

            request.setAccessToken(accessToken);
            
            //Map request_details
            JsonNode requestDetailsnode = jsonNode.get("request_details");
            Response_Entity.Request_details requestDetail = new Response_Entity.Request_details();
            
            requestDetail.setAadhar_number(requestDetailsnode.get("aadhar number").asText());
            
            System.out.println("Adhar is "+ requestDetail.getAadhar_number());
            
            request.setRequestDetails(requestDetail);

            return request;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//-------- API for getting All Data Saved in Local Database -----------
    @GetMapping("/getAllData")
    public ResponseEntity<List<Response_Entity>> getAllResponses() {
        List<Response_Entity> responses = service.getAllData();
        return ResponseEntity.ok(responses);
    }

//--------  API for getting Data by RequestID from Digio API (Like KID241001113112544TFZF7I5DNKQ6JM) --------
    @CrossOrigin(origins = "http://localhost:3000") // Allow specific frontend origin
    @PostMapping("/kyc-response/{requestId}")
    public ResponseEntity<String> getKYCResponse(@PathVariable("requestId") String requestId) {
        try {
        	
        	System.out.println("Inside getKYCResponse");
            // Build the complete API URL
            String url = String.format(DIGIO_API_URL_KYCReponse, requestId);
            System.out.println("Inside getKYCResponse---1");
            // Set up headers with the authorization key
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", AUTH_KEY);
            headers.set("Content-Type", "application/json");
            System.out.println("Inside getKYCResponse--");
            // Create an HttpEntity with the headers
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Use RestTemplate to send the GET request to the Digio API
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Return the response from the Digio API
            return ResponseEntity.ok(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred while fetching KYC response");
        }
    }

//------ API for Manager Approval or reject 
    
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/manage-approval/{requestId}")
    public ResponseEntity<String> manageApproval(@PathVariable String requestId, @RequestParam String status) {
       
    	 System.out.println("manageApproval ____");
         
    	String url = String.format(DIGIO_API_URL_Manager, requestId);

        System.out.println("manageApproval --1");
        // Create JSON request body
        String jsonBody = String.format("{ \"status\": \"%s\" }", status);

        System.out.println("manageApproval --2"+ jsonBody);
        
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", AUTH_KEY);
        System.out.println("manageApproval --4");
        
        // Create request entity
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

        // Call external API using RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return ResponseEntity.ok(response.getBody());  // Return API response
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred: " + e.getMessage());
        }
    }

    
}
