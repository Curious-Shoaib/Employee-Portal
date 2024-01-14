package com.myApp.responseData;


public class DemandResponse {
	
	Integer demandId;
	String technology;
	
	public DemandResponse()
	{
		
	}
	public DemandResponse(Integer demandId,String technology) {
		super();
		this.demandId = demandId;
		this.technology = technology;
	}
	
}
