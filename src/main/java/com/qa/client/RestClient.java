package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//  1. GET method
	
	public  CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
	{
		
		//1.GET Method without headers
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);  //http get request
		CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpget);  //hit the GET url
		return closeableHttpResponse;

		
	}
		
		
	//2.GET Method with headers
	public  CloseableHttpResponse get(String url , HashMap<String, String> headermap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);  //http get request
		for(Map.Entry<String, String> entry : headermap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpget);  //hit the GET url
		return closeableHttpResponse;
	}
		
		
	//3.POST METHOD
	
	public CloseableHttpResponse post(String url, String entitystring , HashMap<String, String> headermap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);  //post request
		httppost.setEntity(new StringEntity(entitystring)); //for payload
		
		//for headers
		
		for(Map.Entry<String, String> entry : headermap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse = httpclient.execute(httppost);  //hit the GET url
		return closeableHttpResponse;
				
	}
	
	
	public CloseableHttpResponse post_nobody(String url, HashMap<String, String> headermap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);  //post request
		
		//for headers
		
		for(Map.Entry<String, String> entry : headermap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse = httpclient.execute(httppost);  //hit the GET url
		return closeableHttpResponse;
				
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	


