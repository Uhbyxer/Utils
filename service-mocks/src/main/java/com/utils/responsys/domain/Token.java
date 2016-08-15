package com.utils.responsys.domain;


public class Token {
	private String authToken;
	
	private String issuedAt;
	
	private String endPoint;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(String issuedAt) {
		this.issuedAt = issuedAt;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public String toString() {
		return "Token [authToken=" + authToken + ", issuedAt=" + issuedAt + ", endPoint=" + endPoint + "]";
	}
	
}
