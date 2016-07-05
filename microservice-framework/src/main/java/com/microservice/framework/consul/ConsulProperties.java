package com.microservice.framework.consul;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsulProperties {

	@Value("${service.name}")
	private String servicename;
	
	@Value("${service.port:8080}")
	private int servicePort;
	
	@Value("${service.tag:dev}")
	private String serviceTag;
	
	// @Value("${serviceIp:localhost}")
	// private String serviceIp;

	@Value("${health.url}")
	private String healthUrl;
	
	@Value("${health.interval:10}")
	private int healthInterval;

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public int getServicePort() {
		return servicePort;
	}

	public void setServicePort(int servicePort) {
		this.servicePort = servicePort;
	}

	public String getServiceTag() {
		return serviceTag;
	}

	public void setServiceTag(String serviceTag) {
		this.serviceTag = serviceTag;
	}

	public String getHealthUrl() {
		return healthUrl;
	}

	public void setHealthUrl(String healthUrl) {
		this.healthUrl = healthUrl;
	}

	public int getHealthInterval() {
		return healthInterval;
	}

	public void setHealthInterval(int healthInterval) {
		this.healthInterval = healthInterval;
	}

}
