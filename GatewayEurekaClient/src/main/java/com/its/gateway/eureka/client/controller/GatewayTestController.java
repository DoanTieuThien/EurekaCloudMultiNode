package com.its.gateway.eureka.client.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.its.gateway.eureka.client.models.ResponseModel;
import com.its.gateway.eureka.client.models.TestRequestModel;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

@RestController
@CrossOrigin("*")
@RequestMapping("/gateway-test")
public class GatewayTestController {

	@Autowired
	private EurekaClient eurekaClient = null;

	@PostMapping("/test")
	public ResponseModel testEurekaClient(@RequestBody TestRequestModel in) {
		ResponseModel response = new ResponseModel();

		try {
			// get bussiness node
			Application application = eurekaClient.getApplication("BussinessNode-1".toUpperCase());
			// get instance list
			List<InstanceInfo> instanceInfoList = application.getInstances();
			String url = "";

			// get random BU let make query
			int urlSize = instanceInfoList.size();
			if (urlSize > 0) {
				Random random = new Random();
				int index = random.nextInt(urlSize);
				url = instanceInfoList.get(index).getHomePageUrl() + "bussiness-test/test";
			}

			RestTemplate restemplate = new RestTemplate();
			HttpHeaders httpHeaders = new HttpHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			HttpEntity<ResponseModel> result = restemplate.exchange(url, HttpMethod.GET, httpEntity,
					ResponseModel.class);

			return result.getBody();
		} catch (Exception exp) {
			exp.printStackTrace();
			response.setCode("API-99999");
			response.setDes(exp.getMessage());
		}
		return response;
	}
}
