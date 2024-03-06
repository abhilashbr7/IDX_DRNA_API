package com.qa.data;

public class AgencySave {
	
	String Request;
	String Response;

	public AgencySave() {
		
	}
	
	public AgencySave(String Request,String Response)
	{
		this.Request = Request;
		this.Response = Response;

	}

	public String Request() {
		return Request;
	}

	public void setRequest(String Request) {
		this.Request = Request;
	}

	public String Response() {
		return Response;
	}

	public void setResponse(String Response) {
		this.Response = Response;
	}

}
