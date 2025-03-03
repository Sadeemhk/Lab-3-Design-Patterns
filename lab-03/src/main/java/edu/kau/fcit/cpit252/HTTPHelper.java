package edu.kau.fcit.cpit252;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.List;

public class HTTPHelper {
    public static HttpResponse<String> sendGet(URI uri){
        try {
            // create a client
            HttpClient httpClient = HttpClient.newHttpClient();
            // create an HTTP GET request
            HttpRequest request = HttpRequest.newBuilder(uri)
                    .GET()
                    .header("accept", "application/json")
                    .build();
            // Get the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            // Test if the response from the server is successful
            if (response.statusCode() !=200) {
                System.err.println(response.statusCode());
                System.err.println(response.body());
                return null;
            }
            return response;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List <T> parseIntoCollection(String responseString,
                                                   Class<? extends Collection> collectionClass,
                                                   Class<?> elementClass){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = null;
        try {
            json = objectMapper.readTree(responseString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        try {
            return objectMapper.treeToValue(json.get("results"), typeFactory.constructCollectionType(collectionClass, elementClass));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
