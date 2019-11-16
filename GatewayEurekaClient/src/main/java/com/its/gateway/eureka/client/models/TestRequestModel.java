package com.its.gateway.eureka.client.models;

import java.io.Serializable;
import java.util.Date;

public class TestRequestModel implements Serializable {
	private String data;
	private String nodeName;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
}
