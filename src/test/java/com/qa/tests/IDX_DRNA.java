package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.IExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.AgencySave;
import com.qa.data.Users;
import com.qa.util.TestUtil;

public class IDX_DRNA extends TestBase implements IExecutionListener {

	TestBase testbase;
	RestClient restclient;
	String serviceurl;
	String apiurl;
	String url;
	String token,credentials,Searchreasonbyinputparameter,CitizenSearch,CitizenSearchTickets,CitizenSearchTicketTransaction;
	String SearchReasonByClient,CitizenSearchShipRenewal,CitizenSearchShipFees,CitizenDetails,CitizenRetryAgency;
	String citizen_referenceId,citizen_referenceId_tickets,citizen_referenceId_ticket_transaction,citizen_referenceId_ShipRenewal;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod

	public void setup() throws ClientProtocolException, IOException {
		testbase = new TestBase();
		serviceurl = prop.getProperty("URL");
		credentials = prop.getProperty("credentials");
		credentials = serviceurl + credentials;
		SearchReasonByClient = prop.getProperty("SearchReasonByClient");
		SearchReasonByClient = serviceurl + SearchReasonByClient;
		Searchreasonbyinputparameter = prop.getProperty("Searchreasonbyinputparameter");
		Searchreasonbyinputparameter = serviceurl + Searchreasonbyinputparameter;
		CitizenSearch = prop.getProperty("CitizenSearch");
		CitizenSearch = serviceurl + CitizenSearch;
		CitizenSearchTicketTransaction = prop.getProperty("CitizenSearch");
		CitizenSearchTicketTransaction = serviceurl + CitizenSearchTicketTransaction;
		CitizenSearchShipRenewal = prop.getProperty("CitizenSearch"); // Get call
		CitizenSearchShipRenewal = serviceurl + CitizenSearchShipRenewal; // Get call
		CitizenSearchShipFees = prop.getProperty("CitizenSearch");
		CitizenSearchShipFees = serviceurl + CitizenSearchShipFees;
		CitizenDetails = prop.getProperty("CitizenDetails");
		CitizenDetails = serviceurl + CitizenDetails;
		CitizenRetryAgency = prop.getProperty("CitizenAgency");
		CitizenRetryAgency = serviceurl + CitizenRetryAgency;
		
	}
	@Test(priority = 1)
	public void Credentilas() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
//		headermap.put("username", "idealQA");
//		headermap.put("password", "%C922Yj0");
//		headermap.put("client_secret", "1orlfkcif7e15iet49mg1aojvrg5ovq6acmr8ldpdo6p7998neg3");
//		headermap.put("client_id", "6b6iij617t0mrnb1g5v6vua93u");
//		headermap.put("x-api-key", "RT3xMZkRjm5lwbjbV4iO86Izfl8JpZ29qPfvR5eb");

		String jsonpayload = "{\r\n"
				+ "    \"Username\": \"drnadev\",\r\n"
				+ "    \"Password\": \"mS1VAud5^8U9HzIz\",\r\n"
				+ "    \"APIKey\": \"9344f18e-33a2-4d17-93d1-f7a9eb23f10e\",\r\n"
				+ "    \"Email\": \"mayanka@yahoo.com\"\r\n"
				+ "}";
		

		closeableHttpResponse = restclient.post(credentials, jsonpayload, headermap);


//			//1.GET status code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

//			//2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

//			//To get value from JSON Array
		token = TestUtil.getValueByjpath(responseJson, "/access_token");
		System.out.println("Token is : " + token);
//		    			
//			//3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}

	@Test(priority = 2)
	public void SearchReasonByClient() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		headermap.put("isCitizen", "true");
		headermap.put("isCorporate", "false");
		headermap.put("isIDValidation", "false");
		headermap.put("Authorization", "Bearer " + token);
		

