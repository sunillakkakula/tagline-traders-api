package com.vtt.apps.util;

import org.json.JSONObject;

public class CustomResponse {

	
	private String responseCode;
	private String responseMessage;
	private org.json.JSONObject responseData;

	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public org.json.JSONObject  getResponseData() {
		return responseData;
	}
	public void setResponseData(org.json.JSONObject responseData) {
		this.responseData = responseData;
	}
	
	@Override
	public String toString() {
		return "CustomResponse [responseCode=" + responseCode + ", responseMessage=" + responseMessage
				+ ", responseData=" + responseData + "]";
	}
	
}
