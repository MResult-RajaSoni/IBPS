package com.newgen.template.common.methods;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

//
///**
// *
// * @author Raja Soni
// */

public class GetProbabilityMethod{
    

        private String processApi(String cibilScore, String loanTerm) throws Exception {
          // Create the request JSON dynamically
          String jsonInputString = String.format(
                  "{\"id\":\"batch-001\",\"requests\":[{\"id\":\"record-001\",\"arguments\":{\"source2__cibil_score\":\"%s\",\"source2__loan_term\":\"%s\"}}]}",
                  cibilScore, loanTerm);

          System.out.println("Request JSON: " + jsonInputString);

          // URL of the API
          URL url = new URL("https://nai-api.newgensoftware.net/openscoring/model/loan_harshal_gidh_mresult_com_66e975e52101f1588bedc79b_V1_randomForestClassification_SegmentConditionNo_SegmentclusterIndexTest_Set_Result/batch");
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();

          // Set request method to POST
          connection.setRequestMethod("POST");

          // Set Content-Type to application/json
          connection.setRequestProperty("Content-Type", "application/json");

          // Enable input and output streams
          connection.setDoOutput(true);

          // Send the JSON input
          try (OutputStream os = connection.getOutputStream()) {
              byte[] input = jsonInputString.getBytes("utf-8");
              os.write(input, 0, input.length);
          }

          // Check response code
          int responseCode = connection.getResponseCode();
          System.out.println("Response Code: " + responseCode);

          // Read the response
          StringBuilder response = new StringBuilder();
          try (BufferedReader br = new BufferedReader(new InputStreamReader(
                  responseCode >= 200 && responseCode < 300 ? connection.getInputStream() : connection.getErrorStream(), "utf-8"))) {
              String responseLine;
              while ((responseLine = br.readLine()) != null) {
                  response.append(responseLine.trim());
              }
          }

          return extractMaxProbability(response.toString());
      }

        private static String extractMaxProbability(String responseJson) {
        double maxProbability = 0.0;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseJson);

            // Navigate to the desired values
            double probability0 = rootNode
                    .path("responses")
                    .get(0)
                    .path("results")
                    .path("probability(0.0)")
                    .asDouble();

            double probability1 = rootNode
                    .path("responses")
                    .get(0)
                    .path("results")
                    .path("probability(1.0)")
                    .asDouble();

            // Determine the max probability
            maxProbability = Math.max(probability0, probability1);

            // Print the probabilities
            System.out.println("Probability(0.0): " + probability0);
            System.out.println("Probability(1.0): " + probability1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Convert to percentage and format
        double percentage = maxProbability * 100;
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(percentage);
    }

}