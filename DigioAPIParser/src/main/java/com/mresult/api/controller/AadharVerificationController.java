package com.mresult.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mresult.api.entity.Response_Entity;
import com.mresult.api.service.Request_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
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
        try {
            String response = sendPostRequest(aadhar, customerIdentifier, templateName);
            if (response != null) {
                // Save the response into the database
                Response_Entity request = mapJsonToCustomerRequest(response);
                service.saveRequest(request);
            }
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException e) {
            return handleHttpClientErrorException(e);
        } catch (Exception e) {
            return handleGeneralException(e);
        }
    }

    private String sendPostRequest(String aadhar, String customerIdentifier, String templateName) {
        String url = "https://ext.digio.in:444/client/kyc/v2/request/with_template";

        String jsonInputString = String.format("{\n" +
                "    \"request_details\": {\n" +
                "        \"aadhar number\": \"%s\"\n" +
                "    },\n" +
                "    \"customer_identifier\": \"%s\",\n" +
                "    \"customer_name\": \"\",\n" +
                "    \"template_name\": \"%s\"\n" +
                "}", aadhar, customerIdentifier, templateName);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", AUTH_KEY);

            HttpEntity<String> requestEntity = new HttpEntity<>(jsonInputString, headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return responseEntity.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.err.println("Error: " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while sending the POST request", e);
        }
    }

    private Response_Entity mapJsonToCustomerRequest(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);

            Response_Entity request = new Response_Entity();
            request.setId(jsonNode.get("id").asText());
            request.setStatus(jsonNode.get("status").asText());
            request.setCustomerIdentifier(jsonNode.get("customer_identifier").asText());
            request.setReferenceId(jsonNode.get("reference_id").asText());
            request.setTransactionId(jsonNode.get("transaction_id").asText());
            request.setCustomerName(jsonNode.get("customer_name").asText());
            request.setExpireInDays(jsonNode.get("expire_in_days").asInt());
            request.setReminderRegistered(jsonNode.get("reminder_registered").asBoolean());
            request.setWorkflowName(jsonNode.get("workflow_name").asText());
            request.setAutoApproved(jsonNode.get("auto_approved").asBoolean());
            request.setTemplateId(jsonNode.get("template_id").asText());

            JsonNode accessTokenNode = jsonNode.get("access_token");
            Response_Entity.AccessToken accessToken = new Response_Entity.AccessToken();
            accessToken.setEntityId(accessTokenNode.get("entity_id").asText());
            accessToken.setAccessTokenId(accessTokenNode.get("id").asText());
            accessToken.setValidTill(LocalDateTime.parse(accessTokenNode.get("valid_till").asText().replace(" ", "T")));
            accessToken.setCreatedAt(LocalDateTime.parse(accessTokenNode.get("created_at").asText().replace(" ", "T")));

            request.setAccessToken(accessToken);

            JsonNode requestDetailsNode = jsonNode.get("request_details");
            Response_Entity.Request_details requestDetail = new Response_Entity.Request_details();
            requestDetail.setAadhar_number(requestDetailsNode.get("aadhar number").asText());
            request.setRequestDetails(requestDetail);

            return request;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while mapping JSON to the response entity", e);
        }
    }

    @GetMapping("/getAllData")
    public ResponseEntity<List<Response_Entity>> getAllResponses() {
        List<Response_Entity> responses = service.getAllData();
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/kyc-response/{requestId}")
    public ResponseEntity<String> getKYCResponse(@PathVariable("requestId") String requestId) {
        try {
            String url = String.format(DIGIO_API_URL_KYCReponse, requestId);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", AUTH_KEY);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException e) {
            return handleHttpClientErrorException(e);
        } catch (ResourceAccessException e) {
            return handleResourceAccessException(e);
        } catch (Exception e) {
            return handleGeneralException(e);
        }
    }

    @PostMapping("/manage-approval/{requestId}")
    public ResponseEntity<String> manageApproval(@PathVariable String requestId, @RequestParam String status) {
        try {
            String url = String.format(DIGIO_API_URL_Manager, requestId);
            String jsonBody = String.format("{ \"status\": \"%s\" }", status);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", AUTH_KEY);

            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (HttpClientErrorException e) {
            return handleHttpClientErrorException(e);
        } catch (Exception e) {
            return handleGeneralException(e);
        }
    }

    // Exception handler for HTTP client errors (e.g., 400 or 404 errors)
    private ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException e) {
    	
    	System.err.println("HttpClientErrorException Occured...");
    	
    	return ResponseEntity.status(e.getStatusCode())
                .body("Client Error: " + e.getResponseBodyAsString());
    }
    
    // Exception handler for Resource Access Exception (e.g., timeouts, connectivity issues)
    private ResponseEntity<String> handleResourceAccessException(ResourceAccessException e) {
        System.err.println("ResourceAccessException Occured... Please Try to change your Network");
    	return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("Resource Access Error: " + e.getMessage());
    }

    // General exception handler
    private ResponseEntity<String> handleGeneralException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal Server Error: " + e.getMessage());
    }
}
