package org.example;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class CurrencyConverter {
    public static void main(String[] args){
        HashMap<Integer,String> currencyCodes=new HashMap<Integer, String>();
        // add currency codes
        currencyCodes.put(1,"USD");
        currencyCodes.put(2,"CAD");
        currencyCodes.put(3,"INR");
        currencyCodes.put(4,"HKD");

        String fromCode, toCode;
        double amount;
        Scanner sc=new Scanner(System.in);
        System.out.println("welcome to currency converter ( us dollars to indian rupees )");
//
//        System.out.println("Currency converting from :");
//        System.out.println("1:USD \t 2:CAD \t 3:INR\t 4:HKD");
//        fromCode=currencyCodes.get(sc.nextInt());
//
//        System.out.println("Currency converting to :");
//        System.out.println("1:USD \t 2:CAD \t 3:INR\t 4:HKD");
//        toCode=currencyCodes.get(sc.nextInt());
        amount=sc.nextFloat();



//        sendHttpGETRequest(fromCode,toCode,amount);
      sendHttpRequest(amount);
        System.out.println("thanks for using the currency converter");








    }

    private static void sendHttpRequest( double amount){
        try {
            // Specify the API URL
            String apiUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_nGkEHqoLhJ3cIIinUtBSWMzHbhxUVU4n1MO3rxcE&currencies=INR";

            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Check if the request was successful (HTTP 200 OK)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response from the API
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                // Read the response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Extract the value you need (INR in this case)
                double inrValue = jsonResponse.getJSONObject("data").getDouble("INR");

                System.out.println("INR Value: per dollar is  " + inrValue);
                DecimalFormat numberFormat = new DecimalFormat("#.00");
                System.out.println("total number of rupees from the amount of dollars in : " + numberFormat.format(inrValue*amount)+"Rupees");
            } else {
                System.out.println("Error: HTTP request failed with response code " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