		closeableHttpResponse = restclient.get(SearchReasonByClient, headermap);

		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		// //2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
		String SearchReasonId = TestUtil.getValueByjpath(responseJson, "/searchReasons[0]/id");
		System.out.println("Response From Json is : " + SearchReasonId);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
		allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}

	@Test(priority = 3)
	public void Searchreasonbyinputparameter() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		headermap.put("searchReasonId", "10195");
		headermap.put("searchType", "CITIZEN");
		headermap.put("Authorization", "Bearer " + token);
		

		closeableHttpResponse = restclient.get(Searchreasonbyinputparameter, headermap);

		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		// //2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
		String InputParametername = TestUtil.getValueByjpath(responseJson, "/parameters[0]/name");
		System.out.println("Tickets Response From Json is : " + InputParametername);
		String InputParameterrequired = TestUtil.getValueByjpath(responseJson, "/parameters[0]/required");
		System.out.println("Tickets Response From Json is : " + InputParameterrequired);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
		allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}

	@Test(priority = 4)
	public void CitizenSearchShipOwn() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Bearer " + token);
		

		String jsonpayload = "{\r\n"
				+ "  \"searchReasonId\": 10195,\r\n"
				+ "  \"ssn\": \"581-80-9555\",\r\n"
				+ "  \"registrationNumber\": \"PR0566EE\"\r\n"
				+ "}";
		

		closeableHttpResponse = restclient.post(CitizenSearch, jsonpayload, headermap);

		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		// //2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
		String citizen_referenceId = TestUtil.getValueByjpath(responseJson, "/citizen_referenceId");
		System.out.println("Fee Response From Json is : " + citizen_referenceId);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
		allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 5)
	public void CitizenSearchTickets() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Bearer " + token);
		
		String jsonpayload = "{\r\n"
				+ "  \"searchReasonId\": 10196,\r\n"
				+ "  \"registrationNumber\": \"PR0033EE\"\r\n"
				+ "}";
		

		closeableHttpResponse = restclient.post(CitizenSearch, jsonpayload, headermap);

		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		// //2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
		String citizen_referenceId_tickets = TestUtil.getValueByjpath(responseJson, "citizen_referenceId");
		System.out.println("Ticket Transaction message Response From Json is : " + citizen_referenceId_tickets);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
		allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}

	@Test(priority = 6)
	public void CitizenSearchTicketTransaction() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Bearer " + token);
		
		String jsonpayload = "{\r\n"
				+ "  \"searchReasonId\": 10197,\r\n"
				+ "  \"registrationNumber\": \"PR0566EE\",\r\n"
				+ "  \"transactionDate\": \"2024-01-31\",\r\n"
				+ "  \"receiptNumber\": \"0000000\",\r\n"
				+ "  \"amount\": 5.00,\r\n"
				+ "  \"UserId\":\"1234567\",\r\n"
				+ "  \"TransactionNumber\": \"3356234\",\r\n"
				+ "  \"TicketNumber\":\"1349992\"\r\n"
				+ "}";
		

		closeableHttpResponse = restclient.post(CitizenSearchTicketTransaction, jsonpayload, headermap);

		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		// //2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
		String citizen_referenceId_ticket_transaction = TestUtil.getValueByjpath(responseJson, "/citizen_referenceId");
		System.out.println("Ticket Transaction message Response From Json is : " + citizen_referenceId_ticket_transaction);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
		allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 7)
	public void CitizenSearchShipRenewal() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Bearer " + token);
		
		String jsonpayload = "{\r\n"
				+ "  \"searchReasonId\": 10198,\r\n"
				+ "  \"registrationNumber\": \"PR0566EE\",\r\n"
				+ "  \"transactionDate\": \"2024-01-31\",\r\n"
				+ "  \"shipPickup\": \"Y\",\r\n"
				+ "  \"mailingAddressLine1\": \"123\",\r\n"
				+ "  \"mailingAddressLine2\": \"123\",\r\n"
				+ "  \"mailingCity\": \"San Juan\",\r\n"
				+ "  \"mailingZip\": \"12345\",\r\n"
				+ "  \"ResidentialAddressLine1\": \"123\",\r\n"
				+ "  \"ResidentialAddressLine2\": \"123\",\r\n"
				+ "  \"ResidentialCity\": \"lARES\",\r\n"
				+ "  \"ResidentialZip\": \"12123\",\r\n"
				+ "  \"receiptNumber\": \"0000000\",\r\n"
				+ "  \"amount\": 5.00,\r\n"
				+ "  \"Email\": \"email@email.com\",\r\n"
				+ "  \"UserId\": \"1394892\",\r\n"
				+ "  \"transactionCode\": \"28937291\"\r\n"
				+ "}";
		

		closeableHttpResponse = restclient.post(CitizenSearchShipRenewal, jsonpayload, headermap);

		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		// //2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
		String citizen_referenceId_ShipRenewal = TestUtil.getValueByjpath(responseJson, "/citizen_referenceId");
		System.out.println("Ticket Transaction message Response From Json is : " + citizen_referenceId_ShipRenewal);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
		allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 8)
	public void CitizenSearchShipFees() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		headermap.put("Authorization", "Bearer " + token);
		
		String jsonpayload = "{\r\n"
				+ "  \"searchReasonId\": 10199\r\n"
				+ "}";
		

		closeableHttpResponse = restclient.post(CitizenSearchShipFees, jsonpayload, headermap);

		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		// //2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
		String citizen_referenceId_Shipfees = TestUtil.getValueByjpath(responseJson, "/citizen_referenceId");
		System.out.println("Ticket Transaction message Response From Json is : " + citizen_referenceId_ShipRenewal);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
		allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 9)
	public void CitizenDetails() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		headermap.put("referenceId", "7853d193-3ff8-4080-b2a3-71d713bb63fc");
		headermap.put("Authorization", "Bearer " + token);
		
		closeableHttpResponse = restclient.get(CitizenDetails,headermap);

		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		// //2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);

//		// To get value from JSON Array
		String citizen_AgencyName = TestUtil.getValueByjpath(responseJson, "/data[0]/Agency");
		System.out.println("Ticket Transaction message Response From Json is : " + citizen_AgencyName);

		// 3.All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {
		allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Array-->" + allHeaders);

	}
	
	@Test(priority = 10)
	public void CitizenRetryAgency() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("content-type", "application/json");
		headermap.put("referenceId", "7853d193-3ff8-4080-b2a3-71d713bb63fc");
		headermap.put("agencyCode", "DRNA");
		headermap.put("Authorization", "Bearer " + token);

		
		closeableHttpResponse = restclient.post_nobody(CitizenRetryAgency,headermap);

		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code:" + statuscode);
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status code is not 200");

		// //2.Json String
		String responsestring = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responsestring);
		System.out.println("JSON response from API---->" + responseJson);
	}

}
