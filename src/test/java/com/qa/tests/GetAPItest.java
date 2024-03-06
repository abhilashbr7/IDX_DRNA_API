package com.qa.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPItest extends TestBase{
	TestBase testbase;
	RestClient restclient;
	String serviceurl;
	String apiurl;
	String url;
	CloseableHttpResponse closeableHttpResponse;
	
	
	@BeforeMethod

	public void setup() throws ClientProtocolException, IOException
	{
		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		apiurl  = prop.getProperty("ServiceURL");
		url = serviceurl + apiurl;
		
		
	}
	
	
	//1.GET Method
	@Test
	public void getAPITest_withoutheaders() throws ClientProtocolException, IOException
	{
		restclient = new RestClient();
		closeableHttpResponse = restclient.get(url);
		
		//1.GET Status Code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" +statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");
				
		//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" +responseJson);
		String s = TestUtil.getValueByjpath(responseJson, "/per_page");
		System.out.println(s);
		Assert.assertEquals(Integer.parseInt(s), 6);
		
		//To get value from JSON Array
		String lastname = TestUtil.getValueByjpath(responseJson, "/data[0]/last_name");
		System.out.println("last name is : " + lastname);
	    			
		//3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String,String> allHeaders = new HashMap<String,String>();
				
		for(Header header : headersArray)
		{
		  allHeaders.put(header.getName(), header.getValue());

		}
	}
	
	
   @Test
   public void getAPITest_withheaders() throws ClientProtocolException, IOException
	{
		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		//headermap.put("username", "abhilashbr7@gmail.com");
		closeableHttpResponse = restclient.get(url,headermap);
		
		//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" +statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");
				
		//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" +responseJson);
		String s = TestUtil.getValueByjpath(responseJson, "/per_page");
		System.out.println(s);
		Assert.assertEquals(Integer.parseInt(s), 6);
		
		//To get value from JSON Array
		String lastname = TestUtil.getValueByjpath(responseJson, "/data[0]/last_name");
		System.out.println("last name is : " + lastname);
	    			
		//3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String,String> allHeaders = new HashMap<String,String>();
				
		for(Header header : headersArray)
		{
		  allHeaders.put(header.getName(), header.getValue());

		}
	}
   
   
   
   
   
   
   
   
   
   
   
   
   
}
