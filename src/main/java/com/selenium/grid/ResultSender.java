package com.selenium.grid;

import io.restassured.RestAssured;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultSender {

    private static final ObjectMapper OM = new ObjectMapper();
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String ELASTICSEARCH_URL = "http://localhost:9200/app/suite"; 
    
    
    @Test
    public static void send(final TestStatus testStatus){
    	 RestAssured.baseURI = "http://localhost:9200/app/suite";
     	System.out.println("In Result Sender");
     	
     	CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(ELASTICSEARCH_URL);
        String JSON_STRING = null;
		try {
			JSON_STRING = OM.writeValueAsString(testStatus);
			
			System.out.println("JSON_STRING-->"+JSON_STRING);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        HttpEntity stringEntity = new StringEntity(JSON_STRING,ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response2=null;
        try {
			 response2= httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            System.out.println("Status-->"+response2.getStatusLine());
    }
    
}
